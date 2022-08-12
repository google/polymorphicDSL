package com.pdsl.gherkin.models;

import com.pdsl.gherkin.PdslGherkinInterpreterImpl;
import com.pdsl.gherkin.PdslGherkinListenerImpl;
import com.pdsl.gherkin.PdslGherkinRecognizer;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;

public class I18nEmoji {
    private static final String resourcePath = "src/test/resources/testdata/good/";
    private static final PdslGherkinRecognizer transformer = new PdslGherkinInterpreterImpl();
    private static final PdslGherkinListenerImpl listener = new PdslGherkinListenerImpl();
    private final List<GherkinStep> stepList;

    public I18nEmoji() throws IOException {
        // Arrange
        Optional<GherkinFeature> featureOptional = transformer.interpretGherkinFile(Path.of(resourcePath + "i18n_emoji.feature").toUri(), listener);
        // Act
        assertThat(featureOptional.isPresent()).isTrue();
        GherkinFeature feature = featureOptional.get();
        stepList = feature.getOptionalGherkinScenarios().get().get(0).getStepsList().get();
    }

    @Test
    public void emojiSteps_containFullTextContent() {
        assertThat(stepList.get(0).getStepContent().getRawString()).contains("    \uD83D\uDE10\uD83C\uDFB8");
    }
}
