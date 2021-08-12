package com.pdsl.reports;

import com.pdsl.reports.proto.TacticalReportData;

import java.io.IOException;
import java.net.URL;

/**
 *
 */
public interface TacticalReportGenerator {

    URL generateTacticalReport(TacticalReportData contextToTestResults) throws IOException;
}
