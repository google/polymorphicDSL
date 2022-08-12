package com.pdsl.gherkin;

import com.pdsl.gherkin.models.GherkinFeature;

import java.net.URI;
import java.net.URL;
import java.util.Optional;

public abstract class PdslGherkinListener extends com.pdsl.gherkin.parser.GherkinParserBaseListener {
    public abstract Optional<GherkinFeature> getGherkinFeature(URI featurePathOrId);
}
