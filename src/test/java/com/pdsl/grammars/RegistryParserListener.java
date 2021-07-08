// Generated from RegistryParser.g4 by ANTLR 4.7.2
package com.pdsl.grammars;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RegistryParser}.
 */
public interface RegistryParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RegistryParser#mathematical_expression}.
	 * @param ctx the parse tree
	 */
	void enterMathematical_expression(RegistryParser.Mathematical_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegistryParser#mathematical_expression}.
	 * @param ctx the parse tree
	 */
	void exitMathematical_expression(RegistryParser.Mathematical_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegistryParser#helloWorld}.
	 * @param ctx the parse tree
	 */
	void enterHelloWorld(RegistryParser.HelloWorldContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegistryParser#helloWorld}.
	 * @param ctx the parse tree
	 */
	void exitHelloWorld(RegistryParser.HelloWorldContext ctx);
}