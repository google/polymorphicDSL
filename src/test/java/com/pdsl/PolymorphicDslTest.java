package com.pdsl;


import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.fail;

import com.pdsl.executors.DefaultPolymorphicDslTestExecutor;
import com.pdsl.executors.PolymorphicDslTestExecutor;
import com.pdsl.grammars.*;
import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.transformers.LineDelimitedTestSpecificationFactory;
import com.pdsl.specifications.TestSpecificationFactory;
import com.pdsl.testcases.TestCase;
import com.pdsl.testcases.TestCaseFactory;
import com.pdsl.testcases.TopDownDepthFirstTestCaseFactory;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
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

    private static final PolymorphicDslTestExecutor executor = new DefaultPolymorphicDslTestExecutor();
    private static final TestCaseFactory testCaseFactory = new TopDownDepthFirstTestCaseFactory();
    @Test
    public void validGrammarWalkThroughRegistryAllStepsInContext_shouldSucceed()
    {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("sentences/valid.pdsl").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new LineDelimitedTestSpecificationFactory<PolymorphicDslRegistryParser, RegistryLexer>(PolymorphicDslRegistryParser.class, RegistryLexer.class,
                        LineDelimitedTestSpecificationFactory.ErrorListenerStrategy.GRAMMAR);
        Set<String> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        Collection<TestCase> testCases = testCaseFactory.processTestSpecification(specifications);
        PolymorphicDslTestRunResults results = executor.runTests(testCases, new PolymorphicDslRegistryParserBaseListener());
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent()).isTrue();
        assertThat(specifications.nestedTestSpecifications().get().size()).isEqualTo(1);
        assertThat(results.failingTestTotal()).isEqualTo(0);
        assertThat(results.passingPhraseTotal()).isEqualTo(5);
        assertThat(results.totalPhrases()).isEqualTo(5);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(0);
        assertThat(results.passingTestTotal()).isEqualTo(1);
        assertThat(results.failingTestTotal()).isEqualTo(0);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(0);
    }

    @Test
    public void validGrammarWalkThroughContext_notAllStepsShouldRun()
    {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("sentences/valid.pdsl").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new LineDelimitedTestSpecificationFactory<PolymorphicDslBetaParser, BetaLexer>(PolymorphicDslBetaParser.class, BetaLexer.class, LineDelimitedTestSpecificationFactory.ErrorListenerStrategy.SUBGRAMMAR);
        Set<String> dslFiles = new HashSet<>();

        dslFiles.add(absolutePathValid);
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
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
    public void contextWalkMatchesNoSteps_noStepsShouldRunAndWarningIssued() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("sentences/math.pdsl").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new LineDelimitedTestSpecificationFactory<PolymorphicDslBetaParser, BetaLexer>(PolymorphicDslBetaParser.class, BetaLexer.class, LineDelimitedTestSpecificationFactory.ErrorListenerStrategy.SUBGRAMMAR);
        Set<String> dslFiles = new HashSet<>();

        dslFiles.add(absolutePathValid);

        try {
            // Act
            TestSpecification specifications = provider.getTestSpecifications(dslFiles);
            //Assert
            fail("No exception when no phrases run");
        } catch (RuntimeException e) { }
    }

    @Test
    public void simpleParser_successfullyWalks() {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("sentences/valid_beta.pdsl").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new LineDelimitedTestSpecificationFactory<PolymorphicDslBetaParser, BetaLexer>(PolymorphicDslBetaParser.class, BetaLexer.class, LineDelimitedTestSpecificationFactory.ErrorListenerStrategy.SUBGRAMMAR);
        Set<String> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        Collection<TestCase> testCases = testCaseFactory.processTestSpecification(specifications);
        PolymorphicDslTestRunResults results = executor.runTests(testCases, new PolymorphicDslBetaParserBaseListener());
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        assertThat(results.passingTestTotal()).isEqualTo(1);
    }

    @Test
    public void testWithEmptyFile_illegalArgument() {
        // Arrange
        TestSpecificationFactory provider =
                new LineDelimitedTestSpecificationFactory<PolymorphicDslBetaParser, BetaLexer>(PolymorphicDslBetaParser.class, BetaLexer.class, LineDelimitedTestSpecificationFactory.ErrorListenerStrategy.GRAMMAR);
        List<String> dslFiles = new LinkedList<>();


        // Act
        // Assert
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
