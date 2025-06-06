package com.pdsl.executors;

import com.pdsl.logging.AnsiTerminalColorHelper;
import com.pdsl.logging.PdslThreadSafeOutputStream;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.specifications.PolymorphicDslTransformationException;
import com.pdsl.testcases.SharedTestCase;
import com.pdsl.testcases.TestCase;
import com.pdsl.testcases.TestSection;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;

/**
 * A logger for the progress of test execution where the output has color.
 *
 * The underlying implementation uses ANSII escape characters which may not be supported by all terminals.
 */
public class ColorizedLoggerObserver implements ExecutorObserver {

    private static final PdslThreadSafeOutputStream stream = new PdslThreadSafeOutputStream();
    private static final String exceptionMessage = "Could not log!";
    private final Charset charset;
    private final byte[] RESET;
    private Strategy strategy = Strategy.HIDE_INTERPRETER;
    // Avoid race conditions in logic
    // where a sharedtestsuite might be running with normal test suites in concurrent environment
    private int concurrencyCounter = 0;
    private enum Strategy {
        LOG_INTERPRETER,
        HIDE_INTERPRETER;
    }

    public ColorizedLoggerObserver() {
        this.charset = Charset.defaultCharset();
        this.RESET = AnsiTerminalColorHelper.RESET.getBytes(charset);
    }

    public ColorizedLoggerObserver(Charset charset) {
        this.charset = charset;
        this.RESET = AnsiTerminalColorHelper.RESET.getBytes(charset);
    }

    private void notifyStreams(InputStream inputStream) {
            try {
                stream.write(inputStream.readAllBytes());
            } catch (IOException e) {
                throw new PolymorphicDslTransformationException(exceptionMessage, e);
            }
    }

    private void notifyStreams(byte[] bytes) {
            try {
                stream.write(bytes);
            } catch (IOException e) {
                throw new PolymorphicDslTransformationException(exceptionMessage, e);
            }
    }

    private void notifyStreams(String str) {
        try {
            stream.write(str.getBytes(Charset.defaultCharset()));
        } catch (IOException e) {
            throw new PolymorphicDslTransformationException(exceptionMessage, e);
        }
    }

    @Override
    public void onBeforeTestSuite(Collection<? extends SharedTestCase> testCases,
                                   String context) {
        strategy = Strategy.LOG_INTERPRETER;
        logBeforeSuite();
    }

    @Override
    public void onBeforeTestCase(TestCase testCase) {
        notifyStreams(AnsiTerminalColorHelper.YELLOW
                + String.format("%s%n%s%n", testCase.getOriginalSource(), testCase.getTestTitle()
                + AnsiTerminalColorHelper.RESET));
       Object longDescription = testCase.getMetadata().get(TestCase.STANDARD_LONG_DESCRIPTION_KEY);
        if (longDescription instanceof InputStream descriptionStream) {
            notifyStreams(AnsiTerminalColorHelper.CYAN.getBytes(charset));
            notifyStreams(descriptionStream);
            notifyStreams(RESET);
        }
    }

    @Override
    public void onTestCaseSuccess(TestCase testCase) {
        notifyStreams(
                (AnsiTerminalColorHelper.GREEN + "All Sentences are parsed." + "\n"
                        + AnsiTerminalColorHelper.RESET).getBytes(charset));
    }

    private void notateInterpreter(Class<?> interpreter) {
        if (strategy.equals(Strategy.LOG_INTERPRETER)) {
            notifyStreams(AnsiTerminalColorHelper.GREY);
            notifyStreams(String.format("^ %s %n", interpreter));
            notifyStreams(AnsiTerminalColorHelper.RESET);
        }
    }

    @Override
    public void onAfterPhrase(ParseTreeListener listener, ParseTreeWalker walker, TestSection testSection) {
        afterPhrase(testSection);
        notateInterpreter(listener.getClass());
    }

    @Override
    public void onAfterPhrase(ParseTreeVisitor<?> visitor, TestSection testSection) {

        afterPhrase(testSection);
        notateInterpreter(visitor.getClass());
    }

    private void afterPhrase(TestSection testSection) {
        notifyStreams(
                (AnsiTerminalColorHelper.GREEN + testSection.getPhrase().getParseTree().getText() + "\n"
                        + AnsiTerminalColorHelper.RESET).getBytes(charset));
    }

    @Override
    public void onPhraseFailure(ParseTreeListener listener, TestSection testSection, TestCase testCase, Throwable exception) {
        onFailure(testSection);
    }

    private void onFailure(TestSection testSection) {
        notifyStreams(
                (AnsiTerminalColorHelper.BRIGHT_RED + testSection.getPhrase().getParseTree().getText() + "\n"
                        + AnsiTerminalColorHelper.RESET).getBytes(charset));
    }

    @Override
    public void onPhraseFailure(ParseTreeVisitor<?> visitor, TestSection testSection, TestCase testCase, Throwable exception) {
        onFailure(testSection);
    }

    @Override
    public void onBeforeTestSuite(Collection<? extends TestCase> testCases, ParseTreeVisitor<?> visitor, String context) {
        logBeforeSuite();
    }

    @Override
    public void onBeforeTestSuite(Collection<? extends TestCase> testCases, ParseTreeListener listener, String context) {
        logBeforeSuite();
    }

    @Override
    public void onAfterTestSuite(Collection<? extends TestCase> testCases, ParseTreeVisitor<?> visitor, MetadataTestRunResults results, String context) {
        logAfterSuite(results);
    }

    @Override
    public void onAfterTestSuite(Collection<? extends TestCase> testCases, ParseTreeListener listener, MetadataTestRunResults results, String context) {
        logAfterSuite(results);
        concurrencyCounter--;
        if (concurrencyCounter <= 0) {
            strategy = Strategy.HIDE_INTERPRETER;
        }
    }

    @Override
    public void onDuplicateSkipped(TestCase testCase) {
        notifyStreams(String.format(
                "A test was skipped because after filtering it duplicated an earlier run test!%n\t%s",
                testCase.getTestTitle()));
    }

    private  void logAfterSuite(MetadataTestRunResults results) {
        if (results.failingTestTotal() == 0) {
            notifyStreams(AnsiTerminalColorHelper.BRIGHT_GREEN + "All phrases successfully executed!"
                    + AnsiTerminalColorHelper.RESET);
        } else {
            notifyStreams(AnsiTerminalColorHelper.BRIGHT_RED + "There were test failures!"
                    + AnsiTerminalColorHelper.RESET);
        }
    }

    private void logBeforeSuite() {
        notifyStreams(AnsiTerminalColorHelper.BRIGHT_YELLOW + "Running tests..." + AnsiTerminalColorHelper.RESET);
        concurrencyCounter++;
    }
}
