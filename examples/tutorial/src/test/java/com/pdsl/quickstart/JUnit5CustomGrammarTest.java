package com.pdsl.quickstart;

import com.example.DotNavigationLexer;
import com.example.DotNavigationParser;
import com.pdsl.custom.CustomDotNavigationListener;
import com.pdsl.custom.CustomGrammarTest;
import com.pdsl.runners.TestSpecificationFactoryGenerator;
import com.pdsl.specifications.FileDelimitedTestSpecificationFactory;
import com.pdsl.testcases.SingleTestOutputPreorderTestCaseFactory;
import com.pdsl.testcases.TestCaseFactory;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.engine.descriptor.PdslConfigParameter;
import org.junit.jupiter.engine.descriptor.PdslExecutable;
import org.junit.jupiter.engine.descriptor.PdslGeneralInvocationContextProvider;
import org.junit.jupiter.engine.descriptor.PdslTestParameter;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class JUnit5CustomGrammarTest {

    private static final CustomDotNavigationListener SINGLETON = new CustomDotNavigationListener();

    @TestTemplate
    @ExtendWith(MyCustomGrammarExtension.class)
    public void myCustomGrammarTests(PdslExecutable pdslExecutable) {
        pdslExecutable.execute();
    }

    @AfterAll
    public static void afterAll() {
        // After the parser interprets the DOT file we provided it stores the information it finds internally.
        // We retrieve that information and check to see if it is doing what we'd expect
        Map<String, List<String>> navigationPath = SINGLETON.getNavigationPath();
        Map<String, List<String>> adminOnly = SINGLETON.getAdminOnly();
        assert navigationPath.get("\"Sign In\"").size() == 1;
        assert navigationPath.get("Home").containsAll(Set.of("\"Shopping Cart\"", "\"Order History\""));
        assert adminOnly.get("Admin").containsAll(Set.of("\"Payment Data\"", "\"Refund Form\"", "\"Product Editor\""));
        assert adminOnly.get("Admin").size() == 3;
    }

    private static class MyCustomGrammarExtension extends PdslGeneralInvocationContextProvider {
        private static final TestCaseFactory TEST_CASE_FACTORY = new SingleTestOutputPreorderTestCaseFactory.DefaultProvider().get();

        private static final TestSpecificationFactoryGenerator TEST_SPECIFICATION_FACTORY_GENERATOR = new FileDelimitedTestSpecificationFactory.Generator();

        @Override
        public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext extensionContext) {

            return getInvocationContext(
                    PdslConfigParameter.createGeneralPdslConfig(
                            () -> TEST_CASE_FACTORY,
                            () -> TEST_SPECIFICATION_FACTORY_GENERATOR,
                            List.of(
                                    new PdslTestParameter.Builder(() -> SINGLETON, DotNavigationLexer.class, DotNavigationParser.class)
                                            .withIncludedResources(new String[] {"website.dot"})
                                            .withStartRule("dotFile")
                                            .build()
                            )
                    )
                    .withResourceRoot(Paths.get("../../documentation/images/graphviz").toUri())
                    .withRecognizerRule("dotFile")
                    .build()
            ).stream();
        }
    }
}
