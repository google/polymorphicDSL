package com.pdsl.gherkin.specifications;

import com.pdsl.specifications.DefaultTestSpecification;
import com.pdsl.specifications.FilteredPhrase;
import com.pdsl.specifications.TestSpecification;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class GherkinTestCaseSpecification implements TestSpecification {

    private final Set<String> tags;
    private final TestSpecification testSpecification;

    public GherkinTestCaseSpecification(Set<String> tags, TestSpecification testSpecification) {
        this.testSpecification = testSpecification;
        this.tags = tags;
    }

    public GherkinTestCaseSpecification(List<GherkinTestCaseSpecification> childFeatures) {
        this.testSpecification = new DefaultTestSpecification.Builder("Gherkin Test Container")
                .withChildTestSpecifications(new ArrayList<>(childFeatures))
                .build();
        this.tags = Set.of();
    }

    public Set<String> getTags() {
        return tags;
    }

    @Override
    public Optional<InputStream> getMetaData() {
        return testSpecification.getMetaData();
    }

    @Override
    public Optional<List<TestSpecification>> nestedTestSpecifications() {
        return testSpecification.nestedTestSpecifications();
    }

    @Override
    public String getId() {
        return testSpecification.getId();
    }

    @Override
    public Optional<List<FilteredPhrase>> getFilteredPhrases() {
        return testSpecification.getFilteredPhrases();
    }
}
