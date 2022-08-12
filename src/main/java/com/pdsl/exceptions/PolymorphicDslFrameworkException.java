package com.pdsl.exceptions;

public class PolymorphicDslFrameworkException extends RuntimeException {
    public PolymorphicDslFrameworkException(String error, Exception e) {
        super(error, e);
    }
}
