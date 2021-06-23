package com.pdsl.gherkin;

import com.pdsl.gherkin.executors.GherkinTestExecutor;
import com.pdsl.grammars.*;
import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.transformers.LineDelimitedTestSpecificationFactory;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.TestSpecificationFactory;
import org.junit.Test;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

public class GherkinTestRunWithFailuresTest {

    private static final PickleJarFactory pickleJarFactory = PickleJarFactory.DEFAULT;
    private static final TestSpecificationFactory stepBodyHelperFactory =
            new LineDelimitedTestSpecificationFactory(AllGrammarsParser.class,
                    AllGrammarsLexer.class, LineDelimitedTestSpecificationFactory.ErrorListenerStrategy.GRAMMAR);
    private static final TestSpecificationFactory subgrammarHelperFactory =
            new LineDelimitedTestSpecificationFactory(AllGrammarsParser.class,
                    AllGrammarsLexer.class, LineDelimitedTestSpecificationFactory.ErrorListenerStrategy.SUBGRAMMAR);
    private static final TestSpecificationFactory provider =
            new DefaultGherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
    private static final GherkinTestExecutor gherkinTestExecutor = new GherkinTestExecutor
            <AllGrammarsParser, AllGrammarsLexer, AllGrammarsParser, AllGrammarsLexer>
            (AllGrammarsParser.class, AllGrammarsLexer.class, AllGrammarsParser.class, AllGrammarsLexer.class);

    @Test
    public void failedMinimal_logsFailedStepResults() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/minimal.feature").getFile()).getAbsolutePath();
        // Arrange
        Set<String> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        PolymorphicDslTestRunResults results = gherkinTestExecutor.processFilesAndRunTests(dslFiles, new AllGrammarsParserBaseListener(), new AllGrammarsFailListener());

        // Assert
        assertThat(results.failingTestTotal()).isEqualTo(1);
        assertThat(results.totalPhrases()).isEqualTo(1);
        assertThat(results.passingPhraseTotal()).isEqualTo(0);
    }

    @Test
    public void failedComplexFeature_logsFailedAndSkippedStepResults() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/complex_background.feature").getFile()).getAbsolutePath();
        // Arrange
        Set<String> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        PolymorphicDslTestRunResults results = gherkinTestExecutor.processFilesAndRunTests(dslFiles, new AllGrammarsParserBaseListener(), new AllGrammarsFailListener());

        // Assert
        assertThat(results.failingTestTotal()).isEqualTo(3);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(1);
        assertThat(results.totalPhrases()).isEqualTo(8);
        assertThat(results.passingPhraseTotal()).isEqualTo(0);
    }

    @Test
    public void partiallySuccessfulComplexFeature_logsFailedAndSkippedStepResults() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/complex_background.feature").getFile()).getAbsolutePath();
        // Arrange
        Set<String> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        PolymorphicDslTestRunResults results = gherkinTestExecutor.processFilesAndRunTests(dslFiles, new AllGrammarsParserBaseListener(), new AllGrammarsFail2ndListener());

        // Assert
        assertThat(results.failingTestTotal()).isEqualTo(3);
        assertThat(results.passingPhraseTotal()).isEqualTo(3);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(1);
        assertThat(results.totalPhrases()).isEqualTo(8);
    }
}
