package com.pdsl.specifications;

import org.antlr.v4.runtime.tree.ParseTree;

public class DefaultPhrase implements Phrase {
    private final ParseTree parseTree;
    private final int indexId;

    public DefaultPhrase(ParseTree parseTree, int indexId) {
        this.parseTree = parseTree;
        this.indexId = indexId;
    }

    @Override
    public ParseTree getParseTree() {
        return parseTree;
    }

    @Override
    public int getPrefilteredIndex() {
        return indexId;
    }
}
