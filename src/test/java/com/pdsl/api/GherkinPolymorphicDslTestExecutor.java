package com.pdsl.api;

import com.pdsl.executors.InterpreterObj;
import com.pdsl.gherkin.DefaultGherkinTestSpecificationFactory;
import com.pdsl.gherkin.executors.GherkinTestExecutor;
import com.pdsl.gherkin.specifications.GherkinTestSpecificationFactory;
import com.pdsl.grammars.*;
import com.pdsl.grammars.InterpreterTwoParser.HiFolksContext;
import com.pdsl.grammars.InterpreterTwoParser.PolymorphicDslAllRulesContext;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.TestSpecificationFactory;
import com.pdsl.testcases.*;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.net.URI;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.truth.Truth.assertThat;

public final class GherkinPolymorphicDslTestExecutor {

    private static final TestSpecificationFactory provider =
            new DefaultGherkinTestSpecificationFactory.Builder(new DefaultPolymorphicDslPhraseFilter(AllGrammarsParser.class, AllGrammarsLexer.class)).build();
    private static final GherkinTestExecutor gherkinTestExecutor = new GherkinTestExecutor(AllGrammarsParser.class, AllGrammarsLexer.class);

    @BeforeClass
    public static void beforeAll() {
        System.setProperty("pdsl.filterDuplicates", "true");
    }

    @AfterClass
    public static void afterAll() {
        System.setProperty("pdsl.filterDuplicates", "false");
    }

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

    @Test
    public void interpreter_executesSuccessfully()  {
        final URI absolutePathValid = new File(getClass().getClassLoader().getResource("framework_specifications/features/interpreter/InterpreterAll.feature").getFile()).toURI();
        final List<List<TestCase>> testCasesList = new ArrayList<>();

        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);

        final TestCaseFactory testCaseFactory = new PreorderTestCaseFactory();

        // Initialize the Interpreter#1
        GherkinTestSpecificationFactory gherkinTestSpecificationFactoryOne = new DefaultGherkinTestSpecificationFactory.Builder(new DefaultPolymorphicDslPhraseFilter(InterpreterOneParser.class, InterpreterOneLexer.class)).build();
        Optional<Collection<TestSpecification>> gherkinTestSpecificationsOne = gherkinTestSpecificationFactoryOne.getTestSpecifications(dslFiles);
        testCasesList.add(testCaseFactory.processTestSpecification(gherkinTestSpecificationsOne.get()).stream().collect(Collectors.toUnmodifiableList()));

        // Initialize the Interpreter#2
        GherkinTestSpecificationFactory gherkinTestSpecificationFactoryTwo = new DefaultGherkinTestSpecificationFactory.Builder(new DefaultPolymorphicDslPhraseFilter(InterpreterTwoParser.class, InterpreterTwoLexer.class)).build();
        Optional<Collection<TestSpecification>> gherkinTestSpecificationsTwo = gherkinTestSpecificationFactoryTwo.getTestSpecifications(dslFiles);
        testCasesList.add(testCaseFactory.processTestSpecification(gherkinTestSpecificationsTwo.get()).stream().collect(Collectors.toUnmodifiableList()));

        // Act
        /*
        * com.pdsl.runners.junit.PdslGherkinJUnit4Runner.runChild
        * */
        List<InterpreterObj> InterpreterObjs = List.of(new InterpreterObj(new InterpreterOneParserBaseListener()), new InterpreterObj(new InterpreterTwoParserBaseListener()));
        SharedTestSuite sharedTestSuite = SharedTestSuite.of(testCasesList, InterpreterObjs);
        MetadataTestRunResults results = gherkinTestExecutor.runTestsWithMetadata(sharedTestSuite.getSharedTestCaseList(), "API");

        // Assert
        assertThat(results.totalPhrases()).isEqualTo(2); // 2 Unique test suites out of 3, 1 filtered out
        assertThat(results.getTestResults().size()).isEqualTo(2); // 2 Unique test suites out of 3, 1 filtered out
        assertThat(results.failingTestTotal()).isEqualTo(0);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(1);
        assertThat(results.passingTestTotal()).isEqualTo(2); // 2 Unique test suites out of 3, 1 filtered out
    }

    @Test
    public void interpreter_executesFail()  {
        final URI absolutePathValid = new File(getClass().getClassLoader().getResource("framework_specifications/features/interpreter/InterpreterAll.feature").getFile()).toURI();
        final List<List<TestCase>> testCasesList = new ArrayList<>();

        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);

        final TestCaseFactory testCaseFactory = new PreorderTestCaseFactory();

        // Initialize the Interpreter#1
        GherkinTestSpecificationFactory gherkinTestSpecificationFactoryOne = new DefaultGherkinTestSpecificationFactory.Builder(new DefaultPolymorphicDslPhraseFilter(InterpreterOneParser.class, InterpreterOneLexer.class)).build();
        Optional<Collection<TestSpecification>> gherkinTestSpecificationsOne = gherkinTestSpecificationFactoryOne.getTestSpecifications(dslFiles);
        testCasesList.add(testCaseFactory.processTestSpecification(gherkinTestSpecificationsOne.get()).stream().collect(Collectors.toUnmodifiableList()));

        // Initialize the Interpreter#2
        GherkinTestSpecificationFactory gherkinTestSpecificationFactoryTwo = new DefaultGherkinTestSpecificationFactory.Builder(new DefaultPolymorphicDslPhraseFilter(InterpreterTwoParser.class, InterpreterTwoLexer.class)).build();
        Optional<Collection<TestSpecification>> gherkinTestSpecificationsTwo = gherkinTestSpecificationFactoryTwo.getTestSpecifications(dslFiles);
        testCasesList.add(testCaseFactory.processTestSpecification(gherkinTestSpecificationsTwo.get()).stream().collect(Collectors.toUnmodifiableList()));

        // Prepare the Listener implementation with exception
        class InterpreterTwoListenerExceptionImpl extends InterpreterTwoParserBaseListener {
            private Logger logger = LoggerFactory.getLogger(InterpreterTwoListenerExceptionImpl.class);

            @Override
            public void enterHiFolks(HiFolksContext ctx) {
                throw new IllegalStateException("Let's fail our test execution");
            }

            @Override
            public void visitErrorNode(ErrorNode errorNode) {
                logger.error(errorNode.toString());
                logger.error(errorNode.getText());

                throw new IllegalStateException(
                    "There was an error in the grammar! Check the G4 files for the issue!");
            }
        }

        // Act
        /*
         * com.pdsl.runners.junit.PdslGherkinJUnit4Runner.runChild
         * */
        List<InterpreterObj> interpreterObjs = List.of(new InterpreterObj(new InterpreterOneParserBaseListener()), new InterpreterObj(new InterpreterTwoListenerExceptionImpl()));
        SharedTestSuite sharedTestSuite = SharedTestSuite.of(testCasesList, interpreterObjs);
        MetadataTestRunResults results = gherkinTestExecutor.runTestsWithMetadata(sharedTestSuite.getSharedTestCaseList(), "APIs");

        // Assert
        assertThat(results.totalPhrases()).isEqualTo(2);
        assertThat(results.getTestResults().size()).isEqualTo(2);
        assertThat(results.failingTestTotal()).isEqualTo(2);
        //assertThat(results.totalFilteredDuplicateTests()).isEqualTo(1);
        assertThat(results.passingTestTotal()).isEqualTo(0);
    }
}
