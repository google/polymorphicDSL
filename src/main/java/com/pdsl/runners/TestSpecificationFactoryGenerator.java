package com.pdsl.runners;

import com.pdsl.specifications.TestSpecificationFactory;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;

/**
 * A generator for Test Specification Factories, which in turn are used to process Test Resources into a format that
 * can later be consumed by a Test Case Factory to create test cases from the original input.
 */
public interface TestSpecificationFactoryGenerator {
    TestSpecificationFactory get(PolymorphicDslPhraseFilter filter);
}
