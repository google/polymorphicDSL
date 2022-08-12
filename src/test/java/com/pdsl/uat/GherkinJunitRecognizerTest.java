package com.pdsl.uat;

import com.pdsl.grammars.*;
import com.pdsl.runners.PdslGherkinApplication;
import com.pdsl.runners.junit.PdslGherkinJUnit4Runner;
import com.pdsl.runners.PdslTest;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.junit.runner.RunWith;

import javax.inject.Provider;

@RunWith(PdslGherkinJUnit4Runner.class)
@PdslGherkinApplication(
        resourceRoot = "src/test/resources/framework_specifications/features",
        dslRecognizerParser = AllGrammarsParser.class,
        dslRecognizerLexer = AllGrammarsLexer.class,
        recognizerRule = "polymorphicDslAllRules"
)
public class GherkinJunitRecognizerTest {

    @PdslTest(
            includesResources = "PdslTestFramework.feature",
            parser = PdslTestResourceParser.class,
            lexer = PdslTestResourceLexer.class,
            listener = GherkinJunitRecognizerTest.FrameworkSpecificationListenerProvider.class
    )
    public void  skipUnrecognizedWithMissingSentences_shouldFail(){}

    public static class FrameworkSpecificationListenerProvider implements Provider<ParseTreeListener> {
        @Override
        public ParseTreeListener get() {
            return new PdslFrameworkSpecificationImpl();
        }
    }

}
