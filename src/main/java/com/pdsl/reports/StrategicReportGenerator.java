package com.pdsl.reports;

import com.pdsl.reports.proto.StrategicReportData;

import java.io.IOException;
import java.net.URL;

/**
 * A report creator that provides the overall view of every test run for a business.
 *
 * This includes the coarsest granularity an enterprise is likely to care about, describing applications at the highest
 * level.
 */
public interface StrategicReportGenerator {
    URL generateStrategicReport(StrategicReportData strategicReportData) throws IOException;
}
