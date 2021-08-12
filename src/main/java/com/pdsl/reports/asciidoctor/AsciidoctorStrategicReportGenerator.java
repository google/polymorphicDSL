package com.pdsl.reports.asciidoctor;

import com.pdsl.reports.StrategicReportGenerator;
import com.pdsl.reports.proto.OperationalReportData;
import com.pdsl.reports.proto.StrategicReportData;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class AsciidoctorStrategicReportGenerator implements StrategicReportGenerator {

    private final Path fileResource;
    private final String documentTitle;
    private final AsciidoctorOperationalReportGenerator operationalReportGenerator;

    public AsciidoctorStrategicReportGenerator(Path fileResource, String documentTitle) {
        this.fileResource = fileResource;
        this.documentTitle = documentTitle;
        this.operationalReportGenerator = new AsciidoctorOperationalReportGenerator(fileResource);
    }

    @Override
    public URL generateStrategicReport(StrategicReportData strategicReportData) throws IOException {
        Path adoc = Files.createFile(fileResource);
        write(String.format("= %s%n%n", documentTitle));
        for (OperationalReportData operationalReportData : strategicReportData.getOperationalReportDataList()) {
            operationalReportGenerator.generateOperationalReport(operationalReportData);
        }
        return adoc.toUri().toURL();
    }

    private void write(String str) throws IOException {
        Files.writeString(fileResource, String.format(str), StandardOpenOption.APPEND);
    }
}
