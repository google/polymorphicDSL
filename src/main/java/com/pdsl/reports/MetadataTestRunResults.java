package com.pdsl.reports;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * The aggregated results of a PDSL test run.
 *
 * This includes tests in all states, including passed, failed, etc.
 *
 * */
public interface MetadataTestRunResults extends TestRunResults {
    Collection<TestResult> getTestResults();

    /** Returns the number of tests that turned into duplicates after being filtered by PDSL. */
    Optional<List<TestResult>> duplicateTestSpecifications();
    String getContext();
}