// Generated from /home/net/repos/polymorphic-dsl/pdsl/src/test/resources/AlphaParser.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AlphaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AlphaParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AlphaParser#mathematical_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMathematical_expression(AlphaParser.Mathematical_expressionContext ctx);
}