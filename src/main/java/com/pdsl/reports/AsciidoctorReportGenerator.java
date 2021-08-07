package com.pdsl.reports;

import com.google.common.base.Preconditions;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

public class AsciidoctorReportGenerator implements TraceableReportGenerator {

    private final Path fileLocation;
    private final Charset charset;
    public AsciidoctorReportGenerator(Path fileLocation) {
        Preconditions.checkNotNull(fileLocation);
        Preconditions.checkArgument(!Files.isDirectory(fileLocation));
        this.fileLocation = fileLocation;
        this.charset = Charset.defaultCharset();
    }
    @Override
    public URL generateReport(Collection<MetadataTestRunResults> testRunResults) throws IOException {

        // Resource, Map<Context, List<Metadata>>
        Map<String, Map<String, List<DefaultTestResult>>> resourceToRuns =
                TraceableReportGenerator.testResourceToContextHelper(testRunResults);
        Path adoc = Files.createFile(fileLocation);
        write(":toc: left%n:icons: font%n:source-highlighter: prettify%n");
        write("= Traceable Report%n%n");
        // For each test resource
        for (Map.Entry<String, Map<String, List<DefaultTestResult>>> resourceEntry : resourceToRuns.entrySet()) {
            boolean previousCellFailed = false;
            // Create a header
            write(String.format("== %s%n%n",resourceEntry.getKey()));
            // Find the longest column to calculate colspan
            final int longestColumn = resourceEntry.getValue().entrySet().stream()
                    .mapToInt(e -> e.getValue().size())
                    .max()
                    .orElseThrow();
            int contextLength = resourceEntry.getValue().keySet().size();
            String[][] tableData = new String[contextLength + 1][longestColumn + 1];
            // Fill out table data
            tableData[0][0] = String.format(".%d+| Test Case/Context %s", longestColumn + 1,  getCellMarkup(previousCellFailed));
            int column = 0;

            // Sort the context by the number of tests executed
            List<Map.Entry<String, List<DefaultTestResult>>> sortedContexts = resourceEntry.getValue().entrySet().stream()
                    .collect(Collectors.toList());
            sortedContexts.sort(new Comparator<Map.Entry<String, List<DefaultTestResult>>>() {
                @Override
                public int compare(Map.Entry<String, List<DefaultTestResult>> stringListEntry, Map.Entry<String, List<DefaultTestResult>> t1) {
                    if (stringListEntry.getValue().size() < t1.getValue().size()) {
                        return -1;
                    } else if (stringListEntry.getValue().size() == t1.getValue().size()) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            });
            // For each test context
            for (Map.Entry<String, List<DefaultTestResult>> contextToTest : sortedContexts) {

                int row = 0;
                column++;
                tableData[column][row] = String.format("| %s", contextToTest.getKey());
                List<DefaultTestResult> defaultTestResultList = contextToTest.getValue();
                for (int r = 0; r < defaultTestResultList.size(); r++) {
                    row++;
                    DefaultTestResult metadata = defaultTestResultList.get(r);
                    int colspan = longestColumn - defaultTestResultList.size() + 1;
                    StringBuilder cellText = new StringBuilder();

                    //If we need a colspan because there will be many empty cells beneath this one
                    if (defaultTestResultList.size() < longestColumn && r == defaultTestResultList.size() - 1) { // A colspan is needed
                        cellText.append(String.format(".%d+", colspan));
                    }
                    cellText.append("| ");
                    cellText.append(metadata.getStatus().equals(DefaultTestResult.Status.PASSED) ? "✅ " : String.format("[%d] %s ",
                            metadata.getFailingPhrase().get().getPrefilteredIndex(),
                            metadata.getFailingPhrase().get().getParseTree().getText()));

                    // Conditionally change cell coloring and markup
                    if (previousCellFailed != !metadata.getStatus().equals(DefaultTestResult.Status.PASSED)) {
                        previousCellFailed = !previousCellFailed;
                        cellText.append(getCellMarkup(previousCellFailed));
                    }
                    tableData[column][row] = cellText.toString();
                }
                previousCellFailed = false;
            }

            // Convert the table to an asciidoctor table with appropriate formatting
            StringBuilder adocTable = new StringBuilder(String.format(".Traceable Report%n"));
            StringBuilder columnFormatString = new StringBuilder();
            for (int b=0; b < contextLength; b++) {
                columnFormatString.append(",^");
            }
            adocTable.append(String.format("[cols=\"^%s\"]%n|===", columnFormatString)); // Force the number of columns
            // Fill out table body
            for (int r = 0; r < longestColumn + 1; r++) {
                adocTable.append(String.format("%n"));
                for (int c = 0; c < contextLength + 1; c++) {
                    String cellValue = tableData[c][r];
                    if (cellValue != null) {
                        adocTable.append(cellValue);
                    }
                }
            }
            adocTable.append(String.format("%n|===%n"));
            write(adocTable.toString());
        }
        return adoc.toUri().toURL();
    }

    // Trigger the color of the cell on failure, otherwise clear
    private String getCellMarkup(boolean isFailed) {
        return isFailed ? "{set:cellbgcolor:red}" : "{set:cellbgcolor!}";
    }

    private void write(String str) throws IOException {
        Files.writeString(fileLocation, String.format(str), StandardOpenOption.APPEND);
    }
}
