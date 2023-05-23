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

    /**
     * Creats a report with high level metrics from the provided data.
     *
     * Metrics produced by this reflect total quality when systems are combined.
     *
     * @param strategicReportData the data from which to generate the report
     * @return a URL location of the generated report
     * @throws IOException if there is an issue writing the report
     */
    URL generateStrategicReport(StrategicReportData strategicReportData) throws IOException;
}
