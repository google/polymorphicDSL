package com.pdsl.testcases;

import com.pdsl.specifications.FilteredPhrase;

import java.net.URI;
import java.util.Iterator;
import java.util.List;

/**
 * An executable test for PDSL to process with a test case executor of some kind.
 *
 * {@see com.pdsl.executors.TraceableTestRunExecutor}
 */
public interface TestCase {

    /**
     * Returns the original source file this test was created from.
     *
     * Note that the URI return might differ from the original source file because fragments might be added
     * to convey additional information about a specific.
     * (e.g. ./Some.feature could become ./Some.feature#line=3 to refer to the specific scenario in the file)
     *
     * @return the URI the test case was produced from
     */
    URI getOriginalSource();

    /**
     * Provides the original phrases that this test case was created from.
     *
     * No metadata or any other residual data from the original test resource this test case was created from is included.
     * @return all the phrases that the recognizer encountered when making the test
     */
    List<String> getUnfilteredPhraseBody();

    /**
     * Provides the relevant phrases for this test case which exclude all phrases that are missing from the DSL context
     * that this test was created from.
     *
     * This may effectively serve as an identifier for the test and should be used instead of {@link #getTestTitle()}
     * for doing comparisons between test cases to see if they are duplicates in practice. These are essentially
     * the textual representations of the phrases in this test case that a
     * {@link com.pdsl.executors.PolymorphicDslTestExecutor} can execute with the iterator returned from
     * {@link #getContextFilteredTestSectionIterator()}.
     *
     * @return the phrases that PDSL views as executable for this test case
     */
    List<String> getContextFilteredPhraseBody();

    /**
     * Provides a name for this test case.
     *
     * <p>The Test Title is <b>not</b> guaranteed to be unique.
     * @return the name of this test case
     */
    String getTestTitle();

    /**
     * Provides an iterator that will only iterate over Test Sections that are expected to be in the test case.
     *
     * <p>The phrases returned correspond to those returned by {@link #getContextFilteredPhraseBody()} (i.e, any phrases
     * filtered out because they are not relevant to this test cases context will not be present).
     *
     * This iterator is intended mechanism by which a {@link com.pdsl.executors.PolymorphicDslTestExecutor} grabs the
     * phrases it needs to execute this test case.
     *
     * @return an iterator for all <b>filtered</b> test phrases
     */
    Iterator<TestSection> getContextFilteredTestSectionIterator();

    /**
     * Provides a list of FilteredPhrases that represent this test case.
     *
     * <p>Each FilteredPhrase represents a potential parse tree to be executed by a visitor/listener.
     * It is possible that some phrase was not recognized by the parser that created this test case.
     *
     * This feature was added to make it easier for multiple listeners/visitors to coordinate with each other when
     * processing parse trees created from the same input. This is particularly necessary when they recognize different
     * subsets of the same input.
     *
     * There should be a 1:1 mapping with the elements produced by this method and the strings returned by
     * getUnfilteredPhraseBody()
     *
     * @return List<FilteredPhrase>  the filtered phrases from all phrases that are associated with the test case
     */
    List<FilteredPhrase> getFilteredPhrases();
}
