package com.pdsl.uat;

import com.pdsl.grammars.*;
import com.pdsl.runners.PdslGherkinApplication;
import com.pdsl.runners.junit.PdslGherkinJUnit4Runner;
import com.pdsl.runners.PdslTest;
import com.pdsl.runners.RecognizedBy;
import org.antlr.v4.runtime.tree.*;
import org.junit.runner.RunWith;

import javax.inject.Provider;

@RunWith(PdslGherkinJUnit4Runner.class)
@PdslGherkinApplication(
        resourceRoot = "src/test/resources/framework_specifications/features",
        dslRecognizerParser = MinimalParser.class,
        dslRecognizerLexer = MinimalLexer.class,
        recognizerRule = "minimal"
)
public class VisitorTest {
    @PdslTest(
            includesResources = "PdslTestFramework.feature",
            parser = PdslTestResourceParser.class,
            lexer = PdslTestResourceLexer.class,
            visitor = VisitorTest.FrameworkSpecificationVisitorProvider.class
    )
    @RecognizedBy(
            dslRecognizerLexer = AllGrammarsLexer.class,
            dslRecognizerParser = AllGrammarsParser.class,
            recognizerRule = "polymorphicDslAllRules"
    )
    public void testThatIsNotRecognizedByParentRecognizer_shouldFailToCompile(){}


    public static class FrameworkSpecificationVisitorProvider implements Provider<ParseTreeVisitor<Integer>> {
        @Override
        public ParseTreeVisitor<Integer> get() {
            return new Visitor();
        }

        private static class Visitor implements PdslTestResourceParserVisitor<Integer> {
            private int visited = 0;
            @Override
            public Integer visitPolymorphicDslAllRules(PdslTestResourceParser.PolymorphicDslAllRulesContext ctx) {
                return ++visited;
            }

            @Override
            public Integer visitDocstring(PdslTestResourceParser.DocstringContext ctx) {
                return ++visited;
            }

            @Override
            public Integer visitGivenTheTestResource(PdslTestResourceParser.GivenTheTestResourceContext ctx) {
                return ++visited;
            }

            @Override
            public Integer visitGivenTheRawResource(PdslTestResourceParser.GivenTheRawResourceContext ctx) {
                return ++visited;
            }

            @Override
            public Integer visitTestResourceValidity(PdslTestResourceParser.TestResourceValidityContext ctx) {
                return ++visited;
            }

            @Override
            public Integer visit(ParseTree tree) {
                int result = tree.accept(this);
                if (result <= 0) {
                    throw new AssertionError("The visitor did not visit any nodes!");
                }
                return result;
            }

            @Override
            public Integer visitChildren(RuleNode node) {
                return ++visited;
            }

            @Override
            public Integer visitTerminal(TerminalNode node) {
                return ++visited;
            }

            @Override
            public Integer visitErrorNode(ErrorNode node) {
                return ++visited;
            }
        }
    }
}
