package com.pdsl.gherkin;

import com.pdsl.executors.DefaultPolymorphicDslTestExecutor;
import com.pdsl.executors.GherkinPolymorphicDslTestExecutor;
import com.pdsl.executors.ParentForEachChildPhraseFullExecutor;
import com.pdsl.grammars.*;
import com.pdsl.grammars.AllGrammarsLexer;
import com.pdsl.grammars.AllGrammarsParser;
import com.pdsl.grammars.AllGrammarsParserBaseListener;
import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.specifications.LineDelimitedTestSpecificationFactory;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.TestSpecificationFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class PolymorphicDslGherkinExecutionTest {

    private static final PickleJarFactory pickleJarFactory = new PickleJarFactory(new PdslGherkinInterpreterImpl(), new PdslGherkinListenerImpl(), StandardCharsets.UTF_8);
    private static final TestSpecificationFactory stepBodyHelperFactory  =
            new LineDelimitedTestSpecificationFactory(AllGrammarsParser.class,
                    AllGrammarsLexer.class, LineDelimitedTestSpecificationFactory.ErrorListenerStrategy.GRAMMAR);
    private static final TestSpecificationFactory subgrammarHelperFactory =
            new LineDelimitedTestSpecificationFactory(AllGrammarsParser.class,
                    AllGrammarsLexer.class, LineDelimitedTestSpecificationFactory.ErrorListenerStrategy.SUBGRAMMAR);
    private static final TestSpecificationFactory provider =
            new GherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
    private static final ParentForEachChildPhraseFullExecutor phraseVerifierContextExecutor = new ParentForEachChildPhraseFullExecutor();

    private static final GherkinPolymorphicDslTestExecutor.Builder executorBuilder =
            new GherkinPolymorphicDslTestExecutor.Builder()
            .withGrammarListener(new AllGrammarsParserBaseListener());

    @Test
    public void minimalFeature_executesProperly() {
        final String absolutePathValid = new File(getClass().getClassLoader()
                .getResource("testdata/good/minimal.feature").getFile()).getAbsolutePath();
        // Arrange
        List<String> dslFiles = new LinkedList<>();
        dslFiles.add(absolutePathValid);

        StepCounterListener stepCounterListener = new StepCounterListener();
        DefaultPolymorphicDslTestExecutor executor =  new DefaultPolymorphicDslTestExecutor(new AllGrammarsParserBaseListener(), stepCounterListener, phraseVerifierContextExecutor);

        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        PolymorphicDslTestRunResults results = executor.runTests(specifications);
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(1);
        assertThat(results.failingTestTotal()).isEqualTo(0);
        assertThat(results.totalPhrases()).isEqualTo(1);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(0);
        assertThat(results.failingTestTotal()).isEqualTo(0);
    }

    @Test
    public void gherkinTestSpecification_executesVeryLongFeatureProperly() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/very_long.feature").getFile()).getAbsolutePath();
        // Arrange
                List<String> dslFiles = new LinkedList<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        DefaultPolymorphicDslTestExecutor executor =  new DefaultPolymorphicDslTestExecutor(
                new AllGrammarsParserBaseListener(), new AllGrammarsParserBaseListener(), phraseVerifierContextExecutor);

        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        PolymorphicDslTestRunResults results = executor.runTests(specifications);
        assertThat(results.totalPhrases()).isEqualTo(5);
        assertThat(results.passingTestTotal()).isEqualTo(1);
        assertThat(results.failingTestTotal()).isEqualTo(0);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(99);
    }

    @Test
    public void gherkinTestSpecification_executesReadmeExampleFeatureProperly() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/readme_example.feature").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new GherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        List<String> dslFiles = new LinkedList<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        DefaultPolymorphicDslTestExecutor executor =  new DefaultPolymorphicDslTestExecutor(new AllGrammarsParserBaseListener(), stepCounterListener, phraseVerifierContextExecutor);

        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        executor.runTests(specifications);
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(2);
    }

    @Test
    public void gherkinTestSpecification_executesRuleFeatureProperly() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/rule.feature").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new GherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        List<String> dslFiles = new LinkedList<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        DefaultPolymorphicDslTestExecutor executor =  new DefaultPolymorphicDslTestExecutor(new AllGrammarsParserBaseListener(), stepCounterListener, phraseVerifierContextExecutor);
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        executorBuilder.withSubgrammarListener(stepCounterListener)
                .build()
                .runTests(specifications);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(5);
        assertThat(stepCounterListener.getStepsInOrderRun().poll()).contains("Given fb");
        assertThat(stepCounterListener.getStepsInOrderRun().poll()).contains("Given ab");
        assertThat(stepCounterListener.getStepsInOrderRun().poll()).contains("Given a");
        assertThat(stepCounterListener.getStepsInOrderRun().poll()).contains("Given fb");
        assertThat(stepCounterListener.getStepsInOrderRun().poll()).contains("Given b");
    }

    @Test
    public void gherkinTestSpecification_executesBackgroundFeatureProperly() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/background.feature").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new GherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        List<String> dslFiles = new LinkedList<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        DefaultPolymorphicDslTestExecutor executor =  new DefaultPolymorphicDslTestExecutor(new AllGrammarsParserBaseListener(), stepCounterListener, phraseVerifierContextExecutor);
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        executor.runTests(specifications);
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(4);
        assertThat(stepCounterListener.getStepsInOrderRun().poll()).contains("Given the minimalism inside a background");
        assertThat(stepCounterListener.getStepsInOrderRun().poll()).contains("Given the minimalism");
        assertThat(stepCounterListener.getStepsInOrderRun().poll()).contains("Given the minimalism inside a background");
        assertThat(stepCounterListener.getStepsInOrderRun().poll()).contains("Given the minimalism");
    }

    @Test
    public void gherkinTestSpecification_executesComplexBackgroundFeatureProperly() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/complex_background.feature").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new GherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        List<String> dslFiles = new LinkedList<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        DefaultPolymorphicDslTestExecutor executor =  new DefaultPolymorphicDslTestExecutor(new AllGrammarsParserBaseListener(), stepCounterListener, phraseVerifierContextExecutor);
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        executor.runTests(specifications);
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(10);
        assertThat(stepCounterListener.getStepsInOrderRun().poll()).contains("Given the minimalism inside a background");
        assertThat(stepCounterListener.getStepsInOrderRun().poll()).contains("Given the minimalism");

        assertThat(stepCounterListener.getStepsInOrderRun().poll()).contains("Given the minimalism inside a background");
        assertThat(stepCounterListener.getStepsInOrderRun().poll()).contains("Given the minimalism");

        assertThat(stepCounterListener.getStepsInOrderRun().poll()).contains("Given the minimalism inside a background");
        assertThat(stepCounterListener.getStepsInOrderRun().poll()).contains("Given a rule background step");
        assertThat(stepCounterListener.getStepsInOrderRun().poll()).contains("Given the 1 minimalism");

        assertThat(stepCounterListener.getStepsInOrderRun().poll()).contains("Given the minimalism inside a background");
        assertThat(stepCounterListener.getStepsInOrderRun().poll()).contains("Given a rule background step");
        assertThat(stepCounterListener.getStepsInOrderRun().poll()).contains("Given the 2 minimalism");
    }
}
