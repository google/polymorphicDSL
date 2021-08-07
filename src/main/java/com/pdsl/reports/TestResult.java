package com.pdsl.reports;

import com.pdsl.specifications.Phrase;

import java.util.List;
import java.util.Optional;

/**
 * The result from the execution of a single {@link com.pdsl.testcases.TestCase} that was processed by a
 * {@link com.pdsl.executors.TraceableTestRunExecutor}.
 */
public interface TestResult {

    enum Status {
        PASSED,
        FAILED,
        DUPLICATE;
    }

    DefaultTestResult.Status getStatus();
    String getTestSuiteId();
    int getPhrasesSkippedDueToFailure();
    int getPassingPhraseTotal();
    Optional<Phrase> getFailingPhrase();
    int getTotalPhrases();
    Optional<Throwable> getFailureReason();
    List<String> getPhraseBody();
}
