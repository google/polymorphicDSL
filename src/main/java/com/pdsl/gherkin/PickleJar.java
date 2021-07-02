package com.pdsl.gherkin;

import com.google.common.base.Preconditions;
import com.pdsl.gherkin.models.GherkinBackground;

import java.net.URL;
import java.util.*;

/**
 * A wrapper object used by a {@code GherkinTestSpecificationFactory} to produce pickle {@code TestSpecification}s.
 *
 * The PickleJar is roughly analogous to a {@code GherkinFeature}
 *
 * The practical need for a PickleJar is that a {@GherkinFeature} is abstract enough that some further processing is needed
 * to determine what the actual test cases are, particularly with parameter substitutions in the text in the event
 * the scenario or rule has an Example table
 *
 * A pickle jar contains a group of pickles that have had the text substitutions already performed
 */
class PickleJar {

    // Gherkin does not support substitutions on backgrounds, so we can recycle the regular GherkinBackground
    private String languageCode;
    private Set<String> featureTags;
    private Optional<GherkinBackground> background;
    private List<PickleJarScenario> scenarios;
    private List<PickleJarRule> rules;
    private final String featureTitle;
    private final URL location;
    private Optional<String> longDescription;

    public PickleJar(Builder builder) {
        this.featureTitle = builder.featureTitle;;
        this.location = builder.location;
        this.languageCode = builder.languageCode;
        this.background = builder.background;
        this.longDescription = builder.longDescription;
        this.featureTags = builder.featureTags;
        this.rules = builder.rules;
        this.scenarios = builder.scenarios;
    }

    public static class Builder {
        private Set<String> featureTags = new HashSet<>();
        private Optional<GherkinBackground> background = Optional.empty();
        private List<PickleJarScenario> scenarios = new LinkedList<>();
        private List<PickleJarRule> rules = new LinkedList<>();
        private final String featureTitle;
        private Optional<String> longDescription = Optional.empty();
        private final URL location;
        private String languageCode;

        public Builder(URL location, String featureTitle, String languageCode) {
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

    public URL getLocation() {
        return location;
    }

    public Optional<String> getLongDescription() {
        return longDescription;
    }
    /**
     * A Gherkin rule that encapsulates additional PickleJarScenarios with a shared context.
     *
     * The rule can contain a background that will extend the top level background (if it exists) for all scenarios
     * within this rule
     *
     */
    static class PickleJarRule {
        private String title;
        private Optional<String> longDescription = Optional.empty();
        private Optional<GherkinBackground> background = Optional.empty();
        private List<PickleJarScenario> scenarios;

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

        private PickleJarRule(Builder builder) {
            this.title = builder.title;
            this.longDescription = builder.longDescription;
            this.background = builder.background;
            this.scenarios = builder.scenarios;
        }

        public static class Builder {
            private String title;
            private Optional<String> longDescription = Optional.empty();
            private Optional<GherkinBackground> background = Optional.empty();
            private List<PickleJarScenario> scenarios;

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
     *
     * This scenario may represent a test created from a row from an Examples table, which in turn was from a
     * more abstract {@Code GherkinScenario}
     *
     * In any case the PickleJarScenario does not have any examples table because it is intended to be more concrete,
     * only missing parent tags and background steps to be a fully concrete pickle
     *
     * It contains the tags from both the scenario and examples table it was derived from
     */
    static class PickleJarScenario {
        private Optional<Set<String>> tags;
        private String scenarioTitleWithParameterSubstitutionsIfNeeded;
        private Optional<String> longDescription;
        private List<String> stepsWithParameterSubstitutionsIfNeeded;

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

        private PickleJarScenario(Builder builder) {
            this.tags = builder.tags;
            this.longDescription = builder.longDescription;
            this.tags = builder.tags;
            this.scenarioTitleWithParameterSubstitutionsIfNeeded = builder.titleWithSubstitutions;
            this.stepsWithParameterSubstitutionsIfNeeded = builder.stepsWithSubstitutions;
        }

        public static class Builder {
            private Optional<Set<String>> tags = Optional.empty();
            private String titleWithSubstitutions;
            private Optional<String> longDescription = Optional.empty();
            private List<String> stepsWithSubstitutions;

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