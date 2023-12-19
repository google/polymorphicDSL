package com.pdsl.tmp;

import com.pdsl.grammars.GammaTwoLexer;
import com.pdsl.grammars.GammaTwoParser;
import com.pdsl.grammars.GammaTwoListenerImpl;
import com.pdsl.runners.PdslGherkinApplication;
import com.pdsl.runners.PdslTest;
import com.pdsl.runners.junit.PdslGherkinJUnit4Runner;
import javax.inject.Provider;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.junit.runner.RunWith;

@RunWith(PdslGherkinJUnit4Runner.class)
@PdslGherkinApplication(
        resourceRoot = "src/test/resources/framework_specifications/features/tmp",
        recognizerRule = "polymorphicDslAllRules"
)
public class GammaTwoTest {

    @PdslTest(
            includesResources = "GammaTwo.feature",
            parser = GammaTwoParser.class,
            lexer = GammaTwoLexer.class,
            listener = GammaTwoTest.GammaTwoListenerProvider.class)
    // @RecognizedBy(
    //         dslRecognizerParser = BetaParser.class,
    //         dslRecognizerLexer = BetaLexer.class,
    //         recognizerRule = "polymorphicDslAllRules"
    // )
    public void helloHiTest(){}

  public static class GammaTwoListenerProvider implements Provider<ParseTreeListener> {
    @Override
    public ParseTreeListener get() {
      return new GammaTwoListenerImpl();
    }
  }
}
