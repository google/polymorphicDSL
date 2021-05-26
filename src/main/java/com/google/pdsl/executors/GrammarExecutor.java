package com.google.pdsl.executors;

import com.google.pdsl.reports.PolymorphicDslTestRunResults;
import com.google.pdsl.specifications.TestSpecification;
import com.google.pdsl.transformers.PhraseTransformer;
import org.antlr.v4.runtime.tree.ParseTreeListener;

import java.util.List;

/**
 * A registry that can verify if a test specification conforms to the provided DSL (in the form of a {@code ParseTreeListener}.
 *
 * Similar to a {@code ContextExecutor}, but a registry does a much lighter walk that may exclude logging and other "heavy weight" features
 */
public interface GrammarExecutor {
    PolymorphicDslTestRunResults processGrammar(TestSpecification testCaseSpecificationList,
                                                ParseTreeListener phraseRegistry);
    PolymorphicDslTestRunResults processGrammarAndSubgrammar(TestSpecification testSpecification, ParseTreeListener grammarListener,
                                     ParseTreeListener subgrammarListener);
}
