package com.pdsl.gherkin.models;

import com.pdsl.gherkin.PdslGherkinInterpreterImpl;
import com.pdsl.gherkin.PdslGherkinListenerImpl;
import com.pdsl.gherkin.PdslGherkinRecognizer;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;

public class ExampleTokens {

    private static final String resourcePath = "src/test/resources/testdata/good/";
    private static final PdslGherkinRecognizer transformer = new PdslGherkinInterpreterImpl();
    private static final PdslGherkinListenerImpl listener = new PdslGherkinListenerImpl();

    @Test
    public void exampleToken_canBeSubstitutedMultipleTimes() throws IOException {
        // Arrange, Act
        Optional<GherkinFeature> featureOptional = transformer.interpretGherkinFile(Path.of(resourcePath + "example_token_multiple.feature").toUri(), listener);
        assertThat(featureOptional.isPresent()).isTrue();
        // Assert
        GherkinStep step = featureOptional.get().getOptionalGherkinScenarios().get().get(0).getStepsList().get().get(0);
        GherkinExamplesTable examplesTable = featureOptional.get().getOptionalGherkinScenarios().get().get(0).getExamples().get().get(0);
        List<Map<String, String>> rows = examplesTable.getRows();
        assertThat(step.getStepContent().getStringWithSubstitutions(rows.get(0))).contains("Given usage usage");
    }

    @Test
    public void exampleToken_canBeSubstitutedOnce() throws IOException {
        // Arrange, Act
        Optional<GherkinFeature> featureOptional = transformer.interpretGherkinFile(Path.of(resourcePath + "example_tokens_everywhere.feature").toUri(), listener);
        assertThat(featureOptional.isPresent()).isTrue();
        GherkinStep step = featureOptional.get().getOptionalGherkinScenarios().get().get(0).getStepsList().get().get(0);
        GherkinExamplesTable examplesTable = featureOptional.get().getOptionalGherkinScenarios().get().get(0).getExamples().get().get(0);
        List<Map<String, String>> rows = examplesTable.getRows();
        // Assert
        assertThat(step.getStepContent().getStringWithSubstitutions(rows.get(0))).contains("Given the deux:");
        assertThat(step.getStepContent().getStringWithSubstitutions(rows.get(1))).contains("Given the dos:");
    }

    @Test
    public void exampleToken_canBeInDocstring() throws IOException {
        // Arrange, Act
        Optional<GherkinFeature> featureOptional = transformer.interpretGherkinFile(Path.of(resourcePath + "example_tokens_everywhere.feature").toUri(), listener);
        assertThat(featureOptional.isPresent()).isTrue();
        GherkinStep step = featureOptional.get().getOptionalGherkinScenarios().get().get(0).getStepsList().get().get(0);
        GherkinExamplesTable examplesTable = featureOptional.get().getOptionalGherkinScenarios().get().get(0).getExamples().get().get(0);
        List<Map<String, String>> rows = examplesTable.getRows();
        // Assert
        assertThat(step.getDocString().get().getTextBody().get().getStringWithSubstitutions(rows.get(0))).isEqualTo("trois");
        assertThat(step.getDocString().get().getTextBody().get().getStringWithSubstitutions(rows.get(1))).isEqualTo("tres");
    }

    @Test
    public void exampleToken_canBeInStepDataTable() throws IOException {
        // Arrange, Act
        Optional<GherkinFeature> featureOptional = transformer.interpretGherkinFile(Path.of(resourcePath + "example_tokens_everywhere.feature").toUri(), listener);
        assertThat(featureOptional.isPresent()).isTrue();
        GherkinStep step = featureOptional.get().getOptionalGherkinScenarios().get().get(0).getStepsList().get().get(1);
        GherkinExamplesTable examplesTable = featureOptional.get().getOptionalGherkinScenarios().get().get(0).getExamples().get().get(0);
        List<Map<String, String>> rows = examplesTable.getRows();
        GherkinString stepDataTableCell = step.getDataTable().get().get(0).get(0);
        // Assert
        // First examples row substitutions
        assertThat(stepDataTableCell.hasSubstitutions()).isTrue();
        Map<String, String> firstRowSubs = rows.get(0);
        assertThat(step.getStepContent().getStringWithSubstitutions(firstRowSubs))
                .isEqualTo("    Given the quatre:\n");
        assertThat("cinq").isEqualTo(stepDataTableCell.getStringWithSubstitutions(firstRowSubs));

        // Second examples row substitutions
        Map<String, String> secondRowSubs = rows.get(1);
        assertThat("    Given the quatro:\n").isEqualTo(step.getStepContent().getStringWithSubstitutions(secondRowSubs));
        assertThat("cinco").isEqualTo(stepDataTableCell.getStringWithSubstitutions(secondRowSubs));
    }
}
