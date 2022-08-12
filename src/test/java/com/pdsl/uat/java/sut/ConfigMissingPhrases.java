package com.pdsl.uat.java.sut;


import com.pdsl.runners.PdslConfiguration;
import com.pdsl.runners.PdslTest;
import com.pdsl.runners.junit.PdslJUnit4ConfigurableRunner;
import com.pdsl.uat.PdslConfigurableExecutorTest;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.junit.runner.RunWith;
import com.pdsl.grammars.RegistryParser;
import com.pdsl.grammars.RegistryLexer;
import com.pdsl.grammars.AlphaParser;
import com.pdsl.grammars.AlphaLexer;
import com.pdsl.grammars.BetaParser;
import com.pdsl.grammars.BetaLexer;
import com.pdsl.grammars.AlphaParserBaseListener;

import javax.inject.Provider;

@RunWith(PdslJUnit4ConfigurableRunner.class)
@PdslConfiguration(
        specificationFactoryProvider = PdslConfigurableExecutorTest.SpecificationFactoryProvider.class,
        testCaseFactoryProvider = PdslConfigurableExecutorTest.TestCaseFactoryProvider.class,
        dslRecognizerParser = BetaParser.class,
        dslRecognizerLexer = BetaLexer.class,
        resourceRoot = "src/test/resources/sentences/",
        recognizerRule = "helloWorld"
)
public class ConfigMissingPhrases {
    @PdslTest(
            parser = AlphaParser.class,
            lexer = AlphaLexer.class,
            includesResources = "partially_invalid.pdsl",
            listener = ConfigurationRecognizerRunner.Listener.class,
            startRule = "mathematical_expression"
    )
    public void resourceUnrecognizedByConfig_throwsException(){}

    public static class Listener implements Provider<ParseTreeListener> {

        @Override
        public ParseTreeListener get() {
            return new AlphaParserBaseListener();
        }
    }
}
