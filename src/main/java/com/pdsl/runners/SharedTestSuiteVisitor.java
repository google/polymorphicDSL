package com.pdsl.runners;

import com.google.common.base.Preconditions;
import com.pdsl.executors.InterpreterObj;
import com.pdsl.gherkin.DefaultGherkinTestSpecificationFactory;
import com.pdsl.gherkin.specifications.GherkinTestSpecificationFactory;
import com.pdsl.specifications.TestResourceFinder;
import com.pdsl.specifications.TestResourceFinderGenerator;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.TestSpecificationFactory;
import com.pdsl.testcases.SharedTestSuite;
import com.pdsl.testcases.TestCase;
import com.pdsl.testcases.TestCaseFactory;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;

import java.net.URI;
import java.nio.file.Paths;
import java.util.*;

public class SharedTestSuiteVisitor implements RecognizerParams.RecognizerParamsOperation<SharedTestSuite> {

    @Override
    public SharedTestSuite recognizerParamsOperation(RecognizerParams<SharedTestSuite> recognizerParams) {
        Preconditions.checkNotNull(recognizerParams.applicationName(), "Application name cannot be null");
        Preconditions.checkNotNull(recognizerParams.resourceRoot(), "Resource root cannot be null");
        Preconditions.checkNotNull(recognizerParams.context(), "Context cannot be null");
        Preconditions.checkNotNull(recognizerParams.providers(), "Providers cannot be null");
        Preconditions.checkNotNull(recognizerParams.providers().resourceFinderProvider(), "DSL resource finder cannot be null");
        Preconditions.checkNotNull(recognizerParams.providers().specificationFactoryProvider(), "Specification Factory Provider cannot be null");
        Preconditions.checkNotNull(recognizerParams.providers().testCaseFactoryProvider(), "Test Factory Provider cannot be null");
        recognizerParams.pdslTestParams().forEach(interpreter -> Preconditions.checkNotNull(interpreter, "No null objects can be in the interpreter array!"));
        // Create a Shared Test Suite
        List<List<TestCase>> testCasesPerInterpreters = new ArrayList<>();
        List<InterpreterObj> interpreterObjs = new ArrayList<>();
        for (PdslTestParams<SharedTestSuite> params : recognizerParams.pdslTestParams()) {

            // Get the tests written in a DSL
            Set<URI> testResources = getTestResources(recognizerParams.providers().resourceFinderProvider().get(),
                    params.includesResources(), params.excludesResources(), recognizerParams.resourceRoot());
            for (InterpreterParam parser : params.interpreters()) {
                // Read the files and structure them as specifications which will allow us to figure out how many
                // test cases to create from each file, which parts to ignore, etc.
                Collection<TestSpecification> specifications = getSpecifications(parser, recognizerParams, testResources);
                // Convert the specificaitons into test cases
                List<TestCase> testCasesForSingleInterpreter = new ArrayList<>(getTestCases(recognizerParams.providers().testCaseFactoryProvider().get(), specifications));
                testCasesPerInterpreters.add(testCasesForSingleInterpreter);
                interpreterObjs.add(parser.interpreterProvider().get());
            }
        }
        return SharedTestSuite.of(testCasesPerInterpreters, interpreterObjs);

    }

    private Set<URI> getTestResources(TestResourceFinderGenerator finderGenerator, String[] includes, String[] excludes, String resourceRoot) {
        TestResourceFinder finder = finderGenerator.get(includes,excludes);
        Set<URI> testResources = new HashSet<>();
        // Find the files we will be testing
        Optional<Collection<URI>> resources = finder.scanForTestResources(Paths.get(resourceRoot).toUri());
        resources.ifPresent(testResources::addAll);
        if (testResources.isEmpty()) {
            throw new IllegalArgumentException(String.format(
                    "No feature files were found!%n\tResource Root: %s%n%n\tincluding: %s%n%n\texcluding: %s",
                    resourceRoot, Arrays.deepToString(includes), Arrays.deepToString(excludes)));
        }
        return testResources;
    }

    private Collection<TestSpecification> getSpecifications(InterpreterParam parser, RecognizerParams<SharedTestSuite> recognizerParams, Set<URI> testResources) {
        PolymorphicDslPhraseFilter polymorphicDslPhraseFilter = new DefaultPolymorphicDslPhraseFilter(
                parser.parser(), parser.lexer(),
                recognizerParams.defaultParserRecognizer(), recognizerParams.defaultLexerRecognizer(),
                parser.startRule(),
                parser.syntaxCheckRule()
        );
        TestSpecificationFactory specificationFactory = recognizerParams.providers().specificationFactoryProvider().get().get(polymorphicDslPhraseFilter);
        Optional<Collection<TestSpecification>> specifications = specificationFactory.getTestSpecifications(testResources);
        if (specifications.isPresent()) {
            return specifications.get();
        }
        // If we hit this point, no specificaitons could be generated
        String tags;
        if (parser.tags().length == 0) {
            tags = "[no tags were used for this test]";
        } else {
            tags = String.join(String.format("\t%n"), parser.tags());
        }
        throw new IllegalArgumentException(String.format("""
                        No tests were found at the provided URIs. 
                        Check the URIs and tags (which may have been used for filtering!
                            Parser: %s,
                            Lexer: %s,
                            Tag Filters: 
                               %s
                        """.stripIndent(), parser.parser(), parser.lexer(), tags));
    }

    private Collection<TestCase> getTestCases(TestCaseFactory testCaseFactory, Collection<TestSpecification> specifications) {
        if (specifications.isEmpty()) {
            throw new IllegalArgumentException("Test Case Factory could not process specifications because it was empty!");
        }
        Collection<TestCase> testCases = testCaseFactory.processTestSpecification(specifications);
        if (testCases.isEmpty()) {
            throw new IllegalArgumentException(String.format(
                    "No test cases were produced from the specifications!%n\tTest Case Factory: %s", testCaseFactory.getClass()));
        }
        return testCases;
    }

}
