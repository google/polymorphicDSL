package com.pdsl.runners.junit;

import com.pdsl.executors.TraceableTestRunExecutor;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.testcases.TestCase;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.junit.runners.model.Statement;

import java.util.Collection;
import java.util.Optional;

class PdslStatement extends Statement {

    private MetadataTestRunResults results;
    private final Collection<TestCase> testCases;
    private final ParseTreeListener parseTreeListener;
    private final String context;
    private final TraceableTestRunExecutor executor;

    public PdslStatement(Collection<TestCase> testCases, ParseTreeListener parseTreeListener, String context, TraceableTestRunExecutor executor) {
        this.testCases = testCases;
        this.parseTreeListener = parseTreeListener;
        this.context = context;
        this.executor = executor;
    }

    public Optional<MetadataTestRunResults> getResults() {
        return Optional.ofNullable(results);
    }
    @Override
    public void evaluate() throws Throwable {
        results = executor.runTestsWithMetadata(testCases, parseTreeListener, context);
        if (results.failingTestTotal() > 0) {
            throw results.getTestResults().stream().findFirst().get().getFailureReason().orElseThrow();
        }
    }
}
