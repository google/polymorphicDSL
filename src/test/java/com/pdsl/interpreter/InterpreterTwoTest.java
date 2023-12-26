package com.pdsl.interpreter;

import com.pdsl.grammars.InterpreterTwoLexer;
import com.pdsl.grammars.InterpreterTwoParser;
import com.pdsl.grammars.InterpreterTwoListenerImpl;
import com.pdsl.runners.PdslGherkinApplication;
import com.pdsl.runners.PdslTest;
import com.pdsl.runners.junit.PdslGherkinJUnit4Runner;
import javax.inject.Provider;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.junit.runner.RunWith;
import com.pdsl.runners.RecognizedBy;

@RunWith(PdslGherkinJUnit4Runner.class)
@PdslGherkinApplication(
        resourceRoot = "src/test/resources/framework_specifications/features/interpreter",
        recognizerRule = "polymorphicDslAllRules"
)
public class InterpreterTwoTest {

    @PdslTest(
            includesResources = "InterpreterTwo.feature",
            parser = InterpreterTwoParser.class,
            lexer = InterpreterTwoLexer.class,
            listener = InterpreterTwoTest.InterpreterTwoListenerProvider.class)
    @RecognizedBy(
            dslRecognizerParser = InterpreterTwoParser.class,
            dslRecognizerLexer = InterpreterTwoLexer.class,
            recognizerRule = "polymorphicDslAllRules"
    )
    public void helloHiTest(){}

  public static class InterpreterTwoListenerProvider implements Provider<ParseTreeListener> {
    @Override
    public ParseTreeListener get() {
      return new InterpreterTwoListenerImpl();
    }
  }
}
