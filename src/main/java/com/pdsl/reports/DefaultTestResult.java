package com.pdsl.reports;

import com.pdsl.specifications.Phrase;
import com.pdsl.testcases.TestCase;

import java.util.List;
import java.util.Optional;

public class DefaultTestResult implements TestResult {

    private Status status;
    private final TestCase testCase;
    private int phrasesSkippedDueToFailure = 0;
    private int passingPhraseTotal = 0;
    private Optional<Phrase> failingPhrase = Optional.empty();
    private Optional<Throwable> failureReason = Optional.empty();

    public DefaultTestResult(int passingPhraseTotal, TestCase testCase, Status status) {
        this.status = status;
        this.passingPhraseTotal = passingPhraseTotal;
        this.testCase = testCase;
    }

    public DefaultTestResult(int passingPhraseTotal, int phrasesSkippedDueToFailure, Phrase failingStep, Throwable failureReason, TestCase testCase) {
        this.status = Status.FAILED;
        this.passingPhraseTotal = passingPhraseTotal;
        this.phrasesSkippedDueToFailure = phrasesSkippedDueToFailure;
        this.failingPhrase = Optional.of(failingStep);
        this.failureReason = Optional.of(failureReason);
        this.testCase = testCase;
    }

    public static DefaultTestResult failedTest(TestCase testCase, Phrase failingPhrase, Throwable failureReason, int failedIndex) {
        return new DefaultTestResult(failedIndex,
                testCase.getContextFilteredPhraseBody().size() - failedIndex, failingPhrase,
                failureReason, testCase);
    }

    public static DefaultTestResult duplicateTest(TestCase testCase) {
        return new DefaultTestResult(0, testCase, Status.DUPLICATE);
    }

    public static DefaultTestResult passingTest(TestCase testCase) {
        return new DefaultTestResult(testCase.getContextFilteredPhraseBody().size(), testCase, Status.PASSED);
    }

    public Status getStatus() {
        return status;
    }

    public String getTestSuiteId() {
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

    public List<String> getPhraseBody() { return testCase.getContextFilteredPhraseBody(); }
}
