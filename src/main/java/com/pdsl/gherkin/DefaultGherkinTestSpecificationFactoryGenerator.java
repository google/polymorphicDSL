package com.pdsl.gherkin;

import com.pdsl.runners.TestSpecificationFactoryGenerator;
import com.pdsl.specifications.TestSpecificationFactory;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;

public class DefaultGherkinTestSpecificationFactoryGenerator implements
    TestSpecificationFactoryGenerator {

  private final DefaultGherkinTestSpecificationFactory.Builder builder;

  public DefaultGherkinTestSpecificationFactoryGenerator(DefaultGherkinTestSpecificationFactory.Builder builder){
    this.builder =builder;
  }

  @Override
  public TestSpecificationFactory get(PolymorphicDslPhraseFilter filter) {
    return this.builder
        .withPhraseFilter(filter)
        .build();
  }
}
