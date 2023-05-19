package com.pdsl.testcases;

import com.google.common.base.Preconditions;
import com.pdsl.specifications.FilteredPhrase;
import java.net.URI;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DefaultPdslTestCase implements TestCase {

    private final String testCaseTitle;
    private final List<TestBodyFragment> testBodyFragments;
    private final List<String> unfilteredPhraseBody;
    private final List<String> contextFilteredPhraseBody;
    private final URI source;
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
        List<FilteredPhrase> filteredPhrases = testBodyFragments.stream()
                .map(TestBodyFragment::getTestPhrases)
                .flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableList());
        this.unfilteredPhraseBody = filteredPhrases.stream()
                .map(FilteredPhrase::getPhrase)
                .collect(Collectors.toUnmodifiableList());
        this.contextFilteredPhraseBody = filteredPhrases.stream()
                .map(FilteredPhrase::getParseTree)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(ParseTree::getText)
                .collect(Collectors.toUnmodifiableList());
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
        return testBodyFragments.stream()
                .map(TestSection::convertBodyFragment)
                .flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableList())
                .iterator();
    }
}
