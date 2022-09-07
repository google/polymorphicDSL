package com.pdsl.runners;

import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.TestSpecificationFactory;

import javax.inject.Provider;
import java.lang.annotation.Annotation;
import java.net.URI;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

class EmptyTestSpecificationFactory implements TestSpecificationFactory, Annotation {
    @Override
    public Optional<Collection<TestSpecification>> getTestSpecifications(Set<URI> testContent) {
        return Optional.empty();
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return EmptyTestSpecificationFactory.class;
    }

    static Provider<EmptyTestSpecificationFactory> getProvider() {
        return new Provider<EmptyTestSpecificationFactory>() {
            @Override
            public EmptyTestSpecificationFactory get() {
                return new EmptyTestSpecificationFactory();
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) { return false; }
        return o.getClass().equals(EmptyTestSpecificationFactory.class);
    }

    @Override
    public int hashCode() {
        return 7;
    }
}
