package com.pdsl.testcases;

import com.pdsl.specifications.FilteredPhrase;
import com.pdsl.specifications.TestSpecification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This test case factory treats test specifications as a single script where the parents are read before the children.
 */
public class SingleTestOutputPreorderTestCaseFactory implements TestCaseFactory {

    @Override
    public Collection<TestCase> processTestSpecification(Collection<TestSpecification> testCaseSpecifications) {
        // Capacity does not necessarily match expected value
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
        List<FilteredPhrase> filteredPhrases = new ArrayList<>(parentTestSections);
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
