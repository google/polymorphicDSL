package com.pdsl.runners;

import javax.inject.Provider;
import java.lang.annotation.Annotation;

class EmptySpecificationFactoryProvider implements Annotation, Provider {
    public EmptySpecificationFactoryProvider(){}
    private static final EmptyTestSpecificationFactory INSTANCE = new EmptyTestSpecificationFactory();
    @Override
    public Class<? extends Annotation> annotationType() {
        return PdslConfiguration.class;
    }

    @Override
    public Object get() {
        return INSTANCE;
    }

    @Override
    public int hashCode() {
        return INSTANCE.hashCode();
    }
    @Override
    public boolean equals(Object o) {
        if (o == null) { return false; }
        return o.equals(INSTANCE);
    }


}
