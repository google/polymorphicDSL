package com.pdsl.component;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.pdsl.logging.PdslThreadSafeOutputStream;
import com.pdsl.reports.AsciidoctorReportGenerator;
import com.pdsl.reports.DefaultTestResult;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.specifications.Phrase;
import com.pdsl.testcases.TestCase;
import org.antlr.v4.runtime.tree.ParseTree;
import org.asciidoctor.Asciidoctor;
import org.asciidoctor.OptionsBuilder;
import org.asciidoctor.SafeMode;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

public class AsciiDoctorTraceableReport {

    private static final Asciidoctor asciidoctor = Asciidoctor.Factory.create();
    private static final Logger logger = LoggerFactory.getLogger(AsciiDoctorTraceableReport.class);
    private WebClient webClient;

    @Before
    public void init() throws Exception {
        webClient = new WebClient();
    }

    @After
    public void close() throws Exception {
        webClient.close();
    }

    @AfterClass
    public static void afterAll() {
        asciidoctor.shutdown();
    }

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
        AsciidoctorReportGenerator asciidoctorReportGenerator = new AsciidoctorReportGenerator(path);
        List<MetadataTestRunResults> testRunResults = new ArrayList<>();
        // Unit
        PolymorphicDslTestRunResults unitPdslResults = new PolymorphicDslTestRunResults(List.of(new PdslThreadSafeOutputStream()), Context.UNIT.name());
        createPassingMetadata(15, "Mock Test").stream().forEach(unitPdslResults::addTestResult);

        // Component
        PolymorphicDslTestRunResults componentPdslResults = new PolymorphicDslTestRunResults(List.of(System.out), Context.COMPONENT.name());
        createPassingMetadata(10, "Mock Test").stream().forEach(componentPdslResults::addTestResult);
        // API
        PolymorphicDslTestRunResults apiPdslResults = new PolymorphicDslTestRunResults(List.of(System.out), Context.API.name());
        createPassingMetadata(8, "Mock Test").stream().forEach(apiPdslResults::addTestResult);

        // System
        PolymorphicDslTestRunResults systemPdslResults = new PolymorphicDslTestRunResults(List.of(System.out), Context.SYSTEM.name());
        createPassingMetadata(5, "Mock Test").stream().forEach(systemPdslResults::addTestResult);

        // UAT
        PolymorphicDslTestRunResults uatPdslResults = new PolymorphicDslTestRunResults(List.of(System.out), Context.UAT.name());
        createPassingMetadata(3, "Mock Test").stream().forEach(uatPdslResults::addTestResult);

        // SMOKE
        PolymorphicDslTestRunResults smokePdslResults = new PolymorphicDslTestRunResults(List.of(System.out), Context.SMOKE.name());
        createPassingMetadata(1, "Mock Test").stream().forEach(smokePdslResults::addTestResult);

        // ACT
        URL adoc = asciidoctorReportGenerator.generateReport(
                List.of(unitPdslResults, componentPdslResults, apiPdslResults, systemPdslResults, uatPdslResults, smokePdslResults));
        assertThat(adoc).isNotNull();
        Path adocHtmlPage = Paths.get(adoc.toString().replace(".adoc", ".html"));
        logger.info(String.format("Path is %s", path.toString()));
        asciidoctor.convertFile(path.toFile(), OptionsBuilder.options()
                .safe(SafeMode.UNSAFE)
        );
        // ASSERT
        // Validate with headless browser
        HtmlPage page = webClient.getPage(adocHtmlPage.toString());
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
        AsciidoctorReportGenerator asciidoctorReportGenerator = new AsciidoctorReportGenerator(path);
        List<MetadataTestRunResults> testRunResults = new ArrayList<>();
        // Unit
        PolymorphicDslTestRunResults unitPdslResults = new PolymorphicDslTestRunResults(List.of(new PdslThreadSafeOutputStream()), "Unit");
        createPassingMetadata(15, "Mock Test").stream().forEach(unitPdslResults::addTestResult);
        createPassingMetadata(14, "Mock Test 2").stream().forEach(unitPdslResults::addTestResult);

        // Component
        PolymorphicDslTestRunResults componentPdslResults = new PolymorphicDslTestRunResults(List.of(System.out), "Component");
        createPassingMetadata(10, "Mock Test").stream().forEach(componentPdslResults::addTestResult);
        createPassingMetadata(9, "Mock Test 2").stream().forEach(componentPdslResults::addTestResult);

        // API
        PolymorphicDslTestRunResults apiPdslResults = new PolymorphicDslTestRunResults(List.of(System.out), "API");
        createPassingMetadata(10, "Mock Test").stream().forEach(apiPdslResults::addTestResult);
        createPassingMetadata(8, "API Only").stream().forEach(apiPdslResults::addTestResult);

        // ACT
        URL adoc = asciidoctorReportGenerator.generateReport(
                List.of(unitPdslResults, componentPdslResults, apiPdslResults));
        assertThat(adoc).isNotNull();
        Path adocHtmlPage = Paths.get(adoc.toString().replace(".adoc", ".html"));
        logger.info(String.format("Path is %s", path.toString()));

        // ASSERT
        // Validate with headless browser
        HtmlPage page = webClient.getPage(adocHtmlPage.toString());
        // Mock Test
        Map<String, TableResults> mockTestResults = getResultsForEachContext("Mock Test", page);
        assertThat(mockTestResults.get(Context.UNIT.toString()).passing).isEqualTo(15);
        assertThat(mockTestResults.get(Context.COMPONENT.toString()).passing).isEqualTo(10);
        assertThat(mockTestResults.get(Context.UNIT.toString()).failing).isEqualTo(0);
        assertThat(mockTestResults.get(Context.COMPONENT.toString()).passing).isEqualTo(0);

        // Mock Test 2
        Map<String, TableResults> mockTests2Results = getResultsForEachContext("Mock Test 2", page);
        assertThat(mockTests2Results.get(Context.UNIT.toString()).passing).isEqualTo(14);
        assertThat(mockTests2Results.get(Context.COMPONENT.toString()).passing).isEqualTo(9);
        assertThat(mockTests2Results.get(Context.UNIT.toString()).failing).isEqualTo(0);
        assertThat(mockTests2Results.get(Context.COMPONENT.toString()).failing).isEqualTo(0);

        // API  Only
        Map<String, TableResults> apiResults = getResultsForEachContext("Mock Test 2", page);
        assertThat(apiResults.get(Context.API.toString()).passing).isEqualTo(8);
        assertThat(apiResults.get(Context.API.toString()).failing).isEqualTo(0);

    }

    @Test
    public void failingPhrases_createsValidAsciidoctorOutput() throws IOException {
        // ARRANGE
        String tempDir = System.getProperty("java.io.tmpdir");
        Path path = Path.of(String.format("%s/%s.adoc", tempDir, UUID.randomUUID()));
        AsciidoctorReportGenerator asciidoctorReportGenerator = new AsciidoctorReportGenerator(path);
        List<MetadataTestRunResults> testRunResults = new ArrayList<>();
        // Unit
        PolymorphicDslTestRunResults unitPdslResults = new PolymorphicDslTestRunResults(List.of(new PdslThreadSafeOutputStream()), Context.UNIT.name());
        createPassingMetadata(15, "Mock Test").stream().forEach(unitPdslResults::addTestResult);
        createPassingMetadata(15, "Mock Test 2").stream().forEach(unitPdslResults::addTestResult);
        createFailingMetadata(3, "Mock Test").stream().forEach(unitPdslResults::addTestResult);

        // Component
        PolymorphicDslTestRunResults componentPdslResults = new PolymorphicDslTestRunResults(List.of(System.out), Context.COMPONENT.name());
        createPassingMetadata(10, "Mock Test").stream().forEach(componentPdslResults::addTestResult);
        createPassingMetadata(10, "Mock Test 2").stream().forEach(componentPdslResults::addTestResult);
        createFailingMetadata(3, "Failing Test").stream().forEach(componentPdslResults::addTestResult);
        URL adoc = asciidoctorReportGenerator.generateReport(
                List.of(unitPdslResults, componentPdslResults));

        assertThat(adoc).isNotNull();
        // ACT
        // Convert into an HTML file
        asciidoctor.convertFile(path.toFile(), OptionsBuilder.options()
                .safe(SafeMode.UNSAFE)
        );
        Path adocHtmlPage = Paths.get(adoc.toString().replace(".adoc", ".html"));
        logger.info(String.format("Path is %s", path.toString()));

        // ASSERT
        // Validate with headless browser
        HtmlPage page = webClient.getPage(adocHtmlPage.toString());
        // Failing Test
        Map<String, TableResults> failingTestResults = getResultsForEachContext("Failing Test", page);
        assertThat(failingTestResults.get(Context.COMPONENT.toString()).failing).isEqualTo(3);
        assertThat(failingTestResults.get(Context.COMPONENT.toString()).passing).isEqualTo(0);
        // Mock Test
        Map<String, TableResults> mockTestResults = getResultsForEachContext("Mock Test", page);
        assertThat(mockTestResults.get(Context.UNIT.toString()).passing).isEqualTo(15);
        assertThat(mockTestResults.get(Context.COMPONENT.toString()).passing).isEqualTo(10);
        assertThat(mockTestResults.get(Context.UNIT.toString()).failing).isEqualTo(3);
        assertThat(mockTestResults.get(Context.COMPONENT.toString()).failing).isEqualTo(0);
        // Mock Test 2
        Map<String, TableResults> mock2TestResults = getResultsForEachContext("Mock Test 2", page);
        assertThat(mock2TestResults.get(Context.UNIT.toString()).passing).isEqualTo(15);
        assertThat(mock2TestResults.get(Context.COMPONENT.toString()).passing).isEqualTo(10);
        assertThat(mock2TestResults.get(Context.UNIT.toString()).failing).isEqualTo(0);
        assertThat(mock2TestResults.get(Context.COMPONENT.toString()).failing).isEqualTo(0);
    }

    private List<DefaultTestResult> createPassingMetadata(int amount, String testSuiteId) {
        List<DefaultTestResult> metadata = new ArrayList<>(amount);
        final TestCase testCase = Mockito.mock(TestCase.class);
        when(testCase.getTestTitle()).thenReturn(testSuiteId);
        when(testCase.getUnfilteredPhraseBody()).thenReturn(List.of("Step1\n", "Step2\n", "Step3"));
        when(testCase.getContextFilteredPhraseBody()).thenReturn(List.of("Step2\n"));
        for (int i = 0; i < amount; i++) {
            metadata.add(DefaultTestResult.passingTest(testCase));
        }
        return metadata;
    }

    private List<DefaultTestResult> createFailingMetadata(int amount, String testSuiteId) {
        List<DefaultTestResult> metadata = new ArrayList<>(amount);
        Phrase phrase = Mockito.mock(Phrase.class);
        ParseTree parseTree = Mockito.mock(ParseTree.class);
        when(parseTree.getText()).thenReturn("A failing phrase");
        when(phrase.getPrefilteredIndex()).thenReturn(Accumulator.next());
        when(phrase.getParseTree()).thenReturn(parseTree);

        final TestCase testCase = Mockito.mock(TestCase.class);
        when(testCase.getTestTitle()).thenReturn(testSuiteId);
        when(testCase.getUnfilteredPhraseBody()).thenReturn(List.of("Step1\n", "Step2\n", "Step3"));
        when(testCase.getContextFilteredPhraseBody()).thenReturn(List.of("Step2\n"));
        for (int i = 0; i < amount; i++) {
            metadata.add(DefaultTestResult.failedTest(testCase, phrase, new IllegalArgumentException("Some exception"), Accumulator.next()));
        }
        return metadata;
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

    private Map<String, TableResults> getResultsForEachContext(String testResource, HtmlPage page) {
        Iterator<HtmlElement> resourceTable = page.getHtmlElementById(String.format("_%s", testResource.replace(' ', '_').toLowerCase()))
                .getParentNode()
                .getHtmlElementDescendants()
                .iterator();
        HtmlElement table = null;
        while (resourceTable.hasNext()) {
            HtmlElement childElement = resourceTable.next();
            DomNodeList<HtmlElement> possibleTable = childElement.getElementsByTagName(("table"));
            if (possibleTable != null && !possibleTable.isEmpty()) {
                table = possibleTable.get(0);
                break;
            }
        }
        assertThat(table).isNotNull();
        // Check which resource the table represents
        List<HtmlElement> testResults = table.getElementsByTagName("tr");
        List<String> contextList = new ArrayList<>(Arrays.asList(testResults.get(0).getTextContent().strip().split("\n")));
        Map<String, TableResults> resultsMap = new HashMap<>();
        // Grab the table headers to determine which column corresponds to each context
        for (String context : contextList) {
            resultsMap.put(context, new TableResults());
        }
        // Go through each table row
        for (int i = 1; i < testResults.size(); i++) {
            HtmlElement row = testResults.get(i);
            DomNodeList<HtmlElement> cells = row.getElementsByTagName("td");
            List<Integer> contextToRemove = new ArrayList<>();
            for (int j = 0; j < cells.size(); j++) {
                HtmlElement cell = cells.get(j);
                if (cell.getTextContent().contains("✅")) {
                    resultsMap.get(contextList.get(j)).passing++;
                } else if (cell.getTextContent().contains("[")) {
                    resultsMap.get(contextList.get(j)).failing++;
                } else {
                    throw new IllegalStateException("Could not interpret cell data! It does not seem to have passed or failed...");
                }
                //Check to see if any table has a rowspan and will thus be ignored in future iterations
                if (!cell.getAttribute("rowspan").isEmpty()) {
                    contextToRemove.add(j);
                }
            }
            // Remove context that won't have cells in the table anymore
            int offset = 0;
            for (int k = 0; k < contextToRemove.size(); k++) {
                contextList.remove(contextList.get(contextToRemove.get(k + offset)));
                offset--;
            }
            if (contextList.isEmpty()) {
                break;
            }
        }
        return resultsMap;
    }
}
