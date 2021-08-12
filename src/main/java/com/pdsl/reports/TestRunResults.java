package com.pdsl.reports;

public interface TestRunResults {

    int passingTestTotal();
    int failingTestTotal();
    int passingPhraseTotal();
    int totalPhrases();
    int totalFilteredDuplicateTests();
}
