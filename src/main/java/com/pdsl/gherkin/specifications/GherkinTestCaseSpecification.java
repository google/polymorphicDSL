package com.pdsl.gherkin.specifications;

import com.pdsl.specifications.TestSpecification;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class GherkinTestCaseSpecification implements TestSpecification {

    private TestSpecification testSpecification;
    private Set<String> tags;

    public GherkinTestCaseSpecification(Set<String> tags, TestSpecification testSpecification) {
        this.testSpecification = testSpecification;
        this.tags = tags;
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
