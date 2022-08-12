package com.pdsl.gherkin;

import com.google.common.base.Preconditions;
import com.pdsl.gherkin.models.GherkinBackground;
import com.pdsl.gherkin.models.GherkinStep;
import com.pdsl.gherkin.filter.GherkinTagsVisitorImpl;
import com.pdsl.gherkin.specifications.GherkinTestSpecification;
import com.pdsl.gherkin.specifications.GherkinTestSpecificationFactory;
import com.pdsl.gherkin.testcases.GherkinTestCaseSpecification;
import com.pdsl.runners.PdslTest;
import com.pdsl.runners.RecognizedBy;
import com.pdsl.logging.AnsiTerminalColorHelper;
import com.pdsl.specifications.DefaultTestSpecification;
import com.pdsl.specifications.FilteredPhrase;
import com.pdsl.specifications.PolymorphicDslTransformationException;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.transformers.PolymorphicDslFileException;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import com.pdsl.transformers.TestSpecificationHelper;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class DefaultGherkinTestSpecificationFactory implements GherkinTestSpecificationFactory {

    private static final Logger logger = LoggerFactory.getLogger(DefaultGherkinTestSpecificationFactory.class);
    private final int DESCRIPTION_MAX_LENGTH;
    private final PolymorphicDslPhraseFilter phraseFilter;
    private final PickleJarFactory pickleJarFactory;
    private final Charset charset;
    private final Optional<? extends Class<? extends Parser>> recognizerParser;
    private final Optional<? extends Class<? extends Lexer>> recognizerLexer;
    private final Optional<String> recognizerRule;

    private DefaultGherkinTestSpecificationFactory(Builder builder) {
        if (builder.recognizerParser.isPresent() ^ builder.recognizerLexer.isPresent()) {
            throw new IllegalArgumentException("If a recognizer is used then BOTH the recognizerParser and recognizerLexer must be set!");
        }
        this.DESCRIPTION_MAX_LENGTH = builder.maxDescriptionLength;
        this.phraseFilter = builder.phraseFilter;
        this.pickleJarFactory = builder.pickleJarFactory;
        this.charset = builder.charset;
        this.recognizerParser = builder.recognizerParser;
        this.recognizerLexer = builder.recognizerLexer;
        this.recognizerRule = builder.recognizerRule;
    }
    public static class Builder {
        private int maxDescriptionLength = 1024;
        private final PolymorphicDslPhraseFilter phraseFilter;
        private PickleJarFactory pickleJarFactory = PickleJarFactory.DEFAULT;
        private Charset charset = Charset.defaultCharset();
        private Optional<? extends Class<? extends Parser>> recognizerParser = Optional.empty();
        private Optional<? extends Class<? extends Lexer>> recognizerLexer = Optional.empty();
        private Optional<String> recognizerRule = Optional.of(RecognizedBy.DEFAULT_RECOGNIZER_RULE_NAME);

        public Builder(PolymorphicDslPhraseFilter polymorphicDslPhrasefilter) {
            Preconditions.checkNotNull(polymorphicDslPhrasefilter);
            phraseFilter = polymorphicDslPhrasefilter;
        }

        public Builder withMaxDescriptionLength(int maxDescriptionLength) {
            this.maxDescriptionLength = maxDescriptionLength;
            return this;
        }

        public Builder withPickleJarFactory(PickleJarFactory pickleJarFactory) {
            Preconditions.checkNotNull(pickleJarFactory);
            this.pickleJarFactory = pickleJarFactory;
            return this;
        }

        public Builder withCharset(Charset charset) {
            Preconditions.checkNotNull(charset);
            this.charset = charset;
            return this;
        }

        public Builder withRecognizerParser(Class<? extends Parser> parserClass) {
            Preconditions.checkNotNull(parserClass);
            recognizerParser = Optional.of(parserClass);
            return this;
        }

        public Builder withRecognizerLexer(Class<? extends Lexer> lexerClass) {
            Preconditions.checkNotNull(lexerClass);
            recognizerLexer = Optional.of(lexerClass);
            return this;
        }

        public Builder withRecognizerRule(String rule) {
            Preconditions.checkNotNull(rule);
            recognizerRule = Optional.of(rule);
            return this;
        }

        public DefaultGherkinTestSpecificationFactory build() {
            return new DefaultGherkinTestSpecificationFactory(this);
        }
    }

    @Override
    public Optional<Collection<TestSpecification>> getTestSpecifications(Set<URI> testContent) {
        Preconditions.checkArgument(testContent != null && !testContent.isEmpty(), "filepaths cannot be null or empty!");
        List<TestSpecification> featureTestSpecifications = new ArrayList<>();
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

    private List<GherkinTestSpecification> transformScenariosToTestSpecifications(List<PickleJar.PickleJarScenario> scenarios, Set<String> parentTags, URI originalSourceLocation) {
        List<GherkinTestSpecification> gherkinTestSpecifications = new ArrayList<>();
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

    private void checkGrammar(List<InputStream> stepBodyAsStrings) {
        if (recognizerParser.isPresent() && recognizerLexer.isPresent()) {
            stepBodyAsStrings = TestSpecificationHelper.checkGrammarValidity(recognizerParser.get(), recognizerLexer.get(), stepBodyAsStrings,
                    recognizerRule.isPresent() ? recognizerRule.get() : PdslTest.DEFAULT_ALL_RULE);
        }
    }

    private Optional<List<FilteredPhrase>> processStepBodyContent(List<GherkinStep> stepBody) {
        List<InputStream> stepBodyAsStrings = stepBody.stream()
                .map(GherkinStep::getFullRawStepText)
                .map(step -> new ByteArrayInputStream(step.getBytes(charset)))
                .collect(Collectors.toUnmodifiableList());
        checkGrammar(stepBodyAsStrings);
        return phraseFilter.filterPhrases(stepBodyAsStrings);
    }

    private Optional<TestSpecification> processStepBody(String title, List<String> stepBody, URI originalResourceLocation) {
        List<InputStream> stepBodyAsInputStream = stepBody.stream()
                .map(step -> new ByteArrayInputStream(step.getBytes(charset)))
                .collect(Collectors.toUnmodifiableList());
        checkGrammar(stepBodyAsInputStream);
        stepBodyAsInputStream.forEach(i -> {
            try {
                i.reset();
            } catch (IOException e) {
                throw new PolymorphicDslTransformationException("Could not reset input streams to prepare for filtering", e);
            }
        });
        Optional<List<FilteredPhrase>> phrases = phraseFilter.filterPhrases(stepBodyAsInputStream);
        if (phrases.isPresent()) {
            return Optional.of(new DefaultTestSpecification.Builder(title, originalResourceLocation).withPhrases(phrases.get()).build());
        } else {
            return Optional.empty();
        }
    }

    private List<TestSpecification> transformRulesToTestSpecifications(List<PickleJar.PickleJarRule> rules, URI originalSourceLocation) {
        List<TestSpecification> testSpecifications = new ArrayList<>();
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

    private List<TestSpecification> getGherkinStepSpecificationScenarios(List<PickleJar.PickleJarScenario> scenarios, Set<String> parentTags, URI originalSourceLocation) {
        List<GherkinTestSpecification> gherkinTestSpecifications = new ArrayList<>();
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

    private Optional<TestSpecification> recursivelyGetTagsAndFilterPickles(TestSpecification testSpecification, Set<String> allParentTags, String tagExpression, URI originalSourceLocation) {
        Set<String> allGherkinItemTags = new HashSet<>(allParentTags);
        DefaultTestSpecification.Builder builder = new DefaultTestSpecification.Builder(testSpecification.getName(), originalSourceLocation);
        // See if this is a gherkin test specification we can get tags from
        if (testSpecification instanceof GherkinTestSpecification) {
            allGherkinItemTags.addAll(((GherkinTestSpecification) testSpecification).getTags());
        }
        // If there are child nodes then this is a container, feature, scenario outline or rule
        if (testSpecification.nestedTestSpecifications().isPresent()) {
            List<TestSpecification> filteredChildren = new ArrayList<>();
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
            return Optional.of(new GherkinTestSpecification(builder.build(), allGherkinItemTags));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
