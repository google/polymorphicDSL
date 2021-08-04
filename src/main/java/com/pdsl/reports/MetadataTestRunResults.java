package com.pdsl.reports;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MetadataTestRunResults extends TestRunResults {
    Collection<TestMetadata> getTestMetadata();
    Optional<List<TestMetadata>> duplicateTestSpecifications();
    String getContext();
}
