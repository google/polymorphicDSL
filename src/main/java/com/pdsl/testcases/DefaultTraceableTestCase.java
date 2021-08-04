package com.pdsl.testcases;

import com.pdsl.specifications.Phrase;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultTraceableTestCase implements TraceableTestCase {

    private final int testCaseId;
    private final String testTitle;
    private final List<TestSection> testSections;

    //TODO: I think this is unnecessary? TestCases have an ID and the phrases have an index.
    public DefaultTraceableTestCase(int testCaseId, String testTitle, List<TestSection> testSections) {
        this.testSections = testSections;
        this.testTitle = testTitle;
        this.testCaseId = testCaseId;
    }

    @Override
    public long getTestCaseId() {
        return testCaseId;
    }

    @Override
    public String getTestTitle() {
        return testTitle;
    }

    @Override
    public Iterator<TestSection> getTestSectionIterator() {
        return testSections.iterator();
    }

    @Override
    public int getPhraseBodyId() {
        return 0;
    }
}
