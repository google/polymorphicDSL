package com.pdsl.reports;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public interface TraceableReportGenerator {
    URL generateReport(Collection<MetadataTestRunResults> testRunResults) throws IOException;

    static Map<String, Map<String, List<DefaultTestResult>>> testResourceToContextHelper(
            Collection<MetadataTestRunResults> testRunResults) {
        Map<String, Map<String, List<DefaultTestResult>>> resourceToRuns = new HashMap<>();
        for (MetadataTestRunResults metadataTestRunResults : testRunResults) {
            for (DefaultTestResult metadata : metadataTestRunResults.getTestMetadata()) {
                Map<String, List<DefaultTestResult>> contextToMetadata =
                        resourceToRuns.computeIfAbsent(metadata.getTestSuiteId(),
                                (k) -> new HashMap<String, List<DefaultTestResult>>());
                List<DefaultTestResult> metadataList = contextToMetadata.computeIfAbsent(metadataTestRunResults.getContext(),
                        (k) -> new ArrayList<DefaultTestResult>());
                metadataList.add(metadata);
            }
        }
        return resourceToRuns;
    }
}
