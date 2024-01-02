package com.pdsl.runners;

import com.google.common.base.Preconditions;
import com.pdsl.exceptions.PolymorphicDslFrameworkException;
import com.pdsl.executors.DefaultPolymorphicDslTestExecutor;
import com.pdsl.executors.TraceableTestRunExecutor;
import com.pdsl.runners.junit.PolymorphicDslJUnitException;
import com.pdsl.specifications.*;
import com.pdsl.testcases.TestCase;
import com.pdsl.testcases.TestCaseFactory;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.TestClass;

import javax.inject.Provider;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.*;

/**
 * A helper for performing common operations with the PDSL framework to assist with integrating
 * in other test frameworks, such as JUnit.
 *
 * The basic flow of a PDSL application is largely language agnostic:
 * - Source files are located and read
 * - They are turned into specifications
 * - Relevant phrases are determined and filtered
 * - Those in turn are turned into test cases
 * - Finally they are executed
 *
 * The helper creates a common workflow for performing some of these actions.
 */
public final class ExecutorHelper {
    private static final String TEST_CASEFACTORY_PROVIDER = "testCaseFactoryProvider";
    private static final String SPECIFICATION_FACTORY_PROVIDER = "specificationFactoryProvider";
    private static final String TEST_RUN_EXECUTOR = "testRunExecutor";
    private static final String RESOURCE_FINDER = "resourceFinder";
    ExecutorHelper(){
        try {
            Preconditions.checkNotNull(PdslConfiguration.class.getMethod(TEST_CASEFACTORY_PROVIDER));
            Preconditions.checkNotNull(PdslConfiguration.class.getMethod(SPECIFICATION_FACTORY_PROVIDER));
            // TestRunExecutor may or not be null
            PdslConfiguration.class.getMethod(TEST_RUN_EXECUTOR);
        } catch (NoSuchMethodException e) {
            throw new PolymorphicDslFrameworkException("PdslConfiguration does not have the following method. This error implies a bug with the framework itself.", e);
        }
    }

    /**
     * A mutually exclusive container for a parse tree listener or visitor.
     */
    public static class ParseTreeTraversal {
        private final Optional<ParseTreeVisitor<?>> visitor;
        private final Optional<ParseTreeListener> listener;

        /**
         * Creates a tree traversal using a visitor.
         * @param visitor to use
         */
        public ParseTreeTraversal(ParseTreeVisitor<?> visitor) {
            this.visitor = Optional.of(visitor);
            this.listener = Optional.empty();
        }

        /**
         * Creates a tree traversal using a listener
         * @param listener to use
         */
        public ParseTreeTraversal(ParseTreeListener listener) {
            this.visitor = Optional.empty();
            this.listener = Optional.of(listener);
        }

        /**
         * Retrieves the listener if one was created.
         *
         * If there is no listener than a visitor was created instead.
         * @return Optional ANTLR4 visitor for reading test DSLs
         */
        public Optional<ParseTreeListener> getListener() {
            return  listener;
        }

        /**
         * Retrieves the visitor if one was created.
         *
         * If there is no visitor than a listener was created instead.
         * @return Optional ANTLR4 visitor for reading test DSLs
         */
        public Optional<ParseTreeVisitor<?>> getVisitor() {
            return visitor;
        }
    }

    /**
     * Obtains either a visitor or listener provider instance from metadata in the supplied @PdslTest annotation.
     *
     * <p>If both a visitor and listener are provided the visitor instance will be created and the listener ignored.
     *
     * If neither are provided a runtime exception will be thrown.
     *
     * @param pdslTest the pdsl test annotation on the class
     * @return a collection of ParseTreeTraversal containing either a visitor or listener instance.
     */
    public List<ParseTreeTraversal> getParseTreeTraversal(PdslTest pdslTest) {
        List<ParseTreeTraversal> traversals = new ArrayList<>();

        /**
         * If the [@PdsLTest.interpreters()]:
         * 1) is NULL or empty array we can use old approach
         * 2) is NOT NULL use the multiple Lexer/Parser; Listener/Visitor
         */
        if(pdslTest.interpreters() == null || pdslTest.interpreters().length == 0) {
            try {
                if (pdslTest.listener().equals(EmptyParseTreeListenerProvider.class) && pdslTest.visitor().equals(EmptyParseTreeVisitorProvider.class) ) {
                    throw new IllegalArgumentException("Either a listener or visitor needs to be provided to the @PdslTest annotation!");
                }
                if (!pdslTest.visitor().equals(EmptyParseTreeVisitorProvider.class)) {
                    Constructor<?> providerConstructor = pdslTest.visitor().getDeclaredConstructor();
                    traversals.add(new ParseTreeTraversal(((Provider<ParseTreeVisitor<?>>) providerConstructor.newInstance()).get()));
                } else {
                    Constructor<?> providerConstructor = pdslTest.listener().getDeclaredConstructor();
                    traversals.add(new ParseTreeTraversal(((Provider<ParseTreeListener>) providerConstructor.newInstance()).get()));
                }
            } catch (NoSuchMethodException e) {
                throw new IllegalStateException(String.format("Could not find a default constructor for the Provider<ParseTreeListener> %s%n"
                    + "Note the Provider MUST have a constructor that takes no parameters, but see the below error for more details.", pdslTest.listener().getSimpleName()), e);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException(String.format("Could not create a %s. Note the provider MUST be public.", pdslTest.listener()), e);
            } catch (InstantiationException | InvocationTargetException e) {
                throw new IllegalStateException(String.format("Something went wrong when trying to create the Parse Tree Listener %s.%n", pdslTest.listener().getSimpleName()), e);
            }
        }
        else {
            Preconditions.checkArgument(pdslTest.interpreters().length %2 == 0,
                "The size of alternative interpreters (Lexer/Parser; Visitor/Listener) in [com.pdsl.runners.@PdslTest], should be even! Actual size: " + pdslTest.interpreters().length);

            for(Interpreter interpreter : pdslTest.interpreters()) {
                try {
                    if (interpreter.listener().equals(EmptyParseTreeListenerProvider.class) && interpreter.visitor().equals(EmptyParseTreeVisitorProvider.class) ) {
                        throw new IllegalArgumentException("Either a listener or visitor needs to be provided to the @PdslTest annotation, attribute [interpreters]!");
                    }
                    if (!interpreter.visitor().equals(EmptyParseTreeVisitorProvider.class)) {
                        Constructor<?> providerConstructor = interpreter.visitor().getDeclaredConstructor();
                        traversals.add(new ParseTreeTraversal(((Provider<ParseTreeVisitor<?>>) providerConstructor.newInstance()).get()));
                    } else {
                        Constructor<?> providerConstructor = interpreter.listener().getDeclaredConstructor();
                        traversals.add(new ParseTreeTraversal(((Provider<ParseTreeListener>) providerConstructor.newInstance()).get()));
                    }
                } catch (NoSuchMethodException e) {
                    throw new IllegalStateException(String.format("Could not find a default constructor for the Provider<ParseTreeListener> %s%n"
                        + "Note the Provider MUST have a constructor that takes no parameters, but see the below error for more details.", interpreter.listener().getSimpleName()), e);
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException(String.format("Could not create a %s. Note the provider MUST be public.", interpreter.listener()), e);
                } catch (InstantiationException | InvocationTargetException e) {
                    throw new IllegalStateException(String.format("Something went wrong when trying to create the Parse Tree Listener %s.%n", interpreter.listener().getSimpleName()), e);
                }
            }//for-loop


        }

        return traversals;
    }

    /**
     * @deprecated use getParseTreeTraversal instead. This will be removed in a future major release.
     * @param pdslTest the pdsl test annotation on the class
     * @return the ParseTreeListener made from the listenerProvider in the pdslTest
     */
    @Deprecated
    public ParseTreeListener getParseTreeListener(PdslTest pdslTest) {
        try {
            if (pdslTest.listener().equals(EmptyParseTreeListenerProvider.class)) {
                throw new IllegalStateException("No listener or visitor was provided in the @PdslTest. ");
            }
            Constructor<?> providerConstructor = pdslTest.listener().getDeclaredConstructor();
            return ((Provider<ParseTreeListener>) providerConstructor.newInstance()).get();
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException(String.format("Could not find a default constructor for the Provider<ParseTreeListener> %s%n"
                    + "Note the Provider MUST have a constructor that takes no parameters, but see the below error for more details.", pdslTest.listener().getSimpleName()), e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(String.format("Could not create a %s. Note the provider MUST be public.", pdslTest.listener()), e);
        } catch (InstantiationException | InvocationTargetException e) {
            throw new IllegalStateException(String.format("Something went wrong when trying to create the Parse Tree Listener %s.%n", pdslTest.listener().getSimpleName()), e);
        }
    }

    /**
     * Creates test specifications from the provided input.
     * @param testSpecificationFactory the factory that will produce test specifications from resources
     * @param testResources the URIs that have the tests written in a DSL
     * @param pdslTest an annotation containing information about how to process the specificaitons
     * @return a collection of TestSpecifications
     */
    public Collection<TestSpecification> getTestSpecifications(TestSpecificationFactory testSpecificationFactory, Set<URI> testResources, PdslTest pdslTest) {
        Optional<Collection<TestSpecification>> testSpecifications = testSpecificationFactory.getTestSpecifications(testResources);
        if (testSpecifications.isEmpty()) {
            throw new IllegalArgumentException(String.format(
                    "Test resource files were found but they were all filtered out!%n\tTag Filter: %s", pdslTest.tags()));
        }
        return testSpecifications.get();
    }

    /**
     * Creates test cases from test specifications using the provided input.
     *
     * @param testCaseFactory the factory that will produce the test cases
     * @param specifications the specifications used by the factory to create the test cases.
     * @return Collection of test cases
     */
    public Collection<TestCase> getTestCases(TestCaseFactory testCaseFactory, Collection<TestSpecification> specifications) {
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
     * Creates JUnit4 friendly "methods" that map to each individual test case.
     *
     * For JUnit4 hooks and other features to work they need to be in the form of a method. We
     * artificially convert the tests into methods to accomodate this.
     *
     * @param clazz the test class with the PDSL annotations
     * @return a collection of framework methods that each represent a single PDSL TestCase
     */
    public List<FrameworkMethod> computePdslTestMethods(TestClass clazz) {
        List<FrameworkMethod> frameworkMethods = new ArrayList<>();
        // Validate annotations are valid
        frameworkMethods.addAll(
                clazz.getAnnotatedMethods(PdslTest.class));
        for (FrameworkMethod pdslTestMethod : frameworkMethods) {
            RecognizedBy recognizedBy = pdslTestMethod.getAnnotation(RecognizedBy.class);
            if (recognizedBy != null) {
                // Make sure that the parser has a rule called 'syntaxRule'
                boolean hasRecognizerRule = Arrays.stream(recognizedBy.dslRecognizerParser().getMethods())
                        .anyMatch(m -> m.getName().equals(recognizedBy.recognizerRule()));
                if (!hasRecognizerRule) {
                    throw new IllegalArgumentException(String.format("RecognizedBy is using a parser missing the expected rule '%s'%n\tClass: %s%n\tMethod: %s%n\tParser: %s%n",
                            recognizedBy.recognizerRule(), pdslTestMethod.getDeclaringClass().getSimpleName(), pdslTestMethod.getName(), recognizedBy.dslRecognizerParser().getSimpleName()));
                }
            }
        }
        return frameworkMethods;
    }

    /**
     * Create a phrase filter with no general recognizer.
     *
     * If any sentences are not recognized by the parser in the PdslTest it will crash.
     *
     * @param pdslTest the pdsl test annotation on the class
     * @return a phrase filter
     */
    public PolymorphicDslPhraseFilter makeDefaultFilter(PdslTest pdslTest) {
        return new DefaultPolymorphicDslPhraseFilter(pdslTest.parser(), pdslTest.lexer(), pdslTest.startRule());
    }

    /**
     * Create a phrase filter with context sensitive phrases and a general recognizer.
     *
     * The only phrases executed will be the ones understood by the subgrammarparser. The
     * supergrammar parser will not execute any code, but will crash the test if it cannot
     * recognize a phrase.
     * @param subGrammarParser parser for phrases that require behavior
     * @param subGrammarLexer lexer for phrases that require behavior
     * @param superGrammarParser parser for phrases that should be ignored
     * @param superGrammarLexer lexer for phrases that should be ignored
     * @param recognizerRule the start rule to invoke in the supergrammar parser
     * @param syntaxCheckRule the syntax rule that all tests must conform to specified in the supergrammar
     * @return a phrase filter
     */
    public PolymorphicDslPhraseFilter makeDefaultFilter(Class<? extends Parser> subGrammarParser,
                                                        Class<? extends Lexer> subGrammarLexer,
                                                        Class<? extends Parser> superGrammarParser,
                                                        Class<? extends Lexer> superGrammarLexer,
                                                        String recognizerRule,
                                                        String syntaxCheckRule) {
        return new DefaultPolymorphicDslPhraseFilter( subGrammarParser,
                subGrammarLexer, superGrammarParser, superGrammarLexer, recognizerRule, syntaxCheckRule);
    }

    /**
     * Create a phrase filter with a subgrammar parser and a supergrammar recognizer using the
     * PdslTest and RecognizedBy annotations.
     * @param pdslTest annotation provided by a test method
     * @param recognizedBy annotation on the same test method
     * @return a phrase filter created from the annotation parameters
     */
    public PolymorphicDslPhraseFilter makeDefaultFilter(PdslTest pdslTest, RecognizedBy recognizedBy) {
        return makeDefaultFilter(pdslTest.parser(), pdslTest.lexer(), recognizedBy.dslRecognizerParser(),
                recognizedBy.dslRecognizerLexer(), pdslTest.startRule(), recognizedBy.recognizerRule());
    }

    /**
     * Create a phrase filter with a subgrammar parser and a supergrammar recognizer using the
     * PdslTest and RecognizedBy annotations. Also requires all tests to conform to the specified
     * recognizerRule in the created recognizer.
     *
     * @param pdslTest annotation provided by a test method
     * @param recognizedBy annotation on the same test method
     * @param recognizerRule the syntax check rule to invoke in the recognizer
     * @return a phrase filter created from the annotation parameters
     */
    public PolymorphicDslPhraseFilter makeDefaultFilter(PdslTest pdslTest, RecognizedBy recognizedBy, String recognizerRule) {
        return makeDefaultFilter(pdslTest.parser(), pdslTest.lexer(), recognizedBy.dslRecognizerParser(), recognizedBy.dslRecognizerLexer(), pdslTest.startRule(), recognizerRule);
    }

    /**
     * Create a phrase filter with a subgrammar parser and a supergrammar recognizer using the
     * PdslTest and RecognizedBy annotations.
     *
     * The default rules polymorphicDslAllRules and polymorphicDslSyntaxCheck rules must exist in the
     * parser and recognizer or there will be a runtime exception.
     *
     * @param pdslTest annotation provided by a test method
     * @param configuration the configuration annotation on a test class
     * @return a phrase filter created from the annotation parameters
     */
    public PolymorphicDslPhraseFilter makeDefaultFilter(PdslTest pdslTest, PdslConfiguration configuration) {
        return new DefaultPolymorphicDslPhraseFilter(pdslTest.parser(), pdslTest.lexer(), configuration.dslRecognizerParser(), configuration.dslRecognizerLexer(), pdslTest.startRule(), configuration.recognizerRule());
    }

    /**
     * A simple data transfer object for providing the results of helper factories.
     */
    public static class PdslProvidersDto {
        private final Provider<? extends TestSpecificationFactoryGenerator> testSpecificationFactoryGenerator;
        private final Provider<? extends TestCaseFactory> testCaseFactoryProvider;
        private final Provider<? extends TraceableTestRunExecutor> testRunExecutor;
        private final Provider<? extends TestResourceFinderGenerator> resourceFinder;

        private final Optional<Class<? extends Parser>> classWideParserRecognizerOptional;
        private final Optional<Class<? extends Lexer>> classWideLexerRecognizerOptional;
        public PdslProvidersDto(Provider<? extends TestSpecificationFactoryGenerator> testSpecificationFactoryGenerator,
            Provider<? extends TestCaseFactory> testCaseFactoryProvider,
            Provider<? extends TraceableTestRunExecutor> testRunExecutor,
            Provider<? extends TestResourceFinderGenerator> resourceFinder,
            PdslConfiguration pdslConfiguration) {

            if(pdslConfiguration.dslRecognizerParser().equals(EmptyRecognizerParser.class)
                    ^ pdslConfiguration.dslRecognizerLexer().equals(EmptyRecognizerLexer.class)) {
                throw new IllegalArgumentException("If a dslRecognizerLexer or dslRecognizerParser is used at all in the @PdslGherkinAnnotation then BOTH of them must be present!");
            }
            this.testSpecificationFactoryGenerator = testSpecificationFactoryGenerator;
            this.testCaseFactoryProvider = testCaseFactoryProvider;
            this.testRunExecutor = testRunExecutor;
            this.resourceFinder = resourceFinder;
            this.classWideParserRecognizerOptional = pdslConfiguration.dslRecognizerParser().equals(EmptyRecognizerParser.class)
                    ? Optional.empty() : Optional.of(pdslConfiguration.dslRecognizerParser());
            this.classWideLexerRecognizerOptional = pdslConfiguration.dslRecognizerLexer().equals(EmptyRecognizerLexer.class)
                    ? Optional.empty() : Optional.of(pdslConfiguration.dslRecognizerLexer());
        }

        /**
         * Provides the created test specification factory generator.
         * @return a provider for a specification factory
         */
        public Provider<? extends TestSpecificationFactoryGenerator> getTestSpecificationFactoryGenerator() {
            return testSpecificationFactoryGenerator;
        }

        /**
         * Provides the created test case factory provider.
         */
        public Provider<? extends TestCaseFactory> getTestCaseFactoryProvider() {
            return testCaseFactoryProvider;
        }

        /** Provides the created test executor provider. */
        public Provider<? extends TraceableTestRunExecutor> getTestRunExecutor() {
            return testRunExecutor;
        }

        /** Provides the created test resource finder generator. */
        public Provider<? extends TestResourceFinderGenerator> getResourceFinder() {
            return resourceFinder;
        }

        /** Provides a recognizer class intended to be used unless overridden by PdslTests. */
        public Optional<Class<? extends Parser>> getClassWideParserRecognizerOptional() {
            return classWideParserRecognizerOptional;
        }

        /** Provides a recognizer lexer class intended to be used unless overridden by PdslTests. */
        public Optional<Class<? extends Lexer>> getClassWideLexerRecognizerOptional() {
            return classWideLexerRecognizerOptional;
        }
    }

    /**
     * Creates a variety of factories useful for running Polymorphic DSL tests.
     * @param provider the class containing PDSL annotations.
     * @param configurationField name of the field associated with the class (used in friendly error message)
     * @return an instance of the provided class.
     */
    public Object createPdslProviderFromClass(Class<?> provider, String configurationField) {
        try {
            return  provider.getConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            throw new PolymorphicDslJUnitException(String.format("Error with parameter(s) in the @PdslConfiguration!%n"
                            + "\tThe field %s must have a default constructor.%n"
                            + "\tOnly the default constructor will be invoked by the JUnit runner.%n"
                            + "\tCLASS THAT NEEDS DEFAULT CONSTRUCTOR:%n\t%s%n",
                    configurationField, provider.getName()), e);
        } catch (InvocationTargetException | InstantiationException e) {
            throw new PolymorphicDslJUnitException(String.format("Error creating %s!", provider.getName()), e);
        } catch (IllegalAccessException e) {
            throw new PolymorphicDslJUnitException(String.format("Could not access constructor for %s. Make sure it is public!", provider.getName()), e);
        }
    }

    /**
     * Provides the default test executor used by most PDSL tests.
     */
    public static final class DefaultExecutorProvider implements Provider<TraceableTestRunExecutor> {
        private static final TraceableTestRunExecutor INSTANCE = new DefaultPolymorphicDslTestExecutor();
        @Override
        public TraceableTestRunExecutor get() {
            return INSTANCE;
        }
    }

    /**
     * Provides the default resource finder used by most PDSL tests.
     */
    public static final class DefaultResourceFinderGenerator implements Provider<TestResourceFinderGenerator> {
        private final String resourceRoot;

        /**
         * Creates a generator for resource finders.
         * @param resourceRoot the root directory to search in
         */
        public DefaultResourceFinderGenerator(String resourceRoot)
        {
            if (resourceRoot.startsWith("file:///")) {
                resourceRoot = resourceRoot.replaceFirst("file:///", "");
            }
            this.resourceRoot = resourceRoot;
            this.INSTANCE = new FileSystemTestResourceGenerator(resourceRoot);
        }
        private final TestResourceFinderGenerator INSTANCE;

        @Override
        public TestResourceFinderGenerator get() {
            return INSTANCE;
        }
    }

    /**
     * Create a collection of factories and other useful objects for a PDSL run.
     * @param pdslConfiguration the configuration specifying which factories to use.
     * @return PdslProvidersDto containing factories needed to run PDSL test cases
     */
    public PdslProvidersDto makePdslElements(PdslConfiguration pdslConfiguration) {
            Provider<? extends TestCaseFactory> testCaseFactory = (Provider<? extends TestCaseFactory>) createPdslProviderFromClass(
                    pdslConfiguration.testCaseFactoryProvider(), TEST_CASEFACTORY_PROVIDER);
            Provider<? extends TestSpecificationFactoryGenerator> specificationFactory = (Provider<? extends TestSpecificationFactoryGenerator>) createPdslProviderFromClass(pdslConfiguration.specificationFactoryProvider(), SPECIFICATION_FACTORY_PROVIDER);
            Provider<? extends TraceableTestRunExecutor> executor = !pdslConfiguration.testRunExecutor().equals(EmptyTestExecutorProvider.class)
                    ? (Provider<? extends TraceableTestRunExecutor>) createPdslProviderFromClass(pdslConfiguration.testRunExecutor(), TEST_RUN_EXECUTOR)
                    : new DefaultExecutorProvider();
            Provider<? extends TestResourceFinderGenerator> resourceFinder = !pdslConfiguration.resourceFinder().equals(EmptyTestResourceFinder.class)
            ? (Provider<? extends TestResourceFinderGenerator>) createPdslProviderFromClass(pdslConfiguration.resourceFinder(), RESOURCE_FINDER)
                    : new DefaultResourceFinderGenerator(pdslConfiguration.resourceRoot());
            return new PdslProvidersDto(specificationFactory, testCaseFactory, executor, resourceFinder, pdslConfiguration);
    }
}
