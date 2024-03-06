package com.pdsl.uat.java.jupiter;

import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.engine.descriptor.*;
import com.pdsl.grammars.AllGrammarsParser;
import com.pdsl.grammars.AllGrammarsLexer;
import com.pdsl.grammars.AllGrammarsParserBaseListener;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class JupiterTagFilteringTest {

    private int totalRunTests = 0;
    @TestTemplate
    @ExtendWith(PdslExtension.class)
    public void pdslGherkinTestFrameworkResources(PdslExecutable executable) {

        executable.execute();
        totalRunTests++;
        assert(totalRunTests == 1);
    }

    private static final Supplier<ParseTreeListener> parseTreeListenerSupplier = () -> new AllGrammarsParserBaseListener();

    private static class PdslExtension extends PdslGherkinInvocationContextProvider {

        @Override
        public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
            return getInvocationContext(PdslConfigParameter.createGherkinPdslConfig(
                            List.of(
                                    new PdslTestParameter.Builder(parseTreeListenerSupplier,
                                            AllGrammarsLexer.class, AllGrammarsParser.class)
                                            .withTagExpression("@comment_tag#2")
                                            .withIncludedResources(new String[] {"tags.feature"})
                                            .build()
                            )
                    )
                    .withApplicationName("Polymorphic DSL Framework")
                    .withContext("User Acceptance Test")
                    .withResourceRoot(Paths.get("src/test/resources/testdata/good").toUri())
                    .withRecognizerRule("polymorphicDslAllRules")
                    .build())
                    .stream();
        }
    }
}
