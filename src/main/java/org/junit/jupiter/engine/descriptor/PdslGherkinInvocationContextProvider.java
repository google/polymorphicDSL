package org.junit.jupiter.engine.descriptor;

import com.pdsl.executors.TraceableTestRunExecutor;
import com.pdsl.gherkin.DefaultGherkinTestSpecificationFactory;
import com.pdsl.gherkin.filter.GherkinTagFilterer;
import com.pdsl.gherkin.filter.GherkinTagsVisitorImpl;
import com.pdsl.gherkin.specifications.GherkinTestSpecificationFactory;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.testcases.PreorderTestCaseFactory;
import com.pdsl.testcases.TaggedTestCase;
import com.pdsl.testcases.TestCase;
import com.pdsl.testcases.TestCaseFactory;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;

import java.util.*;
import java.util.Map.Entry;

import java.net.URI;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A InvocationContextProvider that is optimized for producing TestCases from
 * Gherkin.
 *
 * The primary advantage is that a TestSpecifcationFactory and TestCaseFactory
 * do not need to be provided in any PdslConfigParameters.
 */
public abstract class PdslGherkinInvocationContextProvider extends PdslGeneralInvocationContextProvider {
    private static final Logger logger = LoggerFactory.getLogger(PdslGherkinInvocationContextProvider.class);
    private static final TestCaseFactory TEST_CASE_FACTORY = new PreorderTestCaseFactory();
    private final Map<List<String>, TestCase> duplicateTest = new HashMap<>();
    private static final GherkinTagFilterer gherkinTagFilterer = new GherkinTagsVisitorImpl();

    /**
     * Creates TestSpecifications using  a {@link
     * com.pdsl.gherkin.DefaultGherkinTestSpecificationFactory
     * DefaultGherkinTestSpecificationFactory}.
     */
    @Override
    protected Collection<TestSpecification> getTestSpecifications(PdslConfigParameter configParameter, PolymorphicDslPhraseFilter phraseFilter, Collection<URI> testResources) {
        GherkinTestSpecificationFactory gherkinTestSpecificationFactory = new DefaultGherkinTestSpecificationFactory.Builder(phraseFilter).build();
        Optional<Collection<TestSpecification>> testSpecifications = gherkinTestSpecificationFactory
                .getTestSpecifications(testResources.stream().collect(Collectors.toUnmodifiableSet()));
        if (testSpecifications.isEmpty()) {
            throw new IllegalStateException(String.format("No test specifications could be produced from the resources!%n"
                            + "Test Specification Factory: %s%n"
                            + "Resources: %s%n", configParameter.getSpecificationFactoryProvider().get().getClass(),
                    String.join(String.format("%n\t\t")), testResources));
        }
        return testSpecifications.get();
    }

    @Override
    protected Collection<TestCase> filterTestCases(Collection<TestCase> testCases, String tagExpression) {
        return testCases.stream()
                .filter(tc -> {
                    if (tc instanceof TaggedTestCase) {
                        return gherkinTagFilterer.tagExpressionMatchesPickle(new HashSet(((TaggedTestCase)tc).getTags()), tagExpression);
                    }
                    return true;
                }).collect(Collectors.toUnmodifiableList());
    }

    /**
     * Creates TestCases using a {@link com.pdsl.testcases.PreorderTestCaseFactory
     * PreorderTestCaseFactory} that is suitable for Gherkin.
     */
    @Override
    protected Collection<TestCase> getTestCases(PdslConfigParameter configParameter, Collection<TestSpecification> testSpecifications) {
        Collection<TestCase> testCases = TEST_CASE_FACTORY
            .processTestSpecification(testSpecifications);
        List<URI> duplicateUris = new ArrayList<>();
        // Remove any duplicates with the same filtered test body
        // In the case of gherkin, ignore leading and trailing whitespace on sentences
        for (TestCase testCase : testCases) {
            List<String> whitespaceInsensitiveSteps = testCase.getContextFilteredPhraseBody().stream()
                .map(String::strip)
                .collect(Collectors.toUnmodifiableList());
            if (duplicateTest.containsKey(whitespaceInsensitiveSteps)) {
                duplicateUris.add(testCase.getOriginalSource());
            } else {
                duplicateTest.put(whitespaceInsensitiveSteps, testCase);
            }
        }
        logger.info("[{}] duplicate tests filtered out", testCases.size() - duplicateTest.entrySet().size());
        duplicateUris.stream().forEach(uri -> logger.info(uri.toString()));
        return duplicateTest.entrySet().stream().map(Entry::getValue).collect(Collectors.toUnmodifiableSet());
    }

    protected PdslExecutable getPdslExecutable(TestCase testCase, PdslConfigParameter parameter, PdslTestParameter pdslTestParameter) {
        TraceableTestRunExecutor executor = parameter.getTestRunExecutor().isPresent() ? parameter.getTestRunExecutor().get().get()
                : DEFAULT_EXECUTOR;
        if (pdslTestParameter.getVisitor().isPresent()) {
            return new PdslExecutable(testCase, executor,
                    (Supplier<ParseTreeVisitor<?>>) pdslTestParameter.getVisitor().get(), parameter.getContext());
        }
        return new PdslExecutable(testCase, executor,
                parameter.getContext(), (Supplier<ParseTreeListener>) pdslTestParameter.getListener().get());
    }
}
