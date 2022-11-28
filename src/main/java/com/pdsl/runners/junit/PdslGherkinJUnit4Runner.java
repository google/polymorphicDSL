package com.pdsl.runners.junit;

import com.google.common.base.Preconditions;
import com.pdsl.executors.DefaultPolymorphicDslTestExecutor;
import com.pdsl.executors.TraceableTestRunExecutor;
import com.pdsl.gherkin.DefaultGherkinTestSpecificationFactory;
import com.pdsl.gherkin.specifications.GherkinTestSpecificationFactory;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.reports.TestResult;
import com.pdsl.reports.TestRunResults;
import com.pdsl.runners.*;
import com.pdsl.specifications.*;
import com.pdsl.testcases.PreorderTestCaseFactory;
import com.pdsl.testcases.TestCase;
import com.pdsl.testcases.TestCaseFactory;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.junit.Test;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

import javax.inject.Provider;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class PdslGherkinJUnit4Runner extends BlockJUnit4ClassRunner {

    private final String context;
    private final String applicationName;
    private final String resourceRoot;
    private final Optional<? extends Class<? extends Parser>> classWideRecognizerParser;
    private final Optional<? extends Class<? extends Lexer>> classWideRecognizerLexer;
    private final String classWideRecognizerRule;
    private final Collection<MetadataTestRunResults> results = new ArrayList<>();
    private final Provider<? extends TestResourceFinderGenerator> resourceFinderGeneratorProvider;
    private final Provider<? extends TraceableTestRunExecutor> executorProvider;
    private final ExecutorHelper executorHelper;

    public PdslGherkinJUnit4Runner(Class<?> testClass) throws InitializationError {
        super(testClass);
        PdslGherkinApplication annotation = testClass.getAnnotation(PdslGherkinApplication.class);
        Preconditions.checkNotNull(annotation, String.format("Class run with %s must be annotated with %s!",
                getClass().getSimpleName(), PdslConfiguration.class.getSimpleName()));
        Preconditions.checkArgument(!annotation.context().isBlank(), "Context cannot be blank!");
        Preconditions.checkArgument(!annotation.applicationName().isBlank());
        executorHelper = PdslConfigurationHelper.getExecutorHelper(new JUnitConfigurationAccessor());
        ExecutorHelper.PdslProvidersDto providers = executorHelper.makePdslElements(new PdslGherkinHelperAnnotation(annotation));
        this.classWideRecognizerLexer = providers.getClassWideLexerRecognizerOptional();
        this.classWideRecognizerParser = providers.getClassWideParserRecognizerOptional();
        this.resourceFinderGeneratorProvider = providers.getResourceFinder();
        this.executorProvider = providers.getTestRunExecutor();
        context = annotation.context();
        applicationName = annotation.applicationName();
        this.resourceRoot = annotation.resourceRoot();
        Class<? extends Parser> parser = annotation.dslRecognizerParser();
        Class<? extends Lexer> lexer = annotation.dslRecognizerLexer();
        classWideRecognizerRule = annotation.recognizerRule();
    }
    private static final TestCaseFactory testCaseFactory = new PreorderTestCaseFactory();

    public Object createPdslProviderFromClass(Class<?> provider, String configurationField) {
        try {
            return  provider.getConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            throw new PolymorphicDslJUnitException(String.format("Error with parameter(s) in the @PdslGherkinApplication!%n"
                            + "The field %s must have a default constructor.%n"
                            + "Only the default constructor will be invoked by the JUnit runner."
                            + "CLASS THAT NEEDS DEFAULT CONSTRUCTOR: %s",
                    configurationField, provider.getName()), e);
        } catch (InvocationTargetException | InstantiationException e) {
            throw new PolymorphicDslJUnitException(String.format("Error creating %s!", provider.getName()), e);
        } catch (IllegalAccessException e) {
            throw new PolymorphicDslJUnitException(String.format("Could not access constructor for %s. Make sure it is public!", provider.getName()), e);
        }
    }

    public Map<String, Collection<MetadataTestRunResults>> getMetaDataTestRunResults() {
        return Map.of(context, results);
    }

    /**
     * Returns the methods that run tests.
     * <p>
     * This includes all standard JUnit4 methods annotated with @Test as well as any annotated with @PdslTest.
     * In the event that PdslTests are returned they will be checked for the presence of a RecognizeBy annotation and
     * have the parameters validated as well. In the event there is an issue with verification an IllegalArgumentException
     * will be thrown.
     */
    @Override
    protected List<FrameworkMethod> computeTestMethods() {
        List<FrameworkMethod> frameworkMethods = new ArrayList<>();
            // Validate annotations are valid
        frameworkMethods.addAll(
                getTestClass().getAnnotatedMethods(PdslTest.class));
        for (FrameworkMethod pdslTestMethod : frameworkMethods) {
            RecognizedBy recognizedBy = pdslTestMethod.getAnnotation(RecognizedBy.class);
            if (recognizedBy != null) {
                // Make sure that the parser has a rule called 'syntaxRule'
                boolean hasRecognizerRule = Arrays.stream(recognizedBy.dslRecognizerParser().getMethods())
                        .anyMatch(m -> m.getName().equals(recognizedBy.recognizerRule()));
                if (!hasRecognizerRule) {
                    throw new IllegalArgumentException(String.format("RecognizedBy is using a parser missing the expected rule '%s'%n\tClass: %s%n\tMethod: %s%n\tParser: %s%n",
                            recognizedBy.recognizerRule(),pdslTestMethod.getDeclaringClass().getSimpleName(), pdslTestMethod.getName(), recognizedBy.dslRecognizerParser().getSimpleName()));
                }
            }
        }
        frameworkMethods.addAll(getTestClass().getAnnotatedMethods(Test.class));
        return List.copyOf(frameworkMethods);
    }

    private Collection<TestCase> getTestCases(PdslTest pdslTest, RecognizedBy recognizedBy) {
        Preconditions.checkNotNull(recognizedBy);
        Set<URI> testResources = getTestResources(pdslTest);
        // Create the phrase filter that will determine the grammar we use
        PolymorphicDslPhraseFilter polymorphicDslPhraseFilter = new DefaultPolymorphicDslPhraseFilter(pdslTest.parser(),
                pdslTest.lexer(), recognizedBy.dslRecognizerParser(), recognizedBy.dslRecognizerLexer(), pdslTest.startRule(), recognizedBy.recognizerRule());
        // Make sure that the parser has a rule called 'syntaxRule'
        GherkinTestSpecificationFactory gherkinTestSpecificationFactory = new DefaultGherkinTestSpecificationFactory.Builder(polymorphicDslPhraseFilter)
                .withRecognizerParser(recognizedBy.dslRecognizerParser())
                .withRecognizerLexer(recognizedBy.dslRecognizerLexer())
                .withRecognizerRule(recognizedBy.recognizerRule())
                .build();
        return getTestCases(gherkinTestSpecificationFactory, testResources, pdslTest);
    }

    private Collection<TestCase> getTestCases(PdslTest pdslTest) {
        Set<URI> testResources = getTestResources(pdslTest);
        // Create the phrase filter that will determine the grammar we use
        PolymorphicDslPhraseFilter polymorphicDslPhraseFilter = new DefaultPolymorphicDslPhraseFilter(pdslTest.parser(), pdslTest.lexer());
        GherkinTestSpecificationFactory gherkinTestSpecificationFactory = /*!pdslTest.skipUnrecognized()
                ? new DefaultGherkinTestSpecificationFactory.Builder(polymorphicDslPhraseFilter)
                    .withRecognizerParser(pdslTest.parser())
                    .withRecognizerLexer(pdslTest.lexer())
                        .withRecognizerRule(PdslTest.DEFAULT_ALL_RULE)
                    .build()
                :*/ new DefaultGherkinTestSpecificationFactory.Builder(polymorphicDslPhraseFilter).build();
        return getTestCases(gherkinTestSpecificationFactory, testResources, pdslTest);
    }

    private Collection<TestCase> getTestCases(GherkinTestSpecificationFactory gherkinTestSpecificationFactory, Set<URI> testResources, PdslTest pdslTest) {
        Optional<Collection<TestSpecification>> gherkinTestSpecifications = gherkinTestSpecificationFactory.getTestSpecifications(testResources);
        if (gherkinTestSpecifications.isPresent()) {
            gherkinTestSpecifications = gherkinTestSpecificationFactory.filterGherkinTestSpecificationsByTagExpression(
                    gherkinTestSpecifications.get(),
                    pdslTest.tags());
        }
        // Previous if block may have changed the collection to be empty after filtering
        if (gherkinTestSpecifications.isEmpty()) {
            throw new IllegalArgumentException(String.format(
                    "Feature files were found but they were all filtered out!%n\tTag Filter: %s", pdslTest.tags()));
        }
        return testCaseFactory.processTestSpecification(gherkinTestSpecifications.get());
    }

    private Set<URI> getTestResources(PdslTest pdslTest) {
        TestResourceFinder finder = resourceFinderGeneratorProvider.get().get(pdslTest.includesResources(), pdslTest.excludesResources());
        Set<URI> testResources = new HashSet<>();
        // Find the files we will be testing
        Optional<Collection<URI>> resources = finder.scanForTestResources(Paths.get(resourceRoot).toUri());
        if (resources.isPresent()) {
            testResources.addAll(resources.get());
        }
        if (testResources.isEmpty()) {
            throw new IllegalArgumentException(String.format(
                    "No feature files were found!%n\tResource Root: %s%n%n\tincluding: %s%n%n\texcluding: %s",
                    resourceRoot, Arrays.deepToString(pdslTest.includesResources()), Arrays.deepToString(pdslTest.excludesResources())));
        }
        return testResources;
    }

    private List<String> getGlobResourcePaths(String[] resources) {
        String root = !resourceRoot.endsWith("/") ? "**/" + resourceRoot + "/" : "**/" + resourceRoot;
        return Arrays.stream(resources).map(root::concat).collect(Collectors.toList());
    }

    @Override
    public void run(RunNotifier runNotifier) {
        for (FrameworkMethod method : getChildren()) {
            if (isIgnored(method)) {
                runNotifier.fireTestIgnored(describeChild(method));
            } else {
                runChild(method, runNotifier);
            }
        }
    }

    @Override
    protected void runChild(final FrameworkMethod method, RunNotifier notifier) {
        PdslTest pdslTest = method.getAnnotation(PdslTest.class);
        if (pdslTest != null) {
            notifier.fireTestStarted(describeChild(method));
            RecognizedBy recognizedBy = method.getAnnotation(RecognizedBy.class);
            Collection<TestCase> testCases = null;
            try {
                testCases = recognizedBy == null ? getTestCases(pdslTest) : getTestCases(pdslTest, recognizedBy);
            } catch (RuntimeException e) { // e.g., an issue checking the grammar syntax
                notifier.fireTestFailure(new Failure(describeChild(method), e));
                return;
            }
            //TODO: Allow the input streams for the executor to be customized
            final TraceableTestRunExecutor executor = new DefaultPolymorphicDslTestExecutor();
            try {
                Preconditions.checkArgument(!testCases.isEmpty(),
                    "Somehow no test cases were produced from the features! This is likely an error with the PDSL framework");

                ExecutorHelper.ParseTreeTraversal traversal = executorHelper.getParseTreeTraversal(pdslTest);
                PdslExecutorRunner pdslExecutorRunner;
                if (traversal.getVisitor().isPresent()) {
                    pdslExecutorRunner = new PdslExecutorRunner(getTestClass().getJavaClass(),
                    traversal.getVisitor().get(), testCases, executor, context);
                } else {
                    pdslExecutorRunner = new PdslExecutorRunner(getTestClass().getJavaClass(),
                            traversal.getListener().orElseThrow(), testCases, executor, context);
                }
                pdslExecutorRunner.run(notifier);
                List<MetadataTestRunResults> methodResults = pdslExecutorRunner.getMetadataTestRunResults();
                if (!methodResults.stream().anyMatch(r -> r.failingTestTotal() > 0)) {
                    notifier.fireTestFinished(describeChild(method));
                } else {
                    List<TestResult> failureReasons = methodResults.stream().filter(r -> r.failingTestTotal() > 0)
                            .map(MetadataTestRunResults::getTestResults)
                            .flatMap(Collection::stream)
                            .collect(Collectors.toList());
                    Preconditions.checkArgument(!failureReasons.isEmpty());
                    notifier.fireTestFailure(new Failure(describeChild(method), new TestRunResults.FailedTestResults(failureReasons)));
                }
                results.addAll(methodResults);
            } catch (InitializationError initializationError) {
                throw new IllegalStateException("Could not intialize PdslExecutorRunner!", initializationError);
            }
        } else {
            super.runChild(method, notifier);
        }
    }

    public String getApplicationName() {
        return applicationName;
    }
}
