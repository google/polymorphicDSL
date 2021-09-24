package com.pdsl.gherkin.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GherkinScenario {
    private final Optional<List<String>> tags;
    private final Optional<GherkinString> title;
    private final Optional<GherkinString> longDescription;
    private final Optional<List<GherkinStep>> stepsList;
    private final Optional<List<GherkinExamplesTable>> examples;

    public GherkinScenario(Builder builder) {
        this.tags = builder.tags;
        this.title = builder.title.isEmpty() ? Optional.empty()
                : Optional.of(new GherkinString(builder.title));
        this.longDescription = builder.longDescription.isEmpty() ? Optional.empty()
                : Optional.of(new GherkinString(builder.longDescription));
        this.stepsList = builder.stepsList;
        this.examples = builder.examples.isEmpty() ? Optional.empty()
                : Optional.of(builder.examples);
    }

    public Optional<List<String>> getTags() {
        return tags;
    }

    public Optional<GherkinString> getTitle() {
        return title;
    }

    public Optional<GherkinString> getLongDescription() {
        return longDescription;
    }

    public Optional<List<GherkinStep>> getStepsList() {
        return stepsList;
    }

    public Optional<List<GherkinExamplesTable>> getExamples() {
        return examples;
    }

    public static class Builder {
        private final List<GherkinExamplesTable> examples = new ArrayList<>();
        private Optional<List<String>> tags = Optional.empty();
        private String title = "";
        private String longDescription = "";
        private Optional<List<GherkinStep>> stepsList = Optional.empty();

        public GherkinScenario build() {
            return new GherkinScenario(this);
        }

        public Builder addExamples(GherkinExamplesTable examples) {
            this.examples.add(examples);
            return this;
        }

        public Builder withSteps(List<GherkinStep> stepsList) {
            this.stepsList = Optional.of(stepsList);
            return this;
        }

        public Builder withLongDescription(String longDescription) {
            this.longDescription = longDescription;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withTags(List<String> tags) {
            this.tags = Optional.of(tags);
            return this;
        }
    }
}
