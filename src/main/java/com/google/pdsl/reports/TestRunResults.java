package com.google.pdsl.reports;

import java.util.List;
import java.util.Optional;

public interface TestRunResults {

    int passingTestTotal();
    int failingTestTotal();
    int passingPhraseTotal();
    int totalPhrases();
    int totalFilteredDuplicateTests();
    Optional<List<TestMetadata>> duplicateTestSpecifications();
}
