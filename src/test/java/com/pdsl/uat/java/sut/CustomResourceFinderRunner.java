package com.pdsl.uat.java.sut;

import com.pdsl.runners.PdslConfiguration;
import com.pdsl.runners.PdslTest;
import com.pdsl.runners.junit.PdslJUnit4ConfigurableRunner;
import com.pdsl.specifications.FileSystemTestResourceGenerator;
import com.pdsl.specifications.TestResourceFinder;
import com.pdsl.specifications.TestResourceFinderGenerator;
import com.pdsl.uat.GherkinJunitRecognizerTest;
import com.pdsl.uat.PdslConfigurableExecutorTest;
import org.junit.runner.RunWith;

import javax.inject.Provider;
import java.net.URI;
import java.util.Collection;
import java.util.Optional;
import com.pdsl.grammars.AllGrammarsParser;
import com.pdsl.grammars.AllGrammarsLexer;
import com.pdsl.grammars.PdslTestResourceParser;
import com.pdsl.grammars.PdslTestResourceLexer;
@RunWith(PdslJUnit4ConfigurableRunner.class)
@PdslConfiguration(
        specificationFactoryProvider = PdslConfigurableExecutorTest.SpecificationFactoryProvider.class,
        testCaseFactoryProvider = PdslConfigurableExecutorTest.TestCaseFactoryProvider.class,
        resourceFinder = CustomResourceFinderRunner.CustomResourceFinderProvider.class,
        dslRecognizerParser = AllGrammarsParser.class,
        dslRecognizerLexer = AllGrammarsLexer.class,
        recognizerRule = "polymorphicDslAllRules"
)
public class CustomResourceFinderRunner {

    @PdslTest(
            includesResources = "PdslTestFramework.feature",
            parser = PdslTestResourceParser.class,
            lexer = PdslTestResourceLexer.class,
            listener = GherkinJunitRecognizerTest.FrameworkSpecificationListenerProvider.class
    )
    public void customResourceProvider_findsResourceAndPreventsFailure() {}
    public static class CustomResourceFinderProvider implements Provider<TestResourceFinderGenerator> {
        // Resources will only be found with the below generator because we didn't specify a resource root
        private final TestResourceFinderGenerator finder = new FileSystemTestResourceGenerator("src/test/resources/framework_specifications/features");
        @Override
        public TestResourceFinderGenerator get() {
            return finder;
        }
    }
}
