package com.pdsl.quickstart;

import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.engine.descriptor.PdslConfigParameter;
import org.junit.jupiter.engine.descriptor.PdslExecutable;
import org.junit.jupiter.engine.descriptor.PdslGherkinInvocationContextProvider;
import org.junit.jupiter.engine.descriptor.PdslTestParameter;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import com.example.MyFirstLexer;
import com.example.MyFirstParser;


public class JUnit5Quickstart {

    @TestTemplate
    @ExtendWith(MyExtension.class)
    public void gherkinExample(PdslExecutable pdslExecutable) {
        pdslExecutable.execute();
    }

    private static class MyExtension extends PdslGherkinInvocationContextProvider {

        private static final ParseTreeListener SINGLETON = new MyFirstPdslListener();
        @Override
        public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
            return getInvocationContext(PdslConfigParameter.createGherkinPdslConfig(
                    List.of(
                        new PdslTestParameter.Builder(
                                () -> SINGLETON,
                            MyFirstLexer.class, MyFirstParser.class
                        )
                                .withIncludedResources(new String[] {"PdslQuickstart.feature"})
                                .build())
                    )
                    .withResourceRoot(Paths.get("src/test/resources/features").toUri())
                    .withRecognizerRule("polymorphicDslAllRules")
                    .build()
            ).stream();
        }
    }

}