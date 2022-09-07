package com.pdsl.exceptions;

/** Error thrown when the PDSL framework itself is at fault and the user cannot be expected to solve the poblem. */
public class PolymorphicDslFrameworkException extends RuntimeException {
    public PolymorphicDslFrameworkException(String error, Exception e) {
        super(error, e);
    }
}
