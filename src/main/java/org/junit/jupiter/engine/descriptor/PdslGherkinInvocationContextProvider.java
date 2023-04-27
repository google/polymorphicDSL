package org.junit.jupiter.engine.descriptor;

import com.pdsl.gherkin.DefaultGherkinTestSpecificationFactory;
import com.pdsl.gherkin.specifications.GherkinTestSpecificationFactory;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.testcases.PreorderTestCaseFactory;
import com.pdsl.testcases.TestCase;
import com.pdsl.testcases.TestCaseFactory;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A InvocationContextProvider that is optimized for producing TestCases from
 * Gherkin.
 *
 * The primary advantage is that a TestSpecifcationFactory and TestCaseFactory
 * do not need to be provided in any PdslConfigParameters.
 */
public abstract class PdslGherkinInvocationContextProvider extends PdslGeneralInvocationContextProvider {

    private static final TestCaseFactory TEST_CASE_FACTORY = new PreorderTestCaseFactory();

    /**
     * Creates TestSpecifications using  a {@link
     * com.pdsl.gherkin.DefaultGherkinTestSpecificationFactory
     * DefaultGherkinTestSpecificationFactory}.
     */
    @Override
    protected Collection<TestSpecification> getTestSpecifications(PdslConfigParameter configParameter, PdslTestParameter pdslTestParameter, PolymorphicDslPhraseFilter phraseFilter, Collection<URI> testResources) {
        GherkinTestSpecificationFactory gherkinTestSpecificationFactory = new DefaultGherkinTestSpecificationFactory.Builder(phraseFilter).build();
        Optional<Collection<TestSpecification>> testSpecifications = gherkinTestSpecificationFactory
                .getTestSpecifications(testResources.stream().collect(Collectors.toUnmodifiableSet()));
        if (testSpecifications.isEmpty()) {
            throw new IllegalStateException(String.format("No test specifications could be produced from the resources!%n"
                            + "Test Specification Factory: %s%n"
                            + "Resources: %s%n", configParameter.getSpecificationFactoryProvider().get().getClass(),
                    String.join(String.format("%n\t\t")), testResources));
        }
        Optional<Collection<TestSpecification>> filteredSpecifications = gherkinTestSpecificationFactory.filterGherkinTestSpecificationsByTagExpression(testSpecifications.get(), pdslTestParameter.getTagExpression());
        if (filteredSpecifications.isEmpty()) {
            throw new IllegalStateException(String.format("All scenarios were filtered out!%n"
            + "Tag Expression: %s%n"
                            + "Resources: %s%n", pdslTestParameter.getTagExpression(),
                    String.join(String.format("%n\t\t")), testResources));
        }
        return testSpecifications.get();
    }

    /**
     * Creates TestCases using a {@link com.pdsl.testcases.PreorderTestCaseFactory
     * PreorderTestCaseFactory} that is suitable for Gherkin.
     */
    @Override
    protected Collection<TestCase> getTestCases(PdslConfigParameter configParameter, Collection<TestSpecification> testSpecifications) {
        return TEST_CASE_FACTORY.processTestSpecification(testSpecifications);
    }
}
