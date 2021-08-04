package com.pdsl.reports;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.*;

public interface TraceableReportGenerator {
    URL generateReport(Collection<MetadataTestRunResults> testRunResults) throws IOException;

    public static Map<String, Map<String, List<TestMetadata>>> testResourceToContextHelper(
            Collection<MetadataTestRunResults> testRunResults) {
        // Mock test
        // Unit
        // Unit 1
        // Unit 2
        //Map<Test Resource, Map<Context, List<TestMetadata>>
        Map<String, Map<String, List<TestMetadata>>> resourceToRuns = new HashMap<>();
        for (MetadataTestRunResults metadataTestRunResults : testRunResults) {
            for (TestMetadata metadata : metadataTestRunResults.getTestMetadata()) {
                Map<String, List<TestMetadata>> contextToMetadata =
                        resourceToRuns.computeIfAbsent(metadata.getTestSuiteId(),
                                (k) -> new HashMap<String, List<TestMetadata>>());
                List<TestMetadata> metadataList = contextToMetadata.computeIfAbsent(metadataTestRunResults.getContext(),
                        (k) -> new ArrayList<TestMetadata>());
                metadataList.add(metadata);
            }
        }
        return resourceToRuns;
    }
}
