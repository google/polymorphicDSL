package com.pdsl.gherkin;

import com.pdsl.gherkin.models.GherkinFeature;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public interface PdslGherkinInterpreter {


    Optional<GherkinFeature> interpretGherkinFile(String filepath, PdslGherkinListener listener) throws IOException;

    /**
     * Reads a gherkin file and transforms it into an object if and only if it has scenarios AND all those scenarios have at least one step.
     *
     * In the event the gherkin can be interpreted but is not "well formed" a runtime exception will be thrown indicating what is missing
     *
     * @param filepath The .feature file to convert to a GherkinFeature object
     * @param listener The parse tree listener
     * @return a GherkinFeature matching the contents of the file
     * @throws IOException
     */
    GherkinFeature interpretGherkinFileStrictly(String filepath, PdslGherkinListener listener) throws IOException;

    Optional<GherkinFeature> interpretGherkinFileStrictly(InputStream featureFileContent, String featurePathOrId) throws IOException;
}
