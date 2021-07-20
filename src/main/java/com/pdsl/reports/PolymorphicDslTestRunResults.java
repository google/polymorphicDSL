package com.pdsl.reports;

import com.google.common.base.Preconditions;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

public class PolymorphicDslTestRunResults implements TestRunResults, ReportListener {

    private List<TestMetadata> results = new LinkedList<>();
    private Set<Long> resultIds = new HashSet<>();
    // map for fast lookup
    private Map<Long, List<TestMetadata>> duplicateIdToTestResult = new HashMap<>();
    // The map cannot hold more than one duplicate
    private List<TestMetadata> duplicateTestResults = new LinkedList<>();
    private final List<OutputStream> dslReports;

    public PolymorphicDslTestRunResults(OutputStream report) {
        Preconditions.checkNotNull(report, "reports cannot be null!");;
        dslReports = List.of(report);
    }

    public PolymorphicDslTestRunResults(List<OutputStream> reports) {
        Preconditions.checkNotNull(reports, "reports cannot be null!");;
        Preconditions.checkArgument(reports.isEmpty(), "report output streams cannot be empty");
        dslReports = new LinkedList<>(reports);
    }

    public List<TestMetadata> getTestResultMetadata() {
        return results;
    }

    @Override
    public void addTestResult(TestMetadata testMetadata) {
        Preconditions.checkNotNull(testMetadata, "Test metadata cannot be null!");
        long id = testMetadata.getPhraseBodyId();
        if (resultIds.contains(id)) {
            if (duplicateIdToTestResult.containsKey(id)) {
                duplicateIdToTestResult.get(id).add(testMetadata);
            } else {
                List<TestMetadata> duplicates = new LinkedList<>();
                duplicates.add(testMetadata);
                duplicateIdToTestResult.put(id, duplicates);
            }
            duplicateTestResults.add(testMetadata);
        } else {
            results.add(testMetadata);
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
        return results.stream().filter(r -> r.getIsPassed()).collect(Collectors.toList()).size();
    }

    @Override
    public int failingTestTotal() {
        return results.stream().filter(r -> !r.getIsPassed()).collect(Collectors.toList()).size();
    }

    @Override
    public int passingPhraseTotal() {
        return results.stream().mapToInt(r -> r.getPassingPhraseTotal()).sum();
    }

    @Override
    public int totalPhrases() {
        return results.stream().mapToInt(r -> r.getTotalPhrases()).sum();
    }

    @Override
    public int totalFilteredDuplicateTests() {
        return duplicateTestResults.size();
    }

    @Override
    public Optional<List<TestMetadata>> duplicateTestSpecifications() {
        if (duplicateTestResults.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(duplicateTestResults);
        }
    }

    @Override
    public List<OutputStream> getDslReports() {
        return dslReports;
    }

    @Override
    public boolean containsFilteredTest(int postFilteredTestId) {
        return resultIds.contains(postFilteredTestId);
    }
}
