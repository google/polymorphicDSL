package com.pdsl.grammars;

import com.pdsl.gherkin.DefaultGherkinTestSpecificationFactory;
import com.pdsl.gherkin.executors.GherkinTestExecutor;
import com.pdsl.reports.TestRunResults;
import com.pdsl.transformers.LineDelimitedTestSpecificationFactory;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.TestSpecificationFactory;
import com.pdsl.testcases.ParentForEachChildTestCaseFactory;
import com.pdsl.testcases.TestCase;
import com.pdsl.testcases.TestCaseFactory;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;
import static com.google.common.truth.Truth.assertThat;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PdslFrameworkSpecificationImpl implements PdslFrameworkSpecificationParserListener {

    private Set<String> resourcePaths = new HashSet<>();
    private TestSpecification testSpecification;
    private Collection<TestCase> testCases;
    private TestRunResults results;
    private ParseTreeListener grammarListener;
    private ParseTreeListener subGrammarListener;

    @Override
    public void enterGivenTheTestResource(PdslFrameworkSpecificationParser.GivenTheTestResourceContext ctx) {
        resourcePaths.add(ctx.getText());
    }

    @Override
    public void exitGivenTheTestResource(PdslFrameworkSpecificationParser.GivenTheTestResourceContext ctx) { }

    @Override
    public void enterWhenTheTestResourceIsProcessedByFactory(PdslFrameworkSpecificationParser.WhenTheTestResourceIsProcessedByFactoryContext ctx) {
        TestSpecificationFactory testSpecificationFactory = new DefaultGherkinTestSpecificationFactory(new LineDelimitedTestSpecificationFactory<AllGrammarsParser, AllGrammarsLexer>(AllGrammarsParser.class,
                AllGrammarsLexer.class, LineDelimitedTestSpecificationFactory.ErrorListenerStrategy.GRAMMAR),
                new LineDelimitedTestSpecificationFactory<AllGrammarsParser, AllGrammarsLexer>(AllGrammarsParser.class,
                        AllGrammarsLexer.class, LineDelimitedTestSpecificationFactory.ErrorListenerStrategy.SUBGRAMMAR));
        testSpecification = testSpecificationFactory.getTestSpecifications(resourcePaths);
    }

    @Override
    public void exitWhenTheTestResourceIsProcessedByFactory(PdslFrameworkSpecificationParser.WhenTheTestResourceIsProcessedByFactoryContext ctx) { }

    @Override
    public void enterTestSpecificationIsProduced(PdslFrameworkSpecificationParser.TestSpecificationIsProducedContext ctx) {

    }

    @Override
    public void exitTestSpecificationIsProduced(PdslFrameworkSpecificationParser.TestSpecificationIsProducedContext ctx) { }

    @Override
    public void enterTestSpecificationHasAnId(PdslFrameworkSpecificationParser.TestSpecificationHasAnIdContext ctx) {
        assertThat(testSpecification.getId()).isNotNull();
        assertThat(testSpecification.getId()).isNotEmpty();
    }

    @Override
    public void exitTestSpecificationHasAnId(PdslFrameworkSpecificationParser.TestSpecificationHasAnIdContext ctx) { }

    @Override
    public void enterTestSpecificationInExpectedFormat(PdslFrameworkSpecificationParser.TestSpecificationInExpectedFormatContext ctx) {
        //TODO: Implement for each test resource
    }

    @Override
    public void exitTestSpecificationInExpectedFormat(PdslFrameworkSpecificationParser.TestSpecificationInExpectedFormatContext ctx) { }

    @Override
    public void enterTestSpecificationIsProcessedByTestCaseFactory(PdslFrameworkSpecificationParser.TestSpecificationIsProcessedByTestCaseFactoryContext ctx) {
        TestCaseFactory testCaseFactory = new ParentForEachChildTestCaseFactory();
        testCases = testCaseFactory.processTestSpecification(testSpecification);
    }

    @Override
    public void exitTestSpecificationIsProcessedByTestCaseFactory(PdslFrameworkSpecificationParser.TestSpecificationIsProcessedByTestCaseFactoryContext ctx) { }

    @Override
    public void enterTestCaseIsProduced(PdslFrameworkSpecificationParser.TestCaseIsProducedContext ctx) {
        assertThat(testCases).isNotEmpty();
    }

    @Override
    public void exitTestCaseIsProduced(PdslFrameworkSpecificationParser.TestCaseIsProducedContext ctx) { }

    @Override
    public void enterTestCaseHasUniqueId(PdslFrameworkSpecificationParser.TestCaseHasUniqueIdContext ctx) {
        for(TestCase testCase : testCases) {
            assertThat(testCase.getTestCaseId()).isNotNull();
        }

    }

    @Override
    public void exitTestCaseHasUniqueId(PdslFrameworkSpecificationParser.TestCaseHasUniqueIdContext ctx) { }

    @Override
    public void enterTestCaseHasTitle(PdslFrameworkSpecificationParser.TestCaseHasTitleContext ctx) {
        for (TestCase testCase : testCases) {
            assertThat(testCase.getTestTitle()).isNotNull();
            assertThat(testCase.getTestTitle()).isNotEmpty();
        }
    }

    @Override
    public void exitTestCaseHasTitle(PdslFrameworkSpecificationParser.TestCaseHasTitleContext ctx) { }

    @Override
    public void enterTestCaseHasProperTestBody(PdslFrameworkSpecificationParser.TestCaseHasProperTestBodyContext ctx) {
        // TODO: have mapping to resource
    }

    @Override
    public void exitTestCaseHasProperTestBody(PdslFrameworkSpecificationParser.TestCaseHasProperTestBodyContext ctx) {

    }

    @Override
    public void enterPolymorphicDslTestExecutor(PdslFrameworkSpecificationParser.PolymorphicDslTestExecutorContext ctx) {

    }

    @Override
    public void exitPolymorphicDslTestExecutor(PdslFrameworkSpecificationParser.PolymorphicDslTestExecutorContext ctx) {

    }

    private void compareTestRunResults(TestRunResults a, TestRunResults b) {
        assertThat(a.failingTestTotal()).isEqualTo(b.failingTestTotal());
        assertThat(a.passingPhraseTotal()).isEqualTo(b.passingPhraseTotal());
        assertThat(a.passingTestTotal()).isEqualTo(b.passingTestTotal());
        assertThat(a.totalFilteredDuplicateTests()).isEqualTo(b.totalFilteredDuplicateTests());
        assertThat(a.totalPhrases()).isEqualTo(b.totalPhrases());
    }
    @Override
    public void enterPdslCanProcessAllPhrases(PdslFrameworkSpecificationParser.PdslCanProcessAllPhrasesContext ctx) {
        grammarListener = new AllGrammarsParserBaseListener();
        subGrammarListener = new AllGrammarsParserBaseListener();
    }

    @Override
    public void exitPdslCanProcessAllPhrases(PdslFrameworkSpecificationParser.PdslCanProcessAllPhrasesContext ctx) { }

    @Override
    public void enterTestCaseIsProcessed(PdslFrameworkSpecificationParser.TestCaseIsProcessedContext ctx) {
        GherkinTestExecutor gherkinTestExecutor = new GherkinTestExecutor(AllGrammarsParser.class, AllGrammarsLexer.class,
                AllGrammarsParser.class, AllGrammarsLexer.class);
        // Empty listener that passes everything
        // Check each implementation for run tests (should be side effect free)
        TestRunResults results1 = gherkinTestExecutor.runTests(testCases, grammarListener, subGrammarListener);
        TestRunResults results2 = gherkinTestExecutor.runTests(testCases, grammarListener);
        TestRunResults results3 = gherkinTestExecutor.processFilesAndRunTests(resourcePaths, grammarListener);
        TestRunResults results4 = gherkinTestExecutor.processFilesAndRunTests(resourcePaths, grammarListener, subGrammarListener);
        // Results should be identical for all runs
        compareTestRunResults(results1, results2);
        compareTestRunResults(results2, results3);
        compareTestRunResults(results3, results4);
        results = results1;

    }

    @Override
    public void exitTestCaseIsProcessed(PdslFrameworkSpecificationParser.TestCaseIsProcessedContext ctx) {

    }

    @Override
    public void enterTestRunResultProduced(PdslFrameworkSpecificationParser.TestRunResultProducedContext ctx) {
        assertThat(results).isNotNull();;
    }

    @Override
    public void exitTestRunResultProduced(PdslFrameworkSpecificationParser.TestRunResultProducedContext ctx) { }

    @Override
    public void enterPassingTestTotal(PdslFrameworkSpecificationParser.PassingTestTotalContext ctx) {
        // TODO: Rewrite parser rule to provide INT context
    }

    @Override
    public void exitPassingTestTotal(PdslFrameworkSpecificationParser.PassingTestTotalContext ctx) {

    }

    @Override
    public void enterFailingTestTotal(PdslFrameworkSpecificationParser.FailingTestTotalContext ctx) {
        // TODO: Rewrite parser rule to provide INT context
    }

    @Override
    public void exitFailingTestTotal(PdslFrameworkSpecificationParser.FailingTestTotalContext ctx) {

    }

    @Override
    public void enterPassingPhraseTotal(PdslFrameworkSpecificationParser.PassingPhraseTotalContext ctx) {
        // TODO: Rewrite parser rule to provide INT context
    }

    @Override
    public void exitPassingPhraseTotal(PdslFrameworkSpecificationParser.PassingPhraseTotalContext ctx) {

    }

    @Override
    public void enterTotalPhrases(PdslFrameworkSpecificationParser.TotalPhrasesContext ctx) {
        // TODO: Rewrite parser rule to provide INT context
    }

    @Override
    public void exitTotalPhrases(PdslFrameworkSpecificationParser.TotalPhrasesContext ctx) {

    }

    @Override
    public void enterDuplicateTestTotal(PdslFrameworkSpecificationParser.DuplicateTestTotalContext ctx) {
        // TODO: Rewrite parser rule to provide INT context
    }

    @Override
    public void exitDuplicateTestTotal(PdslFrameworkSpecificationParser.DuplicateTestTotalContext ctx) {

    }

    @Override
    public void enterPolymorphicDslAllRules(PdslFrameworkSpecificationParser.PolymorphicDslAllRulesContext ctx) {

    }

    @Override
    public void exitPolymorphicDslAllRules(PdslFrameworkSpecificationParser.PolymorphicDslAllRulesContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {

    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }
}
