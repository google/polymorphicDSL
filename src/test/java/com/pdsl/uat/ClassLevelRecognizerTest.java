package com.pdsl.uat;

import com.pdsl.grammars.*;
import com.pdsl.runners.PdslGherkinApplication;
import com.pdsl.runners.junit.PdslGherkinJUnit4Runner;
import com.pdsl.runners.PdslTest;
import com.pdsl.runners.RecognizedBy;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.junit.runner.RunWith;

import javax.inject.Provider;

@RunWith(PdslGherkinJUnit4Runner.class)
@PdslGherkinApplication(
        resourceRoot = "src/test/resources/framework_specifications/features",
        dslRecognizerParser = MinimalParser.class,
        dslRecognizerLexer = MinimalLexer.class,
        recognizerRule = "minimal"
)
public class ClassLevelRecognizerTest {
    @PdslTest(
            includesResources = "PdslTestFramework.feature",
            parser = PdslTestResourceParser.class,
            lexer = PdslTestResourceLexer.class,
            listener = ClassLevelRecognizerTest.FrameworkSpecificationListenerProvider.class
    )
    @RecognizedBy(
            dslRecognizerLexer = AllGrammarsLexer.class,
            dslRecognizerParser = AllGrammarsParser.class,
            recognizerRule = "polymorphicDslAllRules"
    )
    public void testThatIsNotRecognizedByParentRecognizer_shouldFailToCompile(){}


    public static class FrameworkSpecificationListenerProvider implements Provider<ParseTreeListener> {
        @Override
        public ParseTreeListener get() {
            return new PdslFrameworkSpecificationImpl();
        }
    }
}
