package com.pdsl.component;

import com.pdsl.grammars.DotTestCaseFactoryLexer;
import com.pdsl.grammars.DotTestCaseFactoryParser;
import com.pdsl.grammars.DotTestCaseFactoryParserBaseListener;
import com.pdsl.runners.PdslConfiguration;
import com.pdsl.runners.PdslTest;
import com.pdsl.runners.junit.PdslJUnit4ConfigurableRunner;
import com.pdsl.specifications.*;
import com.pdsl.testcases.PreorderTestCaseFactory;
import com.pdsl.testcases.SingleTestOutputPreorderTestCaseFactory;
import com.pdsl.testcases.TestCase;
import com.pdsl.testcases.TestCaseFactory;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.apache.commons.lang3.mutable.Mutable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import org.mockito.Mockito;

import javax.inject.Provider;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.logging.Filter;
import java.util.stream.Collectors;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(PdslJUnit4ConfigurableRunner.class)
@PdslConfiguration(
        context = "Component",
        resourceRoot = "src/test/resources/testcase_factories",
        testCaseFactoryProvider = SingleTestOutputPreorderTestCaseFactory.DefaultProvider.class,
        specificationFactoryProvider = FileDelimitedTestSpecificationFactory.DefaultProvider.class,
        recognizerRule = "testSpecifications",
        dslRecognizerParser = DotTestCaseFactoryParser.class,
        dslRecognizerLexer = DotTestCaseFactoryLexer.class
)
public class PreorderTestCaseFactoryTest {
    private int accumulator = 1;

    @PdslTest(
            includesResources = {"simple_specification.dot", "complex_specification.dot"},
            parser = DotTestCaseFactoryParser.class,
            lexer = DotTestCaseFactoryLexer.class,
            listener = ListenerProvider.class,
            startRule = "testSpecifications"
    )
    public void factoriesProcessTestSpecifications_allFactoriesProvideExpectedOutput() {}

    public static class ListenerProvider implements Provider<ParseTreeListener> {
        private static final ParseTreeListener listener = new TestCaseFactoryListener();
        @Override
        public ParseTreeListener get() {
            return listener;
        }
    }
    private static class TestCaseFactoryListener extends DotTestCaseFactoryParserBaseListener {

        private static class TestFilteredPhrase implements  FilteredPhrase {
            private final String phrase;
            private TestFilteredPhrase(String phrase) {
                this.phrase = phrase;
            }

            @Override
            public String getPhrase() {
                return phrase;
            }

            @Override
            public Optional<ParseTree> getParseTree() {
                return Optional.empty();
            }
        }

        private static class MutableTestSpecification implements TestSpecification {

            private final List<TestSpecification> nestedSpecifications = new ArrayList<>();
            private final String name;
            private final Optional<List<FilteredPhrase>> filteredPhrases;

            MutableTestSpecification(String name, List<FilteredPhrase> filteredPhrases) {
                this.name = name;
                this.filteredPhrases = Optional.ofNullable(filteredPhrases);
            }

            @Override
            public Optional<InputStream> getMetaData() {
                return Optional.empty();
            }

            @Override
            public Optional<List<TestSpecification>> nestedTestSpecifications() {
                return nestedSpecifications.isEmpty() ? Optional.empty() : Optional.of(nestedSpecifications);
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public Optional<List<FilteredPhrase>> getFilteredPhrases() {
                return filteredPhrases;
            }

            @Override
            public URI getOriginalTestResource() {
                return URI.create("file:///stubbed_uri-" + getName());
            }
        }

        private Class<? extends TestCaseFactory> getFactoryClass(String name) {
            switch (name) {
                case "Preorder":
                    return PreorderTestCaseFactory.class;
                case "Single Test Output Preorder":
                    return SingleTestOutputPreorderTestCaseFactory.class;
                default:
                    throw new UnsupportedOperationException(String.format("Have no implementation for testing the '%s' factory!", name));
            }
        }

        @Override
        public void enterTestSpecifications(DotTestCaseFactoryParser.TestSpecificationsContext ctx) {
            // ARRANGE
            Map<String, MutableTestSpecification> nameToSpecification = new HashMap<>();
            Map<String, Collection<String>> expectedResults = new HashMap<>();
            //Map<String, Collection<Collection<TestCase>>> actualResults = new HashMap<>();
            Map<String, List<TestCase>> actualResults = new HashMap<>();
            Map<String, List<String>> testCaseNameToExpectedPhrases = new HashMap<>();

            List<DefaultTestSpecification.Builder> specifications = new ArrayList<>(ctx.specification().size());
            nameAllTestSpecificationsBeingUsed(ctx.specification(), nameToSpecification);
            nameAllTestSpecificationsBeingUsed(ctx.results().specification(), nameToSpecification); // Expected results
            assertThat(nameToSpecification.containsKey("Parent")).isTrue(); // Enforce convention to label root node
            // Nest the test specifications as described by the DOT file
            nestSpecificationsHierarchically(ctx.nestedSpecificationRelationships(), nameToSpecification);
            linkManyChildSpecificationsToOneParent(ctx.nestedSpecificationRelationships(), nameToSpecification);
            // Store the possible expected outputs that could be produced from any factory
            ctx.results().specification().stream()
                    .forEach(testCase -> testCaseNameToExpectedPhrases.put(testCase.specificationLabel().FILTERED_PHRASE_NAME().getText(), // Get the name of each test case
                            testCase.PHRASE().stream().map(phrase -> phrase.getText()) // Extract the filtered phrases from each test case
                                    .collect(Collectors.toUnmodifiableList())));
            // Associate factories with their respective outputs by test case name
            for (DotTestCaseFactoryParser.NestedSpecificationRelationshipsContext resultMapping : ctx.results().nestedSpecificationRelationships()) {

                if (resultMapping.linkedSpecification() != null) {
                    String factoryName = resultMapping.linkedSpecification().specificationLabel().FILTERED_PHRASE_NAME().getText();
                    String testCaseLabel = resultMapping.linkedSpecification().linkedPhrase(0).FILTERED_PHRASE_NAME().getText(); // There can only be a depth of 1 because test cases cannot contain test cases
                    Collection<String> outputs = expectedResults.computeIfAbsent(factoryName, k -> new ArrayList<>());
                    outputs.add(testCaseLabel);
                }
                if (resultMapping.multiLinkedSpecification() != null) {
                    String factoryName = resultMapping.multiLinkedSpecification().specificationLabel().FILTERED_PHRASE_NAME().getText();
                    for (int i = 0; i < resultMapping.multiLinkedSpecification().childSpecification().size(); i++) {
                        DotTestCaseFactoryParser.ChildSpecificationContext expectedTestCaseLabel = resultMapping.multiLinkedSpecification().childSpecification().get(i);
                        Collection<String> outputs = expectedResults.computeIfAbsent(factoryName, k -> new ArrayList<>());
                        outputs.add(expectedTestCaseLabel.CHILD_SPECIFICATION().getText());
                    }
                }
            }

            // ACT
            // Find the parent specification and process it with all test case factories
            // By convention, this will be the one named "Parent"
            TestSpecification parentSpecification = nameToSpecification.get("Parent");
            for (String factoryName : expectedResults.keySet()) {
                actualResults.computeIfAbsent(factoryName, x -> new ArrayList<>());
                TestCaseFactory factory;
                switch (factoryName) {
                    case "Preorder":
                        factory = new PreorderTestCaseFactory();
                        break;
                    case "Single Test Output Preorder":
                        factory = new SingleTestOutputPreorderTestCaseFactory();
                        break;
                    default:
                        throw new UnsupportedOperationException(String.format("Have no implementation for testing the '%s' factory!", factoryName));
                }
                actualResults.get(factoryName).addAll(factory.processTestSpecification(List.of(parentSpecification)));
            }

            // ASSERT
            // Make sure factory produces all the expected results
            for (Map.Entry<String, Collection<String>> entry : expectedResults.entrySet()) {
                // For every factory we produced test cases from...
                String factoryName = entry.getKey();
                Collection<String> testCaseNames = entry.getValue();
                // Created the correct amount of test cases...
                assertThat(testCaseNames.size()).isEqualTo(actualResults.get(factoryName).size());
                for (String testCaseName : testCaseNames) {  // Get every test case produced by that factory
                    List<String> expectedPhrases = testCaseNameToExpectedPhrases.get(testCaseName);
                    for (TestCase actualTestCase : actualResults.get(factoryName)) {
                        List<String> actualResultsForTestCase =  actualTestCase.getUnfilteredPhraseBody();
                        // And make sure all of them match one of the expected results

                        try {
                            boolean containsTestCase = actualResults.get(factoryName).stream()
                                    .anyMatch(testSpecification -> testSpecification.getUnfilteredPhraseBody().equals(expectedPhrases));
                            assertThat(containsTestCase).isTrue();
                        } catch (AssertionError e) {
                            String prettyPrintArray = actualResults.get(factoryName).stream()
                                    .map(testCase -> "\n\t\t" + Arrays.toString(testCase.getUnfilteredPhraseBody().toArray()))
                                    .collect(Collectors.joining());
                            throw new AssertionError(String.format("The test cases produced by the factory '%s' had a problem!%n\tExpected To Create Test Case:%s%n\tActual Test Cases:%s", factoryName, expectedPhrases, prettyPrintArray), e);
                        }
                    }
                }
            }
        }
    }

    private static void nameAllTestSpecificationsBeingUsed(List<DotTestCaseFactoryParser.SpecificationContext> ctx,  Map<String, TestCaseFactoryListener.MutableTestSpecification> nameToSpecification) {
        // Get the test specifications
        for (DotTestCaseFactoryParser.SpecificationContext specificationContext : ctx) {
            // Gather all the phrases associated with the specification.
            List<FilteredPhrase> phrases = specificationContext.PHRASE().stream()
                    .map(p -> new TestCaseFactoryListener.TestFilteredPhrase(p.getText()))
                    .collect(Collectors.toUnmodifiableList());
            String name = specificationContext.specificationLabel().FILTERED_PHRASE_NAME().getText();
            nameToSpecification.put(name, new TestCaseFactoryListener.MutableTestSpecification(name, phrases));
        }
    }
    // e.g, label1 -> label2 -> label3
    private static void nestSpecificationsHierarchically(List<DotTestCaseFactoryParser.NestedSpecificationRelationshipsContext> ctx, Map<String, TestCaseFactoryListener.MutableTestSpecification> nameToSpecification) {
        for (DotTestCaseFactoryParser.NestedSpecificationRelationshipsContext nestedRelationship : ctx) {
            if (nestedRelationship.linkedSpecification() == null) { return; }
            TestCaseFactoryListener.MutableTestSpecification current = nameToSpecification.get(nestedRelationship.linkedSpecification().specificationLabel().FILTERED_PHRASE_NAME().getText());
            for (int i=0; i < nestedRelationship.linkedSpecification().linkedPhrase().size(); i++)  {
                DotTestCaseFactoryParser.LinkedPhraseContext child = nestedRelationship.linkedSpecification().linkedPhrase(i);
                // e.g, "Spec 1" -> "Spec 2" -> "Spec 3"
                // This should create a simple hierarchy between the three elements
                current.nestedSpecifications.add(nameToSpecification.get(child.FILTERED_PHRASE_NAME().getText()));
                current = nameToSpecification.get(child.FILTERED_PHRASE_NAME().getText());
            }
        }
    }

    // e.g, "Spec 1" -> {"Spec 2"  "Spec 3"}
    private static void linkManyChildSpecificationsToOneParent(List<DotTestCaseFactoryParser.NestedSpecificationRelationshipsContext> ctx, Map<String, TestCaseFactoryListener.MutableTestSpecification> nameToSpecification) {
        for (DotTestCaseFactoryParser.NestedSpecificationRelationshipsContext nestedContext : ctx) {
            if (nestedContext.multiLinkedSpecification() == null) { return; }
                TestCaseFactoryListener.MutableTestSpecification parent = nameToSpecification.get(nestedContext.multiLinkedSpecification().specificationLabel().FILTERED_PHRASE_NAME().getText());
                for (DotTestCaseFactoryParser.ChildSpecificationContext child : nestedContext.multiLinkedSpecification().childSpecification()) {
                    parent.nestedSpecifications.add(nameToSpecification.get(child.CHILD_SPECIFICATION().getText()));
                }
            }
    }

    private List<FilteredPhrase> makeFilteredPhrases(int quantity, ParseTree parseTree) {
        List list = new ArrayList<>(quantity);
        int t = accumulator + quantity;
        for (int i = accumulator; i < t; i++, accumulator++) {
            int finalI = accumulator;
            FilteredPhrase filteredPhrase = new FilteredPhrase() {

                @Override
                public String getPhrase() {
                    return Integer.toString(finalI);
                }

                @Override
                public Optional<ParseTree> getParseTree() {
                    return Optional.of(parseTree);
                }
            };
            list.add(filteredPhrase);

        }
        return list;
    }

    @Test
    public void preorderFactory_producesTestCasesWithPhrasesInProperOrder() throws URISyntaxException {
        // ARRANGE
        ParseTree parseTree = mock(ParseTree.class);
        doReturn("bar").when(parseTree).getText();

        List<FilteredPhrase> topPhrases = makeFilteredPhrases(3, parseTree);
        List<FilteredPhrase> middlePhrases = makeFilteredPhrases(3, parseTree);
        List<FilteredPhrase> bottomPhrases = makeFilteredPhrases(3, parseTree);
        URI uri = new URI("http://www.fake.com");
        // Top phrases
        DefaultTestSpecification.Builder builder = new DefaultTestSpecification.Builder("stubbed id", uri)
                .withPhrases(bottomPhrases)
                .withTestPhrases(bottomPhrases);


        TestSpecification bottom = builder.build();
        TestSpecification middle = builder
                .withTestPhrases(middlePhrases)
                .withChildTestSpecifications(List.of(bottom)).build();
        TestSpecification top = builder
                .withTestPhrases(topPhrases)
                .withChildTestSpecifications(List.of(middle)).build();

        TestCaseFactory preorderTestCaseFactory = new PreorderTestCaseFactory();

        // ACT
        Collection<TestCase> testCases = preorderTestCaseFactory.processTestSpecification(List.of(top));

        // ASSERT
        assertThat(testCases.size()).isEqualTo(1);
        List<TestCase> cases = List.copyOf(testCases);
        assertThat(cases.get(0).getUnfilteredPhraseBody().size()).isEqualTo(9);
        assertThat(cases.get(0).getUnfilteredPhraseBody().toString()).isEqualTo("[1, 2, 3, 4, 5, 6, 7, 8, 9]");
    }
}
