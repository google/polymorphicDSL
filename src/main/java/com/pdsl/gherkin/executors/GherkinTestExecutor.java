package com.pdsl.gherkin.executors;

import com.pdsl.executors.DefaultPolymorphicDslTestExecutor;
import com.pdsl.executors.PolymorphicDslTestExecutor;
import com.pdsl.gherkin.DefaultGherkinTestSpecificationFactory;
import com.pdsl.gherkin.PdslGherkinInterpreterImpl;
import com.pdsl.gherkin.PdslGherkinListenerImpl;
import com.pdsl.gherkin.PickleJarFactory;
import com.pdsl.gherkin.specifications.GherkinTestSpecificationFactory;
import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.testcases.PreorderTestCaseFactory;
import com.pdsl.testcases.TestCase;
import com.pdsl.testcases.TestCaseFactory;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public class GherkinTestExecutor implements PolymorphicDslTestExecutor {

    private static final PickleJarFactory pickleJarFactory = new PickleJarFactory(new PdslGherkinInterpreterImpl(), new PdslGherkinListenerImpl(), StandardCharsets.UTF_8);
    private final PolymorphicDslPhraseFilter phraseFilter;
    private final GherkinTestSpecificationFactory testSpecificationFactory;
    private final TestCaseFactory testCaseFactory = new PreorderTestCaseFactory();
    private final Logger logger = LoggerFactory.getLogger(GherkinTestExecutor.class);
    private final PolymorphicDslTestExecutor executor = new DefaultPolymorphicDslTestExecutor();

    public <G extends Parser, L extends Lexer, SG extends Parser, SL extends Lexer> GherkinTestExecutor(Class<G> grammarParser, Class<L> grammarLexer, Class<SG> subgrammarParser, Class<SL> subgrammarLexer) {
        phraseFilter = new DefaultPolymorphicDslPhraseFilter<G, L, SG, SL>(grammarParser, grammarLexer, subgrammarParser, subgrammarLexer);
        testSpecificationFactory = new DefaultGherkinTestSpecificationFactory(pickleJarFactory, phraseFilter);
    }

    public GherkinTestExecutor(PolymorphicDslPhraseFilter phraseFilter) {
        this.phraseFilter = phraseFilter;
        testSpecificationFactory = new DefaultGherkinTestSpecificationFactory(pickleJarFactory, phraseFilter);
    }


    public PolymorphicDslTestRunResults processFilesAndRunTests(Set<URL> testResources, String tagExpression,
                                                                ParseTreeListener grammarListener, ParseTreeListener subgrammarListener) {
        // Use the file locations and convert the feature files to test specifications
        Optional<Collection<TestSpecification>> testSpecificationOptional = testSpecificationFactory.getTestSpecifications(testResources);
        if (testSpecificationOptional.isEmpty()) {
            throw new IllegalStateException("None of the test resources could be converted into a test specification!");
        }
        // If tag expressions were provided filter the test specification
        Optional<Collection<TestSpecification>> filteredSpecification =
                testSpecificationFactory.filterGherkinTestSpecificationsByTagExpression(testSpecificationOptional.get(), tagExpression);
        Collection<TestSpecification> testSpecification;
        if (filteredSpecification.isEmpty()) {
            logger.warn("All tests were filtered out! Nothing to execute!");
            return new PolymorphicDslTestRunResults(System.out);
        } else {
            testSpecification = filteredSpecification.get();
        }

        // Convert the test specifications into test cases
        Collection<TestCase> testCases = testCaseFactory.processTestSpecification(testSpecification);
        //Finally execute the tests
        return executor.runTests(testCases, grammarListener, subgrammarListener);
    }

    public PolymorphicDslTestRunResults processFilesAndRunTests(Set<URL> testResources, String tagExpression,
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
            return new PolymorphicDslTestRunResults(System.out);
        } else {
            Collection<TestSpecification> testSpecifications = filteredSpecification.get();
            // Convert the test specifications into test cases
            Collection<TestCase> testCases = testCaseFactory.processTestSpecification(testSpecifications);
            //Finally execute the tests
            return executor.runTests(testCases, grammarListener);
        }
    }

    public PolymorphicDslTestRunResults processFilesAndRunTests(Set<URL> testResources,
                                                                ParseTreeListener grammarListener, ParseTreeListener subgrammarListener) {
        return processFilesAndRunTests(testResources, "", grammarListener, subgrammarListener);
    }

    public PolymorphicDslTestRunResults processFilesAndRunTests(Set<URL> testResources,
                                                                ParseTreeListener grammarListener, String tagExpression) {
        return processFilesAndRunTests(testResources, tagExpression, grammarListener);
    }

    public PolymorphicDslTestRunResults processFilesAndRunTests(Set<URL> testResources, ParseTreeListener grammarListener) {
        return processFilesAndRunTests(testResources, grammarListener, "");
    }

    @Override
    public PolymorphicDslTestRunResults runTests(Collection<TestCase> testCases, ParseTreeListener grammarListener) {
        return executor.runTests(testCases, grammarListener);
    }

    @Override
    public PolymorphicDslTestRunResults runTests(Collection<TestCase> testCases, ParseTreeListener grammarListener,
                                                 ParseTreeListener subgrammarListener) {
        return executor.runTests(testCases, grammarListener, subgrammarListener);
    }
}