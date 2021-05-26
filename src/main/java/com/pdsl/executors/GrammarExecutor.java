package com.pdsl.executors;

import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.specifications.TestSpecification;
import org.antlr.v4.runtime.tree.ParseTreeListener;

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
