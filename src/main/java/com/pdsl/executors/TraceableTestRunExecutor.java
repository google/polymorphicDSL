package com.pdsl.executors;

import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.testcases.TestCase;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

import java.util.Collection;

/** A test executor that can record the results of the test run at a granular level. */
public interface TraceableTestRunExecutor extends PolymorphicDslTestExecutor {

    /**
     * Executes each test case while tracking and preserving the details of each test.
     *
     * <p>The results can be used by a {@link com.pdsl.reports.TraceableReportGenerator} to produce meaningful reports
     * about the test run.
     *
     * @param testCases the test cases to execute
     * @param grammarListener The listener that will execute each phrase
     * @param context Specifies the application context or test methodology (i.e, web, mobile, unit, integration, etc)
     * @return The results of the test run with granular information on each test case
     */
    MetadataTestRunResults runTestsWithMetadata(Collection<TestCase> testCases,
                                    ParseTreeListener grammarListener, String context);

    /**
     * Executes each test case while tracking and preserving the details of each test.
     *
     * <p>The results can be used by a {@link com.pdsl.reports.TraceableReportGenerator} to produce meaningful reports
     * about the test run.
     *
     * @param testCases the test cases to execute
     * @param visitor The visitor that will visit each phrase
     * @param context Specifies the application context or test methodology (i.e, web, mobile, unit, integration, etc)
     * @return The results of the test run with granular information on each test case
     */
    MetadataTestRunResults runTestsWithMetadata(Collection<TestCase> testCases, ParseTreeVisitor<?> visitor, String context);
}
