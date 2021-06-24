package com.pdsl.gherkin.specifications;

import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.TestSpecificationFactory;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface GherkinTestSpecificationFactory extends TestSpecificationFactory {
    public Optional<TestSpecification> filterGherkinTestSpecificationsByTagExpression(
            TestSpecification testSpecification, String tagExpression);
}
