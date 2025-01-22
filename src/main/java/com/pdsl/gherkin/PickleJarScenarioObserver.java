package com.pdsl.gherkin;

import com.pdsl.specifications.TestSpecification;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PickleJarScenarioObserver implements GherkinObserver {


  @Override
  public void onScenarioConverted(String title, List<String> steps, Set<String> tags,
      Map<String, String> substitutions) {
    System.out.println("Scenario Converted:");
    System.out.println("  Title: " + title);
    System.out.println("  Steps: " + steps);
    System.out.println("  Tags: " + tags);
    System.out.println("  Substitutions: " + substitutions);
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
