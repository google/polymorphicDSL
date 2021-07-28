package com.pdsl.gherkin.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GherkinRule {
    private final Optional<String> title;
    private final Optional<String> longDescription;
    private final Optional<GherkinBackground> background;
    private final Optional<List<GherkinScenario>> scenarios;


    private GherkinRule(Builder builder) {
        this.title = builder.title.isEmpty() ? Optional.empty() : Optional.of(builder.title);
        this.longDescription = builder.longDescription.isEmpty() ? Optional.empty()
                : Optional.of(builder.longDescription);
        this.background = builder.background;
        this.scenarios = builder.scenarios.isEmpty() ? Optional.empty() : Optional.of(builder.scenarios);
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

    public static class Builder {
        private final List<GherkinScenario> scenarios = new ArrayList<>();
        private String title = "";
        private String longDescription = "";
        private Optional<GherkinBackground> background = Optional.empty();

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
}
