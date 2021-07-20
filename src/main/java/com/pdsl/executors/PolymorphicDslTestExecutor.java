package com.pdsl.executors;

import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.testcases.TestCase;
import org.antlr.v4.runtime.tree.ParseTreeListener;

import java.util.Collection;

/**
 * A test runner that is used to perform test case execution.
 *
 */
public interface PolymorphicDslTestExecutor {

    /**
     * Walks each of the ParseTrees in each test case triggering a method in the provided parse tree listener.
     *
     * This is the preferred method of running the tests if you are using standard mechanisms to create your test cases
     * and assumes that the parse tree listener is aware of any possible phrases that would be in the test cases
     * @param testCases
     * @param subgrammarListener
     * @return
     */
    PolymorphicDslTestRunResults runTests(Collection<TestCase> testCases,
                                                ParseTreeListener subgrammarListener);

    /**
     * Quickly checks to see that all phrases in each test case exist in a parent grammar before executing what will
     * likely be a more lengthy execution of the subgrammar listener.
     * 
     * This method will likely be unnecessary in the event the test cases were produced by one of the standard
     * Test Specification Factories and Test Case Factories as they should have filtered out anything unknown
     * to the subgrammar already
     * 
     * @param testCases
     * @param grammarListener
     * @param subgrammarListener
     * @return The results of the test run
     */
    PolymorphicDslTestRunResults runTests(Collection<TestCase> testCases, ParseTreeListener grammarListener,
                                                             ParseTreeListener subgrammarListener);
}
