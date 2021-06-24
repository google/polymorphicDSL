package com.pdsl.testcases;

import com.pdsl.specifications.TestSpecification;
import org.antlr.v4.runtime.tree.ParseTreeListener;

import java.util.Collection;

/**
 * A factory that is able to convert TestSpecification objects into TestCases that are used as inputs for
 * @code{GrammarExecutor}s or @code{PolymorphicDslTestExecutor}s.
 */
public interface TestCaseFactory {
    /**
     * Converts test specifications into test cases.
     * @param testCaseSpecification The blueprint for creating the test cases
     * @return A collection of Test Cases
     */
    Collection<TestCase> processTestSpecification(TestSpecification testCaseSpecification);
}
