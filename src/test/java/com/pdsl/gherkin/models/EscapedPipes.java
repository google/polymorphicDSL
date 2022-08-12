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

public class EscapedPipes {

    private static final String resourcePath = "src/test/resources/testdata/good/";
    private static final PdslGherkinRecognizer transformer = new PdslGherkinInterpreterImpl();
    private static final PdslGherkinListenerImpl listener = new PdslGherkinListenerImpl();

    @Test
    public void escapedPipes_preservedInTableCells() throws IOException {
        // Arrange, Act
        Optional<GherkinFeature> featureOptional = transformer.interpretGherkinFile(Path.of(resourcePath + "escaped_pipes.feature").toUri(), listener);
        assertThat(featureOptional.isPresent()).isTrue();

        // Assert

        // Retrieve the steps from the single scenario
        List<GherkinStep> stepList = featureOptional.get().getOptionalGherkinScenarios().get().get(0)
                .getStepsList().get();
        // Check that the value of all cells matches expected value
        List<List<GherkinString>> firstStepCells = stepList.get(0).getDataTable().get();
        assertThat(firstStepCells.get(0).get(0).getRawString()).isEqualTo("æ");
        assertThat(firstStepCells.get(0).get(1).getRawString()).isEqualTo("o");
        assertThat(firstStepCells.get(1).get(0).getRawString()).isEqualTo("a");
        assertThat(firstStepCells.get(1).get(1).getRawString()).isEqualTo("ø");

        List<List<GherkinString>> secondStepCells = stepList.get(1).getDataTable().get();
        assertThat(secondStepCells.get(0).get(0).getRawString()).isEqualTo("|æ\\n");
        assertThat(secondStepCells.get(0).get(1).getRawString()).isEqualTo("\\o\no\\");
        assertThat(secondStepCells.get(1).get(0).getRawString()).isEqualTo("\\|a\\\\n");
        assertThat(secondStepCells.get(1).get(1).getRawString()).isEqualTo("ø\\\nø\\");
    }
}
