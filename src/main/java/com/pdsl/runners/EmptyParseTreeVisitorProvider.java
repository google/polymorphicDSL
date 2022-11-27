package com.pdsl.runners;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

import javax.inject.Provider;

class EmptyParseTreeVisitorProvider implements Provider<ParseTreeVisitor<Void>> {
    @Override
    public ParseTreeVisitor<Void> get() {
        return null;
    }
}
