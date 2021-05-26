// Generated from /home/net/repos/polymorphic-dsl/pdsl/src/test/resources/RegistryParser.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RegistryParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RegistryParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RegistryParser#mathematical_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMathematical_expression(RegistryParser.Mathematical_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegistryParser#helloWorld}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHelloWorld(RegistryParser.HelloWorldContext ctx);
}