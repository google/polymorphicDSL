package com.pdsl.xray.observers;

import com.pdsl.specifications.TestSpecification;
import java.util.Collection;

public interface TestSpecificationFactoryObserver {

  void onTestSpecificationsGenerated(Collection<TestSpecification> testSpecifications);
}
