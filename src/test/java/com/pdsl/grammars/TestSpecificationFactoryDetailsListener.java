// Generated from TestSpecificationFactoryDetails.g4 by ANTLR 4.9
package com.pdsl.grammars;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TestSpecificationFactoryDetailsParser}.
 */
public interface TestSpecificationFactoryDetailsListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TestSpecificationFactoryDetailsParser#givenSpecificTestSpecificationFactory}.
	 * @param ctx the parse tree
	 */
	void enterGivenSpecificTestSpecificationFactory(TestSpecificationFactoryDetailsParser.GivenSpecificTestSpecificationFactoryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestSpecificationFactoryDetailsParser#givenSpecificTestSpecificationFactory}.
	 * @param ctx the parse tree
	 */
	void exitGivenSpecificTestSpecificationFactory(TestSpecificationFactoryDetailsParser.GivenSpecificTestSpecificationFactoryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestSpecificationFactoryDetailsParser#gherkinStepKeyword}.
	 * @param ctx the parse tree
	 */
	void enterGherkinStepKeyword(TestSpecificationFactoryDetailsParser.GherkinStepKeywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestSpecificationFactoryDetailsParser#gherkinStepKeyword}.
	 * @param ctx the parse tree
	 */
	void exitGherkinStepKeyword(TestSpecificationFactoryDetailsParser.GherkinStepKeywordContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestSpecificationFactoryDetailsParser#integerValue}.
	 * @param ctx the parse tree
	 */
	void enterIntegerValue(TestSpecificationFactoryDetailsParser.IntegerValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestSpecificationFactoryDetailsParser#integerValue}.
	 * @param ctx the parse tree
	 */
	void exitIntegerValue(TestSpecificationFactoryDetailsParser.IntegerValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestSpecificationFactoryDetailsParser#textInDoubleQuotes}.
	 * @param ctx the parse tree
	 */
	void enterTextInDoubleQuotes(TestSpecificationFactoryDetailsParser.TextInDoubleQuotesContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestSpecificationFactoryDetailsParser#textInDoubleQuotes}.
	 * @param ctx the parse tree
	 */
	void exitTextInDoubleQuotes(TestSpecificationFactoryDetailsParser.TextInDoubleQuotesContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestSpecificationFactoryDetailsParser#docstring}.
	 * @param ctx the parse tree
	 */
	void enterDocstring(TestSpecificationFactoryDetailsParser.DocstringContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestSpecificationFactoryDetailsParser#docstring}.
	 * @param ctx the parse tree
	 */
	void exitDocstring(TestSpecificationFactoryDetailsParser.DocstringContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestSpecificationFactoryDetailsParser#textInDoubleQuotesEnd}.
	 * @param ctx the parse tree
	 */
	void enterTextInDoubleQuotesEnd(TestSpecificationFactoryDetailsParser.TextInDoubleQuotesEndContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestSpecificationFactoryDetailsParser#textInDoubleQuotesEnd}.
	 * @param ctx the parse tree
	 */
	void exitTextInDoubleQuotesEnd(TestSpecificationFactoryDetailsParser.TextInDoubleQuotesEndContext ctx);
}