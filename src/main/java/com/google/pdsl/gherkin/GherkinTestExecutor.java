package com.google.pdsl.gherkin;

import com.google.pdsl.executors.GherkinPolymorphicDslTestExecutor;
import com.google.pdsl.executors.GrammarExecutor;
import com.google.pdsl.executors.ParentForEachChildPhraseFullExecutor;
import com.google.pdsl.executors.PolymorphicDslTestExecutor;
import com.google.pdsl.reports.PolymorphicDslTestRunResults;
import com.google.pdsl.specifications.LineDelimitedTestSpecificationFactory;
import com.google.pdsl.specifications.TestSpecification;
import com.google.pdsl.specifications.TestSpecificationFactory;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class GherkinTestExecutor<G extends Parser, L extends Lexer, SG extends Parser, SL extends Lexer> {


    private static final PickleJarFactory pickleJarFactory = new PickleJarFactory(new PdslGherkinInterpreterImpl(), new PdslGherkinListenerImpl(), StandardCharsets.UTF_8);
    private TestSpecificationFactory stepBodyGrammarHelperFactory;
    private TestSpecificationFactory stepBodySubgrammarHelperFactory;
    private TestSpecificationFactory provider;

    public GherkinTestExecutor(Class<G> grammarParser, Class<L> grammarLexer, Class<SG> subgrammarParser, Class<SL> subgrammarLexer) {
        stepBodyGrammarHelperFactory = new LineDelimitedTestSpecificationFactory<G, L>(grammarParser, grammarLexer, LineDelimitedTestSpecificationFactory.ErrorListenerStrategy.GRAMMAR);
        stepBodySubgrammarHelperFactory = new LineDelimitedTestSpecificationFactory<SG, SL>(subgrammarParser, subgrammarLexer, LineDelimitedTestSpecificationFactory.ErrorListenerStrategy.SUBGRAMMAR);
        provider = new GherkinTestSpecificationFactory(pickleJarFactory, stepBodyGrammarHelperFactory, stepBodySubgrammarHelperFactory);
    }

    public PolymorphicDslTestRunResults runTests(List<String> fileLocations, String tagExpression,
                                                 ParseTreeListener grammarListener, ParseTreeListener subgrammarListener) {
        GherkinPolymorphicDslTestExecutor.Builder executorBuilder = new GherkinPolymorphicDslTestExecutor.Builder()
                .withVerifierExecutor( new ParentForEachChildPhraseFullExecutor())
                .withTagExpression(tagExpression)
                .withSubgrammarListener(subgrammarListener)
                .withGrammarListener(grammarListener);
        TestSpecification testSpecification = provider.getTestSpecifications(fileLocations);
        return executorBuilder.build().runTests(testSpecification);
    }

    public PolymorphicDslTestRunResults runTests(List<String> fileLocations,
                         ParseTreeListener grammarListener, ParseTreeListener subgrammarListener) {
        return runTests(fileLocations, "", grammarListener, subgrammarListener);
    }
}
