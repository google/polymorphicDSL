package org.junit.jupiter.engine.descriptor;

import com.pdsl.executors.DefaultPolymorphicDslTestExecutor;
import com.pdsl.executors.InterpreterObj;
import com.pdsl.executors.TraceableTestRunExecutor;
import com.pdsl.runners.*;
import com.pdsl.specifications.FileSystemTestResourceGenerator;
import com.pdsl.specifications.TestResourceFinderGenerator;
import com.pdsl.testcases.SharedTestSuite;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class PdslSharedInvocationContextProvider
        implements InvocationInterceptor, TestTemplateInvocationContextProvider {

    private static final TraceableTestRunExecutor DEFAULT_EXECUTOR = new DefaultPolymorphicDslTestExecutor();
    private static final SharedTestSuiteVisitor SHARED_TEST_SUITE_VISITOR = new SharedTestSuiteVisitor();
    private static final JUnit5DefaultPackageAccessor ACCESSOR = new JUnit5DefaultPackageAccessor(new JupiterDescriptorKey());
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

    @Override
    public boolean supportsTestTemplate(ExtensionContext context) {
        return true;
    }

    public Collection<TestTemplateInvocationContext> getInvocationContext(PdslConfigParameter parameter) {
        SharedTestSuite suite = SHARED_TEST_SUITE_VISITOR.recognizerParamsOperation(convert(parameter));

        return suite.getSharedTestCaseList().stream()
                .map(testCase ->  new PdslExecutable(testCase,
                        parameter.getTestRunExecutor().isPresent()
                                ? parameter.getTestRunExecutor().get().get()
                                : DEFAULT_EXECUTOR,
                        parameter.getContext()))
                .map(PdslInvocationContext::new)
                .collect(Collectors.toUnmodifiableList());
    }

    private static RecognizerParams<SharedTestSuite> convert(PdslConfigParameter parameter) {
        return new RecognizerParams<>(
                parameter.getContext(),
                parameter.getApplicationName(),
                parameter.getResourceRoot().toString(),
                convert(parameter.getPdslTestParameters(), parameter),
                parameter.getDslRecognizerLexer().orElse(ACCESSOR.getEmptyRecognizerLexerClass()),
                parameter.getDslRecognizerParser().orElse(ACCESSOR.getEmptyRecognizerParserClass()),
                new RecognizerParams.PdslProviders(
                        parameter.getResourceFinder().isPresent() ? parameter.getResourceFinder().get()
                                : new DefaultResourceFinderGenerator(parameter.getResourceRoot().toString()),
                        parameter.getSpecificationFactoryProvider(),
                        parameter.getTestCaseFactoryProvider()

                )
        );
    }

    private static PdslTestParameter.Builder getBuilder(PdslTestParameter p) {
        if (p.getInterpreters().isPresent()) {
            return new PdslTestParameter.Builder(p.getInterpreters().get());
        }
        if (p.getVisitor().isPresent()) {
            return new PdslTestParameter.Builder(p.getLexer(), p.getParser(), p.getVisitor().get());
        }
        if (p.getListener().isPresent()) {
            return new PdslTestParameter.Builder(p.getListener().get(), p.getLexer(), p.getParser());
        }
        throw new IllegalArgumentException(
                "PdslTestParameter must have either a listener, visitor or collection of Interpreters!");

    }

    private static List<PdslTestParams<SharedTestSuite>> convert(Collection<PdslTestParameter> parameters,
                                                                 PdslConfigParameter config) {
        List<PdslTestParams<SharedTestSuite>> params = new ArrayList<>(parameters.size());
        for (PdslTestParameter p : parameters) {
            PdslTestParams<SharedTestSuite> pdslTestParams = new PdslTestParams<>(
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
                            param.getStartRule(),
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
