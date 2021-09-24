package com.pdsl.component;

import com.pdsl.gherkin.executors.GherkinTestExecutor;
import com.pdsl.grammars.TestCaseFactoryLexer;
import com.pdsl.grammars.TestCaseFactoryParser;
import com.pdsl.grammars.TestCaseFactoryParserListenerImpl;
import com.pdsl.reports.TestRunResults;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.junit.Test;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

public class FrameworkTestCase {

    @Test
    public void testCaseFactory_meetsSpecifications() {
        final URL testResources = getClass().getClassLoader()
                .getResource("framework_specifications/features/TestCaseFactory.feature");
        // Arrange
        Set<URL> dslFiles = new HashSet<>();
        dslFiles.add(testResources);
        PolymorphicDslPhraseFilter phraseFilter = new DefaultPolymorphicDslPhraseFilter(
                TestCaseFactoryParser.class,
                TestCaseFactoryLexer.class
        );
        GherkinTestExecutor gherkinTestExecutor = new GherkinTestExecutor(phraseFilter);
        // Act
        TestRunResults results = gherkinTestExecutor.processFilesAndRunTests(dslFiles, new TestCaseFactoryParserListenerImpl());
        assertThat(results.failingTestTotal()).isEqualTo(0);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(0);
        assertThat(results.totalPhrases()).isGreaterThan(0);
    }
}
