package com.pdsl.uat.java.sut;

import com.pdsl.runners.PdslConfiguration;
import com.pdsl.runners.PdslConfiguration;
import com.pdsl.runners.PdslTest;
import com.pdsl.runners.RecognizedBy;
import com.pdsl.runners.junit.PdslJUnit4ConfigurableRunner;
import com.pdsl.uat.PdslConfigurableExecutorTest;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.junit.runner.RunWith;

import javax.inject.Provider;
import com.pdsl.grammars.RegistryParser;
import com.pdsl.grammars.RegistryLexer;
import com.pdsl.grammars.AlphaParser;
import com.pdsl.grammars.AlphaLexer;
import com.pdsl.grammars.BetaParser;
import com.pdsl.grammars.BetaLexer;
import com.pdsl.grammars.AlphaParserBaseListener;

@RunWith(PdslJUnit4ConfigurableRunner.class)
@PdslConfiguration(
        specificationFactoryProvider = PdslConfigurableExecutorTest.SpecificationFactoryProvider.class,
        testCaseFactoryProvider = PdslConfigurableExecutorTest.TestCaseFactoryProvider.class,
        dslRecognizerParser = RegistryParser.class,
        dslRecognizerLexer = RegistryLexer.class,
        resourceRoot = "src/test/resources/sentences/",
        recognizerRule = "mathematical_expression"
)
public class UnrecognizedPdslPhrases {

    @PdslTest(
            parser = AlphaParser.class,
            lexer = AlphaLexer.class,
            startRule = "mathematical_expression",
            includesResources = "partially_invalid.pdsl",
            listener = ConfigurationRecognizerRunner.Listener.class
    )
    @RecognizedBy(
            dslRecognizerParser = BetaParser.class,
            dslRecognizerLexer = BetaLexer.class,
            recognizerRule = "helloWorld"
    )
    public void registryParserFiltersInput_allowsSubgrammarToExecute(){}

    public static class Listener implements Provider<ParseTreeListener> {

        @Override
        public ParseTreeListener get() {
            return new AlphaParserBaseListener();
        }
    }

}
