package com.pdsl.api;

import com.pdsl.executors.DefaultPolymorphicDslTestExecutor;
import com.pdsl.executors.PolymorphicDslTestExecutor;
import com.pdsl.gherkin.DefaultGherkinTestSpecificationFactory;
import com.pdsl.gherkin.models.GherkinScenario;
import com.pdsl.grammars.AllGrammarsLexer;
import com.pdsl.grammars.AllGrammarsParser;
import com.pdsl.reports.DefaultTestResult;
import com.pdsl.reports.PolymorphicDslTestRunResults;
import com.pdsl.reports.proto.TechnicalReportData;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.testcases.PreorderTestCaseFactory;
import com.pdsl.testcases.TestCase;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

import static com.google.common.truth.Truth.assertThat;

public class ScenarioPositionTest {



    private static final DefaultGherkinTestSpecificationFactory specificationFactory = new DefaultGherkinTestSpecificationFactory.Builder(
            new DefaultPolymorphicDslPhraseFilter(AllGrammarsParser.class, AllGrammarsLexer.class)).build();
    private static final PolymorphicDslTestExecutor executor = DefaultPolymorphicDslTestExecutor
            .ofWithoutDuplicateFiltering(List.of());
    private static final PreorderTestCaseFactory testCaseFactory = new PreorderTestCaseFactory();

    private static URI getURI(URL url) throws URISyntaxException {
        return  Objects.requireNonNull(url).toURI();
    }

    @Test
    public void veryLongResultsLocationInformation_groupedProperlyInNavigableSet() throws URISyntaxException {
        // ARRANGE
        final URI complexBackground = getURI(getClass().getClassLoader()
                .getResource("testdata/good/very_long.feature"));
        Collection<TestSpecification> specs = specificationFactory.getTestSpecifications(Set.of(complexBackground))
                .orElseThrow();
        Collection<TestCase> testCases = testCaseFactory.processTestSpecification(specs);
        PolymorphicDslTestRunResults results = new PolymorphicDslTestRunResults(List.of(Mockito.mock(OutputStream.class)), "API");

        // ACT
        testCases.forEach(tc ->
                results.addTestResult(new DefaultTestResult(1, tc, TechnicalReportData.Status.PASSED)));
        List<GherkinScenario.ScenarioPosition> positions = results.getTestResults().stream()
                .map(r -> r.getTestCase().getOriginalSource())
                .sorted(new Comparator<URI>(){

                    @Override
                    public int compare(URI u1, URI u2) {
                        return u1.getFragment().split("=")[1].compareTo(u2.getFragment().split("=")[1]);
                    }
                })
                .map(GherkinScenario.ScenarioPosition::from)
                .map(Optional::orElseThrow)
                .toList();
        // ASSERT

        for (int i=1; i <= positions.size(); i++) {
            GherkinScenario.ScenarioPosition position = positions.get(i - 1);
            assertThat(position.ruleIndex()).isEqualTo(0);
            assertThat(position.ordinal()).isEqualTo(i);
            assertThat(position.testIndex()).isEqualTo(0);
        }

    }

    @Test
    public void featureTreeStructure_groupedProperlyInNavigableSet() throws URISyntaxException {
        // ARRANGE
        final URI complexBackground = getURI(getClass().getClassLoader()
                .getResource("framework_specifications/features/ScenarioPositionData.feature"));
        Collection<TestSpecification> specs = specificationFactory.getTestSpecifications(Set.of(complexBackground))
                .orElseThrow();
        Collection<TestCase> testCases = testCaseFactory.processTestSpecification(specs);
        PolymorphicDslTestRunResults results = new PolymorphicDslTestRunResults(List.of(Mockito.mock(OutputStream.class)), "API");
        testCases.forEach(tc ->
                results.addTestResult(new DefaultTestResult(1, tc, TechnicalReportData.Status.PASSED)));
        List<GherkinScenario.ScenarioPosition> actualPositions = results.getTestResults().stream()
                .map(r -> r.getTestCase().getOriginalSource())
                .sorted(new Comparator<URI>(){

                    @Override
                    public int compare(URI u1, URI u2) {
                        return u1.getFragment().split("=")[1].compareTo(u2.getFragment().split("=")[1]);
                    }
                })
                .map(GherkinScenario.ScenarioPosition::from)
                .map(Optional::orElseThrow)
                .toList();
        // ASSERT
        List<GherkinScenario.ScenarioPosition> expectedPositions = List.of(
                new GherkinScenario.ScenarioPosition(0,1,0),
                new GherkinScenario.ScenarioPosition(0,2,0),
                new GherkinScenario.ScenarioPosition(0,3,1),
                new GherkinScenario.ScenarioPosition(0,3,2),
                new GherkinScenario.ScenarioPosition(0,4,0),
                new GherkinScenario.ScenarioPosition(0,5,1),
                new GherkinScenario.ScenarioPosition(0,5,2),
                new GherkinScenario.ScenarioPosition(0,5,3),
                new GherkinScenario.ScenarioPosition(0,5,4),
                new GherkinScenario.ScenarioPosition(1,1,0),
                new GherkinScenario.ScenarioPosition(2,1,1),
                new GherkinScenario.ScenarioPosition(2,1,2)
        );

        assertThat(actualPositions.size()).isEqualTo(expectedPositions.size());
        for (int i=0; i < expectedPositions.size(); i++) {
            assertThat(actualPositions.get(i)).isEqualTo(expectedPositions.get(i));
        }
    }

}
