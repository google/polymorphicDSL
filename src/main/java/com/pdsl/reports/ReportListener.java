package com.pdsl.reports;

import java.io.OutputStream;
import java.util.List;

public interface ReportListener {

    void addTestResult(DefaultTestResult defaultTestResult);

    void appendToDslReport(byte[] b);

    public List<OutputStream> getDslReports();

    /**
     * Checks to see if the test ID has already been recorded in the report
     *
     * @param postFilteredTestId Identifier of the TestSpecification after filtering out phrases
     * @return true if report contains this test or false if not
     */
    boolean containsFilteredTest(long postFilteredTestId);
}
