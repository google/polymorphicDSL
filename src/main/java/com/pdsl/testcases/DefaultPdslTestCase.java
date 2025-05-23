package com.pdsl.testcases;

import com.google.common.base.Preconditions;
import com.pdsl.specifications.FilteredPhrase;

import java.io.InputStream;
import java.net.URI;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.*;
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

    public static final PdslTestCaseComparator DEFAULT_TEST_CASE_COMPARATOR = new PdslTestCaseComparator();

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


    /**
     * A comparator for sorting PDSL TestCase objects.
     * <p>
     * Sorts based on the URI source. In the event the URIs are equal apart from the fragment,
     * sorts based on the line number if this was provided as per the RFC 5147 standard.
     * <p>
     * If no line number is provided, sorts based on a simple comparison of the original source URIs as strings.
     */
    public static class PdslTestCaseComparator implements Comparator<TestCase> {

        private static final int NUMBER_INDEX = "line=".length();

        @Override
        public int compare(TestCase source1, TestCase source2) {
            int compareUris = source1.getOriginalSource().getPath().compareTo(
                    source2.getOriginalSource().getPath());
            // If the scenarios came from the same file have the most recent scenario first via line number
            if (compareUris == 0) {
                String fragment1 = source1.getOriginalSource().getFragment();
                String fragment2 = source2.getOriginalSource().getFragment();
                try {
                    return Integer.compare(Integer.parseInt(fragment1.substring(NUMBER_INDEX)),
                            Integer.parseInt(fragment2.substring(NUMBER_INDEX)));
                } catch(NumberFormatException e) {
                    // If there is no fragment specifying 'line=', then treat the URIs as strings.
                    return source1.getOriginalSource().toString().compareTo(
                            source2.getOriginalSource().toString());
                }
            }
            return compareUris;
        }
    }
}
