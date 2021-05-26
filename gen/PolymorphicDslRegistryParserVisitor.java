// Generated from /home/net/repos/polymorphic-dsl/pdsl/src/test/resources/PolymorphicDslRegistryParser.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PolymorphicDslRegistryParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PolymorphicDslRegistryParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PolymorphicDslRegistryParser#polymorphicDslAllRules}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPolymorphicDslAllRules(PolymorphicDslRegistryParser.PolymorphicDslAllRulesContext ctx);
	/**
	 * Visit a parse tree produced by {@link PolymorphicDslRegistryParser#mathematical_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMathematical_expression(PolymorphicDslRegistryParser.Mathematical_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PolymorphicDslRegistryParser#helloWorld}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHelloWorld(PolymorphicDslRegistryParser.HelloWorldContext ctx);
}