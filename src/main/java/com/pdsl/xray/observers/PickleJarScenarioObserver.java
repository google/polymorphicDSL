package com.pdsl.xray.observers;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class PickleJarScenarioObserver implements GherkinObserver {


  @Override
  public void onScenarioConverted(String title, List<String> steps, Set<String> tags,
      Map<String, String> substitutions) {

    addTag(tags, substitutions, "xray-test-plan");
    addTag(tags, substitutions, "xray-test-case");
    addTag(tags, substitutions, "xray-test-platform");
    addTag(tags, substitutions, "xray-test-env");
  }

  private void addTag(Set<String> tags, Map<String, String> substitutions, String key) {
    String value = substitutions.get(key);
    if (value != null) {
      tags.add("@%s=%s".formatted(key, value));
    }
  }



  /*private void initiateNotification(){
    Observer myObserver = new PickleJarScenarioObserver(); // Implement your observer logic

    XrayTestSpecificationFactory customFactory =
        new XrayTestSpecificationFactory.Builder()
            .withMaxDescriptionLength(1024) // Example configuration
            .withObserver(observer)
            .build();

    Optional<Collection<TestSpecification>> testSpecs = customFactory.getTestSpecifications(testContent);

    if (testSpecs.isPresent()) {
      // TODO: Use the test specifications
    }*/
}
