package com.pdsl.gherkin.executors;

import com.pdsl.executors.DefaultPolymorphicDslTestExecutor;
import com.pdsl.executors.TraceableTestRunExecutor;
import com.pdsl.gherkin.DefaultGherkinTestSpecificationFactory;
import com.pdsl.gherkin.PdslGherkinInterpreterImpl;
import com.pdsl.gherkin.PdslGherkinListenerImpl;
import com.pdsl.gherkin.PickleJarFactory;
import com.pdsl.gherkin.specifications.GherkinTestSpecificationFactory;
import com.pdsl.logging.PdslThreadSafeOutputStream;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.reports.TestRunResults;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.testcases.PreorderTestCaseFactory;
import com.pdsl.testcases.TestCase;
import com.pdsl.testcases.TestCaseFactory;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public class GherkinTestExecutor implements TraceableTestRunExecutor {

    private static final PickleJarFactory pickleJarFactory = new PickleJarFactory(new PdslGherkinInterpreterImpl(), new PdslGherkinListenerImpl(), StandardCharsets.UTF_8);
    private final PolymorphicDslPhraseFilter phraseFilter;
    private final GherkinTestSpecificationFactory testSpecificationFactory;
    private final TestCaseFactory testCaseFactory = new PreorderTestCaseFactory();
    private final Logger logger = LoggerFactory.getLogger(GherkinTestExecutor.class);
    private final TraceableTestRunExecutor executor = new DefaultPolymorphicDslTestExecutor();

    public <SG extends Parser, SL extends Lexer> GherkinTestExecutor(Class<SG> subgrammarParser, Class<SL> subgrammarLexer) {
        phraseFilter = new DefaultPolymorphicDslPhraseFilter(subgrammarParser, subgrammarLexer);
        testSpecificationFactory = new DefaultGherkinTestSpecificationFactory.Builder(phraseFilter)
            .withPickleJarFactory(pickleJarFactory)
            .build();
    }

    public GherkinTestExecutor(PolymorphicDslPhraseFilter phraseFilter) {
        this.phraseFilter = phraseFilter;
        testSpecificationFactory = new DefaultGherkinTestSpecificationFactory.Builder(phraseFilter)
                .withPickleJarFactory(pickleJarFactory).build();
    }

    public TestRunResults processFilesAndRunTests(Set<URI> testResources, String tagExpression,
                                                                ParseTreeListener grammarListener) {
        // Use the file locations and convert the feature files to test specifications
        Optional<Collection<TestSpecification>> testSpecificationOptional = testSpecificationFactory.getTestSpecifications(testResources);
        if (testSpecificationOptional.isEmpty()) {
            throw new IllegalStateException("Test resources could not be converted to a Test Specification");
        }
        // If tag expressions were provided filter the test specification
        Optional<Collection<TestSpecification>> filteredSpecification =
                testSpecificationFactory.filterGherkinTestSpecificationsByTagExpression(testSpecificationOptional.get(), tagExpression);
        if (filteredSpecification.isEmpty()) {
            logger.warn("All tests were filtered out! Nothing to execute!");
            return new PolymorphicDslTestRunResults(new PdslThreadSafeOutputStream(), "NONE");
        } else {
            Collection<TestSpecification> testSpecifications = filteredSpecification.get();
            // Convert the test specifications into test cases
            Collection<TestCase> testCases = testCaseFactory.processTestSpecification(testSpecifications);
            //Finally execute the tests
            return executor.runTests(testCases, grammarListener);
        }
    }

    public TestRunResults processFilesAndRunTests(Set<URI> testResources,
                                                  ParseTreeListener grammarListener, String tagExpression) {
        return processFilesAndRunTests(testResources, tagExpression, grammarListener);
    }

    public TestRunResults processFilesAndRunTests(Set<URI> testResources, ParseTreeListener grammarListener) {
        return processFilesAndRunTests(testResources, grammarListener, "");
    }

    @Override
    public TestRunResults runTests(Collection<TestCase> testCases, ParseTreeListener grammarListener) {
        return executor.runTests(testCases, grammarListener);
    }

    @Override
    public TestRunResults runTests(Collection<TestCase> testCases, ParseTreeVisitor subgrammarVisitor) {
        return executor.runTests(testCases, subgrammarVisitor);
    }


    @Override
    public MetadataTestRunResults runTestsWithMetadata(Collection<TestCase> testCases, ParseTreeListener subgrammarListener, String context) {
        return executor.runTestsWithMetadata(testCases, subgrammarListener, context);
    }

    @Override
    public MetadataTestRunResults runTestsWithMetadata(Collection<TestCase> testCases, ParseTreeVisitor<?> visitor, String context) {
        return executor.runTestsWithMetadata(testCases, visitor, context);
    }

    public MetadataTestRunResults runTestsWithMetadata(Set<URI> resources, ParseTreeListener subgrammarListener, String context) {
        Optional<Collection<TestSpecification>> specification = testSpecificationFactory.getTestSpecifications(resources);
        if (specification.isEmpty()) {
            logger.warn("No resources could be converted to a test specification!");
            return new PolymorphicDslTestRunResults(new PdslThreadSafeOutputStream(), context);
        }
        Collection<TestCase> testCases = testCaseFactory.processTestSpecification(specification.get());
        return executor.runTestsWithMetadata(testCases, subgrammarListener, context);
    }

    public MetadataTestRunResults runTestsWithMetadata(Set<URI> resources, String tagExpression, ParseTreeListener subgrammarListener, String context) {
        Optional<Collection<TestSpecification>> specification = testSpecificationFactory.getTestSpecifications(resources);
        if (specification.isEmpty()) {
            logger.warn("No resources could be converted to a test specification!");
            return new PolymorphicDslTestRunResults(new PdslThreadSafeOutputStream(), context);
        }
        Optional<Collection<TestSpecification>> filteredSpecification =
                testSpecificationFactory.filterGherkinTestSpecificationsByTagExpression(specification.get(), tagExpression);
        if (filteredSpecification.isEmpty()) {
            logger.warn("All tests were filtered out by the tag expression!");
            return new PolymorphicDslTestRunResults(new PdslThreadSafeOutputStream(), context);
        }
        Collection<TestCase> testCases = testCaseFactory.processTestSpecification(filteredSpecification.get());
        return executor.runTestsWithMetadata(testCases, subgrammarListener, context);
    }
}