package com.pdsl.gherkin;

import com.pdsl.gherkin.models.GherkinFeature;

import java.util.Optional;

public abstract class PdslGherkinListener extends com.pdsl.gherkin.parser.GherkinParserBaseListener {
    public abstract Optional<GherkinFeature> getGherkinFeature(String featurePathOrId);
}
