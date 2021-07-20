// Generated from SimpleArithmetic.g4 by ANTLR 4.9
package com.pdsl.grammars;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SimpleArithmeticParser}.
 */
public interface SimpleArithmeticListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SimpleArithmeticParser#integerValue}.
	 * @param ctx the parse tree
	 */
	void enterIntegerValue(SimpleArithmeticParser.IntegerValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleArithmeticParser#integerValue}.
	 * @param ctx the parse tree
	 */
	void exitIntegerValue(SimpleArithmeticParser.IntegerValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleArithmeticParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void enterMathExpression(SimpleArithmeticParser.MathExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleArithmeticParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void exitMathExpression(SimpleArithmeticParser.MathExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleArithmeticParser#polymorphicDslAllRules}.
	 * @param ctx the parse tree
	 */
	void enterPolymorphicDslAllRules(SimpleArithmeticParser.PolymorphicDslAllRulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleArithmeticParser#polymorphicDslAllRules}.
	 * @param ctx the parse tree
	 */
	void exitPolymorphicDslAllRules(SimpleArithmeticParser.PolymorphicDslAllRulesContext ctx);
}