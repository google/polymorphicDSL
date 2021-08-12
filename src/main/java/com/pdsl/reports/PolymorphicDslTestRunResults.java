package com.pdsl.reports;

import com.google.common.base.Preconditions;
import com.pdsl.exceptions.PolymorphicDslReportException;
import com.pdsl.reports.proto.TechnicalReportData;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

public class PolymorphicDslTestRunResults implements TestRunResults, MetadataTestRunResults, ReportListener {

    private final List<OutputStream> dslReports;
    private final List<TestResult> results = new LinkedList<>();
    private final Set<List<String>> resultIds = new HashSet<>();
    // map for fast lookup
    private final Map<List<String>, List<DefaultTestResult>> duplicateIdToTestResult = new HashMap<>();
    // The map cannot hold more than one duplicate
    private final List<TestResult> duplicateTestResults = new LinkedList<>();
    private final String context;
    public PolymorphicDslTestRunResults(OutputStream report, String context) {
        Preconditions.checkNotNull(report, "reports cannot be null!");
        dslReports = List.of(report);
        this.context = context;
    }

    public PolymorphicDslTestRunResults(List<OutputStream> reports, String context) {
        Preconditions.checkNotNull(reports, "reports cannot be null!");
        Preconditions.checkArgument(!reports.isEmpty(), "report output streams cannot be empty");
        dslReports = new LinkedList<>(reports);
        this.context = context;
    }

    @Override
    public void addTestResult(DefaultTestResult defaultTestResult) {
        Preconditions.checkNotNull(defaultTestResult, "Test metadata cannot be null!");
        List<String> id = defaultTestResult.getTestCase().getUnfilteredPhraseBody();
        if (resultIds.contains(id)) {
            if (duplicateIdToTestResult.containsKey(id)) {
                duplicateIdToTestResult.get(id).add(defaultTestResult);
            } else {
                List<DefaultTestResult> duplicates = new LinkedList<>();
                duplicates.add(defaultTestResult);
                duplicateIdToTestResult.put(id, duplicates);
            }
            duplicateTestResults.add(defaultTestResult);
        } else {
            results.add(defaultTestResult);
            resultIds.add(id);
        }
    }

    @Override
    public void appendToDslReport(byte[] b) {
        try {
            for (OutputStream stream : dslReports) {
                stream.write(b);
            }
        } catch (IOException e) {
            throw new PolymorphicDslReportException("Could not update polymorphic test report!");
        }
    }

    @Override
    public int passingTestTotal() {
        return results.stream().filter(metadata -> metadata.getStatus().equals(TechnicalReportData.Status.PASSED))
                .collect(Collectors.toList()).size();
    }

    @Override
    public int failingTestTotal() {
        return results.stream().filter(testMetadata -> testMetadata.getStatus().equals(TechnicalReportData.Status.FAILED)).collect(Collectors.toList()).size();
    }

    @Override
    public int passingPhraseTotal() {
        return results.stream().mapToInt(TestResult::getPassingPhraseTotal).sum();
    }

    @Override
    public int totalPhrases() {
        return results.stream().mapToInt(TestResult::getTotalPhrases).sum();
    }

    @Override
    public int totalFilteredDuplicateTests() {
        return duplicateTestResults.size();
    }

    @Override
    public Optional<List<TestResult>> duplicateTestSpecifications() {
        if (duplicateTestResults.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(duplicateTestResults);
        }
    }

    @Override
    public String getContext() {
        return context;
    }

    @Override
    public List<OutputStream> getDslReports() {
        return dslReports;
    }

    @Override
    public boolean containsFilteredTest(List<String> filteredBody) {
        return resultIds.contains(filteredBody);
    }

    @Override
    public Collection<TestResult> getTestResults() {
        return results;
    }
}
