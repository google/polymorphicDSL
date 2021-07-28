package com.pdsl.gherkin.specifications;

import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.TestSpecificationFactory;

import java.util.Collection;
import java.util.Optional;

public interface GherkinTestSpecificationFactory extends TestSpecificationFactory {
    Optional<Collection<TestSpecification>> filterGherkinTestSpecificationsByTagExpression(
            Collection<TestSpecification> testSpecification, String tagExpression);
}
