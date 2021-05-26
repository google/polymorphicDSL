// Generated from /home/net/repos/polymorphic-dsl/pdsl/src/test/resources/PolymorphicDslMinimalParser.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PolymorphicDslMinimalParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PolymorphicDslMinimalParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PolymorphicDslMinimalParser#polymorphicDslAllRules}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPolymorphicDslAllRules(PolymorphicDslMinimalParser.PolymorphicDslAllRulesContext ctx);
	/**
	 * Visit a parse tree produced by {@link PolymorphicDslMinimalParser#minimal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinimal(PolymorphicDslMinimalParser.MinimalContext ctx);
}