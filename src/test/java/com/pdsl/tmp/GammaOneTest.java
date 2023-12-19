package com.pdsl.tmp;

import com.pdsl.grammars.GammaOneLexer;
import com.pdsl.grammars.GammaOneParser;
import com.pdsl.grammars.GammaOneListenerImpl;
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
public class GammaOneTest {

    @PdslTest(
            includesResources = "GammaOne.feature",
            parser = GammaOneParser.class,
            lexer = GammaOneLexer.class,
            listener = GammaOneTest.GammaOneListenerProvider.class
    )
    // @RecognizedBy(
    //         dslRecognizerParser = BetaParser.class,
    //         dslRecognizerLexer = BetaLexer.class,
    //         recognizerRule = "polymorphicDslAllRules"
    // )
    public void helloHiTest(){}

  public static class GammaOneListenerProvider implements Provider<ParseTreeListener> {
    @Override
    public ParseTreeListener get() {
      return new GammaOneListenerImpl();
    }
  }
}
