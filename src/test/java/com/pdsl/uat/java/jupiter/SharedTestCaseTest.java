package com.pdsl.uat.java.jupiter;

import com.pdsl.executors.InterpreterObj;
import com.pdsl.grammars.*;
import com.pdsl.interpreter.InterpreterOne;
import com.pdsl.interpreter.InterpreterTwo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.engine.descriptor.*;

import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.List;
import java.util.stream.Stream;

public class SharedTestCaseTest {

    private static int totalRunTests = 0;
    private static int totalRunTestsFilteredByTag = 0;

    @TestTemplate
    @ExtendWith(PdslGherkinExtension.class)
    public void pdslGherkinTestFrameworkResources(PdslExecutable executable) {
        executable.execute();
        totalRunTests++;
    }

    @TestTemplate
    @ExtendWith(InterpreterOneExtension.class)
    public void interpreterOneTest(PdslExecutable executable) {
        executable.execute();
    }

    @TestTemplate
    @ExtendWith(PdslGherkinExtensionWithTags.class)
    public void PdslGherkinExtensionWithTagsTest(PdslExecutable executable) {
        executable.execute();
        ++totalRunTestsFilteredByTag;
    }

    @AfterAll
    public static void validateNumberOfTestInvocations(){
        assert(totalRunTests == 7);
        assert(totalRunTestsFilteredByTag == 2);
    }

    private static class PdslGherkinExtensionWithTags extends PdslSharedInvocationContextProvider {
        @Override
        public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
            return getInvocationContext(PdslConfigParameter.createGherkinPdslConfig(
                            List.of(
                                    new PdslTestParameter.Builder(
                                            List.of(
                                            new Interpreter(InterpreterOneLexer.class, InterpreterOneParser.class,
                                                    new InterpreterObj(
                                                            new InterpreterOne.InterpreterOneListenerProvider().get())),
                                            new Interpreter(InterpreterTwoLexer.class, InterpreterTwoParser.class,
                                                    new InterpreterObj(new InterpreterTwo.InterpreterTwoListenerProvider().get()))
                                            )
                                    )
                                            .withStartRule("polymorphicDslAllRules")
                                            .withTagExpression("@Tag4")
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
                    .withDslRecognizerParser(InterpreterAllParser.class)
                    .withDslRecognizerLexer(InterpreterAllLexer.class)
                    .build())
                    .stream();
        }
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
                    .withDslRecognizerParser(InterpreterAllParser.class)
                    .withDslRecognizerLexer(InterpreterAllLexer.class)
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
                    .withDslRecognizerParser(InterpreterAllParser.class)
                    .withDslRecognizerLexer(InterpreterAllLexer.class)
                    .build())
                    .stream();
        }
    }

}
