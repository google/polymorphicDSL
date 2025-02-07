package com.pdsl.gherkin;

import com.pdsl.runners.TestSpecificationFactoryGenerator;
import com.pdsl.specifications.TestSpecificationFactory;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;

/** A factory for GherkinTestSpecificationFactories.
*
* Gherkin creates "Pickles" which are eventually turned into test cases. PDSL uses a PickleJarFactory and other constructs
* to process feature files. However, it is not possible for PDSL to parse these feature files unless it has a
* PolymorphicDslPhraseFilter which is not available at compile time.
*
* The DefaultGherkinTestSpecificaitonFactory works well, but if code actions need to be taken while it is being
* processed (or if it simply needs a separate implementation for some of its components) then this generator
* is necessary to allow that modification.
*/
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
