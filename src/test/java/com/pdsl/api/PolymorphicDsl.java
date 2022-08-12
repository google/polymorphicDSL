package com.pdsl.api;


import com.pdsl.executors.DefaultPolymorphicDslTestExecutor;
import com.pdsl.executors.PolymorphicDslTestExecutor;
import com.pdsl.grammars.*;
import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.reports.TestRunResults;
import com.pdsl.specifications.FilteredPhrase;
import com.pdsl.specifications.LineDelimitedTestSpecificationFactory;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.TestSpecificationFactory;
import com.pdsl.testcases.SingleTestOutputPreorderTestCaseFactory;
import com.pdsl.testcases.TestCase;
import com.pdsl.testcases.TestCaseFactory;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.fail;

/**
 * Component tests for PolymorphicDslTestExecutor
 */
public class PolymorphicDsl {

    private static final PolymorphicDslTestExecutor executor = new DefaultPolymorphicDslTestExecutor();
    private static final TestCaseFactory testCaseFactory = new SingleTestOutputPreorderTestCaseFactory();
    private final PolymorphicDslPhraseFilter betaPhraseFilter = new DefaultPolymorphicDslPhraseFilter(
          PolymorphicDslBetaParser.class, BetaLexer.class
    );
    private final TestSpecificationFactory betaTestFactory = new LineDelimitedTestSpecificationFactory(betaPhraseFilter);

    @Test
    public void validGrammarWalkThroughRegistryAllStepsInContext_shouldSucceed() {
        final URI absolutePathValid = new File(getClass().getClassLoader().getResource("sentences/valid.pdsl").getFile()).toURI();
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        // Act
        Optional<Collection<TestSpecification>> testSpecification = betaTestFactory.getTestSpecifications(dslFiles);
        assertThat(testSpecification.isPresent()).isTrue();
        Collection<TestSpecification> specifications = testSpecification.get();
        Collection<TestCase> testCases = testCaseFactory.processTestSpecification(specifications);
        TestRunResults results = executor.runTests(testCases, new PolymorphicDslRegistryParserBaseListener());
        // Assert
        assertThat(results.failingTestTotal()).isEqualTo(0);
        assertThat(results.passingPhraseTotal()).isEqualTo(1);
        assertThat(results.totalPhrases()).isEqualTo(1);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(0);
        assertThat(results.passingTestTotal()).isEqualTo(1);
        assertThat(results.failingTestTotal()).isEqualTo(0);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(0);
    }

    @Test
    public void validGrammarWalkThroughContext_notAllStepsShouldRun() throws URISyntaxException {
        final URI absolutePathValid = getClass().getClassLoader().getResource("sentences/valid.pdsl").toURI();
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        // Act
        Optional<Collection<TestSpecification>> testSpecifications = betaTestFactory.getTestSpecifications(dslFiles);
        assertThat(testSpecifications.isPresent()).isTrue();
        Collection<TestSpecification> specifications = testSpecifications.get();
        Collection<TestCase> testCase = testCaseFactory.processTestSpecification(specifications);
        PolymorphicDslTestRunResults results = new DefaultPolymorphicDslTestExecutor()
                .runTests(testCase, new PolymorphicDslBetaParserBaseListener());
        // Assert
        assertThat(results.passingPhraseTotal()).isEqualTo(1);
        assertThat(results.totalPhrases()).isEqualTo(1);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(0);
        assertThat(results.passingTestTotal()).isEqualTo(1);
        assertThat(results.failingTestTotal()).isEqualTo(0);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(0);
    }

    // NOTE: token recognition errors in the console are expected and indicate that the below test is working
    @Test
    public void contextWalkMatchesNoSteps_noStepsShouldRunAndWarningIssued()  {
        final URI absolutePathValid = new File(getClass().getClassLoader().getResource("sentences/math.pdsl").getFile()).toURI();
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        Optional<Collection<TestSpecification>>  specification = betaTestFactory.getTestSpecifications(dslFiles);
        assertThat(specification.isEmpty()).isTrue();
    }

    @Test
    public void simpleParser_successfullyWalks()  {
        final URI absolutePathValid = new File(getClass().getClassLoader().getResource("sentences/valid_beta.pdsl").getFile()).toURI();
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        // Act
        Optional<Collection<TestSpecification>> testSpecifications = betaTestFactory.getTestSpecifications(dslFiles);
        assertThat(testSpecifications.isPresent()).isTrue();
        Collection<TestSpecification> specifications = testSpecifications.get();
        Collection<TestCase> testCases = testCaseFactory.processTestSpecification(specifications);
        TestRunResults results = executor.runTests(testCases, new PolymorphicDslBetaParserBaseListener());
        // Assert
        assertThat(results.passingTestTotal()).isEqualTo(1);
    }

    @Test
    public void testWithEmptyFile_illegalArgument() {
				boolean exceptionFound = false;
        try {
            TestSpecification emptySpecification = new TestSpecificationStub();
            Collection<TestCase> testCases = testCaseFactory.processTestSpecification(List.of(emptySpecification));
            executor.runTests(testCases, new PolymorphicDslBetaParserBaseListener());
        } catch (Throwable e) {
					exceptionFound = true;
        }
				assertThat(exceptionFound).isTrue();
    }

    private static class TestSpecificationStub implements TestSpecification {

        @Override
        public Optional<InputStream> getMetaData() {
            return Optional.empty();
        }

        @Override
        public Optional<List<TestSpecification>> nestedTestSpecifications() {
            return Optional.empty();
        }

        @Override
        public String getName() {
            return null;
        }

        @Override
        public Optional<List<FilteredPhrase>> getFilteredPhrases() {
            return Optional.empty();
        }

        @Override
        public URI getOriginalTestResource() {
            return null;
        }


    }

    // empty file
    // null file
    // invalid parser
    // invalid lexer

}
