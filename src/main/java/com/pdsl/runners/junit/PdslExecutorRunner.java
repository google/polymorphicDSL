package com.pdsl.runners.junit;

import com.google.common.base.Preconditions;
import com.pdsl.executors.TraceableTestRunExecutor;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.testcases.SharedTestCase;
import org.junit.internal.AssumptionViolatedException;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;

import java.util.*;
import org.junit.runners.model.Statement;
import org.junit.runner.notification.StoppedByUserException;

public class PdslExecutorRunner extends ParentRunner<SharedTestCase> {

  private final TraceableTestRunExecutor executor;

  private final List<SharedTestCase> sharedTestCases;
  private final String context;
  private final List<MetadataTestRunResults> metadataTestRunResults;

  private int accumulator = 1;

  PdslExecutorRunner(Class<?> testClass, List<SharedTestCase> sharedTestCases,
      TraceableTestRunExecutor executor, String context) throws InitializationError {
    super(testClass);
    for (SharedTestCase currentCase : sharedTestCases) {
        Preconditions.checkArgument(!currentCase.getSharedTestCaseWithInterpreters().isEmpty(),
                "Somehow no test cases were produced from the features! This is likely an error with the PDSL framework");
    }

    this.context = context;
    this.executor = executor;
    this.sharedTestCases = sharedTestCases;
    metadataTestRunResults = new ArrayList<>(sharedTestCases.size());
    accumulator = 1;
  }

  public List<MetadataTestRunResults> getMetadataTestRunResults() {
    return metadataTestRunResults;
  }

  @Override
  protected List<SharedTestCase> getChildren() {
    return this.sharedTestCases;
  }

  @Override
  protected Description describeChild(SharedTestCase sharedTestCase) {
    return Description.createTestDescription(getTestClass().getName(),
        String.format("%d - %s", accumulator++,
            sharedTestCase.getSharedTestCaseWithInterpreters().stream().findFirst().orElseThrow()
                .getTestCase().getTestTitle()));
  }

  @Override
  public void run(final RunNotifier notifier) {
    EachTestNotifier testNotifier = new EachTestNotifier(notifier,
            getDescription());
    testNotifier.fireTestSuiteStarted();
    try {
      Statement statement = classBlock(notifier);
      statement.evaluate();
    } catch (AssumptionViolatedException e) {
      testNotifier.addFailedAssumption(e);
    } catch (StoppedByUserException e) {
      throw e;
    } catch (Throwable e) {
      testNotifier.addFailure(e);
    } finally {
      testNotifier.fireTestSuiteFinished();
    }
  }

  @Override
  protected void runChild(SharedTestCase child, RunNotifier runNotifier) {

    Preconditions.checkNotNull(child.getSharedTestCaseWithInterpreters(),
        "A null testcase /interpreter  was created somehow! No way to run!");
    Preconditions.checkArgument(!child.getSharedTestCaseWithInterpreters().isEmpty(),
        "A Empty testcase /interpreter  was created somehow! No way to run!");
    PdslStatement statement = new PdslStatement(child, context, executor);

    try {
      runLeaf(statement,describeChild(child),runNotifier);
    } catch (Throwable e) {
      // runLeaf handles notifier logic
    }

    Optional<MetadataTestRunResults> runResults = statement.getResults();
    if (runResults.isPresent()) {
      metadataTestRunResults.add(statement.getResults().orElseThrow());
    } else {
      throw new IllegalStateException("The PDSL Test Run did not produce any results!");
    }

  }



}
