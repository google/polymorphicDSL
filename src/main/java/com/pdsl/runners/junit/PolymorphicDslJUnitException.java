package com.pdsl.runners.junit;

public class PolymorphicDslJUnitException extends RuntimeException {
    public PolymorphicDslJUnitException(String msg, Exception e) {
        super(msg, e);
    }
}
