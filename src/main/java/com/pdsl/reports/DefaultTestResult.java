package com.pdsl.reports;

import com.google.common.base.Preconditions;
import com.pdsl.reports.proto.TechnicalReportData;
import com.pdsl.specifications.Phrase;
import com.pdsl.testcases.TestCase;

import java.util.Optional;

public class DefaultTestResult implements TestResult {

    private final TechnicalReportData.Status status;
    private final TestCase testCase;
    private int phrasesSkippedDueToFailure = 0;
    private int passingPhraseTotal = 0;
    private Optional<Phrase> failingPhrase = Optional.empty();
    private Optional<Throwable> failureReason = Optional.empty();

    public DefaultTestResult(int passingPhraseTotal, TestCase testCase, TechnicalReportData.Status status) {
        this.status = status;
        this.passingPhraseTotal = passingPhraseTotal;
        this.testCase = testCase;
    }

    public DefaultTestResult(int passingPhraseTotal, int phrasesSkippedDueToFailure, Phrase failingStep, Throwable failureReason, TestCase testCase) {
        Preconditions.checkArgument(passingPhraseTotal >= 0, "Passing phrases cannot be negative");
        Preconditions.checkArgument(phrasesSkippedDueToFailure >= 0, "Failing phrases cannot be negative");
        this.status = TechnicalReportData.Status.FAILED;
        this.passingPhraseTotal = passingPhraseTotal;
        this.phrasesSkippedDueToFailure = phrasesSkippedDueToFailure;
        this.failingPhrase = Optional.of(failingStep);
        this.failureReason = Optional.of(failureReason);
        this.testCase = testCase;
    }

    public static DefaultTestResult failedTest(TestCase testCase, Phrase failingPhrase, Throwable failureReason, int failedIndex, int phrasesSkippedDueToFailure) {
        return new DefaultTestResult(failedIndex,
                phrasesSkippedDueToFailure, failingPhrase,
                failureReason, testCase);
    }

    public static DefaultTestResult duplicateTest(TestCase testCase) {
        return new DefaultTestResult(0, testCase, TechnicalReportData.Status.DUPLICATE);
    }

    public static DefaultTestResult passingTest(TestCase testCase) {
        return new DefaultTestResult(testCase.getContextFilteredPhraseBody().size(), testCase, TechnicalReportData.Status.PASSED);
    }

    public TechnicalReportData.Status getStatus() {
        return status;
    }

    public String getTestCaseTitle() {
        return testCase.getTestTitle();
    }

    public int getPhrasesSkippedDueToFailure() {
        return phrasesSkippedDueToFailure;
    }

    public int getPassingPhraseTotal() {
        return passingPhraseTotal;
    }

    public Optional<Phrase> getFailingPhrase() {
        return failingPhrase;
    }

    public int getTotalPhrases() {
        return testCase.getContextFilteredPhraseBody().size();
    }

    public Optional<Throwable> getFailureReason() {
        return failureReason;
    }

    @Override
    public TestCase getTestCase() {
        return testCase;
    }
}
