package com.pdsl.exceptions;

public class SentenceNotFoundException extends RuntimeException {

    public SentenceNotFoundException(String e) {
        super(e);
    }
}
