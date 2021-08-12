package com.pdsl.grammars;

import com.google.common.base.Preconditions;
import com.pdsl.specifications.DefaultTestSpecification;
import com.pdsl.specifications.FilteredPhrase;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.testcases.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.InputStream;
import java.net.URL;
import java.util.*;

import static com.google.common.truth.Truth.assertThat;

public class TestCaseFactoryParserListenerImpl implements TestCaseFactoryParserListener {

    private DefaultTestSpecification.Builder builder;
    private Collection<TestCase> testCases;
    private TestCaseFactory testCaseFactory;

    @Override
    public void enterGivenAnArbitraryTestSpecification(TestCaseFactoryParser.GivenAnArbitraryTestSpecificationContext ctx) {
        builder = new DefaultTestSpecification.Builder("stub test specification", null);
    }

    @Override
    public void exitGivenAnArbitraryTestSpecification(TestCaseFactoryParser.GivenAnArbitraryTestSpecificationContext ctx) { }

    @Override
    public void enterGivenSpecificationHasPhrase(TestCaseFactoryParser.GivenSpecificationHasPhraseContext ctx) {
        int numberOfPhrases = Integer.parseInt(ctx.integerValue().getText());
        builder.withTestPhrases(generateParseTreeStubs(numberOfPhrases));
    }

    private List<FilteredPhrase> generateParseTreeStubs(int count) {
        List<FilteredPhrase> parseTrees = new ArrayList<>(count);
        for (int i=0; i < count; i++) {
            parseTrees.add(new FilteredPhrase() {

                @Override
                public String getPhrase() {
                    return null;
                }

                @Override
                public Optional<ParseTree> getParseTree() {
                    return Optional.empty();
                }
            });
        }
        return parseTrees;
    }

    @Override
    public void exitGivenSpecificationHasPhrase(TestCaseFactoryParser.GivenSpecificationHasPhraseContext ctx) { }

    @Override
    public void enterGivenTheTestSpecificationChild(TestCaseFactoryParser.GivenTheTestSpecificationChildContext ctx) {
        int childrenCount = Integer.parseInt(ctx.integerValue().getText());
        List<TestSpecification> testSpecifications = new ArrayList<>(childrenCount);
        for (int i=0; i < childrenCount; i++) {
            testSpecifications.add(new TestSpecification() {
                @Override
                public Optional<InputStream> getMetaData() {
                    return Optional.empty();
                }

                @Override
                public Optional<List<TestSpecification>> nestedTestSpecifications() {
                    return Optional.empty();
                }

                @Override
                public String getName() {
                    return null;
                }

                @Override
                public Optional<List<FilteredPhrase>> getFilteredPhrases() {
                    return Optional.empty();
                }

                @Override
                public URL getOriginalTestResource() {
                    return null;
                }
            });
        }
        builder.withChildItems(testSpecifications);
    }

    @Override
    public void exitGivenTheTestSpecificationChild(TestCaseFactoryParser.GivenTheTestSpecificationChildContext ctx) { }

    @Override
    public void enterThenTestCasesAreProduced(TestCaseFactoryParser.ThenTestCasesAreProducedContext ctx) {
        Preconditions.checkNotNull(testCases, "Test cases were never created!");
    }

    @Override
    public void exitThenTestCasesAreProduced(TestCaseFactoryParser.ThenTestCasesAreProducedContext ctx) { }

    @Override
    public void enterThenEachTestCaseHasPhrases(TestCaseFactoryParser.ThenEachTestCaseHasPhrasesContext ctx) {
        Preconditions.checkNotNull(testCases, "Test cases were not instantiated!");
        int phraseCount = Integer.parseInt(ctx.integerValue().getText());
        testCases.stream().forEach(c -> assertThat(getIteratorCount(c.getContextFilteredTestSectionIterator())).isEqualTo(phraseCount));
    }

    private int getIteratorCount(Iterator<TestSection> iterator) {
        int size = 0;
        while (iterator.hasNext()) {
            iterator.next();
            size++;
        }
        return size;
    }
    @Override
    public void exitThenEachTestCaseHasPhrases(TestCaseFactoryParser.ThenEachTestCaseHasPhrasesContext ctx) { }

    private enum TestFactories {
        PREORDER,
        SINGLE_PREORDER;
    }
    @Override
    public void enterGivenSpecificTestCaseFactoryIsUsed(TestCaseFactoryParser.GivenSpecificTestCaseFactoryIsUsedContext ctx) {
        TestFactories testFactoryType = TestFactories.valueOf(
                ctx.textInDoubleQuotes().getText().toUpperCase().replaceAll(" ", "_"));
        switch (testFactoryType) {
            case PREORDER:
                testCaseFactory = new PreorderTestCaseFactory();
                break;
            case SINGLE_PREORDER:
                testCaseFactory = new SingleTestOutputPreorderTestCaseFactory();
                break;
            default:
                throw new IllegalArgumentException("No implementation for " + testFactoryType.name());
        }
    }

    @Override
    public void exitGivenSpecificTestCaseFactoryIsUsed(TestCaseFactoryParser.GivenSpecificTestCaseFactoryIsUsedContext ctx) { }

    @Override
    public void enterWhenTestSpecificationProcessedByFactory(TestCaseFactoryParser.WhenTestSpecificationProcessedByFactoryContext ctx) {
        Preconditions.checkNotNull(testCaseFactory, "Test Case factory was not set!");
        testCases = testCaseFactory.processTestSpecification(List.of(builder.build()));
    }

    @Override
    public void exitWhenTestSpecificationProcessedByFactory(TestCaseFactoryParser.WhenTestSpecificationProcessedByFactoryContext ctx) { }

    @Override
    public void enterPolymorphicDslAllRules(TestCaseFactoryParser.PolymorphicDslAllRulesContext ctx) { }

    @Override
    public void exitPolymorphicDslAllRules(TestCaseFactoryParser.PolymorphicDslAllRulesContext ctx) { }

    @Override
    public void enterGherkinStepKeyword(TestCaseFactoryParser.GherkinStepKeywordContext ctx) { }

    @Override
    public void exitGherkinStepKeyword(TestCaseFactoryParser.GherkinStepKeywordContext ctx) { }

    @Override
    public void enterIntegerValue(TestCaseFactoryParser.IntegerValueContext ctx) { }

    @Override
    public void exitIntegerValue(TestCaseFactoryParser.IntegerValueContext ctx) { }

    @Override
    public void enterTextInDoubleQuotes(TestCaseFactoryParser.TextInDoubleQuotesContext ctx) { }

    @Override
    public void exitTextInDoubleQuotes(TestCaseFactoryParser.TextInDoubleQuotesContext ctx) { }

    @Override
    public void enterDocstring(TestCaseFactoryParser.DocstringContext ctx) { }

    @Override
    public void exitDocstring(TestCaseFactoryParser.DocstringContext ctx) { }

    @Override
    public void visitTerminal(TerminalNode terminalNode) { }

    @Override
    public void visitErrorNode(ErrorNode errorNode) { }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) { }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) { }
}
