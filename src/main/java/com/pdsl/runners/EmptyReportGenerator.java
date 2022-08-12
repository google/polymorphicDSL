package com.pdsl.runners;

import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.reports.TraceableReportGenerator;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Map;

class EmptyReportGenerator implements TraceableReportGenerator {
    @Override
    public URL generateReport(Map<String, Map<String, Collection<MetadataTestRunResults>>> testRunResults) throws IOException {
        return null;
    }
}
