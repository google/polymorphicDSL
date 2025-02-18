package com.pdsl.gherkin;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Defines an interface for objects that observe Gherkin scenario events.
 *
 * Observers implement this interface to receive notifications
 * when a Gherkin scenario is converted.
 */
public interface GherkinObserver {

  void onScenarioConverted(String title, List<String> steps, Set<String> tags,
      Map<String, String> substitutions);

}