package com.pdsl.gherkin;

import com.pdsl.gherkin.models.*;
import com.pdsl.gherkin.parser.GherkinParser;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class PdslGherkinListenerImpl extends PdslGherkinListener {

    private static final Set<Character> escapeCharacters = Set.of('\\', '|', 'n');
    private Optional<GherkinFeature.Builder> builderOptional = Optional.empty();

    public Optional<GherkinFeature> getGherkinFeature(URI featurePathOrId) {
        return builderOptional.isEmpty() ? Optional.empty() : Optional.of(builderOptional.get().withLocation(featurePathOrId).build());
    }

    @Override
    public void enterGherkinDocument(GherkinParser.GherkinDocumentContext ctx) {
        if (ctx.feature() != null) {
            GherkinFeature.Builder builder = new GherkinFeature.Builder();
            builderOptional = Optional.of(builder);
            // Get the language code
            if (ctx.LANGUAGE_HEADER() != null) {
                String languageCode = ctx.LANGUAGE_HEADER().getText().split(":")[1].trim();
                builder.withLanguageCode(languageCode);
            }
        }
    }

    @Override
    public void enterFeature(GherkinParser.FeatureContext ctx) {
        assert (builderOptional.isPresent()) : "GherkinFeature builder not initialized!";
        GherkinFeature.Builder builder = builderOptional.get();
        if (ctx.TAGS() != null) {
            builder.withTags(terminalNodes2StringList(ctx.TAGS()));
        }
        if (ctx.FEATURE_TITLE() != null) {
            builder.withTitle(ctx.FEATURE_TITLE().getText());
        }
        if (ctx.LONG_DESCRIPTION() != null) {
            builder.withLongDescription(list2String(ctx.LONG_DESCRIPTION()));
        }
        if (ctx.background() != null) {
            builder.withBackground(transformBackground(ctx.background()));
        }
        if (ctx.scenario() != null) {
            ctx.scenario().forEach(s -> builder.addScenario(transformScenario(s)));
        }
        if (ctx.ruleBlock() != null) {
            ctx.ruleBlock().forEach(r -> builder.addRule(transformRule(r)));
        }
    }

    private GherkinRule transformRule(GherkinParser.RuleBlockContext ctx) {
        GherkinRule.Builder builder = new GherkinRule.Builder();
        if (ctx.RULE_TITLE() != null) {
            builder.withTitle(ctx.RULE_TITLE().getText());
        }
        if (ctx.LONG_DESCRIPTION() != null) {
            builder.withLongDescription(transformLongDescription(ctx.LONG_DESCRIPTION()));
        }
        if (ctx.background() != null) {
            builder.withGherkinBackground(transformBackground(ctx.background()));
        }
        if (ctx.scenario() != null) {
            ctx.scenario().forEach(s -> builder.addScenarios(transformScenario(s)));
        }
        return builder.build();
    }

    private GherkinScenario transformScenario(GherkinParser.ScenarioContext ctx) {
        GherkinScenario.Builder scenarioBuilder = new GherkinScenario.Builder();
        List<String> tags = new LinkedList<>();
        if (!ctx.TAGS().isEmpty()) {
            ctx.TAGS().forEach(t -> tags.add(t.getText()));
            scenarioBuilder.withTags(tags);
        }
        if (ctx.SCENARIO_TITLE() != null) {
            scenarioBuilder.withTitle(ctx.SCENARIO_TITLE().getText());
        } else if (ctx.SCENARIO_OUTLINE_TITLE() != null) {
            scenarioBuilder.withTitle(ctx.SCENARIO_OUTLINE_TITLE().getText());
        }
        if (ctx.LONG_DESCRIPTION() != null) {
            scenarioBuilder.withLongDescription(transformLongDescription(ctx.LONG_DESCRIPTION()));
        }
        if (ctx.stepBody() != null) {
            scenarioBuilder.withSteps(transformStepBody(ctx.stepBody()));
        }
        if (ctx.examplesBody() != null) {
            ctx.examplesBody().forEach(e -> scenarioBuilder.addExamples(transformExamples(e)));
        }
        return scenarioBuilder.build();
    }

    private GherkinExamplesTable transformExamples(GherkinParser.ExamplesBodyContext ctx) {
        GherkinExamplesTable.Builder builder = new GherkinExamplesTable.Builder();
        if (ctx.EXAMPLES_TITLE() != null) {
            builder.withTitle(ctx.EXAMPLES_TITLE().getText());
        }
        if (ctx.TAGS() != null) {
            List<String> tags = new LinkedList<>();
            ctx.TAGS().forEach(t -> tags.add(t.getText()));
            builder.withTags(tags);
        }
        if (ctx.LONG_DESCRIPTION() != null) {
            builder.withLongDescription(transformLongDescription(ctx.LONG_DESCRIPTION()));
        }
        if (ctx.DATA_ROW() != null) {
            List<List<String>> exampleData = new LinkedList<>();
            ctx.DATA_ROW().forEach(row -> exampleData.add(transformRowData(row.getText())));
            builder.withTable(createSubstitutionMapping(exampleData));
        }
        return builder.build();
    }

    private Map<String, List<String>> createSubstitutionMapping(List<List<String>> exampleContent) {
        // See if the rows first column or row contains the keys by convention
        boolean verticalHeaderRow = exampleContent.get(0).get(0).trim().equals("");
        Map<String, List<String>> substitutions = new HashMap<>();
        if (verticalHeaderRow) {
            // First column has headers, the rest of the cell are the values
            for (List<String> row : exampleContent) {
                substitutions.put(row.get(0), row.subList(1, row.size()));
            }
        } else { // First row in the table has parameter keywords
            List<String> header = exampleContent.get(0);
            for (int i = 0; i < header.size(); i++) { // For each cell entry in a row
                List<String> parameters = new LinkedList<>(); // Create a list of all substitution data
                for (int j = 1; j < exampleContent.size(); j++) { // Add the cells from the ith column in the table
                    parameters.add(exampleContent.get(j).get(i)
                            .strip()); // Keep escaped newlines, but ignore whitespace
                }
                substitutions.put("<" + header.get(i) + ">", parameters);
            }
        }
        return substitutions;
    }

    private String transformLongDescription(List<TerminalNode> description) {
        StringBuilder builder = new StringBuilder();
        description.forEach(d -> builder.append(d.getText()));
        return builder.toString();
    }

    private List<GherkinStep> transformStepBody(GherkinParser.StepBodyContext ctx) {
        List<GherkinStep> steps = new LinkedList<>();
        if (ctx.startingStep() == null) {
            return steps;
        } else { // TODO: Refactor the duplicate code. This is ugly
            GherkinStep.Builder stepBuilder = new GherkinStep.Builder();
            if (ctx.startingStep().GIVEN_STEP() != null) {
                stepBuilder.withStepContent(ctx.startingStep().GIVEN_STEP().getText());
                stepBuilder.withStepKeyword(GherkinStep.StepType.GIVEN,
                        ctx.startingStep().GIVEN_STEP().getSymbol().getText());
            } else if (ctx.startingStep().WHEN_STEP() != null) {
                stepBuilder.withStepContent(ctx.startingStep().WHEN_STEP().getText());
                stepBuilder.withStepKeyword(GherkinStep.StepType.WHEN,
                        ctx.startingStep().WHEN_STEP().getSymbol().getText());
            } else if (ctx.startingStep().THEN_STEP() != null) {
                stepBuilder.withStepContent(ctx.startingStep().THEN_STEP().getText());
                stepBuilder.withStepKeyword(GherkinStep.StepType.THEN,
                        ctx.startingStep().THEN_STEP().getSymbol().getText());
            } else if (ctx.startingStep().WILD_STEP() != null) {
                stepBuilder.withStepContent(ctx.startingStep().WILD_STEP().getText());
                stepBuilder.withStepKeyword(GherkinStep.StepType.WILD,
                        ctx.startingStep().WILD_STEP().getSymbol().getText());
            } else {
                throw new IllegalArgumentException("Error creating a step");
            }

            // Docstring XOR Datatable
            if (ctx.startingStep().DOCSTRING() != null) {
                stepBuilder.withDocString(ctx.startingStep().DOCSTRING().getText());
            } else if (ctx.startingStep().DATA_ROW() != null) {
                List<List<GherkinString>> tableData = new LinkedList<>();
                ctx.startingStep().DATA_ROW().forEach(r -> tableData.add(transformRowData(r.getText())
                        .stream()
                        .map(GherkinString::new)
                        .collect(Collectors.toList())
                ));
                stepBuilder.withDataTable(tableData);
            }
            steps.add(stepBuilder.build());
            // all other steps
            for (GherkinParser.AnyStepContext stepCtx : ctx.anyStep()) {
                GherkinStep.Builder anyStepBuilder = new GherkinStep.Builder();
                if (stepCtx.GIVEN_STEP() != null) {
                    anyStepBuilder.withStepContent(stepCtx.GIVEN_STEP().getText());
                    anyStepBuilder.withStepKeyword(GherkinStep.StepType.GIVEN,
                            stepCtx.GIVEN_STEP().getSymbol().getText());

                } else if (stepCtx.WHEN_STEP() != null) {
                    anyStepBuilder.withStepContent(stepCtx.WHEN_STEP().getText());
                    anyStepBuilder.withStepKeyword(GherkinStep.StepType.WHEN,
                            stepCtx.WHEN_STEP().getSymbol().getText());
                } else if (stepCtx.THEN_STEP() != null) {
                    anyStepBuilder.withStepContent(stepCtx.THEN_STEP().getText());
                    anyStepBuilder.withStepKeyword(GherkinStep.StepType.THEN,
                            stepCtx.THEN_STEP().getSymbol().getText());
                } else if (stepCtx.BUT_STEP() != null) {
                    anyStepBuilder.withStepContent(stepCtx.BUT_STEP().getText());
                    anyStepBuilder.withStepKeyword(GherkinStep.StepType.BUT,
                            stepCtx.BUT_STEP().getSymbol().getText());
                } else if (stepCtx.WILD_STEP() != null) {
                    anyStepBuilder.withStepContent(stepCtx.WILD_STEP().getText());
                    anyStepBuilder.withStepKeyword(GherkinStep.StepType.WILD,
                            stepCtx.WILD_STEP().getSymbol().getText());
                } else if (stepCtx.AND_STEP() != null) {
                    anyStepBuilder.withStepContent(stepCtx.AND_STEP().getText());
                    anyStepBuilder.withStepKeyword(GherkinStep.StepType.AND,
                            stepCtx.AND_STEP().getSymbol().getText());
                } else {
                    throw new IllegalArgumentException("Error creating a step");
                }

                // Docstring XOR Datatable
                if (stepCtx.DOCSTRING() != null) {
                    anyStepBuilder.withDocString(stepCtx.DOCSTRING().getText());
                } else if (stepCtx.DATA_ROW() != null) {
                    List<List<GherkinString>> tableData = new LinkedList<>();
                    stepCtx.DATA_ROW().forEach(r -> tableData.add(transformRowData(r.getText())
                            .stream()
                            .map(GherkinString::new)
                            .collect(Collectors.toList())
                    ));
                    anyStepBuilder.withDataTable(tableData);
                }
                steps.add(anyStepBuilder.build());
            }
        }
        return steps;
    }

    private GherkinBackground transformBackground(GherkinParser.BackgroundContext ctx) {
        GherkinBackground.Builder background = new GherkinBackground.Builder();
        if (ctx.BACKGROUND_TITLE() != null) {
            background.withTitle(ctx.BACKGROUND_TITLE().getText());
        }
        if (ctx.LONG_DESCRIPTION() != null) {
            background.withLongDescription(list2String(ctx.LONG_DESCRIPTION()));
        }
        if (ctx.stepBody() != null) {
            background.withSteps(transformStepBody(ctx.stepBody()));
        }
        return background.build();
    }

    private List<String> transformRowData(String rowText) {
        List<String> cellData = new LinkedList<>();
        StringBuilder cellText = new StringBuilder();
        boolean possibleEscapeCharacter = false;
        String row = rowText.trim();
        for (int i = 1; i < row.length(); i++) {
            char c = row.charAt(i);
            if (possibleEscapeCharacter) {
                if (escapeCharacters.contains(c)) {
                    cellText.append(c == 'n' ? "\n" : c);
                } else { // False escape
                    cellText.append("\\" + c);
                }
                possibleEscapeCharacter = false;
            } else {
                if (c == '\\') { // Escape character
                    possibleEscapeCharacter = true;
                } else if (c == '|') { // End of cell
                    cellData.add(cellText.toString() //trim() removes leading and trailing whitespace
                            .replaceAll("^[ \t]*", "") // Remove leading spaces and tabs
                            .replaceAll("[ \t]*$", "") // Remove trailing spaces and tabs
                    );
                    cellText = new StringBuilder();
                } else {
                    cellText.append(c);
                }
            }
        }
        return cellData;
    }

    private List<String> terminalNodes2StringList(List<TerminalNode> nodeList) {
        List<String> tags = new LinkedList<>();
        nodeList.forEach(t -> tags.add(t.getText()));
        return tags;
    }

    private String list2String(List<TerminalNode> nodeList) {
        StringBuilder stringBuilder = new StringBuilder();
        nodeList.forEach(s -> stringBuilder.append(s.getText()));
        return stringBuilder.toString();
    }
}
