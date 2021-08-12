package com.pdsl.testcases;

import com.pdsl.specifications.Phrase;

import java.io.InputStream;
import java.util.Optional;

public class DefaultTestSection implements TestSection {

    private final Optional<InputStream> metaData;
    private final Phrase phrase;

    public DefaultTestSection(InputStream metaData, Phrase phrase) {
        this.metaData = Optional.ofNullable(metaData);
        this.phrase = phrase;
    }

    public DefaultTestSection(Phrase phrase) {
        this.metaData = Optional.empty();
        this.phrase = phrase;
    }

    @Override
    public Optional<InputStream> getMetaData() {
        return metaData;
    }

    @Override
    public Phrase getPhrase() {
        return phrase;
    }
}
