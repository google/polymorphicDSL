package com.pdsl.reports;

import com.pdsl.reports.proto.OperationalReportData;

import java.io.IOException;
import java.net.URL;

public interface OperationalReportGenerator {
        URL generateOperationalReport(OperationalReportData applicationToContextResults) throws IOException;
}
