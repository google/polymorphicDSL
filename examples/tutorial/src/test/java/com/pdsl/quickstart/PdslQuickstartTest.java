package com.pdsl.quickstart;

import com.pdsl.runners.PdslGherkinApplication;
import com.pdsl.runners.PdslTest;
import com.pdsl.runners.RecognizedBy;
import com.pdsl.runners.junit.PdslGherkinJUnit4Runner;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.junit.runner.RunWith;
import com.example.MyFirstLexer;
import com.example.MyFirstParser;

import javax.inject.Provider;

@RunWith(PdslGherkinJUnit4Runner.class)
@PdslGherkinApplication(
        resourceRoot = "src/test/resources/features"
)
public class PdslQuickstartTest {

    @PdslTest(
            includesResources = "PdslQuickstart.feature",
            parser = MyFirstParser.class,
            lexer = MyFirstLexer.class,
            listener = PdslQuickstartTest.MyFirstPdslProvider.class)
    public void myFirstPdslTest() {}

    // The provider builds the listener for the PDSL framework.
    // It must be static so that it can be seen by the underlying framework.
    // It also must have a default constructor (no parameters).
    // It can be convenient to make it a static class rather than put it in it's
    // own file so that the person running the test can understand if there are
    // special inputs or not (dependency injection, etc).
    public static class MyFirstPdslProvider implements Provider<ParseTreeListener> {

        @Override
        public ParseTreeListener get() {
            return new MyFirstPdslListener();
        }
    }
}
