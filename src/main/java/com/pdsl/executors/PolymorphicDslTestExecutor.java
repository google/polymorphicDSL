package com.pdsl.executors;

import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.testcases.TestCase;
import org.antlr.v4.runtime.tree.ParseTreeListener;

import java.util.Collection;

/**
 * A test runner that has been pre-configured such that it can simply run tests on whatever specification has been given
 * to it.
 *
 */
public interface PolymorphicDslTestExecutor {

    PolymorphicDslTestRunResults runTests(Collection<TestCase> testCases,
                                                ParseTreeListener grammarListener);
    PolymorphicDslTestRunResults runTests(Collection<TestCase> testCases, ParseTreeListener grammarListener,
                                                             ParseTreeListener subgrammarListener);
}
