package com.pdsl.reports;

import java.util.Optional;

/**
 * A provider of test results.
 *
 * This is mostly used by configurable test runners to provide some mechanism of specifying how test reports should be
 * created.
 */
public interface TestResultReportProvider {

    Optional<TestRunResults> getResults();
}
