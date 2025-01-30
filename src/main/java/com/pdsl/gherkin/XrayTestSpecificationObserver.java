package com.pdsl.gherkin;

// Observer that updates Xray after TestSpecifications are generated

import com.pdsl.specifications.TestSpecification;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class XrayTestSpecificationObserver implements XrayTestSpecificationFactoryObserver,
    GherkinObserver {

  private final XrayTestResultUpdater xrayUpdater;
  Logger logger = Logger.getLogger(this.getClass().getName());

  public XrayTestSpecificationObserver(XrayTestResultUpdater xrayUpdater,
      GherkinObservable gherkinObservable) {
    this.xrayUpdater = xrayUpdater;
    gherkinObservable.registerObserver(this);
  }

  @Override
  public void onTestSpecificationsGenerated(Collection<TestSpecification> testSpecifications) {

    logger.info("Test Specifications generated. Notifying Xray Updater...");
  }

  @Override
  public void onScenarioConverted(String title, List<String> steps, Set<String> tags,
      Map<String, String> substitutions) {
    xrayUpdater.onScenarioConverted(title, steps, tags, substitutions);
  }
}
