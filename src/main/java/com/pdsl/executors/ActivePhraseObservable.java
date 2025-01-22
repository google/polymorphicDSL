package com.pdsl.executors;

public interface ActivePhraseObservable {

  void registerObserver(ExecutorObserver observer);

  void removeObserver(ExecutorObserver observer);
}