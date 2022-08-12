package com.pdsl.runners;

import com.pdsl.specifications.TestSpecificationFactory;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;

public interface TestSpecificationFactoryGenerator {

    TestSpecificationFactory get(PolymorphicDslPhraseFilter filter);
}
