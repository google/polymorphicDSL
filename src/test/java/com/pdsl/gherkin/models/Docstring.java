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

public class Docstring {

    private static final String resourcePath = "src/test/resources/testdata/good/";
    private static final PdslGherkinRecognizer transformer = new PdslGherkinInterpreterImpl();
    private static final PdslGherkinListenerImpl listener = new PdslGherkinListenerImpl();
    private final List<GherkinStep> stepList;

    public Docstring() throws IOException {
        // Arrange
        Optional<GherkinFeature> featureOptional = transformer.interpretGherkinFile(Path.of(resourcePath + "docstrings.feature").toUri(), listener);
        // Act
        assertThat(featureOptional.isPresent()).isTrue();
        GherkinFeature feature = featureOptional.get();
        stepList = feature.getOptionalGherkinScenarios().get().get(0).getStepsList().get();
    }

    @Test
    public void docstrings_containFullTextContent() {
        assertThat(stepList.get(0).getDocString().isPresent()).isTrue();
        assertThat(stepList.get(0).getDocString().get().getContentType()).isEqualTo(Optional.empty());
        assertThat(stepList.get(0).getDocString().get().getGherkinString().getRawString()).isEqualTo("      \"\"\"\n" +
                "      first line (no indent)\n" +
                "        second line (indented with two spaces)\n" +
                "\n" +
                "      third line was empty\n" +
                "      \"\"\"\n");

        assertThat(stepList.get(1).getDocString().get().getContentType().get()).isEqualTo("xml");
        assertThat(stepList.get(1).getDocString().get().getGherkinString().getRawString()).isEqualTo("      \"\"\"xml\n" +
                "      <foo>\n" +
                "        <bar />\n" +
                "      </foo>\n" +
                "      \"\"\"\n");
        assertThat(stepList.get(1).getDocString().get().getTextBody().get().getRawString()).isEqualTo("<foo>\n" +
                "        <bar />\n" +
                "      </foo>");
        assertThat(stepList.get(2).getDocString().get().getGherkinString().getRawString()).isEqualTo("      \"\"\"\n" +
                "    wrongly indented line\n" +
                "      \"\"\"\n");
        assertThat(stepList.get(3).getDocString().get().getGherkinString().getRawString()).isEqualTo("      ```\n" +
                "      first line\n" +
                "      second line\n" +
                "      ```\n");
        assertThat(stepList.get(4).getDocString().get().getGherkinString().getRawString()).isEqualTo("      ```\n" +
                "      first line\n" +
                "      \"\"\"\n" +
                "      third line\n" +
                "      ```\n");
        assertThat(stepList.get(5).getDocString().get().getGherkinString().getRawString()).isEqualTo("      \"\"\"\n" +
                "      first line\n" +
                "      ```\n" +
                "      third line\n" +
                "      \"\"\"\n");
        assertThat(stepList.get(6).getDocString().get().getGherkinString().getRawString()).isEqualTo("      \"\"\"\n" +
                "      first line\n" +
                "      \\\"\\\"\\\"\n" +
                "      third line\n" +
                "      \"\"\"\n");
        assertThat(stepList.get(7).getDocString().get().getGherkinString().getRawString()).isEqualTo("      ```\n" +
                "      first line\n" +
                "      \\`\\`\\`\n" +
                "      third line\n" +
                "      ```");
    }

}
