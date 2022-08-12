package com.pdsl.grammars;

public class TestFrameworkException extends RuntimeException {

    public TestFrameworkException(String message, Exception e) {
        super(message, e);
    }
}