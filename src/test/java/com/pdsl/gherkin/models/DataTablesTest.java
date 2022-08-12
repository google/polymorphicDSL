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

public class DataTablesTest {
    private static final String resourcePath = "src/test/resources/testdata/good/";
    private static final PdslGherkinRecognizer transformer = new PdslGherkinInterpreterImpl();
    private static final PdslGherkinListenerImpl listener = new PdslGherkinListenerImpl();

    @Test
    public void stepDataTables_constructedCorrectly() throws IOException {
        // Arrange, Act
        Optional<GherkinFeature> featureOptional = transformer.interpretGherkinFile(Path.of(resourcePath + "datatables.feature").toUri(), listener);
        assertThat(featureOptional.isPresent()).isTrue();
        GherkinFeature feature = featureOptional.get();
        // Get the steps of the single scenario
        List<GherkinStep> steps = feature.getOptionalGherkinScenarios().get().get(0).getStepsList().get();
        //Assert
        assertThat(steps.size()).isEqualTo(5);

        // FIRST STEP datatable
        assertThat(steps.get(0).getDataTable().isPresent()).isTrue();
        List<List<GherkinString>> firstDataTable = steps.get(0).getDataTable().get();
        assertThat(firstDataTable.size()).isEqualTo(2);
        // First row has "Foo" and "Bar"
        assertThat(firstDataTable.get(0).size()).isEqualTo(2);
        assertThat(firstDataTable.get(0).get(0).getRawString()).isEqualTo("foo");
        assertThat(firstDataTable.get(0).get(1).getRawString()).isEqualTo("bar");
        // Second row has "box" and "boo"
        assertThat(firstDataTable.get(1).get(0).getRawString()).isEqualTo("boz");
        assertThat(firstDataTable.get(1).get(1).getRawString()).isEqualTo("boo");

        // SECOND STEP datatable
        assertThat(steps.get(1).getDataTable().isPresent()).isTrue();
        List<List<GherkinString>> secondDataTable = steps.get(1).getDataTable().get();
        // Single row and cell with "foo"
        assertThat(firstDataTable.get(0).get(0).getRawString()).isEqualTo("foo");

        // THIRD STEP datatable
        assertThat(steps.get(2).getDataTable().isPresent()).isTrue();
        List<List<GherkinString>> thirdDataTable = steps.get(2).getDataTable().get();
        // Single row with "foo", "bar", "boz"
        assertThat(thirdDataTable.get(0).size()).isEqualTo(3);
        assertThat(thirdDataTable.get(0).get(0).getRawString()).isEqualTo("foo");
        assertThat(thirdDataTable.get(0).get(1).getRawString()).isEqualTo("bar");
        assertThat(thirdDataTable.get(0).get(2).getRawString()).isEqualTo("boz");
        // FOURTH STEP datatable
        assertThat(steps.get(3).getDataTable().isPresent()).isTrue();
        List<List<GherkinString>> fourthDataTable = steps.get(3).getDataTable().get();
        // Single row with "foo", "", "bar
        assertThat(fourthDataTable.get(0).size()).isEqualTo(3);
        assertThat(fourthDataTable.get(0).get(0).getRawString()).isEqualTo("foo");
        assertThat(fourthDataTable.get(0).get(1).getRawString()).isEqualTo("");
        assertThat(fourthDataTable.get(0).get(2).getRawString()).isEqualTo("boz");

        // FIFTH STEP datatable\
        assertThat(steps.get(4).getDataTable().isPresent()).isTrue();
        List<List<GherkinString>> fifthDataTable = steps.get(4).getDataTable().get();
        // Multi row
        // first row
        assertThat(fifthDataTable.get(0).size()).isEqualTo(2);
        assertThat(fifthDataTable.get(0).get(0).getRawString()).isEqualTo("foo");
        assertThat(fifthDataTable.get(0).get(1).getRawString()).isEqualTo("bar");
        // second row
        assertThat(fifthDataTable.get(1).get(0).getRawString()).isEqualTo("boz");
        assertThat(fifthDataTable.get(1).get(1).getRawString()).isEqualTo("boo");
        // third row
        assertThat(fifthDataTable.get(2).get(0).getRawString()).isEqualTo("boz2");
        assertThat(fifthDataTable.get(2).get(1).getRawString()).isEqualTo("boo2");
    }

    @Test
    public void dataTableWithNewlines_preservedIfEscaped() throws IOException {
        // Arrange, Act
        Optional<GherkinFeature> featureOptional = transformer.interpretGherkinFile(Path.of(resourcePath + "datatables_with_new_lines.feature").toUri(), listener);
        assertThat(featureOptional.isPresent()).isTrue();
        GherkinFeature feature = featureOptional.get();
        // Get steps from single scenario
        List<GherkinStep> steps = feature.getOptionalGherkinScenarios().get().get(0).getStepsList().get();

        // Assert
        // FIRST STEP datatable
        assertThat(steps.get(0).getDataTable().isPresent()).isTrue();
        List<List<GherkinString>> firstDataTable = steps.get(0).getDataTable().get();
        assertThat(firstDataTable.size()).isEqualTo(1);
        assertThat(firstDataTable.get(0).get(0).getRawString()).isEqualTo("\nraindrops--\nher last kiss\ngoodbye.\n");

    }

    @Test
    public void dataTableWithNewlines_preservedNegativeSpace() throws IOException {
        // Arrange, Act
        Optional<GherkinFeature> featureOptional = transformer.interpretGherkinFile(Path.of(resourcePath + "datatables_with_new_lines.feature").toUri(), listener);
        assertThat(featureOptional.isPresent()).isTrue();
        GherkinFeature feature = featureOptional.get();
        // Get steps from single scenario
        List<GherkinStep> steps = feature.getOptionalGherkinScenarios().get().get(0).getStepsList().get();

        // Assert
        // FIRST STEP datatable
        assertThat(steps.get(0).getDataTable().isPresent()).isTrue();
        List<List<GherkinString>> secondDataTable = steps.get(1).getDataTable().get();
        assertThat(secondDataTable.size()).isEqualTo(1);
        assertThat(secondDataTable.get(0).get(0).getRawString()).isEqualTo("lost        i n        space");
    }

    @Test
    public void dataTableWithEscapedPipes_preserveEscapedPipes() throws IOException {
        // Arrange, Act
        Optional<GherkinFeature> featureOptional = transformer.interpretGherkinFile(Path.of(resourcePath + "escaped_pipes.feature").toUri(), listener);
        assertThat(featureOptional.isPresent()).isTrue();
        GherkinFeature feature = featureOptional.get();
        // Get steps from single scenario
        List<GherkinStep> steps = feature.getOptionalGherkinScenarios().get().get(0).getStepsList().get();
    }
}
