package com.pdsl.uat.java.sut;

import com.pdsl.runners.PdslConfiguration;
import com.pdsl.runners.PdslTest;
import com.pdsl.runners.junit.PdslJUnit4ConfigurableRunner;
import com.pdsl.uat.PdslConfigurableExecutorTest;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.junit.runner.RunWith;

import javax.inject.Provider;
import com.pdsl.grammars.AlphaParser;
import com.pdsl.grammars.AlphaLexer;
import com.pdsl.grammars.AlphaParserBaseListener;
@RunWith(PdslJUnit4ConfigurableRunner.class)
@PdslConfiguration(
        specificationFactoryProvider = PdslConfigurableExecutorTest.SpecificationFactoryProvider.class,
        testCaseFactoryProvider = PdslConfigurableExecutorTest.TestCaseFactoryProvider.class,
        resourceRoot = "src/test/resources/sentences/"
)
public class NoRecognizerRunner {

    @PdslTest(
            includesResources = "partially_invalid.pdsl",
            startRule = "mathematical_expression",
            parser = AlphaParser.class,
            lexer = AlphaLexer.class,
            listener = Listener.class
    )
    public void noRecognizer_allowsSkippingUnrecognizedPhrases() {}

    public static class Listener implements Provider<ParseTreeListener> {

        @Override
        public ParseTreeListener get() {
            return new AlphaParserBaseListener();
        }
    }
}
