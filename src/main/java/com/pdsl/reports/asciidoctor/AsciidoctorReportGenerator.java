package com.pdsl.reports.asciidoctor;

import com.google.common.base.Preconditions;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.reports.PdslReportData;
import com.pdsl.reports.TestResult;
import com.pdsl.reports.TraceableReportGenerator;
import org.asciidoctor.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Map;

public class AsciidoctorReportGenerator implements TraceableReportGenerator {

    private final Charset charset;
    private final AsciidoctorStrategicReportGenerator asciidoctorStrategicReportGenerator;
    private final Options options;
    private final Logger logger = LoggerFactory.getLogger(AsciidoctorReportGenerator.class);
    public AsciidoctorReportGenerator(Path fileLocation, String reportTitle) {
        Preconditions.checkNotNull(fileLocation);
        Preconditions.checkArgument(!Files.isDirectory(fileLocation));
        this.charset = Charset.defaultCharset();
        this.options = OptionsBuilder.options().attributes(AttributesBuilder.attributes()
                .docType("html")
                .tableOfContents(true)
                .tableOfContents(Placement.LEFT)
                .sourceHighlighter("rouge")
        ).get();
        this.asciidoctorStrategicReportGenerator = new AsciidoctorStrategicReportGenerator(fileLocation, reportTitle);
    }

    public AsciidoctorReportGenerator(Path fileLocation, String reportTitle, Options options) {
        Preconditions.checkNotNull(fileLocation);
        Preconditions.checkArgument(!Files.isDirectory(fileLocation));
        this.charset = Charset.defaultCharset();
        this.asciidoctorStrategicReportGenerator = new AsciidoctorStrategicReportGenerator(fileLocation, reportTitle);
        this.options = options;
    }


    @Override
    public URL generateReport(Map<String, Map<String, Collection<MetadataTestRunResults>>> testRunResults) throws IOException {
        PdslReportData pdslReportData = new PdslReportData();
        for (Map.Entry<String, Map<String, Collection<MetadataTestRunResults>>> applicationToContext : testRunResults.entrySet()) {
            for (Map.Entry<String, Collection<MetadataTestRunResults>> context : applicationToContext.getValue().entrySet()) {
                for (MetadataTestRunResults metadata : context.getValue()) {
                    for (TestResult testResult : metadata.getTestResults()) {
                        pdslReportData.addData(applicationToContext.getKey(),
                                testResult.getTestCase().getUnfilteredPhraseBody(), context.getKey(), testResult);
                    }
                }
            }
        }
        URL adocURL = asciidoctorStrategicReportGenerator.generateStrategicReport(pdslReportData.getReportData());
        File adocFile = Paths.get(adocURL.getPath()).toFile();
        Asciidoctor asciidoctor = Asciidoctor.Factory.create();
        try {
           asciidoctor.convertFile(adocFile, options);
        } finally {
            asciidoctor.shutdown();
        }
        String processedPath = options.map().get("destination_dir") != null ? (String)options.map().get("destination_dir")
                : adocURL.toString().replace(".adoc", ".html").replaceFirst("file:", "");
        logger.info(String.format("Wrote report to %s",processedPath));
        return Paths.get(processedPath).toUri().toURL();
    }
}
