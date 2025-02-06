package com.pdsl.component;

import com.google.common.base.Ascii;
import com.pdsl.reports.DefaultTestResult;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.reports.asciidoctor.AsciidoctorReportGenerator;
import com.pdsl.specifications.Phrase;
import com.pdsl.testcases.TestCase;
import java.io.File;

import java.nio.charset.StandardCharsets;
import org.antlr.v4.runtime.tree.ParseTree;
import org.asciidoctor.AttributesBuilder;
import org.asciidoctor.OptionsBuilder;
import org.asciidoctor.Placement;
import org.asciidoctor.SafeMode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

public class AsciiDoctorTraceableReport {

    private static final Logger logger = LoggerFactory.getLogger(AsciiDoctorTraceableReport.class);
    private static final OptionsBuilder OPTIONS = OptionsBuilder.options().attributes(AttributesBuilder.attributes()
            .docType("html")
            .tableOfContents(true)
            .tableOfContents(Placement.LEFT)
            .sourceHighlighter("rouge")
    ).safe(SafeMode.UNSAFE);

    private static enum Context {
        UNIT,
        COMPONENT,
        API,
        SYSTEM,
        UAT,
        SMOKE;
    }

    @Test
    public void traceableReportForSingleSuite_createsValidAsciidoctorOutput() throws IOException {

        // Get the temporary directory and print it.
        String tempDir = System.getProperty("java.io.tmpdir");
        Path path = Path.of(String.format("%s/%s.adoc", tempDir, UUID.randomUUID()));
        AsciidoctorReportGenerator asciidoctorReportGenerator = new AsciidoctorReportGenerator(path, "Traceable Report for Single Suite", OPTIONS.get());

        Map<String, Map<String, Collection<MetadataTestRunResults>>> reportData = new HashMap<>();
        reportData.put("PDSL Framework", Map.of(
                Context.UNIT.toString(), createPassingMetadata(15, "Mock Test", Context.UNIT.toString(), PhraseBodyType.ALPHA),
                Context.COMPONENT.toString(), createPassingMetadata(10, "Mock Test", Context.COMPONENT.toString(), PhraseBodyType.ALPHA),
                Context.API.toString(), createPassingMetadata(8, "Mock Test", Context.API.toString(), PhraseBodyType.ALPHA),
                Context.SYSTEM.toString(), createPassingMetadata(5, "Mock Test", Context.SYSTEM.toString(), PhraseBodyType.ALPHA),
                Context.UAT.toString(), createPassingMetadata(3, "Mock Test", Context.UAT.toString(), PhraseBodyType.ALPHA),
                Context.SMOKE.toString(), createPassingMetadata(1, "Mock Test", Context.SMOKE.toString(), PhraseBodyType.ALPHA)
        ));

        // ACT
        URL adoc = asciidoctorReportGenerator.generateReport(reportData);
        assertThat(adoc).isNotNull();
        Path adocHtmlPage = Paths.get(adoc.getPath().toString().replace(".adoc", ".html"));
        logger.info(String.format("Path is %s", path));

        // ASSERT
        // Parse the HTML
        File input = new File(adocHtmlPage.toString());
        Document page = Jsoup.parse(input, StandardCharsets.UTF_8.toString());

        // Mock Test
        Map<String, TableResults> mockTestResults = getResultsForEachContext("Mock Test", page);
        assertThat(mockTestResults.get(Context.UNIT.toString()).passing).isEqualTo(15);
        assertThat(mockTestResults.get(Context.COMPONENT.toString()).passing).isEqualTo(10);
        assertThat(mockTestResults.get(Context.API.toString()).passing).isEqualTo(8);
        assertThat(mockTestResults.get(Context.SYSTEM.toString()).passing).isEqualTo(5);
        assertThat(mockTestResults.get(Context.UAT.toString()).passing).isEqualTo(3);
        assertThat(mockTestResults.get(Context.SMOKE.toString()).passing).isEqualTo(1);
    }


    @Test
    public void traceableReportForMultipleSuites_createsValidAsciidoctorOutput() throws IOException {

        // Arrange
        // Get the temporary directory and print it.
        String tempDir = System.getProperty("java.io.tmpdir");
        Path path = Path.of(String.format("%s/%s.adoc", tempDir, UUID.randomUUID()));
        AsciidoctorReportGenerator asciidoctorReportGenerator = new AsciidoctorReportGenerator(path, "Multiple Traceable Report", OPTIONS.get());
        List<MetadataTestRunResults> testRunResults = new ArrayList<>();
        Map<String, Map<String, Collection<MetadataTestRunResults>>> reportData = new HashMap<>();

        //UNIT
        List<MetadataTestRunResults> unit = new ArrayList<>();
        unit.addAll(createPassingMetadata(15, "Mock Test", Context.UNIT.toString(), PhraseBodyType.ALPHA));
        unit.addAll(createPassingMetadata(14, "Mock Test 2", Context.UNIT.toString(), PhraseBodyType.BETA));

        //COMPONENT
        List<MetadataTestRunResults> component = new ArrayList<>();
        component.addAll(createPassingMetadata(10, "Mock Test", Context.COMPONENT.toString(), PhraseBodyType.ALPHA));
        component.addAll(createPassingMetadata(9, "Mock Test 2", Context.COMPONENT.toString(), PhraseBodyType.BETA));

        //COMPONENT
        List<MetadataTestRunResults> api = new ArrayList<>();
        api.addAll(createPassingMetadata(10, "Mock Test", Context.API.toString(), PhraseBodyType.ALPHA));
        api.addAll(createPassingMetadata(9, "API Only", Context.API.toString(), PhraseBodyType.GAMMA));
        reportData.put("PDSL Framework", Map.of(
                Context.UNIT.toString(), unit,
                Context.COMPONENT.toString(), component,
                Context.API.toString(), api
        ));

        // ACT
        URL adoc = asciidoctorReportGenerator.generateReport(reportData);
        assertThat(adoc).isNotNull();
        Path adocHtmlPage = Paths.get(adoc.getPath().toString().replace(".adoc", ".html"));
        logger.info(String.format("Path is %s", path));

        // ASSERT
        // Parse the HTML
        File input = new File(adocHtmlPage.toString());
        Document page = Jsoup.parse(input, StandardCharsets.UTF_8.toString());

        // Mock Test
        Map<String, TableResults> mockTestResults = getResultsForEachContext("Mock Test", page);
        assertThat(mockTestResults.get(Context.UNIT.toString()).passing).isEqualTo(15);
        assertThat(mockTestResults.get(Context.COMPONENT.toString()).passing).isEqualTo(10);
        assertThat(mockTestResults.get(Context.UNIT.toString()).failing).isEqualTo(0);
        assertThat(mockTestResults.get(Context.COMPONENT.toString()).failing).isEqualTo(0);

        // Mock Test 2
        Map<String, TableResults> mockTests2Results = getResultsForEachContext("Mock Test 2", page);
        assertThat(mockTests2Results.get(Context.UNIT.toString()).passing).isEqualTo(14);
        assertThat(mockTests2Results.get(Context.COMPONENT.toString()).passing).isEqualTo(9);
        assertThat(mockTests2Results.get(Context.UNIT.toString()).failing).isEqualTo(0);
        assertThat(mockTests2Results.get(Context.COMPONENT.toString()).failing).isEqualTo(0);

        // API  Only
        Map<String, TableResults> apiResults = getResultsForEachContext("API Only", page);
        assertThat(apiResults.get(Context.API.toString()).passing).isEqualTo(9);
        assertThat(apiResults.get(Context.API.toString()).failing).isEqualTo(0);
    }

    @Test
    public void failingPhrases_createsValidAsciidoctorOutput() throws IOException {

        // ARRANGE
        String tempDir = System.getProperty("java.io.tmpdir");
        Path path = Path.of(String.format("%s/%s.adoc", tempDir, UUID.randomUUID()));
        AsciidoctorReportGenerator asciidoctorReportGenerator = new AsciidoctorReportGenerator(path, "Failing Phrases", OPTIONS.get());

        // Unit
        List<MetadataTestRunResults> unit = new ArrayList<>();
        unit.addAll(createPassingMetadata(12, "Mock Test", Context.UNIT.toString(), PhraseBodyType.ALPHA));
        unit.addAll(createPassingMetadata(15, "Mock Test 2", Context.UNIT.toString(), PhraseBodyType.BETA));
        unit.addAll(createFailingMetadata(3, "Mock Test", Context.UNIT.toString(), PhraseBodyType.ALPHA));

        // Component
        List<MetadataTestRunResults> component = new ArrayList<>();
        component.addAll(createPassingMetadata(10, "Mock Test", Context.COMPONENT.toString(), PhraseBodyType.ALPHA));
        component.addAll(createPassingMetadata(10, "Mock Test 2", Context.COMPONENT.toString(), PhraseBodyType.BETA));
        component.addAll(createFailingMetadata(3, "Failing Test", Context.COMPONENT.toString(), PhraseBodyType.GAMMA));

        URL adoc = asciidoctorReportGenerator.generateReport(Map.of("Failing Phrases",
                Map.of(Context.UNIT.toString(), unit,
                        Context.COMPONENT.toString(), component)));

        assertThat(adoc).isNotNull();

        // ACT
        // Convert into an HTML file;
        Path adocHtmlPage = Paths.get(adoc.getPath().toString().replace(".adoc", ".html"));
        logger.info(String.format("Path is %s", path));
        logger.info(String.format("Processed document is %s", adoc.getPath()));

        // ASSERT
        // Parse the HTML
        File input = new File(adocHtmlPage.toString());
        Document page = Jsoup.parse(input, StandardCharsets.UTF_8.toString());

        // Mock Test
        Map<String, TableResults> mockTestResults = getResultsForEachContext("Mock Test", page);
        assertThat(mockTestResults.get(Context.UNIT.toString()).passing).isEqualTo(12);
        assertThat(mockTestResults.get(Context.COMPONENT.toString()).passing).isEqualTo(10);
        assertThat(mockTestResults.get(Context.UNIT.toString()).failing).isEqualTo(3);
        assertThat(mockTestResults.get(Context.COMPONENT.toString()).failing).isEqualTo(0);

        // Mock Test 2
        Map<String, TableResults> mock2TestResults = getResultsForEachContext("Mock Test 2", page);
        assertThat(mock2TestResults.get(Context.UNIT.toString()).passing).isEqualTo(15);
        assertThat(mock2TestResults.get(Context.COMPONENT.toString()).passing).isEqualTo(10);
        assertThat(mock2TestResults.get(Context.UNIT.toString()).failing).isEqualTo(0);
        assertThat(mock2TestResults.get(Context.COMPONENT.toString()).failing).isEqualTo(0);

        // Failing Test
        Map<String, TableResults> failingTestResults = getResultsForEachContext("Failing Test", page);
        assertThat(failingTestResults.get(Context.COMPONENT.toString()).failing).isEqualTo(3);
        assertThat(failingTestResults.get(Context.COMPONENT.toString()).passing).isEqualTo(0);
    }

    @Test
    public void pipesInPhrase_doNotBreakTables() throws IOException {

        // Get the temporary directory and print it.
        String tempDir = System.getProperty("java.io.tmpdir");
        Path path = Path.of(String.format("%s/%s.adoc", tempDir, UUID.randomUUID()));
        AsciidoctorReportGenerator asciidoctorReportGenerator = new AsciidoctorReportGenerator(path, "Traceable Report for Single Suite", OPTIONS.get());

        Map<String, Map<String, Collection<MetadataTestRunResults>>> reportData = new HashMap<>();
        reportData.put("Pipe | Test Suite", Map.of(
                Context.UNIT.toString(), createPassingMetadata(15, "Pipe | Test", Context.UNIT.toString(), PhraseBodyType.PIPES)
        ));

        // ACT
        URL adoc = asciidoctorReportGenerator.generateReport(reportData);
        assertThat(adoc).isNotNull();
        Path adocHtmlPage = Paths.get(adoc.getPath().toString().replace(".adoc", ".html"));
        logger.info(String.format("Path is %s", path));

        // ASSERT
        // Parse the HTML
        File input = new File(adocHtmlPage.toString());
        Document page = Jsoup.parse(input, StandardCharsets.UTF_8.toString());

        // Mock Test
        Map<String, TableResults> mockTestResults = getResultsForEachContext("Pipe Test Suite", page);
        assertThat(mockTestResults.get(Context.UNIT.toString()).passing).isEqualTo(15);
    }

    private enum PhraseBodyType {
        ALPHA(List.of("Step1\n", "Step2\n", "Step3"), List.of("Step2\n")),
        BETA(List.of("Beta1\n", "Beta2\n", "Beta3"), List.of("Beta1\n")),
        GAMMA(List.of("Gamma1\n", "Gamma2\n", "Gamma3"), List.of("Gamma1\n", "Gamma3")),
        PIPES(List.of("Pipe Step | 1", "Pipe Step | 2", "Pipe Step | 3"), List.of("Pipe Step | 1", "Pipe Step | 2"));

        private final List<String> unfiltered;
        private final List<String> filtered;

        private PhraseBodyType(List<String> unfiltered, List<String> filtered) {
            this.unfiltered = unfiltered;
            this.filtered = filtered;
        }

        public List<String> getUnfiltered() {
            return unfiltered;
        }

        public List<String> getFiltered() {
            return filtered;
        }
    }

    private Collection<MetadataTestRunResults> createPassingMetadata(int amount, String testSuiteId, String context, PhraseBodyType phraseBody) {
        List<MetadataTestRunResults> pdslResults = new ArrayList<>(amount);
        final TestCase testCase = Mockito.mock(TestCase.class);
        when(testCase.testTitle()).thenReturn(testSuiteId);
        when(testCase.unfilteredPhraseBody()).thenReturn(phraseBody.unfiltered);
        when(testCase.contextFilteredPhraseBody()).thenReturn(phraseBody.filtered);

        for (int i = 0; i < amount; i++) {
            PolymorphicDslTestRunResults pdslresult = new PolymorphicDslTestRunResults(List.of(System.out), context);
            pdslresult.addTestResult(DefaultTestResult.passingTest(testCase));
            pdslResults.add(pdslresult);
        }
        return pdslResults;
    }


    private Collection<MetadataTestRunResults> createFailingMetadata(int amount, String testSuiteId, String context, PhraseBodyType phraseBody) {
        List<MetadataTestRunResults> pdslResults = new ArrayList<>(amount);

        Phrase phrase = Mockito.mock(Phrase.class);
        ParseTree parseTree = Mockito.mock(ParseTree.class);
        when(parseTree.getText()).thenReturn("A failing phrase");
        when(phrase.getPrefilteredIndex()).thenReturn(Accumulator.next());
        when(phrase.getParseTree()).thenReturn(parseTree);

        final TestCase testCase = Mockito.mock(TestCase.class);
        when(testCase.testTitle()).thenReturn(testSuiteId);
        String filteredStep = String.format("Step%d\n", Accumulator.next());
        when(testCase.unfilteredPhraseBody()).thenReturn(phraseBody.unfiltered);
        when(testCase.contextFilteredPhraseBody()).thenReturn(phraseBody.filtered);
        when(testCase.testTitle()).thenReturn(testSuiteId);

        for (int i = 0; i < amount; i++) {
            PolymorphicDslTestRunResults pdslresult = new PolymorphicDslTestRunResults(List.of(System.out), context);
            pdslresult.addTestResult(DefaultTestResult.failedTest(testCase, phrase, new IllegalArgumentException("Some exception"), Accumulator.next(), 1));
            pdslResults.add(pdslresult);
        }
        return pdslResults;
    }

    private static class Accumulator {
        private static int index = 0;

        public static int next() {
            return ++index;
        }
    }

    private static class TableResults {
        private int passing;
        private int failing;

        public TableResults() {
            passing = 0;
            failing = 0;
        }
    }


    private Map<String, TableResults> getResultsForEachContext(String testResource, Document page) {
        // Find all the tables of interest
        Element tableHeader = page.getElementById(String.format("_%s", Ascii.toLowerCase(testResource)
            .replace(' ', '_')
            .replaceAll(" ", "")));

        // Find the context table
        Element root = tableHeader.parent().select("h4[id^=_context_report]").first().parent().select("table").first();

        // Check which resource the table represents
        Elements testResults = root.getElementsByTag("tr");

        Elements headerList = testResults.first().getElementsByTag("th");
        List<String> contextList = headerList.subList(1, headerList.size())
            .stream()
            .map(Element::text)
            .collect(Collectors.toUnmodifiableList());

        Map<String, TableResults> resultsMap = new HashMap<>();
        // Grab the table headers to determine which column corresponds to each context
            for (String context : contextList) {
                resultsMap.put(context, new TableResults());
            }

        // Get the results row
            final int TOTAL_PASSED_ROW_INDEX = 3;
            final int TOTAL_FAILED_ROW_INDEX = 4;

            Elements passedCells = testResults.get(TOTAL_PASSED_ROW_INDEX).getElementsByTag("td");
            Elements failedCells = testResults.get(TOTAL_FAILED_ROW_INDEX).getElementsByTag("td");

            for (int j = 0; j < passedCells.size(); j++) {
                resultsMap.get(contextList.get(j)).passing += Integer.parseInt(passedCells.get(j).text());
                resultsMap.get(contextList.get(j)).failing += Integer.parseInt(failedCells.get(j).text());
            }

        // Remove context that won't have cells in the table anymore
        return resultsMap;
    }
}
