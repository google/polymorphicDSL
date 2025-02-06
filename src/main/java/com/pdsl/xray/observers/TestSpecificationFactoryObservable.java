package com.pdsl.xray.observers;

import com.pdsl.specifications.TestSpecification;
import java.util.Collection;


public interface TestSpecificationFactoryObservable {

  void registerObserver(TestSpecificationFactoryObserver observer);

  // void registerObserver(Observable observable, Observer observer);

  void removeObserver(TestSpecificationFactoryObserver observer);

  void notifyObservers(Collection<TestSpecification> testSpecifications);
}
