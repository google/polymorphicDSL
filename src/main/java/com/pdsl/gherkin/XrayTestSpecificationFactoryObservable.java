package com.pdsl.gherkin;

import com.pdsl.specifications.TestSpecification;
import java.util.Collection;

// Observable interface for TestSpecificationFactory
public interface XrayTestSpecificationFactoryObservable {

  void registerObserver(XrayTestSpecificationFactoryObserver observer);

  void removeObserver(XrayTestSpecificationFactoryObserver observer);

  void notifyObservers(Collection<TestSpecification> testSpecifications);
}
