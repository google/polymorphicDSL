package com.pdsl.reports;

import java.io.OutputStream;
import java.util.List;

/**
 * A listener that is used to process a test result into a presentable format when generating reports.
 */
public interface ReportListener {

    void addTestResult(DefaultTestResult defaultTestResult);

    void appendToDslReport(byte[] b);

    List<OutputStream> getDslReports();

    /**
     * Checks to see if the test ID has already been recorded in the report
     *
     * @param postFilteredTestId Identifier of the TestSpecification after filtering out phrases
     * @return true if report contains this test or false if not
     */
    boolean containsFilteredTest(List<String> filteredBody);
}
