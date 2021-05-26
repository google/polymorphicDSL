// Generated from /home/net/repos/polymorphic-dsl/pdsl/src/test/resources/PolymorphicDslMinimalParser.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link PolymorphicDslMinimalParserVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public class PolymorphicDslMinimalParserBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements PolymorphicDslMinimalParserVisitor<T> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitPolymorphicDslAllRules(PolymorphicDslMinimalParser.PolymorphicDslAllRulesContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitMinimal(PolymorphicDslMinimalParser.MinimalContext ctx) { return visitChildren(ctx); }
}