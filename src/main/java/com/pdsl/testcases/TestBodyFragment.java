package com.pdsl.testcases;

import com.pdsl.specifications.FilteredPhrase;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;


public class TestBodyFragment {

    private final Optional<InputStream> metadata;
    private final List<FilteredPhrase> testPhrases;

    public TestBodyFragment(InputStream metadata, List<FilteredPhrase> testPhrases) {
        this.metadata = Optional.ofNullable(metadata);
        this.testPhrases = testPhrases;
    }

    public TestBodyFragment(List<FilteredPhrase> testPhrases) {
        this.metadata = Optional.empty();
        this.testPhrases = testPhrases;
    }

    public Optional<InputStream> getMetaData() {
        return metadata;
    }

    public List<FilteredPhrase> getTestPhrases() {
        return testPhrases;
    }

}
