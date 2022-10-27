package com.pdsl.parameters;

import com.example.*;
import com.pdsl.runners.PdslConfiguration;
import com.pdsl.runners.PdslGherkinApplication;
import com.pdsl.runners.PdslTest;
import com.pdsl.runners.RecognizedBy;
import com.pdsl.runners.junit.PdslGherkinJUnit4Runner;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.hamcrest.core.Is;
import org.junit.runner.RunWith;

import javax.inject.Provider;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

/**
 * Provides a tutorial example for grammars made to demos made to explain how to extract parameters.
 */
@RunWith(PdslGherkinJUnit4Runner.class)
@PdslGherkinApplication(
        resourceRoot = "src/test/resources/features"
)
public class ParametersTest {

    @PdslTest(
            includesResources = "ParametersExample.feature",
            tags = "@Simplistic",
            parser = SimpleParameterParser.class,
            lexer = SimpleParameterLexer.class,
            listener = SimpleProvider.class,
            startRule = "parameterExample"
    )
    @RecognizedBy(
            dslRecognizerParser = SimpleParameterParser.class,
            dslRecognizerLexer = SimpleParameterLexer.class,
            recognizerRule = "parameterExample"
    )
    public void simpleLexer_extractsAndPrintsParameters(){}

    public static class SimpleProvider implements Provider<ParseTreeListener> {

        @Override
        public ParseTreeListener get() { return new SimpleListener();}
        private static class SimpleListener extends SimpleParameterParserBaseListener {
            @Override
            public void enterParameterExample(SimpleParameterParser.ParameterExampleContext ctx) {
                // You can directly reference tokens in a parser rule
                System.out.println(ctx.NUMBER().getText());
            }
            @Override
            public void visitErrorNode(ErrorNode errorNode) {
                throw new IllegalStateException("An error node was found!\n" + errorNode.getText());
            }
        }
    }

    @PdslTest(
            includesResources = "ParametersExample.feature",
            tags = "@MultipleMatches",
            parser = SimpleParameterParser.class,
            lexer = SimpleParameterLexer.class,
            listener = MultiProvider.class,
            startRule = "multiExample"
    )
    @RecognizedBy(
            dslRecognizerParser = SimpleParameterParser.class,
            dslRecognizerLexer = SimpleParameterLexer.class,
            recognizerRule = "multiExample"
    )
    public void simpleLexer_extractsAndPrintsMultipleParameters(){}

    public static class MultiProvider implements Provider<ParseTreeListener> {

        @Override
        public ParseTreeListener get() { return new MultiListener(); }
        private static class MultiListener extends SimpleParameterParserBaseListener {
            @Override
            public void enterMultiExample(SimpleParameterParser.MultiExampleContext ctx) {
                // If you match more than one rule/token then they are available as a list
                ctx.parameterExample().stream().forEach(paramContext -> System.out.println(paramContext.NUMBER().getText()));
            }
            @Override
            public void visitErrorNode(ErrorNode errorNode) {
                throw new IllegalStateException("An error node was found!\n" + errorNode.getText());
            }
        }
    }

    @PdslTest(
            includesResources = "PowerfulParametersExample.feature",
            parser = PowerfulParameterParser.class,
            lexer = PowerfulParameterLexer.class,
            listener = IslandListenerProvider.class
    )
    @RecognizedBy(
            dslRecognizerParser = PowerfulParameterParser.class,
            dslRecognizerLexer = PowerfulParameterLexer.class,
            recognizerRule = "polymorphicDslAllRules"
    )
    public void powerfulParser_extractsAndPrintsParameters(){}

    public static class IslandListenerProvider implements Provider<ParseTreeListener> {

        @Override
        public ParseTreeListener get() {
            return new IslandListener();
        }
        private static class IslandListener extends PowerfulParameterParserBaseListener {

            @Override
            public void enterQuotedParameter(PowerfulParameterParser.QuotedParameterContext ctx) {
                System.out.println(ctx.BODY().getText());
            }
            @Override
            public void enterDocstringParameter(PowerfulParameterParser.DocstringParameterContext ctx) {
                System.out.println(ctx.DOCSTRING().stream().map(TerminalNode::getText).collect(Collectors.joining()));
            }

            @Override
            public void visitErrorNode(ErrorNode errorNode) {
                throw new IllegalStateException("An error node was found!\n" + errorNode.getText());
            }
        }
    }
}
