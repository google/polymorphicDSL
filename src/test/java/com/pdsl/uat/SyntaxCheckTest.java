package com.pdsl.uat;

import com.pdsl.grammars.*;
import com.pdsl.junit.PdslGherkinApplication;
import com.pdsl.junit.PdslGherkinJUnit4Runner;
import com.pdsl.junit.PdslTest;
import com.pdsl.junit.RecognizedBy;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.runner.RunWith;

import javax.inject.Provider;

@RunWith(PdslGherkinJUnit4Runner.class)
@PdslGherkinApplication(
        resourceRoot = "src/test/resources/framework_specifications/features"
)
public class SyntaxCheckTest {

    @PdslTest(
            includesResources = "SubgrammarsUAT.feature",
            parser = DialectParser.class,
            lexer = DialectLexer.class,
            listener = SyntaxCheckTest.SyntaxCheckProvider.class
    )
    @RecognizedBy(
            dslRecognizerParser = DialectParser.class,
            dslRecognizerLexer = DialectLexer.class
    )
    public void parserWithMissingRecognizerRule_failsToCompile(){}

    public static final class SyntaxCheckProvider implements Provider<DialectParserListener> {

        @Override
        public DialectParserListener get() {
            return new CrashOnGrammarStep();
        }
        private static class CrashOnGrammarStep extends DialectParserBaseListener {
                @Override
                public void enterGrammarStep(DialectParser.GrammarStepContext ctx) {
                        throw new IllegalStateException("Grammar steps should not be entered, only subgrammar steps");
                }
            };
        }
}
