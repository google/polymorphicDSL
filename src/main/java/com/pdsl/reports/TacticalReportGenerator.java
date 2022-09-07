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

    URL generateTacticalReport(TacticalReportData contextToTestResults) throws IOException;
}
