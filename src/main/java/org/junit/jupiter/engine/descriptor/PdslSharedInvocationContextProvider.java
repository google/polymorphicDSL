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

/**
 * A JUnit5 invocation context provider that provides tests created from the Polymorphic DSL test framework.
 *
 * The method getInvocationContext serves as the primary means of creating PDSL tests for JUnit5. The end user is
 * expected to use this in conjunction with a JUnit5 TestTemplate in order to run those tests.
 *
 * Note that a PdslConfigParameter is also used to provide information about the specific tests being run. That object
 * has methods for generating test cases written in Gherkin (via the static constructor createGherkinPdslConfig) or
 * using a different DSL you've created (via createGeneralPdslConfig).
 *
 * E.g
 *
 *  private static class InterpreterOneExtension extends PdslSharedInvocationContextProvider {
 *         @Override
 *         public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
 *             return getInvocationContext(PdslConfigParameter.createGherkinPdslConfig(
 *                             List.of(
 *                                     new PdslTestParameter.Builder(List.of(
 *                                             new Interpreter(InterpreterOneLexer.class, InterpreterOneParser.class,
 *                                                     new InterpreterObj(
 *                                                             new InterpreterOne.InterpreterOneListenerProvider().get()))
 *                                         )
 *                                     )
 *                                             .withStartRule("interpreterAllRules")
 *                                             .withIncludedResources(new String[] {"InterpreterAll.feature"})
 *                                             .withRecognizer(InterpreterAllLexer.class, InterpreterAllParser.class)
 *                                             .withRecognizerRule("polymorphicDslAllRules")
 *                                             .build()
 *                             )
 *                     )
 *                     .withApplicationName("Polymorphic DSL Framework")
 *                     .withContext("User Acceptance Test")
 *                     .withResourceRoot(Paths.get("src/test/resources/framework_specifications/features/interpreter/").toUri())
 *                     .withRecognizerRule("polymorphicDslAllRules")
 *                     .build())
 *                     .stream();
 *         }
 *     }
 */
public abstract class PdslSharedInvocationContextProvider
        implements InvocationInterceptor, TestTemplateInvocationContextProvider {

    private static final TraceableTestRunExecutor DEFAULT_EXECUTOR = new DefaultPolymorphicDslTestExecutor();
    private static final SharedTestSuiteVisitor SHARED_TEST_SUITE_VISITOR = new SharedTestSuiteVisitor();
    private static final JUnit5DefaultPackageAccessor ACCESSOR = new JUnit5DefaultPackageAccessor(new JupiterDescriptorKey());

    @Override
    public boolean supportsTestTemplate(ExtensionContext context) {
        return true;
    }

    /**
     * Creates the TestTemplateInvocationContext objects needed for a JUnit5 TestTemplate.
     * @param parameter the Pdsl configuration to generate a suite of tests.
     * @return a collection of TestTempalteInvocationContext objects to be used by a JUnit5 TestTemplate
     */
    public Collection<TestTemplateInvocationContext> getInvocationContext(PdslConfigParameter parameter) {
        // Validations
        if (parameter.getDslRecognizerLexer().isEmpty() || parameter.getDslRecognizerParser().isEmpty()) {
            throw new IllegalArgumentException("""
        When making a multi-interpreter test you MUST specify a recognizer!
        For documentation on why and how to make recognizers, please visit:
        https://github.com/google/polymorphicDSL/blob/main/documentation/recognizers.adoc
        """);
        }
        SharedTestSuite suite = SHARED_TEST_SUITE_VISITOR.recognizerParamsOperation(PdslConfigParameter.adapt(parameter));

        return suite.getSharedTestCaseList().stream()
                .map(testCase ->  new PdslExecutable(testCase,
                        parameter.getTestRunExecutor().isPresent()
                                ? parameter.getTestRunExecutor().get().get()
                                : DEFAULT_EXECUTOR,
                        parameter.getContext()))
                .map(PdslInvocationContext::new)
                .collect(Collectors.toUnmodifiableList());
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

}
