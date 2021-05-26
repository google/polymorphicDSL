// Generated from /home/net/repos/polymorphic-dsl/pdsl/src/test/resources/RegistryParser.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RegistryParser}.
 */
public interface RegistryParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RegistryParser#mathematical_expression}.
	 * @param ctx the parse tree
	 */
	void enterMathematical_expression(RegistryParser.Mathematical_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegistryParser#mathematical_expression}.
	 * @param ctx the parse tree
	 */
	void exitMathematical_expression(RegistryParser.Mathematical_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegistryParser#helloWorld}.
	 * @param ctx the parse tree
	 */
	void enterHelloWorld(RegistryParser.HelloWorldContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegistryParser#helloWorld}.
	 * @param ctx the parse tree
	 */
	void exitHelloWorld(RegistryParser.HelloWorldContext ctx);
}