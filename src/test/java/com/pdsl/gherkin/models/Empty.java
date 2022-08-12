package com.pdsl.gherkin.models;

import com.pdsl.gherkin.PdslGherkinInterpreterImpl;
import com.pdsl.gherkin.PdslGherkinListenerImpl;
import com.pdsl.gherkin.PdslGherkinRecognizer;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;

public class Empty {
    private static final String resourcePath = "src/test/resources/testdata/good/";
    private static final PdslGherkinRecognizer transformer = new PdslGherkinInterpreterImpl();
    private static final PdslGherkinListenerImpl listener = new PdslGherkinListenerImpl();

    @Test
    public void parseEmptyFile_returnsEmptyResult() throws IOException {
        // Arrange, Act
        Optional<GherkinFeature> featureOptional = transformer.interpretGherkinFile(Path.of(resourcePath + "empty.feature").toUri(), listener);
        // Assert
        assertThat(featureOptional.isPresent()).isFalse();
    }
}
