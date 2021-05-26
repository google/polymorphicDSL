package com.google.pdsl.gherkin;

import com.google.pdsl.executors.GherkinPolymorphicDslTestExecutor;
import com.google.pdsl.executors.ParentForEachChildPhraseFullExecutor;
import com.google.pdsl.grammars.MinimalImpl;
import com.google.pdsl.grammars.*;
import com.google.pdsl.specifications.LineDelimitedTestSpecificationFactory;
import com.google.pdsl.specifications.TestSpecification;
import com.google.pdsl.specifications.TestSpecificationFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

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
            new GherkinTestSpecificationFactory(pickleJarFactory, stepBodyGrammarHelperFactory, stepBodySubgrammarHelperFactory);

    // Only reads the text "Given the minimalism"
    private static final GherkinPolymorphicDslTestExecutor.Builder executorBuilder = new GherkinPolymorphicDslTestExecutor.Builder()
            .withVerifierExecutor( new ParentForEachChildPhraseFullExecutor())
            .withSubgrammarListener(new MinimalImpl())
            .withGrammarListener(new MinimalParserBaseListener());


    @Test
    public void complexBackgroundWithRuleFilteredOut_runsSuccessfully() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/complex_background.feature").getFile()).getAbsolutePath();
        // Arrange
        List<String> dslFiles = new LinkedList<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();

        MinimalImpl minimalListener = new MinimalImpl();
        GherkinPolymorphicDslTestExecutor executor = executorBuilder
                .withSubgrammarListener(minimalListener)
                .withTagExpression("")
                .build();
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        executor.runTests(specifications);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
    }

    @Test
    public void minimalContextExecutor_executesSuccessfully() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/minimal.feature").getFile()).getAbsolutePath();
        // Arrange
        List<String> dslFiles = new LinkedList<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();

        MinimalImpl minimalListener = new MinimalImpl();
        GherkinPolymorphicDslTestExecutor executor = executorBuilder
                .withSubgrammarListener(minimalListener)
                .withTagExpression("")
                .build();
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        executor.runTests(specifications);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(minimalListener.getStepCount()).isEqualTo(1);
    }

    @Test
    public void minimalContextExecutor_filtersOutUnknownSteps() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/background.feature").getFile()).getAbsolutePath();
        // Arrange

        List<String> dslFiles = new LinkedList<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();

        MinimalImpl minimalListener = new MinimalImpl();
        GherkinPolymorphicDslTestExecutor executor = executorBuilder
                .withSubgrammarListener(minimalListener)
                .withTagExpression("")
                .build();

        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        executor.runTests(specifications);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(minimalListener.getStepCount()).isEqualTo(4);
    }
}
