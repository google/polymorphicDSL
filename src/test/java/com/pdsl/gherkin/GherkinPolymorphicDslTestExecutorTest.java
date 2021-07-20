package com.pdsl.gherkin;

import com.pdsl.gherkin.executors.GherkinTestExecutor;
import com.pdsl.grammars.AllGrammarsLexer;
import com.pdsl.grammars.AllGrammarsParser;
import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.LineDelimitedTestSpecificationFactory;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.TestSpecificationFactory;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.junit.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

public class GherkinPolymorphicDslTestExecutorTest {

    private static final TestSpecificationFactory provider =
            new DefaultGherkinTestSpecificationFactory(new DefaultPolymorphicDslPhraseFilter<AllGrammarsParser, AllGrammarsLexer, AllGrammarsParser, AllGrammarsLexer>(
                    AllGrammarsParser.class, AllGrammarsLexer.class, AllGrammarsParser.class, AllGrammarsLexer.class));
    private static final GherkinTestExecutor gherkinTestExecutor = new <AllGrammarsParser, AllGrammarsLexer, AllGrammarsParser, AllGrammarsLexer> GherkinTestExecutor
            (AllGrammarsParser.class, AllGrammarsLexer.class, AllGrammarsParser.class, AllGrammarsLexer.class);

    @Test
    public void minimalFeature_runsAllTests() throws MalformedURLException {
        final URL absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/complex_background.feature").getFile()).toURI().toURL();
        // Arrange
        Set<URL> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        PolymorphicDslTestRunResults results = gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener);
        // Assert
        assertThat(results.totalPhrases()).isEqualTo(8);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(1);
        assertThat(stepCounterListener.getStepsInOrderRun().poll().strip()).isEqualTo("Given the minimalism inside a background");
        assertThat(stepCounterListener.getStepsInOrderRun().poll().strip()).isEqualTo("Given the minimalism");

        assertThat(stepCounterListener.getStepsInOrderRun().poll().strip()).isEqualTo("Given the minimalism inside a background");
        assertThat(stepCounterListener.getStepsInOrderRun().poll().strip()).isEqualTo("Given a rule background step");
        assertThat(stepCounterListener.getStepsInOrderRun().poll().strip()).isEqualTo("Given the 1 minimalism");

        assertThat(stepCounterListener.getStepsInOrderRun().poll().strip()).isEqualTo("Given the minimalism inside a background");
        assertThat(stepCounterListener.getStepsInOrderRun().poll().strip()).isEqualTo("Given a rule background step");
        assertThat(stepCounterListener.getStepsInOrderRun().poll().strip()).isEqualTo("Given the 2 minimalism");
    }

    @Test
    public void noTagFilters_runsAllTests() throws MalformedURLException {
        final URL absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/tags.feature").getFile()).toURI().toURL();
        // Arrange
        Set<URL> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        Optional<Collection<TestSpecification>> allSpecifications = provider.getTestSpecifications(dslFiles);
        assertThat(allSpecifications.isPresent()).isTrue();
        assertThat(allSpecifications.get().size()).isEqualTo(1);
        for (TestSpecification specifications : allSpecifications.get()) {
            PolymorphicDslTestRunResults results = gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener);
            // Assert
            assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
            assertThat(results.failingTestTotal()).isEqualTo(0);
            assertThat(results.passingPhraseTotal()).isEqualTo(5);
            assertThat(results.totalFilteredDuplicateTests()).isEqualTo(1);
            assertThat(stepCounterListener.getStepsInOrderRun().poll().strip()).isEqualTo("Given the minimalism");
        }
    }

    @Test
    public void featureLevelTag_runsAllTests() throws MalformedURLException {
        final URL absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/tags.feature").getFile()).toURI().toURL();
        // Arrange
        Set<URL> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        Optional<TestSpecification> specifications = provider.getTestSpecifications(dslFiles).get().stream().findFirst();
        PolymorphicDslTestRunResults results = gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener, "@feature_tag3");
        // Assert
        assertThat(specifications.get().nestedTestSpecifications().isPresent() || specifications.get().getPhrases().isPresent());
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(1);
        assertThat(results.passingPhraseTotal()).isEqualTo(5);
        assertThat(results.failingTestTotal()).isEqualTo(0);
    }

    @Test
    public void notFeatureLevelTag_noTestsRun() throws MalformedURLException {
        final URL absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/tags.feature").getFile()).toURI().toURL();
        // Arrange
        Set<URL> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        Optional<TestSpecification> specifications = provider.getTestSpecifications(dslFiles).get().stream().findFirst();
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener, "not @feature_tag3");
        // Assert
        assertThat(specifications.get().nestedTestSpecifications().isPresent() || specifications.get().getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(0);
    }

    @Test
    public void scenarioLevelTag_testsRunForScenarioOnly() throws MalformedURLException {
        final URL absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/tags.feature").getFile()).toURI().toURL();
        // Arrange
        Set<URL> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        Optional<TestSpecification> specifications = provider.getTestSpecifications(dslFiles).get().stream().findFirst();
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener, "@scenario_tag1 and @scenario_tag2");
        // Assert
        assertThat(specifications.get().nestedTestSpecifications().isPresent() || specifications.get().getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(1);
    }

    @Test
    public void tableTag_testsRunForTableOnly() throws MalformedURLException {
        final URL absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/tags.feature").getFile()).toURI().toURL();
        // Arrange
        Set<URL> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);

        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        Optional<TestSpecification> specifications = provider.getTestSpecifications(dslFiles).get().stream().findFirst();
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener, "@ex_tag1");
        // Assert
        assertThat(specifications.get().nestedTestSpecifications().isPresent() || specifications.get().getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(1);
    }

    @Test
    public void scenarioLevelTag_testsRunForOutlineOnly() throws MalformedURLException {
        final URL absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/tags.feature").getFile()).toURI().toURL();
        // Arrange
        Set<URL> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        Optional<TestSpecification> specifications = provider.getTestSpecifications(dslFiles).get().stream().findFirst();
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener, "@so_tag1");
        // Assert
        assertThat(specifications.get().nestedTestSpecifications().isPresent() || specifications.get().getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(2);
    }

    @Test
    public void firstJoinedTag_runsScenarioWithJoinedTag() throws MalformedURLException {
        final URL absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/tags.feature").getFile()).toURI().toURL();
        // Arrange
        Set<URL> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        Optional<TestSpecification> specifications = provider.getTestSpecifications(dslFiles).get().stream().findFirst();
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener, "@joined_tag3");
        // Assert
        assertThat(specifications.get().nestedTestSpecifications().isPresent() || specifications.get().getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(1);
    }

    @Test
    public void secondJoinedTag_runsScenarioWithJoinedTag() throws MalformedURLException {
        final URL absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/tags.feature").getFile()).toURI().toURL();
        // Arrange
        Set<URL> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        Optional<TestSpecification> specifications = provider.getTestSpecifications(dslFiles).get().stream().findFirst();
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener, "@joined_tag4");
        // Assert
        assertThat(specifications.get().nestedTestSpecifications().isPresent() || specifications.get().getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(1);
    }

    @Test
    public void datatables_runsValidScenario() throws MalformedURLException {
        final URL absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/datatables.feature").getFile()).toURI().toURL();
        // Arrange
        Set<URL> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        Optional<TestSpecification> specifications = provider.getTestSpecifications(dslFiles).get().stream().findFirst();
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener);
        // Assert
        assertThat(specifications.get().nestedTestSpecifications().isPresent() || specifications.get().getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(5);
    }

    @Test
    public void docstringsFeature_executesSuccessfully() throws MalformedURLException {
        final URL absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/docstrings.feature").getFile()).toURI().toURL();
        // Arrange
        Set<URL> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);

        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        Optional<TestSpecification> specifications = provider.getTestSpecifications(dslFiles).get().stream().findFirst();
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener);
        // Assert
        assertThat(specifications.get().nestedTestSpecifications().isPresent() || specifications.get().getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(8);
    }

    @Test
    public void i18nEmojiFeature_executesSuccessfully() throws MalformedURLException {
        final URL absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/i18n_emoji.feature").getFile()).toURI().toURL();
        // Arrange
        Set<URL> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        Optional<TestSpecification> specifications = provider.getTestSpecifications(dslFiles).get().stream().findFirst();
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener);
        // Assert
        assertThat(specifications.get().nestedTestSpecifications().isPresent() || specifications.get().getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(1);
    }

    @Test
    public void i18nNoFeature_executesSuccessfully() throws MalformedURLException {
        final URL absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/i18n_no.feature").getFile()).toURI().toURL();
        // Arrange
        Set<URL> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        Optional<TestSpecification> specifications = provider.getTestSpecifications(dslFiles).get().stream().findFirst();
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener);
        // Assert
        assertThat(specifications.get().nestedTestSpecifications().isPresent() || specifications.get().getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(3);
    }

    @Test
    public void i18nFrFeature_executesSuccessfully() throws MalformedURLException {
        final URL absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/i18n_fr.feature").getFile()).toURI().toURL();
        // Arrange
        Set<URL> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        Optional<TestSpecification> specifications = provider.getTestSpecifications(dslFiles).get().stream().findFirst();
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener);
        // Assert
        assertThat(specifications.get().nestedTestSpecifications().isPresent() || specifications.get().getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(39);
    }

    @Test
    public void languageFeature_executesSuccessfully() throws MalformedURLException {
        final URL absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/language.feature").getFile()).toURI().toURL();
        // Arrange
        Set<URL> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        Optional<TestSpecification> specifications = provider.getTestSpecifications(dslFiles).get().stream().findFirst();

        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener);
        // Assert
        assertThat(specifications.get().nestedTestSpecifications().isPresent() || specifications.get().getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(1);
    }
}
