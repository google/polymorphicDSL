package com.pdsl.api;

import com.pdsl.gherkin.*;
import com.pdsl.gherkin.executors.GherkinTestExecutor;
import com.pdsl.grammars.*;
import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.reports.TestRunResults;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.TestSpecificationFactory;
import com.pdsl.testcases.PreorderTestCaseFactory;
import com.pdsl.testcases.TestCaseFactory;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

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
public class GherkinApi {
    private static final PickleJarFactory pickleJarFactory = new PickleJarFactory(new PdslGherkinInterpreterImpl(), new PdslGherkinListenerImpl(), StandardCharsets.UTF_8);
    private static final PolymorphicDslPhraseFilter phraseFilter = new DefaultPolymorphicDslPhraseFilter(
           PolymorphicDslMinimalParser.class, AllGrammarsLexer.class);
    private static final TestSpecificationFactory provider =
            new DefaultGherkinTestSpecificationFactory.Builder(phraseFilter).withPickleJarFactory(pickleJarFactory).build();
    private static final GherkinTestExecutor minimalExecutor = new GherkinTestExecutor(PolymorphicDslMinimalParser.class, MinimalLexer.class);
    private static final GherkinTestExecutor executor = new GherkinTestExecutor(AllGrammarsParser.class, AllGrammarsLexer.class);
    // Only reads the text "Given the minimalism"
    private static final TestCaseFactory testCaseFactory = new PreorderTestCaseFactory();
    private static final ParseTreeListener allGrammarsListener = new AllGrammarsParserBaseListener();

    @Test
    public void complexBackgroundWithRuleFilteredOut_runsSuccessfully() throws URISyntaxException, MalformedURLException {
        final URL absolutePathValid = getClass().getClassLoader().getResource("testdata/good/complex_background.feature");
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid.toURI());
        StepCounterListener stepCounterListener = new StepCounterListener();

        MinimalImpl minimalListener = new MinimalImpl();
        // Act
        Optional<Collection<TestSpecification>> specifications = provider.getTestSpecifications(dslFiles);
        assertThat(specifications.isPresent()).isTrue();
        TestRunResults results = executor.runTests(testCaseFactory.processTestSpecification(specifications.get()), new AllGrammarsParserBaseListener());
        // Assert
        assertThat(results.failingTestTotal()).isEqualTo(0);
        assertThat(results.passingTestTotal()).isGreaterThan(0);
    }

    @Test
    public void minimalContextExecutor_executesSuccessfully() throws URISyntaxException, MalformedURLException {
        final URL absolutePathValid = getClass().getClassLoader().getResource("testdata/good/minimal.feature");
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid.toURI());
        StepCounterListener stepCounterListener = new StepCounterListener();

        MinimalImpl minimalListener = new MinimalImpl();
        // Act
        Optional<Collection<TestSpecification>> testSpecification = provider.getTestSpecifications(dslFiles);
        assertThat(testSpecification.isPresent()).isTrue();
        TestRunResults results = minimalExecutor.runTests(testCaseFactory.processTestSpecification(testSpecification.get()), allGrammarsListener);
        // Assert
        assertThat(results.passingPhraseTotal()).isEqualTo(1);
    }

    @Test
    public void minimalContextExecutor_filtersOutUnknownSteps() throws URISyntaxException {
        final URL absolutePathValid = getClass().getClassLoader().getResource("testdata/good/background.feature");
        // Arrange

        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid.toURI());
        StepCounterListener stepCounterListener = new StepCounterListener();

        MinimalImpl minimalListener = new MinimalImpl();

        // Act
        Optional<Collection<TestSpecification>> specifications = provider.getTestSpecifications(dslFiles);
        assertThat(specifications.isPresent()).isTrue();
        TestRunResults results = executor.runTests(testCaseFactory.processTestSpecification(specifications.get()), allGrammarsListener);
        // Assert
        assertThat(results.passingPhraseTotal()).isEqualTo(4);
    }
}
