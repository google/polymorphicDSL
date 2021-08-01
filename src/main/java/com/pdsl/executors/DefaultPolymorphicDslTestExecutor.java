package com.pdsl.executors;

import com.pdsl.logging.PdslThreadSafeOutputStream;
import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.reports.TestMetadata;
import com.pdsl.specifications.Phrase;
import com.pdsl.specifications.PolymorphicDslTransformationException;
import com.pdsl.testcases.TestCase;
import com.pdsl.testcases.TestSection;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;

public class DefaultPolymorphicDslTestExecutor implements PolymorphicDslTestExecutor {

    private static final Logger logger = LoggerFactory.getLogger(DefaultPolymorphicDslTestExecutor.class);
    private ParseTreeWalker walker = new ParseTreeWalker();
    private Optional<MultiOutputStream> outputStreams = Optional.of(new MultiOutputStream(new PdslThreadSafeOutputStream()));
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
        logger.debug("Executing grammar");
        for (TestCase testCase : testCases) {
            testCase.getTestSectionIterator().forEachRemaining(testSection -> walker.walk(grammarListener, testSection.getPhrase().getParseTree()));
        }
        logger.debug("Executing subgrammars...");
        return walk(testCases, subgrammarListener);
    }

    private PolymorphicDslTestRunResults walk(Collection<TestCase> testCases, ParseTreeListener phraseRegistry) {
        PolymorphicDslTestRunResults results = new PolymorphicDslTestRunResults(new PdslThreadSafeOutputStream());
        Set<Long> previouslyExecutedTests = new HashSet<>();
        for (TestCase testCase : testCases) {
            int totalPassingPhrases = 0;
            Phrase activePhrase = null;
            Iterator<TestSection> testBody = testCase.getTestSectionIterator();
            int testBodySize = 0;
            try {
                if (previouslyExecutedTests.contains(testCase.getTestCaseId())) {
                    logger.warn("A test was skipped because after filtering it duplicated an earlier run test!%n\t%s", testCase.getTestTitle());
                    StringBuilder duplicateBody = new StringBuilder();
                    testCase.getTestSectionIterator().forEachRemaining(duplicateBody::append);
                    results.addTestResult(TestMetadata.duplicateTest(testCase.getTestTitle(), testCase.getTestCaseId()));
                } else {
                    previouslyExecutedTests.add(testCase.getTestCaseId());
                    while (testBody.hasNext()) {
                        TestSection section = testBody.next();
                        if (section.getMetaData().isPresent()) {
                            notifyStreams(section.getMetaData().get());
                        }
                        activePhrase = section.getPhrase();
                        testBodySize++;
                        notifyStreams((activePhrase.getParseTree().getText() + "\n").getBytes(charset));
                        walker.walk(phraseRegistry, activePhrase.getParseTree());
                        totalPassingPhrases++;
                    }
                    results.addTestResult(TestMetadata.passingTest(testCase.getTestTitle(), totalPassingPhrases, testCase.getTestCaseId()));
                }
                continue;
            } catch (Throwable e) {
                while (testBody.hasNext()) {
                    testBody.next();
                    testBodySize++;
                }
                int phrasesSkippedDueToFailure = testBodySize                                                                                                                                        // All phrases
                        - totalPassingPhrases // Minus successfully executed steps
                        - 1; // minus the failing phrase
                String errorPhrase = activePhrase != null ? activePhrase.getParseTree().getText() : "phrase was null!";
                results.addTestResult(TestMetadata.failedTest(testCase.getTestTitle(), totalPassingPhrases,
                        phrasesSkippedDueToFailure, errorPhrase, e,
                        testCase.getTestCaseId()));
								logger.error("Phrase failure", e);
                continue;
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
}
