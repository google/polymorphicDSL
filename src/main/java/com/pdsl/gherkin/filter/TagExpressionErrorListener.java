package com.pdsl.gherkin.filter;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

import java.util.BitSet;

public class TagExpressionErrorListener implements ANTLRErrorListener {

    private String tagExpression;

    public TagExpressionErrorListener(String tagExpression) {
        this.tagExpression = tagExpression;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        throw new IllegalArgumentException(
                String.format("Tag expression is invalid do to a syntax error!%n\tTag Expression: %s%n\tMessage: %s %n\tSuccessfully Lexed: %s %n\tError Started At: %s",
                        tagExpression, msg,
                        tagExpression.substring(0, charPositionInLine),
                        tagExpression.substring(charPositionInLine)), e);
    }

    @Override
    public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {}

    @Override
    public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet conflictingAlts, ATNConfigSet configs) {

    }

    @Override
    public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction, ATNConfigSet configs) {

    }
}
