package com.pdsl.runners.junit;

import com.google.common.base.Preconditions;
import com.pdsl.executors.TraceableTestRunExecutor;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.testcases.SharedTestCase;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;

import java.util.*;

public class PdslExecutorRunner extends ParentRunner<SharedTestCase> {
    private final TraceableTestRunExecutor executor;

    private final SharedTestCase sharedTestCase;
    private final String context;
    private final List<MetadataTestRunResults> metadataTestRunResults;

    private int accumulator = 1;

    PdslExecutorRunner(Class<?> testClass, SharedTestCase sharedTestCase, TraceableTestRunExecutor executor, String context) throws InitializationError {
        super(testClass);
        Preconditions.checkArgument(!sharedTestCase.getTestCases().isEmpty(),
            "Somehow no test cases were produced from the features! This is likely an error with the PDSL framework");

        this.context = context;
        this.executor = executor;
        this.sharedTestCase = sharedTestCase;
        metadataTestRunResults = new ArrayList<>(sharedTestCase.getTestCases().size());
        accumulator = 1;
    }

    public List<MetadataTestRunResults> getMetadataTestRunResults() {
        return metadataTestRunResults;
    }

    @Override
    protected List<SharedTestCase> getChildren() {
        return List.of(sharedTestCase);
    }

    @Override
    protected Description describeChild(SharedTestCase sharedTestCase) {
            return Description.createTestDescription(getTestClass().getName(),
                String.format("%d - %s", accumulator ++, sharedTestCase.getTestCases().stream().findFirst().orElseThrow().getTestTitle()));
    }

    @Override
    protected void runChild(SharedTestCase child, RunNotifier runNotifier) {

        Preconditions.checkNotNull(child.getTestCases(), "A null test case was created somehow! No way to run!");

        PdslStatement statement = new PdslStatement(child, context, executor);

        runLeaf(statement, describeChild(child), runNotifier);
        Optional<MetadataTestRunResults> runResults = statement.getResults();
        if (runResults.isPresent()) {
            metadataTestRunResults.add(statement.getResults().orElseThrow());
        } else {
            throw new IllegalStateException("The PDSL Test Run did not produce any results!");
        }

    }
}
