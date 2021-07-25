package com.pdsl.logging;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public class LoggingParseTreeListener implements ParseTreeListener {
    private static final String YELLOW = "\033[93m";
    private static final String RED = "\033[31;1m";
    private static final String GREEN = "\033[92;1;2m";
    private static final String CLEAR_LINE = "\033[1G";
    private static final String RESET_ANSI = "\033[0m";
    private final List<String> rules;
    private Logger logger = LoggerFactory.getLogger(LoggingParseTreeListener.class);
    private boolean odd = false;

    public LoggingParseTreeListener() {
        this.rules = new LinkedList<>();
    }

    public LoggingParseTreeListener(List<String> rules) {
        this.rules = rules;
    }

    @Override
    public void visitTerminal(TerminalNode node) {
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        if (!odd) {
            System.out.print(RED + node.getText() + RESET_ANSI);
        }
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        rules.add(ctx.getText());
        System.out.println(/*String.valueOf(deleteSequence) + */GREEN + ctx.getText() + RESET_ANSI);
    }
}
