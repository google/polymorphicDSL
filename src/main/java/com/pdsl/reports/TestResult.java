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

    /** Returns the status of a test case.
     *
     * @return pass/fail/etc status
     */
    TechnicalReportData.Status getStatus();

    /**
     * Returns the title of the test case
     *
     * @return test case title
     */
    String getTestCaseTitle();

    /**
     * Returns the number of phrases not executed due to a failure.
     *
     * @return the number of skipped phrases
     */
    int getPhrasesSkippedDueToFailure();

    /**
     * Returns the number of passing phrases.
     *
     * @return the number of passing phrases
     */
    int getPassingPhraseTotal();

    /**
     * Returns the phrase that failed in the test if the test failed.
     *
     * All failed tests should have a failed phrase.
     *
     * @return optional phrases if there was a failure
     */
    Optional<Phrase> getFailingPhrase();

    /**
     * Returns the total number of phrases in the test.
     *
     * @return the total number of phrases in the test
     */
    int getTotalPhrases();

    /**
     * If the test failed, provides the exception thrown by the test.
     *
     * @return the exception causing the test failure, otherwise an empty optional
     */
    Optional<Throwable> getFailureReason();

    /**
     * Returns the testcase that was executed.
     *
     * @return the PDSL test case
     */
    TestCase getTestCase();
}
