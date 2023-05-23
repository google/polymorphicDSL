package com.pdsl.specifications;

import org.antlr.v4.runtime.tree.ParseTree;

/**
 * A standard phrase to execute as part of a TestCase.
 */
public class DefaultPhrase implements Phrase {

    private final ParseTree parseTree;
    private final int indexId;

    /**
     * Creates a phrase to use in a test case.
     *
     * @param parseTree the ANTLR parse tree created from parsing a phrase
     * @param indexId the sequential position of this phrase relative to the others
     */
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
