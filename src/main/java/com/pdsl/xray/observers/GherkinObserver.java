package com.pdsl.xray.observers;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface GherkinObserver {

  void onScenarioConverted(String title, List<String> steps, Set<String> tags,
      Map<String, String> substitutions);

}