package com.pdsl.executors;

import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.specifications.Phrase;
import com.pdsl.testcases.TestCase;
import java.util.Collection;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/** An observer that is notified when specific events occur while a Test Executor executes tests. */
public interface ExecutorObserver {

  /** Notifies the implementer of a phrase that is about to be executed with a listener. */
  void onBeforePhrase(ParseTreeListener listener,
      ParseTreeWalker walker, Phrase activePhrase);

  /** Notifies the implementer of a phrase that is about to be executed with a visitor. */
  void onBeforePhrase(ParseTreeVisitor<?> visitor,
      Phrase activePhrase);

  /** Notifies the implementer of a phrase that has completed execution with a listener WITHOUT an exception. */
  void onAfterPhrase(ParseTreeListener listener,
      ParseTreeWalker walker, Phrase activePhrase);
  
  /** Notifies the implementer of a phrase that has completed execution with a visitor WITHOUT an exception. */
  void onAfterPhrase(ParseTreeVisitor<?> visitor,
      Phrase activePhrase);

   /** Notifies the implementer of a phrase that has failed during excecution with a listener. */
  void onPhraseFailure(ParseTreeListener listener,
      Phrase activePhrase, TestCase testCase, Throwable exception);

  /** Notifies the implementer of a phrase that has failed during excecution with a visitor. */
  void onPhraseFailure(ParseTreeVisitor<?> visitor,
      Phrase activePhrase, TestCase testCase, Throwable exception);

  /** Notifies the implementer of a of a test suite that is about to execute with a visitor. */
  void onBeforeTestSuite(Collection<? extends TestCase> testCases, ParseTreeVisitor<?> visitor,
      String context);

  /** Notifies the implementer of a of a test suite that is about to execute with a listener. */
  void onBeforeTestSuite(Collection<? extends TestCase> testCases, ParseTreeListener listener,
      String context);

  /** Notifies the implementer of a of a test suite that is about to execute. 
   *  The implementation of the TestCase collection will likely be SharedTestCases in the event this method is triggered.
   */
  void onBeforeTestSuite(Collection<? extends TestCase> testCases,
      String context);

  /** Notifies the implementer of a of a test suite that has completed execution with a visitor. */
  void onAfterTestSuite(Collection<? extends TestCase> testCases, ParseTreeVisitor<?> visitor, MetadataTestRunResults results,
      String context);

  /** Notifies the implementer of a of a test suite that has completed execution with a listener. */
  void onAfterTestSuite(Collection<? extends TestCase> testCases, ParseTreeListener listener, MetadataTestRunResults results,
      String context);

  /** Notifies the implementer of a of a test suite that is has completed execution. 
   *  The implementation of the TestCase collection will likely be SharedTestCases in the event this method is triggered.
   */
  void onAfterTestSuite(Collection<? extends TestCase> testCases, MetadataTestRunResults results,
      String context);
}
