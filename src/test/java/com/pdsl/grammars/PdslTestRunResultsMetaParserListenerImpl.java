package com.pdsl.grammars;

import com.pdsl.executors.DefaultPolymorphicDslTestExecutor;
import com.pdsl.executors.PolymorphicDslTestExecutor;
import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.reports.TestMetadata;
import com.pdsl.specifications.LineDelimitedTestSpecificationFactory;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.TestSpecificationFactory;
import com.pdsl.testcases.SingleTestOutputPreorderTestCaseFactory;
import com.pdsl.testcases.TestCase;
import com.pdsl.testcases.TestCaseFactory;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

public class PdslTestRunResultsMetaParserListenerImpl implements PdslTestRunResultsMetaParserListener {

    private Optional<PolymorphicDslTestRunResults> testRunResults = Optional.empty();
    private Optional<TestMetadata> testMetadata = Optional.empty();
    private Optional<Collection<TestCase>> testCaseCollection = Optional.empty();
    private ParseTreeListener grammarListener = PdslHelper.ListenerType.ARITHMETIC.getListener();
    private Set<URL> urlSet = new HashSet<>();
    private Path path;

    private static final PolymorphicDslTestExecutor testExecutor = new DefaultPolymorphicDslTestExecutor();
    private static final PolymorphicDslPhraseFilter phraseFilter = new DefaultPolymorphicDslPhraseFilter<SimpleArithmeticParser, SimpleArithmeticLexer>
            ( SimpleArithmeticParser.class,
                    SimpleArithmeticLexer.class);
    private static final TestSpecificationFactory testSpecificationFactory = new LineDelimitedTestSpecificationFactory(phraseFilter);
    private static final TestCaseFactory testCaseFactory = new SingleTestOutputPreorderTestCaseFactory();

    public void beforeEach() {
        testRunResults = Optional.empty();
        testMetadata = Optional.empty();
        testCaseCollection = Optional.empty();
        grammarListener = PdslHelper.ListenerType.ARITHMETIC.getListener();
        urlSet = new HashSet<>();
    }

    @Override
    public void enterPolymorphicDslAllRules(PdslTestRunResultsMetaParser.PolymorphicDslAllRulesContext ctx) {
    }

    @Override
    public void exitPolymorphicDslAllRules(PdslTestRunResultsMetaParser.PolymorphicDslAllRulesContext ctx) {
    }

    @Override
    public void enterWhenTheTestResourceIsProcessedByFactory(PdslTestRunResultsMetaParser.WhenTheTestResourceIsProcessedByFactoryContext ctx) {
        throw new IllegalArgumentException("Not implemented");
    }

    @Override
    public void exitWhenTheTestResourceIsProcessedByFactory(PdslTestRunResultsMetaParser.WhenTheTestResourceIsProcessedByFactoryContext ctx) {
        throw new IllegalArgumentException("Not implemented");
    }

    @Override
    public void enterTestSpecificationIsProduced(PdslTestRunResultsMetaParser.TestSpecificationIsProducedContext ctx) {
        throw new IllegalArgumentException("Not implemented");
    }

    @Override
    public void exitTestSpecificationIsProduced(PdslTestRunResultsMetaParser.TestSpecificationIsProducedContext ctx) {
        throw new IllegalArgumentException("Not implemented");
    }

    @Override
    public void enterTestSpecificationIsProcessedByTestCaseFactory(PdslTestRunResultsMetaParser.TestSpecificationIsProcessedByTestCaseFactoryContext ctx) {
        throw new IllegalArgumentException("Not implemented");
    }

    @Override
    public void exitTestSpecificationIsProcessedByTestCaseFactory(PdslTestRunResultsMetaParser.TestSpecificationIsProcessedByTestCaseFactoryContext ctx) {
        throw new IllegalArgumentException("Not implemented");
    }

    @Override
    public void enterPolymorphicDslTestExecutor(PdslTestRunResultsMetaParser.PolymorphicDslTestExecutorContext ctx) {
        throw new IllegalArgumentException("Not implemented");
    }

    @Override
    public void exitPolymorphicDslTestExecutor(PdslTestRunResultsMetaParser.PolymorphicDslTestExecutorContext ctx) {
        throw new IllegalArgumentException("Not implemented");
    }

    @Override
    public void enterTestRunResultProduced(PdslTestRunResultsMetaParser.TestRunResultProducedContext ctx) {
        assertThat(testRunResults.isPresent()).isTrue();
    }

    @Override
    public void exitTestRunResultProduced(PdslTestRunResultsMetaParser.TestRunResultProducedContext ctx) {
    }

    @Override
    public void enterWhenTheTestCaseIsProcessedByAnyPdslTestExecutor(PdslTestRunResultsMetaParser.WhenTheTestCaseIsProcessedByAnyPdslTestExecutorContext ctx) {
        assertThat(urlSet).isNotNull();
        assertThat(urlSet).isNotEmpty();
        Optional<Collection<TestSpecification>> testSpecification = testSpecificationFactory.getTestSpecifications(urlSet);
        assertThat(testSpecification.isPresent()).isTrue();
        testCaseCollection = Optional.of(testCaseFactory.processTestSpecification(testSpecification.get()));
        assertThat(testCaseCollection.isPresent()).isTrue();
        assertThat(testCaseCollection.get()).isNotNull();
        assertThat(testCaseCollection.get()).isNotEmpty();
        assertThat(grammarListener).isNotNull();
        testRunResults = Optional.of(testExecutor.runTests(testCaseCollection.get(), grammarListener));
    }

    @Override
    public void exitWhenTheTestCaseIsProcessedByAnyPdslTestExecutor(PdslTestRunResultsMetaParser.WhenTheTestCaseIsProcessedByAnyPdslTestExecutorContext ctx) {
    }

    @Override
    public void enterIntegerValue(PdslTestRunResultsMetaParser.IntegerValueContext ctx) {
    }

    @Override
    public void exitIntegerValue(PdslTestRunResultsMetaParser.IntegerValueContext ctx) {
    }

    @Override
    public void enterThenTheTestRunResultsHaveSpecifiedPassingTests(PdslTestRunResultsMetaParser.ThenTheTestRunResultsHaveSpecifiedPassingTestsContext ctx) {
        assertThat(testRunResults.isPresent()).isTrue();
        int actual = Integer.parseInt(PdslHelper.extractStringInQuotes(ctx.integerValue().getText()));
        assertThat(testRunResults.get().passingTestTotal()).isGreaterThan(actual);
    }

    @Override
    public void exitThenTheTestRunResultsHaveSpecifiedPassingTests(PdslTestRunResultsMetaParser.ThenTheTestRunResultsHaveSpecifiedPassingTestsContext ctx) {
    }

    @Override
    public void enterThenTheTestRunResultsHaveSpecifiedFailingTests(PdslTestRunResultsMetaParser.ThenTheTestRunResultsHaveSpecifiedFailingTestsContext ctx) {
        assertThat(testRunResults.isPresent()).isTrue();
        int actual = Integer.parseInt(PdslHelper.extractStringInQuotes(ctx.integerValue().getText()));
        assertThat(testRunResults.get().failingTestTotal()).isGreaterThan(actual);
    }

    @Override
    public void exitThenTheTestRunResultsHaveSpecifiedFailingTests(PdslTestRunResultsMetaParser.ThenTheTestRunResultsHaveSpecifiedFailingTestsContext ctx) {
    }

    @Override
    public void enterGivenTheTestResource(PdslTestRunResultsMetaParser.GivenTheTestResourceContext ctx) {
        urlSet.add(getClass().getClassLoader().getResource(PdslHelper.extractStringInQuotes(ctx.textInDoubleQuotes().getText())));
    }

    @Override
    public void exitGivenTheTestResource(PdslTestRunResultsMetaParser.GivenTheTestResourceContext ctx) {
    }

    @Override
    public void enterGivenTheRawResource(PdslTestRunResultsMetaParser.GivenTheRawResourceContext ctx) {
        urlSet = new HashSet<>();
        Path path = PdslHelper.processRawResourceFromDocstring(ctx.docstring().getText().replaceAll("\"\"\"", "").strip());
        try {
            urlSet.add(path.toUri().toURL());
        } catch (MalformedURLException e) {
            throw new TestFrameworkException("Could not make a temporary file for testing", e);
        }
    }

    @Override
    public void exitGivenTheRawResource(PdslTestRunResultsMetaParser.GivenTheRawResourceContext ctx) {
    }

    @Override
    public void enterGherkinStepKeyword(PdslTestRunResultsMetaParser.GherkinStepKeywordContext ctx) {
    }

    @Override
    public void exitGherkinStepKeyword(PdslTestRunResultsMetaParser.GherkinStepKeywordContext ctx) {
    }

    @Override
    public void enterTextInDoubleQuotes(PdslTestRunResultsMetaParser.TextInDoubleQuotesContext ctx) {
    }

    @Override
    public void exitTextInDoubleQuotes(PdslTestRunResultsMetaParser.TextInDoubleQuotesContext ctx) {
    }

    @Override
    public void enterDocstring(PdslTestRunResultsMetaParser.DocstringContext ctx) {
    }

    @Override
    public void exitDocstring(PdslTestRunResultsMetaParser.DocstringContext ctx) {
    }

    @Override
    public void enterTextInDoubleQuotesEnd(PdslTestRunResultsMetaParser.TextInDoubleQuotesEndContext ctx) {
    }

    @Override
    public void exitTextInDoubleQuotesEnd(PdslTestRunResultsMetaParser.TextInDoubleQuotesEndContext ctx) {
    }

    @Override
    public void enterGivenAnotherTestResource(PdslTestRunResultsMetaParser.GivenAnotherTestResourceContext ctx) {
        Path path = PdslHelper.processRawResourceFromDocstring(ctx.docstring().getText().replaceAll("\"\"\"", "").strip());
        try {
            urlSet.add(path.toUri().toURL());
        } catch (MalformedURLException e) {
            throw new TestFrameworkException("Could not create a temporary test resource!", e);
        }
    }

    @Override
    public void exitGivenAnotherTestResource(PdslTestRunResultsMetaParser.GivenAnotherTestResourceContext ctx) {

    }

    @Override
    public void enterConvertTestResourcesToCollectionWithSingleTestCase(PdslTestRunResultsMetaParser.ConvertTestResourcesToCollectionWithSingleTestCaseContext ctx) {
        assertThat(urlSet).isNotNull();
        ;
        assertThat(urlSet).isNotEmpty();

        SingleTestOutputPreorderTestCaseFactory factory = new SingleTestOutputPreorderTestCaseFactory();
        Optional<Collection<TestSpecification>> testSpecification = testSpecificationFactory.getTestSpecifications(urlSet);
        assertThat(testSpecification.isPresent()).isTrue();
        Collection<TestCase> testCases = factory.processTestSpecification(testSpecification.get());
        testCaseCollection = Optional.of(testCases);
    }

    @Override
    public void exitConvertTestResourcesToCollectionWithSingleTestCase(PdslTestRunResultsMetaParser.ConvertTestResourcesToCollectionWithSingleTestCaseContext ctx) {
    }

    @Override
    public void enterThenTestCaseCollectionHasSpecifiedTestCases(PdslTestRunResultsMetaParser.ThenTestCaseCollectionHasSpecifiedTestCasesContext ctx) {
        assertThat(testCaseCollection.isPresent()).isTrue();
        int expectedValue = Integer.parseInt(ctx.integerValue().getText());
        assertThat(testCaseCollection.get().size()).isEqualTo(expectedValue);
    }

    @Override
    public void exitThenTestCaseCollectionHasSpecifiedTestCases(PdslTestRunResultsMetaParser.ThenTestCaseCollectionHasSpecifiedTestCasesContext ctx) {
    }

    @Override
    public void enterGivenTheSpecifiedGrammarParseTreeListener(PdslTestRunResultsMetaParser.GivenTheSpecifiedGrammarParseTreeListenerContext ctx) {
        String grammarName = PdslHelper.convertToEnumCase(PdslHelper.extractStringInQuotes(ctx.textInDoubleQuotesEnd().getText()));
        PdslHelper.ListenerType listenerType = PdslHelper.ListenerType.valueOf(grammarName);
        grammarListener = listenerType.getListener();
    }

    @Override
    public void exitGivenTheSpecifiedGrammarParseTreeListener(PdslTestRunResultsMetaParser.GivenTheSpecifiedGrammarParseTreeListenerContext ctx) {
    }

    @Override
    public void enterThenSingleTestCaseIsProduced(PdslTestRunResultsMetaParser.ThenSingleTestCaseIsProducedContext ctx) {
        enterConvertTestResourcesToCollectionWithSingleTestCase(null);
        assertThat(testCaseCollection.isPresent()).isTrue();
        assertThat(testCaseCollection.get().size()).isEqualTo(urlSet.size());
    }

    @Override
    public void exitThenSingleTestCaseIsProduced(PdslTestRunResultsMetaParser.ThenSingleTestCaseIsProducedContext ctx) {
    }

    @Override
    public void enterThenTheTestRunResultHasSpecifiedFilteredDuplicateTests(PdslTestRunResultsMetaParser.ThenTheTestRunResultHasSpecifiedFilteredDuplicateTestsContext ctx) {
        assertThat(testRunResults.isPresent()).isTrue();
        int expected = Integer.parseInt(PdslHelper.extractStringInQuotes(ctx.integerValue().getText()));
        assertThat(testRunResults.get().totalFilteredDuplicateTests()).isGreaterThan(expected);
    }

    @Override
    public void exitThenTheTestRunResultHasSpecifiedFilteredDuplicateTests(PdslTestRunResultsMetaParser.ThenTheTestRunResultHasSpecifiedFilteredDuplicateTestsContext ctx) {
    }

    @Override
    public void enterThenTheTestRunResultHasSpecifiedPassingPhrases(PdslTestRunResultsMetaParser.ThenTheTestRunResultHasSpecifiedPassingPhrasesContext ctx) {
        assertThat(testRunResults.isPresent()).isTrue();
        int actual = Integer.parseInt(PdslHelper.extractStringInQuotes(ctx.integerValue().getText()));
        assertThat(testRunResults.get().passingPhraseTotal()).isGreaterThan(actual);
    }

    @Override
    public void exitThenTheTestRunResultHasSpecifiedPassingPhrases(PdslTestRunResultsMetaParser.ThenTheTestRunResultHasSpecifiedPassingPhrasesContext ctx) {
    }

    @Override
    public void enterThenTheTestRunResultHasSpecifiedTotalPhrases(PdslTestRunResultsMetaParser.ThenTheTestRunResultHasSpecifiedTotalPhrasesContext ctx) {
        assertThat(testRunResults.isPresent()).isTrue();
        int actual = Integer.parseInt(PdslHelper.extractStringInQuotes(ctx.integerValue().getText().split(" ")[0])); //TODO: Why does the rule match non-ints
        assertThat(testRunResults.get().totalPhrases()).isAtLeast(actual);
    }

    @Override
    public void exitThenTheTestRunResultHasSpecifiedTotalPhrases(PdslTestRunResultsMetaParser.ThenTheTestRunResultHasSpecifiedTotalPhrasesContext ctx) {
    }

    @Override
    public void enterWhenTheTestMetadataIsRetrievedFromTheTestRunResult(PdslTestRunResultsMetaParser.WhenTheTestMetadataIsRetrievedFromTheTestRunResultContext ctx) {
        assertThat(testRunResults.isPresent()).isTrue();
        assertThat(testRunResults.get().getTestResultMetadata().size()).isGreaterThan(0);
        testMetadata = Optional.of(testRunResults.get().getTestResultMetadata().get(0));
    }

    @Override
    public void exitWhenTheTestMetadataIsRetrievedFromTheTestRunResult(PdslTestRunResultsMetaParser.WhenTheTestMetadataIsRetrievedFromTheTestRunResultContext ctx) {
    }

    @Override
    public void enterWhenTheOnlyTestMetadataItemIsExamined(PdslTestRunResultsMetaParser.WhenTheOnlyTestMetadataItemIsExaminedContext ctx) {
        assertThat(testRunResults.isPresent()).isTrue();
        assertThat(testRunResults.get().getTestResultMetadata().size()).isGreaterThan(0);
        testMetadata = Optional.of(testRunResults.get().getTestResultMetadata().get(0));
    }

    @Override
    public void exitWhenTheOnlyTestMetadataItemIsExamined(PdslTestRunResultsMetaParser.WhenTheOnlyTestMetadataItemIsExaminedContext ctx) {
    }

    @Override
    public void enterThenTheTestMetadataHasAnExceptionExplainingWhyTheTestFailed(PdslTestRunResultsMetaParser.ThenTheTestMetadataHasAnExceptionExplainingWhyTheTestFailedContext ctx) {
        assertThat(testMetadata.isPresent()).isTrue();
        assertThat(testMetadata.get().getFailingPhrase().isPresent()).isTrue();
        assertThat(testMetadata.get().getFailureReason().isPresent()).isTrue();
    }

    @Override
    public void exitThenTheTestMetadataHasAnExceptionExplainingWhyTheTestFailed(PdslTestRunResultsMetaParser.ThenTheTestMetadataHasAnExceptionExplainingWhyTheTestFailedContext ctx) {

    }

    @Override
    public void enterThenTheTestMetadataHasThePhraseThatFailed(PdslTestRunResultsMetaParser.ThenTheTestMetadataHasThePhraseThatFailedContext ctx) {
        assertThat(testMetadata.isPresent()).isTrue();
        assertThat(testMetadata.get().getFailingPhrase().isPresent()).isTrue();
    }

    @Override
    public void exitThenTheTestMetadataHasThePhraseThatFailed(PdslTestRunResultsMetaParser.ThenTheTestMetadataHasThePhraseThatFailedContext ctx) {
    }

    @Override
    public void enterThenTheTestMetadataFailingPhraseIsSpecifiedText(PdslTestRunResultsMetaParser.ThenTheTestMetadataFailingPhraseIsSpecifiedTextContext ctx) {
        assertThat(testMetadata.isPresent()).isTrue();
        assertThat(testMetadata.get().getFailingPhrase().isPresent()).isTrue();
        String actual = PdslHelper.extractStringInQuotes(ctx.textInDoubleQuotesEnd().getText());
        assertThat(testMetadata.get().getFailingPhrase().get()).contains(actual);
    }

    @Override
    public void exitThenTheTestMetadataFailingPhraseIsSpecifiedText(PdslTestRunResultsMetaParser.ThenTheTestMetadataFailingPhraseIsSpecifiedTextContext ctx) {
    }

    @Override
    public void enterThenTheTestMetadataHasOneItemInIt(PdslTestRunResultsMetaParser.ThenTheTestMetadataHasOneItemInItContext ctx) {
        assertThat(testRunResults.isPresent()).isTrue();
        assertThat(testRunResults.get().getTestResultMetadata().size()).isEqualTo(1);
    }

    @Override
    public void exitThenTheTestMetadataHasOneItemInIt(PdslTestRunResultsMetaParser.ThenTheTestMetadataHasOneItemInItContext ctx) {
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
}
