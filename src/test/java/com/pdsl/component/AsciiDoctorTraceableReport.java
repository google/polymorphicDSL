package com.pdsl.component;

import com.pdsl.logging.PdslThreadSafeOutputStream;
import com.pdsl.reports.*;
import com.pdsl.specifications.DefaultPhrase;
import com.pdsl.specifications.Phrase;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

public class AsciiDoctorTraceableReport {

    private static final Logger logger = LoggerFactory.getLogger(AsciiDoctorTraceableReport.class);
    @Test
    public void traceableReportForSingleSuite_createsValidAsciidoctorOutput() throws IOException  {

        // Get the temporary directory and print it.
        String tempDir = System.getProperty("java.io.tmpdir");
        Path path = Path.of(String.format("%s/%s.adoc", tempDir, UUID.randomUUID()));
        AsciidoctorReportGenerator asciidoctorReportGenerator = new AsciidoctorReportGenerator(path);
        List<MetadataTestRunResults> testRunResults = new ArrayList<>();
        // Unit
        PolymorphicDslTestRunResults unitPdslResults = new PolymorphicDslTestRunResults(List.of(new PdslThreadSafeOutputStream()), "Unit");
        createPassingMetadata(15, "Mock Test").stream().forEach(unitPdslResults::addTestResult);

        // Component
        PolymorphicDslTestRunResults componentPdslResults = new PolymorphicDslTestRunResults(List.of(System.out), "Component");
        createPassingMetadata(10, "Mock Test").stream().forEach(componentPdslResults::addTestResult);
        // API
        PolymorphicDslTestRunResults apiPdslResults = new PolymorphicDslTestRunResults(List.of(System.out), "API");
        createPassingMetadata(10, "Mock Test").stream().forEach(apiPdslResults::addTestResult);

        // System
        PolymorphicDslTestRunResults systemPdslResults = new PolymorphicDslTestRunResults(List.of(System.out), "System");
        createPassingMetadata(5, "Mock Test").stream().forEach(systemPdslResults::addTestResult);

        // UAT
        PolymorphicDslTestRunResults uatPdslResults = new PolymorphicDslTestRunResults(List.of(System.out), "UAT");
        createPassingMetadata(3, "Mock Test").stream().forEach(systemPdslResults::addTestResult);

        URL adoc =asciidoctorReportGenerator.generateReport(
                List.of(unitPdslResults, componentPdslResults, apiPdslResults, systemPdslResults, uatPdslResults));
        assertThat(adoc).isNotNull();
        Files.readAllLines(path).stream().anyMatch(str -> str.contains("|==="));
        logger.info(String.format("Path is %s", path.toString()));
    }

    @Test
    public void traceableReportForMultipleSuites_createsValidAsciidoctorOutput() throws IOException  {

        // Get the temporary directory and print it.
        String tempDir = System.getProperty("java.io.tmpdir");
        Path path = Path.of(String.format("%s/%s.adoc", tempDir, UUID.randomUUID()));
        AsciidoctorReportGenerator asciidoctorReportGenerator = new AsciidoctorReportGenerator(path);
        List<MetadataTestRunResults> testRunResults = new ArrayList<>();
        // Unit
        PolymorphicDslTestRunResults unitPdslResults = new PolymorphicDslTestRunResults(List.of(new PdslThreadSafeOutputStream()), "Unit");
        createPassingMetadata(15, "Mock Test").stream().forEach(unitPdslResults::addTestResult);
        createPassingMetadata(15, "Mock Test 2").stream().forEach(unitPdslResults::addTestResult);

        // Component
        PolymorphicDslTestRunResults componentPdslResults = new PolymorphicDslTestRunResults(List.of(System.out), "Component");
        createPassingMetadata(10, "Mock Test").stream().forEach(componentPdslResults::addTestResult);
        createPassingMetadata(10, "Mock Test 2").stream().forEach(componentPdslResults::addTestResult);

        // API
        PolymorphicDslTestRunResults apiPdslResults = new PolymorphicDslTestRunResults(List.of(System.out), "API");
        createPassingMetadata(10, "Mock Test").stream().forEach(apiPdslResults::addTestResult);
        createPassingMetadata(8, "API Only").stream().forEach(apiPdslResults::addTestResult);

        URL adoc =asciidoctorReportGenerator.generateReport(
                List.of(unitPdslResults, componentPdslResults, apiPdslResults));
        assertThat(adoc).isNotNull();
        Files.readAllLines(path).stream().anyMatch(str -> str.contains("|==="));
        logger.info(String.format("Path is %s", path.toString()));
    }

    @Test
    public void failingPhrases_createsValidAsciidoctorOutput() throws IOException {
        String tempDir = System.getProperty("java.io.tmpdir");
        Path path = Path.of(String.format("%s/%s.adoc", tempDir, UUID.randomUUID()));
        AsciidoctorReportGenerator asciidoctorReportGenerator = new AsciidoctorReportGenerator(path);
        List<MetadataTestRunResults> testRunResults = new ArrayList<>();
        // Unit
        PolymorphicDslTestRunResults unitPdslResults = new PolymorphicDslTestRunResults(List.of(new PdslThreadSafeOutputStream()), "Unit");
        createPassingMetadata(15, "Mock Test").stream().forEach(unitPdslResults::addTestResult);
        createPassingMetadata(15, "Mock Test 2").stream().forEach(unitPdslResults::addTestResult);
        createFailingMetadata(3, "Mock Test").stream().forEach(unitPdslResults::addTestResult);

        // Component
        PolymorphicDslTestRunResults componentPdslResults = new PolymorphicDslTestRunResults(List.of(System.out), "Component");
        createPassingMetadata(10, "Mock Test").stream().forEach(componentPdslResults::addTestResult);
        createPassingMetadata(10, "Mock Test 2").stream().forEach(componentPdslResults::addTestResult);
        createFailingMetadata(3, "Failing Test").stream().forEach(componentPdslResults::addTestResult);
        URL adoc =asciidoctorReportGenerator.generateReport(
                List.of(unitPdslResults, componentPdslResults));
        assertThat(adoc).isNotNull();
        Files.readAllLines(path).stream().anyMatch(str -> str.contains("|==="));
        logger.info(String.format("Path is %s", path.toString()));
    }

    private List<TestMetadata> createPassingMetadata(int amount, String testSuiteId) {
        List<TestMetadata> metadata = new ArrayList<>(amount);
        for (int i=0; i < amount; i++) {
            metadata.add(new TestMetadata(testSuiteId, 5, Accumulator.next()));
        }
        return  metadata;
    }

    private List<TestMetadata> createFailingMetadata(int amount, String testSuiteId) {
        List<TestMetadata> metadata = new ArrayList<>(amount);
        Phrase phrase = Mockito.mock(Phrase.class);
        ParseTree parseTree = Mockito.mock(ParseTree.class);
        when(parseTree.getText()).thenReturn("A failing phrase");
        when(phrase.getIndexId()).thenReturn(Accumulator.next());
        when(phrase.getParseTree()).thenReturn(parseTree);
        for (int i=0; i < amount; i++) {
            metadata.add(TestMetadata.failedTest(testSuiteId, 3, 4, phrase, new IllegalArgumentException("Some exception"), Accumulator.next()));
        }
        return metadata;
    }

    private static class Accumulator {
        private static int index = 0;

        public static int next() { return ++index; }
    }
}
