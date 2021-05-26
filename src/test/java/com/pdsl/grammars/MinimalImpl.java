package com.pdsl.grammars;

import com.pdsl.logging.AnsiTerminalColorHelper;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinimalImpl implements PolymorphicDslMinimalParserListener {

    private int stepCounter = 0;
    private Logger logger = LoggerFactory.getLogger(MinimalImpl.class);

    public int getStepCount() {
        return stepCounter;
    }

    @Override
    public void enterPolymorphicDslAllRules(PolymorphicDslMinimalParser.PolymorphicDslAllRulesContext ctx) {

    }

    @Override
    public void exitPolymorphicDslAllRules(PolymorphicDslMinimalParser.PolymorphicDslAllRulesContext ctx) {

    }

    @Override
    public void enterMinimal(PolymorphicDslMinimalParser.MinimalContext ctx) {
        stepCounter++;
        System.out.println(AnsiTerminalColorHelper.BRIGHT_BLUE + ctx.getText() + AnsiTerminalColorHelper.BRIGHT_RED + stepCounter + AnsiTerminalColorHelper.RESET);
    }

    @Override
    public void exitMinimal(PolymorphicDslMinimalParser.MinimalContext ctx) {
        System.out.println(AnsiTerminalColorHelper.CYAN + "Exiting minimal rule" + AnsiTerminalColorHelper.RESET);
    }

    @Override
    public void visitTerminal(TerminalNode node) {

    }

    @Override
    public void visitErrorNode(ErrorNode node) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {

    }
}
