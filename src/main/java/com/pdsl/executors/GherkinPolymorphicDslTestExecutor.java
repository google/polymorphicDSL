package com.pdsl.executors;

import com.google.common.base.Preconditions;
import com.pdsl.gherkin.GherkinTagFilterer;
import com.pdsl.gherkin.GherkinTagFiltererImpl;
import com.pdsl.gherkin.GherkinTestSpecification;
import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.reports.TestResultReportProvider;
import com.pdsl.reports.TestRunResults;
import com.pdsl.specifications.DefaultTestSpecification;
import com.pdsl.specifications.TestSpecification;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class GherkinPolymorphicDslTestExecutor implements PolymorphicDslTestExecutor, TestResultReportProvider {

    private static final Logger logger = LoggerFactory.getLogger(GherkinPolymorphicDslTestExecutor.class);
    private final String tagExpression;
    private final ParseTreeListener grammar;
    // The phrases that actually matter in the sub-component being tested
    private final ParseTreeListener subgrammar;
    private final GrammarExecutor grammarExecutor;
    private final GrammarExecutor subgrammarExecutor;
    private final GherkinTagFilterer gherkinTagFilterer;
    private final Charset charset;
    private Optional<TestRunResults> report = Optional.empty();

    private GherkinPolymorphicDslTestExecutor(Builder builder) {
        this.tagExpression = builder.tagExpression;
        this.grammar = builder.grammar;
        this.grammarExecutor = builder.grammarExecutor;
        this.subgrammar = builder.subgrammar;
        this.subgrammarExecutor = builder.subgrammarExecutor;
        this.gherkinTagFilterer = builder.gherkinTagFilterer;
        this.charset = builder.charset;
    }

    @Override
    public Optional<TestRunResults> getResults() {
        return report;
    }

    public static class Builder {
        private final GrammarExecutor DEFAULT_VERIFIER_EXECUTOR = new ParentForEachChildPhraseFullExecutor();
        private String tagExpression;
        private ParseTreeListener grammar;
        private ParseTreeListener subgrammar;
        private GrammarExecutor grammarExecutor = DEFAULT_VERIFIER_EXECUTOR;
        private GrammarExecutor subgrammarExecutor = DEFAULT_VERIFIER_EXECUTOR;
        private GherkinTagFilterer gherkinTagFilterer = new GherkinTagFiltererImpl();
        private Charset charset = StandardCharsets.UTF_8;

        public GherkinPolymorphicDslTestExecutor build() {
            Preconditions.checkNotNull(grammar, "Phrase registry cannot be null!");
            Preconditions.checkNotNull(subgrammar, "Phrases relevant to test context cannot be null!");
            Preconditions.checkNotNull(grammarExecutor, "Phrase registry verifier cannot be null!");
            Preconditions.checkNotNull(subgrammarExecutor, "Context executor cannot be null!");
            return new GherkinPolymorphicDslTestExecutor(this);
        }

        public Builder withTagExpression(String tagExpression) {
            Preconditions.checkNotNull(tagExpression, "Tag expression cannot be null!");
            this.tagExpression = tagExpression;
            return this;
        }

        public Builder withGherkinTagFilter(GherkinTagFilterer gherkinTagFilterer) {
            Preconditions.checkNotNull(gherkinTagFilterer, "Gherkin tag filter cannot be null!");
            this.gherkinTagFilterer = gherkinTagFilterer;
            return this;
        }

        public Builder withSubgrammarListener(ParseTreeListener parseTreeListener) {
            Preconditions.checkNotNull(parseTreeListener, "Subgrammar listener cannot be null!");
            this.subgrammar = parseTreeListener;
            return this;
        }

        public Builder withGrammarListener(ParseTreeListener phraseRegistry) {
            Preconditions.checkNotNull(phraseRegistry, "Grammar listener cannot be null!");
            this.grammar = phraseRegistry;
            return this;
        }

        public Builder withPhraseRegistryVerifier(GrammarExecutor phraseRegistryVerifier) {
            Preconditions.checkNotNull(phraseRegistryVerifier, "Phrase Registry Verifier cannot be null!");
            this.grammarExecutor = phraseRegistryVerifier;
            return this;
        }

        public Builder withSubgrammarxecutor(GrammarExecutor contextExecutor) {
            Preconditions.checkNotNull(contextExecutor, "Subgrammar Executor cannot be null!");
            this.subgrammarExecutor = contextExecutor;
            return this;
        }

        public Builder withVerifierExecutor(GrammarExecutor verifierExecutor) {
            Preconditions.checkNotNull(verifierExecutor, "Grammar executor cannot be null!");
            this.subgrammarExecutor = verifierExecutor;
            this.grammarExecutor = verifierExecutor;
            return this;
        }
    }

    @Override
    public PolymorphicDslTestRunResults runTests(TestSpecification testSpecification) {
        Optional<TestSpecification> filteredSpecifications = filterGherkinTestSpecificationsByTagExpression(testSpecification);
        if (filteredSpecifications.isEmpty()) {
            logger.warn("All of the tests were filtered out! No tests to run...");
            return new PolymorphicDslTestRunResults(System.out);
        }
        if (grammarExecutor == subgrammarExecutor) { // We're using the same object for both walks
            return grammarExecutor.processGrammarAndSubgrammar(filteredSpecifications.get(), grammar, subgrammar);
        } else {
            grammarExecutor.processGrammar(filteredSpecifications.get(), grammar);
            return subgrammarExecutor.processGrammar(filteredSpecifications.get(), subgrammar);
        }
    }

    private Optional<TestSpecification> filterGherkinTestSpecificationsByTagExpression(TestSpecification testSpecification) {
        if (tagExpression == null || tagExpression.isEmpty()) { return Optional.of(testSpecification); }
        Set<String> allPickleTags = new HashSet<>();
        return recursivelyGetTagsAndFilterPickles(testSpecification, allPickleTags);

    }

    private Optional<TestSpecification> recursivelyGetTagsAndFilterPickles(TestSpecification testSpecification, Set<String> allParentTags) {
        Set<String> allGherkinItemeTags = new HashSet<>(allParentTags);
        DefaultTestSpecification.Builder builder = new DefaultTestSpecification.Builder(testSpecification.getId());
        // See if this is a gherkin test specification we can get tags from
        if (testSpecification instanceof GherkinTestSpecification) {
            allGherkinItemeTags.addAll(((GherkinTestSpecification) testSpecification).getTags());
        }
        // If there are child nodes then this is a container, feature, scenario outline or rule
        if (testSpecification.nestedTestSpecifications().isPresent()) {
            List<TestSpecification> filteredChildren = new LinkedList<>();
            for (TestSpecification childTestSpecification : testSpecification.nestedTestSpecifications().get()) {
                Optional<TestSpecification> filteredChildTestOptional = recursivelyGetTagsAndFilterPickles(childTestSpecification, allGherkinItemeTags);
                if (filteredChildTestOptional.isPresent()) {
                    filteredChildren.add(filteredChildTestOptional.get());
                }
            }
            if (!filteredChildren.isEmpty()) {
                builder.withChildTestSpecifications(filteredChildren);
            }
        } else { // This is a leaf node, i.e a pickle
            // See if this pickle matches the tag expression or not
            if (!gherkinTagFilterer.tagExpressionMatchesPickle(allGherkinItemeTags, tagExpression, charset)) {
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
