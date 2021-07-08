package com.pdsl.gherkin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Optional;

import com.pdsl.gherkin.parser.GherkinLexer;
import com.pdsl.gherkin.parser.GherkinParser;
import com.pdsl.gherkin.models.GherkinFeature;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class PdslGherkinInterpreterImpl implements PdslGherkinRecognizer {

    private PdslGherkinListener listener;

    public Optional<com.pdsl.gherkin.models.GherkinFeature> interpretGherkinFile(URL testResource, PdslGherkinListener listener) throws IOException {
        GherkinLexer lexer = new GherkinLexer(CharStreams.fromStream(testResource.openStream()));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GherkinParser parser = new GherkinParser(tokens);
        parser.setBuildParseTree(true); // tell ANTLR to build a parse tree
        ParseTree tree = parser.gherkinDocument();
        ParseTreeWalker walker = new ParseTreeWalker();
        //GherkinTransformer interpreter = new GherkinTransformer();
        walker.walk(listener, tree);
        return listener.getGherkinFeature(testResource);
    }

    /**
     * Reads a gherkin file and transforms it into an object if and only if it has scenarios AND all those scenarios have at least one step.
     *
     * In the event the gherkin can be interpreted but is not "well formed" a runtime exception will be thrown indicating what is missing
     *
     * @param gherkinLocation The .feature file to convert to a GherkinFeature object
     * @param listener The parse tree listener
     * @return
     * @throws IOException
     */
    public GherkinFeature interpretGherkinFileStrictly(URL gherkinLocation, PdslGherkinListener listener)
            throws IOException {
        Optional<GherkinFeature> gherkinFeatureOptional = interpretGherkinFile(gherkinLocation, listener);
        if (gherkinFeatureOptional.isEmpty()) {
            throw new MalformedGherkinException("Gherkin could not be parsed or file had no feature!");
        }
        GherkinFeature feature = gherkinFeatureOptional.get();
        if (feature.getOptionalGherkinScenarios().isEmpty() && feature.getRules().isEmpty()) {
            throw new MalformedGherkinException("Gherkin file had no scenarios or rules!");
        } else if ((feature.getOptionalGherkinScenarios().isPresent() && feature.getOptionalGherkinScenarios().get().stream().anyMatch(s -> s.getStepsList().isEmpty() ||
                s.getStepsList().get().isEmpty())) ||
                (feature.getRules().isPresent() &&
            feature.getRules().get().stream().anyMatch(r -> r.getScenarios().isEmpty() || r.getScenarios().get().isEmpty()))) {
            throw new MalformedGherkinException("Gherkin contained either no scenarios or at least one scenario with no steps!");
        }
        return feature;
    }

    @Override
    public Optional<GherkinFeature> interpretGherkinFileStrictly(InputStream featureFileContent, URL featurePathOrId) throws IOException {
        GherkinLexer lexer = new GherkinLexer(CharStreams.fromStream(featureFileContent));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GherkinParser parser = new GherkinParser(tokens);
        parser.setBuildParseTree(true); // tell ANTLR to build a parse tree
        ParseTree tree = parser.gherkinDocument();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, tree);
        return listener.getGherkinFeature(featurePathOrId);
    }

    static class MalformedGherkinException extends RuntimeException {

        public MalformedGherkinException(String m) {
            super(m);
        }
    }
}
