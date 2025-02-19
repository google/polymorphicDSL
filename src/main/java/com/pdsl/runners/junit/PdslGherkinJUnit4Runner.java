package com.pdsl.runners.junit;

import com.google.common.base.Preconditions;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.runners.*;
import com.pdsl.testcases.PreorderTestCaseFactory;
import com.pdsl.testcases.TestCaseFactory;
import org.junit.Test;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

import java.util.*;

public class PdslGherkinJUnit4Runner extends BlockJUnit4ClassRunner {

    private static final TestCaseFactory testCaseFactory = new PreorderTestCaseFactory();
    private final String context;
    private final String applicationName;
    private final Collection<MetadataTestRunResults> results = new ArrayList<>();
    private final PdslGherkinApplication pdslGherkinApplication;

    public PdslGherkinJUnit4Runner(Class<?> testClass) throws InitializationError {
        super(testClass);
        pdslGherkinApplication = testClass.getAnnotation(PdslGherkinApplication.class);
        Preconditions.checkNotNull(pdslGherkinApplication, String.format("Class run with %s must be annotated with %s!",
                getClass().getSimpleName(), PdslConfiguration.class.getSimpleName()));
        Preconditions.checkArgument(!pdslGherkinApplication.context().isBlank(), "Context cannot be blank!");
        Preconditions.checkArgument(!pdslGherkinApplication.applicationName().isBlank());
        context = pdslGherkinApplication.context();
        applicationName = pdslGherkinApplication.applicationName();
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
                            recognizedBy.recognizerRule(), pdslTestMethod.getDeclaringClass().getSimpleName(), pdslTestMethod.getName(), recognizedBy.dslRecognizerParser().getSimpleName()));
                }
            }
        }
        frameworkMethods.addAll(getTestClass().getAnnotatedMethods(Test.class));
        return List.copyOf(frameworkMethods);
    }

    @Override
    protected void runChild(final FrameworkMethod method, RunNotifier notifier) {
        PdslTest pdslTest = method.getAnnotation(PdslTest.class);
        if (pdslTest != null) {
            // PdslGherkinApplication is essentially just a simplified PdslConfiguration
            // To simplify the code base, just use composition
            PdslConfiguration pdslConfiguration = JUnit4RecognizerParamsConverter.convert(pdslGherkinApplication);
            PdslJUnit4ConfigurableRunner runner = null;
            try {
                runner = new PdslJUnit4ConfigurableRunner(getTestClass().getJavaClass(), pdslConfiguration, new PreorderTestCaseFactory.DefaultProvider());
            } catch (InitializationError e) {
                throw new IllegalStateException("An error occurred while dispatching the PdslGherkinApplication test to the PdslJUnit4ConfigurableRunner class!", e);
            }
            runner.run(notifier);
        } else {
            super.runChild(method, notifier);
        }
    }

    public String getApplicationName() {
        return applicationName;
    }
}
