package com.pdsl.gherkin.models;

import com.google.common.base.Preconditions;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class GherkinFeature {
    private final String languageCode;
    private final Optional<String> title;
    private final Optional<String> longDescription;
    private final Optional<GherkinBackground> background;
    private final Optional<List<GherkinScenario>> optionalGherkinScenarios;
    private final Optional<List<GherkinRule>> rules;
    private final Optional<List<String>> tags;
    private final URL location;

    private GherkinFeature(Builder builder) {
        this.languageCode = builder.languageCode;
        this.longDescription = builder.longDescription.isEmpty() ? Optional.empty()
                : Optional.of(builder.longDescription);
        this.background = builder.background;
        this.optionalGherkinScenarios = builder.gherkinScenarios.isEmpty() ? Optional.empty()
                : Optional.of(builder.gherkinScenarios);
        this.title = builder.title.isEmpty() ? Optional.empty() : Optional.of(builder.title);
        this.rules = builder.rules.isEmpty() ? Optional.empty() : Optional.of(builder.rules);
        this.tags = builder.tags.isEmpty() ? Optional.empty() : builder.tags;
        this.location = builder.location;
    }

    public static class Builder {
        private URL location;
        private String languageCode = "en";
        private String title = "";
        private String longDescription = "";
        private Optional<GherkinBackground> background = Optional.empty();
        private List<GherkinScenario> gherkinScenarios = new LinkedList<>();
        private List<GherkinRule> rules = new LinkedList<>();
        private Optional<List<String>> tags = Optional.empty();

        public Builder(URL location) {
            Preconditions.checkArgument(location == null, "Location cannot be null!");
            // preconditions on argument delayed until building
            this.location = location;
        }

        public Builder() {}

        public Builder withLocation(URL location) {
            Preconditions.checkArgument(location != null,
                    "Location cannot be null!");
            this.location = location;
            return this;
        }

        public GherkinFeature build() {
            Preconditions.checkArgument(location != null,
                    "Location cannot be null!");
            return new GherkinFeature(this);
        }

        public Builder addScenario(GherkinScenario scenario) {
            gherkinScenarios.add(scenario);
            return this;
        }
        public Builder addRule(GherkinRule rule) {
            rules.add(rule);
            return this;
        }
        public Builder withTags(List<String> tags) {
            this.tags = Optional.of(tags);
            return this;
        }
        public Builder withRules(List<GherkinRule> rules) {
            this.rules = rules;
            return this;
        }

        public Builder withScenarios(List<GherkinScenario> scenarios) {
            this.gherkinScenarios = scenarios;
            return this;
        }

        public Builder withBackground(GherkinBackground background) {
            this.background = Optional.of(background);
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

        public Builder withLanguageCode(String languageCode) {
            this.languageCode = languageCode;
            return this;
        }

        public String getLanguageCode() {
            return languageCode;
        }

        public String getTitle() {
            return title;
        }

        public String getLongDescription() {
            return longDescription;
        }

        public Optional<GherkinBackground> getBackground() {
            return background;
        }

        public List<GherkinScenario> getGherkinScenarios() {
            return gherkinScenarios;
        }

        public List<GherkinRule> getRules() {
            return rules;
        }
    }

    public String getLanguageCode() {
        return languageCode;
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

    public Optional<List<GherkinScenario>> getOptionalGherkinScenarios() {
        return optionalGherkinScenarios;
    }

    public Optional<List<GherkinRule>> getRules() {
        return rules;
    }

    public Optional<List<String>> getTags() {
        return tags;
    }

    public URL getLocation() {return location; }
}
