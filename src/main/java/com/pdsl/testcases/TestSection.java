package com.pdsl.testcases;

import com.google.common.base.Preconditions;
import com.pdsl.specifications.DefaultPhrase;
import com.pdsl.specifications.FilteredPhrase;
import com.pdsl.specifications.Phrase;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A fraction of a full test as a result of a Test Specification being processed by a Test Case Factory.
 *
 * TestSections are useful when a test specification has logic for shared behavior across tests (such as before all or
 * after all hooks with a main body).
 */
public interface TestSection {

    Optional<InputStream> getMetaData();
    Phrase getPhrase();

    static List<TestSection> convertBodyFragment(TestBodyFragment testBodyFragment) {
        Preconditions.checkNotNull(testBodyFragment);
        Preconditions.checkArgument(!testBodyFragment.getTestPhrases().isEmpty());
        List<TestSection> testSections = new ArrayList<>(testBodyFragment.getTestPhrases().size());
        List<FilteredPhrase> filteredPhrases = testBodyFragment.getTestPhrases().stream()
                .filter(filteredPhrase -> filteredPhrase.getParseTree().isPresent())
                .collect(Collectors.toUnmodifiableList());
        Preconditions.checkArgument(!filteredPhrases.isEmpty(),
                "Test Body Fragment must have at least one filtered phrase with a parse tree present");
        for (int i = 0; i < testBodyFragment.getTestPhrases().size(); i++) {
            FilteredPhrase filteredPhrase = testBodyFragment.getTestPhrases().get(i);
            if (filteredPhrase.getParseTree().isPresent()) {
                Phrase phrase = new DefaultPhrase(filteredPhrase.getParseTree().get(), i);
                testSections.add(
                        // If its the first phrase with a parse tree add metadata, else just the phrase
                        filteredPhrase == filteredPhrases.get(0) && testBodyFragment.getMetaData().isPresent()
                                ? new DefaultTestSection(testBodyFragment.getMetaData().get(), phrase)
                                : new DefaultTestSection(phrase));
            }
        }
        return testSections;
    }
}
