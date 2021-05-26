// Generated from PolymorphicDslBetaParser.g4 by ANTLR 4.9
package com.pdsl.grammars;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PolymorphicDslBetaParser}.
 */
public interface PolymorphicDslBetaParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PolymorphicDslBetaParser#polymorphicDslAllRules}.
	 * @param ctx the parse tree
	 */
	void enterPolymorphicDslAllRules(PolymorphicDslBetaParser.PolymorphicDslAllRulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link PolymorphicDslBetaParser#polymorphicDslAllRules}.
	 * @param ctx the parse tree
	 */
	void exitPolymorphicDslAllRules(PolymorphicDslBetaParser.PolymorphicDslAllRulesContext ctx);
	/**
	 * Enter a parse tree produced by {@link PolymorphicDslBetaParser#helloWorld}.
	 * @param ctx the parse tree
	 */
	void enterHelloWorld(PolymorphicDslBetaParser.HelloWorldContext ctx);
	/**
	 * Exit a parse tree produced by {@link PolymorphicDslBetaParser#helloWorld}.
	 * @param ctx the parse tree
	 */
	void exitHelloWorld(PolymorphicDslBetaParser.HelloWorldContext ctx);
}