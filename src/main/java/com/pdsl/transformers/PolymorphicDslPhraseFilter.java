package com.pdsl.transformers;

import org.antlr.v4.runtime.tree.ParseTree;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * A filterer of test content input that also can validate input belongs in a grammar.
 *
 * These objects form the cornerstone of how the PDSL framework follows the interface segregation principle, thus
 * sifting content out of a test not relevant for a specific context.
 */
public interface PolymorphicDslPhraseFilter {

    /**
     * Converts the input stream into parse trees understandable by an underlying grammar.
     *
     * A side effect of this is successfully returning the parse tree means that all phrases are valid
     * RunTime exceptions due to failed conversion should NOT be suppressed as their absence implies that the input is
     * fully contained within an underlying grammar
     *
     * @param testInput
     * @return
     */
    public Optional<List<ParseTree>> validateAndFilterPhrases(List<InputStream> testInput, String testId);
}
