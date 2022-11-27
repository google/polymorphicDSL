package com.pdsl.runners.junit;

import com.google.common.base.Preconditions;
import com.pdsl.executors.TraceableTestRunExecutor;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.runners.PdslConfiguration;
import com.pdsl.runners.PdslGherkinApplication;
import com.pdsl.testcases.TestCase;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;

import java.util.*;

public class PdslExecutorRunner extends ParentRunner<TestCase> {
    private final TraceableTestRunExecutor executor;
    private final Optional<ParseTreeListener> parseTreeListener;
    private final Optional<ParseTreeVisitor<?>> parseTreeVisitor;
    private final List<TestCase> testCases;
    private final String context;
    private final List<MetadataTestRunResults> metadataTestRunResults;

    private int accumulator = 1;

    PdslExecutorRunner(Class<?> testClass, ParseTreeListener parseTreeListener, Collection<TestCase> testCases, TraceableTestRunExecutor executor, String context) throws InitializationError {
        super(testClass);
        Preconditions.checkArgument(!testCases.isEmpty(),
                "Somehow no test cases were produced from the features! This is likely an error with the PDSL framework");

        this.context = context;
        this.executor = executor;
        this.parseTreeListener = Optional.of(parseTreeListener);
        this.testCases = new ArrayList<>(testCases);
        parseTreeVisitor = Optional.empty();
        metadataTestRunResults = new ArrayList<>(testCases.size());
        accumulator = 1;
    }

    PdslExecutorRunner(Class<?> testClass, ParseTreeVisitor<?> parseTreeVisitor, Collection<TestCase> testCases, TraceableTestRunExecutor executor, String context) throws InitializationError {
        super(testClass);
        Preconditions.checkArgument(!testCases.isEmpty(),
                "Somehow no test cases were produced from the features! This is likely an error with the PDSL framework");

        this.context = context;
        this.executor = executor;
        this.parseTreeVisitor = Optional.of(parseTreeVisitor);
        this.testCases = new ArrayList<>(testCases);
        metadataTestRunResults = new ArrayList<>(testCases.size());
        accumulator = 1;
        parseTreeListener = Optional.empty();
    }

    public List<MetadataTestRunResults> getMetadataTestRunResults() {
        return metadataTestRunResults;
    }

    @Override
    protected List<TestCase> getChildren() {
        return testCases;
    }

    @Override
    protected Description describeChild(TestCase child) {
        return Description.createTestDescription(getTestClass().getName(),
                String.format("%d - %s", accumulator++, child.getTestTitle()));
    }

    @Override
    protected void runChild(TestCase child, RunNotifier notifier) {
        Preconditions.checkNotNull(child,
                "A null test case was created somehow! No way to run!");
            PdslStatement statement = parseTreeListener.isPresent()
                    ? new PdslStatement(List.of(child), parseTreeListener.get(), context, executor)
                    : new PdslStatement(List.of(child), parseTreeVisitor.orElseThrow(), context, executor);
            runLeaf(statement, describeChild(child), notifier);
            Optional<MetadataTestRunResults> runResults = statement.getResults();
            if (runResults.isPresent()) {
                metadataTestRunResults.add(statement.getResults().orElseThrow());
            } else {
                throw new IllegalStateException("The PDSL Test Run did not produce any results!");
            }
    }


}
