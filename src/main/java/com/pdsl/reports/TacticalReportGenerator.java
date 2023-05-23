package com.pdsl.reports;

import com.pdsl.reports.proto.TacticalReportData;

import java.io.IOException;
import java.net.URL;

/**
 * Creates granular reports that include test results for one specific application/platform.
 *
 * This provides information such as how many tests passed/failed for the application without specifying the details
 * as to why a particular test failed.
 */
public interface TacticalReportGenerator {

    /**
     * Creates report with homogenous metrics suitable for a specific team or application.
     *
     * @param contextToTestResults the data to generate a report with
     * @return the URL of the generated report
     * @throws IOException  if there is an issue writing the report
     */
    URL generateTacticalReport(TacticalReportData contextToTestResults) throws IOException;
}
