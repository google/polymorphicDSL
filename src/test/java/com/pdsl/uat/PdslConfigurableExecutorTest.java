package com.pdsl.uat;

import com.pdsl.grammars.*;
import com.pdsl.runners.PdslConfiguration;
import com.pdsl.runners.TestSpecificationFactoryGenerator;
import com.pdsl.runners.junit.PdslJUnit4ConfigurableRunner;
import com.pdsl.runners.PdslTest;
import com.pdsl.specifications.LineDelimitedTestSpecificationFactory;
import com.pdsl.testcases.SingleTestOutputPreorderTestCaseFactory;
import com.pdsl.testcases.TestCaseFactory;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.junit.runner.RunWith;

import javax.inject.Provider;

@RunWith(PdslJUnit4ConfigurableRunner.class)
@PdslConfiguration(
        resourceRoot = "src/test/resources/framework_specifications/features",
        dslRecognizerParser = AllGrammarsParser.class,
        dslRecognizerLexer = AllGrammarsLexer.class,
        recognizerRule = "polymorphicDslAllRules",
        specificationFactoryProvider = PdslConfigurableExecutorTest.SpecificationFactoryProvider.class,
        testCaseFactoryProvider = PdslConfigurableExecutorTest.TestCaseFactoryProvider.class
)
public class PdslConfigurableExecutorTest {

    @PdslTest(
            includesResources = "PdslTestFramework.feature",
            parser = PdslTestResourceParser.class,
            lexer = PdslTestResourceLexer.class,
            listener = GherkinJunitRecognizerTest.FrameworkSpecificationListenerProvider.class
    )
    public void  skipUnrecognizedWithMissingSentences_shouldFail(){}

    @PdslTest(
            includesResources = "PdslTestFramework.feature",
            parser = PdslTestResourceParser.class,
            lexer = PdslTestResourceLexer.class,
            visitor = PdslConfigurableExecutorTest.FrameworkSpecificationVisitorProvider.class
    )
    public void  visitorSkipUnrecognizedWithMissingSentences_shouldFail(){}

    public static class FrameworkSpecificationVisitorProvider implements Provider<ParseTreeVisitor<Void>> {
        @Override
        public ParseTreeVisitor<Void> get() {
            return new PdslTestResourceParserBaseVisitor<>();
        }
    }
    public static class FrameworkSpecificationListenerProvider implements Provider<ParseTreeListener> {
        @Override
        public ParseTreeListener get() {
            return new PdslFrameworkSpecificationImpl();
        }
    }

    public static class SpecificationFactoryProvider implements Provider<TestSpecificationFactoryGenerator> {
        private static final TestSpecificationFactoryGenerator INSTANCE = new LineDelimitedTestSpecificationFactory.Generator();
        @Override
        public TestSpecificationFactoryGenerator get() {
            return INSTANCE;
        }
    }

    public static class TestCaseFactoryProvider implements Provider<TestCaseFactory> {
        private static final TestCaseFactory INSTANCE = new SingleTestOutputPreorderTestCaseFactory();
        @Override
        public TestCaseFactory get() {
            return INSTANCE;
        }
    }
}
