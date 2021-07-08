package com.pdsl.reports;

import org.antlr.runtime.tree.ParseTree;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class TestMetadata {

    private boolean isPassed;
    private final String testSuiteId;

    private int phrasesSkippedDueToFailure = 0;
    private int passingPhraseTotal = 0;

    private final long phraseBodyId;

    private Optional<String> failingPhrase = Optional.empty();
    private Optional<Throwable> failureReason = Optional.empty();

    public TestMetadata(String testSuiteId, int passingPhraseTotal, long phraseBodyId) {
        this.isPassed = true;
        this.testSuiteId = testSuiteId;
        this.passingPhraseTotal = passingPhraseTotal;
        this.phraseBodyId = phraseBodyId;
    }

    public static TestMetadata failedTest(String testSuiteId, int passingPhraseTotal,
                                          int phrasesSkippedDueToFailure, String failingStep, Throwable failureReason,
            long phraseBodyId) {
        return new TestMetadata(testSuiteId, passingPhraseTotal, phrasesSkippedDueToFailure, failingStep, failureReason, phraseBodyId);
    }

    public static TestMetadata duplicateTest(String testTitle, long testCaseId) {
        return new TestMetadata(testTitle, 0, testCaseId);
    }

    public static TestMetadata passingTest(String testSuiteId, int passingPhraseTotal, long phraseBodyId) {
        return new TestMetadata(testSuiteId, passingPhraseTotal, phraseBodyId);
    }

    public TestMetadata(String testSuiteId, int passingPhraseTotal, int phrasesSkippedDueToFailure, String failingStep, Throwable failureReason, long phraseBodyId) {
        this.isPassed = false;
        this.testSuiteId = testSuiteId;
        this.passingPhraseTotal = passingPhraseTotal;
        this.phrasesSkippedDueToFailure = phrasesSkippedDueToFailure;
        this.failingPhrase = Optional.of(failingStep);
        this.failureReason = Optional.of(failureReason);
        this.phraseBodyId = phraseBodyId;
    }

    public long getPhraseBodyId() {
        return phraseBodyId;
    }

    public boolean getIsPassed() { return isPassed; }

    public String getTestSuiteId() {
        return testSuiteId;
    }

    public int getPhrasesSkippedDueToFailure() {
        return phrasesSkippedDueToFailure;
    }

    public int getPassingPhraseTotal() {
        return passingPhraseTotal;
    }

    public Optional<String> getFailingPhrase() {
        return failingPhrase;
    }

    public int getTotalPhrases() {
        return passingPhraseTotal + phrasesSkippedDueToFailure  + (isPassed ? 0 : 1);
    }
}
