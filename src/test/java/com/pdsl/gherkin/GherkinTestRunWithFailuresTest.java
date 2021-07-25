package com.pdsl.gherkin;

import com.pdsl.gherkin.executors.GherkinTestExecutor;
import com.pdsl.grammars.*;
import com.pdsl.reports.PolymorphicDslTestRunResults;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

public class GherkinTestRunWithFailuresTest {

    private static final GherkinTestExecutor gherkinTestExecutor = new <AllGrammarsParser, AllGrammarsLexer, AllGrammarsParser, AllGrammarsLexer>
            GherkinTestExecutor
            (AllGrammarsParser.class, AllGrammarsLexer.class, AllGrammarsParser.class, AllGrammarsLexer.class);

    @Test
    public void failedMinimal_logsFailedStepResults() throws MalformedURLException {
        final URL absolutePathValid = getClass().getClassLoader().getResource("testdata/good/minimal.feature");
        // Arrange
        Set<URL> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        PolymorphicDslTestRunResults results = gherkinTestExecutor.processFilesAndRunTests(dslFiles, new AllGrammarsParserBaseListener(), new AllGrammarsFailListener());

        // Assert
        assertThat(results.failingTestTotal()).isEqualTo(1);
        assertThat(results.totalPhrases()).isEqualTo(1);
        assertThat(results.passingPhraseTotal()).isEqualTo(0);
    }

    @Test
    public void failedComplexFeature_logsFailedAndSkippedStepResults() throws MalformedURLException {
        final URL absolutePathValid = getClass().getClassLoader().getResource("testdata/good/complex_background.feature");
        // Arrange
        Set<URL> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        PolymorphicDslTestRunResults results = gherkinTestExecutor.processFilesAndRunTests(dslFiles, new AllGrammarsParserBaseListener(), new AllGrammarsFailListener());

        // Assert
        assertThat(results.failingTestTotal()).isEqualTo(3);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(1);
        assertThat(results.totalPhrases()).isEqualTo(8);
        assertThat(results.passingPhraseTotal()).isEqualTo(0);
    }

    @Test
    public void partiallySuccessfulComplexFeature_logsFailedAndSkippedStepResults() throws MalformedURLException {
        final URL absolutePathValid = getClass().getClassLoader().getResource("testdata/good/complex_background.feature");
        // Arrange
        Set<URL> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        PolymorphicDslTestRunResults results = gherkinTestExecutor.processFilesAndRunTests(dslFiles, new AllGrammarsParserBaseListener(), new AllGrammarsFail2ndListener());

        // Assert
        assertThat(results.failingTestTotal()).isEqualTo(3);
        assertThat(results.passingPhraseTotal()).isEqualTo(3);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(1);
        assertThat(results.totalPhrases()).isEqualTo(8);
    }
}
