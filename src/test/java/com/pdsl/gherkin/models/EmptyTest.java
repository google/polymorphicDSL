package com.pdsl.gherkin.models;

import com.pdsl.gherkin.PdslGherkinListenerImpl;
import com.pdsl.gherkin.PdslGherkinInterpreter;
import com.pdsl.gherkin.PdslGherkinInterpreterImpl;
import org.junit.Test;

import java.io.IOException;
import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;

public class EmptyTest {
    private static final String resourcePath = "src/test/resources/testdata/good/";
    private static final PdslGherkinInterpreter transformer = new PdslGherkinInterpreterImpl();
    private static final PdslGherkinListenerImpl listener = new PdslGherkinListenerImpl();

    @Test
    public void parseEmptyFile_returnsEmptyResult() throws IOException {
        // Arrange, Act
        Optional<GherkinFeature> featureOptional = transformer.interpretGherkinFile(resourcePath + "empty.feature", listener);
        // Assert
        assertThat(featureOptional.isPresent()).isFalse();
    }
}
