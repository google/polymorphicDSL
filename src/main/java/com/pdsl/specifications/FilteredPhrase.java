package com.pdsl.specifications;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Optional;

/**
 * A container for both the text and ANTLR parse tree created from a sentence.
 */
public interface FilteredPhrase {

    /**
     * Gets the raw string the parser was recognizing.
     *
     * @return phrase as a string
     */
    String getPhrase();

    /**
     * Gets the parse tree if the phrase was recognized.
     *
     * @return the parse tree if recognized, otherwise an empty optional
     */
    Optional<ParseTree> getParseTree();
}
