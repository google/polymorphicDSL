package com.google.pdsl.gherkin.models;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class GherkinScenario {
    private Optional<List<String>> tags;
    private Optional<GherkinString> title;
    private Optional<GherkinString> longDescription;
    private Optional<List<GherkinStep>> stepsList;
    private Optional<List<GherkinExamplesTable>> examples;

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

    public static class Builder {
        private Optional<List<String>> tags = Optional.empty();
        private String title = "";
        private String longDescription = "";
        private Optional<List<GherkinStep>> stepsList = Optional.empty();
        private List<GherkinExamplesTable> examples = new LinkedList<>();

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
}
