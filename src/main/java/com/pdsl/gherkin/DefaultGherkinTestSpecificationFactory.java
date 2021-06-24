package com.pdsl.gherkin;

import com.google.common.base.Preconditions;
import com.pdsl.gherkin.models.GherkinBackground;
import com.pdsl.gherkin.models.GherkinStep;
import com.pdsl.gherkin.models.GherkinString;
import com.pdsl.gherkin.specifications.GherkinTestSpecification;
import com.pdsl.gherkin.specifications.GherkinTestSpecificationFactory;
import com.pdsl.logging.AnsiTerminalColorHelper;
import com.pdsl.specifications.DefaultTestSpecification;
import com.pdsl.gherkin.specifications.GherkinTestCaseSpecification;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.TestSpecificationFactory;
import com.pdsl.transformers.PolymorphicDslFileException;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class DefaultGherkinTestSpecificationFactory implements GherkinTestSpecificationFactory {

    // TOOD: create builder
    private PickleJarFactory pickleJarFactory;
    private Charset charset = Charset.defaultCharset();
    private final int DESCRIPTION_MAX_LENGTH;
    private final TestSpecificationFactory grammarHelperFactory;
    private final TestSpecificationFactory subgrammarHelperFactory;
    private final static GherkinTagFilterer gherkinTagFilterer = new GherkinTagFiltererImpl();

    public DefaultGherkinTestSpecificationFactory(PickleJarFactory pickleJarFactory, TestSpecificationFactory grammarHelperFactory, TestSpecificationFactory subgrammarHelperFactory) {
        this.pickleJarFactory = pickleJarFactory;;
        DESCRIPTION_MAX_LENGTH = 1024;
        this.grammarHelperFactory = grammarHelperFactory;
        this.subgrammarHelperFactory = subgrammarHelperFactory;
    }

    public DefaultGherkinTestSpecificationFactory(TestSpecificationFactory grammarHelperFactory, TestSpecificationFactory subgrammarHelperFactory) {
        this.pickleJarFactory = PickleJarFactory.DEFAULT;;
        DESCRIPTION_MAX_LENGTH = 1024;
        this.grammarHelperFactory = grammarHelperFactory;
        this.subgrammarHelperFactory = subgrammarHelperFactory;
    }

    @Override
    public TestSpecification getTestSpecifications(Set<String> dslTestFilePaths) {
        Preconditions.checkArgument(dslTestFilePaths != null && !dslTestFilePaths.isEmpty(), "filepaths cannot be null or empty!");
        List<GherkinTestCaseSpecification> featureTestSpecifications = new LinkedList<>();
        List<PickleJar> pickleJars = pickleJarFactory.getPickleJars(dslTestFilePaths);
       for (PickleJar pickleJar : pickleJars) {
           Set<String> allTagsForTestCase = new HashSet<>();
           DefaultTestSpecification.Builder featureBuilder = new DefaultTestSpecification.Builder(pickleJar.getLocation());
           // Create feature metadata
           ByteArrayOutputStream featureMetaData = new ByteArrayOutputStream();
           addBytesWithCorrectEncoding(featureMetaData, "#language:" + pickleJar.getLanguageCode() + "\n");
           if (!pickleJar.getFeatureTags().isEmpty()) {
               addBytesWithCorrectEncoding(featureMetaData, String.join(" ", pickleJar.getFeatureTags()) + "\n");
               allTagsForTestCase.addAll(pickleJar.getFeatureTags());
           }
           addBytesWithCorrectEncoding(featureMetaData,pickleJar.getFeatureTitle() + "\n");
           if (pickleJar.getLongDescription().isPresent()) {
               addBytesWithCorrectEncoding(featureMetaData, pickleJar.getLongDescription().get());
           }
           if (pickleJar.getBackground().isPresent()) {
               GherkinBackground bg = pickleJar.getBackground().get();
               addBytesWithCorrectEncoding(featureMetaData, getBackgroundText(bg));
               Optional<List<ParseTree>> filteredBackgroundStepBody = processStepBodyContent(bg.getSteps().get(), AnsiTerminalColorHelper.CYAN + "Top level " + AnsiTerminalColorHelper.BRIGHT_CYAN + "Background" + AnsiTerminalColorHelper.RESET + " in " + AnsiTerminalColorHelper.RESET + pickleJar.getLocation());
               if (filteredBackgroundStepBody.isPresent()) {
                   featureBuilder.withTestPhrases(filteredBackgroundStepBody.get());
               }
           }
           featureBuilder.withMetaData(featureMetaData);
           List<TestSpecification> pickles = getGherkinStepSpecificationScenarios(pickleJar.getScenarios(),
                   allTagsForTestCase);

           // Process all rules
           if (!pickleJar.getRules().isEmpty()) {
               pickles.addAll(transformRulesToTestSpecifications(pickleJar.getRules()));
           }
           featureBuilder.withChildTestSpecifications(pickles);
           featureTestSpecifications.add(new GherkinTestCaseSpecification(allTagsForTestCase, featureBuilder.build()));
       }
       // Convert pickle jar into a list of TestSpecifications, where each TestSpecification represents a feature
        return featureTestSpecifications.get(0);
       //return new GherkinTestCaseSpecification(new HashSet<String>(), featureTestSpecifications);
       /*
        return

        */
    }

    private List<GherkinTestSpecification> transformScenariosToTestSpecifications(List<PickleJar.PickleJarScenario> scenarios, Set<String> parentTags) {
        List<GherkinTestSpecification> gherkinTestSpecifications = new LinkedList<>();
        for (PickleJar.PickleJarScenario pickleJarScenario : scenarios) {
            DefaultTestSpecification.Builder topLevelScenario = new DefaultTestSpecification.Builder(pickleJarScenario.getTitleWithSubstitutions());
            // Provide metadata
            topLevelScenario.withMetaData(extractMetaData(pickleJarScenario));
            // Process step body
            // transform step body into list of InputStreams
            Optional<TestSpecification> stepBodyAsTestSpecification = processStepBody(pickleJarScenario.getTitleWithSubstitutions(), pickleJarScenario.getStepsWithSubstitutions());
            if (stepBodyAsTestSpecification.isPresent()) {
                topLevelScenario.withTestPhrases(stepBodyAsTestSpecification.get().getPhrases().get());
            } else { // Failure to parse step body
                // Logging on failure should be handled by the stepBodyHelperFactory
                continue;
            }
            // A gherkin pickle should have all of the tags of its parents as well as its owns
            Set<String> allTags = new HashSet<>(parentTags);
            if (pickleJarScenario.getTags().isPresent()) {
                allTags.addAll(pickleJarScenario.getTags().get());
            }
            gherkinTestSpecifications.add(new GherkinTestSpecification(topLevelScenario.build(), allTags));
        }
        return gherkinTestSpecifications;
    }

    private Optional<List<ParseTree>> processStepBodyContent(List<GherkinStep> stepBody, String gherkinContext) {
        List<InputStream> stepBodyAsStrings = stepBody.stream()
                .map(GherkinStep::getStepContent)
                .map(GherkinString::getRawString) //No substitutions are done on background steps
                .map(step -> new ByteArrayInputStream(step.getBytes(charset)))
                .collect(Collectors.toUnmodifiableList());
        Optional<TestSpecification> tempContainer = grammarHelperFactory.getTestSpecification(gherkinContext, stepBodyAsStrings);
        if (tempContainer.isPresent()) { // Filter content
            stepBodyAsStrings = stepBody.stream()
                    .map(GherkinStep::getStepContent)
                    .map(GherkinString::getRawString) //No substitutions are done on background steps
                    .map(step -> new ByteArrayInputStream(step.getBytes(charset)))
                    .collect(Collectors.toUnmodifiableList());
            tempContainer = subgrammarHelperFactory.getTestSpecification(gherkinContext, stepBodyAsStrings);
        }
        if (tempContainer.isPresent()) {
            if (tempContainer.get().getPhrases().isPresent()) {
                return tempContainer.get().getPhrases();
            } else if (tempContainer.get().nestedTestSpecifications().isPresent()) {
                throw new IllegalStateException("I wasn't expecting this...");
            }
        }
        return Optional.empty();
    }

    private Optional<TestSpecification> processStepBody(String title, List<String> stepBody) {
        List<InputStream> stepBodyAsInputStream = stepBody.stream()
                .map(step -> new ByteArrayInputStream(step.getBytes(charset)))
                .collect(Collectors.toUnmodifiableList());
        Optional<TestSpecification> stepBodyAsSpecification = grammarHelperFactory.getTestSpecification(title, stepBodyAsInputStream);
        if (stepBodyAsSpecification.isPresent()) {
            stepBodyAsInputStream = stepBody.stream()
                    .map(step -> new ByteArrayInputStream(step.getBytes(charset)))
                    .collect(Collectors.toUnmodifiableList());
            return subgrammarHelperFactory.getTestSpecification(title, stepBodyAsInputStream);
        } else {
            return Optional.empty();
        }
    }

    private List<TestSpecification> transformRulesToTestSpecifications(List<PickleJar.PickleJarRule> rules) {
        List<TestSpecification> testSpecifications = new LinkedList<>();
        for (PickleJar.PickleJarRule rule : rules) {
            DefaultTestSpecification.Builder ruleBuilder = new DefaultTestSpecification.Builder(rule.getTitle());
            ByteArrayOutputStream ruleMetaData = new ByteArrayOutputStream();
            if (rule.getLongDescription().isPresent()) {
                addBytesWithCorrectEncoding(ruleMetaData, rule.getLongDescription().get());
                ruleBuilder.withMetaData(ruleMetaData);
            }
            if (rule.getBackground().isPresent()) {
                // Nest the scenarios in a background TestSpecification
                GherkinBackground bg = rule.getBackground().get();
                addBytesWithCorrectEncoding(ruleMetaData, getBackgroundText(bg));
                Optional<List<ParseTree>> filteredBackgroundStepBody = processStepBodyContent(bg.getSteps().get(), AnsiTerminalColorHelper.CYAN + "Rule Background" + AnsiTerminalColorHelper.RESET +" in " + rule.getTitle());
                if (filteredBackgroundStepBody.isPresent()) {
                    ruleBuilder.withTestPhrases(filteredBackgroundStepBody.get());
                }
            }
            List<TestSpecification> filteredScenarios = getGherkinStepSpecificationScenarios(rule.getScenarios(), new HashSet<>());
            if (!filteredScenarios.isEmpty()) {
                ruleBuilder.withMetaData(ruleMetaData);
                ruleBuilder.withChildTestSpecifications(filteredScenarios);
                testSpecifications.add(ruleBuilder.build());
            }
        }
        return testSpecifications;
    }

    private List<TestSpecification> getGherkinStepSpecificationScenarios(List<PickleJar.PickleJarScenario> scenarios, Set<String> parentTags) {
        List<GherkinTestSpecification> gherkinTestSpecifications = new LinkedList<>();
        gherkinTestSpecifications.addAll(transformScenariosToTestSpecifications(scenarios, parentTags));
        // Generics are not covarient. Cast to TestSpecification
        return gherkinTestSpecifications.stream()
                .map(t -> (TestSpecification) t)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TestSpecification> getTestSpecification(String id, List<InputStream> testContent) {
        Optional<TestSpecification> matchGrammar = grammarHelperFactory.getTestSpecification(id, testContent);
        if (matchGrammar.isPresent()) { //Filter content if we verified all sentences belong to grammar
            return subgrammarHelperFactory.getTestSpecification(id, testContent);
        } else {
            return Optional.empty();
        }
    }

    private ByteArrayOutputStream extractMetaData(PickleJar.PickleJarScenario pickleJarScenario) {
        ByteArrayOutputStream scenarioMetaData = new ByteArrayOutputStream();
        if (pickleJarScenario.getTags().isPresent()) {
            addBytesWithCorrectEncoding(scenarioMetaData, AnsiTerminalColorHelper.BRIGHT_YELLOW + String.join(" ", pickleJarScenario.getTags().get()) + AnsiTerminalColorHelper.RESET + "\n");
        }
        addBytesWithCorrectEncoding(scenarioMetaData, AnsiTerminalColorHelper.BRIGHT_PURPLE);
        addBytesWithCorrectEncoding(scenarioMetaData, pickleJarScenario.getTitleWithSubstitutions());
        if (pickleJarScenario.getLongDescription().isPresent()) {
            addBytesWithCorrectEncoding(scenarioMetaData, getAbridgedText(pickleJarScenario.getLongDescription().get()));
        }
        addBytesWithCorrectEncoding(scenarioMetaData, AnsiTerminalColorHelper.RESET);
        return scenarioMetaData;
    }

    private String getBackgroundText(GherkinBackground background) {
        StringBuilder backgroundText = new StringBuilder();
        backgroundText.append(background.getTitle().get().getRawString() + "\n");
        if (background.getLongDescription().isPresent()) {
            backgroundText.append(getAbridgedText(background.getLongDescription().get().getRawString()));
        }
        return backgroundText.toString();
    }

    private String getAbridgedText(String str) {
        if (DESCRIPTION_MAX_LENGTH > 0) {
            return str.length() > DESCRIPTION_MAX_LENGTH ? str.substring(0, DESCRIPTION_MAX_LENGTH) + "\n<abridged>" : str;
        } else {
            return str;
        }
    }

    private void addBytesWithCorrectEncoding(OutputStream stream, String str) {
        try {
            stream.write(str.getBytes(charset));
        } catch (IOException e) {
            throw new PolymorphicDslFileException("Could not write metadata while processing gherkin document!", e);
        }
    }

    public Optional<TestSpecification> filterGherkinTestSpecificationsByTagExpression(TestSpecification testSpecification, String tagExpression) {
        if (tagExpression == null || tagExpression.isEmpty()) { return Optional.of(testSpecification); }
        Set<String> allPickleTags = new HashSet<>();
        return recursivelyGetTagsAndFilterPickles(testSpecification, allPickleTags, tagExpression);
    }

    private Optional<TestSpecification> recursivelyGetTagsAndFilterPickles(TestSpecification testSpecification, Set<String> allParentTags, String tagExpression) {
        Set<String> allGherkinItemTags = new HashSet<>(allParentTags);
        DefaultTestSpecification.Builder builder = new DefaultTestSpecification.Builder(testSpecification.getId());
        // See if this is a gherkin test specification we can get tags from
        if (testSpecification instanceof GherkinTestSpecification) {
            allGherkinItemTags.addAll(((GherkinTestSpecification)testSpecification).getTags());
        }
        // If there are child nodes then this is a container, feature, scenario outline or rule
        if (testSpecification.nestedTestSpecifications().isPresent()) {
            List<TestSpecification> filteredChildren = new LinkedList<>();
            for (TestSpecification childTestSpecification : testSpecification.nestedTestSpecifications().get()) {
                Optional<TestSpecification> filteredChildTestOptional =
                        recursivelyGetTagsAndFilterPickles(childTestSpecification,
                                allGherkinItemTags, tagExpression);
                if (filteredChildTestOptional.isPresent()) {
                    filteredChildren.add(filteredChildTestOptional.get());
                }
            }
            if (!filteredChildren.isEmpty()) {
                builder.withChildTestSpecifications(filteredChildren);
            }
        } else { // This is a leaf node, i.e a pickle
            // See if this pickle matches the tag expression or not
            if (!gherkinTagFilterer.tagExpressionMatchesPickle(allGherkinItemTags, tagExpression, charset)) {
                return Optional.empty();
            } else {
                builder.withTestPhrases(testSpecification.getPhrases().orElseThrow()); // scenario must have steps
            }
        }
        if (testSpecification.getMetaData().isPresent()) {
            builder.withMetaData(testSpecification.getMetaData().get());
        }
        if (testSpecification.getPhrases().isPresent()) {
            builder.withTestPhrases(testSpecification.getPhrases().get());
        }
        if (testSpecification.getPhrases().isEmpty() && testSpecification.nestedTestSpecifications().isEmpty()) {
            // Everything was filtered out
            return Optional.empty();
        }
        try {
            return Optional.of(builder.build());
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
