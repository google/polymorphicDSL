package com.pdsl.testcases;

import com.pdsl.specifications.TestSpecification;

import java.util.Collection;

/**
 * A factory that is able to convert TestSpecification objects into TestCases that are used as inputs for
 * @code{PolymorphicDslTestExecutor}s.
 */
public interface TestCaseFactory {
    /**
     * Converts test specifications into test cases.
     *
     * @param testCaseSpecification The blueprint for creating the test cases
     * @return A collection of Test Cases
     */
    Collection<TestCase> processTestSpecification(Collection<TestSpecification> testCaseSpecification);
}
