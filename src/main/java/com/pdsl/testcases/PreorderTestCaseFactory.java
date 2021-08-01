package com.pdsl.testcases;

import com.pdsl.specifications.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A factory that processes Test Specifications doing a Preorder traversal
 */
public class PreorderTestCaseFactory implements TestCaseFactory, TraceableTestCaseFactory {

    @Override
    public Collection<TestCase> processTestSpecification(Collection<TestSpecification> testSpecifications) {
        Collection<TestCase> testCases = new ArrayList<>(testSpecifications.size()); // Capacity likely too small
        for (TestSpecification testSpecification : testSpecifications) {
            List<TestSection> testBody = new ArrayList<>();
            testCases.addAll(recursiveWalkAndCreateOnLeaf(testSpecification, testBody, Optional.empty(),
                    testSpecification.getId(), new Accumulator()));
        }
        return testCases;
    }

    private List<TestCase> recursiveWalkAndCreateOnLeaf(TestSpecification testSpecification,
                                                        List<TestSection> parentTestSections,
                                                        Optional<InputStream> parentMetaData,
                                                        String rootId,
                                                        Accumulator accumulator) {
        List<TestSection> testSections = new LinkedList<>(parentTestSections);
        Optional<InputStream> childMetaData = testSpecification.getMetaData();
        List<TestCase> testCases = new ArrayList<>();
        if (parentMetaData.isPresent() && childMetaData.isPresent()) {
            try {
                byte[] parentData = parentMetaData.get().readAllBytes();
                byte[] childData = childMetaData.get().readAllBytes();
                byte[] combinedStream = new byte[parentData.length + childData.length];
                System.arraycopy(combinedStream, 0, parentData, 0, parentData.length);
                System.arraycopy(combinedStream, parentData.length, childData, 0, childData.length);
                childMetaData = Optional.of(new ByteArrayInputStream(combinedStream));
            } catch (IOException e) {
                throw new PolymorphicDslTransformationException("Could not get metadata from test specification!", e);
            }
        } else if (parentMetaData.isPresent()) {
            childMetaData = parentMetaData;
        }

        if (testSpecification.getFilteredPhrases().isPresent()) {
            List<FilteredPhrase> filteredPhrases = testSpecification.getFilteredPhrases().get();
            List<Phrase> phrases = new ArrayList<>();
            for (int i=0;  i < filteredPhrases.size(); i++) {
                FilteredPhrase filteredPhrase = filteredPhrases.get(i);
                if (filteredPhrase.getParseTree().isPresent()) {
                    phrases.add(new DefaultPhrase(filteredPhrase.getParseTree().get(), i));
                }
            }
            if (childMetaData.isPresent()) {
                testSections.add(new DefaultTestSection(childMetaData.get(), phrases.get(0)));
                if (phrases.size() > 1) {
                    phrases = phrases.subList(1, phrases.size());
                    List<TestSection> sections = phrases.stream()
                            .map(DefaultTestSection::new)
                            .collect(Collectors.toList());
                    testSections.addAll(sections);
                }
            } else {
                List<TestSection> sections = phrases.stream()
                        .map(DefaultTestSection::new)
                        .collect(Collectors.toList());
                testSections.addAll(sections);
            }
        }
        if (testSpecification.nestedTestSpecifications().isPresent()) {
            for (TestSpecification childTestSpecification : testSpecification.nestedTestSpecifications().get()) {
                testCases.addAll(recursiveWalkAndCreateOnLeaf(childTestSpecification, testSections, childMetaData, rootId, accumulator));
            }
            return testCases;
        } else {
            DefaultPdslTestCase testCase = new DefaultPdslTestCase(rootId + accumulator.nextInt(), testSections);
            List<TestCase> singleTestCase = new ArrayList<>(1);
            singleTestCase.add(testCase);
            return singleTestCase;
        }
    }

    @Override
    public TraceableTestData processTestSpecificationToTraceable(Collection<TestSpecification> testSpecifications) {
        return null;
    }

    private static class Accumulator {
        private int testNumber = 0;

        public int nextInt() {
            return testNumber++;
        }
    }
}
