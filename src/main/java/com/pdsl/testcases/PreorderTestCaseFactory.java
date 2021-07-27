package com.pdsl.testcases;

import com.pdsl.specifications.TestSpecification;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A factory that processes Test Specifications doing a Preorder traversal
 */
public class PreorderTestCaseFactory implements TestCaseFactory {

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
                                                        Optional<ByteArrayOutputStream> parentMetaData,
                                                        String rootId,
                                                        Accumulator accumulator) {
        List<TestSection> testSections = new LinkedList<>(parentTestSections);
        Optional<ByteArrayOutputStream> childMetaData = testSpecification.getMetaData();
        List<TestCase> testCases = new ArrayList<>();
        if (parentMetaData.isPresent() && childMetaData.isPresent()) {
            ByteArrayOutputStream combinedStream = new ByteArrayOutputStream(parentMetaData.get().toByteArray().length + childMetaData.get().toByteArray().length);
            combinedStream.writeBytes(parentMetaData.get().toByteArray());
            combinedStream.writeBytes(childMetaData.get().toByteArray());
            childMetaData = Optional.of(combinedStream);
        } else if (parentMetaData.isPresent()) {
            childMetaData = parentMetaData;
        }

        if (testSpecification.getPhrases().isPresent()) {
            List<ParseTree> parseTrees = testSpecification.getPhrases().get();
            if (childMetaData.isPresent()) {
                testSections.add(new DefaultTestSection(childMetaData.get(), parseTrees.get(0)));
                if (parseTrees.size() > 1) {
                    parseTrees = parseTrees.subList(1, parseTrees.size());
                    List<TestSection> sections = parseTrees.stream()
                            .map(DefaultTestSection::new)
                            .collect(Collectors.toList());
                    testSections.addAll(sections);
                }
            } else {
                List<TestSection> sections = parseTrees.stream()
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

    private static class Accumulator {
        private int testNumber = 0;

        public int nextInt() {
            return testNumber++;
        }
    }
}
