package com.pdsl.component;

import com.pdsl.gherkin.executors.GherkinTestExecutor;
import com.pdsl.grammars.TestExecutorLexer;
import com.pdsl.grammars.TestExecutorMetaParser;
import com.pdsl.grammars.TestExecutorMetaParserListenerImpl;
import com.pdsl.reports.TestRunResults;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.junit.Test;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

public class FrameworkTestExecutor {

    @Test
    public void testExecutor_meetsSpecifications() {
        final URL testResources = getClass().getClassLoader()
                .getResource("framework_specifications/features/TestExecutor.feature");
        // Arrange
        Set<URL> dslFiles = new HashSet<>();
        dslFiles.add(testResources);
        PolymorphicDslPhraseFilter phraseFilter = new DefaultPolymorphicDslPhraseFilter(
                TestExecutorMetaParser.class,
                TestExecutorLexer.class
        );
        GherkinTestExecutor gherkinTestExecutor = new GherkinTestExecutor(phraseFilter);
        // Act
        TestRunResults results = gherkinTestExecutor.processFilesAndRunTests(dslFiles, new TestExecutorMetaParserListenerImpl());
        assertThat(results.failingTestTotal()).isEqualTo(0);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(0);
        assertThat(results.totalPhrases()).isGreaterThan(0);
    }
}
