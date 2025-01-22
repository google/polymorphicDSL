package com.pdsl.executors;

import com.pdsl.specifications.Phrase;
import com.pdsl.testcases.TestCase;
import java.util.Collection;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public interface ExecutorObserver {

  void onBeforePhrase(ParseTreeListener listener,
      ParseTreeWalker walker, Phrase activePhrase);

  void onBeforePhrase(ParseTreeVisitor<?> visitor,
      Phrase activePhrase);

  void onAfterPhrase(ParseTreeListener listener,
      ParseTreeWalker walker, Phrase activePhrase);

  void onAfterPhrase(ParseTreeVisitor<?> visitor,
      Phrase activePhrase);

  void onPhraseFailure(ParseTreeListener listener,
      Phrase activePhrase, Throwable exception);

  void onPhraseFailure(ParseTreeVisitor<?> visitor,
      Phrase activePhrase, Throwable exception);

  void onBeforeTestSuite(Collection<TestCase> testCases, ParseTreeVisitor<?> visitor,
      String context);

  void onAfterTestSuite(Collection<TestCase> testCases, ParseTreeVisitor<?> visitor,
      String context);

  void onBeforeTestSuite(Collection<TestCase> testCases, ParseTreeListener listener,
      String context);

  void onAfterTestSuite(Collection<TestCase> testCases, ParseTreeListener listener,
      String context);

}
