package com.pdsl.testcases;

import com.pdsl.specifications.FilteredPhrase;
import com.pdsl.specifications.TestSpecification;

import javax.inject.Provider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A test case factory where parent phrases are processed before child phrases in the test specification using preorder traversal.
 *
 * This factory will only produce one test case per test resource.
 */
public class SingleTestOutputPreorderTestCaseFactory implements TestCaseFactory {

    public static final class DefaultProvider implements Provider<TestCaseFactory> {
        private static final SingleTestOutputPreorderTestCaseFactory INSTANCE = new SingleTestOutputPreorderTestCaseFactory();
        @Override
        public SingleTestOutputPreorderTestCaseFactory get() {
            return INSTANCE;
        }
    }

    @Override
    public Collection<TestCase> processTestSpecification(Collection<TestSpecification> testCaseSpecifications) {
        Collection<TestCase> testCases = new ArrayList<>(testCaseSpecifications.size());
        for (TestSpecification testCaseSpecification : testCaseSpecifications) {
            List<TestCase> result = new ArrayList<>(1);
            result.add(new DefaultPdslTestCase(testCaseSpecification.getName(), List.of(new TestBodyFragment(
                    recursivelyWalkSpecification(testCaseSpecification, new ArrayList<>())))));
            testCases.addAll(result);
        }
        return testCases;
    }
    private List<FilteredPhrase> recursivelyWalkSpecification(TestSpecification testSpecification, List<FilteredPhrase> parentTestSections) {
        List<FilteredPhrase> filteredPhrases = new ArrayList<>();
        if (testSpecification.getFilteredPhrases().isPresent()) {
            filteredPhrases.addAll(testSpecification.getFilteredPhrases().get());
        }
        if (testSpecification.nestedTestSpecifications().isPresent()) {
            for (TestSpecification childSpecification : testSpecification.nestedTestSpecifications().get()) {
                filteredPhrases.addAll(recursivelyWalkSpecification(childSpecification, filteredPhrases));
            }
        }
        return filteredPhrases;
    }
}
