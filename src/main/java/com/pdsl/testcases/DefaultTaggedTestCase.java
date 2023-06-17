package com.pdsl.testcases;

import java.net.URI;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/** A test case that has arbitrary tags associated with it. */
public class DefaultTaggedTestCase implements TaggedTestCase {

    private final TestCase testCase;
    private final Collection<String> tags;

    public DefaultTaggedTestCase(TestCase testCase, Collection<String> tags) {
        this.testCase = testCase;
        this.tags = tags;
    }

    @Override
    public Collection<String> getTags() {
        return tags;
    }

    @Override
    public URI getOriginalSource() {
        return testCase.getOriginalSource();
    }

    @Override
    public List<String> getUnfilteredPhraseBody() {
        return testCase.getUnfilteredPhraseBody();
    }

    @Override
    public List<String> getContextFilteredPhraseBody() {
        return testCase.getContextFilteredPhraseBody();
    }

    @Override
    public String getTestTitle() {
        return testCase.getTestTitle();
    }

    @Override
    public Iterator<TestSection> getContextFilteredTestSectionIterator() {
        return testCase.getContextFilteredTestSectionIterator();
    }
}
