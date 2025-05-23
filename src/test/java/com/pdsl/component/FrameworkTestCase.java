package com.pdsl.component;

import com.pdsl.executors.DefaultPolymorphicDslTestExecutor;
import com.pdsl.gherkin.executors.GherkinTestExecutor;
import com.pdsl.grammars.*;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.reports.TestResult;
import com.pdsl.reports.TestRunResults;
import com.pdsl.testcases.DefaultPdslTestCase;
import com.pdsl.testcases.TestCase;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.junit.Test;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

public class FrameworkTestCase {

    @Test
    public void testCaseFactory_meetsSpecifications() throws URISyntaxException {
        final URL testResources = getClass().getClassLoader()
                .getResource("framework_specifications/features/TestCaseFactory.feature");
        // Arrange
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(testResources.toURI());
        PolymorphicDslPhraseFilter phraseFilter = new DefaultPolymorphicDslPhraseFilter(
                TestCaseFactoryParser.class,
                TestCaseFactoryLexer.class,
                "polymorphicDslAllRules"
        );
        GherkinTestExecutor gherkinTestExecutor = new GherkinTestExecutor(phraseFilter);
        // Act
        TestRunResults results = gherkinTestExecutor.processFilesAndRunTests(dslFiles, new TestCaseFactoryParserListenerImpl());
        assertThat(results.failingTestTotal()).isEqualTo(0);
        assertThat(results.totalFilteredDuplicateTests()).isEqualTo(0);
        assertThat(results.totalPhrases()).isGreaterThan(0);
    }

    @Test
    public void scenariosAndExamples_haveLineNumberInSourceUri() throws URISyntaxException {
        final URL testResources = getClass().getClassLoader()
                .getResource("testdata/good/scenario_outlines_with_tags.feature");
        // ARRANGE
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(testResources.toURI());
        PolymorphicDslPhraseFilter phraseFilter = new DefaultPolymorphicDslPhraseFilter(
                AllGrammarsParser.class,
                AllGrammarsLexer.class,
                "polymorphicDslAllRules"
        );
        GherkinTestExecutor gherkinTestExecutor = new GherkinTestExecutor(phraseFilter);
        // Act
        MetadataTestRunResults results = gherkinTestExecutor.runTestsWithMetadata(dslFiles, new TestCaseFactoryParserListenerImpl(), "Component");
        // ASSERT
        assertThat(results.getTestResults().size()).isEqualTo(2);
        // Get all the test cases and sort by line number in the URI
        List<TestCase> sortedTests = results.getTestResults().stream()
                .map(TestResult::getTestCase)
                .sorted(DefaultPdslTestCase.DEFAULT_TEST_CASE_COMPARATOR)
                .toList();

        TestCase first = sortedTests.getFirst();
        assertThat(first.getOriginalSource().getFragment()).endsWith("9");
        assertThat(first.getUnfilteredPhraseBody().getFirst()).contains("Given y");
        TestCase second = sortedTests.get(1);
        assertThat(second.getOriginalSource().getFragment()).endsWith("18");
        assertThat(second.getUnfilteredPhraseBody().getFirst()).contains("Given n");
    }

    private record Line2Scenario(int lineNumber, String title){}
    @Test
    public void longFeatureFiles_haveLineNumbersMappedToScenarios() throws URISyntaxException {
        final URL testResources = getClass().getClassLoader()
                .getResource("testdata/good/very_long.feature");
        // ARRANGE
        Set<URI> dslFiles = new HashSet<>();
        dslFiles.add(testResources.toURI());
        PolymorphicDslPhraseFilter phraseFilter = new DefaultPolymorphicDslPhraseFilter(
                AllGrammarsParser.class,
                AllGrammarsLexer.class,
                "polymorphicDslAllRules"
        );
        GherkinTestExecutor gherkinTestExecutor = new GherkinTestExecutor(phraseFilter);
        // Act

        MetadataTestRunResults results = gherkinTestExecutor.runTestsWithMetadata(dslFiles, new TestCaseFactoryParserListenerImpl(), "Component");
        List<TestCase> allTestCases = new ArrayList<>(100);
        allTestCases.addAll(results.getTestResults().stream().map(TestResult::getTestCase).toList());
        allTestCases.addAll(results.duplicateTestSpecifications().orElse(new ArrayList<TestResult>(0))
                .stream()
                .map(TestResult::getTestCase)
                .toList());
        List<TestCase> testCasesSortedByLine = allTestCases.stream()
                .sorted(DefaultPdslTestCase.DEFAULT_TEST_CASE_COMPARATOR)
                .toList();

        // ASSERT
        assertThat(allTestCases.size()).isEqualTo(100);
        List<Line2Scenario> line2Scenarios = new ArrayList<>(100);
        line2Scenarios.add(new Line2Scenario(4, "Scenario: scenario 01"));
        line2Scenarios.add(new Line2Scenario(21, "Scenario: scenario 02"));
        line2Scenarios.add(new Line2Scenario(38, "Scenario: scenario 03"));
        line2Scenarios.add(new Line2Scenario(55, "Scenario: scenario 04"));
        line2Scenarios.add(new Line2Scenario(72, "Scenario: scenario 05"));
        line2Scenarios.add(new Line2Scenario(89, "Scenario: scenario 06"));
        line2Scenarios.add(new Line2Scenario(106, "Scenario: scenario 07"));
        line2Scenarios.add(new Line2Scenario(123, "Scenario: scenario 08"));
        line2Scenarios.add(new Line2Scenario(140, "Scenario: scenario 09"));
        line2Scenarios.add(new Line2Scenario(157, "Scenario: scenario 10"));
        line2Scenarios.add(new Line2Scenario(174, "Scenario: scenario 11"));
        line2Scenarios.add(new Line2Scenario(191, "Scenario: scenario 12"));
        line2Scenarios.add(new Line2Scenario(208, "Scenario: scenario 13"));
        line2Scenarios.add(new Line2Scenario(225, "Scenario: scenario 14"));
        line2Scenarios.add(new Line2Scenario(242, "Scenario: scenario 15"));
        line2Scenarios.add(new Line2Scenario(259, "Scenario: scenario 16"));
        line2Scenarios.add(new Line2Scenario(276, "Scenario: scenario 17"));
        line2Scenarios.add(new Line2Scenario(293, "Scenario: scenario 18"));
        line2Scenarios.add(new Line2Scenario(310, "Scenario: scenario 19"));
        line2Scenarios.add(new Line2Scenario(327, "Scenario: scenario 20"));
        line2Scenarios.add(new Line2Scenario(344, "Scenario: scenario 21"));
        line2Scenarios.add(new Line2Scenario(361, "Scenario: scenario 22"));
        line2Scenarios.add(new Line2Scenario(378, "Scenario: scenario 23"));
        line2Scenarios.add(new Line2Scenario(395, "Scenario: scenario 24"));
        line2Scenarios.add(new Line2Scenario(412, "Scenario: scenario 25"));
        line2Scenarios.add(new Line2Scenario(429, "Scenario: scenario 26"));
        line2Scenarios.add(new Line2Scenario(446, "Scenario: scenario 27"));
        line2Scenarios.add(new Line2Scenario(463, "Scenario: scenario 28"));
        line2Scenarios.add(new Line2Scenario(480, "Scenario: scenario 29"));
        line2Scenarios.add(new Line2Scenario(497, "Scenario: scenario 30"));
        line2Scenarios.add(new Line2Scenario(514, "Scenario: scenario 31"));
        line2Scenarios.add(new Line2Scenario(531, "Scenario: scenario 32"));
        line2Scenarios.add(new Line2Scenario(548, "Scenario: scenario 33"));
        line2Scenarios.add(new Line2Scenario(565, "Scenario: scenario 34"));
        line2Scenarios.add(new Line2Scenario(582, "Scenario: scenario 35"));
        line2Scenarios.add(new Line2Scenario(599, "Scenario: scenario 36"));
        line2Scenarios.add(new Line2Scenario(616, "Scenario: scenario 37"));
        line2Scenarios.add(new Line2Scenario(633, "Scenario: scenario 38"));
        line2Scenarios.add(new Line2Scenario(650, "Scenario: scenario 39"));
        line2Scenarios.add(new Line2Scenario(667, "Scenario: scenario 40"));
        line2Scenarios.add(new Line2Scenario(684, "Scenario: scenario 41"));
        line2Scenarios.add(new Line2Scenario(701, "Scenario: scenario 42"));
        line2Scenarios.add(new Line2Scenario(718, "Scenario: scenario 43"));
        line2Scenarios.add(new Line2Scenario(735, "Scenario: scenario 44"));
        line2Scenarios.add(new Line2Scenario(752, "Scenario: scenario 45"));
        line2Scenarios.add(new Line2Scenario(769, "Scenario: scenario 46"));
        line2Scenarios.add(new Line2Scenario(786, "Scenario: scenario 47"));
        line2Scenarios.add(new Line2Scenario(803, "Scenario: scenario 48"));
        line2Scenarios.add(new Line2Scenario(820, "Scenario: scenario 49"));
        line2Scenarios.add(new Line2Scenario(837, "Scenario: scenario 50"));
        line2Scenarios.add(new Line2Scenario(854, "Scenario: scenario 51"));
        line2Scenarios.add(new Line2Scenario(871, "Scenario: scenario 52"));
        line2Scenarios.add(new Line2Scenario(888, "Scenario: scenario 53"));
        line2Scenarios.add(new Line2Scenario(905, "Scenario: scenario 54"));
        line2Scenarios.add(new Line2Scenario(922, "Scenario: scenario 55"));
        line2Scenarios.add(new Line2Scenario(939, "Scenario: scenario 56"));
        line2Scenarios.add(new Line2Scenario(956, "Scenario: scenario 57"));
        line2Scenarios.add(new Line2Scenario(973, "Scenario: scenario 58"));
        line2Scenarios.add(new Line2Scenario(990, "Scenario: scenario 59"));
        line2Scenarios.add(new Line2Scenario(1007, "Scenario: scenario 60"));
        line2Scenarios.add(new Line2Scenario(1024, "Scenario: scenario 61"));
        line2Scenarios.add(new Line2Scenario(1041, "Scenario: scenario 62"));
        line2Scenarios.add(new Line2Scenario(1058, "Scenario: scenario 63"));
        line2Scenarios.add(new Line2Scenario(1075, "Scenario: scenario 64"));
        line2Scenarios.add(new Line2Scenario(1092, "Scenario: scenario 65"));
        line2Scenarios.add(new Line2Scenario(1109, "Scenario: scenario 66"));
        line2Scenarios.add(new Line2Scenario(1126, "Scenario: scenario 67"));
        line2Scenarios.add(new Line2Scenario(1143, "Scenario: scenario 68"));
        line2Scenarios.add(new Line2Scenario(1160, "Scenario: scenario 69"));
        line2Scenarios.add(new Line2Scenario(1177, "Scenario: scenario 70"));
        line2Scenarios.add(new Line2Scenario(1194, "Scenario: scenario 71"));
        line2Scenarios.add(new Line2Scenario(1211, "Scenario: scenario 72"));
        line2Scenarios.add(new Line2Scenario(1228, "Scenario: scenario 73"));
        line2Scenarios.add(new Line2Scenario(1245, "Scenario: scenario 74"));
        line2Scenarios.add(new Line2Scenario(1262, "Scenario: scenario 75"));
        line2Scenarios.add(new Line2Scenario(1279, "Scenario: scenario 76"));
        line2Scenarios.add(new Line2Scenario(1296, "Scenario: scenario 77"));
        line2Scenarios.add(new Line2Scenario(1313, "Scenario: scenario 78"));
        line2Scenarios.add(new Line2Scenario(1330, "Scenario: scenario 79"));
        line2Scenarios.add(new Line2Scenario(1347, "Scenario: scenario 80"));
        line2Scenarios.add(new Line2Scenario(1364, "Scenario: scenario 81"));
        line2Scenarios.add(new Line2Scenario(1381, "Scenario: scenario 82"));
        line2Scenarios.add(new Line2Scenario(1398, "Scenario: scenario 83"));
        line2Scenarios.add(new Line2Scenario(1415, "Scenario: scenario 84"));
        line2Scenarios.add(new Line2Scenario(1432, "Scenario: scenario 85"));
        line2Scenarios.add(new Line2Scenario(1449, "Scenario: scenario 86"));
        line2Scenarios.add(new Line2Scenario(1466, "Scenario: scenario 87"));
        line2Scenarios.add(new Line2Scenario(1483, "Scenario: scenario 88"));
        line2Scenarios.add(new Line2Scenario(1500, "Scenario: scenario 89"));
        line2Scenarios.add(new Line2Scenario(1517, "Scenario: scenario 90"));
        line2Scenarios.add(new Line2Scenario(1534, "Scenario: scenario 91"));
        line2Scenarios.add(new Line2Scenario(1551, "Scenario: scenario 92"));
        line2Scenarios.add(new Line2Scenario(1568, "Scenario: scenario 93"));
        line2Scenarios.add(new Line2Scenario(1585, "Scenario: scenario 94"));
        line2Scenarios.add(new Line2Scenario(1602, "Scenario: scenario 95"));
        line2Scenarios.add(new Line2Scenario(1619, "Scenario: scenario 96"));
        line2Scenarios.add(new Line2Scenario(1636, "Scenario: scenario 97"));
        line2Scenarios.add(new Line2Scenario(1653, "Scenario: scenario 98"));
        line2Scenarios.add(new Line2Scenario(1670, "Scenario: scenario 99"));
        line2Scenarios.add(new Line2Scenario(1687, "Scenario: scenario 100"));

        for (int i=0; i < line2Scenarios.size(); i++) {
           assertThat(allTestCases.get(i).getTestTitle()).contains(line2Scenarios.get(i).title);
           assertThat(Integer.parseInt(testCasesSortedByLine.get(i).getOriginalSource().getFragment().split("=")[1]))
                   .isEqualTo(line2Scenarios.get(i).lineNumber);
        }
    }


}

