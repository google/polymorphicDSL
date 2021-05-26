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

    private final int phraseBodyId;

    private Optional<String> failingPhrase = Optional.empty();
    private Optional<Throwable> failureReason = Optional.empty();

    public TestMetadata(String testSuiteId, int passingPhraseTotal, int phraseBodyId) {
        this.isPassed = true;
        this.testSuiteId = testSuiteId;
        this.passingPhraseTotal = passingPhraseTotal;
        this.phraseBodyId = phraseBodyId;
    }

    public TestMetadata(String testSuiteId, int passingPhraseTotal, int phrasesSkippedDueToFailure, String failingStep, Throwable failureReason, int phraseBodyId) {
        this.isPassed = false;
        this.testSuiteId = testSuiteId;
        this.passingPhraseTotal = passingPhraseTotal;
        this.phrasesSkippedDueToFailure = phrasesSkippedDueToFailure;
        this.failingPhrase = Optional.of(failingStep);
        this.failureReason = Optional.of(failureReason);
        this.phraseBodyId = phraseBodyId;
    }

    public int getPhraseBodyId() {
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
