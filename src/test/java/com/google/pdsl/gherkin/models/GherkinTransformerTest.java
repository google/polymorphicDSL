package com.google.pdsl.gherkin.models;

import com.google.pdsl.gherkin.PdslGherkinListenerImpl;
import com.google.pdsl.gherkin.PdslGherkinInterpreter;
import com.google.pdsl.gherkin.PdslGherkinInterpreterImpl;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;
/**
 * Tests that the interpreter structures the gherkin file correctly
 */
public class GherkinTransformerTest
{

    private static final String resourcePath = "src/test/resources/testdata/good/";
    private static final PdslGherkinInterpreter transformer = new PdslGherkinInterpreterImpl();
    private static final PdslGherkinListenerImpl listener = new PdslGherkinListenerImpl();

    @Test
    public void minimalFeature_hasSingleScenario() throws IOException
    {
        // Arrange
        Optional<GherkinFeature> featureOptional = transformer.interpretGherkinFile(resourcePath + "minimal.feature", listener);
        // Act
        assertThat(featureOptional.isPresent()).isTrue();
        GherkinFeature feature = featureOptional.get();
        assertThat(feature.getLanguageCode()).isEqualTo("en");
        assertThat(feature.getTitle().isPresent()).isTrue();
        assertThat(feature.getTitle().get()).contains("Minimal");
        // Scenario
        assertThat(feature.getOptionalGherkinScenarios().isPresent());
        List<GherkinScenario> scenarioList = feature.getOptionalGherkinScenarios().get();
        assertThat(scenarioList.size()).isEqualTo(1);
        GherkinScenario scenario = scenarioList.get(0);
        assertThat(scenario.getTitle().isPresent()).isTrue();
        assertThat(scenario.getTitle().get().getRawString()).contains("minimalistic");
        assertThat(scenario.getTags().isPresent()).isFalse();
        assertThat(scenario.getExamples().isPresent()).isFalse();
        assertThat(scenario.getLongDescription().isPresent()).isFalse();
        assertThat(scenario.getExamples().isPresent()).isFalse();

    }

    @Test
    public void minimalExample_exampleKeywordValid() throws IOException {
        // Arrange
        Optional<GherkinFeature> featureOptional = transformer.interpretGherkinFile(resourcePath + "minimal-example.feature", listener);
        // Act
        assertThat(featureOptional.isPresent()).isTrue();
        GherkinFeature feature = featureOptional.get();
        assertThat(feature.getLanguageCode()).isEqualTo("en");
        assertThat(feature.getTitle().isPresent()).isTrue();
        assertThat(feature.getTitle().get()).contains("Minimal");
    }

    @Test
    public void background_interpretsBackgroundAndScenarios() throws IOException {
        // Arrange
        Optional<GherkinFeature> featureOptional = transformer.interpretGherkinFile(resourcePath + "background.feature", listener);
        // Act
        assertThat(featureOptional.isPresent()).isTrue();
        GherkinFeature feature = featureOptional.get();
        assertThat(feature.getTitle().get()).contains("Background");
        assertThat(feature.getBackground().isPresent()).isTrue();
        // Backgrounds
        GherkinBackground background = feature.getBackground().get();
        assertThat(background.getTitle().isPresent()).isTrue();
        assertThat(background.getTitle().get().getRawString().contains("a simple background")).isTrue();
        // Background steps
        assertThat(background.getSteps().isPresent()).isTrue();
        List<GherkinStep> backgroundSteps = background.getSteps().get();
        assertThat(backgroundSteps.size()).isEqualTo(1);
        assertThat(backgroundSteps.get(0).getStepType()).isEqualTo(GherkinStep.StepType.GIVEN);
        assertThat(backgroundSteps.get(0).getDataTable().isPresent()).isFalse();
        // Scenarios
        assertThat(feature.getOptionalGherkinScenarios().isPresent()).isTrue();
        List<GherkinScenario> scenarioList = feature.getOptionalGherkinScenarios().get();
        assertThat(scenarioList.size()).isEqualTo(2);
        GherkinScenario scenario1 = scenarioList.get(0);
        assertThat(scenario1.getStepsList().isPresent()).isTrue();
        assertThat(scenario1.getStepsList().get().size()).isEqualTo(1);
        GherkinScenario scenario2 = scenarioList.get(1);
        assertThat(scenario2.getStepsList().isPresent()).isTrue();
        assertThat(scenario2.getStepsList().get().size()).isEqualTo(1);
    }

    @Test
    public void complexBackground_interpretsBackgroundAndScenarios() throws IOException {
        // Arrange
        Optional<GherkinFeature> featureOptional = transformer.interpretGherkinFile(resourcePath + "complex_background.feature", listener);
        // Act
        assertThat(featureOptional.isPresent()).isTrue();
        GherkinFeature feature = featureOptional.get();
        assertThat(feature.getTitle().get()).contains("Complex background");
        assertThat(feature.getBackground().isPresent()).isTrue();
        // Backgrounds
        GherkinBackground background = feature.getBackground().get();
        assertThat(background.getTitle().isPresent()).isTrue();
        assertThat(background.getTitle().get().getRawString().contains("a simple background")).isTrue();
        // Background steps
        assertThat(background.getSteps().isPresent()).isTrue();
        List<GherkinStep> backgroundSteps = background.getSteps().get();
        assertThat(backgroundSteps.size()).isEqualTo(1);
        assertThat(backgroundSteps.get(0).getStepType()).isEqualTo(GherkinStep.StepType.GIVEN);
        assertThat(backgroundSteps.get(0).getDataTable().isPresent()).isFalse();
        assertThat(backgroundSteps.get(0).getStepContent().getRawString()).contains("Given the minimalism inside a background");
        // Scenarios
        assertThat(feature.getOptionalGherkinScenarios().isPresent()).isTrue();
        List<GherkinScenario> scenarioList = feature.getOptionalGherkinScenarios().get();
        assertThat(scenarioList.size()).isEqualTo(2);
        GherkinScenario scenario1 = scenarioList.get(0);
        assertThat(scenario1.getStepsList().isPresent()).isTrue();
        assertThat(scenario1.getStepsList().get().size()).isEqualTo(1);
        GherkinScenario scenario2 = scenarioList.get(1);
        assertThat(scenario2.getStepsList().isPresent()).isTrue();
        assertThat(scenario2.getStepsList().get().size()).isEqualTo(1);
        // Rules
        assertThat(feature.getRules().isPresent()).isTrue();
        List<GherkinRule> rules = feature.getRules().get();
        assertThat(rules.size()).isEqualTo(1);
        GherkinRule rule = rules.get(0);
        assertThat(rule.getTitle().isPresent()).isTrue();
        assertThat(rule.getTitle().get()).contains("My Rule");
        assertThat(rule.getBackground().isPresent()).isTrue();
        GherkinBackground ruleBackground = rule.getBackground().get();
        assertThat(ruleBackground.getTitle().isPresent()).isTrue();
        assertThat(ruleBackground.getSteps().get().size()).isEqualTo(1);
        // Examples
        GherkinScenario ruleScenario = rule.getScenarios().get().get(0);
        assertThat(ruleScenario.getExamples().isPresent()).isTrue();
        List<GherkinExamplesTable> examplesTableList  = ruleScenario.getExamples().get();
        assertThat(examplesTableList.size()).isEqualTo(1);
        Map<String, List<String>> dataTable = examplesTableList.get(0).getTable().get();
        assertThat(dataTable.keySet().size()).isEqualTo(1);
    }
}
