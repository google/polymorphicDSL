package com.pdsl.gherkin;

import com.pdsl.executors.GherkinPolymorphicDslTestExecutor;
import com.pdsl.grammars.*;
import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.specifications.LineDelimitedTestSpecificationFactory;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.TestSpecificationFactory;
import com.pdsl.grammars.*;
import org.junit.Test;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class GherkinTestRunWithFailuresTest {

    private static final PickleJarFactory pickleJarFactory = PickleJarFactory.DEFAULT;
    private static final TestSpecificationFactory stepBodyHelperFactory  =
            new LineDelimitedTestSpecificationFactory(AllGrammarsParser.class,
                    AllGrammarsLexer.class, LineDelimitedTestSpecificationFactory.ErrorListenerStrategy.GRAMMAR);
    private static final TestSpecificationFactory subgrammarHelperFactory  =
            new LineDelimitedTestSpecificationFactory(AllGrammarsParser.class,
                    AllGrammarsLexer.class, LineDelimitedTestSpecificationFactory.ErrorListenerStrategy.SUBGRAMMAR);
    private static final TestSpecificationFactory provider =
            new GherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
    private static final GherkinPolymorphicDslTestExecutor.Builder executorBuilder = new GherkinPolymorphicDslTestExecutor.Builder()
            .withGrammarListener(new AllGrammarsParserBaseListener())
            .withSubgrammarListener(new AllGrammarsFailListener());

    private static final GherkinTestExecutor gherkinTestExecutor = new GherkinTestExecutor
            <AllGrammarsParser, AllGrammarsLexer, AllGrammarsParser, AllGrammarsLexer>
            (AllGrammarsParser.class, AllGrammarsLexer.class, AllGrammarsParser.class, AllGrammarsLexer.class);

    @Test
    public void failedMinimal_logsFailedStepResults() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/minimal.feature").getFile()).getAbsolutePath();
        // Arrange
        List<String> dslFiles = new LinkedList<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        PolymorphicDslTestRunResults results = gherkinTestExecutor.runTests(dslFiles, new AllGrammarsParserBaseListener(), new AllGrammarsFailListener());

        // Assert
        assertThat(results.failingTestTotal()).isEqualTo(1);
        assertThat(results.totalPhrases()).isEqualTo(1);
        assertThat(results.passingPhraseTotal()).isEqualTo(0);
    }

    @Test
    public void failedComplexFeature_logsFailedAndSkippedStepResults() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/complex_background.feature").getFile()).getAbsolutePath();
        // Arrange
        List<String> dslFiles = new LinkedList<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        PolymorphicDslTestRunResults results = gherkinTestExecutor.runTests(dslFiles, new AllGrammarsParserBaseListener(), new AllGrammarsFailListener());

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
        List<String> dslFiles = new LinkedList<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        PolymorphicDslTestRunResults results = gherkinTestExecutor.runTests(dslFiles, new AllGrammarsParserBaseListener(), new AllGrammarsFail2ndListener());

        // Assert
        assertThat(results.failingTestTotal()).isEqualTo(3);
        assertThat(results.passingPhraseTotal()).isEqualTo(3);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(1);
        assertThat(results.totalPhrases()).isEqualTo(8);
    }
}
