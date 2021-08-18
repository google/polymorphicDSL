package com.pdsl.gherkin;

import com.google.common.base.Preconditions;
import com.pdsl.gherkin.models.GherkinBackground;
import com.pdsl.gherkin.models.GherkinStep;
import com.pdsl.gherkin.filter.GherkinTagsVisitorImpl;
import com.pdsl.gherkin.specifications.GherkinTestSpecification;
import com.pdsl.gherkin.specifications.GherkinTestSpecificationFactory;
import com.pdsl.gherkin.testcases.GherkinTestCaseSpecification;
import com.pdsl.logging.AnsiTerminalColorHelper;
import com.pdsl.specifications.DefaultTestSpecification;
import com.pdsl.specifications.FilteredPhrase;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.transformers.PolymorphicDslFileException;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class DefaultGherkinTestSpecificationFactory implements GherkinTestSpecificationFactory {

    private static final Logger logger = LoggerFactory.getLogger(DefaultGherkinTestSpecificationFactory.class);
    private final int DESCRIPTION_MAX_LENGTH;
    private final PolymorphicDslPhraseFilter phraseFilter;
    private final PickleJarFactory pickleJarFactory;
    private final Charset charset = Charset.defaultCharset();

    public DefaultGherkinTestSpecificationFactory(PickleJarFactory pickleJarFactory,
                                                  PolymorphicDslPhraseFilter phraseFilter) {
        this.pickleJarFactory = pickleJarFactory;
        DESCRIPTION_MAX_LENGTH = 1024;
        this.phraseFilter = phraseFilter;
    }

    public DefaultGherkinTestSpecificationFactory(PolymorphicDslPhraseFilter phraseFilter) {
        this.pickleJarFactory = PickleJarFactory.DEFAULT;
        DESCRIPTION_MAX_LENGTH = 1024;
        this.phraseFilter = phraseFilter;
    }

    @Override
    public Optional<Collection<TestSpecification>> getTestSpecifications(Set<URL> testContent) {
        Preconditions.checkArgument(testContent != null && !testContent.isEmpty(), "filepaths cannot be null or empty!");
        List<TestSpecification> featureTestSpecifications = new LinkedList<>();
        List<PickleJar> pickleJars = pickleJarFactory.getPickleJars(testContent);
        for (PickleJar pickleJar : pickleJars) {
            Set<String> allTagsForTestCase = new HashSet<>();
            DefaultTestSpecification.Builder featureBuilder = new DefaultTestSpecification.Builder(pickleJar.getLocation().toString(), pickleJar.getLocation());
            // Create feature metadata
            ByteArrayOutputStream featureMetaData = new ByteArrayOutputStream();
            addBytesWithCorrectEncoding(featureMetaData, String.format("#language:%s%n", pickleJar.getLanguageCode()));
            if (!pickleJar.getFeatureTags().isEmpty()) {
                addBytesWithCorrectEncoding(featureMetaData, String.join(" ", pickleJar.getFeatureTags()) + "\n");
                allTagsForTestCase.addAll(pickleJar.getFeatureTags());
            }
            addBytesWithCorrectEncoding(featureMetaData, pickleJar.getFeatureTitle() + "\n");
            if (pickleJar.getLongDescription().isPresent()) {
                addBytesWithCorrectEncoding(featureMetaData, pickleJar.getLongDescription().get());
            }
            if (pickleJar.getBackground().isPresent()) {
                GherkinBackground bg = pickleJar.getBackground().get();
                if (bg.getSteps().isEmpty()) {
                    throw new IllegalStateException(String.format("Gherkin background had no steps!%n\tFeature Title: %s", pickleJar.getFeatureTitle()));
                }
                addBytesWithCorrectEncoding(featureMetaData, getBackgroundText(bg));
                logger.info( "%sTop level%s Background%s in %s%s", AnsiTerminalColorHelper.CYAN,  AnsiTerminalColorHelper.BRIGHT_CYAN, AnsiTerminalColorHelper.RESET, pickleJar.getLocation());
                Optional<List<FilteredPhrase>> filteredBackgroundStepBody = processStepBodyContent(bg.getSteps().get());
                if (filteredBackgroundStepBody.isPresent()) {
                    featureBuilder.withTestPhrases(filteredBackgroundStepBody.get());
                }
            }
            featureBuilder.withMetaData(new ByteArrayInputStream(featureMetaData.toByteArray()));
            List<TestSpecification> pickles = getGherkinStepSpecificationScenarios(pickleJar.getScenarios(),
                    allTagsForTestCase, pickleJar.getLocation());

            // Process all rules
            if (!pickleJar.getRules().isEmpty()) {
                pickles.addAll(transformRulesToTestSpecifications(pickleJar.getRules(), pickleJar.getLocation()));
            }
            featureBuilder.withChildTestSpecifications(pickles);
            featureTestSpecifications.add(new GherkinTestCaseSpecification(allTagsForTestCase, featureBuilder.build()));
        }
        return Optional.of(featureTestSpecifications);
    }

    private List<GherkinTestSpecification> transformScenariosToTestSpecifications(List<PickleJar.PickleJarScenario> scenarios, Set<String> parentTags, URL originalSourceLocation) {
        List<GherkinTestSpecification> gherkinTestSpecifications = new LinkedList<>();
        for (PickleJar.PickleJarScenario pickleJarScenario : scenarios) {
            DefaultTestSpecification.Builder topLevelScenario = new DefaultTestSpecification.Builder(pickleJarScenario.getTitleWithSubstitutions(), originalSourceLocation);
            // Provide metadata
            topLevelScenario.withMetaData(new ByteArrayInputStream(extractMetaData(pickleJarScenario).toByteArray()));
            // Process step body
            // transform step body into list of InputStreams
            Optional<TestSpecification> stepBodyAsTestSpecification = processStepBody(pickleJarScenario.getTitleWithSubstitutions(), pickleJarScenario.getStepsWithSubstitutions(), originalSourceLocation);
            if (stepBodyAsTestSpecification.isPresent()) {
                topLevelScenario.withTestPhrases(stepBodyAsTestSpecification.get().getFilteredPhrases().get());
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

    private Optional<List<FilteredPhrase>> processStepBodyContent(List<GherkinStep> stepBody) {
        List<InputStream> stepBodyAsStrings = stepBody.stream()
                .map(GherkinStep::getFullRawStepText)
                //.map(GherkinString::getRawString) //No substitutions are done on background steps
                .map(step -> new ByteArrayInputStream(step.getBytes(charset)))
                .collect(Collectors.toUnmodifiableList());
        return phraseFilter.filterPhrases(stepBodyAsStrings);
    }

    private Optional<TestSpecification> processStepBody(String title, List<String> stepBody, URL originalResourceLocation) {
        List<InputStream> stepBodyAsInputStream = stepBody.stream()
                .map(step -> new ByteArrayInputStream(step.getBytes(charset)))
                .collect(Collectors.toUnmodifiableList());
        Optional<List<FilteredPhrase>> phrases = phraseFilter.filterPhrases(stepBodyAsInputStream);
        if (phrases.isPresent()) {
            return Optional.of(new DefaultTestSpecification.Builder(title, originalResourceLocation).withPhrases(phrases.get()).build());
        } else {
            return Optional.empty();
        }
    }

    private List<TestSpecification> transformRulesToTestSpecifications(List<PickleJar.PickleJarRule> rules, URL originalSourceLocation) {
        List<TestSpecification> testSpecifications = new LinkedList<>();
        for (PickleJar.PickleJarRule rule : rules) {
            DefaultTestSpecification.Builder ruleBuilder = new DefaultTestSpecification.Builder(rule.getTitle(),originalSourceLocation);
            ByteArrayOutputStream ruleMetaData = new ByteArrayOutputStream();
            if (rule.getLongDescription().isPresent()) {
                addBytesWithCorrectEncoding(ruleMetaData, rule.getLongDescription().get());
                ruleBuilder.withMetaData(new ByteArrayInputStream(ruleMetaData.toByteArray()));
            }
            if (rule.getBackground().isPresent()) {
                // Nest the scenarios in a background TestSpecification
                GherkinBackground bg = rule.getBackground().get();
                addBytesWithCorrectEncoding(ruleMetaData, getBackgroundText(bg));
                logger.debug("%sRule Background%s in %s", AnsiTerminalColorHelper.CYAN,  AnsiTerminalColorHelper.RESET, rule.getTitle());
                Optional<List<FilteredPhrase>> filteredBackgroundStepBody = processStepBodyContent(bg.getSteps().orElseThrow());
                if (filteredBackgroundStepBody.isPresent()) {
                    ruleBuilder.withTestPhrases(filteredBackgroundStepBody.get());
                }
            }
            List<TestSpecification> filteredScenarios = getGherkinStepSpecificationScenarios(rule.getScenarios(), new HashSet<>(), originalSourceLocation);
            if (!filteredScenarios.isEmpty()) {
                ruleBuilder.withMetaData(new ByteArrayInputStream(ruleMetaData.toByteArray()));
                ruleBuilder.withChildTestSpecifications(filteredScenarios);
                testSpecifications.add(ruleBuilder.build());
            }
        }
        return testSpecifications;
    }

    private List<TestSpecification> getGherkinStepSpecificationScenarios(List<PickleJar.PickleJarScenario> scenarios, Set<String> parentTags, URL originalSourceLocation) {
        List<GherkinTestSpecification> gherkinTestSpecifications = new LinkedList<>();
        gherkinTestSpecifications.addAll(transformScenariosToTestSpecifications(scenarios, parentTags, originalSourceLocation));
        // Generics are not covarient. Cast to TestSpecification
        return gherkinTestSpecifications.stream()
                .map(TestSpecification.class::cast)
                .collect(Collectors.toList());
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
        if (background.getTitle().isPresent()) {
            backgroundText.append(background.getTitle().get().getRawString() + "\n");
        }
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

    @Override
    public Optional<Collection<TestSpecification>> filterGherkinTestSpecificationsByTagExpression(Collection<TestSpecification> testSpecification, String tagExpression) {
        if (tagExpression == null || tagExpression.isEmpty()) {
            return Optional.of(testSpecification);
        }
        Set<String> allPickleTags = new HashSet<>();
        List<TestSpecification> filteredSpecifications = new ArrayList<>(testSpecification.size());
        for (TestSpecification specification : testSpecification) {
            Optional<TestSpecification> filteredValue = recursivelyGetTagsAndFilterPickles(specification, allPickleTags, tagExpression, specification.getOriginalTestResource());
            if (filteredValue.isPresent()) {
                filteredSpecifications.add(filteredValue.get());
            }
        }
        return !filteredSpecifications.isEmpty() ? Optional.of(filteredSpecifications) : Optional.empty();
    }

    private Optional<TestSpecification> recursivelyGetTagsAndFilterPickles(TestSpecification testSpecification, Set<String> allParentTags, String tagExpression, URL originalSourceLocation) {
        Set<String> allGherkinItemTags = new HashSet<>(allParentTags);
        DefaultTestSpecification.Builder builder = new DefaultTestSpecification.Builder(testSpecification.getName(), originalSourceLocation);
        // See if this is a gherkin test specification we can get tags from
        if (testSpecification instanceof GherkinTestSpecification) {
            allGherkinItemTags.addAll(((GherkinTestSpecification) testSpecification).getTags());
        }
        // If there are child nodes then this is a container, feature, scenario outline or rule
        if (testSpecification.nestedTestSpecifications().isPresent()) {
            List<TestSpecification> filteredChildren = new LinkedList<>();
            for (TestSpecification childTestSpecification : testSpecification.nestedTestSpecifications().get()) {
                Optional<TestSpecification> filteredChildTestOptional =
                        recursivelyGetTagsAndFilterPickles(childTestSpecification,
                                allGherkinItemTags, tagExpression, originalSourceLocation);
                if (filteredChildTestOptional.isPresent()) {
                    filteredChildren.add(filteredChildTestOptional.get());
                }
            }
            if (!filteredChildren.isEmpty()) {
                builder.withChildTestSpecifications(filteredChildren);
            }
        } else { // This is a leaf node, i.e a pickle
            // See if this pickle matches the tag expression or not
            if (!new GherkinTagsVisitorImpl().tagExpressionMatchesPickle(allGherkinItemTags, tagExpression)) {
                return Optional.empty();
            } else {
                builder.withTestPhrases(testSpecification.getFilteredPhrases().orElseThrow()); // scenario must have steps
            }
        }
        if (testSpecification.getMetaData().isPresent()) {
            builder.withMetaData(testSpecification.getMetaData().get());
        }
        if (testSpecification.getFilteredPhrases().isPresent()) {
            builder.withTestPhrases(testSpecification.getFilteredPhrases().get());
        }
        if (testSpecification.getFilteredPhrases().isEmpty() && testSpecification.nestedTestSpecifications().isEmpty()) {
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
