package com.pdsl.executors;

import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.reports.TestRunResults;
import com.pdsl.testcases.TestCase;
import org.antlr.v4.runtime.tree.ParseTreeListener;

import java.util.Collection;

/**
 * A test runner that is used to perform test case execution.
 */
public interface PolymorphicDslTestExecutor {

    /**
     * Walks each of the ParseTrees in each test case triggering a method in the provided parse tree listener.
     * <p>
     * This is the preferred method of running the tests if you are using standard mechanisms to create your test cases
     * and assumes that the parse tree listener is aware of any possible phrases that would be in the test cases
     *
     * @param testCases
     * @param subgrammarListener
     * @return
     */
    TestRunResults runTests(Collection<TestCase> testCases,
                            ParseTreeListener subgrammarListener);

}
