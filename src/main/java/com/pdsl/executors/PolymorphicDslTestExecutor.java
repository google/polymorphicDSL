package com.pdsl.executors;

import com.pdsl.reports.TestRunResults;
import com.pdsl.testcases.TestCase;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

import java.util.Collection;

/** A test runner that is used to perform test case execution. */
public interface PolymorphicDslTestExecutor {

    /**
     * Walks each of the ParseTrees in each test case triggering a method in the provided parse tree listener.
     * <p>
     * This is the preferred method of running the tests if you are using standard mechanisms to create your test cases
     * and assumes that the parse tree listener is aware of any possible phrases that would be in the test cases
     *
     * @param testCases
     * @param subgrammarListener
     * @return the results of the test run
     */
    TestRunResults runTests(Collection<TestCase> testCases,

                            ParseTreeListener subgrammarListener);
    /**
     * Walks each of the ParseTrees in each test case triggering a method in the provided parse tree visitor.
     * <p>
     * This is the preferred method of running the tests if you are using standard mechanisms to create your test cases
     * and assumes that the parse tree visitor is aware of any possible phrases that would be in the test cases
     *
     * @param testCases
     * @param subgrammarVisitor
     * @return the results of the test run
     */
    TestRunResults runTests(Collection<TestCase> testCases,
                            ParseTreeVisitor subgrammarVisitor);

}
