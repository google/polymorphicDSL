package com.pdsl.component;

import com.pdsl.executors.DefaultPolymorphicDslTestExecutor;
import com.pdsl.executors.ExecutorObserver;
import com.pdsl.executors.InterpreterObj;
import com.pdsl.gherkin.executors.GherkinTestExecutor;
import com.pdsl.grammars.TestExecutorLexer;
import com.pdsl.grammars.TestExecutorMetaParser;
import com.pdsl.grammars.TestExecutorMetaParserListenerImpl;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.reports.TestRunResults;
import com.pdsl.runners.SharedTestSuiteVisitor;
import com.pdsl.specifications.FilteredPhrase;
import com.pdsl.testcases.*;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.*;
import org.jruby.ir.Interp;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.internal.stubbing.answers.AnswersValidator;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.logging.Filter;

import static com.google.common.truth.Truth.assertThat;
import static com.ibm.icu.text.PluralRules.Operand.n;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class FrameworkTestExecutor {

    private static final ParseTree parseTree = getParseTree();
    private static final ParseTree failingParseTree = getFailingParseTree();
    private static final ParseTreeListener listener = getListener();
    private static final ParseTreeVisitor<?> visitor = getVisitor();

    private static ParseTreeListener getListener() {
        var listener = mock(ParseTreeListener.class);
        ArgumentCaptor<TerminalNode> terminalNodeCaptor = ArgumentCaptor.forClass(TerminalNode.class);
        doAnswer(invocation -> {
            TerminalNode n = invocation.getArgumentAt(0, TerminalNode.class);
            if (n.getText().contains("Failing")) {
                throw new AssertionError("intended error for PDSL component tests");
            }
            return invocation;
        }).when(listener).visitTerminal(terminalNodeCaptor.capture());
        return listener;
    }

    private static ParseTreeVisitor<?> getVisitor() {
        var visitor = mock(ParseTreeVisitor.class);
        ArgumentCaptor<TerminalNode> terminalNodeCaptor = ArgumentCaptor.forClass(TerminalNode.class);
        doAnswer(invocation -> {
            TerminalNode n = invocation.getArgumentAt(0, TerminalNode.class);
            if (n.getText().contains("Failing")) {
                throw new AssertionError("intended error for PDSL component tests");
            }
            return invocation;
        }).when(visitor).visit(terminalNodeCaptor.capture());
        return visitor;
    }


    @Test
    public void testExecutorObserver_listener_lifecyclesWorkProperly() {
        // ARRANGE
        final ExecutorLifecycleTestObserver observer = new ExecutorLifecycleTestObserver();
        DefaultPolymorphicDslTestExecutor executor =
                DefaultPolymorphicDslTestExecutor.of(Collections.singletonList(observer));

        // ACT
        executor.runTests(List.of(
                getTestCase(7, StubbedTestCaseStrategy.SOME_PHRASES_FILTERED),
                getTestCase(5, StubbedTestCaseStrategy.ALL_PHRASES_PASSING),
                getTestCase(3, StubbedTestCaseStrategy.FIRST_PHRASE_FAILS)), listener);
        // ASSERT
        assertThat(observer.afterTestSuite).isEqualTo(1);
        assertThat(observer.beforeTestSuite).isEqualTo(1);
        assertThat(observer.beforeTestCaseTriggered).isEqualTo(3);
        assertThat(observer.afterTestCaseTriggered).isEqualTo(3);
        assertThat(observer.phraseFailure).isEqualTo(1);
        assertThat(observer.duplicateSkipped).isEqualTo(0);
        assertThat(observer.afterPhrase).isEqualTo(8);
        assertThat(observer.beforePhrase).isEqualTo(9);
        assertThat(observer.testCaseSuccess).isEqualTo(2);
    }

    @Test
    public void testExecutorObserverWithListenerMetadata_lifecyclesWorkProperly() {
        // ARRANGE
        final ExecutorLifecycleTestObserver observer = new ExecutorLifecycleTestObserver();
        DefaultPolymorphicDslTestExecutor executor =
                DefaultPolymorphicDslTestExecutor.of(Collections.singletonList(observer));

        // ACT
        executor.runTestsWithMetadata(List.of(
                getTestCase(7, StubbedTestCaseStrategy.SOME_PHRASES_FILTERED),
                getTestCase(5, StubbedTestCaseStrategy.ALL_PHRASES_PASSING),
                getTestCase(3, StubbedTestCaseStrategy.FIRST_PHRASE_FAILS)), listener, "Component");
        // ASSERT
        assertThat(observer.afterTestSuite).isEqualTo(1);
        assertThat(observer.beforeTestSuite).isEqualTo(1);
        assertThat(observer.beforeTestCaseTriggered).isEqualTo(3);
        assertThat(observer.afterTestCaseTriggered).isEqualTo(3);
        assertThat(observer.phraseFailure).isEqualTo(1);
        assertThat(observer.duplicateSkipped).isEqualTo(0);
        assertThat(observer.afterPhrase).isEqualTo(8);
        assertThat(observer.beforePhrase).isEqualTo(9);
        assertThat(observer.testCaseSuccess).isEqualTo(2);
    }

    @Test
    public void testExecutorObserver_visitor_lifecyclesWorkProperly() {
        // ARRANGE
        final ExecutorLifecycleTestObserver observer = new ExecutorLifecycleTestObserver();
        DefaultPolymorphicDslTestExecutor executor =
                DefaultPolymorphicDslTestExecutor.of(Collections.singletonList(observer));

        // ACT
        executor.runTests(List.of(
                getTestCase(7, StubbedTestCaseStrategy.SOME_PHRASES_FILTERED),
                getTestCase(5, StubbedTestCaseStrategy.ALL_PHRASES_PASSING),
                getTestCase(3, StubbedTestCaseStrategy.FIRST_PHRASE_FAILS)), visitor);
        // ASSERT
        assertThat(observer.afterTestSuite).isEqualTo(1);
        assertThat(observer.beforeTestSuite).isEqualTo(1);
        assertThat(observer.beforeTestCaseTriggered).isEqualTo(3);
        assertThat(observer.afterTestCaseTriggered).isEqualTo(3);
        assertThat(observer.phraseFailure).isEqualTo(1);
        assertThat(observer.duplicateSkipped).isEqualTo(0);
        assertThat(observer.afterPhrase).isEqualTo(8);
        assertThat(observer.beforePhrase).isEqualTo(9);
        assertThat(observer.testCaseSuccess).isEqualTo(2);
    }

    @Test
    public void testExecutorObserverVisitorMetadata_lifecyclesWorkProperly() {
        // ARRANGE
        final ExecutorLifecycleTestObserver observer = new ExecutorLifecycleTestObserver();
        DefaultPolymorphicDslTestExecutor executor =
                DefaultPolymorphicDslTestExecutor.of(Collections.singletonList(observer));

        // ACT
        executor.runTestsWithMetadata(List.of(
                getTestCase(7, StubbedTestCaseStrategy.SOME_PHRASES_FILTERED),
                getTestCase(5, StubbedTestCaseStrategy.ALL_PHRASES_PASSING),
                getTestCase(3, StubbedTestCaseStrategy.FIRST_PHRASE_FAILS)), visitor, "Component");
        // ASSERT
        assertThat(observer.afterTestSuite).isEqualTo(1);
        assertThat(observer.beforeTestSuite).isEqualTo(1);
        assertThat(observer.beforeTestCaseTriggered).isEqualTo(3);
        assertThat(observer.afterTestCaseTriggered).isEqualTo(3);
        assertThat(observer.phraseFailure).isEqualTo(1);
        assertThat(observer.duplicateSkipped).isEqualTo(0);
        assertThat(observer.afterPhrase).isEqualTo(8);
        assertThat(observer.beforePhrase).isEqualTo(9);
        assertThat(observer.testCaseSuccess).isEqualTo(2);
    }

    @Test
    public void executorSharedTestCases_lifecyclesWorkProperly() {

        // ARRANGE
        // 3 phrases visited
        var sharedTestCaseWithInterpreter1 = mock(SharedTestSuite.SharedTestCaseWithInterpreter.class);
        doReturn(getTestCase(5, StubbedTestCaseStrategy.SOME_PHRASES_FILTERED))
                .when(sharedTestCaseWithInterpreter1).getTestCase();
        doReturn(new InterpreterObj(listener))
                .when(sharedTestCaseWithInterpreter1).getInterpreterObj();

        // 5 phrases
        var sharedTestCaseWithInterpreter2 = mock(SharedTestSuite.SharedTestCaseWithInterpreter.class);
        doReturn(getTestCase(5, StubbedTestCaseStrategy.ALL_PHRASES_PASSING))
                .when(sharedTestCaseWithInterpreter2).getTestCase();
        doReturn(InterpreterObj.ofListener(() -> listener))
                .when(sharedTestCaseWithInterpreter2).getInterpreterObj();

        // 5 phrases
        var sharedTestCaseWithInterpreter3 = mock(SharedTestSuite.SharedTestCaseWithInterpreter.class);
        doReturn( getTestCase(5, StubbedTestCaseStrategy.ALL_PHRASES_PASSING))
                .when(sharedTestCaseWithInterpreter3).getTestCase();
        doReturn(new InterpreterObj(visitor)).when(sharedTestCaseWithInterpreter3).getInterpreterObj();

        // 3 phrases
        var sharedTestCaseWithInterpreter4 = mock(SharedTestSuite.SharedTestCaseWithInterpreter.class);
        doReturn( getTestCase(5, StubbedTestCaseStrategy.SOME_PHRASES_FILTERED))
                .when(sharedTestCaseWithInterpreter4).getTestCase();
        doReturn(InterpreterObj.ofVisitor(() -> visitor))
                .when(sharedTestCaseWithInterpreter4).getInterpreterObj();

        var sharedTestCase1 = new SharedTestCase(List.of(
                sharedTestCaseWithInterpreter1,
                sharedTestCaseWithInterpreter2,
                sharedTestCaseWithInterpreter3,
                sharedTestCaseWithInterpreter4));

        // 5 phrases visited)
        var stc1 = mock(SharedTestSuite.SharedTestCaseWithInterpreter.class);
        doReturn(getTestCase(5, StubbedTestCaseStrategy.LAST_PHRASE_FAILS))
                .when(stc1).getTestCase();
        doReturn(InterpreterObj.ofListener(() -> listener)).when(stc1).getInterpreterObj();

        // 3 phrases visited
        var stc2 = mock(SharedTestSuite.SharedTestCaseWithInterpreter.class);
        doReturn(getTestCase(5, StubbedTestCaseStrategy.SOME_PHRASES_FILTERED))
                .when(stc2).getTestCase();
        doReturn(InterpreterObj.ofVisitor(() -> visitor)).when(stc2).getInterpreterObj();

        var sharedTestCase2 = new SharedTestCase(List.of(stc1, stc2));

        final ExecutorLifecycleTestObserver observer = new ExecutorLifecycleTestObserver();
        DefaultPolymorphicDslTestExecutor executor =
                DefaultPolymorphicDslTestExecutor.of(Collections.singletonList(observer));

        // ACT
        executor.runTestsWithMetadata(List.of(
               sharedTestCase1,
                sharedTestCase2), "Component");
        // ASSERT
        assertThat(observer.afterTestSuite).isEqualTo(1);
        assertThat(observer.beforeTestSuite).isEqualTo(1);
        assertThat(observer.beforeTestCaseTriggered).isEqualTo(2);
        assertThat(observer.afterTestCaseTriggered).isEqualTo(2);
        assertThat(observer.phraseFailure).isEqualTo(1);
        assertThat(observer.duplicateSkipped).isEqualTo(0);
        assertThat(observer.beforePhrase).isEqualTo(22);
        assertThat(observer.afterPhrase).isEqualTo(21);
        assertThat(observer.testCaseSuccess).isEqualTo(1);
    }

    @Test
    public void testExecutor_meetsSpecifications() throws URISyntaxException {
        final URL testResources = getClass().getClassLoader()
                .getResource("framework_specifications/features/TestExecutor.feature");
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(testResources.toURI());
        PolymorphicDslPhraseFilter phraseFilter = new DefaultPolymorphicDslPhraseFilter(
                TestExecutorMetaParser.class,
                TestExecutorLexer.class,
                TestExecutorMetaParser.class,
                TestExecutorLexer.class,
                "polymorphicDslAllRules", "polymorphicDslAllRules"
        );
        GherkinTestExecutor gherkinTestExecutor = new GherkinTestExecutor(phraseFilter);
        // Act
        TestRunResults results = gherkinTestExecutor.processFilesAndRunTests(dslFiles, new TestExecutorMetaParserListenerImpl());
        assertThat(results.failingTestTotal()).isEqualTo(0);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(0);
        assertThat(results.totalPhrases()).isGreaterThan(0);
    }


    private static ParseTree getParseTree() {
        var tree = mock(TerminalNode.class);
        when(tree.getText()).thenReturn("Mocked sentence");
        return tree;
    }

    private static ParseTree getFailingParseTree() {
        var tree = mock(TerminalNode.class);
        when(tree.getText()).thenReturn("Failing sentence");
        return tree;
    }

    private enum StubbedTestCaseStrategy {
        ALL_PHRASES_PASSING,
        FIRST_PHRASE_FAILS,
        LAST_PHRASE_FAILS,
        SOME_PHRASES_FILTERED
    }
    private static final FilteredPhrase passingPhrase = new FilteredPhrase() {
        @Override
        public String getPhrase() {
            return "passing phrase";
        }

        @Override
        public Optional<ParseTree> getParseTree() {
            return Optional.of(parseTree);
        }
    };

    private static FilteredPhrase failingPhrase = new FilteredPhrase() {
        @Override
        public String getPhrase() {
            return "failing phrase";
        }

        @Override
        public Optional<ParseTree> getParseTree() {
            return Optional.of(failingParseTree);
        }
    };

    private static FilteredPhrase emptyPhrase = new FilteredPhrase() {
        @Override
        public String getPhrase() {
            return "empty phrase";
        }

        @Override
        public Optional<ParseTree> getParseTree() {
            return Optional.empty();
        }
    };

    private static TestCase getTestCase(int totalPhrases, StubbedTestCaseStrategy strategy) {

        List<FilteredPhrase> phrases = new ArrayList<>(totalPhrases);
        for (int i=1; i <= totalPhrases; i++) {
            switch (strategy) {
                case ALL_PHRASES_PASSING -> {
                    phrases.add(passingPhrase);
                }
                case FIRST_PHRASE_FAILS -> {
                    if (i == 0) {
                        phrases.add(failingPhrase);
                    } else {
                        phrases.add(passingPhrase);
                    }
                }
                case LAST_PHRASE_FAILS -> {
                    if (i == totalPhrases -1) {
                        phrases.add(failingPhrase);
                    } else {
                        phrases.add(passingPhrase);
                    }
                }
                case SOME_PHRASES_FILTERED -> {
                    if ((i & 1) == 1) {
                        phrases.add(passingPhrase);
                    } else {
                        phrases.add(emptyPhrase);
                    }
                }
            }

        }
        try {
            return new DefaultPdslTestCase("stubbed test case", List.of(new TestBodyFragment(phrases)), new URI("./stubbed.feature"));
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }

    private static class ExecutorLifecycleTestObserver implements ExecutorObserver {

        private int beforeTestCaseTriggered = 0;
        private int afterTestCaseTriggered = 0;
        private int testCaseSuccess = 0;
        private int duplicateSkipped = 0;
        private int beforePhrase = 0;
        private int afterPhrase = 0;
        private int phraseFailure = 0;
        private int beforeTestSuite = 0;
        private int afterTestSuite = 0;

        public void reset() {
            beforeTestCaseTriggered = 0;
            afterTestCaseTriggered = 0;
            testCaseSuccess = 0;
            duplicateSkipped = 0;
            beforePhrase = 0;
            afterPhrase = 0;
            phraseFailure = 0;
            beforeTestSuite = 0;
            afterTestSuite = 0;
        }


        @Override
        public void onBeforePhrase(ParseTreeVisitor<?> visitor, TestSection testSection) { beforePhrase++; }

        @Override
        public void onAfterPhrase(ParseTreeListener listener, ParseTreeWalker walker, TestSection testSection) { afterPhrase++; }

        @Override
        public void onAfterPhrase(ParseTreeVisitor<?> visitor, TestSection testSection) { afterPhrase++; }

        @Override
        public void onPhraseFailure(ParseTreeListener listener, TestSection testSection, TestCase testCase, Throwable exception) {
            phraseFailure++;
        }

        @Override
        public void onPhraseFailure(ParseTreeVisitor<?> visitor, TestSection testSection, TestCase testCase, Throwable exception) {
            phraseFailure++;
        }

        @Override
        public void onBeforeTestSuite(Collection<? extends TestCase> testCases, ParseTreeVisitor<?> visitor, String context) {
            beforeTestSuite++;
        }

        @Override
        public void onBeforeTestSuite(Collection<? extends TestCase> testCases, ParseTreeListener listener, String context) {
            beforeTestSuite++;
        }

        @Override
        public void onBeforeTestSuite(Collection<? extends SharedTestCase> testCases, String context) {
            beforeTestSuite++;
        }

        @Override
        public void onAfterTestSuite(Collection<? extends TestCase> testCases, ParseTreeVisitor<?> visitor, MetadataTestRunResults results, String context) {
            afterTestSuite++;
        }

        @Override
        public void onAfterTestSuite(Collection<? extends TestCase> testCases, ParseTreeListener listener, MetadataTestRunResults results, String context) {
            afterTestSuite++;
        }

        @Override
        public void onAfterTestSuite(Collection<? extends SharedTestCase> testCases, MetadataTestRunResults results, String context) {
            afterTestSuite++;
        }

        @Override
        public void onBeforeTestCase(TestCase testCase) {beforeTestCaseTriggered++;}

        @Override
        public void onAfterTestCase(TestCase testCase) { afterTestCaseTriggered++; }

        @Override
        public void onTestCaseSuccess(TestCase testCase) { testCaseSuccess++; }

        @Override
        public void onDuplicateSkipped(TestCase testCase) { duplicateSkipped++; }

        @Override
        public void onBeforePhrase(ParseTreeListener listener, ParseTreeWalker walker, TestSection phrase) { beforePhrase++; }

    }
}
