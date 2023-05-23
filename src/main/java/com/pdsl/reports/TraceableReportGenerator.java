package com.pdsl.reports;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * A generator of test run results that is able to compare how test cases written in a shared DSL performed across
 * multiple contexts.
 *
 * i.e., an identical test specification can be executed in two separate applications/platforms, but it is useful to
 * see if it passed in both of them or not.
 */
public interface TraceableReportGenerator {

    /**
     * Creates a report from the provided data.
     *
     * There are 3 data points for each report:
     * 1) The name of the application under test
     * 2) The context or testing methodology (e.g., Unit, Component, API, etc.)
     * 3) The actual test data
     *
     * @param testRunResults A map of application names to a map of contexts with test run results for each context
     * @return a URL of the generated report
     * @throws IOException  if there is an issue writing the report
     */
    URL generateReport(Map<String, Map<String, Collection<MetadataTestRunResults>>> testRunResults) throws IOException;

    /**
     * Organizes MetadataTestRunResults by application and context.
     *
     * @param testRunResults the results to generate the report from
     * @return test run results mapped from application -> context -> test run results
     */
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
