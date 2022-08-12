package com.pdsl.component;

import com.pdsl.gherkin.executors.GherkinTestExecutor;
import com.pdsl.grammars.PdslTestRunResultsMetaLexer;
import com.pdsl.grammars.PdslTestRunResultsMetaParser;
import com.pdsl.grammars.PdslTestRunResultsMetaParserListenerImpl;
import com.pdsl.reports.TestRunResults;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.junit.Ignore;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

public class FrameworkTestRunResults {

    @Test
    @Ignore("A bug in ANTLR4 causes the wrong parser rule to be invoked for a different rules token")
    public void testRunResults_meetsSpecifications() throws URISyntaxException {
        final URL testResources = getClass().getClassLoader()
                .getResource("framework_specifications/features/TestRunResults.feature");
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(testResources.toURI());
        PolymorphicDslPhraseFilter phraseFilter = new DefaultPolymorphicDslPhraseFilter(
                PdslTestRunResultsMetaParser.class,
                PdslTestRunResultsMetaLexer.class,
                PdslTestRunResultsMetaParser.class,
                PdslTestRunResultsMetaLexer.class,
                "polymorphicDslAllRules", "polymorphicDslAllRules"
        );
        GherkinTestExecutor gherkinTestExecutor = new GherkinTestExecutor(phraseFilter);
        // Act
        TestRunResults results = gherkinTestExecutor.processFilesAndRunTests(dslFiles, new PdslTestRunResultsMetaParserListenerImpl());
        assertThat(results.failingTestTotal()).isEqualTo(0);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(0);
        assertThat(results.totalPhrases()).isGreaterThan(0);
    }
}
