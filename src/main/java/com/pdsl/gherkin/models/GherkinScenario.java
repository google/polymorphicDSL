package com.pdsl.gherkin.models;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

public class GherkinScenario {
    private final Optional<List<String>> tags;
    private final Optional<GherkinString> title;
    private final Optional<GherkinString> longDescription;
    private final Optional<List<GherkinStep>> stepsList;
    private final Optional<List<GherkinExamplesTable>> examples;
    private final Optional<ScenarioPosition> scenarioPosition;
    private final int lineNumber;

    public GherkinScenario(Builder builder) {
        this.tags = builder.tags;
        this.title = builder.title.isEmpty() ? Optional.empty()
                : Optional.of(new GherkinString(builder.title));
        this.longDescription = builder.longDescription.isEmpty() ? Optional.empty()
                : Optional.of(new GherkinString(builder.longDescription));
        this.stepsList = builder.stepsList;
        this.examples = builder.examples.isEmpty() ? Optional.empty()
                : Optional.of(builder.examples);
        this.lineNumber = builder.lineNumber;
        this.scenarioPosition = builder.scenarioPosition;
    }

    /**
     * A specification of the hierarchical position this scenario appeared in with relation to other scenarios.
     * <p>
     * The ruleIndex specifies which rule the scenario was found. [e.g. which rule it was found in]
     * If the test was not nested in a rule the value will be 0
     * <p>
     * The ordinal specifies which order this test showed up in relation to the others in the same rule index.
     * <p>
     * The testIndex specifies is the test was derived from an examples table. If it was, it is the
     * nth row it appeared in starting from one. If it was not in an examples table the number will be 0.
     * <p>
     * This encoding CANNOT guarantee whether an arbitrary scenario was declared before another as it is possible
     * to intersperse rule nodes between root level scenarios. If you want to know the prceise order the test
     * was declared in the original source file use #getLineNumber() and compare using the
     * {@see com.pdsl.testcases.DefaultTestCase.PdslTestCaseComparator}
     * <p>
     * For example:
     * <p>
     * <pre>
     *     {@code
     *     Feature:
     *      Scenario:
     *      # First part is "0" because it is a root node. Last part is "0" because it is not in a table.
     *        Then this group ordinal is 0.1.0
     *      Scenario:
     *        Then this group ordinal is 0.2.0 # The second test in the root, se we increment to 2.
     *      Scenario:
     *        Then this group ordinal is <ORDINAL>
     *      Examples:
     *        |ORDINAL|
     *        | 0.3.1 | # Second test in the root, so we increment the second value to 2
     *        | 0.3.2 | # Increment last digit as it comes from the same group
     *        | 0.3.3 |
     *
     *      Rule: First rule (1)
     *        Scenario:
     *         Then this group ordinal is 1.1.0
     *        Scenario:
     *         Then this group ordinal is 1.2.0
     *        Scenario:
     *          Then this group oridinal is <ORDINAL>
     *          Examples:
     *          |ORDINAL|
     *          | 1.3.1 |
     *          | 1.3.2 |
     *
     *          # Multi-tables continue from the index used in the last table
     *          Examples:
     *          |ORDINAL|
     *          | 1.3.3 |
     *          | 1.3.4
     *
     *      Scenario:
     *        Then this group ordinal is 0.4.0 # Note we're back at root and continue from the last testPosition
     *
     *      Rule: Second rule (2)
     *        Scenario:
     *          Then this group ordinal is 2.1.0
     * }
     * </pre>
     *
     * @param ruleIndex the nth rule this scenario was derived from, 0 if not in a rule
     * @param ordinal   the nth position of this scenario relative to others in the same depth
     * @param testIndex 0 if not derived from an examples table, otherwise the nth row starting from 1
     */
    public record ScenarioPosition(int ruleIndex, int ordinal, int testIndex) implements Comparable<ScenarioPosition> {

        public static String RULE_INDEX= "ruleIndex";
        public static String ORDINAL = "ordinal";
        public static String TABLE_INDEX = "tableIndex";
        private static final ScenarioPositionComparator SINGLETON = new ScenarioPositionComparator();

        private static class ScenarioPositionComparator implements Comparator<ScenarioPosition> {
            @Override
            public int compare(ScenarioPosition p1, ScenarioPosition p2) {
                if (p1.ruleIndex != p2.ruleIndex) {
                    return Integer.compare(p1.ruleIndex, p2.ruleIndex);
                }
                if (p1.ordinal != p2.ordinal) {
                    return Integer.compare(p1.ordinal, p2.ordinal);
                }
                return Integer.compare(p1.testIndex, p2.testIndex);
            }
        }

        @Override
        public int compareTo(ScenarioPosition scenarioPosition) {
            return SINGLETON.compare(this, scenarioPosition);
        }

        public static Optional<ScenarioPosition> from(URI uri) {
            Map<String, String> params = Arrays.stream(uri.getQuery().split("&"))
                    .map(param -> param.split("="))
                    .filter(arr -> arr.length == 2)
                    .collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));
            try {
                int ruleIndex = Integer.parseInt(params.get(RULE_INDEX));
                int ordinal = Integer.parseInt(params.get(ORDINAL));
                int tableIndex = Integer.parseInt(params.get(TABLE_INDEX));
                return Optional.of(new ScenarioPosition(ruleIndex, ordinal, tableIndex));
            } catch(RuntimeException e) {
                return Optional.empty();
            }
        }
    }


    public Optional<ScenarioPosition> getScenarioPositition() { return scenarioPosition; }

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

    public int getLineNumber() {
        return lineNumber;
    }

    public static class Builder {
        private final List<GherkinExamplesTable> examples = new ArrayList<>();
        private Optional<List<String>> tags = Optional.empty();
        private String title = "";
        private String longDescription = "";
        private Optional<List<GherkinStep>> stepsList = Optional.empty();
        private int lineNumber = -1;
        private Optional<ScenarioPosition> scenarioPosition = Optional.empty();

        public GherkinScenario build() {
            return new GherkinScenario(this);
        }

        public Builder withScenarioPosition(ScenarioPosition scenarioPosition) {
            this.scenarioPosition = Optional.ofNullable(scenarioPosition);
            return this;
        }

        public Builder addExamples(GherkinExamplesTable examples) {
            this.examples.add(examples);
            return this;
        }

        public Builder withPosition(int lineNumber) {
            this.lineNumber = lineNumber;
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
