package com.pdsl;


import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.fail;

import com.pdsl.executors.DefaultPolymorphicDslTestExecutor;
import com.pdsl.grammars.*;
import com.pdsl.grammars.*;
import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.LineDelimitedTestSpecificationFactory;
import com.pdsl.specifications.TestSpecificationFactory;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import java.io.File;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Component tests for PolymorphicDslTestExecutor
 */
public class PolymorphicDslTest {

    private static class TestSpecificationStub implements  TestSpecification {

        @Override
        public Optional<OutputStream> getMetaData() {
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

    @Test
    public void validGrammarWalkThroughRegistryAllStepsInContext_shouldSucceed()
    {
        final String absolutePathValid = new File(getClass().getClassLoader().getResource("sentences/valid.pdsl").getFile()).getAbsolutePath();
        // Arrange
        TestSpecificationFactory provider =
                new LineDelimitedTestSpecificationFactory<PolymorphicDslRegistryParser, RegistryLexer>(PolymorphicDslRegistryParser.class, RegistryLexer.class, LineDelimitedTestSpecificationFactory.ErrorListenerStrategy.GRAMMAR);
        List<String> dslFiles = new LinkedList<>();
        dslFiles.add(absolutePathValid);
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent()).isTrue();
        assertThat(specifications.nestedTestSpecifications().get().size()).isEqualTo(1);
        DefaultPolymorphicDslTestExecutor executor = new DefaultPolymorphicDslTestExecutor(new PolymorphicDslRegistryParserBaseListener(),
                new PolymorphicDslRegistryParserBaseListener());
        PolymorphicDslTestRunResults results = executor.runTests(specifications);
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
        List<String> dslFiles = new LinkedList<>();

        dslFiles.add(absolutePathValid);
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent()).isTrue();
        assertThat(specifications.nestedTestSpecifications().get().size()).isEqualTo(1);
        assertThat(specifications.nestedTestSpecifications().get().get(0).getPhrases().get().size()).isEqualTo(1);
        DefaultPolymorphicDslTestExecutor executor = new DefaultPolymorphicDslTestExecutor(new PolymorphicDslRegistryParserBaseListener(),
                new PolymorphicDslBetaParserBaseListener());
        PolymorphicDslTestRunResults results = executor.runTests(specifications);
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
        List<String> dslFiles = new LinkedList<>();

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
        List<String> dslFiles = new LinkedList<>();
        dslFiles.add(absolutePathValid);
        // Act
        TestSpecification specifications = provider.getTestSpecifications(dslFiles);
        // Assert
        assertThat(specifications.nestedTestSpecifications().isPresent() || specifications.getPhrases().isPresent());
        DefaultPolymorphicDslTestExecutor executor = new DefaultPolymorphicDslTestExecutor(new PolymorphicDslRegistryParserBaseListener(),
                new PolymorphicDslBetaParserBaseListener());
        executor.runTests(specifications);
    }

    @Test
    public void testWithEmptyFile_illegalArgument() {
        // Arrange
        TestSpecificationFactory provider =
                new LineDelimitedTestSpecificationFactory<PolymorphicDslBetaParser, BetaLexer>(PolymorphicDslBetaParser.class, BetaLexer.class, LineDelimitedTestSpecificationFactory.ErrorListenerStrategy.GRAMMAR);
        List<String> dslFiles = new LinkedList<>();

        TestSpecification emptySpecification = new TestSpecificationStub();
        // Act
        // Assert
        DefaultPolymorphicDslTestExecutor executor = new DefaultPolymorphicDslTestExecutor(new PolymorphicDslRegistryParserBaseListener(),
                new PolymorphicDslBetaParserBaseListener());
        try {
            executor.runTests(emptySpecification);
             assert(false) : "No exception thrown when running empty list of specifications!";
        } catch (IllegalArgumentException e) {

        }
    }

    // empty file
    // null file
    // invalid parser
    // invalid lexer

}
