package com.pdsl.grammars;

import com.google.common.base.Preconditions;
import com.pdsl.gherkin.DefaultGherkinTestSpecificationFactory;
import com.pdsl.gherkin.specifications.GherkinTestSpecificationFactory;
import com.pdsl.specifications.LineDelimitedTestSpecificationFactory;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.TestSpecificationFactory;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

public class SpecificationFactoryListener implements TestSpecificationFactoryParserListener {

    private Set<URL> urls = new HashSet<>();
    private TestSpecificationFactory factory;
    private PolymorphicDslPhraseFilter phraseFilter;
    private Class<?> parserGrammarClass;
    private Class<?> lexerGrammarClass;
    private Class<?> parserSubgrammarClass;
    private Class<?> lexerSubgrammarClass;
    private Optional<TestSpecification> specification;
    private TestSpecificationFactoryType factoryType;
    private enum TestSpecificationFactoryType {
        LINE_DELIMITED_TEST_SPECIFICATION_FACTORY,
        GHERKIN_TEST_SPECIFICATION_FACTORY;
    }

    @Override
    public void enterGivenSpecificTestSpecificationFactory(TestSpecificationFactoryParser.GivenSpecificTestSpecificationFactoryContext ctx) {
        factoryType = TestSpecificationFactoryType.valueOf(ctx.textInDoubleQuotesEnd().getText().toUpperCase().replaceAll(" ", "_"));
    }

    @Override
    public void exitGivenSpecificTestSpecificationFactory(TestSpecificationFactoryParser.GivenSpecificTestSpecificationFactoryContext ctx) { }

    private enum GrammarType {
        ALL_GRAMMARS(AllGrammarsParser.class, AllGrammarsLexer.class);

        private Class<? extends Parser> parserClass;
        private Class<? extends Lexer> lexerClass;

        public Class<? extends Parser> getParser() {
            return parserClass;
        }

        public Class<? extends Lexer> getLexer() {
            return lexerClass;
        }

        private GrammarType(Class<? extends Parser> parserClass, Class<? extends Lexer> lexerClass) {
            this.parserClass = parserClass;
            this.lexerClass = lexerClass;
        }
    }

    @Override
    public void enterGivenSpecificGrammar(TestSpecificationFactoryParser.GivenSpecificGrammarContext ctx) {
        GrammarType grammarType = GrammarType.valueOf(ctx.textInDoubleQuotesEnd().getText().toUpperCase().replaceAll(" ", "_"));
        parserGrammarClass = grammarType.getParser();
        lexerGrammarClass = grammarType.getLexer();
    }

    @Override
    public void exitGivenSpecificGrammar(TestSpecificationFactoryParser.GivenSpecificGrammarContext ctx) { }

    @Override
    public void enterGivenSpecificSubgrammar(TestSpecificationFactoryParser.GivenSpecificSubgrammarContext ctx) {
        GrammarType grammarType = GrammarType.valueOf(ctx.textInDoubleQuotesEnd().getText().replaceAll(" ", "_").toUpperCase());
        parserSubgrammarClass = grammarType.getParser();
        lexerSubgrammarClass = grammarType.getLexer();
    }

    @Override
    public void exitGivenSpecificSubgrammar(TestSpecificationFactoryParser.GivenSpecificSubgrammarContext ctx) { }

    @Override
    public void enterThenTestSpecificationFailsDueToMissingScenario(TestSpecificationFactoryParser.ThenTestSpecificationFailsDueToMissingScenarioContext ctx) {
        Preconditions.checkNotNull(specification, "The test specification was never created");
        assertThat(specification.isEmpty()).isTrue();
    }

    @Override
    public void exitThenTestSpecificationFailsDueToMissingScenario(TestSpecificationFactoryParser.ThenTestSpecificationFailsDueToMissingScenarioContext ctx) { }

    @Override
    public void enterThenTestSpecificationFailsBecauseOfMissingStepBody(TestSpecificationFactoryParser.ThenTestSpecificationFailsBecauseOfMissingStepBodyContext ctx) {
        assertThat(specification.isEmpty()).isTrue();
    }

    @Override
    public void exitThenTestSpecificationFailsBecauseOfMissingStepBody(TestSpecificationFactoryParser.ThenTestSpecificationFailsBecauseOfMissingStepBodyContext ctx) { }

    @Override
    public void enterGivenNonExistentUrl(TestSpecificationFactoryParser.GivenNonExistentUrlContext ctx) {
        try {
            urls.add(new URL("file:nonexistent"));
        } catch (MalformedURLException e) {
            throw new TestFrameworkException("Could not successfully create a fake URL", e);
        }
    }

    @Override
    public void exitGivenNonExistentUrl(TestSpecificationFactoryParser.GivenNonExistentUrlContext ctx) { }

    @Override
    public void enterThenNoSuchResourceError(TestSpecificationFactoryParser.ThenNoSuchResourceErrorContext ctx) {
        assertThat(specification.isEmpty()).isTrue();
    }

    @Override
    public void exitThenNoSuchResourceError(TestSpecificationFactoryParser.ThenNoSuchResourceErrorContext ctx) { }

    @Override
    public void enterThenTestSpecificationHasTotalPhrases(TestSpecificationFactoryParser.ThenTestSpecificationHasTotalPhrasesContext ctx) {
        Preconditions.checkNotNull(specification, "The Test Specification was never created!");
        int expected = Integer.parseInt(ctx.integerValue().getText());
        assertThat(specification.isPresent()).isTrue();
        assertThat(countPhrases(specification.get(), 0)).isEqualTo(expected);
    }

    private int countPhrases(TestSpecification testSpecification, int total) {
        if (testSpecification.getPhrases().isPresent()) {
            total += testSpecification.getPhrases().get().size();
        }
        if (testSpecification.nestedTestSpecifications().isPresent()) {
            for (TestSpecification child : testSpecification.nestedTestSpecifications().get()) {
                countPhrases(testSpecification, total);
            }
        }
        return total;
    }

    @Override
    public void exitThenTestSpecificationHasTotalPhrases(TestSpecificationFactoryParser.ThenTestSpecificationHasTotalPhrasesContext ctx) { }

    @Override
    public void enterTestSpecificationMayBeProduced(TestSpecificationFactoryParser.TestSpecificationMayBeProducedContext ctx) {
        Preconditions.checkNotNull(lexerGrammarClass, "The lexer grammar was not set");
        Preconditions.checkNotNull(parserGrammarClass, "The parser grammar was not set");
        if (lexerSubgrammarClass == null) {
            lexerSubgrammarClass = lexerGrammarClass;
        }
        if (parserGrammarClass == null) {
            parserSubgrammarClass = parserGrammarClass;
        }
        phraseFilter = new DefaultPolymorphicDslPhraseFilter(parserGrammarClass, lexerGrammarClass, parserSubgrammarClass, lexerSubgrammarClass);
        Preconditions.checkNotNull(phraseFilter, "The phrase filter was never instantiated!");
        switch (factoryType) {
            case LINE_DELIMITED_TEST_SPECIFICATION_FACTORY:
                factory = new LineDelimitedTestSpecificationFactory(phraseFilter);
                break;
            case GHERKIN_TEST_SPECIFICATION_FACTORY:
                factory = new DefaultGherkinTestSpecificationFactory(phraseFilter);
                break;
            default:
                throw new IllegalArgumentException("Do not have any implementation for factory " + factoryType.name());
        };
        specification = factory.getTestSpecifications(urls);
    }

    @Override
    public void exitTestSpecificationMayBeProduced(TestSpecificationFactoryParser.TestSpecificationMayBeProducedContext ctx) { }

    @Override
    public void enterTestResourceProcessedByFactory(TestSpecificationFactoryParser.TestResourceProcessedByFactoryContext ctx) {

    }

    @Override
    public void exitTestResourceProcessedByFactory(TestSpecificationFactoryParser.TestResourceProcessedByFactoryContext ctx) {

    }

    @Override
    public void enterPolymorphicDslAllRules(TestSpecificationFactoryParser.PolymorphicDslAllRulesContext ctx) { }

    @Override
    public void exitPolymorphicDslAllRules(TestSpecificationFactoryParser.PolymorphicDslAllRulesContext ctx) { }

    @Override
    public void enterGherkinStepKeyword(TestSpecificationFactoryParser.GherkinStepKeywordContext ctx) { }

    @Override
    public void exitGherkinStepKeyword(TestSpecificationFactoryParser.GherkinStepKeywordContext ctx) { }

    @Override
    public void enterIntegerValue(TestSpecificationFactoryParser.IntegerValueContext ctx) { }

    @Override
    public void exitIntegerValue(TestSpecificationFactoryParser.IntegerValueContext ctx) { }

    @Override
    public void enterTextInDoubleQuotes(TestSpecificationFactoryParser.TextInDoubleQuotesContext ctx) { }

    @Override
    public void exitTextInDoubleQuotes(TestSpecificationFactoryParser.TextInDoubleQuotesContext ctx) { }

    @Override
    public void enterDocstring(TestSpecificationFactoryParser.DocstringContext ctx) { }

    @Override
    public void exitDocstring(TestSpecificationFactoryParser.DocstringContext ctx) { }

    @Override
    public void enterTextInDoubleQuotesEnd(TestSpecificationFactoryParser.TextInDoubleQuotesEndContext ctx) {

    }

    @Override
    public void exitTextInDoubleQuotesEnd(TestSpecificationFactoryParser.TextInDoubleQuotesEndContext ctx) {

    }

    @Override
    public void enterGivenTheTestResource(TestSpecificationFactoryParser.GivenTheTestResourceContext ctx) {
        throw new IllegalStateException("Have not implemented");
    }

    @Override
    public void exitGivenTheTestResource(TestSpecificationFactoryParser.GivenTheTestResourceContext ctx) { }

    @Override
    public void enterGivenTheRawResource(TestSpecificationFactoryParser.GivenTheRawResourceContext ctx) {

    }

    @Override
    public void exitGivenTheRawResource(TestSpecificationFactoryParser.GivenTheRawResourceContext ctx) { }

    @Override
    public void visitTerminal(TerminalNode terminalNode) { }

    @Override
    public void visitErrorNode(ErrorNode errorNode) { }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) { }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) { }
}
