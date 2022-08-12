package com.pdsl.runners;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.atn.ATN;

class EmptyRecognizerLexer extends Lexer {
    @Override
    public String[] getRuleNames() {
        return new String[0];
    }

    @Override
    public String getGrammarFileName() {
        return null;
    }

    @Override
    public ATN getATN() {
        return null;
    }
}
