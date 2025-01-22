package com.pdsl.gherkin;

import com.pdsl.specifications.TestSpecification;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;


public interface TestSpecificationFactoryObservable {

  void registerObserver(TestSpecificationFactoryObserver observer);

  // void registerObserver(Observable observable, Observer observer);

  void removeObserver(TestSpecificationFactoryObserver observer);

  void notifyObservers(Collection<TestSpecification> testSpecifications);
}
