package com.pdsl.executors;

import com.pdsl.gherkin.xray.models.XrayTestExecutionResult;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.specifications.Phrase;
import com.pdsl.testcases.SharedTestCase;
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
      Phrase activePhrase, TestCase testCase, Throwable exception);

  void onPhraseFailure(ParseTreeVisitor<?> visitor,
      Phrase activePhrase, TestCase testCase, Throwable exception);

  void onBeforeTestSuite(Collection<? extends TestCase> testCases, ParseTreeVisitor<?> visitor,
      String context);
  void onBeforeTestSuite(Collection<? extends TestCase> testCases, ParseTreeListener listener,
      String context);

  void onBeforeTestSuite(Collection<? extends TestCase> testCases,
      String context);


  void onAfterTestSuite(Collection<? extends TestCase> testCases, ParseTreeVisitor<?> visitor, MetadataTestRunResults results,
      String context);


  void onAfterTestSuite(Collection<? extends TestCase> testCases, ParseTreeListener listener, MetadataTestRunResults results,
      String context);

  void onAfterTestSuite(Collection<? extends TestCase> testCases, MetadataTestRunResults results,
      String context);
}
