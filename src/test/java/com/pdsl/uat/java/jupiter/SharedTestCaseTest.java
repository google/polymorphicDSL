package com.pdsl.uat.java.jupiter;

import com.pdsl.executors.InterpreterObj;
import com.pdsl.grammars.*;
import com.pdsl.interpreter.InterpreterOne;
import com.pdsl.interpreter.InterpreterTwo;
import com.pdsl.runners.InterpreterParam;
import com.pdsl.runners.PdslTestParams;
import com.pdsl.runners.RecognizerParams;
import com.pdsl.testcases.SharedTestSuite;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.engine.descriptor.*;

import java.nio.file.Paths;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class SharedTestCaseTest {

    private int totalRunTests = 0;
    @TestTemplate
    @ExtendWith(PdslGherkinExtension.class)
    public void pdslGherkinTestFrameworkResources(PdslExecutable executable) {
        executable.execute();
        totalRunTests++;
        assert(totalRunTests == 1);
    }

    @TestTemplate
    @ExtendWith(InterpreterOneExtension.class)
    public void interpreterOneTest(PdslExecutable executable) {
        executable.execute();
    }


    private static class PdslGherkinExtension extends PdslSharedInvocationContextProvider {
        @Override
        public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
            return getInvocationContext(PdslConfigParameter.createGherkinPdslConfig(
                            List.of(
                                    new PdslTestParameter.Builder(List.of(
                                            new Interpreter(InterpreterOneLexer.class, InterpreterOneParser.class,
                                                    new InterpreterObj(
                                                            new InterpreterOne.InterpreterOneListenerProvider().get())),
                                            new Interpreter(InterpreterTwoLexer.class, InterpreterTwoParser.class,
                                                    new InterpreterObj(new InterpreterTwo.InterpreterTwoListenerProvider().get()))
                                            )
                                    )
                                            .withStartRule("polymorphicDslAllRules")
                                            .withIncludedResources(new String[] {"InterpreterAll.feature"})
                                            .withRecognizer(InterpreterAllLexer.class, InterpreterAllParser.class)
                                            .withRecognizerRule("polymorphicDslAllRulesAll")
                                            .build()
                            )
                    )
                    .withApplicationName("Polymorphic DSL Framework")
                    .withContext("User Acceptance Test")
                    .withResourceRoot(Paths.get("src/test/resources/framework_specifications/features/interpreter/").toUri())
                    .withRecognizerRule("polymorphicDslAllRuless")
                    .build())
                    .stream();
        }
    }

    private static class InterpreterOneExtension extends PdslSharedInvocationContextProvider {
        @Override
        public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
            return getInvocationContext(PdslConfigParameter.createGherkinPdslConfig(
                            List.of(
                                    new PdslTestParameter.Builder(List.of(
                                            new Interpreter(InterpreterOneLexer.class, InterpreterOneParser.class,
                                                    new InterpreterObj(
                                                            new InterpreterOne.InterpreterOneListenerProvider().get()))
                                        )
                                    )
                                            .withStartRule("polymorphicDslAllRules")
                                            .withIncludedResources(new String[] {"InterpreterAll.feature"})
                                            .withRecognizer(InterpreterAllLexer.class, InterpreterAllParser.class)
                                            .withRecognizerRule("polymorphicDslAllRulesAll")
                                            .build()
                            )
                    )
                    .withApplicationName("Polymorphic DSL Framework")
                    .withContext("User Acceptance Test")
                    .withResourceRoot(Paths.get("src/test/resources/framework_specifications/features/interpreter/").toUri())
                    .withRecognizerRule("polymorphicDslAllRuless")
                    .build())
                    .stream();
        }
    }

}
