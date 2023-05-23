package com.pdsl.reports;

import com.pdsl.reports.proto.TechnicalReportData;

import java.io.IOException;
import java.net.URL;

/**
 * The lowest level report which contains stack traces and test results for a specific context in an application.
 */
public interface TechnicalReportGenerator {

    /**
     * Creates a report from the provided test data.
     *
     * @param results the data to make the report from
     * @return the URL of the generated report
     * @throws IOException  if there is an issue writing the report
     */
    URL generateTechnicalReport(TechnicalReportData results) throws IOException;
}
