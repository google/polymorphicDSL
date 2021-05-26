package com.google.pdsl.gherkin.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GherkinRule {
    private Optional<String> title;
    private Optional<String> longDescription;
    private Optional<GherkinBackground> background;
    private Optional<List<GherkinScenario>> scenarios;


    private GherkinRule(Builder builder) {
        this.title = builder.title.isEmpty() ? Optional.empty() : Optional.of(builder.title);
        this.longDescription = builder.longDescription.isEmpty() ? Optional.empty()
                : Optional.of(builder.longDescription);
        this.background = builder.background;
        this.scenarios = builder.scenarios.isEmpty() ? Optional.empty() : Optional.of(builder.scenarios);
    }

    public static class Builder {
        private String title = "";
        private String longDescription = "";
        private Optional<GherkinBackground> background = Optional.empty();
        private List<GherkinScenario> scenarios = new ArrayList<>();

        public GherkinRule build() {
            return new GherkinRule(this);
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withLongDescription(String longDescription) {
            this.longDescription = longDescription;
            return this;
        }

        public Builder withGherkinBackground(GherkinBackground background) {
            this.background = Optional.of(background);
            return this;
        }

        public Builder addScenarios(GherkinScenario scenario) {
            scenarios.add(scenario);
            return this;
        }
    }

    public Optional<String> getTitle() {
        return title;
    }

    public Optional<String> getLongDescription() {
        return longDescription;
    }

    public Optional<GherkinBackground> getBackground() {
        return background;
    }

    public Optional<List<GherkinScenario>> getScenarios() {
        return scenarios;
    }
}
