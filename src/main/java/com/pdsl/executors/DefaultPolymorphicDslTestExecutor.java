package com.pdsl.executors;

import com.pdsl.gherkin.DefaultGherkinTestSpecificationFactory;
import com.pdsl.logging.PdslThreadSafeOutputStream;
import com.pdsl.reports.DefaultTestResult;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.reports.TestRunResults;
import com.pdsl.specifications.DefaultPhrase;
import com.pdsl.specifications.FilteredPhrase;
import com.pdsl.specifications.Phrase;
import com.pdsl.testcases.SharedTestCase;
import com.pdsl.testcases.SharedTestSuite.SharedTestCaseWithInterpreter;
import com.pdsl.testcases.TestCase;
import com.pdsl.testcases.TestSection;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

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
    // In concurrent test cases an observer might be added to the collection while it's being read from
    // Use a type that is safe for concurrency.
    private final List<ExecutorObserver> activePhraseObservers = new CopyOnWriteArrayList<>();
    private final boolean filterDuplicates;
    /**
     * Constructs a DefaultPolymorphicDslTestExecutor with a default ColorizedObserver.
     */
    public DefaultPolymorphicDslTestExecutor() {
        this.registerObserver(new ColorizedLoggerObserver());
        String filterDuplicatesProperty = System.getProperty("pdsl.filterDuplicates");
        filterDuplicates = filterDuplicatesProperty != null && filterDuplicatesProperty.equalsIgnoreCase("true");
    }

    private DefaultPolymorphicDslTestExecutor(List<ExecutorObserver> observers, boolean filterDuplicates) {
        activePhraseObservers.addAll(observers);
        this.filterDuplicates = filterDuplicates;
    }

    /**
     * Creates a test executor that has all provided observers registered to it.
     *
     * The observers will be visited in the order they are provided in the list
     * @param observers
     * @return DefaultPolymorphicDslTestExecutor
     */
    public static DefaultPolymorphicDslTestExecutor of(List<ExecutorObserver> observers) {
        DefaultPolymorphicDslTestExecutor executor = new DefaultPolymorphicDslTestExecutor();
        executor.activePhraseObservers.clear(); // Remove default logger observer
        observers.forEach(executor::registerObserver);
        return executor;
    }

    public static DefaultPolymorphicDslTestExecutor ofFilterDuplicates(List<ExecutorObserver> observers) {
        return new DefaultPolymorphicDslTestExecutor(observers, true);
    }

    public static DefaultPolymorphicDslTestExecutor ofWithoutDuplicateFiltering(List<ExecutorObserver> observers) {
        return new DefaultPolymorphicDslTestExecutor(observers, false);
    }

    @Override
    public PolymorphicDslTestRunResults runTests(Collection<TestCase> testCases,
                                                 ParseTreeListener listener) {
        notifyBeforeTestSuite(testCases, listener, "NONE");
        MetadataTestRunResults results = walk(testCases, new InterpreterObj(listener), "NONE");
        notifyAfterTestSuite(testCases, listener, results, "NONE");
        return (PolymorphicDslTestRunResults) results;
    }

    @Override
    public TestRunResults runTests(Collection<TestCase> testCases,
                                   ParseTreeVisitor<?> subgrammarVisitor) {
        notifyBeforeTestSuite(testCases, subgrammarVisitor,"NONE");
        MetadataTestRunResults results = walk(testCases, new InterpreterObj(subgrammarVisitor), "NONE");
        notifyAfterTestSuite(testCases, subgrammarVisitor, results, "NONE");
        return results;
    }

    private MetadataTestRunResults walk(Collection<TestCase> testCases, InterpreterObj interpreterObj,
                                        String context) {
        PolymorphicDslTestRunResults results = new PolymorphicDslTestRunResults(
                new PdslThreadSafeOutputStream(), context);
        Set<List<String>> previouslyExecutedTests = new HashSet<>();
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

                if (filterDuplicates && previouslyExecutedTests.contains(testCase.getContextFilteredPhraseBody())) {
                    notifyDuplicateSkipped(testCase);
                    results.addTestResult(DefaultTestResult.duplicateTest(testCase));
                    continue;
                }
                if (filterDuplicates) {
                    previouslyExecutedTests.add(testCase.getContextFilteredPhraseBody());
                }
                while (testBody.hasNext()) {
                    testSection = testBody.next();
                    Phrase activePhrase = testSection.getPhrase();
                    if (listener.isPresent()) {
                        notifyBeforeListener(listener.get(), walker, testSection);
                        walker.walk(listener.get(), activePhrase.getParseTree());
                        notifyAfterListener(listener.get(), walker, testSection);
                    } else {
                        notifyBeforeVisitor(visitor.orElseThrow(), testSection);
                        visitor.get().visit(activePhrase.getParseTree());
                        notifyAfterVisitor(visitor.get(), testSection);
                    }
                    phraseIndex++;
                }
                results.addTestResult(DefaultTestResult.passingTest(testCase));
                notifyTestCaseSuccess(testCase);
            } catch (Throwable e) {
                if (listener.isPresent()) {
                    notifyAfterListener(listener.get(), walker, testSection);
                    notifyOnListenerException(listener.get(), testSection, testCase, e);
                } else {
                    notifyAfterVisitor(visitor.get(), testSection);
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

    /**
     * {@inheritDoc}
     *
     * If a supplier is used with any elements in the shared test cases, that supplier will be called to produce
     * a visitor/listener once before each test case.
     */
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
            Map<SharedTestCaseWithInterpreter, Iterator<TestSection>> interpreter2Iterator = sharedTestCase.getSharedTestCaseWithInterpreters()
                    .stream()
                    .collect(Collectors.toMap(interpreter -> interpreter,
                            interpreter -> interpreter.getTestCase().getContextFilteredTestSectionIterator()
                    ));

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
                            if (!interpreter2Iterator.get(interpreter).hasNext()) {
                                // If a parse tree was found there should definitely be a new test section or something
                                // is wrong
                                throw new IllegalStateException("PDSL Framework error: The context filtered interpreters are out of sync!");
                            }
                            testSection = interpreter2Iterator.get(interpreter).next();
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
                notifyTestCaseSuccess(sharedTestCase);
            } catch (Throwable e) {
                if (interpreterObj.isPresent() && phrase.isPresent()) {
                    if (interpreterObj.get().getParseTreeListener().isPresent()) {
                        notifyOnListenerException(interpreterObj.get().getParseTreeListener().get(),
                                testSection, sharedTestCase, e);
                    } else if (interpreterObj.get().getParseTreeVisitor().isPresent()) {
                        notifyOnVisitorException(interpreterObj.get().getParseTreeVisitor().get(),
                                testSection, sharedTestCase, e);
                    }
                }
                results.addTestResult(DefaultTestResult.failedTest(sharedTestCase, null, e, phraseIndex,
                        size - phraseIndex));
            }
            results.addTestResult(DefaultTestResult.passingTest(sharedTestCase));
            notifyAfterTestCase(sharedTestCase);
        }
        notifyAfterTestSuite(sharedTestCases, results, context);
        return results;
    }


    /**
     * Adds an observer that will be notified of events as test cases are executed.
     *
     * beforeTestSuite         - Called once before the execution of test cases
     * afterTestSuite          - Called once after the execution of all test cases
     * beforeTestCaseTriggered - Called once before each test case
     * afterTestCaseTriggered  - Called once after each test case
     * phraseFailure           - Called once for any test that failed
     * duplicateSkipped        - Called once only if filtering is enabled
     *                           fore each identical test was run earlier
     * beforePhrase            - Called before each phrase in each test case.
     * 	                         In the event of a phrase failure, no subsequent
     *                           phrases for that specific test case will be called
     * afterPhrase             - Called after each successful phrase in each test case
     * 	                         In the event of a phrase failure this method will not
     *                           be called
     * testCaseSuccess         - Called once after each test case only if it had no
     *                           phrase failures
     * @param observer an observer to notify during test execution events
     */
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
}
