// Generated from TestRunResultCommonParser.g4 by ANTLR 4.9
package com.pdsl.grammars;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TestRunResultCommonParser}.
 */
public interface TestRunResultCommonParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TestRunResultCommonParser#integerValue}.
	 * @param ctx the parse tree
	 */
	void enterIntegerValue(TestRunResultCommonParser.IntegerValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestRunResultCommonParser#integerValue}.
	 * @param ctx the parse tree
	 */
	void exitIntegerValue(TestRunResultCommonParser.IntegerValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestRunResultCommonParser#thenTheTestRunResultsHaveSpecifiedPassingTests}.
	 * @param ctx the parse tree
	 */
	void enterThenTheTestRunResultsHaveSpecifiedPassingTests(TestRunResultCommonParser.ThenTheTestRunResultsHaveSpecifiedPassingTestsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestRunResultCommonParser#thenTheTestRunResultsHaveSpecifiedPassingTests}.
	 * @param ctx the parse tree
	 */
	void exitThenTheTestRunResultsHaveSpecifiedPassingTests(TestRunResultCommonParser.ThenTheTestRunResultsHaveSpecifiedPassingTestsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestRunResultCommonParser#thenTheTestRunResultsHaveSpecifiedFailingTests}.
	 * @param ctx the parse tree
	 */
	void enterThenTheTestRunResultsHaveSpecifiedFailingTests(TestRunResultCommonParser.ThenTheTestRunResultsHaveSpecifiedFailingTestsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestRunResultCommonParser#thenTheTestRunResultsHaveSpecifiedFailingTests}.
	 * @param ctx the parse tree
	 */
	void exitThenTheTestRunResultsHaveSpecifiedFailingTests(TestRunResultCommonParser.ThenTheTestRunResultsHaveSpecifiedFailingTestsContext ctx);
}