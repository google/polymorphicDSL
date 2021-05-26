package com.pdsl.executors;

import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.specifications.TestSpecification;
import org.antlr.v4.runtime.tree.ParseTreeListener;

public class DefaultPolymorphicDslTestExecutor implements PolymorphicDslTestExecutor {
    // Grammar contianing every valid phrase in the system under test
    private final ParseTreeListener grammar;
    // The phrases that actually matter in the sub-component being tested
    private final ParseTreeListener subgrammar;
    private final GrammarExecutor grammarExecutor;
    private final GrammarExecutor subgrammarExecutor;

    public DefaultPolymorphicDslTestExecutor(ParseTreeListener grammarPhraseListener,
                                             ParseTreeListener subGrammarPhraseListener
                                      ) {
        this.grammar = grammarPhraseListener;
        this.subgrammar = subGrammarPhraseListener;
        this.grammarExecutor = new TopDownPhraseRegistryExecutor();
        this.subgrammarExecutor = new TopDownPhraseRegistryExecutor();
    }

    public DefaultPolymorphicDslTestExecutor(ParseTreeListener grammar,
                                             ParseTreeListener subgrammar,
                                             GrammarExecutor subgrammarExecutor,
                                             GrammarExecutor grammarExecutor
    ) {
        this.grammar = grammar;
        this.subgrammar = subgrammar;
        this.grammarExecutor = grammarExecutor;
        this.subgrammarExecutor = subgrammarExecutor;
    }

    public DefaultPolymorphicDslTestExecutor(ParseTreeListener grammar,
                                             ParseTreeListener subgrammar,
                                             GrammarExecutor verifierExecutor
                                             ) {
        this.grammar = grammar;
        this.subgrammar = subgrammar;
        this.grammarExecutor = verifierExecutor;
        this.subgrammarExecutor = verifierExecutor;
    }

    /**
     * Runs the tests with the default Polymorphic DSL console logger.
     *
     * @param testSpecification the tests to run
     * @return
     */
    public PolymorphicDslTestRunResults runTests(TestSpecification testSpecification) {
        if (testSpecification.getPhrases().isEmpty() && testSpecification.nestedTestSpecifications().isEmpty()) {
            throw new IllegalArgumentException("Test specification was empty! No phrases found.");
        }
        grammarExecutor.processGrammar(testSpecification, grammar);
        return subgrammarExecutor.processGrammar(testSpecification, subgrammar);
    }

}
