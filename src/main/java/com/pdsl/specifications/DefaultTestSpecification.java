package com.pdsl.specifications;

import com.google.common.base.Preconditions;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.*;

public final class DefaultTestSpecification implements TestSpecification {

    private final String id;
    private final Optional<List<ParseTree>> phrases;
    private final Optional<ByteArrayOutputStream> metaData;
    private final Optional<List<? extends TestSpecification>> childItems;

    private DefaultTestSpecification(Builder builder) {
        this.id = builder.id;
        this.phrases = builder.phrases;
        this.metaData = builder.metaData;
        this.childItems = builder.childItems;
    }

    public static class Builder {
        private final String id;
        private Optional<List<ParseTree>> phrases = Optional.empty();
        private Optional<ByteArrayOutputStream> metaData = Optional.empty();
        private Optional<List<? extends TestSpecification>> childItems = Optional.empty();

        public Builder(String id) {
            Preconditions.checkArgument(id != null && !id.isEmpty(), "Test Specification ID cannot be mepty or null!");
            this.id = id;
        }

        public DefaultTestSpecification build() {
            Preconditions.checkArgument(!phrases.isEmpty() || !childItems.isEmpty(), "Phrases cannot be empty if you are not providing childItems!\n"
                    + "Use the other constructor if you have childItems or provide phrases");
            return new DefaultTestSpecification(this);
        }

        public Builder withTestPhrases(List<ParseTree> phrases) {
            Preconditions.checkArgument(!phrases.isEmpty(), "Phrases cannot be empty!");
            this.phrases = Optional.of(phrases);
            return this;
        }

        public Builder withChildTestSpecifications(List<TestSpecification> specifications) {
            Preconditions.checkArgument(!specifications.isEmpty(), "Phrases cannot be empty!");
            this.childItems = Optional.of(specifications);
            return this;
        }

        public Builder withMetaData(ByteArrayOutputStream metaData) {
            this.metaData = Optional.of(metaData);
            return this;
        }

        public Builder withPhrases(List<ParseTree> phrases) {
            Preconditions.checkArgument(!phrases.isEmpty(), "phrases cannot be empty!");
            this.phrases = Optional.of(phrases); // TODO: Make a defensive copy
            return this;
        }

        public void withChildItems(List<? extends TestSpecification> childItems) {
            this.childItems = Optional.of(childItems);
        }
    }

    @Override
    public Optional<ByteArrayOutputStream> getMetaData() {
        return metaData;
    }

    @Override
    public Optional<List<? extends TestSpecification>> nestedTestSpecifications() {
        return childItems;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Optional<Iterator<ParseTree>> getPhraseIterator() {
        return phrases.isPresent() ? Optional.of(phrases.get().iterator()) : Optional.empty();
    }

    @Override
    public Optional<List<ParseTree>> getPhrases() {
        return phrases;
    }

}
