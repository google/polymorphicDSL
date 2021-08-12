package com.pdsl.gherkin.filter;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.BitSet;

public class TagExpressionErrorListener implements ANTLRErrorListener {

    private final String tagExpression;
    private  static final Logger logger = LoggerFactory.getLogger(TagExpressionErrorListener.class);
    public TagExpressionErrorListener(String tagExpression) {
        this.tagExpression = tagExpression;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
				String message = String.format("Tag expression is invalid do to a syntax error!%n\tTag Expression: %s%n\tMessage: %s %n\tSuccessfully Lexed: %s %n\tError Started At: %s",
                        tagExpression, msg,
                        tagExpression.substring(0, charPositionInLine),
                        tagExpression.substring(charPositionInLine));
        throw new IllegalArgumentException(message, e);
    }

    @Override
    public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {
        if (logger.isWarnEnabled()) {
            logger.warn("Ambiguity in gherkin tag! at %s", tagExpression.substring(startIndex, stopIndex));
        }
    }

    @Override
    public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet conflictingAlts, ATNConfigSet configs) {
        if (logger.isTraceEnabled()) {
            logger.trace("Attempting full context at %s", tagExpression.substring(startIndex, stopIndex));
        }
    }

    @Override
    public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction, ATNConfigSet configs) {
        if (logger.isTraceEnabled()) {
            logger.trace("Context sensitive issue at %s", tagExpression.substring(startIndex, stopIndex));
        }
    }
}
