// Generated from GherkinTags.g4 by ANTLR 4.9
package com.pdsl.gherkin.filter;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GherkinTagsParser}.
 */
public interface GherkinTagsListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GherkinTagsParser#not}.
	 * @param ctx the parse tree
	 */
	void enterNot(GherkinTagsParser.NotContext ctx);
	/**
	 * Exit a parse tree produced by {@link GherkinTagsParser#not}.
	 * @param ctx the parse tree
	 */
	void exitNot(GherkinTagsParser.NotContext ctx);
	/**
	 * Enter a parse tree produced by {@link GherkinTagsParser#and}.
	 * @param ctx the parse tree
	 */
	void enterAnd(GherkinTagsParser.AndContext ctx);
	/**
	 * Exit a parse tree produced by {@link GherkinTagsParser#and}.
	 * @param ctx the parse tree
	 */
	void exitAnd(GherkinTagsParser.AndContext ctx);
	/**
	 * Enter a parse tree produced by {@link GherkinTagsParser#or}.
	 * @param ctx the parse tree
	 */
	void enterOr(GherkinTagsParser.OrContext ctx);
	/**
	 * Exit a parse tree produced by {@link GherkinTagsParser#or}.
	 * @param ctx the parse tree
	 */
	void exitOr(GherkinTagsParser.OrContext ctx);
	/**
	 * Enter a parse tree produced by {@link GherkinTagsParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(GherkinTagsParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link GherkinTagsParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(GherkinTagsParser.ExprContext ctx);
}