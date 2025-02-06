package com.pdsl.gherkin;

import com.pdsl.specifications.TestSpecification;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import java.net.URI;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public class XrayTestSpecificationFactory extends DefaultGherkinTestSpecificationFactory {

  private GherkinObserver observer;
  private GherkinObservable observable;

  public XrayTestSpecificationFactory(Builder builder, GherkinObservable subject,
      GherkinObserver observer) {
    super(builder);
    this.observer = observer;
    this.observable = subject;
    subject.registerObserver(observer);
  }


  /*PdslConfig config = PdslConfig.newBuilder()
      .setGeneralPdslConfig(PdslConfigParameter.createGeneralPdslConfig(
          testCaseFactoryProvider, specificationFactoryProvider, pdslTestParameters))
      .build();*/
  public static class Builder extends DefaultGherkinTestSpecificationFactory.Builder {

    private GherkinObserver observer;
    private GherkinObservable observable;

    public Builder(PolymorphicDslPhraseFilter polymorphicDslPhrasefilter) {
      super(polymorphicDslPhrasefilter);
    }

    public Builder withObserver(GherkinObserver observer) {
      this.observer = observer;
      return this;
    }

    @Override
    public XrayTestSpecificationFactory build() {
      return new XrayTestSpecificationFactory(this, observable, observer);
    }
  }

  @Override
  public Optional<Collection<TestSpecification>> getTestSpecifications(Set<URI> testContent) {
    // Delegate to the parent class for actual test specification generation
    Optional<Collection<TestSpecification>> testSpecifications = super.getTestSpecifications(
        testContent);

    // Notify the observer after getting test specifications (optional)
    if (testSpecifications.isPresent() && observer != null) {
      // observer.onTestSpecificationsGenerated(testSpecifications);
    }

    return testSpecifications;


  }
}
