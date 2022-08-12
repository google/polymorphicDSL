package com.pdsl.component;

import com.pdsl.gherkin.executors.GherkinTestExecutor;
import com.pdsl.grammars.PdslFrameworkSpecificationImpl;
import com.pdsl.grammars.PdslTestResourceLexer;
import com.pdsl.grammars.PdslTestResourceParser;
import com.pdsl.reports.TestRunResults;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

public class FrameworkTestResource {
    @Test
    public void testResources_meetsSpecifications() throws URISyntaxException {
        final URL testResources = getClass().getClassLoader()
                .getResource("framework_specifications/features/TestResource.feature");
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(testResources.toURI());
        PolymorphicDslPhraseFilter phraseFilter =
                new DefaultPolymorphicDslPhraseFilter(
                        PdslTestResourceParser.class, PdslTestResourceLexer.class,
                        "polymorphicDslAllRules");
        GherkinTestExecutor gherkinTestExecutor = new GherkinTestExecutor(phraseFilter);
        // Act
        TestRunResults results = gherkinTestExecutor.processFilesAndRunTests(dslFiles, new PdslFrameworkSpecificationImpl());
        assertThat(results.failingTestTotal()).isEqualTo(0);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(0);
    }
}
