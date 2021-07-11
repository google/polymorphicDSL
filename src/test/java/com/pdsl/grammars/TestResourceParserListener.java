// Generated from TestResourceParser.g4 by ANTLR 4.9
package com.pdsl.grammars;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TestResourceParser}.
 */
public interface TestResourceParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TestResourceParser#givenTheTestResource}.
	 * @param ctx the parse tree
	 */
	void enterGivenTheTestResource(TestResourceParser.GivenTheTestResourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestResourceParser#givenTheTestResource}.
	 * @param ctx the parse tree
	 */
	void exitGivenTheTestResource(TestResourceParser.GivenTheTestResourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestResourceParser#givenTheRawResource}.
	 * @param ctx the parse tree
	 */
	void enterGivenTheRawResource(TestResourceParser.GivenTheRawResourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestResourceParser#givenTheRawResource}.
	 * @param ctx the parse tree
	 */
	void exitGivenTheRawResource(TestResourceParser.GivenTheRawResourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestResourceParser#gherkinStepKeyword}.
	 * @param ctx the parse tree
	 */
	void enterGherkinStepKeyword(TestResourceParser.GherkinStepKeywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestResourceParser#gherkinStepKeyword}.
	 * @param ctx the parse tree
	 */
	void exitGherkinStepKeyword(TestResourceParser.GherkinStepKeywordContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestResourceParser#integerValue}.
	 * @param ctx the parse tree
	 */
	void enterIntegerValue(TestResourceParser.IntegerValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestResourceParser#integerValue}.
	 * @param ctx the parse tree
	 */
	void exitIntegerValue(TestResourceParser.IntegerValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestResourceParser#textInDoubleQuotes}.
	 * @param ctx the parse tree
	 */
	void enterTextInDoubleQuotes(TestResourceParser.TextInDoubleQuotesContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestResourceParser#textInDoubleQuotes}.
	 * @param ctx the parse tree
	 */
	void exitTextInDoubleQuotes(TestResourceParser.TextInDoubleQuotesContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestResourceParser#docstring}.
	 * @param ctx the parse tree
	 */
	void enterDocstring(TestResourceParser.DocstringContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestResourceParser#docstring}.
	 * @param ctx the parse tree
	 */
	void exitDocstring(TestResourceParser.DocstringContext ctx);
}