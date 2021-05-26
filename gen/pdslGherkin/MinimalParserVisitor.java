// Generated from /home/net/repos/polymorphic-dsl/pdsl/src/test/resources/pdslGherkin/MinimalParser.g4 by ANTLR 4.9.1
package pdslGherkin;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MinimalParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MinimalParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MinimalParser#minimal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinimal(MinimalParser.MinimalContext ctx);
}