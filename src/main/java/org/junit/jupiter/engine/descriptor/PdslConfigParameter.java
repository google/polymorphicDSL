package org.junit.jupiter.engine.descriptor;

import com.google.common.base.Preconditions;
import com.pdsl.executors.InterpreterObj;
import com.pdsl.executors.TraceableTestRunExecutor;
import com.pdsl.gherkin.DefaultGherkinTestSpecificationFactory;
import com.pdsl.runners.*;
import com.pdsl.specifications.FileSystemTestResourceGenerator;
import com.pdsl.specifications.LineDelimitedTestSpecificationFactory;
import com.pdsl.specifications.TestResourceFinderGenerator;
import com.pdsl.specifications.TestSpecificationFactory;
import com.pdsl.testcases.PreorderTestCaseFactory;
import com.pdsl.testcases.TestCaseFactory;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;

import java.net.URI;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * A Data Transfer Object used to integrate the PDSL framework with JUnit5.
 */
public class PdslConfigParameter {

    private String context = "Unspecified";
    private String applicationName = "Polymorphic DSL System Under Test";
    private URI resourceRoot = Paths.get("./").toUri();
    private Optional<Class<? extends Parser>> dslRecognizerParser = Optional.empty();
    private Optional<Class<? extends Lexer>> dslRecognizerLexer = Optional.empty();
    private Optional<Supplier<? extends TraceableTestRunExecutor>> testRunExecutor = Optional.empty();
    private Optional<Supplier<? extends TestResourceFinderGenerator>> resourceFinder = Optional.empty();
    private String recognizerRule = RecognizedBy.DEFAULT_RECOGNIZER_RULE_NAME;
    private final Supplier<? extends TestSpecificationFactoryGenerator> specificationFactoryProvider;
    private final Supplier<? extends TestCaseFactory> testCaseFactoryProvider;
    private final Collection<PdslTestParameter> pdslTestParameters;

    private static final JUnit5DefaultPackageAccessor ACCESSOR = new JUnit5DefaultPackageAccessor(new JupiterDescriptorKey());

    private PdslConfigParameter(Builder builder) {
        this.context = builder.context;
        this.applicationName = builder.applicationName;
        this.resourceRoot = builder.resourceRoot;
        this.dslRecognizerParser = builder.dslRecognizerParser;
        this.dslRecognizerLexer = builder.dslRecognizerLexer;
        this.testRunExecutor = builder.testRunExecutor;
        this.resourceFinder = builder.resourceFinder;
        this.recognizerRule = builder.recognizerRule;
        this.specificationFactoryProvider = builder.specificationFactoryProvider;
        this.testCaseFactoryProvider = builder.testCaseFactoryProvider;
        this.pdslTestParameters = builder.pdslTestParameters;
    }

    public String getContext() {
        return context;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public URI getResourceRoot() {
        return resourceRoot;
    }

    public Optional<Class<? extends Parser>> getDslRecognizerParser() {
        return dslRecognizerParser;
    }

    public Optional<Class<? extends Lexer>> getDslRecognizerLexer() {
        return dslRecognizerLexer;
    }

    public Optional<Supplier<? extends TraceableTestRunExecutor>> getTestRunExecutor() {
        return testRunExecutor;
    }

    public Optional<Supplier<? extends TestResourceFinderGenerator>> getResourceFinder() {
        return resourceFinder;
    }

    public String getRecognizerRule() {
        return recognizerRule;
    }

    public Supplier<? extends TestSpecificationFactoryGenerator> getSpecificationFactoryProvider() {
        return specificationFactoryProvider;
    }

    public Supplier<? extends TestCaseFactory> getTestCaseFactoryProvider() {
        return testCaseFactoryProvider;
    }

    public  Collection<PdslTestParameter> getPdslTestParameters() {
        return pdslTestParameters;
    }

    public static Builder createGeneralPdslConfig(Supplier<? extends TestCaseFactory> testCaseFactoryProvider,
                                                  Supplier<? extends TestSpecificationFactoryGenerator> specificationFactoryProvider,
                                                  Collection<PdslTestParameter> pdslTestParameters) {
        return new Builder(testCaseFactoryProvider, specificationFactoryProvider, pdslTestParameters);
    }

    private static final TestCaseFactory GHERKIN_TEST_SPECIFICATION_FACTORY_SINGLETON = new PreorderTestCaseFactory();
    public static Builder createGherkinPdslConfig(Collection<PdslTestParameter> pdslTestParameters) {
        return new Builder(() -> GHERKIN_TEST_SPECIFICATION_FACTORY_SINGLETON,
                () -> filter -> new DefaultGherkinTestSpecificationFactory.Builder(filter).build(),
                pdslTestParameters);
    }

    /**
     * A builder for creating a PdslConfigParameter.
     */
    public static class Builder {
        private String context = "Unspecified";
        private String applicationName = "Polymorphic DSL System Under Test";
        private URI resourceRoot = Paths.get("./").toUri();
        private Optional<Class<? extends Parser>> dslRecognizerParser = Optional.empty();
        private Optional<Class<? extends Lexer>> dslRecognizerLexer = Optional.empty();
        private Optional<Supplier<? extends TraceableTestRunExecutor>> testRunExecutor = Optional.empty();
        private Optional<Supplier<? extends TestResourceFinderGenerator>> resourceFinder = Optional.empty();
        private String recognizerRule = RecognizedBy.DEFAULT_RECOGNIZER_RULE_NAME;
        private Supplier<? extends TestSpecificationFactoryGenerator> specificationFactoryProvider;
        private Supplier<? extends TestCaseFactory> testCaseFactoryProvider;
        private Collection<PdslTestParameter> pdslTestParameters;

        Builder(Supplier<? extends TestCaseFactory> testCaseFactoryProvider,
                       Supplier<? extends TestSpecificationFactoryGenerator> specificationFactoryProvider,
                       Collection<PdslTestParameter> pdslTestParameters) {
            Preconditions.checkNotNull(pdslTestParameters, "PDSL test parameters cannot be null!");
            Preconditions.checkArgument(!pdslTestParameters.isEmpty(), "PDSL test parameters cannot be empty!");
            this.testCaseFactoryProvider = testCaseFactoryProvider;
            this.specificationFactoryProvider = specificationFactoryProvider;
            this.pdslTestParameters = pdslTestParameters;
        }

        Builder(Supplier<? extends TestCaseFactory> testCaseFactoryProvider,
                Supplier<? extends TestSpecificationFactoryGenerator> specificationFactoryProvider) {
            Preconditions.checkNotNull(pdslTestParameters, "PDSL test parameters cannot be null!");
            Preconditions.checkArgument(!pdslTestParameters.isEmpty(), "PDSL test parameters cannot be empty!");
            this.testCaseFactoryProvider = testCaseFactoryProvider;
            this.specificationFactoryProvider = specificationFactoryProvider;
        }

        public Builder withContext(String context) {
            this.context = context;
            return this;
        }

        public Builder withApplicationName(String applicationName) {
            this.applicationName = applicationName;
            return this;
        }

        public Builder withResourceRoot(URI resourceRoot) {
            this.resourceRoot = resourceRoot;
            return this;
        }

        public Builder withDslRecognizerParser(Class<? extends Parser> dslRecognizerParser) {
            this.dslRecognizerParser = Optional.ofNullable(dslRecognizerParser);
            return this;
        }

        public Builder withDslRecognizerLexer(Class<? extends Lexer> dslRecognizerLexer) {
            this.dslRecognizerLexer = Optional.ofNullable(dslRecognizerLexer);
            return this;
        }

        public Builder withTestRunExecutor(Supplier<? extends TraceableTestRunExecutor> executor) {
            this.testRunExecutor = Optional.of(executor);
            return this;
        }

        public Builder withTestResourceFinderGenerator(Supplier<? extends TestResourceFinderGenerator> resourceFinderGenerator) {
            this.resourceFinder = Optional.of(resourceFinderGenerator);
            return this;
        }

        public Builder withTestSpecificationFactoryGenerator(Supplier<? extends TestSpecificationFactoryGenerator> testSpecificationFactoryGenerator) {
            Preconditions.checkNotNull(testSpecificationFactoryGenerator, "Test specifcation factory generator cannot be null!");
            this.specificationFactoryProvider = testSpecificationFactoryGenerator;
            return this;
        }

        public Builder withTestCaseFactory(Supplier<? extends TestCaseFactory> testCaseFactoryProvider) {
            this.testCaseFactoryProvider = testCaseFactoryProvider;
            return this;
        }

        public Builder withPdslTestParameter(Collection<PdslTestParameter> pdslTestParameters) {
            Preconditions.checkNotNull(pdslTestParameters, "Pdsl test parameters cannot be null!");
            Preconditions.checkArgument(pdslTestParameters.isEmpty(), "PDSL test parameters cannot be empty!");
            this.pdslTestParameters = pdslTestParameters;
            return this;
        }

        public Builder withRecognizerRule(String recognizerRule) {
            Preconditions.checkNotNull(recognizerRule, "recognizer rule cannot be null!");
            Preconditions.checkArgument(!recognizerRule.isEmpty(), "Recognizer rule cannot be empty!");
            this.recognizerRule = recognizerRule;
            return this;
        }

        public Builder withPdslTestParameters(Collection<PdslTestParameter> pdslTestParameters) {
            Preconditions.checkNotNull(pdslTestParameters, "PDSL test parameters cannot be null!");
            Preconditions.checkArgument(!pdslTestParameters.isEmpty(), "PDSL test parameters cannot be empty!");
            this.pdslTestParameters = pdslTestParameters;
            return this;
        }

        /**
         * Creates a PdslConfigParameter.
         *
         * A non-empty list of PdslTestParameters must have been provided
         * otherwise a runtime exception will be thrown.
         */
        public PdslConfigParameter build() {
            if (pdslTestParameters.isEmpty()) {
                throw new IllegalStateException("PDSL test parameters cannot be empty!");
            }
            return new PdslConfigParameter(this);
        }

    }

    private static final class DefaultResourceFinderGenerator implements Supplier<TestResourceFinderGenerator> {
        /**
         * Creates a generator for resource finders.
         * @param resourceRoot the root directory to search in
         */
        public DefaultResourceFinderGenerator(String resourceRoot) {
            if (resourceRoot.startsWith("file:///")) {
                resourceRoot = resourceRoot.replaceFirst("file:///", "");
            }
            this.INSTANCE = new FileSystemTestResourceGenerator(resourceRoot);
        }
        private final TestResourceFinderGenerator INSTANCE;

        @Override
        public TestResourceFinderGenerator get() {
            return INSTANCE;
        }
    }

    public static RecognizerParams adapt(PdslConfigParameter parameter) {
        return new RecognizerParams(
                parameter.getContext(),
                parameter.getApplicationName(),
                parameter.getResourceRoot().toString(),
                convert(parameter.getPdslTestParameters(), parameter),
                parameter.getDslRecognizerLexer().orElse(ACCESSOR.getEmptyRecognizerLexerClass()),
                parameter.getDslRecognizerParser().orElse(ACCESSOR.getEmptyRecognizerParserClass()),
                new RecognizerParams.PdslSuppliers(
                        parameter.getResourceFinder().isPresent() ? parameter.getResourceFinder().get()
                                : new DefaultResourceFinderGenerator(parameter.getResourceRoot().toString()),
                        parameter.getSpecificationFactoryProvider(),
                        parameter.getTestCaseFactoryProvider()

                )
        );
    }



    private static List<PdslTestParams> convert(Collection<PdslTestParameter> parameters,
                                                PdslConfigParameter config) {
        List<PdslTestParams> params = new ArrayList<>(parameters.size());
        for (PdslTestParameter p : parameters) {
            PdslTestParams pdslTestParams = new PdslTestParams(
                    getRecognizerLexer(config, p),
                    getRecognizerParser(config, p),
                    getInterpreterParams(config, p),
                    List.of(p.getTagExpression()),
                    p.getIncludesResources(),
                    p.getExcludesResources());
            params.add(pdslTestParams);
        }
        return params;
    }

    private static Class<? extends Lexer> getRecognizerLexer(PdslConfigParameter config, PdslTestParameter param) {
        if (param.getRecognizedByLexer().isPresent()) {
            return param.getRecognizedByLexer().get();
        }
        if (config.getDslRecognizerLexer().isPresent()) {
            return config.getDslRecognizerLexer().get();
        }
        return param.getLexer();
    }

    private static Class<? extends Parser> getRecognizerParser(PdslConfigParameter config, PdslTestParameter param) {
        if (param.getRecognizedByParser().isPresent()) {
            return param.getRecognizedByParser().get();
        }
        if (config.getDslRecognizerParser().isPresent()) {
            return config.getDslRecognizerParser().get();
        }
        return param.getParser();
    }

    private static Supplier<InterpreterObj> getInterpreterObj(PdslTestParameter param) {
        if (param.getVisitor().isPresent()) {
            return () -> new InterpreterObj(param.getVisitor().get().get());
        }
        if (param.getListener().isPresent()) {
            return () -> new InterpreterObj(param.getListener().get().get());
        }
        throw new IllegalArgumentException("No parser/visitor was attached to PdslTestParameter!");
    }

    private static InterpreterParam[] getInterpreterParams(PdslConfigParameter config, PdslTestParameter param) {

        if (param.getInterpreters().isPresent()) {
            return param.getInterpreters().get().stream()
                    .map(i -> new InterpreterParam(
                                    i.parser(),
                                    i.lexer(),
                                    i::interpreterObj,
                                    new String[] {param.getTagExpression()},
                                    param.getIncludesResources(),
                                    param.getExcludesResources(),
                                    i.interpreterObj().getStartRule(),
                                    param.getRecognizerRule().isPresent() ? param.getRecognizerRule().get() : config.getRecognizerRule()
                            )
                    ).toList().toArray(new InterpreterParam[]{});
        }
        return new InterpreterParam[] {
                new InterpreterParam(
                        param.getParser(),
                        param.getLexer(),
                        getInterpreterObj(param),
                        new String[] {param.getTagExpression()},
                        param.getIncludesResources(),
                        param.getExcludesResources(),
                        param.getStartRule(),
                        param.getRecognizerRule().isPresent() ? param.getRecognizerRule().get() : config.getRecognizerRule()
                )
        };
    }
}
