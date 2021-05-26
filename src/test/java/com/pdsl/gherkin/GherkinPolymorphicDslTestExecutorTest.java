package com.pdsl.gherkin;

import com.pdsl.executors.GherkinPolymorphicDslTestExecutor;
import com.pdsl.grammars.*;
import com.pdsl.grammars.AllGrammarsLexer;
import com.pdsl.grammars.AllGrammarsParser;
import com.pdsl.grammars.AllGrammarsParserBaseListener;
import com.pdsl.specifications.LineDelimitedTestSpecificationFactory;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.TestSpecificationFactory;
import org.junit.Test;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

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
            new GherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
    private static final GherkinPolymorphicDslTestExecutor.Builder executorBuilder = new GherkinPolymorphicDslTestExecutor.Builder()
            .withGrammarListener(new AllGrammarsParserBaseListener());

    private static final GherkinTestExecutor gherkinTestExecutor = new GherkinTestExecutor
            <AllGrammarsParser, AllGrammarsLexer, AllGrammarsParser, AllGrammarsLexer>
            (AllGrammarsParser.class, AllGrammarsLexer.class, AllGrammarsParser.class, AllGrammarsLexer.class);

    @Test
    public void minimalFeature_runsAllTests() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/complex_background.feature").getFile()).getAbsolutePath();
        // Arrange
        List<String> dslFiles = new LinkedList<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        gherkinTestExecutor.runTests(dslFiles, new AllGrammarsParserBaseListener(), stepCounterListener);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(10);
        assertThat(stepCounterListener.getStepsInOrderRun().poll().strip()).isEqualTo("Given the minimalism inside a background");
        assertThat(stepCounterListener.getStepsInOrderRun().poll().strip()).isEqualTo("Given the minimalism");

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
        List<String> dslFiles = new LinkedList<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();

        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        gherkinTestExecutor.runTests(dslFiles, new AllGrammarsParserBaseListener(), stepCounterListener);
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
                new GherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        List<String> dslFiles = new LinkedList<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        GherkinPolymorphicDslTestExecutor executor = executorBuilder
                .withTagExpression("@feature_tag3")
                .withSubgrammarListener(stepCounterListener)
                .build();
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        executor.runTests(specifications);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(6);
    }

    @Test
    public void notFeatureLevelTag_noTestsRun() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/tags.feature").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new GherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        List<String> dslFiles = new LinkedList<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        GherkinPolymorphicDslTestExecutor executor = executorBuilder
                .withSubgrammarListener(stepCounterListener)
                .withTagExpression("not @feature_tag3")
                .build();
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        executor.runTests(specifications);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(0);
    }

    @Test
    public void scenarioLevelTag_testsRunForScenarioOnly() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/tags.feature").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new GherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        List<String> dslFiles = new LinkedList<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        GherkinPolymorphicDslTestExecutor executor = executorBuilder
                .withSubgrammarListener(stepCounterListener)
                .withTagExpression("@scenario_tag1 and @scenario_tag2")
                .build();
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        executor.runTests(specifications);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(1);
    }

    @Test
    public void tableTag_testsRunForTableOnly() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/tags.feature").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new GherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        List<String> dslFiles = new LinkedList<>();
        dslFiles.add(absolutePathValid);

        StepCounterListener stepCounterListener = new StepCounterListener();
        GherkinPolymorphicDslTestExecutor executor = executorBuilder
                .withTagExpression("@ex_tag1")
                .withSubgrammarListener(stepCounterListener)
                .build();
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        executor.runTests(specifications);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(1);
    }

    @Test
    public void scenarioLevelTag_testsRunForOutlineOnly() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/tags.feature").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new GherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        List<String> dslFiles = new LinkedList<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        GherkinPolymorphicDslTestExecutor executor = executorBuilder
                .withTagExpression("@so_tag1")
                .withSubgrammarListener(stepCounterListener)
                .build();
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        executor.runTests(specifications);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(2);
    }

    @Test
    public void firstJoinedTag_runsScenarioWithJoinedTag() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/tags.feature").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new GherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        List<String> dslFiles = new LinkedList<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();

        GherkinPolymorphicDslTestExecutor executor = executorBuilder
                .withSubgrammarListener(stepCounterListener)
                .withTagExpression("@joined_tag3")
                .build();
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        executor.runTests(specifications);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(1);
    }

    @Test
    public void secondJoinedTag_runsScenarioWithJoinedTag() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/tags.feature").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new GherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        List<String> dslFiles = new LinkedList<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        GherkinPolymorphicDslTestExecutor executor = executorBuilder
                .withSubgrammarListener(stepCounterListener)
                .withTagExpression("@joined_tag4")
                .build();
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        executor.runTests(specifications);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(1);
    }

    @Test
    public void datatables_runsValidScenario() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/datatables.feature").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new GherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        List<String> dslFiles = new LinkedList<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        GherkinPolymorphicDslTestExecutor executor = executorBuilder
                .withSubgrammarListener(stepCounterListener)
                .withTagExpression("")
                .build();
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        executor.runTests(specifications);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(5);
    }

    @Test
    public void docstringsFeature_executesSuccessfully() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/docstrings.feature").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new GherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        List<String> dslFiles = new LinkedList<>();
        dslFiles.add(absolutePathValid);

        StepCounterListener stepCounterListener = new StepCounterListener();

        GherkinPolymorphicDslTestExecutor executor = executorBuilder
                .withSubgrammarListener(stepCounterListener)
                .build();
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        executor.runTests(specifications);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(8);
    }

    @Test
    public void i18nEmojiFeature_executesSuccessfully() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/i18n_emoji.feature").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new GherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        List<String> dslFiles = new LinkedList<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        GherkinPolymorphicDslTestExecutor executor = executorBuilder
                .withSubgrammarListener(stepCounterListener)
                .withTagExpression("")
                .build();
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        executor.runTests(specifications);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(1);
    }

    @Test
    public void i18nNoFeature_executesSuccessfully() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/i18n_no.feature").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new GherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        List<String> dslFiles = new LinkedList<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        GherkinPolymorphicDslTestExecutor executor = executorBuilder
                .withSubgrammarListener(stepCounterListener)
                .withTagExpression("")
                .build();
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        executor.runTests(specifications);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(3);
    }

    @Test
    public void i18nFrFeature_executesSuccessfully() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/i18n_fr.feature").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new GherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        List<String> dslFiles = new LinkedList<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        GherkinPolymorphicDslTestExecutor executor = executorBuilder
                .withSubgrammarListener(stepCounterListener)
                .build();
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        executor.runTests(specifications);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(39);
    }

    @Test
    public void languageFeature_executesSuccessfully() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("testdata/good/language.feature").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new GherkinTestSpecificationFactory(pickleJarFactory, stepBodyHelperFactory, subgrammarHelperFactory);
        List<String> dslFiles = new LinkedList<>();
        dslFiles.add(absolutePathValid);
        StepCounterListener stepCounterListener = new StepCounterListener();
        GherkinPolymorphicDslTestExecutor executor = executorBuilder
                .withSubgrammarListener(stepCounterListener)
                .withTagExpression("")
                .build();
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        executor.runTests(specifications);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(stepCounterListener.getPhrasesEncountered()).isEqualTo(1);
    }
}
