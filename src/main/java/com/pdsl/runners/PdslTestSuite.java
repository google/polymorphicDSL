package com.pdsl.runners;

import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.reports.TraceableReportGenerator;
import com.pdsl.runners.EmptyReportGenerator;
import com.pdsl.runners.junit.PdslGherkinJUnit4Runner;
import org.junit.runner.Runner;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import org.junit.internal.builders.AllDefaultPossibilitiesBuilder;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PdslTestSuite extends Suite {

        private final List<Runner> runners;
        private final String systemUnderTest;
        private final Optional<Class<? extends TraceableReportGenerator>> reportGenerator;
        private final Map<String /* Application */,
                Map<String /*Context*/, Collection<MetadataTestRunResults> /*Tests*/>> pdslResults = new HashMap<>();
        private static final Logger logger = LoggerFactory.getLogger(PdslGherkinJUnit4Runner.class);


        /**
         * Called reflectively on classes annotated with <code>@RunWith(Suite.class)</code>
         *
         * @param clazz the root class
         * @param builder builds runners for classes in the suite
         */
        public PdslTestSuite(Class<?> clazz, RunnerBuilder builder) throws InitializationError {
            this(builder, clazz, getAnnotatedClasses(clazz));
        }


    /**
     * The <code>PdslSuiteClasses</code> annotation specifies the classes to be run when a class
     * annotated with <code>@RunWith(Suite.class)</code> is run as well as the name of the system under test.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Inherited
    public @interface PdslSuiteClasses {
        /**
         * @return the classes to be run
         */
        String systemUnderTest();
        Class<? extends TraceableReportGenerator> reportGenerator() default EmptyReportGenerator.class;
    }

    private static Class<?>[] getAnnotatedClasses(Class<?> klass) throws InitializationError {
        SuiteClasses suiteClasses = klass.getAnnotation(SuiteClasses.class);
        PdslSuiteClasses pdslSuiteClasses = klass.getAnnotation(PdslSuiteClasses.class);
        if (suiteClasses == null || pdslSuiteClasses == null) {
            throw new InitializationError(String.format("class '%s' must have a SuiteClasses AND PdslSuiteClasses annotations", klass.getName()));
        }
        return suiteClasses.value();
    }

    private static String getSystemUnderTest(Class<?> klass) throws InitializationError {
        PdslSuiteClasses annotation = klass.getAnnotation(PdslSuiteClasses.class);
        if (annotation == null) {
            throw new InitializationError(String.format("class '%s' must have a PdslSuiteClasses annotation", klass.getName()));
        }
        return annotation.systemUnderTest();
    }

    private static Optional<Class<? extends TraceableReportGenerator>> getReportGenerator(Class<?> klass) throws InitializationError {
        PdslSuiteClasses annotation = klass.getAnnotation(PdslSuiteClasses.class);
        if (annotation == null) {
            throw new InitializationError(String.format("class '%s' must have a PdslSuiteClasses annotation", klass.getName()));
        }
        return Optional.ofNullable(annotation.reportGenerator());
    }


        /**
         * Call this when there is no single root class (for example, multiple class names
         * passed on the command line to {@link org.junit.runner.JUnitCore}
         *
         * @param builder builds runners for classes in the suite
         * @param classes the classes in the suite
         */
        public PdslTestSuite(RunnerBuilder builder, Class<?>[] classes) throws InitializationError {
            this(null, builder.runners(null, classes));
        }

        /**
         * Call this when the default builder is good enough. Left in for compatibility with JUnit 4.4.
         *
         * @param klass the root of the suite
         * @param suiteClasses the classes in the suite
         */
        protected PdslTestSuite(Class<?> klass, Class<?>[] suiteClasses) throws InitializationError {
            this(new AllDefaultPossibilitiesBuilder(), klass, suiteClasses);
        }

        /**
         * Called by this class and subclasses once the classes making up the suite have been determined
         *
         * @param builder builds runners for classes in the suite
         * @param klass the root of the suite
         * @param suiteClasses the classes in the suite
         */
        protected PdslTestSuite(RunnerBuilder builder, Class<?> klass, Class<?>[] suiteClasses) throws InitializationError {
            this(klass, builder.runners(klass, suiteClasses));
        }

        /**
         * Called by this class and subclasses once the runners making up the suite have been determined
         *
         * @param klass root of the suite
         * @param runners for each class in the suite, a {@link Runner}
         */
        protected PdslTestSuite(Class<?> klass, List<Runner> runners) throws InitializationError {
            super(klass, runners);
            this.runners = runners;
            this.systemUnderTest = getSystemUnderTest(klass);
            this.reportGenerator = getReportGenerator(klass);
        }

        @Override
        protected List<Runner> getChildren() {
            return runners;
        }

        @Override
        protected Description describeChild(Runner child) {
            return child.getDescription();
        }


        @Override
        public void run(RunNotifier runNotifier) {
            // Run the tests
            super.run(runNotifier);
            // Generate a report
            if (reportGenerator.isPresent() && !(reportGenerator.get().equals(EmptyReportGenerator.class))) {
                Class<? extends TraceableReportGenerator> reportGeneratorClass = reportGenerator.get();
                try {
                    TraceableReportGenerator traceableReportGenerator = reportGeneratorClass.getDeclaredConstructor().newInstance();
                    if (traceableReportGenerator == null)  {
                        throw new InstantiationException("The TraceableReportGenerator Provider returned a null value!");
                    }
										logger.info("Generating report");
                    traceableReportGenerator.generateReport(pdslResults);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    logger.error("Could not generate the report! Error while getting TraceableReportProvider!", e);
                } catch (NoSuchMethodException e) {
                    logger.error(String.format("Could not generate the report because %s does not have an default constructor that takes no arguments!%n" +
                            "Try wrapping the class you want in a new class and use composition and method forwarding to allow reporting for this class", reportGeneratorClass.getSimpleName()));
                } catch  (IOException e) {
                    logger.error("Could not successfully write the report!", e);
                }
            }
        }

        @Override
        protected void runChild(Runner runner, final RunNotifier notifier) {
            runner.run(notifier);
            if (runner instanceof PdslGherkinJUnit4Runner) {
                PdslGherkinJUnit4Runner application = (PdslGherkinJUnit4Runner) runner;
                Map<String, Collection<MetadataTestRunResults>> suiteResults =
                        pdslResults.computeIfAbsent(application.getApplicationName(), k -> new HashMap<>());
                // Merge the test results from the child suite
                application.getMetaDataTestRunResults().forEach((k, v) -> suiteResults.merge(k, v, (suiteK, suiteV) -> {
                        suiteV.addAll(v);
                        return suiteV;
                    }
                ));
            }
        }
}
