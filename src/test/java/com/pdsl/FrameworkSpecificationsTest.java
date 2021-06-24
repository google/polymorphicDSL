package com.pdsl;

import com.pdsl.gherkin.executors.GherkinTestExecutor;
import com.pdsl.grammars.*;
import com.pdsl.reports.TestRunResults;
import org.junit.Test;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

public class FrameworkSpecificationsTest {

    @Test
    public void gherkinPdslTestFramework_meetsTestSpecifications() {

        final String absolutePathValid =
                new File(getClass().getClassLoader()
                        .getResource("framework_specifications/PdslTestFramework.feature")
                        .getFile()).getAbsolutePath();
        // Arrange
        Set<String> dslFiles = new HashSet<>();
        dslFiles.add(absolutePathValid);
        GherkinTestExecutor gherkinTestExecutor = new GherkinTestExecutor(PdslFrameworkSpecificationParser.class,
                PdslFrameworkSpecificationLexer.class, PdslFrameworkSpecificationParser.class,
                PdslFrameworkSpecificationLexer.class);
        // Act
        TestRunResults results = gherkinTestExecutor.processFilesAndRunTests(dslFiles, new PdslFrameworkSpecificationImpl());
        assertThat(results.failingTestTotal()).isEqualTo(0);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(0);
    }
}
