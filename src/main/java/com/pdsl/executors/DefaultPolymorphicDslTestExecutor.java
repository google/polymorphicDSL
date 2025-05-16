package com.pdsl.executors;

import com.pdsl.logging.AnsiTerminalColorHelper;
import com.pdsl.logging.PdslThreadSafeOutputStream;
import com.pdsl.reports.DefaultTestResult;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.reports.TestRunResults;
import com.pdsl.specifications.DefaultPhrase;
import com.pdsl.specifications.FilteredPhrase;
import com.pdsl.specifications.Phrase;
import com.pdsl.specifications.PolymorphicDslTransformationException;
import com.pdsl.testcases.SharedTestCase;
import com.pdsl.testcases.SharedTestSuite.SharedTestCaseWithInterpreter;
import com.pdsl.testcases.TestCase;
import com.pdsl.testcases.TestSection;

import java.util.stream.Collectors;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;

/**
 * An executor that runs PDSL tests create from a TestCaseFactory.
 * <p>
 * The default executor has colorized terminal output and prints the phrases as they execute and the
 * stack trace on failure.
 * <p>
 * All metadata associated with the tests will also be printed.
 */
public class DefaultPolymorphicDslTestExecutor implements TraceableTestRunExecutor, ActivePhraseObservable {

    private final ParseTreeWalker walker = new ParseTreeWalker();
    private static final Charset DEFAULT_CHARSET = Charset.defaultCharset();
    private final List<ExecutorObserver> activePhraseObservers = new ArrayList<>();

    public DefaultPolymorphicDslTestExecutor() {
        this.registerObserver(new ColorizedLoggerObserver());
    }

    public static DefaultPolymorphicDslTestExecutor of(List<ExecutorObserver> observers) {
        DefaultPolymorphicDslTestExecutor executor = new DefaultPolymorphicDslTestExecutor();
        executor.activePhraseObservers.clear(); // Remove default logger observer
        observers.forEach(executor::registerObserver);
        return executor;
    }

    @Override
    public PolymorphicDslTestRunResults runTests(Collection<TestCase> testCases,
                                                 ParseTreeListener listener) {
        // Walk the phrase registry to make sure all phrases are defined

        notifyBeforeTestSuite(testCases, listener, "");
        MetadataTestRunResults results = walk(testCases, new InterpreterObj(listener), "NONE");
        notifyAfterTestSuite(testCases, listener, results, "");
        return (PolymorphicDslTestRunResults) results;
    }

    @Override
    public TestRunResults runTests(Collection<TestCase> testCases,
                                   ParseTreeVisitor<?> subgrammarVisitor) {
        MetadataTestRunResults results = walk(testCases, new InterpreterObj(subgrammarVisitor), "NONE");
        notifyAfterTestSuite(testCases, subgrammarVisitor, results, "");
        return (PolymorphicDslTestRunResults) results;
    }


    @Override
    public void registerObserver(ExecutorObserver observer) {
        activePhraseObservers.add(observer);
    }

    @Override
    public void removeObserver(ExecutorObserver observer) {
        activePhraseObservers.remove(observer);
    }

    private void notifyDuplicateSkipped(TestCase testCase) {
        activePhraseObservers.forEach(o -> o.onDuplicateSkipped(testCase));
    }

    private void notifyTestCaseSuccess(TestCase testCase) {
        activePhraseObservers.forEach(o -> o.onTestCaseSuccess(testCase));
    }

    private void notifyBeforeListener(ParseTreeListener listener, ParseTreeWalker walker,
                                      TestSection testSection) {
        activePhraseObservers.forEach(o -> o.onBeforePhrase(listener, walker, testSection));
    }

    private void notifyBeforeVisitor(ParseTreeVisitor<?> visitor,
                                     TestSection testSection) {
        activePhraseObservers.forEach(o -> o.onBeforePhrase(visitor, testSection));
    }

    private void notifyAfterListener(ParseTreeListener listener, ParseTreeWalker walker,
                                     TestSection testSection) {
        activePhraseObservers.forEach(o -> o.onAfterPhrase(listener, walker, testSection));
    }

    private void notifyBeforeTestCase(TestCase testCase) {
        activePhraseObservers.forEach(o -> o.onBeforeTestCase(testCase));
    }

    private void notifyAfterTestCase(TestCase testCase) {
        activePhraseObservers.forEach(o -> o.onAfterTestCase(testCase));
    }

    private void notifyAfterVisitor(ParseTreeVisitor<?> visitor,
                                    TestSection testSection) {
        activePhraseObservers.forEach(o -> o.onAfterPhrase(visitor, testSection));
    }

    private void notifyOnListenerException(ParseTreeListener listener,
                                           TestSection testSection, TestCase testCase, Throwable exception) {
        activePhraseObservers.forEach(o -> o.onPhraseFailure(listener, testSection, testCase, exception));
    }

    private void notifyOnVisitorException(ParseTreeVisitor<?> visitor,
                                          TestSection testSection, TestCase testCase, Throwable exception) {
        activePhraseObservers.forEach(a -> a.onPhraseFailure(visitor, testSection, testCase, exception));
    }

    private void notifyBeforeTestSuite(Collection<TestCase> testCases, ParseTreeVisitor<?> visitor,
                                       String context) {
        activePhraseObservers.forEach(a -> a.onBeforeTestSuite(testCases, visitor, context));
    }

    private void notifyBeforeTestSuite(Collection<SharedTestCase> testCases,
                                       String context) {
        activePhraseObservers.forEach(a -> a.onBeforeTestSuite(testCases, context));
    }

    private void notifyAfterTestSuite(Collection<TestCase> testCases, ParseTreeVisitor<?> visitor, MetadataTestRunResults results,
                                      String context) {
        activePhraseObservers.forEach(a -> a.onAfterTestSuite(testCases, visitor, results, context));
    }

    private void notifyBeforeTestSuite(Collection<TestCase> testCases, ParseTreeListener listener,
                                       String context) {
        activePhraseObservers.forEach(a -> a.onBeforeTestSuite(testCases, listener, context));
    }

    private void notifyAfterTestSuite(Collection<TestCase> testCases, ParseTreeListener listener, MetadataTestRunResults results,
                                      String context) {
        activePhraseObservers.forEach(a -> a.onAfterTestSuite(testCases, listener, results, context));
    }

    private void notifyAfterTestSuite(Collection<SharedTestCase> testCases, MetadataTestRunResults results,
                                      String context) {
        activePhraseObservers.forEach(a -> a.onAfterTestSuite(testCases, results, context));
    }


    private MetadataTestRunResults walk(Collection<TestCase> testCases, InterpreterObj interpreterObj,
                                        String context) {
        PolymorphicDslTestRunResults results = new PolymorphicDslTestRunResults(
                new PdslThreadSafeOutputStream(), context);
        Set<List<String>> previouslyExecutedTests = new HashSet<>();

        String filterDuplicatesProperty = System.getProperty("pdsl.filterDuplicates");
        boolean filter = filterDuplicatesProperty != null && filterDuplicatesProperty.equalsIgnoreCase("true");
        for (TestCase testCase : testCases) {
            Optional<ParseTreeListener> listener = interpreterObj.getListenerSupplier().isPresent()
                    ? Optional.of(interpreterObj.getListenerSupplier().get().get())
                    : Optional.empty();
            Optional<ParseTreeVisitor<?>> visitor = interpreterObj.getVisitorSupplier().isPresent()
                    ? Optional.of(interpreterObj.getVisitorSupplier().get().get())
                    : Optional.empty();
            notifyBeforeTestCase(testCase);
            TestSection testSection = testCase.getContextFilteredTestSectionIterator().next();
            Iterator<TestSection> testBody = testCase.getContextFilteredTestSectionIterator();
            int phraseIndex = 0;
            try {

                if (filter && previouslyExecutedTests.contains(testCase.getContextFilteredPhraseBody())) {
                    notifyDuplicateSkipped(testCase);
                    results.addTestResult(DefaultTestResult.duplicateTest(testCase));
                    continue;
                }
                if (filter) {
                    previouslyExecutedTests.add(testCase.getContextFilteredPhraseBody());
                }
                while (testBody.hasNext()) {
                    testSection = testBody.next();
                    Phrase activePhrase = testSection.getPhrase();
                    if (listener.isPresent()) {
                        if (testSection.getMetaData().isPresent()) {
                            notifyBeforeListener(listener.get(), walker, testSection);
                        }
                        notifyBeforeListener(listener.get(), walker, testSection);
                        walker.walk(listener.get(), activePhrase.getParseTree());
                        notifyAfterListener(listener.get(), walker, testSection);
                    } else {
                        if (testSection.getMetaData().isPresent()) {
                            notifyBeforeVisitor(visitor.orElseThrow(), testSection);
                        }
                        notifyBeforeVisitor(visitor.orElseThrow(), testSection);
                        visitor.get().visit(activePhrase.getParseTree());
                        notifyAfterVisitor(visitor.get(), testSection);
                    }
                    phraseIndex++;
                }
                results.addTestResult(DefaultTestResult.passingTest(testCase));

            } catch (Throwable e) {
                if (listener.isPresent()) {
                    notifyOnListenerException(listener.get(), testSection, testCase, e);
                } else {
                    notifyOnVisitorException(visitor.orElseThrow(), testSection, testCase, e);
                }

                int phrasesSkippedDueToFailure = 0;
                while (testBody.hasNext()) {
                    testBody.next();
                    phrasesSkippedDueToFailure++;
                }
                results.addTestResult(DefaultTestResult.failedTest(testCase, testSection.getPhrase(), e, phraseIndex,
                        phrasesSkippedDueToFailure));
            }
            notifyAfterTestCase(testCase);
        }
        return results;
    }

    @Override
    public MetadataTestRunResults runTestsWithMetadata(Collection<TestCase> testCases,
                                                       ParseTreeListener subgrammarListener, String context) {
        notifyBeforeTestSuite(testCases, subgrammarListener, context);
        MetadataTestRunResults results = walk(testCases, new InterpreterObj(subgrammarListener),
                context);
        notifyAfterTestSuite(testCases, subgrammarListener, results, context);
        return results;
    }

    @Override
    public MetadataTestRunResults runTestsWithMetadata(Collection<TestCase> testCases,
                                                       ParseTreeVisitor<?> visitor, String context) {
        notifyBeforeTestSuite(testCases, visitor, context);
        MetadataTestRunResults results = walk(testCases, new InterpreterObj(visitor), context);
        notifyAfterTestSuite(testCases, visitor, results, context);
        return results;
    }

    @Override
    public MetadataTestRunResults runTestsWithMetadata(Collection<SharedTestCase> sharedTestCases,
                                                       String context) {
        notifyBeforeTestSuite(sharedTestCases, context);
        PolymorphicDslTestRunResults results = new PolymorphicDslTestRunResults(
                new PdslThreadSafeOutputStream(), context);


        for (SharedTestCase sharedTestCase : sharedTestCases) {
            List<TestCase> listOfTestCases = sharedTestCase.getSharedTestCaseWithInterpreters().stream()
                    .map(SharedTestCaseWithInterpreter::getTestCase).toList();
            int size = listOfTestCases.getFirst().getUnfilteredPhraseBody().size();
            TestCase testCase = listOfTestCases.stream().findFirst().orElseThrow();
            notifyBeforeTestCase(sharedTestCase);
            // Create each visitor/listener one time per test case
            Map<InterpreterObj, ParseTreeListener> suppliedListeneres = new HashMap<>();
            Map<InterpreterObj, ParseTreeVisitor<?>> suppliedVisitors = new HashMap<>();
            Optional<Phrase> phrase = Optional.empty();
            int phraseIndex = 0;
            Optional<InterpreterObj> interpreterObj = Optional.empty();
            // TODO: The default implementation associates metadata with the first phrase.
            // We should design for more general use cases, but we can't do this without
            // severely refactoring the API. Save this for v2
            TestSection testSection = sharedTestCase.getSharedTestCaseWithInterpreters().stream()
                    .map(SharedTestCaseWithInterpreter::getTestCase)
                    .map(TestCase::getContextFilteredTestSectionIterator)
                    .filter(Iterator::hasNext)
                    .map(Iterator::next)
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("No executable phrases were found!"));

            try {
                for (int j = 0;
                     j < sharedTestCase.getSharedTestCaseWithInterpreters().getFirst().getTestCase()
                             .getUnfilteredPhraseBody().size(); j++) {
                    for (SharedTestCaseWithInterpreter interpreter : sharedTestCase.getSharedTestCaseWithInterpreters()) {
                        FilteredPhrase filteredPhrase = interpreter.getTestCase().getFilteredPhrases()
                                .get(phraseIndex);

                        //TODO - Add implementation for the duplication checking
                        Optional<ParseTree> parseTree = filteredPhrase.getParseTree();
                        if (parseTree.isPresent()) {
                            phrase = Optional.of(new DefaultPhrase(parseTree.get(), phraseIndex));

                            interpreterObj = Optional.of(interpreter.getInterpreterObj());

                            if (interpreterObj.get().getListenerSupplier().isPresent()) {
                                ParseTreeListener listener = suppliedListeneres.computeIfAbsent(interpreterObj.get(),
                                        (i) -> i.getListenerSupplier().orElseThrow().get());
                                notifyBeforeListener(listener, walker,
                                        testSection);
                                walker.walk(listener, parseTree.get());
                                notifyAfterListener(listener, walker,
                                        testSection);
                            } else {
                                ParseTreeVisitor<?> visitor = suppliedVisitors.computeIfAbsent(interpreterObj.get(),
                                        (i) -> i.getVisitorSupplier().orElseThrow().get());
                                notifyBeforeVisitor(visitor,
                                        testSection);
                                visitor.visit(parseTree.get());
                                notifyAfterVisitor(visitor, testSection);
                            }

                        }
                    }
                    phraseIndex++;
                }

                notifyTestCaseSuccess(testCase);


            } catch (Throwable e) {
                if (interpreterObj.isPresent() && phrase.isPresent()) {
                    if (interpreterObj.get().getParseTreeListener().isPresent()) {
                        notifyOnListenerException(interpreterObj.get().getParseTreeListener().get(),
                                testSection, testCase,
                                e);
                    } else if (interpreterObj.get().getParseTreeVisitor().isPresent()) {
                        notifyOnVisitorException(interpreterObj.get().getParseTreeVisitor().get(),
                                testSection, testCase,
                                e);
                    }
                }
                results.addTestResult(DefaultTestResult.failedTest(testCase, null, e, phraseIndex,
                        size - phraseIndex));
            }
            results.addTestResult(DefaultTestResult.passingTest(testCase));
            notifyAfterTestCase(testCase);
        }
        notifyAfterTestSuite(sharedTestCases, results, context);
        return results;
    }


}


