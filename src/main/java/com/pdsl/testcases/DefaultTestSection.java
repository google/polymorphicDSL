package com.pdsl.testcases;

import com.pdsl.specifications.Phrase;

import java.io.InputStream;
import java.util.Optional;

/**
 * A container of phrases representing a chunk of a test case.
 *
 * This may include phrases that the test case may not actually execute, but
 * were identified by a recognizer.
 */
public class DefaultTestSection implements TestSection {

    private final Optional<InputStream> metaData;
    private final Phrase phrase;

    /**
     * Creates a test section with the provided phrase.
     *
     * @param metaData additional information about the phrase
     * @param phrase an arbitrary sentence recognized by a parser
     */
    public DefaultTestSection(InputStream metaData, Phrase phrase) {
        this.metaData = Optional.ofNullable(metaData);
        this.phrase = phrase;
    }

    /**
     * Creates a test section with the provided phrase.
     *
     * @param phrase an arbitrary sentence recognized by a parser
     */
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
