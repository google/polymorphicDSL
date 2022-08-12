package com.pdsl.gherkin.specifications;

import com.pdsl.specifications.DefaultTestSpecification;
import com.pdsl.specifications.FilteredPhrase;
import com.pdsl.specifications.TestSpecification;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class GherkinTestSpecification implements TestSpecification {

    private final Set<String> tags;
    private final TestSpecification testSpecification;

    public GherkinTestSpecification(TestSpecification testSpecification, Set<String> tags) {
        this.tags = tags;
        this.testSpecification = testSpecification;
    }

    public GherkinTestSpecification(List<GherkinTestSpecification> gherkinTestSpecifications, URI originalSourceUrl) {
        this.tags = Set.of();
        this.testSpecification = new DefaultTestSpecification.Builder("Gherkin test container", originalSourceUrl)
                .withChildTestSpecifications(new ArrayList<>(gherkinTestSpecifications))
                .build();
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
    public String getName() {
        return testSpecification.getName();
    }

    @Override
    public Optional<List<FilteredPhrase>> getFilteredPhrases() {
        return testSpecification.getFilteredPhrases();
    }

    @Override
    public URI getOriginalTestResource() {
        return testSpecification.getOriginalTestResource();
    }
}
