package com.pdsl.xray.observers;

// Concrete Observable implementation for TestSpecificationFactory
import com.pdsl.specifications.TestSpecification;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ConcreteXrayTestSpecificationFactoryObservable implements XrayTestSpecificationFactoryObservable {
  private List<XrayTestSpecificationFactoryObserver> observers = new ArrayList<>();

  @Override
  public void registerObserver(XrayTestSpecificationFactoryObserver observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(XrayTestSpecificationFactoryObserver observer) {
    observers.remove(observer);
  }

  @Override
  public void notifyObservers(Collection<TestSpecification> testSpecifications) {
    for (XrayTestSpecificationFactoryObserver observer : observers) {
      observer.onTestSpecificationsGenerated(testSpecifications);
    }
  }
}
