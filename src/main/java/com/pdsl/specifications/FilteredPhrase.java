package com.pdsl.specifications;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Optional;

/**
 * A container for both the text and ANTLR parse tree created from a sentence.
 */
public interface FilteredPhrase {
    String getPhrase();
    Optional<ParseTree> getParseTree();
}
