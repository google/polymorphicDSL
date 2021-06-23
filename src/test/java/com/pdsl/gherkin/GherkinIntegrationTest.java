package com.pdsl.gherkin;

import com.pdsl.gherkin.executors.GherkinTestExecutor;
import com.pdsl.grammars.*;
import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.transformers.LineDelimitedTestSpecificationFactory;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.TestSpecificationFactory;
import com.pdsl.grammars.MinimalImpl;
import com.pdsl.testcases.ParentForEachChildTestCaseFactory;
import com.pdsl.testcases.TestCaseFactory;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class GherkinIntegrationTest {
    private static final PickleJarFactory pickleJarFactory = new PickleJarFactory(new PdslGherkinInterpreterImpl(), new PdslGherkinListenerImpl(), StandardCharsets.UTF_8);
    private static final TestSpecificationFactory stepBodyGrammarHelperFactory  =
            new LineDelimitedTestSpecificationFactory<AllGrammarsParser, AllGrammarsLexer>(AllGrammarsParser.class,
                    AllGrammarsLexer.class, LineDelimitedTestSpecificationFactory.ErrorListenerStrategy.GRAMMAR);
    private static final TestSpecificationFactory stepBodySubgrammarHelperFactory =
            new LineDelimitedTestSpecificationFactory<PolymorphicDslMinimalParser, AllGrammarsLexer>(PolymorphicDslMinimalParser.class,
                    AllGrammarsLexer.class, LineDelimitedTestSpecificationFactory.ErrorListenerStrategy.SUBGRAMMAR);
    private static final TestSpecificationFactory provider =
            new DefaultGherkinTestSpecificationFactory(pickleJarFactory, stepBodyGrammarHelperFactory, stepBodySubgrammarHelperFactory);
    private static final GherkinTestExecutor minimalExecutor = new GherkinTestExecutor(MinimalParser.class, MinimalLexer.class, MinimalParser.class, MinimalLexer.class);
    private static final GherkinTestExecutor executor = new GherkinTestExecutor(AllGrammarsParser.class, AllGrammarsLexer.class, AllGrammarsParser.class, AllGrammarsLexer.class);
    // Only reads the text "Given the minimalism"
    private static final TestCaseFactory testCaseFactory = new ParentForEachChildTestCaseFactory();
    private static final ParseTreeListener allGrammarsListener = new AllGrammarsParserBaseListener();

    @Test
    public void complexBackgroundWithRuleFilteredOut_runsSuccessfully() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/complex_background.feature").getFile()).getAbsolutePath();
        // Arrange
        Set<String> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();

        MinimalImpl minimalListener = new MinimalImpl();
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        executor.runTests(testCaseFactory.processTestSpecification(specifications), new AllGrammarsParserBaseListener());
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
    }

    @Test
    public void minimalContextExecutor_executesSuccessfully() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/minimal.feature").getFile()).getAbsolutePath();
        // Arrange
        Set<String> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();

        MinimalImpl minimalListener = new MinimalImpl();
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        PolymorphicDslTestRunResults results = minimalExecutor.runTests(testCaseFactory.processTestSpecification(specifications), allGrammarsListener);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(results.passingPhraseTotal()).isEqualTo(1);
    }

    @Test
    public void minimalContextExecutor_filtersOutUnknownSteps() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/background.feature").getFile()).getAbsolutePath();
        // Arrange

        Set<String> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();

        MinimalImpl minimalListener = new MinimalImpl();

        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        PolymorphicDslTestRunResults results = executor.runTests(testCaseFactory.processTestSpecification(specifications), allGrammarsListener);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(results.passingPhraseTotal()).isEqualTo(4);
    }
}
