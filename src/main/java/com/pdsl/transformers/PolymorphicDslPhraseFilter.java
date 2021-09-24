package com.pdsl.transformers;

import com.pdsl.specifications.FilteredPhrase;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * A filterer of test content input that also can validate that the input belongs in a grammar.
 * <p>
 * These objects form the cornerstone of how the PDSL framework follows the interface segregation principle, i.e.
 * sifting content out of a test not relevant for a specific context.
 */
public interface PolymorphicDslPhraseFilter {

    /**
     * Converts the input stream into parse trees understandable by an underlying grammar.
     * <p>
     * Some phrases may be filtered out because they are deemed irrelevant to some context by the underlying grammar.
     *
     * @param testInput
     * @return An optional containing the FilteredPhrases which indicate whether or not they were recognized by the grammar
     */
    Optional<List<FilteredPhrase>> filterPhrases(List<InputStream> testInput);

}
