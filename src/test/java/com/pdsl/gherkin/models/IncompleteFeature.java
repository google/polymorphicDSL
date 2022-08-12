package com.pdsl.gherkin.models;

import com.pdsl.gherkin.PdslGherkinInterpreterImpl;
import com.pdsl.gherkin.PdslGherkinListenerImpl;
import com.pdsl.gherkin.PdslGherkinRecognizer;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;

public class IncompleteFeature {

    private static final String resourcePath = "src/test/resources/testdata/good/";
    private static final PdslGherkinRecognizer transformer = new PdslGherkinInterpreterImpl();
    private static final PdslGherkinListenerImpl listener = new PdslGherkinListenerImpl();

    @Test
    public void backgroundWithNoSTeps_notPresent() throws IOException {
        Optional<GherkinFeature> featureOptional = transformer.interpretGherkinFile(Path.of(resourcePath + "incomplete_background_1.feature").toUri(), listener);
        assertThat(featureOptional.isPresent()).isTrue();
        // Background should be present, but have no steps
        assertThat(featureOptional.get().getBackground().isPresent()).isTrue();
        assertThat(featureOptional.get().getBackground().get().getSteps().isEmpty()).isTrue();
        // Scenario and step should still be present
        assertThat(featureOptional.get().getOptionalGherkinScenarios().isPresent()).isTrue();
        assertThat(featureOptional.get().getOptionalGherkinScenarios().get().size()).isEqualTo(1);
    }

    @Test
    public void backgroundWithNoStepsAndLongDescription_notPresent() throws IOException {
        Optional<GherkinFeature> featureOptional = transformer.interpretGherkinFile(Path.of(resourcePath + "incomplete_background_2.feature").toUri(), listener);
        assertThat(featureOptional.isPresent()).isTrue();
        // Background should be present, but have no steps
        assertThat(featureOptional.get().getBackground().isPresent()).isTrue();
        assertThat(featureOptional.get().getBackground().get().getSteps().isEmpty()).isTrue();
        assertThat(featureOptional.get().getBackground().get().getLongDescription().isPresent()).isTrue();
        // Scenario and step should still be present
        assertThat(featureOptional.get().getOptionalGherkinScenarios().isPresent()).isTrue();
        assertThat(featureOptional.get().getOptionalGherkinScenarios().get().size()).isEqualTo(1);
    }

    // TODO: Determine if we want to allow empty scenarios
    @Test
    public void featureWithOnlyDescription_stillParses() throws IOException {
        Optional<GherkinFeature> featureOptional = transformer.interpretGherkinFile(Path.of(resourcePath + "incomplete_feature_1.feature").toUri(), listener);
        assertThat(featureOptional.isPresent()).isTrue();
        GherkinFeature feature = featureOptional.get();
        assertThat(feature.getTitle().isPresent()).isTrue();
        assertThat(feature.getLongDescription().isPresent()).isTrue();
        // No scenarios or background
        assertThat(feature.getBackground().isEmpty()).isTrue();
        assertThat(feature.getOptionalGherkinScenarios().isPresent()).isFalse();
    }

    // TODO: Determine if we want to allow empty features
    @Test
    public void featureContainingOnlyTitle_stillParses() throws IOException {
        Optional<GherkinFeature> featureOptional = transformer.interpretGherkinFile(Path.of(resourcePath + "incomplete_feature_2.feature").toUri(), listener);
        assertThat(featureOptional.isPresent()).isTrue();
        GherkinFeature feature = featureOptional.get();
        assertThat(feature.getTitle().isPresent()).isTrue();
        assertThat(feature.getLongDescription().isPresent()).isFalse();
        // No scenarios or background
        assertThat(feature.getBackground().isEmpty()).isTrue();
        assertThat(feature.getOptionalGherkinScenarios().isPresent()).isFalse();
    }

    // TODO: Do we really want to allow gherkin features that only contain a comment?
    @Test
    public void featureContainingOnlyAComment_stillParses() throws IOException {
        Optional<GherkinFeature> featureOptional = transformer.interpretGherkinFile(Path.of(resourcePath + "incomplete_feature_3.feature").toUri(), listener);
        assertThat(featureOptional.isPresent()).isTrue();
    }
}
