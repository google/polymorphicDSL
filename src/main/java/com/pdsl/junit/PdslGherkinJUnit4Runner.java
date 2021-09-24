package com.pdsl.junit;

import com.google.common.base.Preconditions;
import com.pdsl.executors.DefaultPolymorphicDslTestExecutor;
import com.pdsl.executors.TraceableTestRunExecutor;
import com.pdsl.gherkin.DefaultGherkinTestSpecificationFactory;
import com.pdsl.gherkin.specifications.GherkinTestSpecificationFactory;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.reports.TestResult;
import com.pdsl.reports.TestRunResults;
import com.pdsl.specifications.FileSystemTestResourceFinder;
import com.pdsl.specifications.GlobPathMatcher;
import com.pdsl.specifications.TestResourceFinder;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.testcases.PreorderTestCaseFactory;
import com.pdsl.testcases.TestCase;
import com.pdsl.testcases.TestCaseFactory;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import com.pdsl.transformers.TestSpecificationHelper;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

import javax.inject.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.PathMatcher;
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
    private static final TestCaseFactory testCaseFactory = new PreorderTestCaseFactory();

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
        Set<URL> testResources = getTestResources(pdslTest);
        // Create the phrase filter that will determine the grammar we use
        PolymorphicDslPhraseFilter polymorphicDslPhraseFilter = new DefaultPolymorphicDslPhraseFilter(pdslTest.parser(),
                pdslTest.lexer(), recognizedBy.dslRecognizerParser(), recognizedBy.dslRecognizerLexer(), recognizedBy.recognizerRule());
        // Make sure that the parser has a rule called 'syntaxRule'
        GherkinTestSpecificationFactory gherkinTestSpecificationFactory = new DefaultGherkinTestSpecificationFactory.Builder(polymorphicDslPhraseFilter)
                .withRecognizerParser(recognizedBy.dslRecognizerParser())
                .withRecognizerLexer(recognizedBy.dslRecognizerLexer())
                .withRecognizerRule(recognizedBy.recognizerRule())
                .build();
        return getTestCases(gherkinTestSpecificationFactory, testResources, pdslTest);
    }

    private Collection<TestCase> getTestCases(PdslTest pdslTest) {
        Set<URL> testResources = getTestResources(pdslTest);
        // Create the phrase filter that will determine the grammar we use
        PolymorphicDslPhraseFilter polymorphicDslPhraseFilter = new DefaultPolymorphicDslPhraseFilter(pdslTest.parser(), pdslTest.lexer());
        GherkinTestSpecificationFactory gherkinTestSpecificationFactory = !pdslTest.skipUnrecognized()
                ? new DefaultGherkinTestSpecificationFactory.Builder(polymorphicDslPhraseFilter)
                    .withRecognizerParser(pdslTest.parser())
                    .withRecognizerLexer(pdslTest.lexer())
                        .withRecognizerRule(PdslTest.DEFAULT_ALL_RULE)
                    .build()
                : new DefaultGherkinTestSpecificationFactory.Builder(polymorphicDslPhraseFilter).build();
        return getTestCases(gherkinTestSpecificationFactory, testResources, pdslTest);
    }

    private Collection<TestCase> getTestCases(GherkinTestSpecificationFactory gherkinTestSpecificationFactory, Set<URL> testResources, PdslTest pdslTest) {
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

    private Set<URL> getTestResources(PdslTest pdslTest) {
        Set<URL> testResources = new HashSet<>();
        // Find the files we will be testing
        PathMatcher pathMatcher = new GlobPathMatcher(getGlobResourcePaths(pdslTest.includesResources()),
                getGlobResourcePaths(pdslTest.excludesResources()));
        TestResourceFinder finder = new FileSystemTestResourceFinder(pathMatcher);
        try {
            Optional<Collection<URL>> resources = finder.scanForTestResources(Paths.get(resourceRoot).toUri().toURL());
            if (resources.isPresent()) {
                testResources.addAll(resources.get());
            }
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(String.format(
                    "Could not find test resources when searching file system at:%n\t%s", resourceRoot),
                    e);
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
            ParseTreeListener parseTreeListener = getParseTreeListener(pdslTest);
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

                PdslExecutorRunner pdslExecutorRunner = new PdslExecutorRunner(getTestClass().getJavaClass(),
                        parseTreeListener, testCases, executor);
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

    private ParseTreeListener getParseTreeListener(PdslTest pdslTest) {
        try {
            Constructor<?> providerConstructor = pdslTest.listener().getDeclaredConstructor();
            return ((Provider<ParseTreeListener>) providerConstructor.newInstance()).get();
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException(String.format("Could not find a default constructor for the Provider<ParseTreeListener> %s%n"
                    + "Note the Provider MUST have a constructor that takes no parameters, but see the below error for more details.", pdslTest.listener().getSimpleName()), e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(String.format("Could not create a %s. Note the provider MUST be public.", pdslTest.listener()), e);
        } catch (InstantiationException | InvocationTargetException e) {
            throw new IllegalStateException(String.format("Something went wrong when trying to create the Parse Tree Listener %s.%n", pdslTest.listener().getSimpleName()), e);
        }
    }

    public String getApplicationName() {
        return applicationName;
    }

    public PdslGherkinJUnit4Runner(Class<?> testClass) throws InitializationError {
        super(testClass);
        PdslGherkinApplication annotation = testClass.getAnnotation(PdslGherkinApplication.class);
        if (annotation.dslRecognizerLexer().equals(EmptyRecognizerLexer.class) ^ annotation.dslRecognizerParser().equals(EmptyRecognizerParser.class)) {
            throw new IllegalArgumentException("If a dslRecognizerLexer or dslRecognizerParser is used at all in the @PdslGherkinAnnotation then both of them must be present!");
        }
        Preconditions.checkNotNull(annotation, String.format("Class run with %s must be annotated with %s!",
                getClass().getSimpleName(), PdslGherkinApplication.class.getSimpleName()));
        Preconditions.checkArgument(!annotation.context().isBlank(), "Context cannot be blank!");
        Preconditions.checkArgument(!annotation.applicationName().isBlank());
        context = annotation.context();
        applicationName = annotation.applicationName();
        this.resourceRoot = annotation.resourceRoot();
        Class<? extends Parser> parser = annotation.dslRecognizerParser();
        Class<? extends Lexer> lexer = annotation.dslRecognizerLexer();
        classWideRecognizerRule = annotation.recognizerRule();
        if (parser.equals(EmptyRecognizerParser.class)) {
            classWideRecognizerParser = Optional.empty();
            classWideRecognizerLexer = Optional.empty();
        } else {
            classWideRecognizerParser = Optional.of(parser);
            classWideRecognizerLexer = Optional.of(lexer);
        }
    }
}
