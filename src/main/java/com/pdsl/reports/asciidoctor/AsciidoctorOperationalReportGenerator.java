package com.pdsl.reports.asciidoctor;

import com.pdsl.reports.OperationalReportGenerator;
import com.pdsl.reports.proto.OperationalReportData;
import com.pdsl.reports.proto.TacticalReportData;
import com.pdsl.reports.proto.TechnicalReportData;
import com.pdsl.reports.proto.TestCaseGroup;
import com.pdsl.testcases.TestCase;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.Map;

public class AsciidoctorOperationalReportGenerator implements OperationalReportGenerator {

    private final Path fileResource;
    private final AsciidoctorTacticalReportGenerator asciidoctorTacticalReportGenerator;
    public AsciidoctorOperationalReportGenerator(Path fileResource) {
        this.fileResource = fileResource;
        this.asciidoctorTacticalReportGenerator = new AsciidoctorTacticalReportGenerator(fileResource);
    }

    @Override
    public URL generateOperationalReport(OperationalReportData operationalReportData) throws IOException {
        // Create application title
        write(String.format("== %s%n%n", operationalReportData.getApplication()));
        // Create application overview and table
        write(String.format("=== Overview%n%n"));
        write(String.format(".All %s Test Results%n", operationalReportData.getApplication()));
        write("[%%autowidth, cols=\"^,^,^\" options=header]%n");
        write(String.format("|===%n"));
        write(String.format("| Total | Passed | Failed %n"));
        long passed = operationalReportData.getTacticalReportDataList().stream()
                .flatMap(tactical -> tactical.getContextToTestCasesMap().entrySet().stream()
                        .map(Map.Entry::getValue))
                .map(TestCaseGroup::getTechnicalReportDataList)
                .flatMap(Collection::stream)
                .filter(technicalReportData -> technicalReportData.getStatus().equals(TechnicalReportData.Status.PASSED))
                .count();
        long failed = operationalReportData.getTacticalReportDataList().stream()
                .flatMap(tactical -> tactical.getContextToTestCasesMap().entrySet().stream()
                        .map(Map.Entry::getValue))
                .map(TestCaseGroup::getTechnicalReportDataList)
                .flatMap(Collection::stream)
                .filter(technicalReportData -> technicalReportData.getStatus().equals(TechnicalReportData.Status.FAILED))
                .count();
        write(String.format("| %d | %d | %d %n", passed + failed, passed, failed));
        write(String.format("|===%n%n"));

        for (TacticalReportData tacticalReportData : operationalReportData.getTacticalReportDataList()) {
            asciidoctorTacticalReportGenerator.generateTacticalReport(tacticalReportData);
        }
        return fileResource.toUri().toURL();
    }

    private void write(String str) throws IOException {
        Files.writeString(fileResource, String.format(str), StandardOpenOption.APPEND);
    }

}
