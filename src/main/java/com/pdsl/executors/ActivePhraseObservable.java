package com.pdsl.executors;

/** An implementer of the Observer Pattern for test executions. */
public interface ActivePhraseObservable {

  void registerObserver(ExecutorObserver observer);

  void removeObserver(ExecutorObserver observer);
}
