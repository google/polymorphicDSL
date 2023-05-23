package com.pdsl.gherkin.specifications;

import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.TestSpecificationFactory;

import java.util.Collection;
import java.util.Optional;

/**
 * A test specification factory that provides additional gherkin features.
 */
public interface GherkinTestSpecificationFactory extends TestSpecificationFactory {

    /**
     * Analyzes test specifications and removes those that don't match the provided tag expression.
     *
     * e.g., the expression "＠UnitOnly" will only run gherkin pickles with those tags.
     *
     * Note the gherkin language specification supports the logical operators and, or + not.
     * They may also be parenthesised, e.g, "@MyApp and not @YourApp or (@TheirApp and @MyApp)"
     *
     * @param testSpecifications the specifications to filter
     * @param tagExpression the tag expression used to filter
     * @return an optional containing matched test specifications, or empty if none found
     */
    Optional<Collection<TestSpecification>> filterGherkinTestSpecificationsByTagExpression(
            Collection<TestSpecification> testSpecifications, String tagExpression);
}
