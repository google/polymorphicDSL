package com.pdsl.uat.java;

import com.pdsl.executors.DefaultPolymorphicDslTestExecutor;
import com.pdsl.executors.TraceableTestRunExecutor;
import com.pdsl.grammars.MinimalImpl;
import com.pdsl.grammars.PdslHelper;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.reports.TestRunResults;
import com.pdsl.runners.PdslGherkinApplication;
import com.pdsl.runners.PdslTest;
import com.pdsl.runners.RecognizedBy;
import com.pdsl.runners.junit.PdslGherkinJUnit4Runner;
import com.pdsl.specifications.FileSystemTestResourceFinder;
import com.pdsl.specifications.GlobPathMatcher;
import com.pdsl.specifications.TestResourceFinder;
import com.pdsl.specifications.TestResourceFinderGenerator;
import com.pdsl.testcases.TestCase;
import com.pdsl.uat.PdslConfigurableExecutorTest;
import com.pdsl.uat.java.sut.*;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.jruby.javasupport.Java;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import com.pdsl.grammars.JavaTestRunnerParserBaseListener;
import com.pdsl.grammars.JavaTestRunnerParserListener;
import com.pdsl.grammars.JavaTestRunnerParser;
import com.pdsl.grammars.JavaTestRunnerLexer;
import com.pdsl.grammars.JavaTestRunnerParser;
import javax.inject.Provider;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;

@RunWith(PdslGherkinJUnit4Runner.class)
@PdslGherkinApplication(
        resourceRoot = "src/test/resources/framework_specifications/features/java",
        recognizerRule = "polymorphicDslAllRules"
)
public class JavaTestRunner {

    @PdslTest(
            parser = JavaTestRunnerParser.class,
            lexer = JavaTestRunnerLexer.class,
            includesResources = "JavaTestRunner.feature",
            listener = JavaTestRunner.JavaTestRunnerProvider.class
    )
    /*
    @RecognizedBy(
            dslRecognizerParser = JavaTestRunnerParser.class,
            dslRecognizerLexer = JavaTestRunnerLexer.class,
            recognizerRule = "polymorphicDslAllRules"
    )
   
     */
    public void javaTestRunnerFeatures_fullySupported() {
    }

    public static class JavaTestRunnerProvider implements Provider<ParseTreeListener> {

        @Override
        public ParseTreeListener get() {
            return new JavaTestRunnerParserListenerImpl();
        }
    }

    private static class JavaTestRunnerParserListenerImpl extends AbstractJavaTestRunnerParserListenerImpl {

        private int criteria = 0;
        private Optional<Result> result = Optional.empty();

        private String applicationName = "";
        private String context = "";

        private static final int ALL_TESTS_PASS = 1;
        private static final int CONFIG_DOES_NOT_SPECIFY_DEFAULT_RULE = 1 << 1;
        private static final int CUSTOM_EXECUTOR = 1 << 2;
        private static final int TAG_FILTERING = 1 << 3;
        private static final int HAS_TAGS = 1 << 4;
        private static final int RESOURCE_MATCHES_TAG = 1 << 5;
        private static final int RESOURCE_DOES_NOT_MATCH_TAG = 1 << 6;
        private static final int PDSL_RECOGNIZED_BY = 1 << 7;
        private static final int PHRASES_NOT_IN_PDSL_RECOGNIZER = 1 << 8;
        private static final int PHRASES_NOT_IN_CONFIG_RECOGNIZER = 1 << 9;
        private static final int SPECIFIES_RESOURCE_ROOT = 1 << 10;
        private static final int CUSTOM_RESOURCE_FINDER = 1 << 11;
        private static final int CONFIGURATION_RECOGNIZER_PARSER =  1 << 12;
        private static final int CONFIGURATION_RECOGNIZER_LEXER=  1 << 13;
        private static final int CONFIGURATION_RECOGNIZER_RULE =  1 << 14;
        private static final int APPLICATION_NAME = 1 << 15;
        private static final int REPORT_CONTEXT = 1 << 16;
        private static final int CUSTOM_REPORT_GENERATOR = 1 << 17;
        private static final int PDSL_SUITE = 1 << 18;
        private static final int NO_CONFIGURATION_RECOGNIZER = 1 << 19;
        private static final int NO_PDSL_RECOGNIZER = 1 << 20;
        private static final Map<Integer, Class<?>> bitmask2TestClass = new HashMap<>(){{
            put(JavaTestRunnerParserListenerImpl.ALL_TESTS_PASS, PdslConfigurableExecutorTest.class);

            put(JavaTestRunnerParserListenerImpl.TAG_FILTERING
                            | JavaTestRunnerParserListenerImpl.RESOURCE_MATCHES_TAG
                            | JavaTestRunnerParserListenerImpl.HAS_TAGS, TaggedScenarioRunner.class);

            put(JavaTestRunnerParserListenerImpl.ALL_TESTS_PASS
                    | JavaTestRunnerParserListenerImpl.SPECIFIES_RESOURCE_ROOT, PdslConfigurableExecutorTest.class);

            put(JavaTestRunnerParserListenerImpl.ALL_TESTS_PASS
                    | JavaTestRunnerParserListenerImpl.CUSTOM_EXECUTOR, ConfigurableExecutorRunner.class);

            put(JavaTestRunnerParserListenerImpl.ALL_TESTS_PASS
                    | JavaTestRunnerParserListenerImpl.CUSTOM_RESOURCE_FINDER, CustomResourceFinderRunner.class);

            put(JavaTestRunnerParserListenerImpl.ALL_TESTS_PASS
                    | JavaTestRunnerParserListenerImpl.CONFIGURATION_RECOGNIZER_PARSER
                    | JavaTestRunnerParserListenerImpl.CONFIGURATION_RECOGNIZER_LEXER, ConfigurationRecognizerRunner.class);

            put(JavaTestRunnerParserListenerImpl.ALL_TESTS_PASS
                    | JavaTestRunnerParserListenerImpl.CONFIGURATION_RECOGNIZER_PARSER
                    | JavaTestRunnerParserListenerImpl.CONFIGURATION_RECOGNIZER_LEXER
                    | JavaTestRunnerParserListenerImpl.CONFIGURATION_RECOGNIZER_RULE, ConfigurationRecognizerRunner.class);

            put(JavaTestRunnerParserListenerImpl.CONFIGURATION_RECOGNIZER_PARSER
                    | JavaTestRunnerParserListenerImpl.CONFIGURATION_RECOGNIZER_LEXER
                    | JavaTestRunnerParserListenerImpl.PHRASES_NOT_IN_CONFIG_RECOGNIZER, ConfigMissingPhrases.class);

            put(JavaTestRunnerParserListenerImpl.ALL_TESTS_PASS
                    | JavaTestRunnerParserListenerImpl.CONFIGURATION_RECOGNIZER_PARSER
                    | JavaTestRunnerParserListenerImpl.CONFIGURATION_RECOGNIZER_LEXER
                    | JavaTestRunnerParserListenerImpl.CONFIGURATION_RECOGNIZER_RULE
                    | JavaTestRunnerParserListenerImpl.PDSL_RECOGNIZED_BY, PdslRecognizedByPhrases.class);

            put(JavaTestRunnerParserListenerImpl.PHRASES_NOT_IN_CONFIG_RECOGNIZER
                    | JavaTestRunnerParserListenerImpl.CONFIGURATION_RECOGNIZER_PARSER
                    | JavaTestRunnerParserListenerImpl.CONFIGURATION_RECOGNIZER_LEXER
                    | JavaTestRunnerParserListenerImpl.CONFIGURATION_RECOGNIZER_RULE
                    | JavaTestRunnerParserListenerImpl.PDSL_RECOGNIZED_BY, UnrecognizedPdslPhrases.class);

            put(JavaTestRunnerParserListenerImpl.CONFIGURATION_RECOGNIZER_PARSER
                    | JavaTestRunnerParserListenerImpl.CONFIGURATION_RECOGNIZER_LEXER
                    | JavaTestRunnerParserListenerImpl.CONFIGURATION_RECOGNIZER_RULE
                    | JavaTestRunnerParserListenerImpl.PDSL_RECOGNIZED_BY, PdslRecognizedByPhrases.class);
            put(JavaTestRunnerParserListenerImpl.CONFIGURATION_RECOGNIZER_PARSER
                    | JavaTestRunnerParserListenerImpl.CONFIGURATION_RECOGNIZER_LEXER
                    | JavaTestRunnerParserListenerImpl.CONFIGURATION_RECOGNIZER_RULE
                    | JavaTestRunnerParserListenerImpl.PDSL_RECOGNIZED_BY
                    | JavaTestRunnerParserListenerImpl.PHRASES_NOT_IN_PDSL_RECOGNIZER, UnrecognizedPdslPhrases.class);
            put(JavaTestRunnerParserListenerImpl.ALL_TESTS_PASS
                    | JavaTestRunnerParserListenerImpl.NO_CONFIGURATION_RECOGNIZER
                    | JavaTestRunnerParserListenerImpl.NO_PDSL_RECOGNIZER, NoRecognizerRunner.class);
            put(JavaTestRunnerParserListenerImpl.ALL_TESTS_PASS
                    | JavaTestRunnerParserListenerImpl.NO_CONFIGURATION_RECOGNIZER
                    | JavaTestRunnerParserListenerImpl.NO_PDSL_RECOGNIZER, NoRecognizerRunner.class);
            put(JavaTestRunnerParserListenerImpl.ALL_TESTS_PASS
                    | JavaTestRunnerParserListenerImpl.CONFIGURATION_RECOGNIZER_PARSER
                    | JavaTestRunnerParserListenerImpl.CONFIGURATION_RECOGNIZER_LEXER
                    | JavaTestRunnerParserListenerImpl.CONFIGURATION_RECOGNIZER_RULE
                    | JavaTestRunnerParserListenerImpl.NO_PDSL_RECOGNIZER, ConfigurationRecognizerRunner.class);
            put(JavaTestRunnerParserListenerImpl.PHRASES_NOT_IN_CONFIG_RECOGNIZER
                    | JavaTestRunnerParserListenerImpl.CONFIGURATION_RECOGNIZER_PARSER
                    | JavaTestRunnerParserListenerImpl.CONFIGURATION_RECOGNIZER_LEXER
                    | JavaTestRunnerParserListenerImpl.CONFIGURATION_RECOGNIZER_RULE
                    | JavaTestRunnerParserListenerImpl.NO_PDSL_RECOGNIZER, ConfigMissingPhrases.class);
        }};

        private static String getCriteria(int criteria) {
            StringBuilder builder = new StringBuilder();
            if ((JavaTestRunnerParserListenerImpl.ALL_TESTS_PASS & criteria) > 0) {
                builder.append("All tests pass,");
            }
            if ((CONFIG_DOES_NOT_SPECIFY_DEFAULT_RULE & criteria) > 0) {
                builder.append("Config does not specify default rule,");
            }
            if ((CUSTOM_EXECUTOR & criteria) > 0) {
                builder.append("custom executor,");
            }
            if ((TAG_FILTERING & criteria) > 0) {
                builder.append("tag filtering,");
            }
            if ((HAS_TAGS & criteria) > 0) {
                builder.append("has tags,");
            }
            if ((RESOURCE_MATCHES_TAG & criteria) > 0) {
                builder.append("resource matches tag,");
            }
            if ((RESOURCE_DOES_NOT_MATCH_TAG & criteria) > 0) {
                builder.append("resource does not match tag,");
            }
            if ((PDSL_RECOGNIZED_BY & criteria) > 0) {
                builder.append("pdsl recognized by,");
            }
            if ((PHRASES_NOT_IN_PDSL_RECOGNIZER & criteria) > 0) {
                builder.append("phrases non recognized by,");
            }
            if ((PHRASES_NOT_IN_CONFIG_RECOGNIZER & criteria) > 0) {
                builder.append("phrases not in config recognizer,");
            }
            if ((SPECIFIES_RESOURCE_ROOT & criteria) > 0) {
                builder.append("specifies resource root,");
            }
            if ((CUSTOM_RESOURCE_FINDER & criteria) > 0) {
                builder.append("custom resource finder,");
            }
            if ((CONFIGURATION_RECOGNIZER_LEXER & criteria) > 0) {
                builder.append("configuration recognizer lexer,");
            }
            if ((CONFIGURATION_RECOGNIZER_PARSER & criteria) > 0) {
                builder.append("configuration recognizer parser,");
            }
            if ((CONFIGURATION_RECOGNIZER_RULE & criteria) > 0) {
                builder.append("configuration recognizer rule,");
            }
            if ((APPLICATION_NAME & criteria) > 0) {
                builder.append("application name,");
            }
            if ((REPORT_CONTEXT & criteria) > 0) {
                builder.append("report context,");
            }
            if ((CUSTOM_REPORT_GENERATOR & criteria) > 0) {
                builder.append("configuration report generator,");
            }
            if ((NO_CONFIGURATION_RECOGNIZER & criteria) > 0) {
                builder.append("no configuration recognizer,");
            }
            if ((NO_PDSL_RECOGNIZER & criteria) > 0) {
                builder.append("no pdsl recognizer,");
            }

            return builder.toString().replaceAll(",", "\n\t");
        }
        public void enterGivenPdslConfigurationDoesNotSpecifyDefaultRule(JavaTestRunnerParser.GivenPdslConfigurationDoesNotSpecifyDefaultRuleContext ctx) {
            criteria |= CONFIG_DOES_NOT_SPECIFY_DEFAULT_RULE;
        }

        public void enterWhenTestRunnerExecutes(JavaTestRunnerParser.WhenTestRunnerExecutesContext ctx) {
            Class<?> representativeTestClass = bitmask2TestClass.get(criteria);
            if (representativeTestClass == null) {
                throw new IllegalArgumentException(String.format("Do not know which class to use for criteria%n\tbitmask: %s%n\t%s", Integer.toBinaryString(criteria), getCriteria(criteria)));
            }
            JUnitCore runner = new JUnitCore();
            result = Optional.of(runner.run(representativeTestClass));

        }
        public void enterThenAllTestsPass(JavaTestRunnerParser.ThenAllTestsPassContext ctx) {
            if (result.isEmpty()) {
                throw new IllegalStateException("Did not get test result. Did the JUnitCore runner execute?");
            }
            assertThat(result.get().wasSuccessful()).isTrue();
            assertThat(result.get().getFailures()).isEmpty();
        }
        public void enterThenSpecifiedTestExecutorWasUsed(JavaTestRunnerParser.ThenSpecifiedTestExecutorWasUsedContext ctx) {
            // ConfigurableExecutorRunner.class fails if a custom executor isn't used
            assertThat(result.get().wasSuccessful()).isTrue();
        }
        public void enterThenSpecifiedResourceProviderWasUsed(JavaTestRunnerParser.ThenSpecifiedResourceProviderWasUsedContext ctx) {
            // CustomResourceFinderRunner.class will not pass unless custom finder used
            assertThat(result.get().wasSuccessful()).isTrue();
        }
        public void enterThenTestIsSkipped(JavaTestRunnerParser.ThenTestIsSkippedContext ctx) {
            assertThat(result.get().wasSuccessful()).isTrue();
            assertThat(result.get().getRunCount()).isEqualTo(2); // If tags don't work then all tests will run instead of just the one (plus the runner)

        }

        public void enterThenPdslFrameworkThrowsAnException(JavaTestRunnerParser.ThenPdslFrameworkThrowsAnExceptionContext ctx) {
            if (result.isEmpty()) {
                throw new IllegalStateException("Did not get test result and cannot check exception. Did the JUnitCore runner execute?");
            }
            assertThat(result.get().wasSuccessful()).isFalse();
        }

        public void enterThenExceptionCommunicatesResourceCouldNotBeInterpreted(JavaTestRunnerParser.ThenExceptionCommunicatesResourceCouldNotBeInterpretedContext ctx) {
            if (result.isEmpty()) {
                throw new IllegalStateException("Did not get test result. Did the JUnitCore runner execute?");
            }
            assertThat(result.get().getFailures().stream().anyMatch(e -> e.getException().getMessage().contains("Syntax check on grammar failed"))).isTrue();
        }
        public void enterThenExceptionStatesBothRecognizerParserAndLexerNeeded(JavaTestRunnerParser.ThenExceptionStatesBothRecognizerParserAndLexerNeededContext ctx) {
            assertThat(result.get().getFailures().stream().anyMatch(e -> e.getException().getMessage().contains("UPDATE MESSAGE"))).isTrue();
        }
        public void enterThenExceptionStatesMissingRequiredSyntaxCheckRule(JavaTestRunnerParser.ThenExceptionStatesMissingRequiredSyntaxCheckRuleContext ctx) {
            assertThat(result.get().getFailures().stream().anyMatch(e -> e.getException().getMessage().contains("UPDATE MESSAGE"))).isTrue();
        }
        public void enterGivenPdslTest(JavaTestRunnerParser.GivenPdslTestContext ctx) {
            criteria = 0;
            result = Optional.empty();
        }

        public void enterGivenAllPdslTestsAreValid(JavaTestRunnerParser.GivenAllPdslTestsAreValidContext ctx) {
            criteria |= ALL_TESTS_PASS;
        }

        public void enterGivenPdslConfigurationSpecifiesProperty(JavaTestRunnerParser.GivenPdslConfigurationSpecifiesPropertyContext ctx) {
            String property = PdslHelper.extractStringInQuotes(ctx.textInDoubleQuotes().getText());
            if (ctx.getText().toLowerCase().contains(" not ")) {
                switch (property) {
                    case "dslRecognizerParser":
                        criteria &= ~CONFIGURATION_RECOGNIZER_PARSER;
                        criteria |= NO_CONFIGURATION_RECOGNIZER;
                        break;
                    case "dslRecognizerLexer":
                        criteria &= ~CONFIGURATION_RECOGNIZER_LEXER;
                        criteria |= NO_CONFIGURATION_RECOGNIZER;
                        break;
                }
            } else {
                switch (property) {
                    case "resourceRoot":
                        criteria |= SPECIFIES_RESOURCE_ROOT;
                        break;
                    case "testRunExecutor":
                        criteria |= CUSTOM_EXECUTOR;
                        break;
                    case "resourceFinder":
                        criteria |= CUSTOM_RESOURCE_FINDER;
                        break;
                    case "dslRecognizerParser":
                        criteria |= CONFIGURATION_RECOGNIZER_PARSER;
                        break;
                    case "dslRecognizerLexer":
                        criteria |= CONFIGURATION_RECOGNIZER_LEXER;
                        break;
                    case "recognizerRule":
                        criteria |= CONFIGURATION_RECOGNIZER_RULE;
                        break;
                    case "MyApp":
                        criteria |= APPLICATION_NAME;
                        break;
                    case "Unit":
                    case "API":
                        criteria |= REPORT_CONTEXT;
                        break;
                    case "reportGenerator":
                        criteria |= CUSTOM_REPORT_GENERATOR;
                        break;
                    default:
                        throw new IllegalArgumentException("Do not have any implementation for '" + property + "'");
                }
            }
        }

        public void enterGivenThePdslTestUsesFactoriesThatFilterBasedOnTags(JavaTestRunnerParser.GivenThePdslTestUsesFactoriesThatFilterBasedOnTagsContext ctx) {
            criteria |= TAG_FILTERING;
        }
        public void enterGivenThePdslTestHasATagSpecified(JavaTestRunnerParser.GivenThePdslTestHasATagSpecifiedContext ctx) {
            criteria |= HAS_TAGS;
        }
        public void enterGivenTestResourceMarkWithSpecifiedTag(JavaTestRunnerParser.GivenTestResourceMarkWithSpecifiedTagContext ctx) {
            criteria |= RESOURCE_MATCHES_TAG;
        }
        public void enterGivenPdslRecognizedBy(JavaTestRunnerParser.GivenPdslRecognizedByContext ctx) {
            if (ctx.getText().toLowerCase().contains("does not")) {
                criteria |= NO_PDSL_RECOGNIZER;
            } else {
                criteria |= PDSL_RECOGNIZED_BY;
            }
        }
        public void enterGivenPslTestHasPhrasesNotInRecognizer(JavaTestRunnerParser.GivenPslTestHasPhrasesNotInRecognizerContext ctx) {
            criteria |= PHRASES_NOT_IN_PDSL_RECOGNIZER;
        }
        public void enterGivenPdslTestHasResourcesThatMightBeRecognizedByConfiguration(JavaTestRunnerParser.GivenPdslTestHasResourcesThatMightBeRecognizedByConfigurationContext ctx) {
            if (ctx.getText().toLowerCase().contains(" not ")) {
                criteria |= PHRASES_NOT_IN_CONFIG_RECOGNIZER;
            }
        }
        public void enterGivenPdslTestHasResources(JavaTestRunnerParser.GivenPdslTestHasResourcesContext ctx) {
            if (ctx.getText().toLowerCase().contains(" not ")) {
                criteria |= PHRASES_NOT_IN_PDSL_RECOGNIZER;
            }
        }


        public void enterGivenRecognizedBySpecifiesParameter(JavaTestRunnerParser.GivenRecognizedBySpecifiesParameterContext ctx) {

            String param = PdslHelper.extractStringInQuotes(ctx.textInDoubleQuotes().getText());
            switch (param) {
                case "dslRecognizerLexer":
                case "dslRecognizerParser":
                case "recognizerRule":
                    criteria |= JavaTestRunnerParserListenerImpl.PDSL_RECOGNIZED_BY;
                    break;
                default:
                    throw new IllegalArgumentException("Do not know how to process " + param);
            }
        }
    }

    private abstract static class AbstractJavaTestRunnerParserListenerImpl implements JavaTestRunnerParserListener {
        public void exitTextInDoubleQuotes(JavaTestRunnerParser.TextInDoubleQuotesContext ctx) {}
        public void enterTextInDoubleQuotes(JavaTestRunnerParser.TextInDoubleQuotesContext ctx) {}
        public void exitGivenPdslConfigurationSpecifiesProperty(JavaTestRunnerParser.GivenPdslConfigurationSpecifiesPropertyContext ctx) {}
        public void exitDocstring(JavaTestRunnerParser.DocstringContext ctx ) {}
        public void enterDocstring(JavaTestRunnerParser.DocstringContext ctx ) {}
        public void exitGivenAllPdslTestsAreValid(JavaTestRunnerParser.GivenAllPdslTestsAreValidContext ctx) {}
        public void exitGivenPdslTest(JavaTestRunnerParser.GivenPdslTestContext ctx) {}
        //public void exitGivenPdslConfigurationSpecifiesProperty(JavaTestRunnerParser.GivenPdslConfigurationSpecifiesPropertyContext ctx) {}
        public void exitGivenThePdslTestUsesFactoriesThatFilterBasedOnTags(JavaTestRunnerParser.GivenThePdslTestUsesFactoriesThatFilterBasedOnTagsContext ctx) {}
        public void exitGivenThePdslTestHasATagSpecified(JavaTestRunnerParser.GivenThePdslTestHasATagSpecifiedContext ctx) {}
        public void exitGivenTestResourceMarkWithSpecifiedTag(JavaTestRunnerParser.GivenTestResourceMarkWithSpecifiedTagContext ctx) {}
        public void exitGivenPdslRecognizedBy(JavaTestRunnerParser.GivenPdslRecognizedByContext ctx) {}
        public void exitGivenPslTestHasPhrasesNotInRecognizer(JavaTestRunnerParser.GivenPslTestHasPhrasesNotInRecognizerContext ctx) {}
        public void exitGivenPdslTestHasResourcesThatMightBeRecognizedByConfiguration(JavaTestRunnerParser.GivenPdslTestHasResourcesThatMightBeRecognizedByConfigurationContext ctx) {}
        public void exitGivenPdslTestHasResources(JavaTestRunnerParser.GivenPdslTestHasResourcesContext ctx) {}
        public void exitGivenPdslConfigurationDoesNotSpecifyDefaultRule(JavaTestRunnerParser.GivenPdslConfigurationDoesNotSpecifyDefaultRuleContext ctx) {}
        public void exitWhenTestRunnerExecutes(JavaTestRunnerParser.WhenTestRunnerExecutesContext ctx) {}
        public void exitThenAllTestsPass(JavaTestRunnerParser.ThenAllTestsPassContext ctx) {}
        public void exitThenSpecifiedTestExecutorWasUsed(JavaTestRunnerParser.ThenSpecifiedTestExecutorWasUsedContext ctx) {}
        public void exitThenSpecifiedResourceProviderWasUsed(JavaTestRunnerParser.ThenSpecifiedResourceProviderWasUsedContext ctx) {}
        public void exitThenTestIsSkipped(JavaTestRunnerParser.ThenTestIsSkippedContext ctx) {}

        public void exitThenPdslFrameworkThrowsAnException(JavaTestRunnerParser.ThenPdslFrameworkThrowsAnExceptionContext ctx) {}
        public void exitThenExceptionCommunicatesResourceCouldNotBeInterpreted(JavaTestRunnerParser.ThenExceptionCommunicatesResourceCouldNotBeInterpretedContext ctx) {}
        public void exitThenExceptionStatesBothRecognizerParserAndLexerNeeded(JavaTestRunnerParser.ThenExceptionStatesBothRecognizerParserAndLexerNeededContext ctx) {}
        public void exitThenExceptionStatesMissingRequiredSyntaxCheckRule(JavaTestRunnerParser.ThenExceptionStatesMissingRequiredSyntaxCheckRuleContext ctx) {}

        public void enterPolymorphicDslAllRules(JavaTestRunnerParser.PolymorphicDslAllRulesContext ctx) {}
        public void exitPolymorphicDslAllRules(JavaTestRunnerParser.PolymorphicDslAllRulesContext ctx) {}
        public void exitGivenRecognizedBySpecifiesParameter(JavaTestRunnerParser.GivenRecognizedBySpecifiesParameterContext ctx) {}
        /////////////////
        public void enterEveryRule(org.antlr.v4.runtime.ParserRuleContext ctx) {}
        public void exitEveryRule(org.antlr.v4.runtime.ParserRuleContext ctx) {}
        public void visitErrorNode(org.antlr.v4.runtime.tree.ErrorNode ctx) {}
        public void visitTerminal(org.antlr.v4.runtime.tree.TerminalNode node) {}
    }
}
