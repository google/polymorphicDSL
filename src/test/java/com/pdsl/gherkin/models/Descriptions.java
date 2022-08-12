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

public class Descriptions {

    private static final String resourcePath = "src/test/resources/testdata/good/";
    private static final PdslGherkinRecognizer transformer = new PdslGherkinInterpreterImpl();
    private static final PdslGherkinListenerImpl listener = new PdslGherkinListenerImpl();

    @Test
    public void titleAndLongDescriptions_foundAndMatchInputText() throws IOException {
        // Arrange
        Optional<GherkinFeature> featureOptional = transformer.interpretGherkinFile(Path.of(resourcePath + "descriptions.feature").toUri(), listener);
        // Act
        assertThat(featureOptional.isPresent()).isTrue();
        GherkinFeature feature = featureOptional.get();
        assertThat(feature.getTitle().isPresent()).isTrue();
        //Assert
        // Feature long description
        assertThat(feature.getLongDescription().isPresent()).isTrue();
        assertThat(feature.getLongDescription().get().trim()).isEqualTo("This is a single line description");

        //Scenario long descriptions
        assertThat(feature.getOptionalGherkinScenarios().isPresent()).isTrue();
        List<GherkinScenario> scenarioList = feature.getOptionalGherkinScenarios().get();
        assertThat(scenarioList.size()).isEqualTo(8);

        // Scenario 1
        assertThat(scenarioList.get(0).getLongDescription().get().getRawString().stripTrailing()).isEqualTo("  This description\n" +
                "  has two lines and indented with two spaces");
        // Scenario 2
        assertThat(scenarioList.get(1).getLongDescription().get().getRawString().stripTrailing()).isEqualTo("This is a description without indentation");
        // Scenario 3
        assertThat(scenarioList.get(2).getLongDescription().get().getRawString().stripTrailing()).isEqualTo("  This description\n" +
                "\n" +
                "  has an empty line in the middle");
        // Scenario 4
        assertThat(scenarioList.get(3).getLongDescription().get().getRawString()).isEqualTo("  This description\n" +
                "  has an empty lines around\n\n");
        // Scenario 5
        assertThat(scenarioList.get(4).getLongDescription().get().getRawString().trim()).isEqualTo("This description\n" +
                "  has a comment after");
        // Scenario 6
        assertThat(scenarioList.get(5).getLongDescription().get().getRawString().stripTrailing()).isEqualTo("  This description\n" +
                "  has a comment right after");
        // Scenario 7
        assertThat(scenarioList.get(6).getLongDescription().get().getRawString().strip()).isEqualTo("This description has an \\\"\\\"\\\" (escaped docstring sparator)");
        // Scenario 8
        assertThat(scenarioList.get(7).getLongDescription().get().getRawString().strip()).isEqualTo("This is a scenario outline description");
        // Scenario 8 examples table
        assertThat(scenarioList.get(7).getExamples().get().get(0).getLongDescription().get().strip()).isEqualTo("This is an examples description");
    }
}
