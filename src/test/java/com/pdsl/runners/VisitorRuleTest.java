package com.pdsl.runners;

import com.pdsl.exceptions.DuplicateVisitorMethodsException;
import com.pdsl.executors.InterpreterObj;
import com.pdsl.grammars.DialectLexer;
import com.pdsl.grammars.DialectParser;
import com.pdsl.specifications.TestResourceFinder;
import com.pdsl.specifications.TestResourceFinderGenerator;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.TestSpecificationFactory;
import com.pdsl.testcases.TestCase;
import com.pdsl.testcases.TestCaseFactory;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.junit.Test;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VisitorRuleTest {

    public static class VisitorA extends AbstractParseTreeVisitor<Void> {
        public Void visitSomething() { return null; }
    }

    public static class VisitorB extends AbstractParseTreeVisitor<Void> {
        public Void visitSomething() { return null; }
    }

    @Test(expected = DuplicateVisitorMethodsException.class)
    public void noDuplicatesRule_duplicateLinesAcrossInterpreters_throwsException() {
        SharedTestSuiteVisitor sharedTestSuiteVisitor = new SharedTestSuiteVisitor();
        RecognizerParams recognizerParams = mock(RecognizerParams.class);
        
        TestCase testCase1 = mock(TestCase.class);
        when(testCase1.getContextFilteredPhraseBody()).thenReturn(List.of("Shared line"));
        TestCase testCase2 = mock(TestCase.class);
        when(testCase2.getContextFilteredPhraseBody()).thenReturn(List.of("Shared line")); // Duplicate across DIFFERENT interpreters

        InterpreterParam interpreterParam1 = new InterpreterParam(
                DialectParser.class,
                DialectLexer.class,
                () -> new InterpreterObj(new VisitorA()),
                new String[]{}, new String[]{"*.feature"}, new String[]{}, "polymorphicDslAllRules", "polymorphicDslSyntaxCheck"
        );
        InterpreterParam interpreterParam2 = new InterpreterParam(
                DialectParser.class,
                DialectLexer.class,
                () -> new InterpreterObj(new VisitorB()),
                new String[]{}, new String[]{"*.feature"}, new String[]{}, "polymorphicDslAllRules", "polymorphicDslSyntaxCheck"
        );
        PdslTestParams pdslTestParams = new PdslTestParams(
                DialectLexer.class,
                DialectParser.class,
                new InterpreterParam[]{interpreterParam1, interpreterParam2},
                List.of(), new String[]{"*.feature"}, new String[]{}
        );

        when(recognizerParams.visitorRule()).thenReturn(VisitorRule.NO_DUPLICATES_RULE);
        when(recognizerParams.pdslTestParams()).thenReturn(List.of(pdslTestParams));

        // We need enough mock data to get past the initial checks in recognizerParamsOperation
        when(recognizerParams.applicationName()).thenReturn("app");
        when(recognizerParams.resourceRoot()).thenReturn("./");
        when(recognizerParams.context()).thenReturn("context");
        
        TestResourceFinderGenerator resourceFinderGenerator = mock(TestResourceFinderGenerator.class);
        TestResourceFinder resourceFinder = mock(TestResourceFinder.class);
        when(resourceFinderGenerator.get(any(String[].class), any(String[].class))).thenReturn(resourceFinder);
        when(resourceFinder.scanForTestResources(any(URI.class))).thenReturn(Optional.of(List.of(URI.create("file:///tmp/test.feature"))));

        TestSpecificationFactoryGenerator specificationFactoryGenerator = mock(TestSpecificationFactoryGenerator.class);
        TestSpecificationFactory specificationFactory = mock(TestSpecificationFactory.class);
        when(specificationFactoryGenerator.get(any())).thenReturn(specificationFactory);
        when(specificationFactory.getTestSpecifications(any(Set.class))).thenReturn(Optional.of(List.of(mock(TestSpecification.class))));

        TestCaseFactory testCaseFactory = mock(TestCaseFactory.class);
        // Return testCase1 for the first interpreter and testCase2 for the second
        when(testCaseFactory.processTestSpecification(any(Collection.class)))
                .thenReturn(List.of(testCase1))
                .thenReturn(List.of(testCase2));

        RecognizerParams.PdslSuppliers providers = new RecognizerParams.PdslSuppliers(
                () -> resourceFinderGenerator,
                () -> specificationFactoryGenerator,
                () -> testCaseFactory
        );
        when(recognizerParams.providers()).thenReturn(providers);

        sharedTestSuiteVisitor.recognizerParamsOperation(recognizerParams);
    }
    
    @Test
    public void noDuplicatesRule_duplicateLinesWithinSameInterpreter_doesNotThrow() {
        SharedTestSuiteVisitor sharedTestSuiteVisitor = new SharedTestSuiteVisitor();
        RecognizerParams recognizerParams = mock(RecognizerParams.class);

        TestCase testCase1 = mock(TestCase.class);
        when(testCase1.getContextFilteredPhraseBody()).thenReturn(List.of("Common line"));
        TestCase testCase2 = mock(TestCase.class);
        when(testCase2.getContextFilteredPhraseBody()).thenReturn(List.of("Common line")); // Duplicate within SAME interpreter

        InterpreterParam interpreterParam = new InterpreterParam(
                DialectParser.class,
                DialectLexer.class,
                () -> new InterpreterObj(new VisitorA()),
                new String[]{}, new String[]{"*.feature"}, new String[]{}, "polymorphicDslAllRules", "polymorphicDslSyntaxCheck"
        );
        PdslTestParams pdslTestParams = new PdslTestParams(
                DialectLexer.class,
                DialectParser.class,
                new InterpreterParam[]{interpreterParam},
                List.of(), new String[]{"*.feature"}, new String[]{}
        );

        when(recognizerParams.visitorRule()).thenReturn(VisitorRule.NO_DUPLICATES_RULE);
        when(recognizerParams.pdslTestParams()).thenReturn(List.of(pdslTestParams));

        // We need enough mock data to get past the initial checks in recognizerParamsOperation
        when(recognizerParams.applicationName()).thenReturn("app");
        when(recognizerParams.resourceRoot()).thenReturn("./");
        when(recognizerParams.context()).thenReturn("context");

        TestResourceFinderGenerator resourceFinderGenerator = mock(TestResourceFinderGenerator.class);
        TestResourceFinder resourceFinder = mock(TestResourceFinder.class);
        when(resourceFinderGenerator.get(any(String[].class), any(String[].class))).thenReturn(resourceFinder);
        when(resourceFinder.scanForTestResources(any(URI.class))).thenReturn(Optional.of(List.of(URI.create("file:///tmp/test.feature"))));

        TestSpecificationFactoryGenerator specificationFactoryGenerator = mock(TestSpecificationFactoryGenerator.class);
        TestSpecificationFactory specificationFactory = mock(TestSpecificationFactory.class);
        when(specificationFactoryGenerator.get(any())).thenReturn(specificationFactory);
        when(specificationFactory.getTestSpecifications(any(Set.class))).thenReturn(Optional.of(List.of(mock(TestSpecification.class))));

        TestCaseFactory testCaseFactory = mock(TestCaseFactory.class);
        when(testCaseFactory.processTestSpecification(any(Collection.class))).thenReturn(List.of(testCase1, testCase2));

        RecognizerParams.PdslSuppliers providers = new RecognizerParams.PdslSuppliers(
                () -> resourceFinderGenerator,
                () -> specificationFactoryGenerator,
                () -> testCaseFactory
        );
        when(recognizerParams.providers()).thenReturn(providers);

        sharedTestSuiteVisitor.recognizerParamsOperation(recognizerParams);
    }
    
    @Test
    public void defaultRule_duplicateTestCases_doesNotThrow() {
        SharedTestSuiteVisitor sharedTestSuiteVisitor = new SharedTestSuiteVisitor();
        RecognizerParams recognizerParams = mock(RecognizerParams.class);

        TestCase testCase1 = mock(TestCase.class);
        when(testCase1.getContextFilteredPhraseBody()).thenReturn(List.of("phrase 1"));
        TestCase testCase2 = mock(TestCase.class);
        when(testCase2.getContextFilteredPhraseBody()).thenReturn(List.of("phrase 1")); // Duplicate

        InterpreterParam interpreterParam = new InterpreterParam(
                DialectParser.class,
                DialectLexer.class,
                () -> new InterpreterObj(new VisitorA()),
                new String[]{}, new String[]{"*.feature"}, new String[]{}, "polymorphicDslAllRules", "polymorphicDslSyntaxCheck"
        );
        PdslTestParams pdslTestParams = new PdslTestParams(
                DialectLexer.class,
                DialectParser.class,
                new InterpreterParam[]{interpreterParam},
                List.of(), new String[]{"*.feature"}, new String[]{}
        );

        when(recognizerParams.visitorRule()).thenReturn(VisitorRule.DEFAULT_RULE);
        when(recognizerParams.pdslTestParams()).thenReturn(List.of(pdslTestParams));

        // We need enough mock data to get past the initial checks in recognizerParamsOperation
        when(recognizerParams.applicationName()).thenReturn("app");
        when(recognizerParams.resourceRoot()).thenReturn("./");
        when(recognizerParams.context()).thenReturn("context");
        
        TestResourceFinderGenerator resourceFinderGenerator = mock(TestResourceFinderGenerator.class);
        TestResourceFinder resourceFinder = mock(TestResourceFinder.class);
        when(resourceFinderGenerator.get(any(String[].class), any(String[].class))).thenReturn(resourceFinder);
        when(resourceFinder.scanForTestResources(any(URI.class))).thenReturn(Optional.of(List.of(URI.create("file:///tmp/test.feature"))));

        TestSpecificationFactoryGenerator specificationFactoryGenerator = mock(TestSpecificationFactoryGenerator.class);
        TestSpecificationFactory specificationFactory = mock(TestSpecificationFactory.class);
        when(specificationFactoryGenerator.get(any())).thenReturn(specificationFactory);
        when(specificationFactory.getTestSpecifications(any(Set.class))).thenReturn(Optional.of(List.of(mock(TestSpecification.class))));

        TestCaseFactory testCaseFactory = mock(TestCaseFactory.class);
        when(testCaseFactory.processTestSpecification(any(Collection.class))).thenReturn(List.of(testCase1, testCase2));

        RecognizerParams.PdslSuppliers providers = new RecognizerParams.PdslSuppliers(
                () -> resourceFinderGenerator,
                () -> specificationFactoryGenerator,
                () -> testCaseFactory
        );
        when(recognizerParams.providers()).thenReturn(providers);

        sharedTestSuiteVisitor.recognizerParamsOperation(recognizerParams);
    }
}
