package org.junit.jupiter.engine.descriptor;

import com.google.common.base.Preconditions;
import com.pdsl.executors.TraceableTestRunExecutor;
import com.pdsl.runners.*;
import com.pdsl.specifications.LineDelimitedTestSpecificationFactory;
import com.pdsl.specifications.TestResourceFinderGenerator;
import com.pdsl.testcases.PreorderTestCaseFactory;
import com.pdsl.testcases.TestCaseFactory;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;

import java.net.URI;
import java.nio.file.Paths;
import java.util.Collection;
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

    public static Builder createGherkinPdslConfig(Collection<PdslTestParameter> pdslTestParameters) {
        // Gherkin uses predetermined specification/test factories. Because it ignores them put in placeholder suppliers.
        return new Builder (() -> new PreorderTestCaseFactory(), () -> new LineDelimitedTestSpecificationFactory.Generator(), pdslTestParameters);
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
}
