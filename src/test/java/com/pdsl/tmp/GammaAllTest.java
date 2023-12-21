package com.pdsl.tmp;

import com.pdsl.grammars.GammaAllParser;
import com.pdsl.grammars.GammaOneLexer;
import com.pdsl.grammars.GammaOneParser;
import com.pdsl.grammars.GammaTwoLexer;
import com.pdsl.grammars.GammaTwoParser;
import com.pdsl.runners.CodeExecution;
import com.pdsl.runners.PdslGherkinApplication;
import com.pdsl.runners.PdslTest;
import com.pdsl.runners.RecognizedBy;
import com.pdsl.runners.junit.PdslGherkinJUnit4Runner;
import org.junit.runner.RunWith;

@RunWith(PdslGherkinJUnit4Runner.class)
@PdslGherkinApplication(
        resourceRoot = "src/test/resources/framework_specifications/features/tmp",
        recognizerRule = "polymorphicDslAllRules"
    //recognizerRule = "polymorphicDslAllRulesAll"
)
public class GammaAllTest {

    @PdslTest(
            includesResources = "GammaAll.feature",

        // parser = GammaOneParser.class,
        // lexer = GammaOneLexer.class,
        // listener = SimpleHelloTest.SimpleHelloListenerProvider.class

            codeExecution = {
                @CodeExecution(parser = GammaOneParser.class, lexer = GammaOneLexer.class, listener = GammaOneTest.GammaOneListenerProvider.class)
                //, @CodeExecution(parser = GammaTwoParser.class, lexer = GammaTwoLexer.class, listener = GammaTwoTest.GammaTwoListenerProvider.class)
                //,                @CodeExecution(parser = GammaAllParser.class, lexer = GammaTwoLexer.class, listener = GammaTwoTest.GammaTwoListenerProvider.class)
            }
    )
    @RecognizedBy(
            dslRecognizerParser = GammaOneParser.class,
            dslRecognizerLexer = GammaOneLexer.class,
            recognizerRule = "polymorphicDslAllRules"
    )
    public void helloHiTest(){}
}
