package com.pdsl.testcases;

import com.pdsl.specifications.DefaultPhrase;
import com.pdsl.specifications.FilteredPhrase;
import com.pdsl.specifications.Phrase;
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
            List<TestSection> testSections = new ArrayList<>(16);
            List<TestCase> result = new ArrayList<>(1);
            testSections.addAll(recursivelyWalkSpecification(testCaseSpecification, testSections));
            result.add(new DefaultPdslTestCase(testCaseSpecification.getId(), testSections));
            testCases.addAll(result);
        }
        return testCases;
    }

    private List<TestSection> recursivelyWalkSpecification(TestSpecification testSpecification, List<TestSection> parentTestSections) {
        List<TestSection> testSections = new ArrayList<>(parentTestSections);
        if (testSpecification.getFilteredPhrases().isPresent()) {
            List<FilteredPhrase> filteredPhrases = testSpecification.getFilteredPhrases().get();

            for (int i=0; i < filteredPhrases.size(); i++) {
                FilteredPhrase filteredPhrase = filteredPhrases.get(i);
                if (filteredPhrase.getParseTree().isPresent()) {
                    Phrase phrase = new DefaultPhrase(filteredPhrase.getParseTree().get(), i);
                    TestSection testSection = testSpecification.getMetaData().isPresent() ? new DefaultTestSection(testSpecification.getMetaData().get(), phrase) : new DefaultTestSection(phrase);
                    testSections.add(testSection);
                }
            }
        }
        if (testSpecification.nestedTestSpecifications().isPresent()) {
            for (TestSpecification childSpecification : testSpecification.nestedTestSpecifications().get()) {
                testSections.addAll(recursivelyWalkSpecification(childSpecification, testSections));
            }
        }
        return testSections;
    }
}
