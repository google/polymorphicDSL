package com.pdsl.gherkin;

import com.pdsl.executors.ExecutorObserver;
import com.pdsl.specifications.Phrase;
import com.pdsl.testcases.TestCase;
import java.util.Collection;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class XrayExecutorObserver implements ExecutorObserver {

  private final XrayTestResultUpdater xrayUpdater;

  public XrayExecutorObserver(XrayTestResultUpdater xrayUpdater) {
    this.xrayUpdater = xrayUpdater;
  }

  @Override
  public void onBeforePhrase(ParseTreeListener listener, ParseTreeWalker walker,
      Phrase activePhrase) {
  }

  @Override
  public void onBeforePhrase(ParseTreeVisitor<?> visitor,
      Phrase activePhrase) {
  }

  @Override
  public void onAfterPhrase(ParseTreeListener listener, ParseTreeWalker walker,
      Phrase activePhrase) {
  }

  @Override
  public void onAfterPhrase(ParseTreeVisitor<?> visitor,
      Phrase activePhrase) {
  }

  @Override
  public void onPhraseFailure(ParseTreeListener listener,
      Phrase activePhrase, Throwable exception) {
  }

  @Override
  public void onPhraseFailure(ParseTreeVisitor<?> visitor,
      Phrase activePhrase, Throwable exception) {
  }

  @Override
  public void onBeforeTestSuite(Collection<TestCase> testCases, ParseTreeVisitor<?> visitor,
      String context) {
  }

  @Override
  public void onAfterTestSuite(Collection<TestCase> testCases, ParseTreeVisitor<?> visitor,
      String context) {
    xrayUpdater.onAfterTestSuite(testCases, visitor, context);
  }

  @Override
  public void onBeforeTestSuite(Collection<TestCase> testCases, ParseTreeListener listener,
      String context) {
  }

  @Override
  public void onAfterTestSuite(Collection<TestCase> testCases, ParseTreeListener listener,
      String context) {
  }
}
