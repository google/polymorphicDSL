package com.pdsl.runners;

import com.google.common.base.Preconditions;
import org.junit.jupiter.engine.descriptor.JupiterDescriptorKey;

/**
 * DO NOT USE, PDSL Framework use only.
 *
 * The SharedTestSuiteVisitor is useful for centralizing logic for generating test cases, which is by far
 * the most logically complex part of any of the runners.
 *
 * However, a lot of the logic depends on classes with default visibility not intended for consumption by anyone else.
 * In order to expose these classes while still keeping them encapsulated we limit the constructor to only be
 * usable by the JUnit5 framework.
 */
public class JUnit5DefaultPackageAccessor {

    public JUnit5DefaultPackageAccessor(JupiterDescriptorKey key) {
        Preconditions.checkNotNull(key);
    }

    public Class<EmptyRecognizerLexer> getEmptyRecognizerLexerClass() {
        return EmptyRecognizerLexer.class;
    }

    public Class<EmptyRecognizerParser> getEmptyRecognizerParserClass() {
        return EmptyRecognizerParser.class;
    }
}
