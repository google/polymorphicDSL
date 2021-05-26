// Generated from MinimalParser.g4 by ANTLR 4.9
package com.google.pdsl.grammars;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MinimalParser}.
 */
public interface MinimalParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MinimalParser#minimal}.
	 * @param ctx the parse tree
	 */
	void enterMinimal(MinimalParser.MinimalContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinimalParser#minimal}.
	 * @param ctx the parse tree
	 */
	void exitMinimal(MinimalParser.MinimalContext ctx);
}