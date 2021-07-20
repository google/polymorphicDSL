package com.pdsl.grammars;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import static com.google.common.truth.Truth.assertThat;

public class SimpleArithmeticImpl implements SimpleArithmeticListener {
    @Override
    public void enterIntegerValue(SimpleArithmeticParser.IntegerValueContext ctx) {

    }

    @Override
    public void exitIntegerValue(SimpleArithmeticParser.IntegerValueContext ctx) {

    }

    @Override
    public void enterMathExpression(SimpleArithmeticParser.MathExpressionContext ctx) {

    }

    @Override
    public void exitMathExpression(SimpleArithmeticParser.MathExpressionContext ctx) {
        int augent = Integer.parseInt(ctx.integerValue().get(0).getText());
        int addent = Integer.parseInt(ctx.integerValue().get(1).getText());
        int expectedSum = Integer.parseInt(ctx.integerValue().get(2).getText());
        assertThat(augent + addent).isEqualTo(expectedSum);
    }

    @Override
    public void enterPolymorphicDslAllRules(SimpleArithmeticParser.PolymorphicDslAllRulesContext ctx) {

    }

    @Override
    public void exitPolymorphicDslAllRules(SimpleArithmeticParser.PolymorphicDslAllRulesContext ctx) {

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
