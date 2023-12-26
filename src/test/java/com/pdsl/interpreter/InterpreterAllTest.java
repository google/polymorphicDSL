package com.pdsl.interpreter;

import com.pdsl.grammars.InterpreterAllLexer;
import com.pdsl.grammars.InterpreterAllParser;
import com.pdsl.grammars.InterpreterOneLexer;
import com.pdsl.grammars.InterpreterOneParser;
import com.pdsl.grammars.InterpreterTwoLexer;
import com.pdsl.grammars.InterpreterTwoParser;
import com.pdsl.runners.Interpreter;
import com.pdsl.runners.PdslGherkinApplication;
import com.pdsl.runners.PdslTest;
import com.pdsl.runners.RecognizedBy;
import com.pdsl.runners.junit.PdslGherkinJUnit4Runner;
import org.junit.runner.RunWith;

@RunWith(PdslGherkinJUnit4Runner.class)
@PdslGherkinApplication(
        resourceRoot = "src/test/resources/framework_specifications/features/interpreter",
        recognizerRule = "polymorphicDslAllRules"
    //recognizerRule = "polymorphicDslAllRulesAll"
)
public class InterpreterAllTest {

    @PdslTest(
            includesResources = "InterpreterAll.feature",

        // parser = GammaOneParser.class,
        // lexer = GammaOneLexer.class,
        // listener = SimpleHelloTest.SimpleHelloListenerProvider.class

        interpreter = {
                @Interpreter(parser = InterpreterOneParser.class, lexer = InterpreterOneLexer.class, listener = InterpreterOneTest.InterpreterOneListenerProvider.class)
                , @Interpreter(parser = InterpreterTwoParser.class, lexer = InterpreterTwoLexer.class, listener = InterpreterTwoTest.InterpreterTwoListenerProvider.class)
                //,                @CodeExecution(parser = GammaAllParser.class, lexer = GammaTwoLexer.class, listener = GammaTwoTest.GammaTwoListenerProvider.class)
            }
    )
    @RecognizedBy(
            dslRecognizerParser = InterpreterAllParser.class,
            dslRecognizerLexer = InterpreterAllLexer.class,
            recognizerRule = "polymorphicDslAllRulesAll"
    )
    public void helloHiTest1(){}

    @PdslTest(includesResources = "InterpreterAll.feature",

        interpreter = {
            @Interpreter(parser = InterpreterOneParser.class, lexer = InterpreterOneLexer.class, listener = InterpreterOneTest.InterpreterOneListenerProvider.class),
            @Interpreter(parser = InterpreterTwoParser.class, lexer = InterpreterTwoLexer.class, listener = InterpreterTwoTest.InterpreterTwoListenerProvider.class)
            //,                @CodeExecution(parser = GammaAllParser.class, lexer = GammaTwoLexer.class, listener = GammaTwoTest.GammaTwoListenerProvider.class)
        }
    )
    @RecognizedBy(
        dslRecognizerParser = InterpreterAllParser.class,
        dslRecognizerLexer = InterpreterAllLexer.class,
        recognizerRule = "polymorphicDslAllRulesAll"
    )
    public void helloHiTest2(){}
}
