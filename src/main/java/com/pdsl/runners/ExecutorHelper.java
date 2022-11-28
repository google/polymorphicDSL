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

        public ParseTreeTraversal(ParseTreeVisitor<?> visitor) {
            this.visitor = Optional.of(visitor);
            this.listener = Optional.empty();
        }

        public ParseTreeTraversal(ParseTreeListener listener) {
            this.visitor = Optional.empty();
            this.listener = Optional.of(listener);
        }

        public Optional<ParseTreeListener> getListener() {
            return  listener;
        }

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
     * @param pdslTest
     * @return a ParseTreeTraversal containing either a visitor or listener instance.
     */
    public ParseTreeTraversal getParseTreeTraversal(PdslTest pdslTest) {
        try {
            if (pdslTest.listener().equals(EmptyParseTreeListenerProvider.class) && pdslTest.visitor().equals(EmptyParseTreeVisitorProvider.class) ) {
                throw new IllegalArgumentException("Either a listener or listener needs to be provided to the @PdslTest annotation!");
            }
            if (!pdslTest.visitor().equals(EmptyParseTreeVisitorProvider.class)) {
                Constructor<?> providerConstructor = pdslTest.visitor().getDeclaredConstructor();
                return new ParseTreeTraversal(((Provider<ParseTreeVisitor<?>>) providerConstructor.newInstance()).get());
            } else {
                Constructor<?> providerConstructor = pdslTest.listener().getDeclaredConstructor();
                return new ParseTreeTraversal(((Provider<ParseTreeListener>) providerConstructor.newInstance()).get());
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

    /**
     * @deprecated use getParseTreeTraversal instead. This will be removed in a future major release.
     * @param pdslTest
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

    public Collection<TestSpecification> getTestSpecifications(TestSpecificationFactory testSpecificationFactory, Set<URI> testResources, PdslTest pdslTest) {
        Optional<Collection<TestSpecification>> testSpecifications = testSpecificationFactory.getTestSpecifications(testResources);
        if (testSpecifications.isEmpty()) {
            throw new IllegalArgumentException(String.format(
                    "Test resource files were found but they were all filtered out!%n\tTag Filter: %s", pdslTest.tags()));
        }
        return testSpecifications.get();
    }

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
                            recognizedBy.recognizerRule(),pdslTestMethod.getDeclaringClass().getSimpleName(), pdslTestMethod.getName(), recognizedBy.dslRecognizerParser().getSimpleName()));
                }
            }
        }
        return frameworkMethods;
    }

    public PolymorphicDslPhraseFilter makeDefaultFilter(PdslTest pdslTest) {
        return new DefaultPolymorphicDslPhraseFilter(pdslTest.parser(), pdslTest.lexer(), pdslTest.startRule());
    }



    public PolymorphicDslPhraseFilter makeDefaultFilter(Class<? extends Parser> subGrammarParser,
                                                        Class<? extends Lexer> subGrammarLexer,
                                                        Class<? extends Parser> superGrammarParser,
                                                        Class<? extends Lexer> superGrammarLexer,
                                                        String recognizerRule,
                                                        String syntaxCheckRule) {
        return new DefaultPolymorphicDslPhraseFilter( subGrammarParser,
                subGrammarLexer, superGrammarParser, superGrammarLexer, recognizerRule, syntaxCheckRule);
    }
    public PolymorphicDslPhraseFilter makeDefaultFilter(PdslTest pdslTest, RecognizedBy recognizedBy) {
        return makeDefaultFilter(pdslTest.parser(), pdslTest.lexer(), recognizedBy.dslRecognizerParser(),
                recognizedBy.dslRecognizerLexer(), pdslTest.startRule(), recognizedBy.recognizerRule());
    }

    public PolymorphicDslPhraseFilter makeDefaultFilter(PdslTest pdslTest, RecognizedBy recognizedBy, String recognizerRule) {
        return makeDefaultFilter(pdslTest.parser(), pdslTest.lexer(), recognizedBy.dslRecognizerParser(), recognizedBy.dslRecognizerLexer(), pdslTest.startRule(), recognizerRule);
    }

    public PolymorphicDslPhraseFilter makeDefaultFilter(PdslTest pdslTest, PdslConfiguration configuration) {
        return new DefaultPolymorphicDslPhraseFilter(pdslTest.parser(), pdslTest.lexer(), configuration.dslRecognizerParser(), configuration.dslRecognizerLexer(), pdslTest.startRule(), configuration.recognizerRule());
    }

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

        public Provider<? extends TestSpecificationFactoryGenerator> getTestSpecificationFactoryGenerator() {
            return testSpecificationFactoryGenerator;
        }

        public Provider<? extends TestCaseFactory> getTestCaseFactoryProvider() {
            return testCaseFactoryProvider;
        }

        public Provider<? extends TraceableTestRunExecutor> getTestRunExecutor() {
            return testRunExecutor;
        }

        public Provider<? extends TestResourceFinderGenerator> getResourceFinder() {
            return resourceFinder;
        }

        public Optional<Class<? extends Parser>> getClassWideParserRecognizerOptional() {
            return classWideParserRecognizerOptional;
        }

        public Optional<Class<? extends Lexer>> getClassWideLexerRecognizerOptional() {
            return classWideLexerRecognizerOptional;
        }
    }

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

    public static final class DefaultExecutorProvider implements Provider<TraceableTestRunExecutor> {
        private static final TraceableTestRunExecutor INSTANCE = new DefaultPolymorphicDslTestExecutor();
        @Override
        public TraceableTestRunExecutor get() {
            return INSTANCE;
        }
    }

    public static final class DefaultResourceFinderGenerator implements Provider<TestResourceFinderGenerator> {
        private final String resourceRoot;
        public DefaultResourceFinderGenerator(String resourceRoot) {
            this.resourceRoot = resourceRoot;
            this.INSTANCE = new FileSystemTestResourceGenerator(resourceRoot);
        }
        private final TestResourceFinderGenerator INSTANCE;

        @Override
        public TestResourceFinderGenerator get() {
            return INSTANCE;
        }
    }

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
