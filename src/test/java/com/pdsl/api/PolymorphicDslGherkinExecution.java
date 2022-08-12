package com.pdsl.api;

import com.pdsl.gherkin.*;
import com.pdsl.gherkin.executors.GherkinTestExecutor;
import com.pdsl.grammars.AllGrammarsLexer;
import com.pdsl.grammars.AllGrammarsParser;
import com.pdsl.grammars.AllGrammarsParserBaseListener;
import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.reports.TestRunResults;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.testcases.PreorderTestCaseFactory;
import com.pdsl.testcases.TestCase;
import com.pdsl.testcases.TestCaseFactory;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class PolymorphicDslGherkinExecution {

    private static final PickleJarFactory pickleJarFactory = new PickleJarFactory(new PdslGherkinInterpreterImpl(), new PdslGherkinListenerImpl(), StandardCharsets.UTF_8);
    private static final PolymorphicDslPhraseFilter allGrammarsPhraseFilter = new DefaultPolymorphicDslPhraseFilter(AllGrammarsParser.class, AllGrammarsLexer.class
    );

    private static final DefaultGherkinTestSpecificationFactory provider =
            new DefaultGherkinTestSpecificationFactory.Builder(allGrammarsPhraseFilter)
                    .withPickleJarFactory(pickleJarFactory).build();

    private static final TestCaseFactory testCaseFactory = new PreorderTestCaseFactory();
    private static final GherkinTestExecutor executor =
            new GherkinTestExecutor(AllGrammarsParser.class,
                    AllGrammarsLexer.class);
    private static final AllGrammarsParserBaseListener listener = new AllGrammarsParserBaseListener();

    @Test
    public void minimalFeature_executesProperly() throws MalformedURLException {
        final URI absolutePathValid = new File(getClass().getClassLoader()
                .getResource("testdata/good/minimal.feature").getFile()).toURI();
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);

        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        Optional<Collection<TestSpecification>> testSpecification = provider.getTestSpecifications(dslFiles);
        assertThat(testSpecification.isPresent()).isTrue();
        Collection<TestSpecification> specifications = testSpecification.get();
        Collection<TestCase> testCases = testCaseFactory.processTestSpecification(specifications);
        // Assert
        TestRunResults results = executor.runTests(testCases, stepCounterListener);
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(1);
        assertThat(results.failingTestTotal()).isEqualTo(0);
        assertThat(results.totalPhrases()).isEqualTo(1);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(0);
        assertThat(results.failingTestTotal()).isEqualTo(0);
    }

    @Test
    public void gherkinTestSpecification_executesVeryLongFeatureProperly() throws MalformedURLException, URISyntaxException {
        final URL absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/very_long.feature").getFile()).toURI().toURL();
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid.toURI());
        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        Optional<Collection<TestSpecification>> testSpecification = provider.getTestSpecifications(dslFiles);
        assertThat(testSpecification.isPresent()).isTrue();
        Collection<TestSpecification> specifications = testSpecification.get();
        Collection<TestCase> testCases = testCaseFactory.processTestSpecification(specifications);
        // Assert));
        TestRunResults results = executor.runTests(testCases, stepCounterListener);
        assertThat(results.totalPhrases()).isEqualTo(5);
        assertThat(results.passingTestTotal()).isEqualTo(1);
        assertThat(results.failingTestTotal()).isEqualTo(0);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(99);
    }

    @Test
    public void gherkinTestSpecification_executesReadmeExampleFeatureProperly() throws MalformedURLException, URISyntaxException {
        final URL absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/readme_example.feature").getFile()).toURI().toURL();
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid.toURI());
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        Optional<Collection<TestSpecification>> testSpecification = provider.getTestSpecifications(dslFiles);
        assertThat(testSpecification.isPresent()).isTrue();
        Collection<TestSpecification> specifications = testSpecification.get();
        Collection<TestCase> testCases = testCaseFactory.processTestSpecification(specifications);
        // Assert
        executor.runTests(testCases, stepCounterListener);
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(2);
    }

    @Test
    public void gherkinTestSpecification_executesRuleFeatureProperly() throws MalformedURLException, URISyntaxException {
        final URL absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/rule.feature").getFile()).toURI().toURL();
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid.toURI());
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        Optional<Collection<TestSpecification>> testSpecificatons = provider.getTestSpecifications(dslFiles);
        assertThat(testSpecificatons.isPresent()).isTrue();
        Collection<TestSpecification> specifications = testSpecificatons.get();
        Collection<TestCase> testCases = testCaseFactory.processTestSpecification(specifications);
        executor.runTests(testCases, stepCounterListener);
        // Assert
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(5);
        assertThat(stepCounterListener.getStepsInOrderRun().poll()).contains("Given fb");
        assertThat(stepCounterListener.getStepsInOrderRun().poll()).contains("Given ab");
        assertThat(stepCounterListener.getStepsInOrderRun().poll()).contains("Given a");
        assertThat(stepCounterListener.getStepsInOrderRun().poll()).contains("Given fb");
        assertThat(stepCounterListener.getStepsInOrderRun().poll()).contains("Given b");
    }

    @Test
    public void gherkinTestSpecification_executesBackgroundFeatureProperly() throws MalformedURLException, URISyntaxException {
        final URL absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/background.feature").getFile()).toURI().toURL();
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid.toURI());
        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        Optional<Collection<TestSpecification>> testSpecification = provider.getTestSpecifications(dslFiles);
        assertThat(testSpecification.isPresent()).isTrue();
        Collection<TestSpecification> specifications = testSpecification.get();
        Collection<TestCase> testCases = testCaseFactory.processTestSpecification(specifications);
        // Assert
        executor.runTests(testCases, stepCounterListener);
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(4);
        assertThat(stepCounterListener.getStepsInOrderRun().poll()).contains("Given the minimalism inside a background");
        assertThat(stepCounterListener.getStepsInOrderRun().poll()).contains("Given the minimalism");
        assertThat(stepCounterListener.getStepsInOrderRun().poll()).contains("Given the minimalism inside a background");
        assertThat(stepCounterListener.getStepsInOrderRun().poll()).contains("Given the minimalism");
    }

    @Test
    public void gherkinTestSpecification_executesComplexBackgroundFeatureProperly() throws MalformedURLException, URISyntaxException {
        final URL absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/complex_background.feature").getFile()).toURI().toURL();
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid.toURI());
        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        Optional<Collection<TestSpecification>> testSpecification = provider.getTestSpecifications(dslFiles);
        assertThat(testSpecification.isPresent()).isTrue();
        Collection<TestSpecification> specifications = testSpecification.get();
        Collection<TestCase> testCases = testCaseFactory.processTestSpecification(specifications);
        // Assert
        TestRunResults results = executor.runTests(testCases, stepCounterListener);
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
