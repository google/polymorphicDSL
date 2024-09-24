package com.pdsl.runners.junit;

import com.google.common.base.Preconditions;
import com.pdsl.executors.DefaultPolymorphicDslTestExecutor;
import com.pdsl.executors.InterpreterObj;
import com.pdsl.executors.TraceableTestRunExecutor;
import com.pdsl.gherkin.DefaultGherkinTestSpecificationFactory;
import com.pdsl.gherkin.specifications.GherkinTestSpecificationFactory;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.reports.TestResult;
import com.pdsl.runners.*;
import com.pdsl.specifications.*;
import com.pdsl.testcases.PreorderTestCaseFactory;
import com.pdsl.testcases.SharedTestSuite;
import com.pdsl.testcases.TestCase;
import com.pdsl.testcases.TestCaseFactory;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.junit.AssumptionViolatedException;
import org.junit.Test;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runner.notification.StoppedByUserException;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import javax.inject.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class PdslGherkinJUnit4Runner extends BlockJUnit4ClassRunner {

    private final String context;
    private final String applicationName;
    private final String resourceRoot;
    private final Collection<MetadataTestRunResults> results = new ArrayList<>();
    private final Provider<? extends TestResourceFinderGenerator> resourceFinderGeneratorProvider;
    private final ExecutorHelper executorHelper;

    public PdslGherkinJUnit4Runner(Class<?> testClass) throws InitializationError {
        super(testClass);
        System.out.print("JS23001 : 50");
        PdslGherkinApplication annotation = testClass.getAnnotation(PdslGherkinApplication.class);
        Preconditions.checkNotNull(annotation, String.format("Class run with %s must be annotated with %s!",
                getClass().getSimpleName(), PdslConfiguration.class.getSimpleName()));
        Preconditions.checkArgument(!annotation.context().isBlank(), "Context cannot be blank!");
        Preconditions.checkArgument(!annotation.applicationName().isBlank());
        executorHelper = PdslConfigurationHelper.getExecutorHelper(new JUnitConfigurationAccessor());
        ExecutorHelper.PdslProvidersDto providers = executorHelper.makePdslElements(new PdslGherkinHelperAnnotation(annotation));
        this.resourceFinderGeneratorProvider = providers.getResourceFinder();
        context = annotation.context();
        applicationName = annotation.applicationName();
        this.resourceRoot = annotation.resourceRoot();
    }

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

    private List<List<TestCase>> getTestCases(PdslTest pdslTest, RecognizedBy recognizedBy) {
        Preconditions.checkNotNull(recognizedBy);
        Set<URI> testResources = getTestResources(pdslTest);
        List<List<TestCase>> main = new ArrayList<>();

        /**
         * If the [Interpreter] collection:
         * 1) is NULL or empty we can use old (default) approach
         * 2) is NOT NULL use the multiple Lexer/Parser
         */
        if(pdslTest.interpreters() == null || pdslTest.interpreters().length == 0) {
            // Create the phrase filter that will determine the grammar we use
            PolymorphicDslPhraseFilter polymorphicDslPhraseFilter = new DefaultPolymorphicDslPhraseFilter(
                pdslTest.parser(), pdslTest.lexer(), recognizedBy.dslRecognizerParser(),
                recognizedBy.dslRecognizerLexer(), pdslTest.startRule(),
                recognizedBy.recognizerRule());

            // Make sure that the parser has a rule called 'syntaxRule'
            GherkinTestSpecificationFactory gherkinTestSpecificationFactory = new DefaultGherkinTestSpecificationFactory.Builder(polymorphicDslPhraseFilter)
                .withRecognizerParser(recognizedBy.dslRecognizerParser())
                .withRecognizerLexer(recognizedBy.dslRecognizerLexer())
                .withRecognizerRule(recognizedBy.recognizerRule())
                .build();

            main.add(getTestCases(gherkinTestSpecificationFactory, testResources, pdslTest));
            return main;
        }
            Preconditions.checkArgument(pdslTest.interpreters().length %2 == 0,
                "The size of alternative interpreters (Lexer/Parser; Visitor/Listener) in [com.pdsl.runners.@PdslTest], should be even! Actual size: " + pdslTest.interpreters().length);
        for(Interpreter interpreter : pdslTest.interpreters()) {

                // Create the phrase filter that will determine the grammar we use
                PolymorphicDslPhraseFilter polymorphicDslPhraseFilter = new DefaultPolymorphicDslPhraseFilter(
                    interpreter.parser(), interpreter.lexer(), recognizedBy.dslRecognizerParser(),
                    recognizedBy.dslRecognizerLexer(), pdslTest.startRule(),
                    recognizedBy.recognizerRule());

                // Make sure that the parser has a rule called 'syntaxRule'
                GherkinTestSpecificationFactory gherkinTestSpecificationFactory = new DefaultGherkinTestSpecificationFactory.Builder(polymorphicDslPhraseFilter)
                    .withRecognizerParser(recognizedBy.dslRecognizerParser())
                    .withRecognizerLexer(recognizedBy.dslRecognizerLexer())
                    .withRecognizerRule(recognizedBy.recognizerRule())
                    .build();

                main.add(getTestCases(gherkinTestSpecificationFactory, testResources, pdslTest));
            }
        return main;
    }

    private List<List<TestCase>> getTestCases(PdslTest pdslTest) {
        Set<URI> testResources = getTestResources(pdslTest);
        List<List<TestCase>> main = new ArrayList<>();

        /**
         * If the [Interpreter] collection:
         * 1) is NULL or empty we can use old (default) approach
         * 2) is NOT NULL use the multiple Lexer/Parser
         */
        if(pdslTest.interpreters() == null || pdslTest.interpreters().length == 0) {
            // Create the phrase filter that will determine the grammar we use
            PolymorphicDslPhraseFilter polymorphicDslPhraseFilter = new DefaultPolymorphicDslPhraseFilter(pdslTest.parser(), pdslTest.lexer());
            GherkinTestSpecificationFactory gherkinTestSpecificationFactory = /*!pdslTest.skipUnrecognized()
                ? new DefaultGherkinTestSpecificationFactory.Builder(polymorphicDslPhraseFilter)
                    .withRecognizerParser(pdslTest.parser())
                    .withRecognizerLexer(pdslTest.lexer())
                        .withRecognizerRule(PdslTest.DEFAULT_ALL_RULE)
                    .build()
                :*/ new DefaultGherkinTestSpecificationFactory.Builder(polymorphicDslPhraseFilter).build();

            main.add(getTestCases(gherkinTestSpecificationFactory, testResources, pdslTest));
            return main;
        }
            Preconditions.checkArgument(pdslTest.interpreters().length %2 == 0,
                "The size of alternative interpreters (Lexer/Parser; Visitor/Listener) in [com.pdsl.runners.@PdslTest], should be even! Actual size: " + pdslTest.interpreters().length);

            for(Interpreter interpreter : pdslTest.interpreters()) {
                PolymorphicDslPhraseFilter polymorphicDslPhraseFilter = new DefaultPolymorphicDslPhraseFilter(
                    interpreter.parser(), interpreter.lexer());

                GherkinTestSpecificationFactory gherkinTestSpecificationFactory = new DefaultGherkinTestSpecificationFactory.Builder(polymorphicDslPhraseFilter).build();

                main.add(getTestCases(gherkinTestSpecificationFactory, testResources, pdslTest));
            }

        return main;
    }

    private List<TestCase> getTestCases(GherkinTestSpecificationFactory gherkinTestSpecificationFactory, Set<URI> testResources, PdslTest pdslTest) {
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
        return testCaseFactory.processTestSpecification(gherkinTestSpecifications.get()).stream().collect(Collectors.toUnmodifiableList());
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

    @Override
    protected void runChild(final FrameworkMethod method, RunNotifier notifier) {
        PdslTest pdslTest = method.getAnnotation(PdslTest.class);
        if (pdslTest != null) {
            RecognizedBy recognizedBy = method.getAnnotation(RecognizedBy.class);
            List<List<TestCase>> testCasesList;
            // This should be a test suite, but because we want the lifecycle benefits of the
            // BlockJunit4Runner class the results will display strangely

            // There isn't a great way to solve this without rewriting large swathes of code. It seems a lot of the
            // trouble exists in the JUnit logic implemented in the logic of IDEs like IntelliJ. They seem to make
            // assumptions on how JUnit4 tests will be run and code changes we make in this code appear not to affect
            // how they render hierarchically.
            Description description = super.describeChild(method);
           notifier.fireTestStarted(description);

            try {
                testCasesList = recognizedBy == null ? getTestCases(pdslTest) : getTestCases(pdslTest, recognizedBy);
            } catch (RuntimeException e) { // e.g., an issue checking the grammar syntax
                notifier.fireTestAssumptionFailed(new Failure(describeChild(method), e));
                return;
            }
            //TODO: Allow the input streams for the executor to be customized
            final TraceableTestRunExecutor executor = new DefaultPolymorphicDslTestExecutor();

            try {
                Preconditions.checkArgument(!testCasesList.isEmpty(), "Somehow no test cases were produced from the features! This is likely an error with the PDSL framework");

                List<ExecutorHelper.ParseTreeTraversal> traversals = executorHelper.getParseTreeTraversal(pdslTest);
                    List<InterpreterObj> interpreterObjs = traversals.stream().map(v -> v.getVisitor().isPresent()
                        ? new InterpreterObj(v.getVisitor().get())
                        : new InterpreterObj(v.getListener().get())).collect(Collectors.toUnmodifiableList());

                Preconditions.checkArgument(traversals.size() == testCasesList.size(),
                    "The size of TC's and provided interpreters (Listener/Visitor) should be the same.");

                SharedTestSuite sharedTestSuite = SharedTestSuite.of(testCasesList,interpreterObjs);


                PdslExecutorRunner pdslExecutorRunner = new PdslExecutorRunner(getTestClass().getJavaClass(), sharedTestSuite.getSharedTestCaseList(), executor, context);
                        pdslExecutorRunner.run(notifier);
                List<MetadataTestRunResults> methodResults = new ArrayList<>(pdslExecutorRunner.getMetadataTestRunResults());
                results.addAll(methodResults);
                // The results rendered in IDEs will be the individual test cases like you'd expect, but also
                // each individual method that ran them. This is undesirable, but it looks like it would require a
                // custom plugin made for the IDE to solve it.
                // To make things less awkward, make the test suite fail if one of it's test failed, so we don't see a
                // passing test suite and failed test case.
                Optional<MetadataTestRunResults> failingTest = pdslExecutorRunner.getMetadataTestRunResults().stream().filter(r -> r.failingTestTotal() > 0).findFirst();
                failingTest.ifPresent(metadataTestRunResults -> notifier.fireTestFailure(new Failure(describeChild(method),
                                metadataTestRunResults.getTestResults().stream()
                                        .map(TestResult::getFailureReason)
                                        .filter(Optional::isPresent)
                                        .findFirst().orElseThrow().orElseThrow()
                        )
                ));
                notifier.fireTestFinished(description);
            } catch (InitializationError initializationError) {
                throw new IllegalStateException("Could not initialize PdslExecutorRunner!", initializationError);
            }
        } else {
            super.runChild(method, notifier);
        }
    }

    public String getApplicationName() {
        return applicationName;
    }
}
