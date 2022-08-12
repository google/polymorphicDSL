package com.pdsl.exceptions;

public class PolymorphicDslTestResourceException extends RuntimeException {
    public PolymorphicDslTestResourceException(String error) {
        super(error);
    }
    public PolymorphicDslTestResourceException(String error, Exception e) {
        super(error, e);
    }
}
