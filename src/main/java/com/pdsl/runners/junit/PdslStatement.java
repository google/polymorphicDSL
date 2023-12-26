package com.pdsl.runners.junit;

import com.pdsl.executors.TraceableTestRunExecutor;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.testcases.SharedTestCase;
import java.util.List;
import org.junit.runners.model.Statement;

import java.util.Optional;

class PdslStatement extends Statement {

    private MetadataTestRunResults results;
    //private final List<TestCase> testCases;
    //private final List<ExecutorHelper.ParseTreeTraversal> parseTreeTraversal;
    // private final Optional<ParseTreeListener> parseTreeListener;
    // private final Optional<ParseTreeVisitor<?>> parseTreeVisitor;

  private final SharedTestCase sharedTestCase;
    private final String context;
    private final TraceableTestRunExecutor executor;

  public PdslStatement(SharedTestCase sharedTestCase, String context, TraceableTestRunExecutor executor) {
    //this.testCases = testCases;
    //this.parseTreeTraversal = parseTreeTraversal;//Optional.of(parseTreeListener);
    this.sharedTestCase = sharedTestCase;
    this.context = context;
    this.executor = executor;
    //parseTreeVisitor = Optional.empty();
  }

    // public PdslStatement(List<TestCase> testCases, List<ExecutorHelper.ParseTreeTraversal> parseTreeTraversal, String context, TraceableTestRunExecutor executor) {
    //     //this.testCases = testCases;
    //     //this.parseTreeTraversal = parseTreeTraversal;//Optional.of(parseTreeListener);
    //     this.context = context;
    //     this.executor = executor;
    //     //parseTreeVisitor = Optional.empty();
    // }

    // public PdslStatement(Collection<TestCase> testCases, ParseTreeVisitor<?> parseTreeVisitor, String context, TraceableTestRunExecutor executor) {
    //     this.testCases = testCases;
    //     this.parseTreeVisitor = Optional.of(parseTreeVisitor);
    //     parseTreeListener = Optional.empty();
    //     this.context = context;
    //     this.executor = executor;
    // }

    public Optional<MetadataTestRunResults> getResults() {
        return Optional.ofNullable(results);
    }

    @Override
     public void evaluate() throws Throwable {
            // if (parseTreeListener.isPresent()) {

      //TODO
                // List<Interpreter> interpreters = parseTreeTraversal.stream().map(v -> v.getListener().isPresent()
                //         ? new Interpreter(v.getListener().get()) : new Interpreter(v.getVisitor().get())).collect(Collectors.toList());

                //List<SharedTestCase> sharedTestCase = interpreters.stream().map()

                results = executor.runTestsWithMetadata(List.of(sharedTestCase), context);
            // } else {
            //     results = executor.runTestsWithMetadata(testCases, parseTreeVisitor.orElseThrow(), context);
            // }

            if (results.failingTestTotal() > 0) {
                throw results.getTestResults().stream().findFirst().get().getFailureReason().orElseThrow();
            }

    //     if (parseTreeListener.isPresent()) {
    //         results = executor.runTestsWithMetadata(testCases, parseTreeListener.get(), context);
    //     } else {
    //         results = executor.runTestsWithMetadata(testCases, parseTreeVisitor.orElseThrow(), context);
    //     }
    //     if (results.failingTestTotal() > 0) {
    //         throw results.getTestResults().stream().findFirst().get().getFailureReason().orElseThrow();
    //     }
    }
}
