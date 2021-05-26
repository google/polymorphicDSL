// Generated from PolymorphicDslRegistryParser.g4 by ANTLR 4.9
package com.google.pdsl.grammars;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PolymorphicDslRegistryParser}.
 */
public interface PolymorphicDslRegistryParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PolymorphicDslRegistryParser#polymorphicDslAllRules}.
	 * @param ctx the parse tree
	 */
	void enterPolymorphicDslAllRules(PolymorphicDslRegistryParser.PolymorphicDslAllRulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link PolymorphicDslRegistryParser#polymorphicDslAllRules}.
	 * @param ctx the parse tree
	 */
	void exitPolymorphicDslAllRules(PolymorphicDslRegistryParser.PolymorphicDslAllRulesContext ctx);
	/**
	 * Enter a parse tree produced by {@link PolymorphicDslRegistryParser#mathematical_expression}.
	 * @param ctx the parse tree
	 */
	void enterMathematical_expression(PolymorphicDslRegistryParser.Mathematical_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PolymorphicDslRegistryParser#mathematical_expression}.
	 * @param ctx the parse tree
	 */
	void exitMathematical_expression(PolymorphicDslRegistryParser.Mathematical_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PolymorphicDslRegistryParser#helloWorld}.
	 * @param ctx the parse tree
	 */
	void enterHelloWorld(PolymorphicDslRegistryParser.HelloWorldContext ctx);
	/**
	 * Exit a parse tree produced by {@link PolymorphicDslRegistryParser#helloWorld}.
	 * @param ctx the parse tree
	 */
	void exitHelloWorld(PolymorphicDslRegistryParser.HelloWorldContext ctx);
}