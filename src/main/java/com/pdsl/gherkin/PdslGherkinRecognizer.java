package com.pdsl.gherkin;

import com.pdsl.gherkin.models.GherkinFeature;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.Optional;

/**
 * A recognizer of the Gherkin protocol.
 *
 * This is able to take a URI of a Gherkin resource and turn it into an underlying object that represents the text.
 */
public interface PdslGherkinRecognizer {

    Optional<GherkinFeature> interpretGherkinFile(URI filepath, PdslGherkinListener listener) throws IOException;

    /**
     * Reads a gherkin file and transforms it into an object if and only if it has scenarios AND all those scenarios have at least one step.
     * <p>
     * In the event the gherkin can be interpreted but is not "well formed" a runtime exception will be thrown indicating what is missing
     *
     * @param gherkinLocation The .feature file to convert to a GherkinFeature object
     * @param listener        The parse tree listener
     * @return a GherkinFeature matching the contents of the file
     * @throws IOException
     */
    GherkinFeature interpretGherkinFileStrictly(URI gherkinLocation, PdslGherkinListener listener) throws IOException;

    Optional<GherkinFeature> interpretGherkinFileStrictly(InputStream featureFileContent, URI featurePathOrId) throws IOException;
}
