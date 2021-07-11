package com.pdsl.framework;

import com.pdsl.gherkin.executors.GherkinTestExecutor;
import com.pdsl.grammars.*;
import com.pdsl.reports.TestRunResults;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.junit.Test;

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

    @Test
    public void testSpecificationFactory_meetsSpecifications() {
        final URL testResources = getClass().getClassLoader()
                .getResource("framework_specifications/TestSpecificationFactory.feature");
        // Arrange
        Set<URL> dslFiles = new HashSet<>();
        dslFiles.add(testResources);
        PolymorphicDslPhraseFilter phraseFilter = new DefaultPolymorphicDslPhraseFilter<TestSpecificationFactoryParser, TestSpecificationFactoryLexer,
                TestSpecificationFactoryParser, TestSpecificationFactoryLexer>(TestSpecificationFactoryParser.class,
                TestSpecificationFactoryLexer.class,
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

    @Test
    public void testResources_meetsSpecifications() {
        final URL testResources = getClass().getClassLoader()
                .getResource("framework_specifications/TestResource.feature");
        // Arrange
        Set<URL> dslFiles = new HashSet<>();
        dslFiles.add(testResources);
        PolymorphicDslPhraseFilter phraseFilter = new DefaultPolymorphicDslPhraseFilter<PdslTestResourceParser, PdslTestResourceLexer,
                PdslTestResourceParser, PdslTestResourceLexer>(PdslTestResourceParser.class,
                PdslTestResourceLexer.class,
                PdslTestResourceParser.class,
                PdslTestResourceLexer.class
        );
        GherkinTestExecutor gherkinTestExecutor = new GherkinTestExecutor(phraseFilter);
        // Act
        TestRunResults results = gherkinTestExecutor.processFilesAndRunTests(dslFiles, new PdslFrameworkSpecificationImpl());
        assertThat(results.failingTestTotal()).isEqualTo(0);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(0);
    }

    @Test
    public void testCaseFactory_meetsSpecifications() {
        final URL testResources = getClass().getClassLoader()
                .getResource("framework_specifications/TestCaseFactory.feature");
        // Arrange
        Set<URL> dslFiles = new HashSet<>();
        dslFiles.add(testResources);
        PolymorphicDslPhraseFilter phraseFilter = new DefaultPolymorphicDslPhraseFilter<TestCaseFactoryParser, TestCaseFactoryLexer,
                TestCaseFactoryParser, TestCaseFactoryLexer>(TestCaseFactoryParser.class,
                TestCaseFactoryLexer.class,
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
