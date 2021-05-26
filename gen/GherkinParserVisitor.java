// Generated from /home/net/repos/polymorphic-dsl/pdsl/src/main/antlr4/com/google/pdsl/gherkin/GherkinParser.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GherkinParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GherkinParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GherkinParser#gherkinDocument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGherkinDocument(GherkinParser.GherkinDocumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link GherkinParser#feature}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFeature(GherkinParser.FeatureContext ctx);
	/**
	 * Visit a parse tree produced by {@link GherkinParser#ruleBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleBlock(GherkinParser.RuleBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link GherkinParser#background}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBackground(GherkinParser.BackgroundContext ctx);
	/**
	 * Visit a parse tree produced by {@link GherkinParser#scenario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScenario(GherkinParser.ScenarioContext ctx);
	/**
	 * Visit a parse tree produced by {@link GherkinParser#stepBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStepBody(GherkinParser.StepBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link GherkinParser#examplesBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExamplesBody(GherkinParser.ExamplesBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link GherkinParser#startingStep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStartingStep(GherkinParser.StartingStepContext ctx);
	/**
	 * Visit a parse tree produced by {@link GherkinParser#anyStep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnyStep(GherkinParser.AnyStepContext ctx);
}