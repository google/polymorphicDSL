package com.pdsl;

import com.pdsl.gherkin.executors.GherkinTestExecutor;
import com.pdsl.grammars.*;
import com.pdsl.reports.TestRunResults;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static com.google.common.truth.Truth.assertThat;

public class FrameworkSpecificationsTest {

    @Test
    public void gherkinPdslTestFramework_meetsTestSpecifications() throws IOException {

        final URL testResources = getClass().getClassLoader()
                .getResource("framework_specifications/PdslTestFramework.feature");
        // Arrange
        Set<URL> dslFiles = new HashSet<>();
        Path tmpDir = Files.createTempDirectory(String.format("pdsl_temp_test-%s", UUID.randomUUID()));
        dslFiles.add(testResources);
        /*
        PolymorphicDslPhraseFilter phraseFilter = new InterpreterBasedPhraseFilter.Builder(Path.of(System.getProperty("user.dir") + "/src/test/resources"),
                "PdslFrameworkSpecification", "com.pdsl.grammars")
                .withGrammarLexer("PdslFrameworkSpecificationLexer")
                .withGrammarLibrary(Path.of(System.getProperty("user.dir") + "/src/test/resources/"))
                .withCodeGenerationDirectory(tmpDir)
                .build();
         */
        PolymorphicDslPhraseFilter phraseFilter = new DefaultPolymorphicDslPhraseFilter<PdslFrameworkSpecificationParser, PdslFrameworkSpecificationLexer,
                PdslFrameworkSpecificationParser, PdslFrameworkSpecificationLexer>(PdslFrameworkSpecificationParser.class,
                PdslFrameworkSpecificationLexer.class,
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
