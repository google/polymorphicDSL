package com.pdsl.reports;

import com.pdsl.reports.proto.StrategicReportData;

import java.io.IOException;
import java.net.URL;

public interface StrategicReportGenerator {
    URL generateStrategicReport(StrategicReportData strategicReportData) throws IOException;
}
