package com.pdsl.transformers;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.InputStream;
import java.lang.reflect.Method;
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
     * Some phrases may be filtered out because they are deemed irrelevant to some context by the underlying grammar
     *
     * A side effect of this is successfully returning the parse tree means that all phrases are valid
     * RunTime exceptions due to failed conversion should NOT be suppressed as their absence implies that the input is
     * fully contained within an underlying grammar
     *
     * @param testInput
     * @return
     */
    Optional<List<ParseTree>> validateAndFilterPhrases(List<InputStream> testInput);

    /**
     * Converts the input stream into parse trees understandable by an underlying grammar.
     *
     * Every provided input stream will map to a parse tree; if the input stream is NOT recognized by the underlying grammar
     * a runtime exception will be thrown
     * @param testInput
     * @return
     */
    List<ParseTree> validatePhrases(List<InputStream> testInput);

}
