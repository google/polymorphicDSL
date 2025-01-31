package com.pdsl.executors;

import com.pdsl.logging.AnsiTerminalColorHelper;
import com.pdsl.logging.PdslThreadSafeOutputStream;
import com.pdsl.reports.DefaultTestResult;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.reports.TestRunResults;
import com.pdsl.specifications.DefaultPhrase;
import com.pdsl.specifications.FilteredPhrase;
import com.pdsl.specifications.Phrase;
import com.pdsl.specifications.PolymorphicDslTransformationException;
import com.pdsl.testcases.SharedTestCase;
import com.pdsl.testcases.SharedTestSuite.SharedTestCaseWithInterpreter;
import com.pdsl.testcases.TestCase;
import com.pdsl.testcases.TestSection;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;

/**
 * An executor that runs PDSL tests create from a TestCaseFactory.
 *
 * The default executor has colorized terminal output and prints the phrases as they execute and the
 * stack trace on failure.
 *
 * All metadata associated with the tests will also be printed.
 */
public class DefaultPolymorphicDslTestExecutor implements TraceableTestRunExecutor,ActivePhraseObservable {

  private static final Logger logger = LoggerFactory.getLogger(
      DefaultPolymorphicDslTestExecutor.class);
  private final ParseTreeWalker walker = new ParseTreeWalker();
  private final Optional<MultiOutputStream> outputStreams = Optional.of(
      new MultiOutputStream(new PdslThreadSafeOutputStream()));
  private static final Charset DEFAULT_CHARSET = Charset.defaultCharset();
  private static final byte[] RESET = AnsiTerminalColorHelper.RESET.getBytes(DEFAULT_CHARSET);

  @Override
  public PolymorphicDslTestRunResults runTests(Collection<TestCase> testCases,
      ParseTreeListener listener) {
    // Walk the phrase registry to make sure all phrases are defined
    logger.info(
        AnsiTerminalColorHelper.BRIGHT_YELLOW + "Running tests..." + AnsiTerminalColorHelper.RESET);
    notifyBeforeTestSuite(testCases, listener, "");
    MetadataTestRunResults results = walk(testCases, new PhraseRegistry(listener), "NONE");
    //send results to notifyAfterTestSuite
    notifyAfterTestSuite(testCases, listener,results, "");
    if (results.failingTestTotal() == 0) {
      logger.info(AnsiTerminalColorHelper.BRIGHT_GREEN + "All phrases successfully executed!"
          + AnsiTerminalColorHelper.RESET);
    } else {
      logger.error(AnsiTerminalColorHelper.BRIGHT_RED + "There were test failures!"
          + AnsiTerminalColorHelper.RESET);
    }
    return (PolymorphicDslTestRunResults) results;
  }

  @Override
  public TestRunResults runTests(Collection<TestCase> testCases,
      ParseTreeVisitor subgrammarVisitor) {
    logger.info(
        AnsiTerminalColorHelper.BRIGHT_YELLOW + "Running tests..." + AnsiTerminalColorHelper.RESET);
    notifyBeforeTestSuite(testCases, subgrammarVisitor, "");
    MetadataTestRunResults results = walk(testCases, new PhraseRegistry(subgrammarVisitor), "NONE");
    notifyAfterTestSuite(testCases, subgrammarVisitor,results, "");
    if (results.failingTestTotal() == 0) {
      logger.info(AnsiTerminalColorHelper.BRIGHT_YELLOW + "All phrases successfully executed!"
          + AnsiTerminalColorHelper.RESET);
    } else {
      logger.error(AnsiTerminalColorHelper.BRIGHT_RED + "There were test failures!"
          + AnsiTerminalColorHelper.RESET);
    }
    return (PolymorphicDslTestRunResults) results;
  }

  List<ExecutorObserver> activePhraseObservers = new ArrayList<>();

  @Override
  public void registerObserver(ExecutorObserver observer) {
    activePhraseObservers.add(observer);
  }

  @Override
  public void removeObserver(ExecutorObserver observer) {
    activePhraseObservers.remove(observer);
  }

  private void notifyBeforeListener(ParseTreeListener listener, ParseTreeWalker walker,
      Phrase activePhrase) {
    activePhraseObservers.forEach(o -> o.onBeforePhrase(listener, walker, activePhrase));
  }

  private void notifyBeforeVisitor(ParseTreeVisitor<?> visitor,
      Phrase activePhrase) {
    activePhraseObservers.forEach(o -> o.onBeforePhrase(visitor, activePhrase));
  }

  private void notifyAfterListener(ParseTreeListener listener, ParseTreeWalker walker,
      Phrase activePhrase) {
    activePhraseObservers.forEach(o -> o.onAfterPhrase(listener, walker, activePhrase));
  }

  private void notifyAfterVisitor(ParseTreeVisitor<?> visitor,
      Phrase activePhrase) {
    activePhraseObservers.forEach(o -> o.onAfterPhrase(visitor, activePhrase));
  }

  private void notifyOnListenerException(ParseTreeListener listener,
      Phrase activePhrase, TestCase testCase, Throwable exception) {
    activePhraseObservers.forEach(o -> o.onPhraseFailure(listener, activePhrase,testCase, exception));
  }

  private void notifyOnVisitorException(ParseTreeVisitor<?> visitor,
      Phrase activePhrase, TestCase testCase, Throwable exception) {
    activePhraseObservers.forEach(a -> a.onPhraseFailure(visitor, activePhrase,testCase, exception));
  }

  private void notifyBeforeTestSuite(Collection<TestCase> testCases, ParseTreeVisitor<?> visitor,
      String context) {
    activePhraseObservers.forEach(a -> a.onBeforeTestSuite(testCases, visitor, context));
  }

  private void notifyAfterTestSuite(Collection<TestCase> testCases, ParseTreeVisitor<?> visitor, MetadataTestRunResults results,
      String context) {
    activePhraseObservers.forEach(a -> a.onAfterTestSuite(testCases, visitor, results, context));
  }

  private void notifyBeforeTestSuite(Collection<TestCase> testCases, ParseTreeListener listener,
      String context) {
    activePhraseObservers.forEach(a -> a.onBeforeTestSuite(testCases, listener, context));
  }

  private void notifyAfterTestSuite(Collection<TestCase> testCases, ParseTreeListener listener, MetadataTestRunResults results,
      String context) {
    activePhraseObservers.forEach(a -> a.onAfterTestSuite(testCases, listener, results, context));
  }
  /**
   * A container for a listener XOR a visitor.
   */
  private static final class PhraseRegistry {

    private final Optional<ParseTreeListener> listener;
    private final Optional<ParseTreeVisitor<?>> visitor;

    PhraseRegistry(ParseTreeListener listener) {
      this.listener = Optional.of(listener);
      this.visitor = Optional.empty();
    }

    PhraseRegistry(ParseTreeVisitor visitor) {
      this.listener = Optional.empty();
      this.visitor = Optional.of(visitor);
    }
  }

  private MetadataTestRunResults walk(Collection<TestCase> testCases, PhraseRegistry phraseRegistry,
      String context) {
    PolymorphicDslTestRunResults results = new PolymorphicDslTestRunResults(
        new PdslThreadSafeOutputStream(), context);
    Set<List<String>> previouslyExecutedTests = new HashSet<>();

    for (TestCase testCase : testCases) {

      notifyStreams(AnsiTerminalColorHelper.YELLOW.getBytes(DEFAULT_CHARSET));
      notifyStreams(String.format("%s%n%s", testCase.getOriginalSource(), testCase.getTestTitle())
          .getBytes(DEFAULT_CHARSET));
      notifyStreams(String.format("%n").getBytes(DEFAULT_CHARSET));
      notifyStreams(RESET);
      Phrase activePhrase = null;
      Iterator<TestSection> testBody = testCase.getContextFilteredTestSectionIterator();
      int phraseIndex = 0;
      try {
        if (previouslyExecutedTests.contains(testCase.getContextFilteredPhraseBody())) {
          logger.warn(String.format(
              "A test was skipped because after filtering it duplicated an earlier run test!%n\t%s",
              testCase.getTestTitle()));
          StringBuilder duplicateBody = new StringBuilder();
          testCase.getContextFilteredTestSectionIterator().forEachRemaining(duplicateBody::append);
          results.addTestResult(DefaultTestResult.duplicateTest(testCase));
        } else {
          previouslyExecutedTests.add(testCase.getContextFilteredPhraseBody());
          while (testBody.hasNext()) {
            TestSection section = testBody.next();
            if (section.getMetaData().isPresent()) {
              notifyStreams(AnsiTerminalColorHelper.CYAN.getBytes(DEFAULT_CHARSET));
              notifyStreams(section.getMetaData().get());
              notifyStreams(RESET);
            }
            activePhrase = section.getPhrase();
            if (phraseRegistry.listener.isPresent()) {
              notifyBeforeListener(phraseRegistry.listener.get(), walker, activePhrase);
              walker.walk(phraseRegistry.listener.get(), activePhrase.getParseTree());
              notifyAfterListener(phraseRegistry.listener.get(), walker, activePhrase);
            } else {
              notifyBeforeVisitor(phraseRegistry.visitor.get(), activePhrase);
              phraseRegistry.visitor.get().visit(activePhrase.getParseTree());
              notifyAfterVisitor(phraseRegistry.visitor.get(), activePhrase);
            }
            phraseIndex++;
            notifyStreams(
                (AnsiTerminalColorHelper.GREEN + activePhrase.getParseTree().getText() + "\n"
                    + AnsiTerminalColorHelper.RESET).getBytes(DEFAULT_CHARSET));
          }
          results.addTestResult(DefaultTestResult.passingTest(testCase));
        }
      } catch (Throwable e) {
        if (phraseRegistry.listener.isPresent()) {
          notifyOnListenerException(phraseRegistry.listener.get(), activePhrase, testCase, e);
        } else {
          notifyOnVisitorException(phraseRegistry.visitor.get(), activePhrase,testCase, e);
        }
        notifyStreams(
            (AnsiTerminalColorHelper.BRIGHT_RED + activePhrase.getParseTree().getText() + "\n"
                + AnsiTerminalColorHelper.RESET).getBytes(DEFAULT_CHARSET));

        int phrasesSkippedDueToFailure = 0;
        while (testBody.hasNext()) {
          testBody.next();
          phrasesSkippedDueToFailure++;
        }
        results.addTestResult(DefaultTestResult.failedTest(testCase, activePhrase, e, phraseIndex,
            phrasesSkippedDueToFailure));
        logger.error("Phrase failure", e);
      }
    }
    return results;
  }

  private void notifyStreams(InputStream inputStream) {
    if (outputStreams.isPresent()) {
      try {
        outputStreams.get().write(inputStream.readAllBytes());
      } catch (IOException e) {
        throw new PolymorphicDslTransformationException("Could not notify streams!", e);
      }
    }
  }

  private void notifyStreams(byte[] bytes) {
    if (outputStreams.isPresent()) {
      try {
        outputStreams.get().write(bytes);
      } catch (IOException e) {
        throw new PolymorphicDslTransformationException("Could not notify streams!", e);
      }
    }
  }

  @Override
  public MetadataTestRunResults runTestsWithMetadata(Collection<TestCase> testCases,
      ParseTreeListener subgrammarListener, String context) {
    logger.info("Running tests...");
    notifyBeforeTestSuite(testCases, subgrammarListener, "");
    MetadataTestRunResults results = walk(testCases, new PhraseRegistry(subgrammarListener),
        context);
    notifyAfterTestSuite(testCases, subgrammarListener,results, "");
    if (results.failingTestTotal() == 0) {
      logger.info(AnsiTerminalColorHelper.BRIGHT_GREEN + "All phrases successfully executed!"
          + AnsiTerminalColorHelper.RESET);
    } else {
      logger.error(AnsiTerminalColorHelper.BRIGHT_RED + "There were test failures!"
          + AnsiTerminalColorHelper.RESET);
    }
    return (PolymorphicDslTestRunResults) results;
  }

  @Override
  public MetadataTestRunResults runTestsWithMetadata(Collection<TestCase> testCases,
      ParseTreeVisitor<?> visitor, String context) {
    logger.info("Running tests...");
    notifyBeforeTestSuite(testCases, visitor, "");
    MetadataTestRunResults results = walk(testCases, new PhraseRegistry(visitor), context);
    notifyAfterTestSuite(testCases, visitor, results,"");
    if (results.failingTestTotal() == 0) {
      logger.info(AnsiTerminalColorHelper.BRIGHT_GREEN + "All phrases successfully executed!"
          + AnsiTerminalColorHelper.RESET);
    } else {
      logger.error(AnsiTerminalColorHelper.BRIGHT_RED + "There were test failures!"
          + AnsiTerminalColorHelper.RESET);
    }
    return (PolymorphicDslTestRunResults) results;
  }

  @Override
  public MetadataTestRunResults runTestsWithMetadata(Collection<SharedTestCase> sharedTestCases,
      String context) {
    PolymorphicDslTestRunResults results = new PolymorphicDslTestRunResults(
        new PdslThreadSafeOutputStream(), context);
    //Set<List<String>> previouslyExecutedTests = new HashSet<>();

    for (SharedTestCase sharedTestCase : sharedTestCases) {
      List<TestCase> listOfTestCases = sharedTestCase.getSharedTestCaseWithInterpreters().stream()
          .map(tc -> tc.getTestCase()).collect(
              Collectors.toUnmodifiableList());
      int size = listOfTestCases.get(0).getUnfilteredPhraseBody().size();
      TestCase testCase = listOfTestCases.stream().findFirst().orElseThrow();

      notifyStreams(AnsiTerminalColorHelper.YELLOW.getBytes(DEFAULT_CHARSET));
      notifyStreams(String.format("%s%n%s", testCase.getOriginalSource(), testCase.getTestTitle())
          .getBytes(DEFAULT_CHARSET));
      notifyStreams(String.format("%n").getBytes(DEFAULT_CHARSET));
      notifyStreams(RESET);

      String filteredPhraseText = null;
      Optional<Phrase> phrase = Optional.empty();
      int phraseIndex = 0;
      Optional<InterpreterObj> interpreterObj = Optional.empty();
      try {
        //for (SharedTestCaseWithInterpreter interpreter : sharedTestCase.getSharedTestCaseWithInterpreters()) {
        for (int j = 0;
            j < sharedTestCase.getSharedTestCaseWithInterpreters().get(0).getTestCase()
                .getUnfilteredPhraseBody().size(); j++) {
          for (SharedTestCaseWithInterpreter interpreter : sharedTestCase.getSharedTestCaseWithInterpreters()) {

            FilteredPhrase filteredPhrase = interpreter.getTestCase().getFilteredPhrases()
                .get(phraseIndex);
            filteredPhraseText = filteredPhrase.getPhrase();


            //TODO - Add implementation for the duplication checking
            Optional<ParseTree> parseTree = filteredPhrase.getParseTree();
            if (parseTree.isPresent()) {
              phrase = Optional.of(new DefaultPhrase(parseTree.get(), phraseIndex));

              interpreterObj = Optional.of(interpreter.getInterpreterObj());

              if (interpreterObj.get().getParseTreeListener().isPresent()) {
                notifyBeforeListener(interpreterObj.get().getParseTreeListener().get(), walker,
                    phrase.get());
                walker.walk(interpreterObj.get().getParseTreeListener().get(), parseTree.get());
                notifyAfterListener(interpreterObj.get().getParseTreeListener().get(), walker,
                    phrase.get());
              } else {
                notifyBeforeVisitor(interpreterObj.get().getParseTreeVisitor().get(),
                    phrase.get());
                interpreterObj.get().getParseTreeVisitor().get().visit(parseTree.get());
                notifyAfterVisitor(interpreterObj.get().getParseTreeVisitor().get(),
                    phrase.get());
              }

              notifyStreams(
                  (AnsiTerminalColorHelper.GREY + parseTree.get().getText() + "\n"
                      + AnsiTerminalColorHelper.RESET).getBytes(DEFAULT_CHARSET));
            }
          }
          phraseIndex++;
        }
        notifyStreams(
            (AnsiTerminalColorHelper.GREEN + "All Sentences are parsed." + "\n"
                + AnsiTerminalColorHelper.RESET).getBytes(DEFAULT_CHARSET));
      
      } catch (Throwable e) {
        if (interpreterObj.isPresent() && phrase.isPresent()) {
          if (interpreterObj.get().getParseTreeListener().isPresent()) {
            notifyOnListenerException(interpreterObj.get().getParseTreeListener().get(),
                phrase.get(),testCase,
                e);
          } else if (interpreterObj.get().getParseTreeVisitor().isPresent()) {
            notifyOnVisitorException(interpreterObj.get().getParseTreeVisitor().get(),
                phrase.get(),testCase,
                e);
          }
        }
        notifyStreams(
            (AnsiTerminalColorHelper.BRIGHT_RED + filteredPhraseText + "\n"
                + AnsiTerminalColorHelper.RESET).getBytes(DEFAULT_CHARSET));

        results.addTestResult(DefaultTestResult.failedTest(testCase, null, e, phraseIndex,
            size - phraseIndex));
        logger.error("Phrase failure", e);
      }

      results.addTestResult(DefaultTestResult.passingTest(testCase));
    }

    return results;
  }


}


