// Generated from TestSpecificationFactoryParser.g4 by ANTLR 4.9
package com.pdsl.grammars;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TestSpecificationFactoryParser}.
 */
public interface TestSpecificationFactoryParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TestSpecificationFactoryParser#givenSpecificTestSpecificationFactory}.
	 * @param ctx the parse tree
	 */
	void enterGivenSpecificTestSpecificationFactory(TestSpecificationFactoryParser.GivenSpecificTestSpecificationFactoryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestSpecificationFactoryParser#givenSpecificTestSpecificationFactory}.
	 * @param ctx the parse tree
	 */
	void exitGivenSpecificTestSpecificationFactory(TestSpecificationFactoryParser.GivenSpecificTestSpecificationFactoryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestSpecificationFactoryParser#givenSpecificGrammar}.
	 * @param ctx the parse tree
	 */
	void enterGivenSpecificGrammar(TestSpecificationFactoryParser.GivenSpecificGrammarContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestSpecificationFactoryParser#givenSpecificGrammar}.
	 * @param ctx the parse tree
	 */
	void exitGivenSpecificGrammar(TestSpecificationFactoryParser.GivenSpecificGrammarContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestSpecificationFactoryParser#givenSpecificSubgrammar}.
	 * @param ctx the parse tree
	 */
	void enterGivenSpecificSubgrammar(TestSpecificationFactoryParser.GivenSpecificSubgrammarContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestSpecificationFactoryParser#givenSpecificSubgrammar}.
	 * @param ctx the parse tree
	 */
	void exitGivenSpecificSubgrammar(TestSpecificationFactoryParser.GivenSpecificSubgrammarContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestSpecificationFactoryParser#thenTestSpecificationFailsDueToMissingScenario}.
	 * @param ctx the parse tree
	 */
	void enterThenTestSpecificationFailsDueToMissingScenario(TestSpecificationFactoryParser.ThenTestSpecificationFailsDueToMissingScenarioContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestSpecificationFactoryParser#thenTestSpecificationFailsDueToMissingScenario}.
	 * @param ctx the parse tree
	 */
	void exitThenTestSpecificationFailsDueToMissingScenario(TestSpecificationFactoryParser.ThenTestSpecificationFailsDueToMissingScenarioContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestSpecificationFactoryParser#thenTestSpecificationFailsBecauseOfMissingStepBody}.
	 * @param ctx the parse tree
	 */
	void enterThenTestSpecificationFailsBecauseOfMissingStepBody(TestSpecificationFactoryParser.ThenTestSpecificationFailsBecauseOfMissingStepBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestSpecificationFactoryParser#thenTestSpecificationFailsBecauseOfMissingStepBody}.
	 * @param ctx the parse tree
	 */
	void exitThenTestSpecificationFailsBecauseOfMissingStepBody(TestSpecificationFactoryParser.ThenTestSpecificationFailsBecauseOfMissingStepBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestSpecificationFactoryParser#givenNonExistentUrl}.
	 * @param ctx the parse tree
	 */
	void enterGivenNonExistentUrl(TestSpecificationFactoryParser.GivenNonExistentUrlContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestSpecificationFactoryParser#givenNonExistentUrl}.
	 * @param ctx the parse tree
	 */
	void exitGivenNonExistentUrl(TestSpecificationFactoryParser.GivenNonExistentUrlContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestSpecificationFactoryParser#thenNoSuchResourceError}.
	 * @param ctx the parse tree
	 */
	void enterThenNoSuchResourceError(TestSpecificationFactoryParser.ThenNoSuchResourceErrorContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestSpecificationFactoryParser#thenNoSuchResourceError}.
	 * @param ctx the parse tree
	 */
	void exitThenNoSuchResourceError(TestSpecificationFactoryParser.ThenNoSuchResourceErrorContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestSpecificationFactoryParser#thenTestSpecificationHasTotalPhrases}.
	 * @param ctx the parse tree
	 */
	void enterThenTestSpecificationHasTotalPhrases(TestSpecificationFactoryParser.ThenTestSpecificationHasTotalPhrasesContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestSpecificationFactoryParser#thenTestSpecificationHasTotalPhrases}.
	 * @param ctx the parse tree
	 */
	void exitThenTestSpecificationHasTotalPhrases(TestSpecificationFactoryParser.ThenTestSpecificationHasTotalPhrasesContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestSpecificationFactoryParser#testSpecificationMayBeProduced}.
	 * @param ctx the parse tree
	 */
	void enterTestSpecificationMayBeProduced(TestSpecificationFactoryParser.TestSpecificationMayBeProducedContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestSpecificationFactoryParser#testSpecificationMayBeProduced}.
	 * @param ctx the parse tree
	 */
	void exitTestSpecificationMayBeProduced(TestSpecificationFactoryParser.TestSpecificationMayBeProducedContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestSpecificationFactoryParser#testResourceProcessedByFactory}.
	 * @param ctx the parse tree
	 */
	void enterTestResourceProcessedByFactory(TestSpecificationFactoryParser.TestResourceProcessedByFactoryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestSpecificationFactoryParser#testResourceProcessedByFactory}.
	 * @param ctx the parse tree
	 */
	void exitTestResourceProcessedByFactory(TestSpecificationFactoryParser.TestResourceProcessedByFactoryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestSpecificationFactoryParser#polymorphicDslAllRules}.
	 * @param ctx the parse tree
	 */
	void enterPolymorphicDslAllRules(TestSpecificationFactoryParser.PolymorphicDslAllRulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestSpecificationFactoryParser#polymorphicDslAllRules}.
	 * @param ctx the parse tree
	 */
	void exitPolymorphicDslAllRules(TestSpecificationFactoryParser.PolymorphicDslAllRulesContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestSpecificationFactoryParser#gherkinStepKeyword}.
	 * @param ctx the parse tree
	 */
	void enterGherkinStepKeyword(TestSpecificationFactoryParser.GherkinStepKeywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestSpecificationFactoryParser#gherkinStepKeyword}.
	 * @param ctx the parse tree
	 */
	void exitGherkinStepKeyword(TestSpecificationFactoryParser.GherkinStepKeywordContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestSpecificationFactoryParser#integerValue}.
	 * @param ctx the parse tree
	 */
	void enterIntegerValue(TestSpecificationFactoryParser.IntegerValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestSpecificationFactoryParser#integerValue}.
	 * @param ctx the parse tree
	 */
	void exitIntegerValue(TestSpecificationFactoryParser.IntegerValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestSpecificationFactoryParser#textInDoubleQuotes}.
	 * @param ctx the parse tree
	 */
	void enterTextInDoubleQuotes(TestSpecificationFactoryParser.TextInDoubleQuotesContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestSpecificationFactoryParser#textInDoubleQuotes}.
	 * @param ctx the parse tree
	 */
	void exitTextInDoubleQuotes(TestSpecificationFactoryParser.TextInDoubleQuotesContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestSpecificationFactoryParser#docstring}.
	 * @param ctx the parse tree
	 */
	void enterDocstring(TestSpecificationFactoryParser.DocstringContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestSpecificationFactoryParser#docstring}.
	 * @param ctx the parse tree
	 */
	void exitDocstring(TestSpecificationFactoryParser.DocstringContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestSpecificationFactoryParser#givenTheTestResource}.
	 * @param ctx the parse tree
	 */
	void enterGivenTheTestResource(TestSpecificationFactoryParser.GivenTheTestResourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestSpecificationFactoryParser#givenTheTestResource}.
	 * @param ctx the parse tree
	 */
	void exitGivenTheTestResource(TestSpecificationFactoryParser.GivenTheTestResourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestSpecificationFactoryParser#givenTheRawResource}.
	 * @param ctx the parse tree
	 */
	void enterGivenTheRawResource(TestSpecificationFactoryParser.GivenTheRawResourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestSpecificationFactoryParser#givenTheRawResource}.
	 * @param ctx the parse tree
	 */
	void exitGivenTheRawResource(TestSpecificationFactoryParser.GivenTheRawResourceContext ctx);
}