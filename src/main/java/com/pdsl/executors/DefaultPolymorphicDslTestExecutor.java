package com.pdsl.executors;

import com.pdsl.logging.PdslThreadSafeOutputStream;
import com.pdsl.reports.DefaultTestResult;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.reports.PolymorphicDslTestRunResults;
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

public class DefaultPolymorphicDslTestExecutor implements TraceableTestRunExecutor {

    private static final Logger logger = LoggerFactory.getLogger(DefaultPolymorphicDslTestExecutor.class);
    private final ParseTreeWalker walker = new ParseTreeWalker();
    private final Optional<MultiOutputStream> outputStreams = Optional.of(new MultiOutputStream(new PdslThreadSafeOutputStream()));
    private final Charset charset = Charset.defaultCharset();

    @Override
    public PolymorphicDslTestRunResults runTests(Collection<TestCase> testCases, ParseTreeListener phraseRegistry) {
        // Walk the phrase registry to make sure all phrases are defined
        logger.info("Running tests...");
        MetadataTestRunResults results = walk(testCases, phraseRegistry, "NONE");
        if (results.failingTestTotal() == 0) {
            logger.info("All phrases successfully executed!");
        } else {
            logger.error("There were test failures!");
        }
        return (PolymorphicDslTestRunResults) results;
    }

    private MetadataTestRunResults walk(Collection<TestCase> testCases, ParseTreeListener phraseRegistry, String context) {
        PolymorphicDslTestRunResults results = new PolymorphicDslTestRunResults(new PdslThreadSafeOutputStream(), context);
        Set<List<String>> previouslyExecutedTests = new HashSet<>();
        for (TestCase testCase : testCases) {
            Phrase activePhrase = null;
            Iterator<TestSection> testBody = testCase.getContextFilteredTestSectionIterator();
            int phraseIndex = 0;
            try {
                if (previouslyExecutedTests.contains(testCase.getContextFilteredPhraseBody())) {
                    logger.warn("A test was skipped because after filtering it duplicated an earlier run test!%n\t%s", testCase.getTestTitle());
                    StringBuilder duplicateBody = new StringBuilder();
                    testCase.getContextFilteredTestSectionIterator().forEachRemaining(duplicateBody::append);
                    results.addTestResult(DefaultTestResult.duplicateTest(testCase));
                } else {
                    previouslyExecutedTests.add(testCase.getContextFilteredPhraseBody());
                    while (testBody.hasNext()) {
                        TestSection section = testBody.next();
                        if (section.getMetaData().isPresent()) {
                            notifyStreams(section.getMetaData().get());
                        }
                        activePhrase = section.getPhrase();
                        notifyStreams((activePhrase.getParseTree().getText() + "\n").getBytes(charset));
                        walker.walk(phraseRegistry, activePhrase.getParseTree());
                        phraseIndex++;
                    }
                    results.addTestResult(DefaultTestResult.passingTest(testCase));
                }
                continue;
            } catch (Throwable e) {
                int phrasesSkippedDueToFailure = 0;
                while (testBody.hasNext()) {
                    testBody.next();
                    phrasesSkippedDueToFailure++;
                }
                results.addTestResult(DefaultTestResult.failedTest(testCase,activePhrase, e, phraseIndex, phrasesSkippedDueToFailure));
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

    @Override
    public MetadataTestRunResults runTestsWithMetadata(Collection<TestCase> testCases, ParseTreeListener subgrammarListener, String context) {
        return runTests(testCases, subgrammarListener);
    }
}
