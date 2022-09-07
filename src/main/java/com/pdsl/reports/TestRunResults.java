package com.pdsl.reports;

import com.google.common.base.Preconditions;
import com.pdsl.reports.proto.TechnicalReportData;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A report of the test results from an arbitrary test run.
 *
 * This could be for a single test case that is part of a larger suite or an entire test plan itself.
 */
public interface TestRunResults {

    int passingTestTotal();
    int failingTestTotal();
    int passingPhraseTotal();
    int totalPhrases();
    int totalFilteredDuplicateTests();

    class FailedTestResults extends RuntimeException {
        private Collection<TestResult> failedResults;
        public FailedTestResults(Collection<TestResult> failedResults) {
            super("At least one child test failed during execution!", failedResults.stream()
                    .map(TestResult::getFailureReason)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .findFirst().orElseThrow());
            Preconditions.checkArgument(!failedResults.stream().anyMatch(r -> r.getStatus().equals(TechnicalReportData.Status.PASSED)),
                    "A passing test was included with the test results");

        }

        @Override
        public void printStackTrace() {
            failedResults.stream()
                    .map(TestResult::getFailureReason)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .forEach(Throwable::printStackTrace);
        }

        @Override
        public StackTraceElement[] getStackTrace() {
            return (StackTraceElement[]) failedResults.stream()
                    .map(TestResult::getFailureReason)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .map(Throwable::getStackTrace)
                    .map(Arrays::asList)
                    .flatMap(List::stream)
                    .collect(Collectors.toList())
                    .toArray();
        }
    }
}
