// Generated from PdslCommonParser.g4 by ANTLR 4.9
package com.pdsl.grammars;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PdslCommonParser}.
 */
public interface PdslCommonParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PdslCommonParser#whenTheTestResourceIsProcessedByFactory}.
	 * @param ctx the parse tree
	 */
	void enterWhenTheTestResourceIsProcessedByFactory(PdslCommonParser.WhenTheTestResourceIsProcessedByFactoryContext ctx);
	/**
	 * Exit a parse tree produced by {@link PdslCommonParser#whenTheTestResourceIsProcessedByFactory}.
	 * @param ctx the parse tree
	 */
	void exitWhenTheTestResourceIsProcessedByFactory(PdslCommonParser.WhenTheTestResourceIsProcessedByFactoryContext ctx);
	/**
	 * Enter a parse tree produced by {@link PdslCommonParser#testSpecificationIsProduced}.
	 * @param ctx the parse tree
	 */
	void enterTestSpecificationIsProduced(PdslCommonParser.TestSpecificationIsProducedContext ctx);
	/**
	 * Exit a parse tree produced by {@link PdslCommonParser#testSpecificationIsProduced}.
	 * @param ctx the parse tree
	 */
	void exitTestSpecificationIsProduced(PdslCommonParser.TestSpecificationIsProducedContext ctx);
	/**
	 * Enter a parse tree produced by {@link PdslCommonParser#testSpecificationIsProcessedByTestCaseFactory}.
	 * @param ctx the parse tree
	 */
	void enterTestSpecificationIsProcessedByTestCaseFactory(PdslCommonParser.TestSpecificationIsProcessedByTestCaseFactoryContext ctx);
	/**
	 * Exit a parse tree produced by {@link PdslCommonParser#testSpecificationIsProcessedByTestCaseFactory}.
	 * @param ctx the parse tree
	 */
	void exitTestSpecificationIsProcessedByTestCaseFactory(PdslCommonParser.TestSpecificationIsProcessedByTestCaseFactoryContext ctx);
	/**
	 * Enter a parse tree produced by {@link PdslCommonParser#polymorphicDslTestExecutor}.
	 * @param ctx the parse tree
	 */
	void enterPolymorphicDslTestExecutor(PdslCommonParser.PolymorphicDslTestExecutorContext ctx);
	/**
	 * Exit a parse tree produced by {@link PdslCommonParser#polymorphicDslTestExecutor}.
	 * @param ctx the parse tree
	 */
	void exitPolymorphicDslTestExecutor(PdslCommonParser.PolymorphicDslTestExecutorContext ctx);
	/**
	 * Enter a parse tree produced by {@link PdslCommonParser#testRunResultProduced}.
	 * @param ctx the parse tree
	 */
	void enterTestRunResultProduced(PdslCommonParser.TestRunResultProducedContext ctx);
	/**
	 * Exit a parse tree produced by {@link PdslCommonParser#testRunResultProduced}.
	 * @param ctx the parse tree
	 */
	void exitTestRunResultProduced(PdslCommonParser.TestRunResultProducedContext ctx);
}