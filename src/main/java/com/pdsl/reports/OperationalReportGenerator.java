package com.pdsl.reports;

import com.pdsl.reports.proto.OperationalReportData;

import java.io.IOException;
import java.net.URL;

/**
 * A report creator that compares test runs across the application/platform boundary.
 *
 * Because PDSL uses the same test specifications for multiple applications it is useful to see if a test passed in
 * both one application and the other.
 */
public interface OperationalReportGenerator {
        URL generateOperationalReport(OperationalReportData applicationToContextResults) throws IOException;
}
