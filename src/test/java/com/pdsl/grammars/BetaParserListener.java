// Generated from BetaParser.g4 by ANTLR 4.7.2
package com.pdsl.grammars;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BetaParser}.
 */
public interface BetaParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link BetaParser#helloWorld}.
	 * @param ctx the parse tree
	 */
	void enterHelloWorld(BetaParser.HelloWorldContext ctx);
	/**
	 * Exit a parse tree produced by {@link BetaParser#helloWorld}.
	 * @param ctx the parse tree
	 */
	void exitHelloWorld(BetaParser.HelloWorldContext ctx);
}