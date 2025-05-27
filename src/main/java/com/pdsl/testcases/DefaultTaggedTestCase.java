package com.pdsl.testcases;

import com.pdsl.specifications.FilteredPhrase;

import java.net.URI;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/** A test case that has arbitrary tags associated with it. */
public class DefaultTaggedTestCase implements TaggedTestCase {

    private final TestCase testCase;
    private final Collection<String> tags; // Keep to avoid the need for casting and checking generic types
    public DefaultTaggedTestCase(TestCase testCase, Collection<String> tags) {
        this.testCase = testCase;
        this.tags = tags;
        testCase.getMetadata().put(TestCase.DEFAULT_TAGS, this.tags); // Add a reference just to be thorough
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

    @Override
    public List<FilteredPhrase> getFilteredPhrases() {
        return testCase.getFilteredPhrases();
    }

    @Override
    public Map<String, Object> getMetadata() {
        return testCase.getMetadata();
    }
}
