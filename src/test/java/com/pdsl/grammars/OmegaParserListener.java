// Generated from OmegaParser.g4 by ANTLR 4.7.2
package com.pdsl.grammars;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link OmegaParser}.
 */
public interface OmegaParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link OmegaParser#allInputs}.
	 * @param ctx the parse tree
	 */
	void enterAllInputs(OmegaParser.AllInputsContext ctx);
	/**
	 * Exit a parse tree produced by {@link OmegaParser#allInputs}.
	 * @param ctx the parse tree
	 */
	void exitAllInputs(OmegaParser.AllInputsContext ctx);
}