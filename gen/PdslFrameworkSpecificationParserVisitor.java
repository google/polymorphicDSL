// Generated from /home/nboyer/repos/polymorphicDSL/src/test/resources/PdslFrameworkSpecificationParser.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PdslFrameworkSpecificationParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PdslFrameworkSpecificationParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PdslFrameworkSpecificationParser#givenTheTestResource}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGivenTheTestResource(PdslFrameworkSpecificationParser.GivenTheTestResourceContext ctx);
	/**
	 * Visit a parse tree produced by {@link PdslFrameworkSpecificationParser#whenTheTestResourceIsProcessedByFactory}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhenTheTestResourceIsProcessedByFactory(PdslFrameworkSpecificationParser.WhenTheTestResourceIsProcessedByFactoryContext ctx);
	/**
	 * Visit a parse tree produced by {@link PdslFrameworkSpecificationParser#testSpecificationIsProduced}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestSpecificationIsProduced(PdslFrameworkSpecificationParser.TestSpecificationIsProducedContext ctx);
	/**
	 * Visit a parse tree produced by {@link PdslFrameworkSpecificationParser#testSpecificationHasAnId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestSpecificationHasAnId(PdslFrameworkSpecificationParser.TestSpecificationHasAnIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link PdslFrameworkSpecificationParser#testSpecificationInExpectedFormat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestSpecificationInExpectedFormat(PdslFrameworkSpecificationParser.TestSpecificationInExpectedFormatContext ctx);
	/**
	 * Visit a parse tree produced by {@link PdslFrameworkSpecificationParser#testSpecificationIsProcessedByTestCaseFactory}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestSpecificationIsProcessedByTestCaseFactory(PdslFrameworkSpecificationParser.TestSpecificationIsProcessedByTestCaseFactoryContext ctx);
	/**
	 * Visit a parse tree produced by {@link PdslFrameworkSpecificationParser#testCaseIsProduced}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestCaseIsProduced(PdslFrameworkSpecificationParser.TestCaseIsProducedContext ctx);
	/**
	 * Visit a parse tree produced by {@link PdslFrameworkSpecificationParser#testCaseHasUniqueId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestCaseHasUniqueId(PdslFrameworkSpecificationParser.TestCaseHasUniqueIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link PdslFrameworkSpecificationParser#testCaseHasTitle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestCaseHasTitle(PdslFrameworkSpecificationParser.TestCaseHasTitleContext ctx);
	/**
	 * Visit a parse tree produced by {@link PdslFrameworkSpecificationParser#testCaseHasProperTestBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestCaseHasProperTestBody(PdslFrameworkSpecificationParser.TestCaseHasProperTestBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link PdslFrameworkSpecificationParser#polymorphicDslTestExecutor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPolymorphicDslTestExecutor(PdslFrameworkSpecificationParser.PolymorphicDslTestExecutorContext ctx);
	/**
	 * Visit a parse tree produced by {@link PdslFrameworkSpecificationParser#pdslCanProcessAllPhrases}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPdslCanProcessAllPhrases(PdslFrameworkSpecificationParser.PdslCanProcessAllPhrasesContext ctx);
	/**
	 * Visit a parse tree produced by {@link PdslFrameworkSpecificationParser#testCaseIsProcessed}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestCaseIsProcessed(PdslFrameworkSpecificationParser.TestCaseIsProcessedContext ctx);
	/**
	 * Visit a parse tree produced by {@link PdslFrameworkSpecificationParser#testRunResultProduced}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestRunResultProduced(PdslFrameworkSpecificationParser.TestRunResultProducedContext ctx);
	/**
	 * Visit a parse tree produced by {@link PdslFrameworkSpecificationParser#passingTestTotal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPassingTestTotal(PdslFrameworkSpecificationParser.PassingTestTotalContext ctx);
	/**
	 * Visit a parse tree produced by {@link PdslFrameworkSpecificationParser#passingPhraseTotal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPassingPhraseTotal(PdslFrameworkSpecificationParser.PassingPhraseTotalContext ctx);
	/**
	 * Visit a parse tree produced by {@link PdslFrameworkSpecificationParser#failingTestTotal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFailingTestTotal(PdslFrameworkSpecificationParser.FailingTestTotalContext ctx);
	/**
	 * Visit a parse tree produced by {@link PdslFrameworkSpecificationParser#totalPhrases}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTotalPhrases(PdslFrameworkSpecificationParser.TotalPhrasesContext ctx);
	/**
	 * Visit a parse tree produced by {@link PdslFrameworkSpecificationParser#duplicateTestTotal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDuplicateTestTotal(PdslFrameworkSpecificationParser.DuplicateTestTotalContext ctx);
	/**
	 * Visit a parse tree produced by {@link PdslFrameworkSpecificationParser#polymorphicDslAllRules}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPolymorphicDslAllRules(PdslFrameworkSpecificationParser.PolymorphicDslAllRulesContext ctx);
	/**
	 * Visit a parse tree produced by {@link PdslFrameworkSpecificationParser#polymorphicDslSyntaxRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPolymorphicDslSyntaxRule(PdslFrameworkSpecificationParser.PolymorphicDslSyntaxRuleContext ctx);
}