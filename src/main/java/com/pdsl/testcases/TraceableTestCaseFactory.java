package com.pdsl.testcases;

import com.pdsl.specifications.Phrase;
import com.pdsl.specifications.TestSpecification;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface TraceableTestCaseFactory extends TestCaseFactory {

    TraceableTestData processTestSpecificationToTraceable(Collection<TestSpecification> testSpecifications);

    public static class TraceableTestData {
        private final Map<Integer, List<Phrase>> phraseBodyMap;
        private final List<TraceableTestCase> testCases;        public TraceableTestData(Map<Integer, List<Phrase>> phraseBodyMap, List<TraceableTestCase> testCases) {
            this.phraseBodyMap = phraseBodyMap;
            this.testCases = testCases;
        }
        public Map<Integer, List<Phrase>> getPhraseBodyMap() {
            return phraseBodyMap;
        }
        public List<TraceableTestCase> getTraceableTestCases() {
            return testCases;
        }
    }
}
