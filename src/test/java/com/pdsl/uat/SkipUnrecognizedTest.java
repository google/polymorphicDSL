package com.pdsl.uat;

import com.pdsl.grammars.*;
import com.pdsl.junit.PdslGherkinApplication;
import com.pdsl.junit.PdslGherkinJUnit4Runner;
import com.pdsl.junit.PdslTest;
import com.pdsl.junit.RecognizedBy;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.junit.Ignore;
import org.junit.runner.RunWith;

import javax.inject.Provider;

@RunWith(PdslGherkinJUnit4Runner.class)
@PdslGherkinApplication(
        resourceRoot = "src/test/resources/framework_specifications/features"
)
public class SkipUnrecognizedTest {

    @Ignore // This test is supposed to fail on purpose. Use for troubleshooting.
    @PdslTest(
            includesResources = "PdslTestFramework.feature",
            parser = PdslTestResourceParser.class,
            lexer = PdslTestResourceLexer.class,
            listener = SkipUnrecognizedTest.FrameworkSpecificationListenerProvider.class,
            skipUnrecognized = false // Test should fail to be recognized
    )
    public void skipUnrecognized_shouldFailWhenTestResouceIsUnrecognized(){}

    // Ordinarily skipUnrecognized would cause this test to fail, but since a @RecognizedBy is provided it will
    // instead just match what it can
    //TODO: Get a parser that successfully creates a test specification
    @PdslTest(
            includesResources = "PdslTestFramework.feature",
            parser = PdslTestRunResultsMetaParser.class,
            lexer = PdslTestRunResultsMetaLexer.class,
            listener = SkipUnrecognizedTest.FrameworkSpecificationListenerProvider.class,
            skipUnrecognized = false // Should be ignored since @RecognizedBy is provided
    )
    @RecognizedBy(
            dslRecognizerParser = AllGrammarsParser.class,
            dslRecognizerLexer = AllGrammarsLexer.class,
            recognizerRule = "polymorphicDslAllRules"

    )
    public void skipUnrecognized_shouldIsIgnoredWhenRecognizedByIsProvided(){}

    public static class FrameworkSpecificationListenerProvider implements Provider<ParseTreeListener> {
        @Override
        public ParseTreeListener get() {
            return new PdslFrameworkSpecificationImpl();
        }
    }
}
