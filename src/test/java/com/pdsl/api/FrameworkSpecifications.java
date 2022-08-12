package com.pdsl.api;

import com.pdsl.gherkin.executors.GherkinTestExecutor;
import com.pdsl.grammars.*;
import com.pdsl.reports.TestRunResults;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static com.google.common.truth.Truth.assertThat;

public class FrameworkSpecifications {

    @Test
    public void gherkinPdslTestFramework_meetsTestSpecifications() throws IOException, URISyntaxException {

        final URI testResources = getClass().getClassLoader()
                .getResource("framework_specifications/features/PdslTestFramework.feature").toURI();
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        Path tmpDir = Files.createTempDirectory(String.format("pdsl_temp_test-%s", UUID.randomUUID()));
        dslFiles.add(testResources);
        PolymorphicDslPhraseFilter phraseFilter = new DefaultPolymorphicDslPhraseFilter(
                PdslFrameworkSpecificationParser.class,
                PdslFrameworkSpecificationLexer.class
        );
        GherkinTestExecutor gherkinTestExecutor = new GherkinTestExecutor(phraseFilter);
        // Act
        TestRunResults results = gherkinTestExecutor.processFilesAndRunTests(dslFiles, new PdslFrameworkSpecificationImpl());
        assertThat(results.failingTestTotal()).isEqualTo(0);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(0);
    }
}
