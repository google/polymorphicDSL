package com.pdsl.gherkin;

import com.pdsl.specifications.TestSpecification;
import java.util.Collection;

// Observer interface for TestSpecificationFactory
public interface XrayTestSpecificationFactoryObserver {

  void onTestSpecificationsGenerated(Collection<TestSpecification> testSpecifications);
}
