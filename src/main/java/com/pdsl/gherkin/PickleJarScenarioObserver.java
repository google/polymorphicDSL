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
   if( substitutions.get("xray-test-case")!=null){
     tags.add("@xray-test-case="+substitutions.get("xray-test-case"));
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
