package com.pdsl.runners;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.*;

import javax.inject.Provider;

class EmptyParseTreeListenerProvider implements Provider<ParseTreeListener> {

    @Override
    public ParseTreeListener get() {
        return null;
    }
}
