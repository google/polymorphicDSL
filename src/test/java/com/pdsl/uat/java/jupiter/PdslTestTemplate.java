package com.pdsl.uat.java.jupiter;

import com.google.common.base.Ascii;
import com.pdsl.grammars.*;
import com.pdsl.reports.TestRunResults;
import com.pdsl.runners.PdslConfiguration;
import com.pdsl.runners.PdslGherkinApplication;
import com.pdsl.runners.PdslTest;
import com.pdsl.runners.TestSpecificationFactoryGenerator;
import com.pdsl.runners.junit.PdslGherkinJUnit4Runner;
import com.pdsl.specifications.LineDelimitedTestSpecificationFactory;
import com.pdsl.testcases.PreorderTestCaseFactory;
import com.pdsl.testcases.TestCaseFactory;
import com.pdsl.uat.GherkinJunitRecognizerTest;
import org.antlr.runtime.CommonToken;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.engine.descriptor.*;
import org.junit.runner.RunWith;
import org.yecht.Data;

import java.nio.file.Paths;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertThat;

public class PdslTestTemplate {
    
    @TestTemplate
    @ExtendWith(PdslExtension.class)
    public void pdslTestFrameworkResources(PdslExecutable executable) {
        executable.execute();
    }

    @TestTemplate
    @ExtendWith(PdslGherkinExtension.class)
    public void pdslGherkinTestFrameworkResources(PdslExecutable executable) {
        executable.execute();
    }

    private static final Supplier<ParseTreeListener> parseTreeListenerSupplier = () -> new AllGrammarsParserBaseListener();

    private static class PdslExtension extends PdslGeneralInvocationContextProvider {
        private final Supplier<TestCaseFactory> testCaseFactorySupplier = () -> new PreorderTestCaseFactory.DefaultProvider().get();
        private final Supplier<TestSpecificationFactoryGenerator> testSpecificationFactoryGeneratorSupplier = () -> new LineDelimitedTestSpecificationFactory.Generator();

        @Override
        public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
            return getInvocationContext(PdslConfigParameter.createGeneralPdslConfig(testCaseFactorySupplier, testSpecificationFactoryGeneratorSupplier,
                        List.of(
                                new PdslTestParameter.Builder(parseTreeListenerSupplier,
                                        AllGrammarsLexer.class, AllGrammarsParser.class)
                                        .build()
                        )
                    )
                    .withApplicationName("Polymorphic DSL Framework")
                    .withContext("User Acceptance Test")
                    .withResourceRoot(Paths.get("src/test/resources/testdata/good").toUri())
                    .withRecognizerRule("polymorphicDslAllRules")
                    .build())
                    .stream();
        }
    }

    private static class PdslGherkinExtension extends PdslGherkinInvocationContextProvider {
        private static final ParseTreeVisitor<?> INSTANCE = new JupiterFrameworkVisitor();
        @Override
        public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {

            return getInvocationContext(PdslConfigParameter.createGherkinPdslConfig(
                    List.of(
                            new PdslTestParameter.Builder(
                                    PdslJavaTestRunnerLexer.class, PdslJavaTestRunnerParser.class, () -> INSTANCE)
                                    .withIncludedResources(new String[] {"JavaTestRunner.feature"})
                                    .build()
                            )
                    )
                    .withApplicationName("Polymorphic DSL Framework")
                    .withContext("User Acceptance Test")
                    .withResourceRoot(Paths.get("src/test/resources/framework_specifications/features/java").toUri())
                    .withRecognizerRule("polymorphicDslAllRules")
                    .build())
                    .stream();
        }
    }

    private static class JupiterFrameworkVisitor extends AbstractParseTreeVisitor<Void>  implements PdslJavaTestRunnerParserVisitor<Void> {
        private static final Supplier<ParseTreeVisitor<?>> VISITOR_SUPPLIER = () -> new AllGrammarsParserBaseVisitor<>();
        private PdslTestParameter.Builder pdslTestBuilder;
        private PdslConfigParameter.Builder configBuilder;
        private final Set<ConfigParam> configParameters = new HashSet<>(); //We need to delay initialization of the configBuilder
        private Collection<TestRunResults> testRunResults;

        private enum ConfigParam {
            BAD_RECOGNIZER,
            RESOURCE_ROOT,
            TEST_RUN_EXECUTOR,
            RESOURCE_FINDER,
            RECOGNIZER_RULE,
            GOOD_RECOGNIZER;

            private static ConfigParam create(String str) {
                switch (str) {
                    case "resourceRoot":
                        return RESOURCE_ROOT;
                    case "testRunExecutor":
                        return TEST_RUN_EXECUTOR;
                    case "resourceFinder":
                        return RESOURCE_FINDER;
                    case "dslRecognizerLexer":
                    case "dslRecognizerParser":
                        return GOOD_RECOGNIZER;
                    case "recognizerRule":
                        return RECOGNIZER_RULE;

                    default:
                        throw new IllegalArgumentException("Do not know of a value for " + str);
                }
            }
        }
        public Void visitGivenPdslTest(PdslJavaTestRunnerParser.GivenPdslTestContext ctx) {
            configParameters.clear();
            configBuilder = PdslConfigParameter.createGherkinPdslConfig(
                            List.of(
                                    new PdslTestParameter.Builder(
                                            PdslJavaTestRunnerLexer.class, PdslJavaTestRunnerParser.class, () -> new AllGrammarsParserBaseVisitor<>())
                                            .withIncludedResources(new String[] {"JavaTestRunner.feature"})
                                            .build()
                            )
                    )
                    .withApplicationName("Polymorphic DSL Framework")
                    .withContext("User Acceptance Test")
                    .withResourceRoot(Paths.get("src/test/resources/framework_specifications/features/java").toUri())
                    .withRecognizerRule("polymorphicDslAllRules");
            pdslTestBuilder =  new PdslTestParameter.Builder(
                    PdslJavaTestRunnerLexer.class, PdslJavaTestRunnerParser.class, VISITOR_SUPPLIER)
                    .withIncludedResources(new String[] {"JavaTestRunner.feature"});
            return null;
        }

        public Void visitGivenAllPdslTestsAreValid(PdslJavaTestRunnerParser.GivenAllPdslTestsAreValidContext ctx) {
            pdslTestBuilder
                    .withParser(PdslJavaTestRunnerParser.class)
                    .withLexer(PdslJavaTestRunnerLexer.class);
            return null;
        }
        public Void visitGivenPdslConfigurationSpecifiesProperty(PdslJavaTestRunnerParser.GivenPdslConfigurationSpecifiesPropertyContext ctx){
            configParameters.add(ConfigParam.create(ctx.PARAMETER().getText()));
            return null;
        }

        public Void visitGivenThePdslTestUsesFactoriesThatFilterBasedOnTags(PdslJavaTestRunnerParser.GivenThePdslTestUsesFactoriesThatFilterBasedOnTagsContext ctx) {
            //We use the gherkin test specification factory in this test case which has tag filtering
            return null;
        }
        public Void visitGivenThePdslTestHasATagSpecified(PdslJavaTestRunnerParser.GivenThePdslTestHasATagSpecifiedContext ctx) {
            pdslTestBuilder.withTagExpression("@TaggedScenario"); // Rather than point at another resource
            return null;
        }
        public Void visitGivenTestResourceMarkWithSpecifiedTag(PdslJavaTestRunnerParser.GivenTestResourceMarkWithSpecifiedTagContext ctx) {
            //JavaTestRunner.feature is already marked
            return null;
        }
        public Void visitGivenPdslRecognizedBy(PdslJavaTestRunnerParser.GivenPdslRecognizedByContext ctx) {
            pdslTestBuilder
                    .withRecognizer(JavaRunnerSubgrammarLexer.class, PdslJavaTestRunnerParser.class);
            return null;
        }
        public Void visitGivenPslTestHasPhrasesNotInRecognizer(PdslJavaTestRunnerParser.GivenPslTestHasPhrasesNotInRecognizerContext ctx){
            pdslTestBuilder //Some random feature file that will break execution
                    .withIncludedResources(new String[] {"JavaReporting.feature"});
            return null;
        }
        public Void visitGivenPdslTestHasResourcesThatMightBeRecognizedByConfiguration(PdslJavaTestRunnerParser.GivenPdslTestHasResourcesThatMightBeRecognizedByConfigurationContext ctx)  {
            configParameters.add(Ascii.toUpperCase(ctx.getText()).contains(" NOT ") ? ConfigParam.BAD_RECOGNIZER : ConfigParam.GOOD_RECOGNIZER);
            return null;
        }
        public Void visitGivenPdslTestHasResources(PdslJavaTestRunnerParser.GivenPdslTestHasResourcesContext ctx) {
            if (Ascii.toUpperCase(ctx.getText()).contains(" NOT ")) {
                pdslTestBuilder.withRecognizer(SimpleArithmeticLexer.class, SimpleArithmeticParser.class); // Use some random parser
            } else {
                pdslTestBuilder.withRecognizer(AllGrammarsLexer.class, AllGrammarsParser.class); // Use a parser that always works
            }
            return null;
        }
        public Void visitGivenPdslConfigurationDoesNotSpecifyDefaultRule(PdslJavaTestRunnerParser.GivenPdslConfigurationDoesNotSpecifyDefaultRuleContext ctx){
            // By default nothing is specified in the builder
            return null;
        }
        public Void visitGivenRecognizedBySpecifiesParameter(PdslJavaTestRunnerParser.GivenRecognizedBySpecifiesParameterContext ctx){
            // This negative scenario doesn't apply to the JUnit5 implementation. We should probably move it out of the
            // grammar.
            return null;
        }
        public Void visitWhenTestRunnerExecutes(PdslJavaTestRunnerParser.WhenTestRunnerExecutesContext ctx){
            // Add specified parameters to the pdsl configuration
            for (ConfigParam configParam : configParameters) {
                switch (configParam) {
                    case GOOD_RECOGNIZER:
                            configBuilder
                                    .withDslRecognizerLexer(AllGrammarsLexer.class)
                                    .withDslRecognizerParser(AllGrammarsParser.class);
                            break;
                    case BAD_RECOGNIZER:
                        configBuilder
                                .withDslRecognizerParser(SimpleArithmeticParser.class)
                                .withDslRecognizerLexer(SimpleArithmeticLexer.class);
                        break;
                }
            }

            PdslGherkinInvocationContextProvider invocationContextProvider = new PdslGherkinInvocationContextProvider() {

                @Override
                public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
                    return getInvocationContext(configBuilder.build()).stream();
                }
            };


            Collection<PdslExecutable> executables = invocationContextProvider.createPdslExecutables(configBuilder.build());
            testRunResults = executables.stream()
                    .map(PdslExecutable::execute)
                    .collect(Collectors.toUnmodifiableList());
            return null;
        }
        public Void visitThenAllTestsPass(PdslJavaTestRunnerParser.ThenAllTestsPassContext ctx){
            assertThat(testRunResults.stream()
                    .mapToInt(TestRunResults::failingTestTotal)
                    .sum()).isEqualTo(0);
            return null;
        }
        public Void visitThenSpecifiedTestExecutorWasUsed(PdslJavaTestRunnerParser.ThenSpecifiedTestExecutorWasUsedContext ctx) {

            return null;
        }
        public Void visitThenSpecifiedResourceProviderWasUsed(PdslJavaTestRunnerParser.ThenSpecifiedResourceProviderWasUsedContext ctx){return null; }
        public Void visitThenTestIsSkipped(PdslJavaTestRunnerParser.ThenTestIsSkippedContext ctx){
            return null; }
        public Void visitThenPdslFrameworkThrowsAnException(PdslJavaTestRunnerParser.ThenPdslFrameworkThrowsAnExceptionContext ctx){return null; }
        public Void visitThenExceptionCommunicatesResourceCouldNotBeInterpreted(PdslJavaTestRunnerParser.ThenExceptionCommunicatesResourceCouldNotBeInterpretedContext ctx){return null; }
        public Void visitThenExceptionStatesBothRecognizerParserAndLexerNeeded(PdslJavaTestRunnerParser.ThenExceptionStatesBothRecognizerParserAndLexerNeededContext ctx){return null; }
        public Void visitThenExceptionStatesMissingRequiredSyntaxCheckRule(PdslJavaTestRunnerParser.ThenExceptionStatesMissingRequiredSyntaxCheckRuleContext ctx){ return null;}
        public Void visitPolymorphicDslAllRules(PdslJavaTestRunnerParser.PolymorphicDslAllRulesContext ctx){
            return visitChildren(ctx);
        }

        public Void visitTerminal(TerminalNode terminalNode) {return null;}
        public Void visitErrorNode(ErrorNode e) { throw new IllegalStateException(String.format("An error was encountered in the grammar!%n\t%s",  e.getText())); }
    }
}
