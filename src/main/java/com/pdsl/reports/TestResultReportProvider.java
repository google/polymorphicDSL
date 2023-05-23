package com.pdsl.reports;

import java.util.Optional;

/**
 * A provider of test results.
 *
 * This is mostly used by configurable test runners to provide some mechanism of specifying how test reports should be
 * created.
 */
public interface TestResultReportProvider {

    /**
     * Returns the results of a test run.
     *
     * @return the results of a PDSL test run
     */
    Optional<TestRunResults> getResults();
}
