package com.pdsl.executors;

/** An implementer of the Observer Pattern for test executions. */
public interface ExecutorObservable {

  void registerObserver(ExecutorObserver observer);

  void removeObserver(ExecutorObserver observer);
}
