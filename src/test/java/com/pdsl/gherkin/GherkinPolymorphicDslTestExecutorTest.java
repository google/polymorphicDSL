package com.pdsl.gherkin;

import com.pdsl.gherkin.executors.GherkinTestExecutor;
import com.pdsl.grammars.AllGrammarsLexer;
import com.pdsl.grammars.AllGrammarsParser;
import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.transformers.LineDelimitedTestSpecificationFactory;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.TestSpecificationFactory;
import org.junit.Test;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

public class GherkinPolymorphicDslTestExecutorTest {


    private static final PickleJarFactory pickleJarFactory = PickleJarFactory.DEFAULT;
    private static final TestSpecificationFactory stepBodyHelperFactory  =
            new LineDelimitedTestSpecificationFactory(AllGrammarsParser.class,
                    AllGrammarsLexer.class, LineDelimitedTestSpecificationFactory.ErrorListenerStrategy.GRAMMAR);
    private static final TestSpecificationFactory subgrammarHelperFactory  =
            new LineDelimitedTestSpecificationFactory(AllGrammarsParser.class,
                    AllGrammarsLexer.class, LineDelimitedTestSpecificationFactory.ErrorListenerStrategy.SUBGRAMMAR);
    private static final TestSpecificationFactory provider =
            new DefaultGherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);

    private static final GherkinTestExecutor gherkinTestExecutor = new GherkinTestExecutor
            <AllGrammarsParser, AllGrammarsLexer, AllGrammarsParser, AllGrammarsLexer>
            (AllGrammarsParser.class, AllGrammarsLexer.class, AllGrammarsParser.class, AllGrammarsLexer.class);

    @Test
    public void minimalFeature_runsAllTests() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/complex_background.feature").getFile()).getAbsolutePath();
        // Arrange
        Set<String> dslFiles = new HashSet<>();
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
    public void noTagFilters_runsAllTests() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/tags.feature").getFile()).getAbsolutePath();
        // Arrange
        Set<String> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        PolymorphicDslTestRunResults results = gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(6);
        assertThat(stepCounterListener.getStepsInOrderRun().poll().strip()).isEqualTo("Given the minimalism");
    }

    @Test
    public void featureLevelTag_runsAllTests() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/tags.feature").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new DefaultGherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        Set<String> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener, "@feature_tag3");
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(6);
    }

    @Test
    public void notFeatureLevelTag_noTestsRun() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/tags.feature").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new DefaultGherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        Set<String> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener, "not @feature_tag3");
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(0);
    }

    @Test
    public void scenarioLevelTag_testsRunForScenarioOnly() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/tags.feature").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new DefaultGherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        Set<String> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener, "@scenario_tag1 and @scenario_tag2");
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(1);
    }

    @Test
    public void tableTag_testsRunForTableOnly() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/tags.feature").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new DefaultGherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        Set<String> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);

        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener, "@ex_tag1");
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(1);
    }

    @Test
    public void scenarioLevelTag_testsRunForOutlineOnly() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/tags.feature").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new DefaultGherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        Set<String> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener, "@so_tag1");
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(2);
    }

    @Test
    public void firstJoinedTag_runsScenarioWithJoinedTag() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/tags.feature").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new DefaultGherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        Set<String> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener, "@joined_tag3");
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(1);
    }

    @Test
    public void secondJoinedTag_runsScenarioWithJoinedTag() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/tags.feature").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new DefaultGherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        Set<String> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener, "@joined_tag4");
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(1);
    }

    @Test
    public void datatables_runsValidScenario() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/datatables.feature").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new DefaultGherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        Set<String> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(5);
    }

    @Test
    public void docstringsFeature_executesSuccessfully() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/docstrings.feature").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new DefaultGherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        Set<String> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);

        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(8);
    }

    @Test
    public void i18nEmojiFeature_executesSuccessfully() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/i18n_emoji.feature").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new DefaultGherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        Set<String> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(1);
    }

    @Test
    public void i18nNoFeature_executesSuccessfully() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/i18n_no.feature").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new DefaultGherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        Set<String> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(3);
    }

    @Test
    public void i18nFrFeature_executesSuccessfully() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/i18n_fr.feature").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new DefaultGherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        Set<String> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(39);
    }

    @Test
    public void languageFeature_executesSuccessfully() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/language.feature").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new DefaultGherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        Set<String> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        gherkinTestExecutor.processFilesAndRunTests(dslFiles, stepCounterListener);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(1);
    }
}
