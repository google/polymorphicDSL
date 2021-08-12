package com.pdsl.api;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A listener that keeps track of each phrase visited.
 * <p>
 * This is used for assertions in testing
 */
public class StepCounterListener implements ParseTreeListener {
    private int stepsEncountered = 0;
    private Queue<String> steps = new LinkedList<>();

    public int getPhrasesEncountered() {
        return stepsEncountered;
    }

    public Queue<String> getStepsInOrderRun() {
        return steps;
    }

    @Override
    public void visitTerminal(TerminalNode node) {

    }

    @Override
    public void visitErrorNode(ErrorNode node) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        stepsEncountered++;
        steps.add(ctx.getText());
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {

    }
}
