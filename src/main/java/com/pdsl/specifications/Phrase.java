package com.pdsl.specifications;

import org.antlr.v4.runtime.tree.ParseTree;

public interface Phrase {
    ParseTree getParseTree();

    int getIndexId();
}
