package com.pdsl.runners;

import com.google.common.base.Preconditions;
import com.pdsl.executors.InterpreterObj;
import com.pdsl.specifications.TestResourceFinder;
import com.pdsl.specifications.TestResourceFinderGenerator;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.TestSpecificationFactory;
import com.pdsl.testcases.SharedTestSuite;
import com.pdsl.testcases.TestCase;
import com.pdsl.testcases.TestCaseFactory;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * A visitor for producing PDSL test cases that can have more than one parser and listener/visitor process it.
 *
 * This visitor represents the default behavior for PDSL implementations seen in other test frameworks (e.g. JUnit).
 * It is highly suitable for embedding in other frameworks to have consistent logic and to get the benefits of new
 * features and enhancements to PDSL in general to 3rd party implementers.
 */
public class SharedTestSuiteVisitor implements RecognizerParams.RecognizerParamsOperation<SharedTestSuite> {

    @Override
    public SharedTestSuite recognizerParamsOperation(RecognizerParams recognizerParams) {
        Preconditions.checkNotNull(recognizerParams.applicationName(), "Application name cannot be null");
        Preconditions.checkNotNull(recognizerParams.resourceRoot(), "Resource root cannot be null");
        Preconditions.checkNotNull(recognizerParams.context(), "Context cannot be null");
        Preconditions.checkNotNull(recognizerParams.providers(), "Providers cannot be null");
        Preconditions.checkNotNull(recognizerParams.providers().resourceFinderSupplier(), "DSL resource finder cannot be null");
        Preconditions.checkNotNull(recognizerParams.providers().specificationFactoryProvider(), "Specification Factory Provider cannot be null");
        Preconditions.checkNotNull(recognizerParams.providers().testCaseFactoryProvider(), "Test Factory Provider cannot be null");
        recognizerParams.pdslTestParams().forEach(interpreter -> Preconditions.checkNotNull(interpreter, "No null objects can be in the interpreter array!"));
        // Create a Shared Test Suite
        List<List<TestCase>> testCasesPerInterpreters = new ArrayList<>();
        List<InterpreterObj> interpreterObjs = new ArrayList<>();
        for (PdslTestParams params : recognizerParams.pdslTestParams()) {

            // Get the tests written in a DSL
            Set<URI> testResources;
            try {
                URI resourceRoot = new URI(recognizerParams.resourceRoot());
                testResources = getTestResources(recognizerParams.providers().resourceFinderSupplier().get(),
                        params.includesResources(), params.excludesResources(), resourceRoot);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }

            for (InterpreterParam parser : params.interpreters()) {
                // Read the files and structure them as specifications which will allow us to figure out how many
                // test cases to create from each file, which parts to ignore, etc.
                Collection<TestSpecification> specifications = getSpecifications(parser, recognizerParams, params, testResources);
                // Convert the specificaitons into test cases
                List<TestCase> testCasesForSingleInterpreter = new ArrayList<>(getTestCases(recognizerParams.providers().testCaseFactoryProvider().get(), specifications));
                testCasesPerInterpreters.add(testCasesForSingleInterpreter);
                interpreterObjs.add(parser.interpreterProvider().get());
            }
        }
        return SharedTestSuite.of(testCasesPerInterpreters, interpreterObjs);

    }

    private Set<URI> getTestResources(TestResourceFinderGenerator finderGenerator, String[] includes, String[] excludes, URI resourceRoot) {
        TestResourceFinder finder = finderGenerator.get(includes,excludes);
        Set<URI> testResources = new HashSet<>();
        // Find the files we will be testing
        Optional<Collection<URI>> resources =  finder.scanForTestResources(resourceRoot);
        resources.ifPresent(testResources::addAll);
        if (testResources.isEmpty()) {
            throw new IllegalArgumentException(String.format(
                    "No feature files were found!%n\tResource Root: %s%n%n\tincluding: %s%n%n\texcluding: %s",
                    resourceRoot, Arrays.deepToString(includes), Arrays.deepToString(excludes)));
        }
        return testResources;
    }

    private Collection<TestSpecification> getSpecifications(InterpreterParam parser, RecognizerParams recognizerParams, PdslTestParams pdslTestParams,  Set<URI> testResources) {
        PolymorphicDslPhraseFilter polymorphicDslPhraseFilter = new DefaultPolymorphicDslPhraseFilter(
                parser.parser(), parser.lexer(),
                getRecognizerParser(pdslTestParams, parser, recognizerParams),
                getRecognizerLexer(pdslTestParams, parser, recognizerParams),
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

    /**
     * Create the parser for a recognizer from the provided objects.
     *
     * If a PdslTestParams provides a recognizer it will be used. If not, if the RecognizerParamas provides one
     * it will be used. Otherwise the same parser used by the PdslTestParams for executing phrases will double as a
     * recognizer.
     *
     * The important business logic guaranteed by this is that EVERY phrase must be recognized by something, even if
     * it doesn't trigger any work when recognized.
     *
     * The recognizer will ignore sentences and is there to provide guarantees that there were no typos made in the
     * DSL tests before they execute. The interpreter is what triggers some behavior when a sentence is parsed. If no
     * recognizer is provided then the interpreter will double as a recognizer (i.e, the framework will throw an
     * exception if the interpreter doesn't recognizer a sentence because nothing else could either).
     *
     * @param params the information needed for a specific test that needs a recognizer
     * @param parser
     * @param recognizerParams
     * @return the class of the parser to use in a test specification factory
     */
    public static Class<? extends Parser> getRecognizerParser(PdslTestParams params, InterpreterParam parser,
                                                                    RecognizerParams recognizerParams) {
        if (!params.parserRecognizerClass().equals(EmptyRecognizerParser.class)) {
            return params.parserRecognizerClass();
        }
        if(!recognizerParams.defaultParserRecognizer().equals(EmptyRecognizerParser.class)) {
          return recognizerParams.defaultParserRecognizer();
        }
        return parser.parser();
    }

    /**
     * Create the lexer for a recognizer from the provided objects.
     *
     * If a PdslTestParams provides a recognizer it will be used. If not, if the RecognizerParamas provides one
     * it will be used. Otherwise the same parser used by the PdslTestParams for executing phrases will double as a
     * recognizer.
     *
     * The important business logic guaranteed by this is that EVERY phrase must be recognized by something, even if
     * it doesn't trigger any work when recognized.
     *
     * The recognizer will ignore sentences and is there to provide guarantees that there were no typos made in the
     * DSL tests before they execute. The interpreter is what triggers some behavior when a sentence is parsed. If no
     * recognizer is provided then the interpreter will double as a recognizer (i.e, the framework will throw an
     * exception if the interpreter doesn't recognizer a sentence because nothing else could either).
     *
     * @param params the information needed for a specific test that needs a recognizer
     * @param parser
     * @param recognizerParams
     * @return the class of the parser to use in a test specification factory
     */
    public static Class<? extends Lexer> getRecognizerLexer(PdslTestParams params, InterpreterParam parser,
                                                            RecognizerParams recognizerParams) {
        if (!params.lexerRecognizerClass().equals(EmptyRecognizerLexer.class)) {
            return params.lexerRecognizerClass();
        }
        if(!recognizerParams.defaultLexerRecognizer().equals(EmptyRecognizerLexer.class)) {
            return recognizerParams.defaultLexerRecognizer();
        }
        return parser.lexer();
    }


}
