package com.pdsl.specifications;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Optional;

public interface FilteredPhrase {
    String getPhrase();
    Optional<ParseTree> getParseTree();
}
