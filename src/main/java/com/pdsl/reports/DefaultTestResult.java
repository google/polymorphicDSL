package com.pdsl.reports;

import com.google.common.base.Preconditions;
import com.pdsl.reports.proto.TechnicalReportData;
import com.pdsl.specifications.Phrase;
import com.pdsl.testcases.TestCase;

import java.util.Optional;

/**
 * A standard result from an executed test case.
 */
public class DefaultTestResult implements TestResult {

    private final TechnicalReportData.Status status;
    private final TestCase testCase;
    private int phrasesSkippedDueToFailure = 0;
    private int passingPhraseTotal = 0;
    private Optional<Phrase> failingPhrase = Optional.empty();
    private Optional<Throwable> failureReason = Optional.empty();

    /**
     * Creates a test result for an exectuted test case.
     *
     * @param passingPhraseTotal the number of phrases that successfully executed
     * @param testCase the test case executed
     * @param status the result of the test case
     */
    public DefaultTestResult(int passingPhraseTotal, TestCase testCase, TechnicalReportData.Status status) {
        this.status = status;
        this.passingPhraseTotal = passingPhraseTotal;
        this.testCase = testCase;
    }

    /**
     * Creates a failed test result for an executed test case.
     *
     * @param passingPhraseTotal the number of phrases that successfully executed
     * @param phrasesSkippedDueToFailure the number of phrases not executed due to premature failure
     * @param testCase the test case executed
     * @param failingStep the result of the test case
     * @param failureReason the exception encountered when the test failed
     */
    public DefaultTestResult(int passingPhraseTotal, int phrasesSkippedDueToFailure, Phrase failingStep, Throwable failureReason, TestCase testCase) {
        Preconditions.checkArgument(passingPhraseTotal >= 0, "Passing phrases cannot be negative");
        Preconditions.checkArgument(phrasesSkippedDueToFailure >= 0, "Failing phrases cannot be negative");
        this.status = TechnicalReportData.Status.FAILED;
        this.passingPhraseTotal = passingPhraseTotal;
        this.phrasesSkippedDueToFailure = phrasesSkippedDueToFailure;
        this.failingPhrase = Optional.ofNullable(failingStep);
        this.failureReason = Optional.of(failureReason);
        this.testCase = testCase;
    }

    /**
     * Creates a test result for a failed test case.
     *
     * @param failingPhrase the phrase that failed during execution
     * @param phrasesSkippedDueToFailure the number of phrases not executed due to premature failure
     * @param testCase the test case executed
     * @param failedIndex the result index of the failed phrase in the test case
     * @param failureReason the exception encountered when the test failed
     * @return a test result
     */
    public static DefaultTestResult failedTest(TestCase testCase, Phrase failingPhrase, Throwable failureReason, int failedIndex, int phrasesSkippedDueToFailure) {
        return new DefaultTestResult(failedIndex,
                phrasesSkippedDueToFailure, failingPhrase,
                failureReason, testCase);
    }

    /**
     * Creates a test result for a duplicate test.
     *
     * @param testCase the executed test case
     * @return a test result
     */
    public static DefaultTestResult duplicateTest(TestCase testCase) {
        return new DefaultTestResult(0, testCase, TechnicalReportData.Status.DUPLICATE);
    }

    /**
     * Creates a passing test result
     *
     * @param testCase that was executed
     * @return a passing test result
     */
    public static DefaultTestResult passingTest(TestCase testCase) {
        return new DefaultTestResult(testCase.contextFilteredPhraseBody().size(), testCase, TechnicalReportData.Status.PASSED);
    }

    /**
     * Returns the status of the test result.
     *
     * @return test case status
     */
    public TechnicalReportData.Status getStatus() {
        return status;
    }

    /**
     * Provides the title of the test case.
     *
     * @return the test case title
     */
    public String getTestCaseTitle() {
        return testCase.testTitle();
    }

    /**
     * Provides the number of phrases skipped due to failure.
     *
     * @return skipped phrases count
     */
    public int getPhrasesSkippedDueToFailure() {
        return phrasesSkippedDueToFailure;
    }

    /**
     * Provides the total number of passing phrases.
     *
     * @return the total passing phrases
     */
    public int getPassingPhraseTotal() {
        return passingPhraseTotal;
    }

    /**
     * Provides a failing phrase if the test case failed.
     *
     * @return a failing phrase if the test case failed, else an empty optional
     */
    public Optional<Phrase> getFailingPhrase() {
        return failingPhrase;
    }

    /**
     * Returns the total phrases in the test case.
     *
     * @return the total number of phrases
     */
    public int getTotalPhrases() {
        return testCase.contextFilteredPhraseBody().size();
    }

    /**
     * Returns an exception if the test failed.
     *
     * @return the throwable if the test failed, else an empty optional
     */
    public Optional<Throwable> getFailureReason() {
        return failureReason;
    }

    @Override
    public TestCase getTestCase() {
        return testCase;
    }
}
