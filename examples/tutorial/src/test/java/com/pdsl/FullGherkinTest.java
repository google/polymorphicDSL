package com.pdsl;

import com.pdsl.scaling.ReportConstants;
import com.pdsl.scaling.integration.RestaurantIntegrationTest;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.engine.descriptor.PdslConfigParameter;
import org.junit.jupiter.engine.descriptor.PdslExecutable;
import org.junit.jupiter.engine.descriptor.PdslGherkinInvocationContextProvider;
import org.junit.jupiter.engine.descriptor.PdslTestParameter;
import com.example.FullGherkinParserBaseVisitor;
import com.example.FullGherkinParser;
import com.example.FullGherkinLexer;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class FullGherkinTest {

    @TestTemplate
    @ExtendWith(FullGherkinIntegrationContext.class)
    public void allGherkinFeatures_canBeParsed(PdslExecutable pdslExecutable) {
        pdslExecutable.execute();
    }

    private static class FullGherkinIntegrationContext extends PdslGherkinInvocationContextProvider {
        private static final ParseTreeVisitor<Void> UNIT_SINGLETON = new FullGherkinParserBaseVisitor<Void>();

        @Override
        public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(
                ExtensionContext context) {
            return getInvocationContext(PdslConfigParameter.createGherkinPdslConfig(
                            List.of(new PdslTestParameter.Builder(
                                    FullGherkinLexer.class,
                                    FullGherkinParser.class,
                                    () -> UNIT_SINGLETON
                            )
                                    .withIncludedResources(new String[] {"FullGherkinExample.feature"}).build())
                    )
                    .withContext("Unit")
                    .withDslRecognizerLexer(FullGherkinLexer.class)
                    .withDslRecognizerParser(FullGherkinParser.class)
                    .withResourceRoot(Paths.get("./src/test/resources/features").toUri())
                    .withApplicationName(ReportConstants.getApplicationName())
                    .withRecognizerRule("polymorphicDslAllRules")
                    .build()).stream();
        }
    }
}
