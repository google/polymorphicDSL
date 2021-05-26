// Generated from /home/net/repos/polymorphic-dsl/pdsl/src/test/resources/PolymorphicDslRegistryParser.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link PolymorphicDslRegistryParserVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public class PolymorphicDslRegistryParserBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements PolymorphicDslRegistryParserVisitor<T> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitPolymorphicDslAllRules(PolymorphicDslRegistryParser.PolymorphicDslAllRulesContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitMathematical_expression(PolymorphicDslRegistryParser.Mathematical_expressionContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitHelloWorld(PolymorphicDslRegistryParser.HelloWorldContext ctx) { return visitChildren(ctx); }
}