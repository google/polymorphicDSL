package com.pdsl.component;

import com.pdsl.gherkin.executors.GherkinTestExecutor;
import com.pdsl.grammars.TestSpecificationFactoryLexer;
import com.pdsl.grammars.TestSpecificationFactoryParser;
import com.pdsl.grammars.TestSpecificationFactoryParserListenerImpl;
import com.pdsl.reports.TestRunResults;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.net.URL;
import java.util.*;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.fail;

@RunWith(JUnit4.class)
public class FrameworkTestSpecification {

    @Test
    public void testSpecificationFactory_meetsSpecifications() {
        final URL testResources = getClass().getClassLoader()
                .getResource("framework_specifications/features/TestSpecificationFactory.feature");
        // Arrange
        Set<URL> dslFiles = new HashSet<>();
        dslFiles.add(testResources);
        PolymorphicDslPhraseFilter phraseFilter = new DefaultPolymorphicDslPhraseFilter(
                TestSpecificationFactoryParser.class,
                TestSpecificationFactoryLexer.class
        );
        GherkinTestExecutor gherkinTestExecutor = new GherkinTestExecutor(phraseFilter);
        // Act
        TestRunResults results = gherkinTestExecutor.processFilesAndRunTests(dslFiles, new TestSpecificationFactoryParserListenerImpl());
        assertThat(results.failingTestTotal()).isEqualTo(0);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(0);
        assertThat(results.passingPhraseTotal()).isEqualTo(62);
    }
}
