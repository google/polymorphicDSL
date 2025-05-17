package com.pdsl.executors;

import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.specifications.Phrase;
import com.pdsl.testcases.SharedTestCase;
import com.pdsl.testcases.TestCase;
import com.pdsl.testcases.TestSection;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.Collection;

/** An observer that is notified when specific events occur while a Test Executor executes tests. */
public interface ExecutorObserver {

  /**
   * Notifies just before a test case is going to execute.
   * @param testCase
   */
  default void onBeforeTestCase(TestCase testCase) {}

  /**
   * Notifies after a test case has executed regardless of whether it has passed or failed.
   * @param testCase
   */
  default void onAfterTestCase(TestCase testCase) {}

  /**
   * Notifies when a test case has completed execution with no phrase failures.
   * @param testCase
   */
  default void onTestCaseSuccess(TestCase testCase) {}

  /**
   * Notifies the implementer that a test case isn't being run because a separate test case
   * already ran that did the same thing.
   * @param testCase that was skipped
   */
  default void onDuplicateSkipped(TestCase testCase) {}

  /** Notifies the implementer of a phrase that is about to be executed with a listener. */
  default void onBeforePhrase(ParseTreeListener listener,
      ParseTreeWalker walker, TestSection phrase) {}

  /** Notifies the implementer of a phrase that is about to be executed with a visitor. */
  default void onBeforePhrase(ParseTreeVisitor<?> visitor,
      TestSection testSection) {}

  /** Notifies the implementer of a phrase that has completed execution with a listener WITHOUT an exception. */
  default void onAfterPhrase(ParseTreeListener listener,
      ParseTreeWalker walker, TestSection testSection) {}
  
  /** Notifies the implementer of a phrase that has completed execution with a visitor WITHOUT an exception. */
  void onAfterPhrase(ParseTreeVisitor<?> visitor,
                     TestSection testSection);

   /** Notifies the implementer of a phrase that has failed during excecution with a listener. */
  default void onPhraseFailure(ParseTreeListener listener,
                               TestSection testSection, TestCase testCase, Throwable exception) {}

  /** Notifies the implementer of a phrase that has failed during excecution with a visitor. */
  default void onPhraseFailure(ParseTreeVisitor<?> visitor,
                               TestSection testSection, TestCase testCase, Throwable exception) {}

  /** Notifies the implementer of a test suite that is about to execute with a visitor. */
  default void onBeforeTestSuite(Collection<? extends TestCase> testCases, ParseTreeVisitor<?> visitor,
      String context) {}

  /** Notifies the implementer of a test suite that is about to execute with a listener. */
  default void onBeforeTestSuite(Collection<? extends TestCase> testCases, ParseTreeListener listener,
      String context) {}

  /** Notifies the implementer of a test suite that is about to execute.
   *
   */
  default void onBeforeTestSuite(Collection<? extends SharedTestCase> testCases,
      String context) {}

  /** Notifies the implementer of a test suite that has completed execution with a visitor. */
  default void onAfterTestSuite(Collection<? extends TestCase> testCases, ParseTreeVisitor<?> visitor, MetadataTestRunResults results,
      String context) {}

  /** Notifies the implementer  a test suite that has completed execution with a listener. */
  default void onAfterTestSuite(Collection<? extends TestCase> testCases, ParseTreeListener listener, MetadataTestRunResults results,
      String context) {}

  /**
   * Notifies the implementer of a test suite that is has completed execution.
   */
  default void onAfterTestSuite(Collection<? extends SharedTestCase> testCases, MetadataTestRunResults results,
      String context) {}
}
