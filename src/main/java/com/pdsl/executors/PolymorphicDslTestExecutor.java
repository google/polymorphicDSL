package com.pdsl.executors;

import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.specifications.TestSpecification;

/**
 * A test runner that has been pre-configured such that it can simply run tests on whatever specification has been given
 * to it.
 *
 * Contrast with a {@code GrammarExecutor} which must have a parse tree listener provided to use with the test run
 */
public interface PolymorphicDslTestExecutor {

   /**
     * Executes tests according to the provided test case specification.
     *
     * @param testCaseSpecification the tests to run
     * @return PolymorphicDslTestRunResults an object containing metrics related to the run
     */
    PolymorphicDslTestRunResults runTests(TestSpecification testCaseSpecification);
}
