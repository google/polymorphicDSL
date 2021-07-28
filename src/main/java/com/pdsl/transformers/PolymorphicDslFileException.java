package com.pdsl.transformers;

public class PolymorphicDslFileException extends RuntimeException {
    public PolymorphicDslFileException(String message, Exception e) {
        super(message, e);
    }
}
