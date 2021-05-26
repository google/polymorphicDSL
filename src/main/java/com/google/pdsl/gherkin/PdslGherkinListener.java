package com.google.pdsl.gherkin;

import com.google.pdsl.gherkin.models.GherkinFeature;

import java.util.Optional;

public abstract class PdslGherkinListener extends com.google.pdsl.gherkin.GherkinParserBaseListener {
    public abstract Optional<GherkinFeature> getGherkinFeature(String featurePathOrId);
}
