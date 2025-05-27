package com.pdsl.testcases;

import com.pdsl.specifications.Phrase;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A container of phrases representing a chunk of a test case.
 *
 * This may include phrases that the test case may not actually execute, but
 * were identified by a recognizer.
 */
public class DefaultTestSection implements TestSection {


    private final Phrase phrase;
    private final Optional<Map<String, Object>> metadata;
    private final Optional<InputStream> longDescription;
    /**
     * Creates a test section with the provided phrase.
     *
     * @param longDescription additional information about the phrase
     * @param phrase an arbitrary sentence recognized by a parser
     */
    public DefaultTestSection(InputStream longDescription, Phrase phrase) {
        Map<String, Object> metadataMap = new ConcurrentHashMap<>(1);
        metadataMap.put(TestCase.STANDARD_LONG_DESCRIPTION_KEY, longDescription);
        this.metadata = Optional.of(metadataMap);
        this.phrase = phrase;
        this.longDescription = Optional.ofNullable(longDescription);
    }

    /**
     * Creates a test section with the provided phrase.
     *
     * @param phrase an arbitrary sentence recognized by a parser
     */
    public DefaultTestSection(Phrase phrase) {
        this.metadata = Optional.empty();
        this.phrase = phrase;
        this.longDescription = Optional.empty();
    }

    @Override
    public Optional<InputStream> getMetaData() {
        return longDescription;
    }

    @Override
    public Optional<Map<String, Object>> getSectionMetadata() {
        return metadata;
    }

    @Override
    public Phrase getPhrase() {
        return phrase;
    }
}
