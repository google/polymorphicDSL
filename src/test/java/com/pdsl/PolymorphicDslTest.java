package com.pdsl;


import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.fail;

import com.pdsl.executors.DefaultPolymorphicDslTestExecutor;
import com.pdsl.executors.PolymorphicDslTestExecutor;
import com.pdsl.grammars.*;
import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.LineDelimitedTestSpecificationFactory;
import com.pdsl.specifications.TestSpecificationFactory;
import com.pdsl.testcases.TestCase;
import com.pdsl.testcases.TestCaseFactory;
import com.pdsl.testcases.SingleTestOutputPreorderTestCaseFactory;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Component tests for PolymorphicDslTestExecutor
 */
public class PolymorphicDslTest {

    private static class TestSpecificationStub implements  TestSpecification {

        @Override
        public Optional<ByteArrayOutputStream> getMetaData() {
            return Optional.empty();
        }

        @Override
        public Optional<List<? extends TestSpecification>> nestedTestSpecifications() {
            return Optional.empty();
        }

        @Override
        public String getId() {
            return null;
        }

        @Override
        public Optional<Iterator<ParseTree>> getPhraseIterator() {
            return Optional.empty();
        }

        @Override
        public Optional<List<ParseTree>> getPhrases() {
            return Optional.empty();
        }
    }
    private final PolymorphicDslPhraseFilter betaPhraseFilter = new DefaultPolymorphicDslPhraseFilter<PolymorphicDslRegistryParser, RegistryLexer, PolymorphicDslBetaParser, BetaLexer>(
            PolymorphicDslRegistryParser.class, RegistryLexer.class, PolymorphicDslBetaParser.class, BetaLexer.class
    );
    private final TestSpecificationFactory betaTestFactory = new LineDelimitedTestSpecificationFactory(betaPhraseFilter);
    private static final PolymorphicDslTestExecutor executor = new DefaultPolymorphicDslTestExecutor();
    private static final TestCaseFactory testCaseFactory = new SingleTestOutputPreorderTestCaseFactory();
    @Test
    public void validGrammarWalkThroughRegistryAllStepsInContext_shouldSucceed() throws MalformedURLException {
        final URL absolutePathValid = new File(getClass().getClassLoader().getResource("sentences/valid.pdsl").getFile()).toURI().toURL();
        // Arrange
        Set<URL> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        // Act
        Optional<TestSpecification> testSpecification = betaTestFactory.getTestSpecifications(dslFiles);
        assertThat(testSpecification.isPresent()).isTrue();
        TestSpecification specifications = testSpecification.get();
        Collection<TestCase> testCases = testCaseFactory.processTestSpecification(specifications);
        PolymorphicDslTestRunResults results = executor.runTests(testCases, new PolymorphicDslRegistryParserBaseListener());
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent()).isTrue();
        assertThat(specifications.nestedTestSpecifications().get().size()).isEqualTo(1);
        assertThat(results.failingTestTotal()).isEqualTo(0);
        assertThat(results.passingPhraseTotal()).isEqualTo(1);
        assertThat(results.totalPhrases()).isEqualTo(1);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(0);
        assertThat(results.passingTestTotal()).isEqualTo(1);
        assertThat(results.failingTestTotal()).isEqualTo(0);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(0);
    }

    @Test
    public void validGrammarWalkThroughContext_notAllStepsShouldRun() throws MalformedURLException {
        final URL absolutePathValid = getClass().getClassLoader().getResource("sentences/valid.pdsl");
        // Arrange
        Set<URL> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        // Act
        Optional<TestSpecification> testSpecifications = betaTestFactory.getTestSpecifications(dslFiles);
        assertThat(testSpecifications.isPresent()).isTrue();
        TestSpecification specifications = testSpecifications.get();
        Collection<TestCase> testCase = testCaseFactory.processTestSpecification(specifications);
        PolymorphicDslTestRunResults results = new DefaultPolymorphicDslTestExecutor()
                .runTests(testCase, new PolymorphicDslBetaParserBaseListener());
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent()).isTrue();
        assertThat(specifications.nestedTestSpecifications().get().size()).isEqualTo(1);
        assertThat(specifications.nestedTestSpecifications().get().get(0).getPhrases().get().size()).isEqualTo(1);
        assertThat(results.passingPhraseTotal()).isEqualTo(1);
        assertThat(results.totalPhrases()).isEqualTo(1);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(0);
        assertThat(results.passingTestTotal()).isEqualTo(1);
        assertThat(results.failingTestTotal()).isEqualTo(0);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(0);
    }

    // NOTE: token recognition errors in the console are expected and indicate that the below test is working
    @Test
    public void contextWalkMatchesNoSteps_noStepsShouldRunAndWarningIssued() throws MalformedURLException {
        final URL absolutePathValid = new File(getClass().getClassLoader().getResource("sentences/math.pdsl").getFile()).toURI().toURL();
        // Arrange
        Set<URL> dslFiles = new HashSet<>();

        dslFiles.add(absolutePathValid);

        try {
            // Act
            Optional<TestSpecification> testSpecification = betaTestFactory.getTestSpecifications(dslFiles);
            //Assert
            fail("No exception when no phrases run");
        } catch (Throwable e) { }
    }

    @Test
    public void simpleParser_successfullyWalks() throws MalformedURLException {
        final URL absolutePathValid = new File(getClass().getClassLoader().getResource("sentences/valid_beta.pdsl").getFile()).toURI().toURL();
        // Arrange
        Set<URL> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        // Act
        Optional<TestSpecification> testSpecifications = betaTestFactory.getTestSpecifications(dslFiles);
        assertThat(testSpecifications.isPresent()).isTrue();
        TestSpecification specifications = testSpecifications.get();
        Collection<TestCase> testCases = testCaseFactory.processTestSpecification(specifications);
        PolymorphicDslTestRunResults results = executor.runTests(testCases, new PolymorphicDslBetaParserBaseListener());
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(results.passingTestTotal()).isEqualTo(1);
    }

    @Test
    public void testWithEmptyFile_illegalArgument() {
        try {
            TestSpecification emptySpecification = new TestSpecificationStub();
            Collection<TestCase> testCases = testCaseFactory.processTestSpecification(emptySpecification);
            executor.runTests(testCases, new PolymorphicDslBetaParserBaseListener());
             assert(false) : "No exception thrown when running empty list of specifications!";
        } catch (Throwable e) {

        }
    }

    // empty file
    // null file
    // invalid parser
    // invalid lexer

}
