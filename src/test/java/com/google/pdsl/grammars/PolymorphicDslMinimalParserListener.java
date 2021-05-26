// Generated from PolymorphicDslMinimalParser.g4 by ANTLR 4.9
package com.google.pdsl.grammars;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PolymorphicDslMinimalParser}.
 */
public interface PolymorphicDslMinimalParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PolymorphicDslMinimalParser#polymorphicDslAllRules}.
	 * @param ctx the parse tree
	 */
	void enterPolymorphicDslAllRules(PolymorphicDslMinimalParser.PolymorphicDslAllRulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link PolymorphicDslMinimalParser#polymorphicDslAllRules}.
	 * @param ctx the parse tree
	 */
	void exitPolymorphicDslAllRules(PolymorphicDslMinimalParser.PolymorphicDslAllRulesContext ctx);
	/**
	 * Enter a parse tree produced by {@link PolymorphicDslMinimalParser#minimal}.
	 * @param ctx the parse tree
	 */
	void enterMinimal(PolymorphicDslMinimalParser.MinimalContext ctx);
	/**
	 * Exit a parse tree produced by {@link PolymorphicDslMinimalParser#minimal}.
	 * @param ctx the parse tree
	 */
	void exitMinimal(PolymorphicDslMinimalParser.MinimalContext ctx);
}