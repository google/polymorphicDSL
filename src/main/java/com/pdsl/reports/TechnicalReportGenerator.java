package com.pdsl.reports;

import com.pdsl.reports.proto.TechnicalReportData;

import java.io.IOException;
import java.net.URL;

/**
 * The lowest level report which contains stack traces and test results for a specific context in an application.
 */
public interface TechnicalReportGenerator {

    URL generateTechnicalReport(TechnicalReportData results) throws IOException;
}
