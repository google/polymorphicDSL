package com.pdsl.runners.junit;

import com.pdsl.executors.InterpreterObj;
import com.pdsl.executors.TraceableTestRunExecutor;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.testcases.SharedTestCase;
import java.util.List;
import org.junit.runners.model.Statement;

import java.util.Optional;

class PdslStatement extends Statement {

    private MetadataTestRunResults results;

  private final SharedTestCase sharedTestCase;
    private final String context;
    private final TraceableTestRunExecutor executor;

  public PdslStatement(SharedTestCase sharedTestCase, String context, TraceableTestRunExecutor executor) {
    this.sharedTestCase = sharedTestCase;
    this.context = context;
    this.executor = executor;
  }

    public Optional<MetadataTestRunResults> getResults() {
        return Optional.ofNullable(results);
    }

    @Override
     public void evaluate() throws Throwable {

      /**
       * 1) If the size of collection (of Interpreter - Listener or Visitor) is ONE - it means
       * {@link com.pdsl.runners.PdslTest} annotation attribute {@link com.pdsl.runners.Interpreter} empty
       * and Listener or Visitor provided in the {@link com.pdsl.runners.PdslTest} annotation - default implementation/behaviour.
       * And we will use - com.pdsl.executors.TraceableTestRunExecutor#runTestsWithMetadata(java.util.Collection, org.antlr.v4.runtime.tree.ParseTreeListener, java.lang.String)
       *
       * 2) In the opposite case - it means we have collections of additional Listener or Visitor and
       * we will use - com.pdsl.executors.TraceableTestRunExecutor#runTestsWithMetadata(java.util.Collection, java.lang.String)
       */
    if(sharedTestCase.getInterpreters().size() == 1){

      InterpreterObj interpreterObj = sharedTestCase.getInterpreters().stream().findFirst().get();

          if (interpreterObj.getParseTreeListener().isPresent()) {
              results = executor.runTestsWithMetadata(sharedTestCase.getTestCases(), interpreterObj.getParseTreeListener().get(), context);
          } else {
              results = executor.runTestsWithMetadata(sharedTestCase.getTestCases(), interpreterObj.getParseTreeVisitor().orElseThrow(), context);
          }
    }
    else{
      results = executor.runTestsWithMetadata(List.of(sharedTestCase), context);
    }
            if (results.failingTestTotal() > 0) {
                throw results.getTestResults().stream().findFirst().get().getFailureReason().orElseThrow();
            }
    }
}
