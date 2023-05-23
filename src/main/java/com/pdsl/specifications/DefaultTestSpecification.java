package com.pdsl.specifications;

import com.google.common.base.Preconditions;

import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * A standard test specification suitable for PDSL test case factories.
 */
public final class DefaultTestSpecification implements TestSpecification {

    private final String id;
    private final Optional<List<FilteredPhrase>> phrases;
    private final Optional<InputStream> metaData;
    private final Optional<List<TestSpecification>> childItems;
    private final URI originalTestResource;

    private DefaultTestSpecification(Builder builder) {
        this.id = builder.id;
        this.phrases = builder.phrases;
        this.metaData = builder.metaData;
        this.childItems = builder.childItems;
        this.originalTestResource = builder.originalTestResource;
    }

    @Override
    public Optional<InputStream> getMetaData() {
        return metaData;
    }

    @Override
    public Optional<List<TestSpecification>> nestedTestSpecifications() {
        return childItems;
    }

    @Override
    public String getName() {
        return id;
    }

    @Override
    public Optional<List<FilteredPhrase>> getFilteredPhrases() {
        return phrases;
    }

    @Override
    public URI getOriginalTestResource() {
        return originalTestResource;
    }

    /**
     * A builder for creating TestSpecifications.
     */
    public static class Builder {
        private final String id;
        private Optional<List<FilteredPhrase>> phrases = Optional.empty();
        private Optional<InputStream> metaData = Optional.empty();
        private Optional<List<TestSpecification>> childItems = Optional.empty();
        private final URI originalTestResource;

        /**
         * Creates a builder for test specifications.
         *
         * @param id specification id
         * @param originalTestResource original location the specification was derived from
         */
        public Builder(String id, URI originalTestResource) {
            Preconditions.checkArgument(id != null && !id.isEmpty(), "Test Specification ID cannot be empty or null!");
            this.id = id;
            this.originalTestResource = originalTestResource;
        }

        /**
         * Creates a test specification.
         *
         * @return a test specification
         */
        public DefaultTestSpecification build() {
            Preconditions.checkArgument(!phrases.isEmpty() || !childItems.isEmpty(), "Phrases cannot be empty if you are not providing childItems!\n"
                    + "Use the other constructor if you have childItems or provide phrases");
            Preconditions.checkNotNull(id);
            Preconditions.checkNotNull(originalTestResource);
            // Make sure all child specifications meet criteria
            childItems.ifPresent(childList -> childList.stream().forEach(this::validateTestSpecification));
            return new DefaultTestSpecification(this);
        }

        private void validateTestSpecification(TestSpecification specification) {
            Preconditions.checkArgument(!phrases.isEmpty() || !childItems.isEmpty(), "Phrases cannot be empty if you are not providing childItems!\n"
                + "Use the other constructor if you have childItems or provide phrases");
            Preconditions.checkNotNull(id);
            Preconditions.checkNotNull(originalTestResource);
            specification.nestedTestSpecifications().ifPresent(childSpecs -> childSpecs.stream().forEach(this::validateTestSpecification));
        }

        /**
         * Sets the executable phrases in the specification.
         *
         * @param phrases phrases to execute
         * @return this builder
         */
        public Builder withTestPhrases(List<FilteredPhrase> phrases) {
            Preconditions.checkArgument(!phrases.isEmpty(), "Phrases cannot be empty!");
            this.phrases = Optional.of(phrases);
            return this;
        }

        /**
         * Sets child specifications to nest in the created specification.
         *
         * @param specifications child specifications
         * @return this builder
         */
        public Builder withChildTestSpecifications(List<TestSpecification> specifications) {
            Preconditions.checkArgument(!specifications.isEmpty(), "Test Specifications cannot be empty!");
            this.childItems = Optional.of(specifications);
            return this;
        }

        /**
         * Sets the metadata for the specification
         *
         * @param metaData for the specification
         * @return this builder
         */
        public Builder withMetaData(InputStream metaData) {
            this.metaData = Optional.of(metaData);
            return this;
        }

        /**
         * Sets executable phrases for this specification.
         * @param phrases to execute
         * @return this builder
         */
        public Builder withPhrases(List<FilteredPhrase> phrases) {
            Preconditions.checkArgument(!phrases.isEmpty(), "phrases cannot be empty!");
            this.phrases = Optional.of(phrases); // TODO: Make a defensive copy
            return this;
        }

        /**
         * Sets child specifications.
         *
         * @param childItems to nest in the specification
         */
        public void withChildItems(List<TestSpecification> childItems) {
            this.childItems = Optional.of(childItems);
        }
    }

}
