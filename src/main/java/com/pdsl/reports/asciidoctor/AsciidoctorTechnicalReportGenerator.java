package com.pdsl.reports.asciidoctor;

import com.pdsl.reports.TechnicalReportGenerator;
import com.pdsl.reports.proto.TechnicalReportData;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * A report generator that focuses on test or codepoint specific metrics.
 *
 * These reports are suitable for engineers that are analyzing or
 * troubleshooting individual test cases.
 */
public class AsciidoctorTechnicalReportGenerator implements TechnicalReportGenerator {

    private final Path fileResource;

    /**
     * A report generator that can produce granular metrics at the provided location.
     * @param fileResource the location to generate reports at
     */
    public AsciidoctorTechnicalReportGenerator(Path fileResource) {
        this.fileResource = fileResource;
    }

    @Override
    public URL generateTechnicalReport(TechnicalReportData results) throws IOException {
        if (results.getStatus().equals(TechnicalReportData.Status.FAILED)) {
            write(String.format("====== _%s_%n%n", results.getTestCaseTitle()));
            write(String.format("_Failed Step_:  \"*%s*%n%n", results.getFailingPhrase()));
            write(String.format("_Failure Reason_%n%n", results.getTestCaseTitle()));
            write(String.format("```%n%s%n```%n%n", results.getFailureReason()));
        }
        return fileResource.toUri().toURL();
    }

    private void write(String str) throws IOException {
        Files.writeString(fileResource, String.format(str), StandardOpenOption.APPEND);
    }
}
