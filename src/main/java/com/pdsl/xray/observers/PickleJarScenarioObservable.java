package com.pdsl.xray.observers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class PickleJarScenarioObservable implements GherkinObservable {

  private List<GherkinObserver> observers = new ArrayList<>();

  @Override
  public void registerObserver(GherkinObserver observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(GherkinObserver observer) {
    observers.remove(observer);
  }

  @Override
  public void notifyObservers(String title, List<String> steps, Set<String> tags,
      Map<String, String> substitutions) {
    for (GherkinObserver observer : observers) {
      observer.onScenarioConverted(title, steps, tags, substitutions);
    }
  }
}
