package com.google.pdsl.gherkin.models;

import com.google.pdsl.gherkin.PdslGherkinInterpreter;
import com.google.pdsl.gherkin.PdslGherkinInterpreterImpl;
import com.google.pdsl.gherkin.PdslGherkinListenerImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;

public class I18nEmojiTest {
    private static final String resourcePath = "src/test/resources/testdata/good/";
    private static final PdslGherkinInterpreter transformer = new PdslGherkinInterpreterImpl();
    private static final PdslGherkinListenerImpl listener = new PdslGherkinListenerImpl();
    private final List<GherkinStep> stepList;

    public I18nEmojiTest() throws IOException {
        // Arrange
        Optional<GherkinFeature> featureOptional = transformer.interpretGherkinFile(resourcePath + "i18n_emoji.feature", listener);
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
