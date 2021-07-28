// Generated from GherkinTags.g4 by ANTLR 4.9
package com.pdsl.gherkin.filter;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GherkinTagsParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GherkinTagsVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GherkinTagsParser#not}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(GherkinTagsParser.NotContext ctx);
	/**
	 * Visit a parse tree produced by {@link GherkinTagsParser#and}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(GherkinTagsParser.AndContext ctx);
	/**
	 * Visit a parse tree produced by {@link GherkinTagsParser#or}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr(GherkinTagsParser.OrContext ctx);
	/**
	 * Visit a parse tree produced by {@link GherkinTagsParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(GherkinTagsParser.ExprContext ctx);
}