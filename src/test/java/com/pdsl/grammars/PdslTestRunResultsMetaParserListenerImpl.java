package com.pdsl.grammars;

import com.pdsl.exceptions.PolymorphicDslFrameworkException;
import com.pdsl.executors.DefaultPolymorphicDslTestExecutor;
import com.pdsl.executors.TraceableTestRunExecutor;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.reports.TestResult;
import com.pdsl.gherkin.parser.GherkinCommonContextHelper;
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
import com.pdsl.grammars.PdslTestRunResultsMetaParser;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.*;

import static com.google.common.truth.Truth.assertThat;

public class PdslTestRunResultsMetaParserListenerImpl implements PdslTestRunResultsMetaParserListener {

    private final GherkinCommonContextHelper ctxHelper = new GherkinCommonContextHelper(PdslTestRunResultsMetaParser.VOCABULARY);
    private Optional<MetadataTestRunResults> testRunResults = Optional.empty();
    private Optional<TestResult> testMetadata = Optional.empty();
    private Optional<Collection<TestCase>> testCaseCollection = Optional.empty();
    private ParseTreeListener grammarListener = PdslHelper.ListenerType.ARITHMETIC.getListener();
    private Set<URI> urlSet = new HashSet<>();

    private static final TraceableTestRunExecutor testExecutor = new DefaultPolymorphicDslTestExecutor();
    private static final PolymorphicDslPhraseFilter phraseFilter = new DefaultPolymorphicDslPhraseFilter
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
        testRunResults = Optional.of(testExecutor.runTestsWithMetadata(testCaseCollection.get(), grammarListener, "Integration"));
    }

    @Override
    public void exitWhenTheTestCaseIsProcessedByAnyPdslTestExecutor(PdslTestRunResultsMetaParser.WhenTheTestCaseIsProcessedByAnyPdslTestExecutorContext ctx) {
    }


    @Override
    public void enterThenTheTestRunResultsHaveSpecifiedPassingTests(PdslTestRunResultsMetaParser.ThenTheTestRunResultsHaveSpecifiedPassingTestsContext ctx) {
        assertThat(testRunResults.isPresent()).isTrue();
        int actual = ctxHelper.extractInt(ctx);
        assertThat(testRunResults.get().passingTestTotal()).isGreaterThan(actual);
    }

    @Override
    public void exitThenTheTestRunResultsHaveSpecifiedPassingTests(PdslTestRunResultsMetaParser.ThenTheTestRunResultsHaveSpecifiedPassingTestsContext ctx) {
    }

    @Override
    public void enterThenTheTestRunResultsHaveSpecifiedFailingTests(PdslTestRunResultsMetaParser.ThenTheTestRunResultsHaveSpecifiedFailingTestsContext ctx) {
        assertThat(testRunResults.isPresent()).isTrue();
        int actual = ctxHelper.extractInt(ctx);
        assertThat(testRunResults.get().failingTestTotal()).isGreaterThan(actual);
    }

    @Override
    public void exitThenTheTestRunResultsHaveSpecifiedFailingTests(PdslTestRunResultsMetaParser.ThenTheTestRunResultsHaveSpecifiedFailingTestsContext ctx) {
    }

    @Override
    public void enterGivenTheTestResource(PdslTestRunResultsMetaParser.GivenTheTestResourceContext ctx) {
        try {
            urlSet.add(getClass().getClassLoader().getResource(ctxHelper.extractTextInQuotes(ctx)).toURI());
        } catch (URISyntaxException e) {
            throw new PolymorphicDslFrameworkException("Could not add resource due to issue with URI",e);
        }
    }

    @Override
    public void exitGivenTheTestResource(PdslTestRunResultsMetaParser.GivenTheTestResourceContext ctx) {
    }

    @Override
    public void enterGivenTheRawResource(PdslTestRunResultsMetaParser.GivenTheRawResourceContext ctx) {
        urlSet = new HashSet<>();
        Path path = PdslHelper.processRawResourceFromDocstring(ctxHelper.extractDocstring(ctx));
        urlSet.add(path.toUri());
    }

    @Override
    public void exitGivenTheRawResource(PdslTestRunResultsMetaParser.GivenTheRawResourceContext ctx) {
    }


    @Override
    public void enterDocstring(PdslTestRunResultsMetaParser.DocstringContext ctx) {
    }

    @Override
    public void exitDocstring(PdslTestRunResultsMetaParser.DocstringContext ctx) {
    }
    @Override
    public void enterGivenAnotherTestResource(PdslTestRunResultsMetaParser.GivenAnotherTestResourceContext ctx) {
        Path path = PdslHelper.processRawResourceFromDocstring(ctxHelper.extractDocstring(ctx));
        urlSet.add(path.toUri());
    }

    @Override
    public void exitGivenAnotherTestResource(PdslTestRunResultsMetaParser.GivenAnotherTestResourceContext ctx) {

    }

    @Override
    public void enterConvertTestResourcesToCollectionWithSingleTestCase(PdslTestRunResultsMetaParser.ConvertTestResourcesToCollectionWithSingleTestCaseContext ctx) {

            assertThat(urlSet).isNotNull();
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
        int expectedValue = ctxHelper.extractInt(ctx);
        assertThat(testCaseCollection.get().size()).isEqualTo(-1);
    }

    @Override
    public void exitThenTestCaseCollectionHasSpecifiedTestCases(PdslTestRunResultsMetaParser.ThenTestCaseCollectionHasSpecifiedTestCasesContext ctx) {
    }

    @Override
    public void enterGivenTheSpecifiedGrammarParseTreeListener(PdslTestRunResultsMetaParser.GivenTheSpecifiedGrammarParseTreeListenerContext ctx) {
        String grammarName = PdslHelper.convertToEnumCase(ctxHelper.extractTextInQuotes(ctx));
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
        int expected = ctxHelper.extractInt(ctx);
        assertThat(testRunResults.get().totalFilteredDuplicateTests()).isGreaterThan(expected);
    }

    @Override
    public void exitThenTheTestRunResultHasSpecifiedFilteredDuplicateTests(PdslTestRunResultsMetaParser.ThenTheTestRunResultHasSpecifiedFilteredDuplicateTestsContext ctx) {
    }

    @Override
    public void enterThenTheTestRunResultHasSpecifiedPassingPhrases(PdslTestRunResultsMetaParser.ThenTheTestRunResultHasSpecifiedPassingPhrasesContext ctx) {
        assertThat(testRunResults.isPresent()).isTrue();
        int actual = ctxHelper.extractInt(ctx);
        assertThat(testRunResults.get().passingPhraseTotal()).isGreaterThan(actual);
    }

    @Override
    public void exitThenTheTestRunResultHasSpecifiedPassingPhrases(PdslTestRunResultsMetaParser.ThenTheTestRunResultHasSpecifiedPassingPhrasesContext ctx) {
    }

    @Override
    public void enterThenTheTestRunResultHasSpecifiedTotalPhrases(PdslTestRunResultsMetaParser.ThenTheTestRunResultHasSpecifiedTotalPhrasesContext ctx) {
        assertThat(testRunResults.isPresent()).isTrue();
        int actual = ctxHelper.extractInt(ctx);
        assertThat(testRunResults.get().totalPhrases()).isAtLeast(actual);
    }

    @Override
    public void exitThenTheTestRunResultHasSpecifiedTotalPhrases(PdslTestRunResultsMetaParser.ThenTheTestRunResultHasSpecifiedTotalPhrasesContext ctx) {
    }

    @Override
    public void enterWhenTheTestMetadataIsRetrievedFromTheTestRunResult(PdslTestRunResultsMetaParser.WhenTheTestMetadataIsRetrievedFromTheTestRunResultContext ctx) {
        assertThat(testRunResults.isPresent()).isTrue();
        assertThat(testRunResults.get().getTestResults().size()).isGreaterThan(0);
        testMetadata = Optional.of(testRunResults.get().getTestResults().stream().findFirst().orElseThrow());
    }

    @Override
    public void exitWhenTheTestMetadataIsRetrievedFromTheTestRunResult(PdslTestRunResultsMetaParser.WhenTheTestMetadataIsRetrievedFromTheTestRunResultContext ctx) {
    }

    @Override
    public void enterWhenTheOnlyTestMetadataItemIsExamined(PdslTestRunResultsMetaParser.WhenTheOnlyTestMetadataItemIsExaminedContext ctx) {
        assertThat(testRunResults.isPresent()).isTrue();
        assertThat(testRunResults.get().getTestResults().size()).isGreaterThan(0);
        testMetadata = Optional.of(testRunResults.get().getTestResults().stream().findFirst().orElseThrow());
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
        String actual = ctxHelper.extractTextInQuotes(ctx);
        assertThat(testMetadata.get().getFailingPhrase().get().getParseTree().getText()).contains(actual);
    }

    @Override
    public void exitThenTheTestMetadataFailingPhraseIsSpecifiedText(PdslTestRunResultsMetaParser.ThenTheTestMetadataFailingPhraseIsSpecifiedTextContext ctx) {
    }

    @Override
    public void enterThenTheTestMetadataHasOneItemInIt(PdslTestRunResultsMetaParser.ThenTheTestMetadataHasOneItemInItContext ctx) {
        assertThat(testRunResults.isPresent()).isTrue();
        assertThat(testRunResults.get().getTestResults().size()).isEqualTo(1);
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
