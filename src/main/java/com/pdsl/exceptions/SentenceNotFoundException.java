package com.pdsl.exceptions;

/**
 * Error thrown when PDSL is unable to lex a sentence from some input.
 *
 * This essentially means that there are sentences included in the test specification or test resource that
 * do not exist in the grammar.
 */
public class SentenceNotFoundException extends RuntimeException {

    public SentenceNotFoundException(String e) {
        super(e);
    }
}
