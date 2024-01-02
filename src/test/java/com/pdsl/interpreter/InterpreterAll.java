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

/**
 * Positive scenario.
 * The example of the test case what able to combine in itself two different Lexer/Parser (`InterpreterOneParser.class` AND `InterpreterTwoParser.class`),
 * what were implemented previously/independently.
 *
 */
@RunWith(PdslGherkinJUnit4Runner.class)
@PdslGherkinApplication(
    resourceRoot = "src/test/resources/framework_specifications/features/interpreter",
    recognizerRule = "polymorphicDslAllRules"
)
public final class InterpreterAll {

  @PdslTest(
      includesResources = "InterpreterAll.feature",
      interpreters = {
          @Interpreter(parser = InterpreterOneParser.class, lexer = InterpreterOneLexer.class, listener = InterpreterOne.InterpreterOneListenerProvider.class),
          @Interpreter(parser = InterpreterTwoParser.class, lexer = InterpreterTwoLexer.class, listener = InterpreterTwo.InterpreterTwoListenerProvider.class)
      }
  )
  @RecognizedBy(
      dslRecognizerParser = InterpreterAllParser.class,
      dslRecognizerLexer = InterpreterAllLexer.class,
      recognizerRule = "polymorphicDslAllRulesAll"
  )
  public void helloHiTest1() {
  }

  @PdslTest(includesResources = "InterpreterAll.feature",

      interpreters = {
          @Interpreter(parser = InterpreterOneParser.class, lexer = InterpreterOneLexer.class, listener = InterpreterOne.InterpreterOneListenerProvider.class)
          ,@Interpreter(parser = InterpreterTwoParser.class, lexer = InterpreterTwoLexer.class, listener = InterpreterTwo.InterpreterTwoListenerProvider.class)
          //,@Interpreter(parser = InterpreterTwoParser.class, lexer = InterpreterTwoLexer.class, listener = InterpreterTwo.InterpreterTwoListenerProvider.class)
      }
  )
  @RecognizedBy(
      dslRecognizerParser = InterpreterAllParser.class,
      dslRecognizerLexer = InterpreterAllLexer.class,
      recognizerRule = "polymorphicDslAllRulesAll"
  )
  //@Test()//expected = RuntimeException.class
  public void helloHiTest2() {
  }
}
