package com.pdsl.testcases;

import com.pdsl.gherkin.filter.TagFilterer;
import com.pdsl.specifications.PolymorphicDslTransformationException;
import com.pdsl.specifications.TaggedTestSpecification;
import com.pdsl.specifications.TestSpecification;

import javax.inject.Provider;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.Supplier;

/**
 * A factory that processes Test Specifications doing a Preorder traversal.
 *
 * It is capable of producing multiple test cases from a single test specification.
 */
public class PreorderTestCaseFactory implements TestCaseFactory {

    private static final PreorderTestCaseFactory INSTANCE = new PreorderTestCaseFactory();

    public static final class DefaultProvider implements Provider<TestCaseFactory> {

        @Override
        public PreorderTestCaseFactory get() {
            return INSTANCE;
        }
    }

    public static final class DefaultSupplier implements Supplier<TestCaseFactory> {

        @Override
        public TestCaseFactory get() {
            return INSTANCE;
        }
    }

    @Override
    public Collection<TestCase> processTestSpecification(Collection<TestSpecification> testSpecifications) {
        Collection<TestCase> testCases = new ArrayList<>(testSpecifications.size()); // Will likely need to be resized regardless
        for (TestSpecification testSpecification : testSpecifications) {
            testCases.addAll(recursiveWalkAndCreateOnLeaf(testSpecification, new ArrayList<>(), new ArrayList<>(), Optional.empty(), new Accumulator()));
        }
        return testCases;
    }

    private List<TestCase> recursiveWalkAndCreateOnLeaf(TestSpecification testSpecification,
                                                        Collection<String> tags,
                                                        List<TestBodyFragment> parentTestBodyFragments,
                                                        Optional<InputStream> parentMetaData,
                                                        Accumulator accumulator) {
        List<TestBodyFragment> childTestBodyFragments = new ArrayList<>(parentTestBodyFragments);
        Optional<InputStream> childMetaData = testSpecification.getMetaData();
        List<TestCase> testCases = new ArrayList<>();
        if (testSpecification instanceof TaggedTestSpecification) {
            tags.addAll(((TaggedTestSpecification)testSpecification).getTags());
        }
        // Get Metadata
        if (parentMetaData.isPresent() && childMetaData.isPresent()) {
            childMetaData = Optional.of(combineMetadata(parentMetaData.get(), childMetaData.get()));
        } else if (parentMetaData.isPresent() && childMetaData.isEmpty()) {
            childMetaData = parentMetaData;
        }
        // Add phrases in this node if present
        if (testSpecification.getFilteredPhrases().isPresent()) {
            childTestBodyFragments.add(new TestBodyFragment(childMetaData.isPresent() ? childMetaData.get() : null, testSpecification.getFilteredPhrases().get()));
        }
        // Add phrases in child node if present
        if (testSpecification.nestedTestSpecifications().isPresent()) {
            for (TestSpecification childTestSpecification : testSpecification.nestedTestSpecifications().get()) {
                testCases.addAll(recursiveWalkAndCreateOnLeaf(childTestSpecification, new ArrayList<>(tags), childTestBodyFragments, childMetaData, accumulator));
            }
            return testCases;
        } else {
          TestCase testCase = new DefaultPdslTestCase(testSpecification.getName(), childTestBodyFragments, testSpecification.getOriginalTestResource());
          if (!tags.isEmpty()) {
            testCase = new DefaultTaggedTestCase(testCase, tags);
          }
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
            System.arraycopy(parentData, 0, combinedStream, 0, parentData.length);
            System.arraycopy(childData, 0, combinedStream, parentData.length, childData.length);
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
