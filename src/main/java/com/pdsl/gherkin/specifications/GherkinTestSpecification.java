package com.pdsl.gherkin.specifications;

import com.pdsl.specifications.TestSpecification;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
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

    public Set<String> getTags() {
        return tags;
    }

    @Override
    public Optional<ByteArrayOutputStream> getMetaData() {
        return testSpecification.getMetaData();
    }

    @Override
    public Optional<List<? extends TestSpecification>> nestedTestSpecifications() {
        return testSpecification.nestedTestSpecifications();
    }

    @Override
    public String getId() {
        return testSpecification.getId();
    }

    @Override
    public Optional<Iterator<ParseTree>> getPhraseIterator() {
        return testSpecification.getPhraseIterator();
    }

    @Override
    public Optional<List<ParseTree>> getPhrases() {
        return testSpecification.getPhrases();
    }
}
