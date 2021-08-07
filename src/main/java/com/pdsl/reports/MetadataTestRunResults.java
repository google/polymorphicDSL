package com.pdsl.reports;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MetadataTestRunResults extends TestRunResults {
    Collection<DefaultTestResult> getTestMetadata();
    Optional<List<DefaultTestResult>> duplicateTestSpecifications();
    String getContext();
}
