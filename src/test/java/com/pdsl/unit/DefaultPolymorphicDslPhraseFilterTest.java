package com.pdsl.unit;

import com.pdsl.gherkin.executors.GherkinTestExecutor;
import com.pdsl.grammars.PdslFrameworkSpecificationImpl;
import com.pdsl.grammars.PdslTestResourceLexer;
import com.pdsl.grammars.PdslTestResourceParser;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

public class DefaultPolymorphicDslPhraseFilterTest {

    // Make a mock lexer, override getAllTokens
    // Make a mock parser
    @Test
    public void testResources_meetsSpecifications() throws URISyntaxException {
        final URL testResources = getClass().getClassLoader()
                .getResource("framework_specifications/features/TestResource.feature");
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(testResources.toURI());
        PolymorphicDslPhraseFilter phraseFilter =
                new DefaultPolymorphicDslPhraseFilter (
                        PdslTestResourceParser.class, PdslTestResourceLexer.class);
        GherkinTestExecutor gherkinTestExecutor = new GherkinTestExecutor(phraseFilter);
        // Act
        MetadataTestRunResults results = gherkinTestExecutor.runTestsWithMetadata(dslFiles, new PdslFrameworkSpecificationImpl(), "Component");
        assertThat(results.failingTestTotal()).isEqualTo(0);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(0);
        assertThat(results.passingTestTotal()).isEqualTo(results.getTestResults().size());
    }
}
