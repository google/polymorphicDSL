// Generated from TestCaseFactoryParser.g4 by ANTLR 4.9
package com.pdsl.grammars;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TestCaseFactoryParser}.
 */
public interface TestCaseFactoryParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TestCaseFactoryParser#givenAnArbitraryTestSpecification}.
	 * @param ctx the parse tree
	 */
	void enterGivenAnArbitraryTestSpecification(TestCaseFactoryParser.GivenAnArbitraryTestSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestCaseFactoryParser#givenAnArbitraryTestSpecification}.
	 * @param ctx the parse tree
	 */
	void exitGivenAnArbitraryTestSpecification(TestCaseFactoryParser.GivenAnArbitraryTestSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestCaseFactoryParser#givenSpecificationHasPhrase}.
	 * @param ctx the parse tree
	 */
	void enterGivenSpecificationHasPhrase(TestCaseFactoryParser.GivenSpecificationHasPhraseContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestCaseFactoryParser#givenSpecificationHasPhrase}.
	 * @param ctx the parse tree
	 */
	void exitGivenSpecificationHasPhrase(TestCaseFactoryParser.GivenSpecificationHasPhraseContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestCaseFactoryParser#givenTheTestSpecificationChild}.
	 * @param ctx the parse tree
	 */
	void enterGivenTheTestSpecificationChild(TestCaseFactoryParser.GivenTheTestSpecificationChildContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestCaseFactoryParser#givenTheTestSpecificationChild}.
	 * @param ctx the parse tree
	 */
	void exitGivenTheTestSpecificationChild(TestCaseFactoryParser.GivenTheTestSpecificationChildContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestCaseFactoryParser#thenTestCasesAreProduced}.
	 * @param ctx the parse tree
	 */
	void enterThenTestCasesAreProduced(TestCaseFactoryParser.ThenTestCasesAreProducedContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestCaseFactoryParser#thenTestCasesAreProduced}.
	 * @param ctx the parse tree
	 */
	void exitThenTestCasesAreProduced(TestCaseFactoryParser.ThenTestCasesAreProducedContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestCaseFactoryParser#thenEachTestCaseHasPhrases}.
	 * @param ctx the parse tree
	 */
	void enterThenEachTestCaseHasPhrases(TestCaseFactoryParser.ThenEachTestCaseHasPhrasesContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestCaseFactoryParser#thenEachTestCaseHasPhrases}.
	 * @param ctx the parse tree
	 */
	void exitThenEachTestCaseHasPhrases(TestCaseFactoryParser.ThenEachTestCaseHasPhrasesContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestCaseFactoryParser#givenSpecificTestCaseFactoryIsUsed}.
	 * @param ctx the parse tree
	 */
	void enterGivenSpecificTestCaseFactoryIsUsed(TestCaseFactoryParser.GivenSpecificTestCaseFactoryIsUsedContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestCaseFactoryParser#givenSpecificTestCaseFactoryIsUsed}.
	 * @param ctx the parse tree
	 */
	void exitGivenSpecificTestCaseFactoryIsUsed(TestCaseFactoryParser.GivenSpecificTestCaseFactoryIsUsedContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestCaseFactoryParser#whenTestSpecificationProcessedByFactory}.
	 * @param ctx the parse tree
	 */
	void enterWhenTestSpecificationProcessedByFactory(TestCaseFactoryParser.WhenTestSpecificationProcessedByFactoryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestCaseFactoryParser#whenTestSpecificationProcessedByFactory}.
	 * @param ctx the parse tree
	 */
	void exitWhenTestSpecificationProcessedByFactory(TestCaseFactoryParser.WhenTestSpecificationProcessedByFactoryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestCaseFactoryParser#polymorphicDslAllRules}.
	 * @param ctx the parse tree
	 */
	void enterPolymorphicDslAllRules(TestCaseFactoryParser.PolymorphicDslAllRulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestCaseFactoryParser#polymorphicDslAllRules}.
	 * @param ctx the parse tree
	 */
	void exitPolymorphicDslAllRules(TestCaseFactoryParser.PolymorphicDslAllRulesContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestCaseFactoryParser#gherkinStepKeyword}.
	 * @param ctx the parse tree
	 */
	void enterGherkinStepKeyword(TestCaseFactoryParser.GherkinStepKeywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestCaseFactoryParser#gherkinStepKeyword}.
	 * @param ctx the parse tree
	 */
	void exitGherkinStepKeyword(TestCaseFactoryParser.GherkinStepKeywordContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestCaseFactoryParser#integerValue}.
	 * @param ctx the parse tree
	 */
	void enterIntegerValue(TestCaseFactoryParser.IntegerValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestCaseFactoryParser#integerValue}.
	 * @param ctx the parse tree
	 */
	void exitIntegerValue(TestCaseFactoryParser.IntegerValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestCaseFactoryParser#textInDoubleQuotes}.
	 * @param ctx the parse tree
	 */
	void enterTextInDoubleQuotes(TestCaseFactoryParser.TextInDoubleQuotesContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestCaseFactoryParser#textInDoubleQuotes}.
	 * @param ctx the parse tree
	 */
	void exitTextInDoubleQuotes(TestCaseFactoryParser.TextInDoubleQuotesContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestCaseFactoryParser#docstring}.
	 * @param ctx the parse tree
	 */
	void enterDocstring(TestCaseFactoryParser.DocstringContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestCaseFactoryParser#docstring}.
	 * @param ctx the parse tree
	 */
	void exitDocstring(TestCaseFactoryParser.DocstringContext ctx);
}