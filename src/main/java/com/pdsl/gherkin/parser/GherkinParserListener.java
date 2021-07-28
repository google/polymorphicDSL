// Generated from com/pdsl/gherkin/GherkinParser.g4 by ANTLR 4.9.1
package com.pdsl.gherkin.parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GherkinParser}.
 */
public interface GherkinParserListener extends ParseTreeListener {
    /**
     * Enter a parse tree produced by {@link GherkinParser#gherkinDocument}.
     *
     * @param ctx the parse tree
     */
    void enterGherkinDocument(GherkinParser.GherkinDocumentContext ctx);

    /**
     * Exit a parse tree produced by {@link GherkinParser#gherkinDocument}.
     *
     * @param ctx the parse tree
     */
    void exitGherkinDocument(GherkinParser.GherkinDocumentContext ctx);

    /**
     * Enter a parse tree produced by {@link GherkinParser#feature}.
     *
     * @param ctx the parse tree
     */
    void enterFeature(GherkinParser.FeatureContext ctx);

    /**
     * Exit a parse tree produced by {@link GherkinParser#feature}.
     *
     * @param ctx the parse tree
     */
    void exitFeature(GherkinParser.FeatureContext ctx);

    /**
     * Enter a parse tree produced by {@link GherkinParser#ruleBlock}.
     *
     * @param ctx the parse tree
     */
    void enterRuleBlock(GherkinParser.RuleBlockContext ctx);

    /**
     * Exit a parse tree produced by {@link GherkinParser#ruleBlock}.
     *
     * @param ctx the parse tree
     */
    void exitRuleBlock(GherkinParser.RuleBlockContext ctx);

    /**
     * Enter a parse tree produced by {@link GherkinParser#background}.
     *
     * @param ctx the parse tree
     */
    void enterBackground(GherkinParser.BackgroundContext ctx);

    /**
     * Exit a parse tree produced by {@link GherkinParser#background}.
     *
     * @param ctx the parse tree
     */
    void exitBackground(GherkinParser.BackgroundContext ctx);

    /**
     * Enter a parse tree produced by {@link GherkinParser#scenario}.
     *
     * @param ctx the parse tree
     */
    void enterScenario(GherkinParser.ScenarioContext ctx);

    /**
     * Exit a parse tree produced by {@link GherkinParser#scenario}.
     *
     * @param ctx the parse tree
     */
    void exitScenario(GherkinParser.ScenarioContext ctx);

    /**
     * Enter a parse tree produced by {@link GherkinParser#stepBody}.
     *
     * @param ctx the parse tree
     */
    void enterStepBody(GherkinParser.StepBodyContext ctx);

    /**
     * Exit a parse tree produced by {@link GherkinParser#stepBody}.
     *
     * @param ctx the parse tree
     */
    void exitStepBody(GherkinParser.StepBodyContext ctx);

    /**
     * Enter a parse tree produced by {@link GherkinParser#examplesBody}.
     *
     * @param ctx the parse tree
     */
    void enterExamplesBody(GherkinParser.ExamplesBodyContext ctx);

    /**
     * Exit a parse tree produced by {@link GherkinParser#examplesBody}.
     *
     * @param ctx the parse tree
     */
    void exitExamplesBody(GherkinParser.ExamplesBodyContext ctx);

    /**
     * Enter a parse tree produced by {@link GherkinParser#startingStep}.
     *
     * @param ctx the parse tree
     */
    void enterStartingStep(GherkinParser.StartingStepContext ctx);

    /**
     * Exit a parse tree produced by {@link GherkinParser#startingStep}.
     *
     * @param ctx the parse tree
     */
    void exitStartingStep(GherkinParser.StartingStepContext ctx);

    /**
     * Enter a parse tree produced by {@link GherkinParser#anyStep}.
     *
     * @param ctx the parse tree
     */
    void enterAnyStep(GherkinParser.AnyStepContext ctx);

    /**
     * Exit a parse tree produced by {@link GherkinParser#anyStep}.
     *
     * @param ctx the parse tree
     */
    void exitAnyStep(GherkinParser.AnyStepContext ctx);
}