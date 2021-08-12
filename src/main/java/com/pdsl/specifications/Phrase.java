package com.pdsl.specifications;

import org.antlr.v4.runtime.tree.ParseTree;

/**
 * A string that was successful parsed by the DSL with a resulting phrase tree and an index.
 *
 * <p>When a {@link com.pdsl.testcases.TestCaseFactory} is processing a {@link com.pdsl.specifications.TestSpecification}
 * it must determine which phrases are relevant by context. Those that are deemed relevant are converted into Phrases
 * that will ultimately form the most important part of a {@link com.pdsl.testcases.TestCase}.
 */
public interface Phrase {

    ParseTree getParseTree();

    /**
     * The phrase index from the <i>unfiltered</i>, original test resource.
     *
     * <p>This index ID is therefore only suitable for describing the phrase index as it existed previously in its
     * {@link com.pdsl.specifications.TestSpecification} when it was being interpreted by a
     * {@link com.pdsl.testcases.TestCaseFactory}.
     *
     * <p>This is useful for associating a failed step with the original context from which it was derived; it is <i>NOT</i>
     * to be used to see if it is the <i>nth</i> element of a {@link com.pdsl.testcases.TestSection} as this cannot
     * be guaranteed.
     *
     * @return the index this phrase had prior to context filtering
     */
    int getPrefilteredIndex();
}
