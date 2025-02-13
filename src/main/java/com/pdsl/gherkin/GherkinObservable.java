package com.pdsl.gherkin;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface GherkinObservable {

  void registerObserver(GherkinObserver observer);

  void removeObserver(GherkinObserver observer);

  void notifyObservers(String title, List<String> steps, Set<String> tags,
      Map<String, String> substitutions);
}
