// Generated from /home/net/repos/polymorphic-dsl/pdsl/src/test/resources/AlphaParser.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AlphaParser}.
 */
public interface AlphaParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AlphaParser#mathematical_expression}.
	 * @param ctx the parse tree
	 */
	void enterMathematical_expression(AlphaParser.Mathematical_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlphaParser#mathematical_expression}.
	 * @param ctx the parse tree
	 */
	void exitMathematical_expression(AlphaParser.Mathematical_expressionContext ctx);
}