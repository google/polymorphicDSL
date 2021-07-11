// Generated from PdslTestResourceParser.g4 by ANTLR 4.9
package com.pdsl.grammars;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PdslTestResourceParser}.
 */
public interface PdslTestResourceParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PdslTestResourceParser#polymorphicDslAllRules}.
	 * @param ctx the parse tree
	 */
	void enterPolymorphicDslAllRules(PdslTestResourceParser.PolymorphicDslAllRulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link PdslTestResourceParser#polymorphicDslAllRules}.
	 * @param ctx the parse tree
	 */
	void exitPolymorphicDslAllRules(PdslTestResourceParser.PolymorphicDslAllRulesContext ctx);
	/**
	 * Enter a parse tree produced by {@link PdslTestResourceParser#givenTheTestResource}.
	 * @param ctx the parse tree
	 */
	void enterGivenTheTestResource(PdslTestResourceParser.GivenTheTestResourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link PdslTestResourceParser#givenTheTestResource}.
	 * @param ctx the parse tree
	 */
	void exitGivenTheTestResource(PdslTestResourceParser.GivenTheTestResourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link PdslTestResourceParser#givenTheRawResource}.
	 * @param ctx the parse tree
	 */
	void enterGivenTheRawResource(PdslTestResourceParser.GivenTheRawResourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link PdslTestResourceParser#givenTheRawResource}.
	 * @param ctx the parse tree
	 */
	void exitGivenTheRawResource(PdslTestResourceParser.GivenTheRawResourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link PdslTestResourceParser#gherkinStepKeyword}.
	 * @param ctx the parse tree
	 */
	void enterGherkinStepKeyword(PdslTestResourceParser.GherkinStepKeywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link PdslTestResourceParser#gherkinStepKeyword}.
	 * @param ctx the parse tree
	 */
	void exitGherkinStepKeyword(PdslTestResourceParser.GherkinStepKeywordContext ctx);
	/**
	 * Enter a parse tree produced by {@link PdslTestResourceParser#integerValue}.
	 * @param ctx the parse tree
	 */
	void enterIntegerValue(PdslTestResourceParser.IntegerValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link PdslTestResourceParser#integerValue}.
	 * @param ctx the parse tree
	 */
	void exitIntegerValue(PdslTestResourceParser.IntegerValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link PdslTestResourceParser#textInDoubleQuotes}.
	 * @param ctx the parse tree
	 */
	void enterTextInDoubleQuotes(PdslTestResourceParser.TextInDoubleQuotesContext ctx);
	/**
	 * Exit a parse tree produced by {@link PdslTestResourceParser#textInDoubleQuotes}.
	 * @param ctx the parse tree
	 */
	void exitTextInDoubleQuotes(PdslTestResourceParser.TextInDoubleQuotesContext ctx);
	/**
	 * Enter a parse tree produced by {@link PdslTestResourceParser#docstring}.
	 * @param ctx the parse tree
	 */
	void enterDocstring(PdslTestResourceParser.DocstringContext ctx);
	/**
	 * Exit a parse tree produced by {@link PdslTestResourceParser#docstring}.
	 * @param ctx the parse tree
	 */
	void exitDocstring(PdslTestResourceParser.DocstringContext ctx);
	/**
	 * Enter a parse tree produced by {@link PdslTestResourceParser#testResourceValidity}.
	 * @param ctx the parse tree
	 */
	void enterTestResourceValidity(PdslTestResourceParser.TestResourceValidityContext ctx);
	/**
	 * Exit a parse tree produced by {@link PdslTestResourceParser#testResourceValidity}.
	 * @param ctx the parse tree
	 */
	void exitTestResourceValidity(PdslTestResourceParser.TestResourceValidityContext ctx);
}