package com.pdsl.specifications;

public class PolymorphicDslTransformationException extends RuntimeException {
    public PolymorphicDslTransformationException(String s, Exception e) {
        super(s, e);
    }

    public PolymorphicDslTransformationException(String s) {
        super(s);
    }
}
