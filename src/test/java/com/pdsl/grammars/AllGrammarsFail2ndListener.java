package com.pdsl.grammars;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class AllGrammarsFail2ndListener implements AllGrammarsParserListener{
    private boolean first = true;

    @Override
    public void enterPolymorphicDslAllRules(AllGrammarsParser.PolymorphicDslAllRulesContext ctx) {
        if (first) {
            first = false;
        } else {
            first = true; // Reset for next test run
            assert false : "Every phrase after the first fails with this listener!";
        }
    }

    @Override
    public void exitPolymorphicDslAllRules(AllGrammarsParser.PolymorphicDslAllRulesContext ctx) {

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