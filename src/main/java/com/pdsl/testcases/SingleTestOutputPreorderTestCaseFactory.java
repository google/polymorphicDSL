package com.pdsl.testcases;

import com.pdsl.specifications.TestSpecification;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * This test case factory treats test specifications as a single script where the parents are read before the children.
 */
public class SingleTestOutputPreorderTestCaseFactory implements TestCaseFactory {

    @Override
    public Collection<TestCase> processTestSpecification(Collection<TestSpecification> testCaseSpecifications)  {
        // Capacity does not necessarily match expected value
        Collection<TestCase> testCases = new ArrayList<>(testCaseSpecifications.size());
        for (TestSpecification testCaseSpecification : testCaseSpecifications) {
            List<TestSection> testSections = new ArrayList<>(16);
            List<TestCase> result = new ArrayList<>(1);
            recursivelyWalkSpecification(testCaseSpecification, testSections);
            result.add(new DefaultPdslTestCase(testCaseSpecification.getId(), testSections));
            testCases.addAll(result);
        }
        return testCases;
    }

    private void  recursivelyWalkSpecification(TestSpecification testSpecification, List<TestSection> testSections) {
        if (testSpecification.getPhrases().isPresent()) {
            for (ParseTree parseTree : testSpecification.getPhrases().get()) {
                if (testSpecification.getMetaData().isPresent()) {
                    testSections.add(new DefaultTestSection(testSpecification.getMetaData().get(), parseTree));
                } else {
                    testSections.add(new DefaultTestSection(parseTree));
                }
            }
        }
        if (testSpecification.nestedTestSpecifications().isPresent()) {
            for (TestSpecification childSpecification : testSpecification.nestedTestSpecifications().get()) {
                recursivelyWalkSpecification(childSpecification, testSections);
            }
        }
    }
}
