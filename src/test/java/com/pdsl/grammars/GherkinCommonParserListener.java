// Generated from GherkinCommonParser.g4 by ANTLR 4.9
package com.pdsl.grammars;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GherkinCommonParser}.
 */
public interface GherkinCommonParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GherkinCommonParser#gherkinStepKeyword}.
	 * @param ctx the parse tree
	 */
	void enterGherkinStepKeyword(GherkinCommonParser.GherkinStepKeywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link GherkinCommonParser#gherkinStepKeyword}.
	 * @param ctx the parse tree
	 */
	void exitGherkinStepKeyword(GherkinCommonParser.GherkinStepKeywordContext ctx);
	/**
	 * Enter a parse tree produced by {@link GherkinCommonParser#integerValue}.
	 * @param ctx the parse tree
	 */
	void enterIntegerValue(GherkinCommonParser.IntegerValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GherkinCommonParser#integerValue}.
	 * @param ctx the parse tree
	 */
	void exitIntegerValue(GherkinCommonParser.IntegerValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link GherkinCommonParser#textInDoubleQuotes}.
	 * @param ctx the parse tree
	 */
	void enterTextInDoubleQuotes(GherkinCommonParser.TextInDoubleQuotesContext ctx);
	/**
	 * Exit a parse tree produced by {@link GherkinCommonParser#textInDoubleQuotes}.
	 * @param ctx the parse tree
	 */
	void exitTextInDoubleQuotes(GherkinCommonParser.TextInDoubleQuotesContext ctx);
	/**
	 * Enter a parse tree produced by {@link GherkinCommonParser#docstring}.
	 * @param ctx the parse tree
	 */
	void enterDocstring(GherkinCommonParser.DocstringContext ctx);
	/**
	 * Exit a parse tree produced by {@link GherkinCommonParser#docstring}.
	 * @param ctx the parse tree
	 */
	void exitDocstring(GherkinCommonParser.DocstringContext ctx);
}