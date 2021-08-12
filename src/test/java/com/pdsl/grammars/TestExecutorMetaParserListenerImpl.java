package com.pdsl.grammars;

import com.google.common.base.Preconditions;
import com.pdsl.executors.DefaultPolymorphicDslTestExecutor;
import com.pdsl.executors.TraceableTestRunExecutor;
import com.pdsl.gherkin.DefaultGherkinTestSpecificationFactory;
import com.pdsl.gherkin.executors.GherkinTestExecutor;
import com.pdsl.reports.DefaultTestResult;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.reports.TestResult;
import com.pdsl.specifications.LineDelimitedTestSpecificationFactory;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.TestSpecificationFactory;
import com.pdsl.testcases.PreorderTestCaseFactory;
import com.pdsl.testcases.TestCase;
import com.pdsl.testcases.TestCaseFactory;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static com.google.common.truth.Truth.assertThat;

public class TestExecutorMetaParserListenerImpl implements TestExecutorMetaParserListener {

    private PdslHelper.ExecutorType executorType;
    private PdslHelper.ListenerType grammarListener;
    private PdslHelper.ListenerType subgrammarListener;
    private Set<URL> urls = new HashSet<>();
    private TraceableTestRunExecutor executor;
    private PdslHelper.SupportedGrammars grammar;
    private PdslHelper.SupportedGrammars subgrammar;
    private Optional<MetadataTestRunResults> results = Optional.empty();
    private Optional<Collection<TestSpecification>> testSpecification = Optional.empty();
    private TestCaseFactory testCaseFactory = new PreorderTestCaseFactory();
    private PdslHelper.Factories factoryType;
    private Collection<TestCase> testCases;
    private final Logger logger = LoggerFactory.getLogger(TestExecutorMetaParserListenerImpl.class);

    private String extractQuotedText(String str) {
        return str.strip().replaceAll("\"", "");
    }

    @Override
    public void enterPolymorphicDslAllRules(TestExecutorMetaParser.PolymorphicDslAllRulesContext ctx) { }

    @Override
    public void exitPolymorphicDslAllRules(TestExecutorMetaParser.PolymorphicDslAllRulesContext ctx) { }

    @Override
    public void enterGivenTheTestExecutorIsSpecified(TestExecutorMetaParser.GivenTheTestExecutorIsSpecifiedContext ctx) {
        String testExecutor = extractQuotedText(ctx.textInDoubleQuotesEnd().getText());
        executorType = PdslHelper.ExecutorType.valueOf(PdslHelper.convertToEnumCase(testExecutor));
    }

    @Override
    public void exitGivenTheTestExecutorIsSpecified(TestExecutorMetaParser.GivenTheTestExecutorIsSpecifiedContext ctx) { }

    @Override
    public void enterGivenTheGrammarListener(TestExecutorMetaParser.GivenTheGrammarListenerContext ctx) {
        String listener = extractQuotedText(ctx.textInDoubleQuotesEnd().getText());
        grammarListener = PdslHelper.ListenerType.valueOf(PdslHelper.convertToEnumCase(listener));
    }

    @Override
    public void exitGivenTheGrammarListener(TestExecutorMetaParser.GivenTheGrammarListenerContext ctx) { }

    @Override
    public void enterGivenTheSubgrammarListener(TestExecutorMetaParser.GivenTheSubgrammarListenerContext ctx) {
        String listener = extractQuotedText(ctx.textInDoubleQuotesEnd().getText());
        subgrammarListener = PdslHelper.ListenerType.valueOf(PdslHelper.convertToEnumCase(listener));
    }

    @Override
    public void exitGivenTheSubgrammarListener(TestExecutorMetaParser.GivenTheSubgrammarListenerContext ctx) { }

    @Override
    public void enterWhenTheGherkinTestExecutorRunsWithTagExpression(TestExecutorMetaParser.WhenTheGherkinTestExecutorRunsWithTagExpressionContext ctx) {
        Preconditions.checkNotNull(grammar);
        if (subgrammarListener == null) {
            subgrammarListener = grammarListener;
        }
        if (subgrammar == null) {
            subgrammar = grammar;
        }
        GherkinTestExecutor gherkinTestExecutor = new GherkinTestExecutor(new DefaultPolymorphicDslPhraseFilter(subgrammar.getParserClass(), subgrammar.getLexerClass()));
        executor = gherkinTestExecutor;
        String tagExpression = PdslHelper.extractStringInQuotes(ctx.textInDoubleQuotesEnd().getText());
        MetadataTestRunResults runResults = ((GherkinTestExecutor)executor).runTestsWithMetadata(urls, tagExpression, subgrammarListener.getListener(), "Integration");
        results = Optional.of(runResults);
    }

    @Override
    public void exitWhenTheGherkinTestExecutorRunsWithTagExpression(TestExecutorMetaParser.WhenTheGherkinTestExecutorRunsWithTagExpressionContext ctx) {}

    @Override
    public void enterGivenTheGrammarPhraseFilter(TestExecutorMetaParser.GivenTheGrammarPhraseFilterContext ctx) {
        String phraseFilter = PdslHelper.extractStringInQuotes(ctx.textInDoubleQuotesEnd().getText());
        String enumName = PdslHelper.convertToEnumCase(phraseFilter);
        grammar = PdslHelper.SupportedGrammars.valueOf(enumName);

    }

    @Override
    public void exitGivenTheGrammarPhraseFilter(TestExecutorMetaParser.GivenTheGrammarPhraseFilterContext ctx) {

    }

    @Override
    public void enterGivenTheSubgrammarPhraseFilter(TestExecutorMetaParser.GivenTheSubgrammarPhraseFilterContext ctx) {
        String phraseFilter = PdslHelper.extractStringInQuotes(ctx.textInDoubleQuotesEnd().getText());
        String enumName = PdslHelper.convertToEnumCase(phraseFilter);
        subgrammar = PdslHelper.SupportedGrammars.valueOf(enumName);
    }

    @Override
    public void exitGivenTheSubgrammarPhraseFilter(TestExecutorMetaParser.GivenTheSubgrammarPhraseFilterContext ctx) {

    }


    @Override
    public void enterGivenTheTestResource(TestExecutorMetaParser.GivenTheTestResourceContext ctx) {
        throw new TestFrameworkException("Not implemented exception", new IllegalArgumentException("Do not support URL test resources"));
    }

    @Override
    public void exitGivenTheTestResource(TestExecutorMetaParser.GivenTheTestResourceContext ctx) {

    }

    @Override
    public void enterGivenTheRawResource(TestExecutorMetaParser.GivenTheRawResourceContext ctx) {
        String resourceBody = ctx.docstring().getText().strip().replaceAll("\"\"\"", "");
        // Create a temporary file
        try {
            Path tempFile = Files.createTempFile("pdsl" + UUID.randomUUID(), ".tmp.txt");
            Files.writeString(tempFile, resourceBody);
            urls.add(tempFile.toUri().toURL());
        } catch (IOException e) {
            throw new TestFrameworkException("Could not create a temporary file to process raw input!", e);
        }
    }

    @Override
    public void exitGivenTheRawResource(TestExecutorMetaParser.GivenTheRawResourceContext ctx) {

    }

    @Override
    public void enterGherkinStepKeyword(TestExecutorMetaParser.GherkinStepKeywordContext ctx) {

    }

    @Override
    public void exitGherkinStepKeyword(TestExecutorMetaParser.GherkinStepKeywordContext ctx) {

    }

    @Override
    public void enterIntegerValue(TestExecutorMetaParser.IntegerValueContext ctx) {

    }

    @Override
    public void exitIntegerValue(TestExecutorMetaParser.IntegerValueContext ctx) {

    }

    @Override
    public void enterTextInDoubleQuotes(TestExecutorMetaParser.TextInDoubleQuotesContext ctx) {

    }

    @Override
    public void exitTextInDoubleQuotes(TestExecutorMetaParser.TextInDoubleQuotesContext ctx) {

    }

    @Override
    public void enterDocstring(TestExecutorMetaParser.DocstringContext ctx) {

    }

    @Override
    public void exitDocstring(TestExecutorMetaParser.DocstringContext ctx) {

    }

    @Override
    public void enterTextInDoubleQuotesEnd(TestExecutorMetaParser.TextInDoubleQuotesEndContext ctx) {

    }

    @Override
    public void exitTextInDoubleQuotesEnd(TestExecutorMetaParser.TextInDoubleQuotesEndContext ctx) {

    }


    @Override
    public void enterWhenTheTestResourceIsProcessedByFactory(TestExecutorMetaParser.WhenTheTestResourceIsProcessedByFactoryContext ctx) {
        assertThat(grammar).isNotNull();
        if (subgrammar == null) {
            subgrammar = grammar;
        }
        TestSpecificationFactory factory;
        PolymorphicDslPhraseFilter phraseFilter = new DefaultPolymorphicDslPhraseFilter(grammar.getParserClass(), grammar.getLexerClass());
        switch (factoryType) {
            case GHERKIN_TEST_SPECIFICATION_FACTORY:
                factory = new DefaultGherkinTestSpecificationFactory(phraseFilter);
                break;
            case LINE_DELIMITED_TEST_SPECIFICATION_FACTORY:
                factory = new LineDelimitedTestSpecificationFactory(phraseFilter);
                break;
            default:
                throw new TestFrameworkException("Could not create Test Specification Factory!", new IllegalArgumentException("No implementation for factory " + factoryType.name()));
        }
        assertThat(urls).isNotNull();
        assertThat(urls).isNotEmpty();
        testSpecification = factory.getTestSpecifications(urls);
        assertThat(testSpecification.isPresent()).isTrue();
        testCases = testCaseFactory.processTestSpecification(testSpecification.get());
    }

    @Override
    public void exitWhenTheTestResourceIsProcessedByFactory(TestExecutorMetaParser.WhenTheTestResourceIsProcessedByFactoryContext ctx) {

    }

    @Override
    public void enterTestSpecificationIsProduced(TestExecutorMetaParser.TestSpecificationIsProducedContext ctx) {
        if(ctx.getText().toLowerCase().contains(" not ")) {
            assertThat(testSpecification.isEmpty()).isTrue();
        } else {
            assertThat(testSpecification.isPresent()).isTrue();
        }

    }

    @Override
    public void exitTestSpecificationIsProduced(TestExecutorMetaParser.TestSpecificationIsProducedContext ctx) {

    }

    @Override
    public void enterTestSpecificationIsProcessedByTestCaseFactory(TestExecutorMetaParser.TestSpecificationIsProcessedByTestCaseFactoryContext ctx) {
       assertThat(testSpecification.isPresent()).isTrue();
        testCases = testCaseFactory.processTestSpecification(testSpecification.get());

    }

    @Override
    public void exitTestSpecificationIsProcessedByTestCaseFactory(TestExecutorMetaParser.TestSpecificationIsProcessedByTestCaseFactoryContext ctx) {

    }

    @Override
    public void enterPolymorphicDslTestExecutor(TestExecutorMetaParser.PolymorphicDslTestExecutorContext ctx) {
        System.out.println("I am at\n" + ctx.getText());
        assert(false);
    }

    @Override
    public void exitPolymorphicDslTestExecutor(TestExecutorMetaParser.PolymorphicDslTestExecutorContext ctx) {

    }

    @Override
    public void enterTestRunResultProduced(TestExecutorMetaParser.TestRunResultProducedContext ctx) {
        assertThat(results.isPresent()).isTrue();
    }

    @Override
    public void exitTestRunResultProduced(TestExecutorMetaParser.TestRunResultProducedContext ctx) {

    }

    @Override
    public void enterWhenTheTestCaseIsProcessedByAnyPdslTestExecutor(TestExecutorMetaParser.WhenTheTestCaseIsProcessedByAnyPdslTestExecutorContext ctx) {
        if (executor == null) {
            executor = new DefaultPolymorphicDslTestExecutor();
        }
        results = Optional.of(executor.runTestsWithMetadata(testCases, grammarListener.getListener(), "Integration"));
    }

    @Override
    public void exitWhenTheTestCaseIsProcessedByAnyPdslTestExecutor(TestExecutorMetaParser.WhenTheTestCaseIsProcessedByAnyPdslTestExecutorContext ctx) {

    }

    @Override
    public void enterThenTheTestRunResultsHaveSpecifiedPassingTests(TestExecutorMetaParser.ThenTheTestRunResultsHaveSpecifiedPassingTestsContext ctx) {
        assertThat(results.isPresent()).isTrue();
        for (TestResult metadata : results.get().getTestResults()) {
            if (metadata.getFailingPhrase().isPresent()) {
                logger.error("Test Case ID: %s %n\tException: %s", metadata.getTestCaseTitle(), metadata.getFailureReason().orElseThrow());
            }
        }
        assertThat(results.get().passingTestTotal()).isEqualTo(Integer.parseInt(PdslHelper.extractStringInQuotes(ctx.integerValue().getText())));

    }

    @Override
    public void exitThenTheTestRunResultsHaveSpecifiedPassingTests(TestExecutorMetaParser.ThenTheTestRunResultsHaveSpecifiedPassingTestsContext ctx) {

    }

    @Override
    public void enterThenTheTestRunResultsHaveSpecifiedFailingTests(TestExecutorMetaParser.ThenTheTestRunResultsHaveSpecifiedFailingTestsContext ctx) {
        assertThat(results.isPresent()).isTrue();
        assertThat(results.get().failingTestTotal()).isEqualTo(Integer.parseInt(PdslHelper.extractStringInQuotes(ctx.getText())));
    }

    @Override
    public void exitThenTheTestRunResultsHaveSpecifiedFailingTests(TestExecutorMetaParser.ThenTheTestRunResultsHaveSpecifiedFailingTestsContext ctx) {

    }

    @Override
    public void enterGivenSpecificTestSpecificationFactory(TestExecutorMetaParser.GivenSpecificTestSpecificationFactoryContext ctx) {
        factoryType = PdslHelper.Factories.valueOf(
                PdslHelper.convertToEnumCase(PdslHelper.extractStringInQuotes(ctx.textInDoubleQuotesEnd().getText())));
    }

    @Override
    public void exitGivenSpecificTestSpecificationFactory(TestExecutorMetaParser.GivenSpecificTestSpecificationFactoryContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode node) {

    }

    @Override
    public void visitErrorNode(ErrorNode node) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {

    }

    private enum ExecutorType {
        DEFAULT_PDSL_TEST_EXECUTOR,
        DEFAULT_GHERKIN_TEST_EXECUTOR;
    }
}
