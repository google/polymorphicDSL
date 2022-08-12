package com.pdsl.grammars;

import com.google.common.base.Preconditions;
import com.pdsl.gherkin.parser.GherkinCommonContextHelper;
import com.pdsl.logging.AnsiTerminalColorHelper;
import com.pdsl.specifications.DefaultTestSpecification;
import com.pdsl.specifications.FilteredPhrase;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.testcases.*;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.*;

import static com.google.common.truth.Truth.assertThat;

public class TestCaseFactoryParserListenerImpl implements TestCaseFactoryParserListener {
    private static final GherkinCommonContextHelper ctxHelper = new GherkinCommonContextHelper(TestCaseFactoryParser.VOCABULARY);
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
        int numberOfPhrases = ctxHelper.extractInt(ctx);
        builder.withTestPhrases(generateParseTreeStubs(numberOfPhrases));
    }

    private static final ParseTree stubbedParseTree = new ParseTree() {
        @Override
        public ParseTree getParent() {
            return null;
        }

        @Override
        public ParseTree getChild(int i) {
            return null;
        }

        @Override
        public void setParent(RuleContext parent) {

        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return null;
        }

        @Override
        public String getText() {
            return "hard coded value in stubbed parse tree";
        }

        @Override
        public String toStringTree(Parser parser) {
            return "yet another hard coded value in stubbed parse tree";
        }

        @Override
        public Interval getSourceInterval() {
            return null;
        }

        @Override
        public Object getPayload() {
            return null;
        }

        @Override
        public int getChildCount() {
            return 0;
        }

        @Override
        public String toStringTree() {
            return "";
        }
    };

    private List<FilteredPhrase> generateParseTreeStubs(int count) {
        List<FilteredPhrase> parseTrees = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            parseTrees.add(new FilteredPhrase() {

                @Override
                public String getPhrase() {
                    return "hard coded stub phrase response";
                }

                @Override
                public Optional<ParseTree> getParseTree() {
                    return Optional.of(stubbedParseTree);
                }
            });

        }
        return parseTrees;
    }

    @Override
    public void exitGivenSpecificationHasPhrase(TestCaseFactoryParser.GivenSpecificationHasPhraseContext ctx) { }

    private static final FilteredPhrase stubbedPhrase  = new FilteredPhrase() {

        @Override
        public String getPhrase() {
            return "stubbed filtered phrase";
        }

        @Override
        public Optional<ParseTree> getParseTree() {

            return Optional.of(stubbedParseTree);
        }
    };
    @Override
    public void enterGivenTheTestSpecificationChild(TestCaseFactoryParser.GivenTheTestSpecificationChildContext ctx) {
        int childrenCount = ctxHelper.extractInt(ctx);
        List phrases = new ArrayList(childrenCount);
        for (int i=0; i < childrenCount; i++) {
            phrases.add(stubbedPhrase);
        }
        List<TestSpecification> testSpecifications = new ArrayList<>(1);
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


                    return Optional.of(phrases);
                }

                @Override
                public URI getOriginalTestResource() {
                    return null;
                }
            });

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
        int phraseCount = ctxHelper.extractInt(ctx);
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
        PARENT_FOR_EACH_CHILD,
        SINGLE_TEST_OUTPUT_PREORDER,
        SINGLE_PREORDER;
    }
    @Override
    public void enterGivenSpecificTestCaseFactoryIsUsed(TestCaseFactoryParser.GivenSpecificTestCaseFactoryIsUsedContext ctx) {
        TestFactories testFactoryType = TestFactories.valueOf(
               ctxHelper.extractTextInQuotes(ctx).toUpperCase().replaceAll(" ", "_"));
        switch (testFactoryType) {
            case PARENT_FOR_EACH_CHILD:
            case PREORDER:
                testCaseFactory = new PreorderTestCaseFactory();
                break;
            case SINGLE_TEST_OUTPUT_PREORDER:
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
    public void enterDocstring(TestCaseFactoryParser.DocstringContext ctx) { }

    @Override
    public void exitDocstring(TestCaseFactoryParser.DocstringContext ctx) { }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {

    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {
        throw new IllegalStateException(String.format(AnsiTerminalColorHelper.RED + "There was an error while processing a phrase!%n%s%n%s%n" + AnsiTerminalColorHelper.RESET,
                errorNode.getText(), "\tTOKEN: " + ctxHelper.getTokenSymbolicName(errorNode.getSymbol().getType()) ));
    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) { }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) { }
}
