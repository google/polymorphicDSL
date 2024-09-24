package com.pdsl.runners;

import com.google.common.base.Preconditions;
import com.pdsl.executors.DefaultPolymorphicDslTestExecutor;
import com.pdsl.executors.InterpreterObj;
import com.pdsl.executors.TraceableTestRunExecutor;
import com.pdsl.gherkin.DefaultGherkinTestSpecificationFactory;
import com.pdsl.runners.junit.PolymorphicDslJUnitException;
import com.pdsl.specifications.TestResourceFinderGenerator;
import com.pdsl.specifications.TestSpecificationFactory;
import com.pdsl.testcases.PreorderTestCaseFactory;
import com.pdsl.testcases.SharedTestSuite;
import com.pdsl.testcases.TestCaseFactory;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;

import javax.inject.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A utility class for converting JUnit4 annotations into a RecognizerParams object.
 */
public final class JUnit4RecognizerParamsConverter {

    private JUnit4RecognizerParamsConverter() {}

    private static final ExecutorHelper HELPER = new ExecutorHelper();
    public record PdslTestDto(PdslTest pdslTest, Optional<RecognizedBy> recognizedBy) {
    }

    public static RecognizerParams<SharedTestSuite> convert(PdslTestDto pdslTestDto, PdslGherkinApplication gherkinConfig) {
        Preconditions.checkNotNull(pdslTestDto, "PdslTest DTOs cannot be null!");
        Preconditions.checkNotNull(pdslTestDto.recognizedBy, "RecognizedBy cannot be null!");
        Preconditions.checkNotNull(pdslTestDto.pdslTest, "PdslTest cannot be null!");
        Preconditions.checkNotNull(gherkinConfig, "PdslGherkinApplication cannot be null!");
        PdslConfiguration configuration = convert(gherkinConfig);
       return convert(pdslTestDto, configuration);
    }

    public static RecognizerParams<SharedTestSuite> convert(PdslTestDto pdslTestDto, PdslConfiguration configuration) {
        Preconditions.checkNotNull(pdslTestDto, "PdslTest DTOs cannot be null!");
        Preconditions.checkNotNull(pdslTestDto.recognizedBy, "RecognizedBy cannot be null!");
        Preconditions.checkNotNull(pdslTestDto.pdslTest, "PdslTest cannot be null!");
        Preconditions.checkNotNull(configuration, "PdslConfiguration cannot be null!");
        PdslTestParams<SharedTestSuite> pdslTestParams = getPdslTestParams(pdslTestDto, configuration);
        ExecutorHelper.PdslProvidersDto dto = HELPER.makePdslElements(configuration);
        return new RecognizerParams<>(
                configuration.context(),
                configuration.applicationName(),
                configuration.resourceRoot(),
                List.of(pdslTestParams),
                configuration.dslRecognizerLexer(),
                configuration.dslRecognizerParser(),
                new RecognizerParams.PdslProviders(
                        dto.getResourceFinder(),
                        dto.getTestSpecificationFactoryGenerator(),
                        dto.getTestCaseFactoryProvider()
                )
        );
    }

    private static PdslTestParams<SharedTestSuite> getPdslTestParams(
        PdslTestDto pdslTestDto, PdslConfiguration pdslConfiguration) {

        PdslTest pdslTest = pdslTestDto.pdslTest;
        List<InterpreterParam> interpreterObjs = HELPER.getParseTreeTraversal(pdslTest).stream()
                .map(t -> t.getListener().isPresent()
                        ? new InterpreterObj(t.getListener().get())
                        : new InterpreterObj(t.getVisitor().orElseThrow()))
                .map(interpreterObj -> new InterpreterParam(
                                pdslTest.parser(),
                                pdslTest.lexer(),
                                () -> interpreterObj,
                                new String[]{pdslTest.tags()},
                                pdslTest.includesResources(),
                                pdslTest.excludesResources(),
                                pdslTest.startRule(),
                                getSyntaxCheckRule(pdslTestDto, pdslConfiguration)
                        )
                ).toList();

        PdslTestParams<SharedTestSuite> param = new PdslTestParams<>(
                getRecognizerLexer(pdslTestDto, pdslConfiguration),
                getRecognizerParser(pdslTestDto, pdslConfiguration),
                interpreterObjs.toArray(new InterpreterParam[interpreterObjs.size()]),
                List.of(pdslTestDto.pdslTest.tags()),
                pdslTestDto.pdslTest.includesResources(),
                pdslTestDto.pdslTest.excludesResources()
        );
        return param;
    }

    private static PdslConfiguration convert(PdslGherkinApplication pdslGherkinApplication) {
        return new PdslConfiguration() {
            private final PdslGherkinApplication configuration = pdslGherkinApplication;
            @Override
            public Class<? extends Annotation> annotationType() {
                return PdslConfiguration.class;
            }

            @Override
            public String context() {
                return configuration.context();
            }

            @Override
            public String applicationName() {
                return configuration.applicationName();
            }

            @Override
            public String resourceRoot() {
                return configuration.resourceRoot();
            }

            @Override
            public Class<? extends Parser> dslRecognizerParser() {
                return configuration.dslRecognizerParser();
            }

            @Override
            public Class<? extends Lexer> dslRecognizerLexer() {
                return configuration.dslRecognizerLexer();
            }

            @Override
            public Class<? extends Provider<? extends TraceableTestRunExecutor>> testRunExecutor() {
                return DefaultExecutorProvider.class;
            }

            @Override
            public Class<? extends Provider<? extends TestResourceFinderGenerator>> resourceFinder() {
                return configuration.resourceFinder();
            }

            @Override
            public String recognizerRule() {
                return configuration.recognizerRule();
            }

            @Override
            public Class<? extends Provider<? extends TestSpecificationFactoryGenerator>> specificationFactoryProvider() {
                return new Provider<TestSpecificationFactoryGenerator>() {
                    @Override
                    public TestSpecificationFactoryGenerator get() {
                        return filter -> new DefaultGherkinTestSpecificationFactory.Builder(filter)
                                .withRecognizerParser(configuration.dslRecognizerParser())
                                .withRecognizerLexer(configuration.dslRecognizerLexer())
                                .withRecognizerRule(configuration.recognizerRule())
                                .build();
                    }
                }.getClass();

            }

            @Override
            public Class<? extends Provider<? extends TestCaseFactory>> testCaseFactoryProvider() {
                return PreorderTestCaseFactory.DefaultProvider.class;
            }
        };

    }

    private static final class DefaultExecutorProvider implements Provider<TraceableTestRunExecutor> {
        private static final TraceableTestRunExecutor DEFAULT_EXECUTOR = new DefaultPolymorphicDslTestExecutor();

        @Override
        public TraceableTestRunExecutor get() {
            return DEFAULT_EXECUTOR;
        }
    }

    private static String getSyntaxCheckRule(PdslTestDto dto, PdslConfiguration configuration) {
        // If a RecognizedBy annotation was provided, use that
        if (dto.recognizedBy().isPresent()) {
            return dto.recognizedBy.get().recognizerRule();
        }
        // Else use the default syntax check
        if (!configuration.recognizerRule().isEmpty()) {
            return configuration.recognizerRule();
        }
        // Otherwise assume the start rule is the same as the recognizer rule
        return dto.pdslTest.startRule();
    }

    private static Class<? extends Lexer> getRecognizerLexer(PdslTestDto dto, PdslConfiguration configuration) {
        // If a RecognizedBy annotation was provided, use that
        if (dto.recognizedBy().isPresent()) {
            return dto.recognizedBy().get().dslRecognizerLexer();
        }
        // Else use the default configuration is one was specified
        if (!configuration.dslRecognizerLexer().equals(EmptyRecognizerLexer.class)) {
            return configuration.dslRecognizerLexer();
        }
        // Otherwise assume the PdslTest doubles as a recognizer
        return dto.pdslTest.lexer();
    }

    private static Class<? extends Parser> getRecognizerParser(PdslTestDto dto, PdslConfiguration configuration) {
        // If a RecognizedBy annotation was provided, use that
        if (dto.recognizedBy().isPresent()) {
            return dto.recognizedBy().get().dslRecognizerParser();
        }
        // Else use the default configuration is one was specified
        if (!configuration.dslRecognizerLexer().equals(EmptyRecognizerLexer.class)) {
            return configuration.dslRecognizerParser();
        }
        // Otherwise assume the PdslTest doubles as a recognizer
        return dto.pdslTest.parser();
    }
}
