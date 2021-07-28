package com.pdsl.testcases;

import com.google.common.base.Preconditions;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultPdslTestCase implements TestCase {

    private final List<TestSection> testBody;
    private final String testCaseTitle;
    private int hashCodeId; //TODO: How do we deal with collisions?

    public DefaultPdslTestCase(String testCaseTitle, List<TestSection> testBody) {
        String errMessage = "Test case title cannot be empty or null!";
        Preconditions.checkNotNull(testCaseTitle, errMessage);
        Preconditions.checkArgument(!testCaseTitle.isEmpty(), errMessage);
        this.testBody = testBody;
        this.testCaseTitle = testCaseTitle;
        this.hashCodeId = testBody.stream()
                .map(testSection -> testSection.getParseTree().getText())
                .collect(Collectors.toUnmodifiableList())
                .hashCode();
    }

    @Override
    public long getTestCaseId() {
        return hashCodeId;
    }

    @Override
    public String getTestTitle() {
        return testCaseTitle;
    }

    @Override
    public int getBodySize() {
        return testBody.size();
    }

    @Override
    public Iterator<TestSection> getTestBody() {
        return testBody.listIterator();
    }
}
