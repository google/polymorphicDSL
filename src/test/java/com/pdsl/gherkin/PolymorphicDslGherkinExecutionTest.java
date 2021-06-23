package com.pdsl.gherkin;

import com.pdsl.gherkin.executors.GherkinTestExecutor;
import com.pdsl.grammars.AllGrammarsLexer;
import com.pdsl.grammars.AllGrammarsParser;
import com.pdsl.grammars.AllGrammarsParserBaseListener;
import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.transformers.LineDelimitedTestSpecificationFactory;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.TestSpecificationFactory;
import com.pdsl.testcases.ParentForEachChildTestCaseFactory;
import com.pdsl.testcases.TestCase;
import com.pdsl.testcases.TestCaseFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;

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
    private static final DefaultGherkinTestSpecificationFactory provider =
            new DefaultGherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);

    private static final TestCaseFactory testCaseFactory = new ParentForEachChildTestCaseFactory();
    private static final GherkinTestExecutor executor =
            new GherkinTestExecutor(AllGrammarsParser.class, AllGrammarsLexer.class, AllGrammarsParser.class,
                    AllGrammarsLexer.class);
    private static final AllGrammarsParserBaseListener listener = new AllGrammarsParserBaseListener();

    @Test
    public void minimalFeature_executesProperly() {
        final String absolutePathValid = new File(getClass().getClassLoader()
                .getResource("testdata/good/minimal.feature").getFile()).getAbsolutePath();
        // Arrange
        Set<String> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);

        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        Collection<TestCase> testCases = testCaseFactory.processTestSpecification(specifications);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        PolymorphicDslTestRunResults results = executor.runTests(testCases, stepCounterListener);
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
        Set<String> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        Collection<TestCase> testCases = testCaseFactory.processTestSpecification(specifications);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        PolymorphicDslTestRunResults results = executor.runTests(testCases, stepCounterListener);
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
                new DefaultGherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        Set<String> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        Collection<TestCase> testCases = testCaseFactory.processTestSpecification(specifications);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        executor.runTests(testCases, stepCounterListener);
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(2);
    }

    @Test
    public void gherkinTestSpecification_executesRuleFeatureProperly() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/rule.feature").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new DefaultGherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        Set<String> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        Collection<TestCase> testCases = testCaseFactory.processTestSpecification(specifications);
        executor.runTests(testCases, stepCounterListener);
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
                new DefaultGherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        Set<String> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        Collection<TestCase> testCases = testCaseFactory.processTestSpecification(specifications);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        executor.runTests(testCases, stepCounterListener);
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
                new DefaultGherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        Set<String> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        Collection<TestCase> testCases = testCaseFactory.processTestSpecification(specifications);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        PolymorphicDslTestRunResults results = executor.runTests(testCases, stepCounterListener);
        assertThat(results.passingPhraseTotal()).isEqualTo(8);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(1);
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
