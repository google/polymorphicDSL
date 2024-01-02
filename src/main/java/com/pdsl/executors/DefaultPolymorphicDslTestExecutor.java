package com.pdsl.executors;

import com.pdsl.logging.AnsiTerminalColorHelper;
import com.pdsl.logging.PdslThreadSafeOutputStream;
import com.pdsl.reports.DefaultTestResult;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.reports.TestRunResults;
import com.pdsl.specifications.FilteredPhrase;
import com.pdsl.specifications.Phrase;
import com.pdsl.specifications.PolymorphicDslTransformationException;
import com.pdsl.testcases.SharedTestCase;
import com.pdsl.testcases.TestCase;
import com.pdsl.testcases.TestSection;
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
public class DefaultPolymorphicDslTestExecutor implements TraceableTestRunExecutor {

  private static final Logger logger = LoggerFactory.getLogger(
      DefaultPolymorphicDslTestExecutor.class);
  private final ParseTreeWalker walker = new ParseTreeWalker();
  private final Optional<MultiOutputStream> outputStreams = Optional.of(
      new MultiOutputStream(new PdslThreadSafeOutputStream()));
  private static final Charset DEFAULT_CHARSET = Charset.defaultCharset();
  private static final byte[] RESET = AnsiTerminalColorHelper.RESET.getBytes(DEFAULT_CHARSET);

  @Override
  public PolymorphicDslTestRunResults runTests(Collection<TestCase> testCases,
      ParseTreeListener phraseRegistry) {
    // Walk the phrase registry to make sure all phrases are defined
    logger.info(
        AnsiTerminalColorHelper.BRIGHT_YELLOW + "Running tests..." + AnsiTerminalColorHelper.RESET);
    MetadataTestRunResults results = walk(testCases, new PhraseRegistry(phraseRegistry), "NONE");
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
    MetadataTestRunResults results = walk(testCases, new PhraseRegistry(subgrammarVisitor), "NONE");
    if (results.failingTestTotal() == 0) {
      logger.info(AnsiTerminalColorHelper.BRIGHT_YELLOW + "All phrases successfully executed!"
          + AnsiTerminalColorHelper.RESET);
    } else {
      logger.error(AnsiTerminalColorHelper.BRIGHT_RED + "There were test failures!"
          + AnsiTerminalColorHelper.RESET);
    }
    return (PolymorphicDslTestRunResults) results;
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

  private MetadataTestRunResults walk(Collection<TestCase> testCases, PhraseRegistry phraseRegistry, String context) {
    PolymorphicDslTestRunResults results = new PolymorphicDslTestRunResults(new PdslThreadSafeOutputStream(), context);
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
              walker.walk(phraseRegistry.listener.get(), activePhrase.getParseTree());
            } else {
              phraseRegistry.visitor.get().visit(activePhrase.getParseTree());
            }
            phraseIndex++;
            notifyStreams(
                (AnsiTerminalColorHelper.GREEN + activePhrase.getParseTree().getText() + "\n"
                    + AnsiTerminalColorHelper.RESET).getBytes(DEFAULT_CHARSET));
          }
          results.addTestResult(DefaultTestResult.passingTest(testCase));
        }
      } catch (Throwable e) {
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
        e.printStackTrace();
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
    MetadataTestRunResults results = walk(testCases, new PhraseRegistry(subgrammarListener),
        context);
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
    MetadataTestRunResults results = walk(testCases, new PhraseRegistry(visitor), context);
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
  public MetadataTestRunResults runTestsWithMetadata(Collection<SharedTestCase> sharedTestCases, String context) {
    PolymorphicDslTestRunResults results = new PolymorphicDslTestRunResults(new PdslThreadSafeOutputStream(), context);
    //Set<List<String>> previouslyExecutedTests = new HashSet<>();

    for(SharedTestCase sharedTestCase : sharedTestCases) {

      TestCase testCase = sharedTestCase.getTestCases().stream().findFirst().orElseThrow();

      notifyStreams(AnsiTerminalColorHelper.YELLOW.getBytes(DEFAULT_CHARSET));
      notifyStreams(String.format("%s%n%s", testCase.getOriginalSource(), testCase.getTestTitle())
          .getBytes(DEFAULT_CHARSET));
      notifyStreams(String.format("%n").getBytes(DEFAULT_CHARSET));
      notifyStreams(RESET);

      Phrase activePhrase = null;
      Iterator<TestSection> testBody = testCase.getContextFilteredTestSectionIterator();

      List<Iterator<FilteredPhrase>> sharedParseIterator = sharedTestCase.getSharedParseIterator();

      while(sharedParseIterator.get(0).hasNext()) {
        for (int i = 0; i < sharedParseIterator.size(); i++) {

          Iterator<FilteredPhrase> iterator = sharedParseIterator.get(i);

          FilteredPhrase filteredPhrase = iterator.next();

            Optional<ParseTree> parseTree = filteredPhrase.getParseTree(); // iterator.next()

            //TODO - Add implementation for the duplication checking
            if (parseTree.isPresent()) {

              InterpreterObj interpreterObj = sharedTestCase.getInterpreters().get(i);

              if (interpreterObj.getParseTreeListener().isPresent()) {
                walker.walk(interpreterObj.getParseTreeListener().get(), parseTree.get());
              } else {
                interpreterObj.getParseTreeVisitor().get().visit(parseTree.get());
              }

              notifyStreams(
                  (AnsiTerminalColorHelper.GREEN + parseTree.get().getText() + "\n"
                      + AnsiTerminalColorHelper.RESET).getBytes(DEFAULT_CHARSET));
            }
        }//for
      }//while
      results.addTestResult(DefaultTestResult.passingTest(testCase));
    }//for

    return results;
  }
}
