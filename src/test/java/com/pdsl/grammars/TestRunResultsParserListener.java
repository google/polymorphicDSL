// Generated from TestRunResultsParser.g4 by ANTLR 4.9
package com.pdsl.grammars;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TestRunResultsParser}.
 */
public interface TestRunResultsParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TestRunResultsParser#integerValue}.
	 * @param ctx the parse tree
	 */
	void enterIntegerValue(TestRunResultsParser.IntegerValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestRunResultsParser#integerValue}.
	 * @param ctx the parse tree
	 */
	void exitIntegerValue(TestRunResultsParser.IntegerValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestRunResultsParser#convertTestResourcesToCollectionWithSingleTestCase}.
	 * @param ctx the parse tree
	 */
	void enterConvertTestResourcesToCollectionWithSingleTestCase(TestRunResultsParser.ConvertTestResourcesToCollectionWithSingleTestCaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestRunResultsParser#convertTestResourcesToCollectionWithSingleTestCase}.
	 * @param ctx the parse tree
	 */
	void exitConvertTestResourcesToCollectionWithSingleTestCase(TestRunResultsParser.ConvertTestResourcesToCollectionWithSingleTestCaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestRunResultsParser#thenTestCaseCollectionHasSpecifiedTestCases}.
	 * @param ctx the parse tree
	 */
	void enterThenTestCaseCollectionHasSpecifiedTestCases(TestRunResultsParser.ThenTestCaseCollectionHasSpecifiedTestCasesContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestRunResultsParser#thenTestCaseCollectionHasSpecifiedTestCases}.
	 * @param ctx the parse tree
	 */
	void exitThenTestCaseCollectionHasSpecifiedTestCases(TestRunResultsParser.ThenTestCaseCollectionHasSpecifiedTestCasesContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestRunResultsParser#givenTheSpecifiedGrammarParseTreeListener}.
	 * @param ctx the parse tree
	 */
	void enterGivenTheSpecifiedGrammarParseTreeListener(TestRunResultsParser.GivenTheSpecifiedGrammarParseTreeListenerContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestRunResultsParser#givenTheSpecifiedGrammarParseTreeListener}.
	 * @param ctx the parse tree
	 */
	void exitGivenTheSpecifiedGrammarParseTreeListener(TestRunResultsParser.GivenTheSpecifiedGrammarParseTreeListenerContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestRunResultsParser#thenSingleTestCaseIsProduced}.
	 * @param ctx the parse tree
	 */
	void enterThenSingleTestCaseIsProduced(TestRunResultsParser.ThenSingleTestCaseIsProducedContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestRunResultsParser#thenSingleTestCaseIsProduced}.
	 * @param ctx the parse tree
	 */
	void exitThenSingleTestCaseIsProduced(TestRunResultsParser.ThenSingleTestCaseIsProducedContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestRunResultsParser#thenTheTestRunResultHasSpecifiedFilteredDuplicateTests}.
	 * @param ctx the parse tree
	 */
	void enterThenTheTestRunResultHasSpecifiedFilteredDuplicateTests(TestRunResultsParser.ThenTheTestRunResultHasSpecifiedFilteredDuplicateTestsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestRunResultsParser#thenTheTestRunResultHasSpecifiedFilteredDuplicateTests}.
	 * @param ctx the parse tree
	 */
	void exitThenTheTestRunResultHasSpecifiedFilteredDuplicateTests(TestRunResultsParser.ThenTheTestRunResultHasSpecifiedFilteredDuplicateTestsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestRunResultsParser#thenTheTestRunResultHasSpecifiedPassingPhrases}.
	 * @param ctx the parse tree
	 */
	void enterThenTheTestRunResultHasSpecifiedPassingPhrases(TestRunResultsParser.ThenTheTestRunResultHasSpecifiedPassingPhrasesContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestRunResultsParser#thenTheTestRunResultHasSpecifiedPassingPhrases}.
	 * @param ctx the parse tree
	 */
	void exitThenTheTestRunResultHasSpecifiedPassingPhrases(TestRunResultsParser.ThenTheTestRunResultHasSpecifiedPassingPhrasesContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestRunResultsParser#thenTheTestRunResultHasSpecifiedTotalPhrases}.
	 * @param ctx the parse tree
	 */
	void enterThenTheTestRunResultHasSpecifiedTotalPhrases(TestRunResultsParser.ThenTheTestRunResultHasSpecifiedTotalPhrasesContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestRunResultsParser#thenTheTestRunResultHasSpecifiedTotalPhrases}.
	 * @param ctx the parse tree
	 */
	void exitThenTheTestRunResultHasSpecifiedTotalPhrases(TestRunResultsParser.ThenTheTestRunResultHasSpecifiedTotalPhrasesContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestRunResultsParser#whenTheTestMetadataIsRetrievedFromTheTestRunResult}.
	 * @param ctx the parse tree
	 */
	void enterWhenTheTestMetadataIsRetrievedFromTheTestRunResult(TestRunResultsParser.WhenTheTestMetadataIsRetrievedFromTheTestRunResultContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestRunResultsParser#whenTheTestMetadataIsRetrievedFromTheTestRunResult}.
	 * @param ctx the parse tree
	 */
	void exitWhenTheTestMetadataIsRetrievedFromTheTestRunResult(TestRunResultsParser.WhenTheTestMetadataIsRetrievedFromTheTestRunResultContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestRunResultsParser#whenTheOnlyTestMetadataItemIsExamined}.
	 * @param ctx the parse tree
	 */
	void enterWhenTheOnlyTestMetadataItemIsExamined(TestRunResultsParser.WhenTheOnlyTestMetadataItemIsExaminedContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestRunResultsParser#whenTheOnlyTestMetadataItemIsExamined}.
	 * @param ctx the parse tree
	 */
	void exitWhenTheOnlyTestMetadataItemIsExamined(TestRunResultsParser.WhenTheOnlyTestMetadataItemIsExaminedContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestRunResultsParser#thenTheTestMetadataHasAnExceptionExplainingWhyTheTestFailed}.
	 * @param ctx the parse tree
	 */
	void enterThenTheTestMetadataHasAnExceptionExplainingWhyTheTestFailed(TestRunResultsParser.ThenTheTestMetadataHasAnExceptionExplainingWhyTheTestFailedContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestRunResultsParser#thenTheTestMetadataHasAnExceptionExplainingWhyTheTestFailed}.
	 * @param ctx the parse tree
	 */
	void exitThenTheTestMetadataHasAnExceptionExplainingWhyTheTestFailed(TestRunResultsParser.ThenTheTestMetadataHasAnExceptionExplainingWhyTheTestFailedContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestRunResultsParser#thenTheTestMetadataHasThePhraseThatFailed}.
	 * @param ctx the parse tree
	 */
	void enterThenTheTestMetadataHasThePhraseThatFailed(TestRunResultsParser.ThenTheTestMetadataHasThePhraseThatFailedContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestRunResultsParser#thenTheTestMetadataHasThePhraseThatFailed}.
	 * @param ctx the parse tree
	 */
	void exitThenTheTestMetadataHasThePhraseThatFailed(TestRunResultsParser.ThenTheTestMetadataHasThePhraseThatFailedContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestRunResultsParser#thenTheTestMetadataFailingPhraseIsSpecifiedText}.
	 * @param ctx the parse tree
	 */
	void enterThenTheTestMetadataFailingPhraseIsSpecifiedText(TestRunResultsParser.ThenTheTestMetadataFailingPhraseIsSpecifiedTextContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestRunResultsParser#thenTheTestMetadataFailingPhraseIsSpecifiedText}.
	 * @param ctx the parse tree
	 */
	void exitThenTheTestMetadataFailingPhraseIsSpecifiedText(TestRunResultsParser.ThenTheTestMetadataFailingPhraseIsSpecifiedTextContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestRunResultsParser#thenTheTestMetadataHasOneItemInIt}.
	 * @param ctx the parse tree
	 */
	void enterThenTheTestMetadataHasOneItemInIt(TestRunResultsParser.ThenTheTestMetadataHasOneItemInItContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestRunResultsParser#thenTheTestMetadataHasOneItemInIt}.
	 * @param ctx the parse tree
	 */
	void exitThenTheTestMetadataHasOneItemInIt(TestRunResultsParser.ThenTheTestMetadataHasOneItemInItContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestRunResultsParser#gherkinStepKeyword}.
	 * @param ctx the parse tree
	 */
	void enterGherkinStepKeyword(TestRunResultsParser.GherkinStepKeywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestRunResultsParser#gherkinStepKeyword}.
	 * @param ctx the parse tree
	 */
	void exitGherkinStepKeyword(TestRunResultsParser.GherkinStepKeywordContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestRunResultsParser#textInDoubleQuotes}.
	 * @param ctx the parse tree
	 */
	void enterTextInDoubleQuotes(TestRunResultsParser.TextInDoubleQuotesContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestRunResultsParser#textInDoubleQuotes}.
	 * @param ctx the parse tree
	 */
	void exitTextInDoubleQuotes(TestRunResultsParser.TextInDoubleQuotesContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestRunResultsParser#docstring}.
	 * @param ctx the parse tree
	 */
	void enterDocstring(TestRunResultsParser.DocstringContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestRunResultsParser#docstring}.
	 * @param ctx the parse tree
	 */
	void exitDocstring(TestRunResultsParser.DocstringContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestRunResultsParser#textInDoubleQuotesEnd}.
	 * @param ctx the parse tree
	 */
	void enterTextInDoubleQuotesEnd(TestRunResultsParser.TextInDoubleQuotesEndContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestRunResultsParser#textInDoubleQuotesEnd}.
	 * @param ctx the parse tree
	 */
	void exitTextInDoubleQuotesEnd(TestRunResultsParser.TextInDoubleQuotesEndContext ctx);
}