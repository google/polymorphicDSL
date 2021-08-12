package com.pdsl.reports.asciidoctor;

import com.pdsl.reports.TacticalReportGenerator;
import com.pdsl.reports.proto.TacticalReportData;
import com.pdsl.reports.proto.TechnicalReportData;
import com.pdsl.reports.proto.TestCaseGroup;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AsciidoctorTacticalReportGenerator implements TacticalReportGenerator {

    private final Path fileResource;
    private final AsciidoctorTechnicalReportGenerator asciidoctorTechnicalReportGenerator;
    private static final String TABLE_START = " | %d ";
    public AsciidoctorTacticalReportGenerator(Path fileResource) {
        this.fileResource = fileResource;
        this.asciidoctorTechnicalReportGenerator = new AsciidoctorTechnicalReportGenerator(fileResource);
    }

    private void write(String str) throws IOException {
        Files.writeString(fileResource, String.format(str), StandardOpenOption.APPEND);
    }

    @Override
    public URL generateTacticalReport(TacticalReportData tacticalReportData) throws IOException {
        // Get all names used to describe this test
        List<String> names = tacticalReportData.getContextToTestCasesMap().entrySet().stream()
                .flatMap(entry -> entry.getValue().getTechnicalReportDataList().stream()
                        .map(TechnicalReportData::getTestCaseTitle))
                .distinct()
                .collect(Collectors.toUnmodifiableList());
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            if (i == 0) {
                write(String.format("=== %s %n%n", name));
            } else if (i == 1) {
                write(String.format("*Other Names:*%n"));
                write(String.format("- %s%n", name));
            } else {
                write(String.format("- %s%n", name));
            }
        }

        // Create phrase body
        write(String.format("%n==== Phrase Body%n%n"));
        write(String.format(".Context Ratios for %s%n", names.get(0)));
        write("[%%autowidth, options=header, cols=\"^,^\"]%n");
        write("|===%n");
        write("| Index | Phrase %n");
        for (int i = 0; i < tacticalReportData.getUnfilteredPhraseBodyList().size(); i++) {
            write(String.format("| %d | %s %n", i,
                    // Escape pipes
                    // If it starts with a pipe or it is a pipe NOT preceeded by a backslash, escape
                    tacticalReportData.getUnfilteredPhraseBodyList().get(i)
                            .replaceAll("([^\\\\])\\|", "$1\\\\|")));
        }
        write("|===%n%n");

        // Create the Context Report
        write(String.format("==== Context Report%n%n"));
        write(String.format(".Context Ratios for %s%n", names.get(0)));
        // Specific column formatting
        List<String> testContexts = tacticalReportData.getContextToTestCasesMap().entrySet().stream().map(Map.Entry::getKey)
                .collect(Collectors.toUnmodifiableList());
        StringBuilder contextHeader = new StringBuilder("[options=header, cols=\"h");
        testContexts.forEach(c -> contextHeader.append(",^"));
        contextHeader.append("\"]%n");
        write(contextHeader.toString());
        write("|===%n");
        write("| Context Ratio ");
        for (String context : testContexts) {
            write(String.format("| %s ", context));
        }
        write(String.format("%n"));
        write("| Total ");
        double largestSuite = 0;
        for (String context : testContexts) {
            int suiteSize = tacticalReportData.getContextToTestCasesMap().get(context).getTechnicalReportDataList().size();
            if (suiteSize > largestSuite) {
                largestSuite = suiteSize;
            }
            write(String.format(TABLE_START, suiteSize));
        }
        write(String.format("%n"));
        write("| %% of Largest ");
        for (String context : testContexts) {
            double percentage = tacticalReportData.getContextToTestCasesMap().get(context).getTechnicalReportDataList().size() / largestSuite;
            write(String.format(" | %.1f", percentage * 100));
            write("%% "); // Add % symbol at the end
        }
        write(String.format("%n"));
        write("| Total Passed ");
        for (String context : testContexts) {
            long contextPassed = tacticalReportData.getContextToTestCasesMap().get(context).getTechnicalReportDataList().stream()
                    .filter(technical -> technical.getStatus().equals(TechnicalReportData.Status.PASSED))
                    .count();
            write(String.format(TABLE_START, contextPassed));
        }
        write(String.format("%n"));
        write("| Total Failed ");
        long contextFailed = 0;
        for (String context : testContexts) {
            contextFailed = tacticalReportData.getContextToTestCasesMap().get(context).getTechnicalReportDataList().stream()
                    .filter(technical -> technical.getStatus().equals(TechnicalReportData.Status.FAILED))
                    .count();
            write(String.format(TABLE_START, contextFailed));
        }
        write(String.format("%n|===%n%n"));

        // show failures
        if (contextFailed > 0) {
            write(String.format("==== Failures%n%n"));
            write(String.format("Failures for %s%n%n", names.get(0)));
            for (Map.Entry<String, TestCaseGroup> entry : tacticalReportData.getContextToTestCasesMap().entrySet()) {
                write(String.format("===== %s%n%n", entry.getKey()));
                for (TechnicalReportData technicalReportData : entry.getValue().getTechnicalReportDataList()) {
                    if (technicalReportData.getStatus().equals(TechnicalReportData.Status.FAILED)) {
                        asciidoctorTechnicalReportGenerator.generateTechnicalReport(technicalReportData);
                    }
                }
            }
        }
        return fileResource.toUri().toURL();
    }
}
