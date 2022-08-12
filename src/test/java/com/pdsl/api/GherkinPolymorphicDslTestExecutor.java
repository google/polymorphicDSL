package com.pdsl.api;

import com.pdsl.gherkin.DefaultGherkinTestSpecificationFactory;
import com.pdsl.gherkin.executors.GherkinTestExecutor;
import com.pdsl.grammars.AllGrammarsLexer;
import com.pdsl.grammars.AllGrammarsParser;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.TestSpecificationFactory;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import org.junit.Test;

import java.io.File;
import java.net.URI;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

public class GherkinPolymorphicDslTestExecutor {

    private static final TestSpecificationFactory provider =
            new DefaultGherkinTestSpecificationFactory.Builder(new DefaultPolymorphicDslPhraseFilter(AllGrammarsParser.class, AllGrammarsLexer.class)).build();
    private static final GherkinTestExecutor gherkinTestExecutor = new GherkinTestExecutor(AllGrammarsParser.class, AllGrammarsLexer.class);

    @Test
    public void minimalFeature_runsAllTests()  {
        final URI absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/complex_background.feature").getFile()).toURI();
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        MetadataTestRunResults results = gherkinTestExecutor.runTestsWithMetadata(dslFiles, stepCounterListener, "API");
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
    public void noTagFilters_runsAllTests()  {
        final URI absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/tags.feature").getFile()).toURI();
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        Optional<Collection<TestSpecification>> allSpecifications = provider.getTestSpecifications(dslFiles);
        assertThat(allSpecifications.isPresent()).isTrue();
        assertThat(allSpecifications.get().size()).isEqualTo(1);
        for (TestSpecification specifications : allSpecifications.get()) {
            MetadataTestRunResults results = gherkinTestExecutor.runTestsWithMetadata(dslFiles, stepCounterListener, "API");
            // Assert
            assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getFilteredPhrases().isPresent()).isTrue();
            assertThat(results.failingTestTotal()).isEqualTo(0);
            assertThat(results.passingPhraseTotal()).isEqualTo(5);
            assertThat(results.totalFilteredDuplicateTests()).isEqualTo(1);
            assertThat(stepCounterListener.getStepsInOrderRun().poll().strip()).isEqualTo("Given the minimalism");
        }
    }

    @Test
    public void featureLevelTag_runsAllTests()  {
        final URI absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/tags.feature").getFile()).toURI();
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        Optional<TestSpecification> specifications = provider.getTestSpecifications(dslFiles).get().stream().findFirst();
        MetadataTestRunResults results = gherkinTestExecutor.runTestsWithMetadata(dslFiles, stepCounterListener, "@feature_tag3");
        // Assert
        assertThat(specifications.get().nestedTestSpecifications().isPresent() || specifications.get().getFilteredPhrases().isPresent()).isTrue();
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(1);
        assertThat(results.passingPhraseTotal()).isEqualTo(5);
        assertThat(results.failingTestTotal()).isEqualTo(0);
    }

    @Test
    public void notFeatureLevelTag_noTestsRun()  {
        final URI absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/tags.feature").getFile()).toURI();
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        Optional<TestSpecification> specifications = provider.getTestSpecifications(dslFiles).get().stream().findFirst();
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener, "not @feature_tag3");
        // Assert
        assertThat(specifications.get().nestedTestSpecifications().isPresent() || specifications.get().getFilteredPhrases().isPresent()).isTrue();
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(0);
    }

    @Test
    public void scenarioLevelTag_testsRunForScenarioOnly()  {
        final URI absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/tags.feature").getFile()).toURI();
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        Optional<TestSpecification> specifications = provider.getTestSpecifications(dslFiles).get().stream().findFirst();
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener, "@scenario_tag1 and @scenario_tag2");
        // Assert
        assertThat(specifications.get().nestedTestSpecifications().isPresent() || specifications.get().getFilteredPhrases().isPresent()).isTrue();
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(1);
    }

    @Test
    public void tableTag_testsRunForTableOnly()  {
        final URI absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/tags.feature").getFile()).toURI();
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);

        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        Optional<TestSpecification> specifications = provider.getTestSpecifications(dslFiles).get().stream().findFirst();
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener, "@ex_tag1");
        // Assert
        assertThat(specifications.get().nestedTestSpecifications().isPresent() || specifications.get().getFilteredPhrases().isPresent()).isTrue();
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(1);
    }

    @Test
    public void scenarioLevelTag_testsRunForOutlineOnly()  {
        final URI absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/tags.feature").getFile()).toURI();
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        Optional<TestSpecification> specifications = provider.getTestSpecifications(dslFiles).get().stream().findFirst();
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener, "@so_tag1");
        // Assert
        assertThat(specifications.get().nestedTestSpecifications().isPresent() || specifications.get().getFilteredPhrases().isPresent()).isTrue();
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(2);
    }

    @Test
    public void firstJoinedTag_runsScenarioWithJoinedTag()  {
        final URI absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/tags.feature").getFile()).toURI();
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        Optional<TestSpecification> specifications = provider.getTestSpecifications(dslFiles).get().stream().findFirst();
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener, "@joined_tag3");
        // Assert
        assertThat(specifications.get().nestedTestSpecifications().isPresent() || specifications.get().getFilteredPhrases().isPresent()).isTrue();
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(1);
    }

    @Test
    public void secondJoinedTag_runsScenarioWithJoinedTag()  {
        final URI absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/tags.feature").getFile()).toURI();
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        Optional<TestSpecification> specifications = provider.getTestSpecifications(dslFiles).get().stream().findFirst();
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener, "@joined_tag4");
        // Assert
        assertThat(specifications.get().nestedTestSpecifications().isPresent() || specifications.get().getFilteredPhrases().isPresent()).isTrue();
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(1);
    }

    @Test
    public void datatables_runsValidScenario()  {
        final URI absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/datatables.feature").getFile()).toURI();
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        Optional<TestSpecification> specifications = provider.getTestSpecifications(dslFiles).get().stream().findFirst();
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener);
        // Assert
        assertThat(specifications.get().nestedTestSpecifications().isPresent() || specifications.get().getFilteredPhrases().isPresent()).isTrue();
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(5);
    }

    @Test
    public void docstringsFeature_executesSuccessfully()  {
        final URI absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/docstrings.feature").getFile()).toURI();
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);

        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        Optional<TestSpecification> specifications = provider.getTestSpecifications(dslFiles).get().stream().findFirst();
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener);
        // Assert
        assertThat(specifications.get().nestedTestSpecifications().isPresent() || specifications.get().getFilteredPhrases().isPresent()).isTrue();
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(8);
    }

    @Test
    public void i18nEmojiFeature_executesSuccessfully()  {
        final URI absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/i18n_emoji.feature").getFile()).toURI();
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        Optional<TestSpecification> specifications = provider.getTestSpecifications(dslFiles).get().stream().findFirst();
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener);
        // Assert
        assertThat(specifications.get().nestedTestSpecifications().isPresent() || specifications.get().getFilteredPhrases().isPresent()).isTrue();
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(1);
    }

    @Test
    public void i18nNoFeature_executesSuccessfully()  {
        final URI absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/i18n_no.feature").getFile()).toURI();
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        Optional<TestSpecification> specifications = provider.getTestSpecifications(dslFiles).get().stream().findFirst();
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener);
        // Assert
        assertThat(specifications.get().nestedTestSpecifications().isPresent() || specifications.get().getFilteredPhrases().isPresent()).isTrue();
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(3);
    }

    @Test
    public void i18nFrFeature_executesSuccessfully()  {
        final URI absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/i18n_fr.feature").getFile()).toURI();
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        Optional<TestSpecification> specifications = provider.getTestSpecifications(dslFiles).get().stream().findFirst();
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener);
        // Assert
        assertThat(specifications.get().nestedTestSpecifications().isPresent() || specifications.get().getFilteredPhrases().isPresent()).isTrue();
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(39);
    }

    @Test
    public void languageFeature_executesSuccessfully()  {
        final URI absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/language.feature").getFile()).toURI();
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        Optional<TestSpecification> specifications = provider.getTestSpecifications(dslFiles).get().stream().findFirst();

        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener);
        // Assert
        assertThat(specifications.get().nestedTestSpecifications().isPresent() || specifications.get().getFilteredPhrases().isPresent()).isTrue();
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(1);
    }
}
