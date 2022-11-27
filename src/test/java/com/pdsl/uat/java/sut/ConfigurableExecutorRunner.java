package com.pdsl.uat.java.sut;

import com.pdsl.executors.TraceableTestRunExecutor;
import com.pdsl.grammars.AllGrammarsFailListener;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.reports.TestResult;
import com.pdsl.reports.TestRunResults;
import com.pdsl.runners.PdslConfiguration;
import com.pdsl.runners.PdslTest;
import com.pdsl.runners.junit.PdslJUnit4ConfigurableRunner;
import com.pdsl.testcases.TestCase;
import com.pdsl.uat.PdslConfigurableExecutorTest;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.junit.runner.RunWith;

import javax.inject.Provider;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import com.pdsl.grammars.AllGrammarsParser;
import com.pdsl.grammars.AllGrammarsLexer;
import com.pdsl.grammars.AllGrammarsFailListener;
@RunWith(PdslJUnit4ConfigurableRunner.class)
@PdslConfiguration(
        resourceRoot = "src/test/resources/testdata/good",
        specificationFactoryProvider = PdslConfigurableExecutorTest.SpecificationFactoryProvider.class,
        testCaseFactoryProvider = PdslConfigurableExecutorTest.TestCaseFactoryProvider.class,
        testRunExecutor = ConfigurableExecutorRunner.CustomExecutorProvider.class,
        recognizerRule = "polymorphicDslAllRules"
)
public class ConfigurableExecutorRunner {

    @PdslTest(
            parser = AllGrammarsParser.class,
            lexer = AllGrammarsLexer.class,
            listener = ConfigurableExecutorRunner.ListenerProvider.class // If the custom executor is injected, this listener won't get called and throw an exception
    )
    public void customListener_preventsFailure(){}

    public static class ListenerProvider implements Provider<ParseTreeListener> {
        @Override
        public ParseTreeListener get() {
            return new AllGrammarsFailListener();

        }
    }
    public static class CustomExecutorProvider implements Provider<TraceableTestRunExecutor> {
        @Override
        public TraceableTestRunExecutor get() {
            return new CustomExecutor();
        }
    }

    private static class CustomExecutor implements TraceableTestRunExecutor {

        @Override
        public TestRunResults runTests(Collection<TestCase> testCases, ParseTreeListener subgrammarListener) {
            return new TestRunResults() {
                @Override
                public int passingTestTotal() {
                    return 1;
                }

                @Override
                public int failingTestTotal() {
                    return 0;
                }

                @Override
                public int passingPhraseTotal() {
                    return 0;
                }

                @Override
                public int totalPhrases() {
                    return 1;
                }

                @Override
                public int totalFilteredDuplicateTests() {
                    return 1;
                }
            };
        }

        @Override
        public TestRunResults runTests(Collection<TestCase> testCases, ParseTreeVisitor subgrammarVisitor) {
            throw new UnsupportedOperationException("Not implemented for this test case!");
        }

        @Override
        public MetadataTestRunResults runTestsWithMetadata(Collection<TestCase> testCases, ParseTreeListener grammarListener, String context) {
            return new MetadataTestRunResults() {
                @Override
                public Collection<TestResult> getTestResults() {
                    return null;
                }

                @Override
                public Optional<List<TestResult>> duplicateTestSpecifications() {
                    return Optional.empty();
                }

                @Override
                public String getContext() {
                    return null;
                }

                @Override
                public int passingTestTotal() {
                    return 0;
                }

                @Override
                public int failingTestTotal() {
                    return 0;
                }

                @Override
                public int passingPhraseTotal() {
                    return 0;
                }

                @Override
                public int totalPhrases() {
                    return 0;
                }

                @Override
                public int totalFilteredDuplicateTests() {
                    return 0;
                }
            };
        }

        @Override
        public MetadataTestRunResults runTestsWithMetadata(Collection<TestCase> testCases, ParseTreeVisitor<?> visitor, String context) {
            throw new UnsupportedOperationException("Not implemented for this test case!");
        }
    }
}
