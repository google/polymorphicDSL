package com.pdsl.interpreter;

import com.pdsl.grammars.InterpreterOneLexer;
import com.pdsl.grammars.InterpreterOneParser;
import com.pdsl.grammars.InterpreterOneListenerImpl;
import com.pdsl.runners.PdslGherkinApplication;
import com.pdsl.runners.PdslTest;
import com.pdsl.runners.RecognizedBy;
import com.pdsl.runners.junit.PdslGherkinJUnit4Runner;
import javax.inject.Provider;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.junit.runner.RunWith;

@RunWith(PdslGherkinJUnit4Runner.class)
@PdslGherkinApplication(
        resourceRoot = "src/test/resources/framework_specifications/features/interpreter",
        recognizerRule = "polymorphicDslAllRules"
)
public class InterpreterOne {

    @PdslTest(
            includesResources = "InterpreterOne.feature",
            parser = InterpreterOneParser.class,
            lexer = InterpreterOneLexer.class,
            listener = InterpreterOne.InterpreterOneListenerProvider.class
    )
    @RecognizedBy(
            dslRecognizerParser = InterpreterOneParser.class,
            dslRecognizerLexer = InterpreterOneLexer.class,
            recognizerRule = "polymorphicDslAllRules"
    )
    public void helloHiTest(){}

  public static class InterpreterOneListenerProvider implements Provider<ParseTreeListener> {
    @Override
    public ParseTreeListener get() {
      return new InterpreterOneListenerImpl();
    }
  }
}
