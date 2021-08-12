package com.pdsl.reports;

import com.pdsl.reports.proto.TechnicalReportData;
import com.pdsl.specifications.Phrase;
import com.pdsl.testcases.TestCase;

import java.util.Optional;

/**
 * The result from the execution of a single {@link com.pdsl.testcases.TestCase} that was processed by a
 * {@link com.pdsl.executors.TraceableTestRunExecutor}.
 */
public interface TestResult {

    TechnicalReportData.Status getStatus();
    String getTestCaseTitle();
    int getPhrasesSkippedDueToFailure();
    int getPassingPhraseTotal();
    Optional<Phrase> getFailingPhrase();
    int getTotalPhrases();
    Optional<Throwable> getFailureReason();
    TestCase getTestCase();
}
