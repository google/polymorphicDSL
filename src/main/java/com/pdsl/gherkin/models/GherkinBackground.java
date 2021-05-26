package com.pdsl.gherkin.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A background that contains shared functionality with lower level {@code GherkinScenario} or {@GherkinRule}s.
 */
public class GherkinBackground {
    private Optional<GherkinString> title; //TODO: See if substitutions can be done in background at all
    private Optional<GherkinString> longDescription; //TODO: See if substitutions can be done in background at all
    private Optional<List<GherkinStep>> steps;

    public GherkinBackground(Builder builder) {
        this.title = builder.title.isEmpty() ? Optional.empty() : Optional.of(new GherkinString(builder.title));
        this.longDescription = Optional.of(new GherkinString(builder.longDescription));
        this.steps = builder.steps.isEmpty() ? Optional.empty() : Optional.of(builder.steps);
    }

    public static class Builder {
        private String title = "";
        private String longDescription = "";
        private List<GherkinStep> steps = new ArrayList<>();

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withLongDescription(String longDescription) {
            this.longDescription = longDescription;
            return this;
        }

        public Builder withSteps(List<GherkinStep> steps) {
            this.steps = steps;
            return this;
        }

        public GherkinBackground build() {
            return new GherkinBackground(this);
        }
    }

    public Optional<GherkinString> getTitle() {
        return title;
    }

    public Optional<GherkinString> getLongDescription() {
        return longDescription;
    }

    public Optional<List<GherkinStep>> getSteps() {
        return steps;
    }
}
