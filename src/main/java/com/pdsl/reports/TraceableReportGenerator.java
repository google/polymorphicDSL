package com.pdsl.reports;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public interface TraceableReportGenerator {
    URL generateReport(Map<String, Map<String, Collection<MetadataTestRunResults>>> testRunResults) throws IOException;

    static Map<String, Map<String, List<TestResult>>> testResourceToContextHelper(
            Collection<MetadataTestRunResults> testRunResults) {
        Map<String, Map<String, List<TestResult>>> resourceToRuns = new HashMap<>();
        for (MetadataTestRunResults metadataTestRunResults : testRunResults) {
            for (TestResult metadata : metadataTestRunResults.getTestResults()) {
                Map<String, List<TestResult>> contextToMetadata =
                        resourceToRuns.computeIfAbsent(metadata.getTestCaseTitle(),
                                k -> new HashMap<String, List<TestResult>>());
                List<TestResult> metadataList = contextToMetadata.computeIfAbsent(metadataTestRunResults.getContext(),
                        k -> new ArrayList<TestResult>());
                metadataList.add(metadata);
            }
        }
        return resourceToRuns;
    }
}
