package com.pdsl.testcases;

import com.pdsl.specifications.FilteredPhrase;

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
    public URI originalSource() {
        return testCase.originalSource();
    }

    @Override
    public List<String> unfilteredPhraseBody() {
        return testCase.unfilteredPhraseBody();
    }

    @Override
    public List<String> contextFilteredPhraseBody() {
        return testCase.contextFilteredPhraseBody();
    }

    @Override
    public String testTitle() {
        return testCase.testTitle();
    }

    @Override
    public Iterator<TestSection> getContextFilteredTestSectionIterator() {
        return testCase.getContextFilteredTestSectionIterator();
    }

    @Override
    public List<FilteredPhrase> filteredPhrases() {
        return testCase.filteredPhrases();
    }
}
