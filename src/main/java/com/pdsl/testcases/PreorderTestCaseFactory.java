package com.pdsl.testcases;

import com.pdsl.runners.TestSpecificationFactoryGenerator;
import com.pdsl.specifications.LineDelimitedTestSpecificationFactory;
import com.pdsl.specifications.PolymorphicDslTransformationException;
import com.pdsl.specifications.TestSpecification;

import javax.inject.Provider;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * A factory that processes Test Specifications doing a Preorder traversal.
 *
 * It is capable of producing multiple test cases from a single test specification.
 */
public class PreorderTestCaseFactory implements TestCaseFactory {

    public static final class DefaultProvider implements Provider<TestCaseFactory> {
        private static final PreorderTestCaseFactory INSTANCE = new PreorderTestCaseFactory();
        @Override
        public PreorderTestCaseFactory get() {
            return INSTANCE;
        }
    }

    @Override
    public Collection<TestCase> processTestSpecification(Collection<TestSpecification> testSpecifications) {
        Collection<TestCase> testCases = new ArrayList<>(testSpecifications.size()); // Will likely need to be resized regardless
        for (TestSpecification testSpecification : testSpecifications) {
            testCases.addAll(recursiveWalkAndCreateOnLeaf(testSpecification, new ArrayList<>(), Optional.empty(),
                    testSpecification.getName(), new Accumulator()));
        }
        return testCases;
    }

    private List<TestCase> recursiveWalkAndCreateOnLeaf(TestSpecification testSpecification,
                                                        List<TestBodyFragment> parentTestBodyFragments,
                                                        Optional<InputStream> parentMetaData,
                                                        String rootId,
                                                        Accumulator accumulator) {
        List<TestBodyFragment> childTestBodyFragments = new ArrayList<>(parentTestBodyFragments);
        Optional<InputStream> childMetaData = testSpecification.getMetaData();
        List<TestCase> testCases = new ArrayList<>();
        // Get Metadata
        if (parentMetaData.isPresent() && childMetaData.isPresent()) {
            childMetaData = Optional.of(combineMetadata(parentMetaData.get(), childMetaData.get()));
        } else if (parentMetaData.isPresent()) {
            childMetaData = parentMetaData;
        }
        // Add phrases in this node if present
        if (testSpecification.getFilteredPhrases().isPresent()) {
            childTestBodyFragments.add(new TestBodyFragment(childMetaData.isPresent() ? childMetaData.get() : null, testSpecification.getFilteredPhrases().get()));
        }
        // Add phrases in child node if present
        if (testSpecification.nestedTestSpecifications().isPresent()) {
            for (TestSpecification childTestSpecification : testSpecification.nestedTestSpecifications().get()) {
                testCases.addAll(recursiveWalkAndCreateOnLeaf(childTestSpecification, childTestBodyFragments, childMetaData, rootId, accumulator));
            }
            return testCases;
        } else {
            DefaultPdslTestCase testCase = new DefaultPdslTestCase(rootId + accumulator.nextInt(), childTestBodyFragments);
            List<TestCase> singleTestCase = new ArrayList<>(1);
            singleTestCase.add(testCase);
            return singleTestCase;
        }
    }

    private InputStream combineMetadata(InputStream parentMetaData, InputStream childMetaData) {
        try {
            byte[] parentData = parentMetaData.readAllBytes();
            byte[] childData = childMetaData.readAllBytes();
            byte[] combinedStream = new byte[parentData.length + childData.length];
            System.arraycopy(combinedStream, 0, parentData, 0, parentData.length);
            System.arraycopy(combinedStream, parentData.length, childData, 0, childData.length);
            return new ByteArrayInputStream(combinedStream);
        } catch (IOException e) {
            throw new PolymorphicDslTransformationException("Could not get metadata from test specification!", e);
        }
    }

    private static class Accumulator {
        private int testNumber = 0;
        public int nextInt() {
            return testNumber++;
        }
    }
}
