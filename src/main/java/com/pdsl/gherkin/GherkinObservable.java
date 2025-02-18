package com.pdsl.gherkin;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Defines an interface for objects that can be observed for Gherkin scenario events.
 *
 * This interface allows objects to register as observers of Gherkin scenarios
 * and receive notifications when a scenario is converted.
 */
public interface GherkinObservable {

  void registerObserver(GherkinObserver observer);

  void removeObserver(GherkinObserver observer);

  void notifyObservers(String title, List<String> steps, Set<String> tags,
      Map<String, String> substitutions);
}
