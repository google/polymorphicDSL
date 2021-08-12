package com.pdsl.reports;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MetadataTestRunResults extends TestRunResults {
    Collection<TestResult> getTestResults();
    Optional<List<TestResult>> duplicateTestSpecifications();
    String getContext();
}

// Applications
// Resource
// Context
// TestResult