package com.pdsl.testcases;

import java.util.Iterator;
import java.util.List;

/**
 * An executable test for PDSL to process with a test case executor of some kind.
 *
 * @see {com.pdsl.executors.TraceableTestRunExecutor}
 */
public interface TestCase {

    /**
     * Provides the original phrases that this test case was created from.
     *
     * No metadata or any other residual data from the original test resource this test case was created from is included.
     * @return
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
     * @return
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
}
