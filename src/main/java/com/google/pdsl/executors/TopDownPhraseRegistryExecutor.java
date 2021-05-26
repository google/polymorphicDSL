package com.google.pdsl.executors;

import com.google.pdsl.reports.PolymorphicDslTestRunResults;
import com.google.pdsl.reports.TestMetadata;
import com.google.pdsl.reports.TestRunResults;
import com.google.pdsl.specifications.TestSpecification;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public class TopDownPhraseRegistryExecutor implements GrammarExecutor {

    private Logger logger = LoggerFactory.getLogger(TopDownPhraseRegistryExecutor.class);
    private ParseTreeWalker walker = new ParseTreeWalker();
    @Override
    public PolymorphicDslTestRunResults processGrammar(TestSpecification testSpecification, ParseTreeListener phraseRegistry) {
        // Walk the phrase registry to make sure all phrases are defined
        logger.info("Verifying that all phrases are in the registry...");
        List<ParseTree> phrases = getPhrases(testSpecification);
        PolymorphicDslTestRunResults results = walk(phrases, phraseRegistry, testSpecification.getId());
        logger.info("All phrases successfully verified!");
        return results;
    }

    @Override
    public PolymorphicDslTestRunResults processGrammarAndSubgrammar(TestSpecification testSpecification, ParseTreeListener grammarListener, ParseTreeListener subgrammarListener) {
        logger.debug("Verifying all steps are in grammar: " + testSpecification.getId());
        List<ParseTree> phrases = getPhrases(testSpecification);
        walk(phrases, grammarListener, testSpecification.getId());
        logger.debug("Executing subgrammar: " + testSpecification.getId());
        PolymorphicDslTestRunResults results = walk(phrases, subgrammarListener, testSpecification.getId());
        return results;
    }

    public static List<ParseTree> getPhrases(TestSpecification testSpecification) {
        List<ParseTree> phrases = new LinkedList<>();
        if (testSpecification.getPhrases().isPresent()) {
            phrases.addAll(testSpecification.getPhrases().get());
        }
        if (testSpecification.nestedTestSpecifications().isPresent()) {
            for (TestSpecification specification : testSpecification.nestedTestSpecifications().get()) {
                phrases.addAll(getPhrases(specification));
            }
        }
        return phrases;
    }

    private PolymorphicDslTestRunResults walk(List<ParseTree> phrases, ParseTreeListener phraseRegistry, String testId) {
        TestSpecification testSpecification;
        PolymorphicDslTestRunResults results = new PolymorphicDslTestRunResults(System.out);
        int totalPassingPhrases = 0;
        for (ParseTree phrase : phrases) {
            try {
                walker.walk(phraseRegistry, phrase);
                totalPassingPhrases++;
            } catch (Throwable e) {
                results.addTestResult(new TestMetadata(testId, totalPassingPhrases,
                        phrases.size() - totalPassingPhrases, phrase.getText(), e,
                        phrases.hashCode()));
            }
        }
        results.addTestResult(new TestMetadata(testId, totalPassingPhrases, phrases.hashCode()));
        return results;
    }
}
