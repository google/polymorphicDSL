// Generated from AllGrammarsParser.g4 by ANTLR 4.9
package com.pdsl.grammars;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AllGrammarsParser}.
 */
public interface AllGrammarsParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AllGrammarsParser#polymorphicDslAllRules}.
	 * @param ctx the parse tree
	 */
	void enterPolymorphicDslAllRules(AllGrammarsParser.PolymorphicDslAllRulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link AllGrammarsParser#polymorphicDslAllRules}.
	 * @param ctx the parse tree
	 */
	void exitPolymorphicDslAllRules(AllGrammarsParser.PolymorphicDslAllRulesContext ctx);
}