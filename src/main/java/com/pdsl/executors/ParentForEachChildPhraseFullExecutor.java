package com.pdsl.executors;

import com.google.common.base.Preconditions;
import com.pdsl.logging.AnsiTerminalColorHelper;
import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.reports.TestMetadata;
import com.pdsl.specifications.TestSpecification;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class ParentForEachChildPhraseFullExecutor implements GrammarExecutor {

    private Logger logger = LoggerFactory.getLogger(TopDownPhraseRegistryExecutor.class);
    private ParseTreeWalker walker = ParseTreeWalker.DEFAULT;

    private enum ExecutionMode {
        WITH_LOGGING,
        NO_LOGGING;
    }

    @Override
    public PolymorphicDslTestRunResults processGrammar(TestSpecification testCaseSpecification, ParseTreeListener dispatcher) {
        logger.info(AnsiTerminalColorHelper.BRIGHT_PURPLE + "Executing test specifications..." + AnsiTerminalColorHelper.RESET);
       return recursiveWalk(testCaseSpecification, dispatcher, ExecutionMode.WITH_LOGGING);

    }

    @Override
    public PolymorphicDslTestRunResults processGrammarAndSubgrammar(TestSpecification testSpecification, ParseTreeListener phraseRegistry, ParseTreeListener subgrammarListener) {
        // Walk the phrase registry to make sure all phrases are defined
        logger.info(AnsiTerminalColorHelper.BRIGHT_PURPLE + "Verifying that all phrases are in the registry..." + AnsiTerminalColorHelper.RESET);
        // recursively print metadata of each parent and pass phrases to child until we hit a leaf node,
        // then execute on the leaf
        recursiveWalk(testSpecification, phraseRegistry, ExecutionMode.NO_LOGGING);
        logger.info(AnsiTerminalColorHelper.BRIGHT_PURPLE + "All phrases successfully verified!" + AnsiTerminalColorHelper.RESET);
        return processGrammar(testSpecification, subgrammarListener);
    }

    private PolymorphicDslTestRunResults recursiveWalk(TestSpecification testSpecification, ParseTreeListener phraseRegistry, ExecutionMode mode) {
        PolymorphicDslTestRunResults results = new PolymorphicDslTestRunResults(System.out);
        recursiveWalkAndExecuteOnLeaf(testSpecification, new LinkedList<TestSection>(), phraseRegistry, mode, results);
        return results;
    }

    private void recursiveWalkAndExecuteOnLeaf(TestSpecification testSpecification, Deque<TestSection> parentPhrases, ParseTreeListener phraseRegistry, ExecutionMode mode,
                                               PolymorphicDslTestRunResults results) {
        LinkedList<TestSection> allParentPhrases = new LinkedList<>(parentPhrases);
        if (mode.equals(ExecutionMode.WITH_LOGGING) && testSpecification.getPhrases().isEmpty()) { // No test sections will be made to do logging
            System.out.println(testSpecification.getId().strip());
            if (testSpecification.getMetaData().isPresent()) {
                System.out.println(testSpecification.getMetaData().get());
            }
        }
        if (testSpecification.getPhrases().isPresent()) {
            allParentPhrases.add(new TestSection(testSpecification.getMetaData().isPresent() ? testSpecification.getMetaData().get().toString() : "", testSpecification.getPhrases().get(), mode));
        }
        if (testSpecification.nestedTestSpecifications().isPresent()) {
            for (TestSpecification childTestSpecification : testSpecification.nestedTestSpecifications().get()) {
                recursiveWalkAndExecuteOnLeaf(childTestSpecification, allParentPhrases, phraseRegistry, mode, results);
            }
        } else {
            TestSectionIterator testSectionIterator = new TestSectionIterator(allParentPhrases);
            int passedPhrases = 0;
            // Used to calculate a (hopefully) unique hashcode on the step body
            LinkedList<String> parseTrees = new LinkedList<>();
            Optional<TestMetadata> testMetadata = Optional.empty();
            while (testSectionIterator.hasNext()) {
                ParseTree parseTree = testSectionIterator.next();
                parseTrees.add(parseTree.getText());
                try {
                    walker.walk(phraseRegistry, parseTree);
                    passedPhrases++;
                } catch (Throwable e) {
                    int phrasesSkippedDueToFailure = 0;
                    while (testSectionIterator.hasNext()) {
                        parseTrees.add(testSectionIterator.next().getText());
                        phrasesSkippedDueToFailure++;
                    }
                    testMetadata = Optional.of(new TestMetadata(testSpecification.getId(),
                            passedPhrases,
                            phrasesSkippedDueToFailure,
                            parseTree.getText(), e, parseTrees.hashCode()));
                }
            }
            if (testMetadata.isPresent()) {
                results.addTestResult(testMetadata.get());
            } else {
                results.addTestResult(new TestMetadata(testSpecification.getId(), passedPhrases, parseTrees.hashCode()));
            }
        }
    }

    private static class TestSectionIterator implements Iterator {
        private Queue<TestSection> testSections;
        private TestSection currentSection;
        private final int phraseBodyId;
        public TestSectionIterator(Deque<TestSection> testSections) {
            Preconditions.checkNotNull(testSections, "Test sections cannot be null!");
            Preconditions.checkArgument(!testSections.isEmpty(), "Test Sections cannot be empty!");
            this.testSections = new LinkedList<>(testSections.stream()  // Defensive copy necessary
                    .map(TestSection::new)
                    .collect(Collectors.toList()));
            this.currentSection = this.testSections.poll();
            this.phraseBodyId = new LinkedList<>(testSections).hashCode();
        }

        @Override
        public boolean hasNext() {

            return currentSection.hasNext() || !testSections.isEmpty();
        }

        @Override
        public ParseTree next() {
            if (!currentSection.hasNext()) {
                currentSection = testSections.poll();
            }
            return currentSection.next();
        }
    }

    private static class TestSection implements Iterator {
        boolean firstElementAccessed = false;
        private final String testSection;
        private final Queue<ParseTree> parseTrees;
        private final ExecutionMode mode;

        public TestSection(String title, Collection<ParseTree> parseTrees, ExecutionMode mode) {
            this.testSection = title;
            this.parseTrees = new LinkedList<>(parseTrees); //Defensive copy
            this.mode = mode;
            this.firstElementAccessed = false;
        }

        public TestSection(TestSection testSectionToCopy) {
            this.firstElementAccessed = false;
            this.testSection = testSectionToCopy.testSection;
            this.parseTrees = new LinkedList<>(testSectionToCopy.parseTrees);
            this.mode = testSectionToCopy.mode;
        }

        @Override
        public int hashCode() {
            // List hashcodes are well defined to care about element order, which we also care about
            return new LinkedList<ParseTree>(parseTrees).hashCode();
        }

        @Override
        public boolean hasNext() {
            return firstElementAccessed == false || parseTrees.peek() != null;
        }

        @Override
        public ParseTree next() {
            if (!firstElementAccessed) {
                firstElementAccessed = true;
                if (mode.equals(ExecutionMode.WITH_LOGGING)) {
                    System.out.println(testSection.strip());
                }
            }
            return parseTrees.poll();
        }

        public Queue<ParseTree> getParseTreeBody() {
            return parseTrees;
        }
    }
}