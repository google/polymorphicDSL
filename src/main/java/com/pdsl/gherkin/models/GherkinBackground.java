package com.pdsl.gherkin.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A background that contains shared functionality with lower level {@code GherkinScenario} or {＠GherkinRule}s.
 */
public class GherkinBackground {
    private final Optional<GherkinString> title;
    private final Optional<GherkinString> longDescription;
    private final Optional<List<GherkinStep>> steps;

    public GherkinBackground(Builder builder) {
        this.title = builder.title.isEmpty() ? Optional.empty() : Optional.of(new GherkinString(builder.title));
        this.longDescription = Optional.of(new GherkinString(builder.longDescription));
        this.steps = builder.steps.isEmpty() ? Optional.empty() : Optional.of(builder.steps);
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

    public static class Builder {
        private String title = "";
        private String longDescription = "";
        private List<GherkinStep> steps = new ArrayList<>();

        /**
         * Sets the title of the gherkin background.
         *
         * @param title title of the background
         * @return this builder
         */
        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * Sets the long description of the gherkin background.
         *
         * @param longDescription description of background
         * @return this builder
         */
        public Builder withLongDescription(String longDescription) {
            this.longDescription = longDescription;
            return this;
        }

        /**
         * Sets the steps of the background.
         *
         * @param steps the steps in the background
         * @return this builder
         */
        public Builder withSteps(List<GherkinStep> steps) {
            this.steps = steps;
            return this;
        }

        /**
         * Creates a GherkinBackground DTO.
         *
         * @return a GherkinBackground DTO
         */
        public GherkinBackground build() {
            return new GherkinBackground(this);
        }
    }
}
