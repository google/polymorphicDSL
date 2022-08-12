package com.pdsl.gherkin;

import com.google.common.base.Preconditions;
import com.pdsl.gherkin.models.GherkinBackground;

import java.net.URI;
import java.util.*;

/**
 * A wrapper object used by a {@code GherkinTestSpecificationFactory} to produce pickle {@code TestSpecification}s.
 * <p>
 * The PickleJar is roughly analogous to a {@code GherkinFeature}
 * <p>
 * The practical need for a PickleJar is that a {@GherkinFeature} is abstract enough that some further processing is needed
 * to determine what the actual test cases are, particularly with parameter substitutions in the text in the event
 * the scenario or rule has an Example table
 * <p>
 * A pickle jar contains a group of pickles that have had the text substitutions already performed
 */
class PickleJar {

    private final String featureTitle;
    private final URI location;
    // Gherkin does not support substitutions on backgrounds, so we can recycle the regular GherkinBackground
    private final String languageCode;
    private final Set<String> featureTags;
    private final Optional<GherkinBackground> background;
    private final List<PickleJarScenario> scenarios;
    private final List<PickleJarRule> rules;
    private final Optional<String> longDescription;

    public PickleJar(Builder builder) {
        this.featureTitle = builder.featureTitle;
        this.location = builder.location;
        this.languageCode = builder.languageCode;
        this.background = builder.background;
        this.longDescription = builder.longDescription;
        this.featureTags = builder.featureTags;
        this.rules = builder.rules;
        this.scenarios = builder.scenarios;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public Set<String> getFeatureTags() {
        return featureTags;
    }

    public Optional<GherkinBackground> getBackground() {
        return background;
    }

    public List<PickleJarScenario> getScenarios() {
        return scenarios;
    }

    public List<PickleJarRule> getRules() {
        return rules;
    }

    public String getFeatureTitle() {
        return featureTitle;
    }

    public URI getLocation() {
        return location;
    }

    public Optional<String> getLongDescription() {
        return longDescription;
    }

    public static class Builder {
        private final String featureTitle;
        private final URI location;
        private final List<PickleJarScenario> scenarios = new ArrayList<>();
        private final List<PickleJarRule> rules = new ArrayList<>();
        private final String languageCode;
        private Set<String> featureTags = new HashSet<>();
        private Optional<GherkinBackground> background = Optional.empty();
        private Optional<String> longDescription = Optional.empty();

        public Builder(URI location, String featureTitle, String languageCode) {
            Preconditions.checkNotNull(location, "Gherkin location cannot be null!");
            this.location = location;
            this.featureTitle = featureTitle;
            this.languageCode = languageCode;
        }

        public PickleJar build() {
            return new PickleJar(this);
        }

        public Builder withLongDescription(String longDescription) {
            this.longDescription = Optional.ofNullable(longDescription);
            return this;
        }

        public Builder withRules(List<PickleJarRule> rules) {
            Preconditions.checkNotNull(rules, "rules cannot be null!");
            this.rules.addAll(rules);
            return this;
        }

        public Builder withFeatureLevelScenarios(List<PickleJarScenario> scenarios) {
            Preconditions.checkNotNull(scenarios, "Scenarios cannot be null!");
            this.scenarios.addAll(scenarios);
            return this;
        }

        public Builder withGherkinBackground(GherkinBackground gherkinBackground) {
            this.background = Optional.ofNullable(gherkinBackground);
            return this;
        }

        public Builder withFeatureTags(Set<String> featureTags) {
            this.featureTags = featureTags;
            return this;
        }
    }

    /**
     * A Gherkin rule that encapsulates additional PickleJarScenarios with a shared context.
     * <p>
     * The rule can contain a background that will extend the top level background (if it exists) for all scenarios
     * within this rule
     */
    static class PickleJarRule {
        private final String title;
        private final List<PickleJarScenario> scenarios;
        private Optional<String> longDescription = Optional.empty();
        private Optional<GherkinBackground> background = Optional.empty();

        private PickleJarRule(Builder builder) {
            this.title = builder.title;
            this.longDescription = builder.longDescription;
            this.background = builder.background;
            this.scenarios = builder.scenarios;
        }

        public String getTitle() {
            return title;
        }

        public Optional<String> getLongDescription() {
            return longDescription;
        }

        public Optional<GherkinBackground> getBackground() {
            return background;
        }

        public List<PickleJarScenario> getScenarios() {
            return scenarios;
        }

        public static class Builder {
            private final String title;
            private final List<PickleJarScenario> scenarios;
            private Optional<String> longDescription = Optional.empty();
            private Optional<GherkinBackground> background = Optional.empty();

            public Builder(String title, List<PickleJarScenario> scenarios) {
                Preconditions.checkArgument(title != null || title.isEmpty(), "Title cannot be null or empty!");
                this.title = title;
                this.scenarios = scenarios;
            }

            public PickleJarRule build() {
                return new PickleJarRule(this);
            }

            public Builder withLongDescription(String longDescription) {
                this.longDescription = Optional.ofNullable(longDescription);
                return this;
            }

            public Builder withBackground(GherkinBackground background) {
                this.background = Optional.ofNullable(background);
                return this;
            }
        }
    }

    /**
     * A Gherkin Scenario that has had parameter substitutions performed on the scenario and step text.
     * <p>
     * This scenario may represent a test created from a row from an Examples table, which in turn was from a
     * more abstract {@Code GherkinScenario}
     * <p>
     * In any case the PickleJarScenario does not have any examples table because it is intended to be more concrete,
     * only missing parent tags and background steps to be a fully concrete pickle
     * <p>
     * It contains the tags from both the scenario and examples table it was derived from
     */
    static class PickleJarScenario {
        private final String scenarioTitleWithParameterSubstitutionsIfNeeded;
        private final Optional<String> longDescription;
        private final List<String> stepsWithParameterSubstitutionsIfNeeded;
        private Optional<Set<String>> tags;

        private PickleJarScenario(Builder builder) {
            this.tags = builder.tags;
            this.longDescription = builder.longDescription;
            this.tags = builder.tags;
            this.scenarioTitleWithParameterSubstitutionsIfNeeded = builder.titleWithSubstitutions;
            this.stepsWithParameterSubstitutionsIfNeeded = builder.stepsWithSubstitutions;
        }

        public Optional<Set<String>> getTags() {
            return tags;
        }

        public String getTitleWithSubstitutions() {
            return scenarioTitleWithParameterSubstitutionsIfNeeded;
        }

        public Optional<String> getLongDescription() {
            return longDescription;
        }

        public List<String> getStepsWithSubstitutions() {
            return stepsWithParameterSubstitutionsIfNeeded;
        }

        public static class Builder {
            private final String titleWithSubstitutions;
            private final List<String> stepsWithSubstitutions;
            private Optional<Set<String>> tags = Optional.empty();
            private Optional<String> longDescription = Optional.empty();

            public Builder(String titleWithSubstitutions, List<String> stepsWithSubstitutions) {
                this.titleWithSubstitutions = titleWithSubstitutions;
                this.stepsWithSubstitutions = stepsWithSubstitutions;
            }

            public PickleJarScenario build() {
                return new PickleJarScenario(this);
            }

            public Builder withLongDescription(String longDescription) {
                this.longDescription = Optional.ofNullable(longDescription);
                return this;
            }

            public Builder withTags(Collection<String> tags) {
                if (tags == null || tags.isEmpty()) {
                    this.tags = Optional.empty();
                } else {
                    Set<String> scenarioTags = new HashSet<>();
                    scenarioTags.addAll(tags);
                    this.tags = Optional.of(scenarioTags);
                }
                return this;
            }

        }
    }


}