package com.pdsl.testcases;

import com.google.common.base.Preconditions;
import com.pdsl.specifications.FilteredPhrase;

import java.io.InputStream;
import java.net.URI;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A standard test case used by PDSL for test case execution.
 */
public class DefaultPdslTestCase implements TestCase {

    private final String testCaseTitle;
    private final List<TestBodyFragment> testBodyFragments;
    private final List<String> unfilteredPhraseBody;
    private final List<String> contextFilteredPhraseBody;
    private final List<FilteredPhrase> phrasesToTestSections;
    private final URI source;

    /**
     * Creates a PDSL test case.
     *
     * @param testCaseTitle title of the test case
     * @param testBodyFragments chunks of phrases that may or may not execute
     * @param source the original source this test case was created from
     */
    public DefaultPdslTestCase(String testCaseTitle, List<TestBodyFragment> testBodyFragments, URI source) {
        String errMessage = "Test case title cannot be empty or null!";
        Preconditions.checkNotNull(testCaseTitle, errMessage);
        Preconditions.checkNotNull(source);
        Preconditions.checkArgument(!testCaseTitle.isEmpty(), errMessage);
        Preconditions.checkNotNull(testBodyFragments, errMessage);
        Preconditions.checkArgument(!testBodyFragments.isEmpty(), errMessage);
        this.source = source;
        this.testBodyFragments = testBodyFragments;
        this.testCaseTitle = testCaseTitle;
        this.phrasesToTestSections = testBodyFragments.stream()
                .map(TestBodyFragment::getTestPhrases)
                .flatMap(Collection::stream)
                .toList();
        this.unfilteredPhraseBody = phrasesToTestSections.stream()
                .map(FilteredPhrase::getPhrase)
                .toList();
        this.contextFilteredPhraseBody = phrasesToTestSections.stream()
                .map(FilteredPhrase::getParseTree)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(ParseTree::getText)
                .toList();
    }

    @Override
    public URI getOriginalSource() { return source; }
    @Override
    public List<String> getUnfilteredPhraseBody() {
        return unfilteredPhraseBody;
    }

    @Override
    public List<String> getContextFilteredPhraseBody() {
        return contextFilteredPhraseBody;
    }

    @Override
    public String getTestTitle() {
        return testCaseTitle;
    }

    @Override
    public Iterator<TestSection> getContextFilteredTestSectionIterator() {
        return getTestSectionStream()
                .toList()
                .iterator();
    }

    private Stream<TestSection> getTestSectionStream() {
        return testBodyFragments.stream()
                .map(TestSection::convertBodyFragment)
                .flatMap(Collection::stream);
    }

    public List<Optional<InputStream>> getUnfilteredTestSectionsMetaData() {
        return testBodyFragments.stream()
                .map(TestBodyFragment::getMetaData)
                .toList();
    }

    @Override
    public List<FilteredPhrase> getFilteredPhrases() {
       return phrasesToTestSections;
    }
}
