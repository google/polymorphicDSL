package com.pdsl.runners;

import com.google.common.base.Preconditions;
import com.pdsl.executors.InterpreterObj;
import com.pdsl.executors.TraceableTestRunExecutor;
import com.pdsl.gherkin.DefaultGherkinTestSpecificationFactory;
import com.pdsl.specifications.TestResourceFinderGenerator;
import com.pdsl.specifications.TestSpecificationFactory;
import com.pdsl.testcases.PreorderTestCaseFactory;
import com.pdsl.testcases.TestCaseFactory;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

import javax.inject.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * A utility class for converting JUnit4 annotations into a RecognizerParams object.
 */
public final class JUnit4RecognizerParamsConverter {

    private JUnit4RecognizerParamsConverter() {
    }

    private static final ExecutorHelper HELPER = new ExecutorHelper();

    public record PdslTestDto(PdslTest pdslTest, Optional<RecognizedBy> recognizedBy) {}

    public static RecognizerParams convert(PdslTestDto pdslTestDto, PdslConfiguration configuration) {
        Preconditions.checkNotNull(pdslTestDto, "PdslTest DTOs cannot be null!");
        Preconditions.checkNotNull(pdslTestDto.recognizedBy, "RecognizedBy cannot be null!");
        Preconditions.checkNotNull(pdslTestDto.pdslTest, "PdslTest cannot be null!");
        Preconditions.checkNotNull(configuration, "PdslConfiguration cannot be null!");
        PdslTestParams pdslTestParams = getPdslTestParams(pdslTestDto, configuration);
        return new RecognizerParams(
                configuration.context(),
                configuration.applicationName(),
                configuration.resourceRoot(),
                List.of(pdslTestParams),
                configuration.dslRecognizerLexer(),
                configuration.dslRecognizerParser(),
                new RecognizerParams.PdslSuppliers(
                        () -> HELPER.makeTestResourceFinderGenerator(configuration.resourceFinder(), configuration.resourceRoot()).get(),
                        configuration instanceof GherkinWrapperConfiguration
                                ?  () -> filter -> new DefaultGherkinTestSpecificationFactory.Builder(filter)
                                .withRecognizerParser(getRecognizerParser(pdslTestDto, configuration))
                                .withRecognizerLexer(getRecognizerLexer(pdslTestDto, configuration))
                                .withRecognizerRule(getSyntaxCheckRule(pdslTestDto, configuration))
                                .build()
                                : () -> HELPER.makeSpecificationFactoryGenerator(configuration.specificationFactoryProvider()).get(),
                        () -> HELPER.makeTestCaseFactoryProvider(configuration.testCaseFactoryProvider()).get()
                )
        );
    }

    public static RecognizerParams convert(PdslTestDto pdslTestDto, PdslGherkinApplication pdslGherkinApplication) {
        Preconditions.checkNotNull(pdslTestDto, "PdslTest DTOs cannot be null!");
        Preconditions.checkNotNull(pdslTestDto.recognizedBy, "RecognizedBy cannot be null!");
        Preconditions.checkNotNull(pdslTestDto.pdslTest, "PdslTest cannot be null!");
        Preconditions.checkNotNull(pdslGherkinApplication, "PdslGherkinApplication cannot be null!");
        PdslTestParams pdslTestParams = getPdslTestParams(pdslTestDto, convert(pdslGherkinApplication));
        ProviderInstances providers = getProviderInstances(pdslGherkinApplication);

        return new RecognizerParams(
                pdslGherkinApplication.context(),
                pdslGherkinApplication.applicationName(),
                pdslGherkinApplication.resourceRoot(),
                List.of(pdslTestParams),
                pdslGherkinApplication.dslRecognizerLexer(),
                pdslGherkinApplication.dslRecognizerParser(),
                new RecognizerParams.PdslSuppliers(
                        () -> providers.testResourceFinderGenerator().get(),
                        () -> providers.testSpecificationFactoryGenerator().get(),
                        () -> providers.testCaseFactory().get()
                )
        );
    }

    private static <T> T createObjectWithDDefaultConstructor(Class<T> object) {
        try {
            return object.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException(String.format("Could not find a default constructor for the Provider<ParseTreeListener> %s%n"
                    + "Note the Provider MUST have a constructor that takes no parameters, but see the below error for more details.", object.getSimpleName()), e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(String.format("Could not create a %s. Note the provider MUST be public.", object), e);
        } catch (InstantiationException | InvocationTargetException e) {
            throw new IllegalStateException(String.format("Something went wrong when trying to create the Parse Tree Listener %s.%n", object.getSimpleName()), e);
        }
    }

    private static InterpreterParam[] getInterpreterParams(PdslTestDto pdslTestDto, PdslConfiguration pdslConfiguration) {
        PdslTest pdslTest = pdslTestDto.pdslTest;
        // Only one parameter can be provided for interpreters
        if (!(!pdslTest.visitor().equals(EmptyParseTreeVisitorProvider.class)
            ^ !pdslTest.listener().equals(EmptyParseTreeListenerProvider.class)
            ^ pdslTest.interpreters().length > 0)) {
                throw new IllegalArgumentException(
                        String.format("""
            PdslTest object cannot have both listener/visitor set and interpreters!
            PdslTest: %s,
            Visitor: %s,
            Listener: %s,
            Interpreters: %s
        """.stripIndent(), pdslTest,
                                pdslTest.visitor().equals(EmptyParseTreeVisitorProvider.class) ? "None" : pdslTest.visitor(),
                                pdslTest.listener().equals(EmptyParseTreeListenerProvider.class) ? "None" : pdslTest.listener(),
                                Arrays.toString(pdslTest.interpreters())));
        }
        Optional<Supplier<InterpreterObj>> provider = Optional.empty();
        if (!pdslTest.visitor().equals(EmptyParseTreeVisitorProvider.class)) {
            provider = Optional.of(getInterpreterObjFromVisitorClass(pdslTest.visitor()));
        }
        if (!pdslTest.listener().equals(EmptyParseTreeListenerProvider.class)) {
            provider = Optional.of(getInterpreterObjFromListenerClass(pdslTest.listener()));
        }
        if (provider.isPresent()) {
            return new InterpreterParam[] { new InterpreterParam(
                    pdslTest.parser(), pdslTest.lexer(),
                    provider.get(),
                    new String[]{pdslTest.tags()},
                    pdslTest.includesResources(),
                    pdslTest.excludesResources(),
                    pdslTest.startRule(),
                    getSyntaxCheckRule(pdslTestDto, pdslConfiguration)
            )};
        }
        List<InterpreterParam> interpreterParams = new ArrayList<>();
        if (pdslTest.interpreters().length > 0) {
            for (int i=0; i < pdslTest.interpreters().length; i++) {
                Interpreter interpreter = pdslTest.interpreters()[i];
                Supplier<InterpreterObj> interpreterObjProvider = !interpreter.listener().equals(EmptyParseTreeListenerProvider.class)
                        ? getInterpreterObjFromListenerClass(interpreter.listener())
                        : getInterpreterObjFromVisitorClass(interpreter.visitor());
                interpreterParams.add(
                        new InterpreterParam(
                                interpreter.parser(), interpreter.lexer(),
                                interpreterObjProvider,
                                new String[]{pdslTest.tags()},
                                pdslTest.includesResources(),
                                pdslTest.excludesResources(),
                                pdslTest.startRule(),
                                getSyntaxCheckRule(pdslTestDto, pdslConfiguration)
                        )
                );
            }
        }
        return interpreterParams.toArray(InterpreterParam[]::new);
    }

    private static Supplier<InterpreterObj> getInterpreterObjFromListenerClass(Class<? extends Provider<? extends ParseTreeListener>> clazz) {
        return  new Supplier<InterpreterObj>() {
            private final Provider<ParseTreeListener> LISTENER = (Provider<ParseTreeListener>) createObjectWithDDefaultConstructor(clazz);
            @Override
            public InterpreterObj get() {
                return new InterpreterObj(LISTENER.get());
            }
        };
    }

    private static Supplier<InterpreterObj> getInterpreterObjFromVisitorClass(Class<? extends Provider<? extends ParseTreeVisitor<?>>> clazz) {
        return  new Supplier<>() {
            private final Provider<ParseTreeVisitor<?>> VISITOR = (Provider<ParseTreeVisitor<?>>) createObjectWithDDefaultConstructor(clazz);
            @Override
            public InterpreterObj get() {
                return new InterpreterObj(VISITOR.get());
            }
        };
    }
    private static PdslTestParams getPdslTestParams(
            PdslTestDto pdslTestDto, PdslConfiguration pdslConfiguration) {

       return PdslTestParams.from(
               getRecognizerLexer(pdslTestDto, pdslConfiguration),
               getRecognizerParser(pdslTestDto, pdslConfiguration),
               getInterpreterParams(pdslTestDto, pdslConfiguration),
               List.of(pdslTestDto.pdslTest.tags()),
               pdslTestDto.pdslTest().includesResources(),
               pdslTestDto.pdslTest().excludesResources()
       );
    }

    public record ProviderInstances(
            Supplier<? extends TestSpecificationFactoryGenerator> testSpecificationFactoryGenerator,
            Supplier<? extends TestResourceFinderGenerator> testResourceFinderGenerator,
            Supplier<? extends TestCaseFactory> testCaseFactory,
            Supplier<? extends TraceableTestRunExecutor> testRuneExecutor) {
    }

    public static ProviderInstances getProviderInstances(PdslGherkinApplication pdslGherkinApplication) {

        return new ProviderInstances(
                () -> filter -> new DefaultGherkinTestSpecificationFactory.Builder(filter)
                        .withRecognizerParser(pdslGherkinApplication.dslRecognizerParser())
                        .withRecognizerLexer(pdslGherkinApplication.dslRecognizerLexer())
                        .withRecognizerRule(pdslGherkinApplication.recognizerRule())
                        .build(),
                () -> HELPER.makeTestResourceFinderGenerator(pdslGherkinApplication.resourceFinder(), pdslGherkinApplication.resourceRoot()).get(),
                new PreorderTestCaseFactory.DefaultSupplier(),
                () -> HELPER.makeTraceableTestRunExecuter(pdslGherkinApplication.testRunExecutor()).get()
        );
    }

    public static PdslConfiguration convert(PdslGherkinApplication pdslGherkinApplication) {
        Preconditions.checkNotNull(pdslGherkinApplication, "PdslGherkinApplication cannot be null!");

        return new GherkinWrapperConfiguration(pdslGherkinApplication);



    }

public static class GherkinWrapperConfiguration implements PdslConfiguration {

    private final PdslGherkinApplication configuration;
    private GherkinWrapperConfiguration(PdslGherkinApplication pdslGherkinApplication) {
        configuration = pdslGherkinApplication;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        return configuration.equals(obj);
    }

    @Override
    public int hashCode() {
        return configuration.hashCode();
    }

    @Override
    public String toString() {
        return configuration.toString();
    }

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
        return configuration.testRunExecutor();
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
        throw new UnsupportedOperationException();
    }

    @Override
    public Class<? extends Provider<? extends TestCaseFactory>> testCaseFactoryProvider() {
        return PreorderTestCaseFactory.DefaultProvider.class;
    }

    private static class GherkinWrapperSpecificationGenerator implements TestSpecificationFactoryGenerator {
        private final PdslGherkinApplication pdslGherkinApplication;

        private GherkinWrapperSpecificationGenerator(PdslGherkinApplication pdslGherkinApplication) {
            this.pdslGherkinApplication = pdslGherkinApplication;
        }

        @Override
        public TestSpecificationFactory get(PolymorphicDslPhraseFilter filter) {
            return new DefaultGherkinTestSpecificationFactory.Builder(filter)
                    .withRecognizerParser(pdslGherkinApplication.dslRecognizerParser())
                    .withRecognizerLexer(pdslGherkinApplication.dslRecognizerLexer())
                    .withRecognizerRule(pdslGherkinApplication.recognizerRule())
                    .build();
        }
    }
}

private static String getSyntaxCheckRule(JUnit4RecognizerParamsConverter.PdslTestDto dto, PdslConfiguration configuration) {
    // If a RecognizedBy annotation was provided, use that
    if (dto.recognizedBy().isPresent() && !dto.recognizedBy.get().recognizerRule().isBlank()) {
        return dto.recognizedBy.get().recognizerRule();
    }
    // Else use the default syntax check
    if (!configuration.recognizerRule().isEmpty() && !configuration.recognizerRule().isBlank()) {
        return configuration.recognizerRule();
    }
    // Otherwise assume the start rule is the same as the recognizer rule
    return dto.pdslTest.startRule();
}

private static Class<? extends Lexer> getRecognizerLexer(JUnit4RecognizerParamsConverter.PdslTestDto dto, PdslConfiguration configuration) {
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

private static Class<? extends Parser> getRecognizerParser(JUnit4RecognizerParamsConverter.PdslTestDto dto, PdslConfiguration configuration) {
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
