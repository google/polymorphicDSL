// Generated from ExtendedTestResourceParser.g4 by ANTLR 4.9
package com.pdsl.grammars;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExtendedTestResourceParser}.
 */
public interface ExtendedTestResourceParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExtendedTestResourceParser#testResourceValidity}.
	 * @param ctx the parse tree
	 */
	void enterTestResourceValidity(ExtendedTestResourceParser.TestResourceValidityContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExtendedTestResourceParser#testResourceValidity}.
	 * @param ctx the parse tree
	 */
	void exitTestResourceValidity(ExtendedTestResourceParser.TestResourceValidityContext ctx);
}