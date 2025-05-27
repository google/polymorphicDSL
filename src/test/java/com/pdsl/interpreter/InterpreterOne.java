package com.pdsl.interpreter;

import com.pdsl.grammars.InterpreterOneLexer;
import com.pdsl.grammars.InterpreterOneParser;
import com.pdsl.grammars.InterpreterOneParserBaseListener;
import com.pdsl.runners.PdslGherkinApplication;
import com.pdsl.runners.PdslTest;
import com.pdsl.runners.RecognizedBy;
import com.pdsl.runners.junit.PdslGherkinJUnit4Runner;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.junit.runner.RunWith;

import javax.inject.Provider;

/**
 * This is for debugging only.
 * It's basic happy path pDSL framework scenario testing - it's covered in the regular regression, does not require to be included in the main regression scope.
 */
@RunWith(PdslGherkinJUnit4Runner.class)
@PdslGherkinApplication(
        resourceRoot = "src/test/resources/framework_specifications/features/interpreter",
        recognizerRule = "polymorphicDslAllRules"
)
public final class InterpreterOne {

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
      return new InterpreterOneParserBaseListener();
    }
  }
}
