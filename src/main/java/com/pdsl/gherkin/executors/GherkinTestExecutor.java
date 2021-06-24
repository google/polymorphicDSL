package com.pdsl.gherkin.executors;

import com.pdsl.executors.DefaultPolymorphicDslTestExecutor;
import com.pdsl.executors.PolymorphicDslTestExecutor;
import com.pdsl.gherkin.*;
import com.pdsl.gherkin.specifications.GherkinTestSpecificationFactory;
import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.transformers.LineDelimitedTestSpecificationFactory;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.TestSpecificationFactory;
import com.pdsl.testcases.ParentForEachChildTestCaseFactory;
import com.pdsl.testcases.TestCase;
import com.pdsl.testcases.TestCaseFactory;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public class GherkinTestExecutor<G extends Parser, L extends Lexer, SG extends Parser, SL extends Lexer> implements PolymorphicDslTestExecutor {

    private static final PickleJarFactory pickleJarFactory = new PickleJarFactory(new PdslGherkinInterpreterImpl(), new PdslGherkinListenerImpl(), StandardCharsets.UTF_8);
    private final TestSpecificationFactory stepBodyGrammarHelperFactory;
    private final TestSpecificationFactory stepBodySubgrammarHelperFactory;
    private final GherkinTestSpecificationFactory provider;
    private final TestCaseFactory testCaseFactory = new ParentForEachChildTestCaseFactory();
    private final Logger logger = LoggerFactory.getLogger(GherkinTestExecutor.class);
    private final PolymorphicDslTestExecutor executor = new DefaultPolymorphicDslTestExecutor();

    public GherkinTestExecutor(Class<G> grammarParser, Class<L> grammarLexer, Class<SG> subgrammarParser, Class<SL> subgrammarLexer) {
        stepBodyGrammarHelperFactory = new LineDelimitedTestSpecificationFactory<G, L>(grammarParser, grammarLexer, LineDelimitedTestSpecificationFactory.ErrorListenerStrategy.GRAMMAR);
        stepBodySubgrammarHelperFactory = new LineDelimitedTestSpecificationFactory<SG, SL>(subgrammarParser, subgrammarLexer, LineDelimitedTestSpecificationFactory.ErrorListenerStrategy.SUBGRAMMAR);
        provider = new DefaultGherkinTestSpecificationFactory(pickleJarFactory, stepBodyGrammarHelperFactory, stepBodySubgrammarHelperFactory);
    }

    public PolymorphicDslTestRunResults processFilesAndRunTests(Set<String> fileLocations, String tagExpression,
                                                                ParseTreeListener grammarListener, ParseTreeListener subgrammarListener) {
        // Use the file locations and convert the feature files to test specifications
        TestSpecification testSpecification = provider.getTestSpecifications(fileLocations);

        // If tag expressions were provided filter the test specification
        Optional<TestSpecification> filteredSpecification =
                provider.filterGherkinTestSpecificationsByTagExpression(testSpecification, tagExpression);
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

    public PolymorphicDslTestRunResults processFilesAndRunTests(Set<String> fileLocations, String tagExpression,
                                                                ParseTreeListener grammarListener) {
        // Use the file locations and convert the feature files to test specifications
        TestSpecification testSpecification = provider.getTestSpecifications(fileLocations);

        // If tag expressions were provided filter the test specification
        Optional<TestSpecification> filteredSpecification =
                provider.filterGherkinTestSpecificationsByTagExpression(testSpecification, tagExpression);
        if (filteredSpecification.isEmpty()) {
            logger.warn("All tests were filtered out! Nothing to execute!");
            return new PolymorphicDslTestRunResults(System.out);
        } else {
            testSpecification = filteredSpecification.get();
        }

        // Convert the test specifications into test cases
        Collection<TestCase> testCases = testCaseFactory.processTestSpecification(testSpecification);
        //Finally execute the tests
        return executor.runTests(testCases, grammarListener);
    }

    public PolymorphicDslTestRunResults processFilesAndRunTests(Set<String> fileLocations,
                                                                ParseTreeListener grammarListener, ParseTreeListener subgrammarListener) {
        return processFilesAndRunTests(fileLocations, "", grammarListener, subgrammarListener);
    }

    public PolymorphicDslTestRunResults processFilesAndRunTests(Set<String> fileLocations,
                                                                ParseTreeListener grammarListener, String tagExpression) {
        return processFilesAndRunTests(fileLocations, tagExpression, grammarListener);
    }

    public PolymorphicDslTestRunResults processFilesAndRunTests(Set<String> fileLocations, ParseTreeListener grammarListener) {
        return processFilesAndRunTests(fileLocations, grammarListener, "");
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