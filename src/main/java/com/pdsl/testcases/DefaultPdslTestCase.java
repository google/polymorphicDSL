package com.pdsl.testcases;

import com.google.common.base.Preconditions;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

public class DefaultPdslTestCase implements TestCase {

    private final List<TestSection> testBody;
    private final String testCaseTitle;

    @Override
    public long getTestCaseId() {
        return testBody.stream()
                .map(testSection -> testSection.getParseTree().getText())
                .collect(Collectors.toUnmodifiableList())
                .hashCode();
    } //TODO: Find a method that won't potentially have collisions

    @Override
    public String getTestTitle() {
        return null;
    }

    @Override
    public int getBodySize() {
        return testBody.size();
    }

    @Override
    public Iterator<TestSection> getTestBody() {
        return testBody.listIterator();
    }

    public DefaultPdslTestCase(String testCaseTitle, List<TestSection> testBody) {
        String errMessage = "Test case title cannot be empty or null!";
        Preconditions.checkNotNull(testCaseTitle, errMessage);
        Preconditions.checkArgument(!testCaseTitle.isEmpty(), errMessage);
        this.testBody = testBody;
        this.testCaseTitle = testCaseTitle;
    }
}
