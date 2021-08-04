package com.pdsl.executors;

import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.testcases.TestCase;
import org.antlr.v4.runtime.tree.ParseTreeListener;

import java.util.Collection;

public interface TraceableTestRunExecutor extends PolymorphicDslTestExecutor {

    MetadataTestRunResults runTestsWithMetadata(Collection<TestCase> testCases,
                                    ParseTreeListener subgrammarListener, String context);
}
