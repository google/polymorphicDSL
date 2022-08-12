package com.pdsl.api;

import com.pdsl.gherkin.executors.GherkinTestExecutor;
import com.pdsl.grammars.*;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.reports.TestRunResults;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

public class GherkinTestRunWithFailures {

    private static final GherkinTestExecutor gherkinTestExecutor = new <AllGrammarsParser, AllGrammarsLexer>
            GherkinTestExecutor
            (AllGrammarsParser.class, AllGrammarsLexer.class);

    @Test
    public void failedMinimal_logsFailedStepResults() throws MalformedURLException, URISyntaxException {
        final URL absolutePathValid = getClass().getClassLoader().getResource("testdata/good/minimal.feature");
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid.toURI());
        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        TestRunResults results = gherkinTestExecutor.processFilesAndRunTests(dslFiles, new AllGrammarsFailListener());

        // Assert
        assertThat(results.failingTestTotal()).isEqualTo(1);
        assertThat(results.totalPhrases()).isEqualTo(1);
        assertThat(results.passingPhraseTotal()).isEqualTo(0);
    }

    @Test
    public void failedComplexFeature_logsFailedAndSkippedStepResults() throws MalformedURLException, URISyntaxException {
        final URL absolutePathValid = getClass().getClassLoader().getResource("testdata/good/complex_background.feature");
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid.toURI());
        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        TestRunResults results = gherkinTestExecutor.processFilesAndRunTests(dslFiles, new AllGrammarsFailListener());

        // Assert
        assertThat(results.failingTestTotal()).isEqualTo(3);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(1);
        assertThat(results.totalPhrases()).isEqualTo(8);
        assertThat(results.passingPhraseTotal()).isEqualTo(0);
    }

    @Test
    public void partiallySuccessfulComplexFeature_logsFailedAndSkippedStepResults() throws MalformedURLException, URISyntaxException {
        final URL absolutePathValid = getClass().getClassLoader().getResource("testdata/good/complex_background.feature");
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid.toURI());
        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        TestRunResults results = gherkinTestExecutor.processFilesAndRunTests(dslFiles,  new AllGrammarsFail2ndListener());

        // Assert
        assertThat(results.failingTestTotal()).isEqualTo(3);
        assertThat(results.passingPhraseTotal()).isEqualTo(3);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(1);
        assertThat(results.totalPhrases()).isEqualTo(8);
    }
}
