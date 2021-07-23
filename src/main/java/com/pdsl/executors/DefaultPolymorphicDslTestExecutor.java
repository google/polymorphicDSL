package com.pdsl.executors;

import com.pdsl.logging.AnsiTerminalColorHelper;
import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.reports.TestMetadata;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.testcases.TestCase;
import com.pdsl.testcases.TestSection;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Stream;

public class DefaultPolymorphicDslTestExecutor implements PolymorphicDslTestExecutor {

    private Logger logger = LoggerFactory.getLogger(DefaultPolymorphicDslTestExecutor.class);
    private ParseTreeWalker walker = new ParseTreeWalker();
    private Optional<Collection<OutputStream>> outputStreams = Optional.of(List.of(System.out));
    private Charset charset = Charset.defaultCharset();
    @Override
    public PolymorphicDslTestRunResults runTests(Collection<TestCase> testCases, ParseTreeListener phraseRegistry) {
        // Walk the phrase registry to make sure all phrases are defined
        logger.info("Running tests...");
        PolymorphicDslTestRunResults results = walk(testCases, phraseRegistry);
        if (results.failingTestTotal() == 0) {
            logger.info("All phrases successfully executed!");
        } else {
            logger.error("There were test failures!");
        }
        return results;
    }

    @Override
    public PolymorphicDslTestRunResults runTests(Collection<TestCase> testCases, ParseTreeListener grammarListener, ParseTreeListener subgrammarListener) {
        logger.info("Beginning  Polymorphic DSL test case execution.");
        logger.debug(String.format("Executing grammar"));
        for (TestCase testCase : testCases) {
            testCase.getTestBody().forEachRemaining(testSection -> walker.walk(grammarListener, testSection.getParseTree()));
        }
        logger.debug("Executing subgrammars...");
        PolymorphicDslTestRunResults results = walk(testCases, subgrammarListener);
        return results;
    }

    private PolymorphicDslTestRunResults walk(Collection<TestCase> testCases, ParseTreeListener phraseRegistry) {
        PolymorphicDslTestRunResults results = new PolymorphicDslTestRunResults(System.out);
        Set<Long> previouslyExecutedTests = new HashSet<>();
        int totalDuplicates = 0;
        for (TestCase testCase : testCases) {
            int totalPassingPhrases = 0;
            ParseTree activePhrase = null;
            Iterator<TestSection> testBody = testCase.getTestBody();
            try {
                if (previouslyExecutedTests.contains(testCase.getTestCaseId())) {
                    logger.warn("A test was skipped because after filtering it duplicated an earlier run test!\n\t" + testCase.getTestTitle());
                    StringBuilder duplicateBody = new StringBuilder();
                    testCase.getTestBody().forEachRemaining(duplicateBody::append);
                    totalDuplicates++;
                    results.addTestResult(TestMetadata.duplicateTest(testCase.getTestTitle(), testCase.getTestCaseId()));
                    continue;
                } else {
                    previouslyExecutedTests.add(testCase.getTestCaseId());
                    while (testBody.hasNext()) {
                        TestSection section = testBody.next();
                        if (section.getMetaData().isPresent()) {
                            notifyStreams(section.getMetaData().get());
                        }
                        activePhrase = section.getParseTree();
                        notifyStreams((activePhrase.getText() + "\n").getBytes(charset));
                        walker.walk(phraseRegistry, activePhrase);
                        totalPassingPhrases++;
                    };
                    results.addTestResult(TestMetadata.passingTest(testCase.getTestTitle(), totalPassingPhrases, testCase.getTestCaseId()));
                    continue;
                }
            } catch (Throwable e) {
                int phrasesSkippedDueToFailure = testCase.getBodySize() // All phrases
                        - totalPassingPhrases // Minus successfully executed steps
                        - 1; // minus the failing phrase
                String errorPhrase = activePhrase != null ? activePhrase.getText() : "phrase was null!";
                results.addTestResult(TestMetadata.failedTest(testCase.getTestTitle(), totalPassingPhrases,
                        phrasesSkippedDueToFailure, errorPhrase, e,
                        testCase.getTestCaseId()));
                e.printStackTrace();
                continue;
            }

        }
        return results;
    }

    private void notifyStreams(ByteArrayOutputStream outputStream) {
        if (outputStreams.isPresent()) {
            for (OutputStream stream : outputStreams.get()) {
                try {
                    stream.write(outputStream.toByteArray());
                } catch (IOException e) {
                    logger.error("An issue processing metadata occurred!", e);
                }
            }
        }
    }

    private void notifyStreams(byte[] outputStream) {
        if (outputStreams.isPresent()) {
            for (OutputStream stream : outputStreams.get()) {
                try {
                    stream.write(outputStream);
                } catch (IOException e) {
                    logger.error("An issue processing metadata occurred!", e);
                }
            }
        }
    }
}
