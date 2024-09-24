package com.pdsl.runners.junit;

import com.google.common.base.Preconditions;
import com.pdsl.exceptions.PolymorphicDslTestResourceException;
import com.pdsl.executors.InterpreterObj;
import com.pdsl.executors.TraceableTestRunExecutor;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.reports.TestResult;
import com.pdsl.reports.TestRunResults;
import com.pdsl.runners.*;
import com.pdsl.specifications.TestResourceFinder;
import com.pdsl.specifications.TestResourceFinderGenerator;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.testcases.SharedTestCase;
import com.pdsl.testcases.SharedTestSuite;
import com.pdsl.testcases.TestCase;
import com.pdsl.testcases.TestCaseFactory;
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
import java.net.URI;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class PdslJUnit4ConfigurableRunner extends BlockJUnit4ClassRunner {

    private static final String FACTORY_PROVIDER_FIELD = "testCaseFactoryProvider";
    private static final String SPECIFICATION_FACTORY_PROVIDER = "specificationFactoryProvider";
    private static final ExecutorHelper executorHelper = PdslConfigurationHelper.getExecutorHelper(new JUnitConfigurationAccessor());
    private static final SharedTestSuiteVisitor SHARED_TEST_SUITE_VISITOR = new SharedTestSuiteVisitor();

    private final PdslConfiguration pdslConfiguration;
    private final Provider<? extends TraceableTestRunExecutor> testRunExecutor;
    private final Collection<MetadataTestRunResults> results = new ArrayList<>();

    public PdslJUnit4ConfigurableRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
        PdslConfiguration pdslConfiguration = testClass.getAnnotation(PdslConfiguration.class);
        final String exampleConfiguration = String.format(
                "@PdslConfiguration(%n"
                + "%s: <your specification generator>,%n"
                + "%s: <testCaseFactoryProvider>%n"
                + ")%n"
                + "public class %s {%n", SPECIFICATION_FACTORY_PROVIDER, FACTORY_PROVIDER_FIELD, testClass.getName());
        final String className = getClassNameWithoutPackage(testClass);
        // Check to see if required fields are available
        if (pdslConfiguration == null) {
            throw new InitializationError(String.format("The class %s does not have the required @PdslConfiguration annotation.%n"
                    + "e.g.:%n%s", exampleConfiguration, className));
        }
        List<String> missingRequiredFields = new ArrayList<>();
        if (pdslConfiguration.testCaseFactoryProvider() == null) {
            missingRequiredFields.add(FACTORY_PROVIDER_FIELD);
        }
        if (pdslConfiguration.specificationFactoryProvider() == null) {
            missingRequiredFields.add(SPECIFICATION_FACTORY_PROVIDER);
        }
        if (!missingRequiredFields.isEmpty()) {
            String requiredFields = missingRequiredFields.stream().collect(Collectors.joining(String.format("%n\t")));
            throw new InitializationError(String.format("@PdslConfiguration is missing the required field(s):%n\t%s%n"
            + "Please update it to have these fields:%n%s", requiredFields, exampleConfiguration));
        }
        // Verify that the factories have default constructors we can call
        ExecutorHelper.PdslProvidersDto providers = executorHelper.makePdslElements(pdslConfiguration);
        this.pdslConfiguration = testClass.getAnnotation(PdslConfiguration.class);
        this.testRunExecutor = providers.getTestRunExecutor();
    }

    private static String getClassNameWithoutPackage(Class<?> clazz) {
        String[] classNameComponents = clazz.getName().split("\\.");
        return classNameComponents[classNameComponents.length - 1];
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
        List<FrameworkMethod> frameworkMethods = executorHelper.computePdslTestMethods(getTestClass());
        frameworkMethods.addAll(getTestClass().getAnnotatedMethods(Test.class));
        return List.copyOf(frameworkMethods);
    }

    @Override
    protected void runChild(final FrameworkMethod method, RunNotifier notifier) {
        PdslTest pdslTest = method.getAnnotation(PdslTest.class);
        if (pdslTest != null) {
            notifier.fireTestStarted(describeChild(method));
            RecognizerParams<SharedTestSuite> recognizerParams = JUnit4RecognizerParamsConverter.convert(
                    new JUnit4RecognizerParamsConverter.PdslTestDto(pdslTest, Optional.ofNullable(method.getAnnotation(RecognizedBy.class))),
                    pdslConfiguration);
            SharedTestSuite sharedTestSuite = SHARED_TEST_SUITE_VISITOR.recognizerParamsOperation(recognizerParams);
            try {
                PdslExecutorRunner pdslExecutorRunner = new PdslExecutorRunner(getTestClass().getJavaClass(), sharedTestSuite.getSharedTestCaseList(), testRunExecutor.get(), pdslConfiguration.context());
                pdslExecutorRunner.run(notifier);
                List<MetadataTestRunResults> methodResults = pdslExecutorRunner.getMetadataTestRunResults();
                // Check if the PDSL framework had any test failures. If so handle them here.
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
                throw new IllegalStateException("Could not initialize PdslExecutorRunner!", initializationError);
            }
        } else {
            super.runChild(method, notifier);
        }
    }
}
