package org.junit.jupiter.engine.descriptor;

import com.pdsl.executors.TraceableTestRunExecutor;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.testcases.TestCase;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * A wrapper class for a PDSL TestCase that allows integration with the JUnit5 framework.
 *
 * A PdslExecutable is intended to be used with a JUnit @TestTemplate.
 * This allows other JUnit5 features (such as hooks, disabled tests, etc) to
 * be fully supported with PDSL.
 *
 * The "execute()" method must be called inside the @TestTemplate method.
 * By default a runtime exception will be thrown if there is a test failure.
 *
 */
public class PdslExecutable {

        private final TestCase pdslTest;
        private final TraceableTestRunExecutor executor;
        private final Optional<Supplier<ParseTreeVisitor<?>>> visitor;
        private final Optional<Supplier<ParseTreeListener>> listener;
        private final String context;

        PdslExecutable(TestCase pdslTest, TraceableTestRunExecutor executor, Supplier<ParseTreeVisitor<?>> visitor, String context) {
            this.pdslTest = pdslTest;
            this.executor = executor;
            this.visitor = Optional.of(visitor);
            this.listener = Optional.empty();
            this.context = context;
        }

        PdslExecutable(TestCase pdslTest, TraceableTestRunExecutor executor, String context, Supplier<ParseTreeListener> listener) {
            this.pdslTest = pdslTest;
            this.executor = executor;
            this.visitor = Optional.empty();
            this.listener = Optional.of(listener);
            this.context = context;
        }

        /**
         * Runs a PDSL TestCase and returns MetadataTestRunResults if successful.
         *
         * A single TestCase will be processed with the standard implementation.
         *
         * A runtime exception will be thrown if any failures are encounted by the test.
         */
        public MetadataTestRunResults execute() {
            MetadataTestRunResults results =
                visitor.isPresent() ? executor.runTestsWithMetadata(List.of(pdslTest), visitor.get().get(), context)
                        : executor.runTestsWithMetadata(List.of(pdslTest), listener.get().get(), context);

            if (results.failingTestTotal() > 0) {
                Optional<Throwable> throwable = results.getTestResults().stream()
                        .filter(r -> r.getFailureReason().isPresent())
                        .map(r -> r.getFailureReason().get())
                        .findFirst();
                if (throwable.isPresent()) {
                    throw new TestFailure(throwable.get());
                }
                throw new IllegalStateException("A test failed while executing! (No stack trace was produced by the failure)");
            }

            return results;
        }

        /** Returns the text representation of the test case. */
        public String getTestTitle() {
            return pdslTest.getTestTitle();
        }

        /**
         * The purpose of that getter it to provide access to the `TestCase` instance inside the test case execution (@TestTemplate).
         *
         * @return
         */
        public TestCase getTestCase() {
          return pdslTest;
        }

        private static class TestFailure extends RuntimeException {
            TestFailure(Throwable t) {
                super(t);
            }
        }

}
