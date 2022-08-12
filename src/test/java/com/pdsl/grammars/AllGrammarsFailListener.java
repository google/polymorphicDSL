package com.pdsl.grammars;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class AllGrammarsFailListener implements AllGrammarsParserListener {
    @Override
    public void enterPolymorphicDslAllRules(AllGrammarsParser.PolymorphicDslAllRulesContext ctx) {

    }

    @Override
    public void exitPolymorphicDslAllRules(AllGrammarsParser.PolymorphicDslAllRulesContext ctx) {
        assert(false) : "Test Listeners fails all steps by default!";
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