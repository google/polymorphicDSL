// Generated from DialectParser.g4 by ANTLR 4.9.2
package com.pdsl.grammars;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DialectParser}.
 */
public interface DialectParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DialectParser#grammarStep}.
	 * @param ctx the parse tree
	 */
	void enterGrammarStep(DialectParser.GrammarStepContext ctx);
	/**
	 * Exit a parse tree produced by {@link DialectParser#grammarStep}.
	 * @param ctx the parse tree
	 */
	void exitGrammarStep(DialectParser.GrammarStepContext ctx);
	/**
	 * Enter a parse tree produced by {@link DialectParser#subgrammarStep}.
	 * @param ctx the parse tree
	 */
	void enterSubgrammarStep(DialectParser.SubgrammarStepContext ctx);
	/**
	 * Exit a parse tree produced by {@link DialectParser#subgrammarStep}.
	 * @param ctx the parse tree
	 */
	void exitSubgrammarStep(DialectParser.SubgrammarStepContext ctx);
	/**
	 * Enter a parse tree produced by {@link DialectParser#polymorphicDslSyntaxCheck}.
	 * @param ctx the parse tree
	 */
	void enterPolymorphicDslSyntaxCheck(DialectParser.PolymorphicDslSyntaxCheckContext ctx);
	/**
	 * Exit a parse tree produced by {@link DialectParser#polymorphicDslSyntaxCheck}.
	 * @param ctx the parse tree
	 */
	void exitPolymorphicDslSyntaxCheck(DialectParser.PolymorphicDslSyntaxCheckContext ctx);
	/**
	 * Enter a parse tree produced by {@link DialectParser#polymorphicDslAllRules}.
	 * @param ctx the parse tree
	 */
	void enterPolymorphicDslAllRules(DialectParser.PolymorphicDslAllRulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link DialectParser#polymorphicDslAllRules}.
	 * @param ctx the parse tree
	 */
	void exitPolymorphicDslAllRules(DialectParser.PolymorphicDslAllRulesContext ctx);
}