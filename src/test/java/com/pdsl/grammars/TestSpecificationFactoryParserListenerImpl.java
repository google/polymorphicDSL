package com.pdsl.grammars;

import ch.qos.logback.classic.html.UrlCssBuilder;
import com.google.common.base.Preconditions;
import com.pdsl.gherkin.DefaultGherkinTestSpecificationFactory;
import com.pdsl.specifications.LineDelimitedTestSpecificationFactory;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.TestSpecificationFactory;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.*;

import static com.google.common.truth.Truth.assertThat;

public class TestSpecificationFactoryParserListenerImpl implements TestSpecificationFactoryParserListener {
    private Optional<Path> lastFile = Optional.empty();
    private Optional<Collection<TestSpecification>>  lastTestSpecification = Optional.empty();
    private Optional<TestSpecificationFactory> factory = Optional.empty();
    private Set<URL> testResources = new HashSet<>();
    private Optional<Exception> exception = Optional.empty();
    private Optional<PdslHelper.SupportedGrammars> grammar = Optional.empty();
    private Optional<PdslHelper.SupportedGrammars> subgrammar = Optional.empty();
    private PdslHelper.Factories factoryType;

    @Override
    public void enterGivenSpecificTestSpecificationFactory(TestSpecificationFactoryParser.GivenSpecificTestSpecificationFactoryContext ctx) {
        factoryType = PdslHelper.Factories.valueOf(textInDoubleQuotes(ctx.textInDoubleQuotesEnd().getText()).replaceAll(" ", "_").toUpperCase());
    }

    @Override
    public void exitGivenSpecificTestSpecificationFactory(TestSpecificationFactoryParser.GivenSpecificTestSpecificationFactoryContext ctx) { }

    @Override
    public void enterGivenSpecificGrammar(TestSpecificationFactoryParser.GivenSpecificGrammarContext ctx) {
        grammar = Optional.of(PdslHelper.SupportedGrammars.valueOf(PdslHelper.convertToEnumCase(textInDoubleQuotes(ctx.textInDoubleQuotesEnd().getText()))));
    }

    @Override
    public void exitGivenSpecificGrammar(TestSpecificationFactoryParser.GivenSpecificGrammarContext ctx) { }

    @Override
    public void enterGivenSpecificSubgrammar(TestSpecificationFactoryParser.GivenSpecificSubgrammarContext ctx) {
        subgrammar = Optional.of(PdslHelper.SupportedGrammars.valueOf(PdslHelper.convertToEnumCase(PdslHelper.extractStringInQuotes(textInDoubleQuotes(ctx.textInDoubleQuotesEnd().getText()))
                )));
    }

    private String textInDoubleQuotes(String txt) {
        return txt.strip().substring(1, txt.length()-2);
    }

    @Override
    public void exitGivenSpecificSubgrammar(TestSpecificationFactoryParser.GivenSpecificSubgrammarContext ctx) { }

    @Override
    public void enterThenTestSpecificationFailsDueToMissingScenario(TestSpecificationFactoryParser.ThenTestSpecificationFailsDueToMissingScenarioContext ctx) {
        assertThat(exception.isPresent()).isTrue();
    }

    @Override
    public void exitThenTestSpecificationFailsDueToMissingScenario(TestSpecificationFactoryParser.ThenTestSpecificationFailsDueToMissingScenarioContext ctx) { }

    @Override
    public void enterThenTestSpecificationFailsBecauseOfMissingStepBody(TestSpecificationFactoryParser.ThenTestSpecificationFailsBecauseOfMissingStepBodyContext ctx) {
        assertThat(exception.isPresent()).isTrue();
    }

    @Override
    public void exitThenTestSpecificationFailsBecauseOfMissingStepBody(TestSpecificationFactoryParser.ThenTestSpecificationFailsBecauseOfMissingStepBodyContext ctx) { }

    @Override
    public void enterGivenNonExistentUrl(TestSpecificationFactoryParser.GivenNonExistentUrlContext ctx) {
        lastFile = Optional.of(Path.of("./nonexistentfilepath"));
    }

    @Override
    public void exitGivenNonExistentUrl(TestSpecificationFactoryParser.GivenNonExistentUrlContext ctx) { }

    @Override
    public void enterThenNoSuchResourceError(TestSpecificationFactoryParser.ThenNoSuchResourceErrorContext ctx) {
        assertThat(exception.isPresent()).isTrue();
    }

    @Override
    public void exitThenNoSuchResourceError(TestSpecificationFactoryParser.ThenNoSuchResourceErrorContext ctx) { }

    @Override
    public void enterThenTestSpecificationHasTotalPhrases(TestSpecificationFactoryParser.ThenTestSpecificationHasTotalPhrasesContext ctx) {
        assertThat(lastTestSpecification.isPresent()).isTrue();
        int actual = totalPhrasesInTestSpecification(lastTestSpecification.get().stream().findFirst().get(), 0);
        assertThat(actual).isEqualTo(Integer.parseInt(ctx.integerValue().getText()));
    }

    private int totalPhrasesInTestSpecification(TestSpecification specification, Integer total) {
        if (specification.getPhrases().isPresent()) {
            total += specification.getPhrases().get().size();
        }
        if (specification.nestedTestSpecifications().isPresent()) {
            for (TestSpecification childSpecification : specification.nestedTestSpecifications().get()) {
                total += totalPhrasesInTestSpecification(childSpecification, total);
            }
        }
        return total;
    }

    @Override
    public void exitThenTestSpecificationHasTotalPhrases(TestSpecificationFactoryParser.ThenTestSpecificationHasTotalPhrasesContext ctx) { }

    @Override
    public void enterTestSpecificationMayBeProduced(TestSpecificationFactoryParser.TestSpecificationMayBeProducedContext ctx) {
        if (factory.isEmpty()) {
            throw new IllegalStateException("The Test Factory was never set!");
        } else {
            try {
                lastTestSpecification = factory.get().getTestSpecifications(testResources);
            } catch (Exception e) {
                exception = Optional.of(e);
            }
        }
    }

    @Override
    public void exitTestSpecificationMayBeProduced(TestSpecificationFactoryParser.TestSpecificationMayBeProducedContext ctx) { }

    @Override
    public void enterTestResourceProcessedByFactory(TestSpecificationFactoryParser.TestResourceProcessedByFactoryContext ctx) {
        assertThat(testResources).isNotNull();
        assertThat(testResources).isNotEmpty();
        assertThat(grammar.isPresent()).isTrue();
        if (subgrammar.isEmpty()) {
            subgrammar = grammar;
        }
        PolymorphicDslPhraseFilter phraseFilter = new DefaultPolymorphicDslPhraseFilter(grammar.get().getParserClass(),
                grammar.get().getLexerClass(), subgrammar.get().getParserClass(), subgrammar.get().getLexerClass());

        switch(factoryType) {
            case GHERKIN_TEST_SPECIFICATION_FACTORY:
                factory = Optional.of(new DefaultGherkinTestSpecificationFactory(phraseFilter));
                break;
            case LINE_DELIMITED_TEST_SPECIFICATION_FACTORY:
                factory = Optional.of(new LineDelimitedTestSpecificationFactory(phraseFilter));
                break;
            default:
                throw new IllegalArgumentException("Do not support the test factory " + factoryType.name());
        }
        try {
            lastTestSpecification = factory.get().getTestSpecifications(testResources);
        } catch (Exception e) {
            exception = Optional.of(e);
        }
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
        String resource = ctx.textInDoubleQuotes().getText();
        URL url = getClass().getClassLoader().getResource(resource);
        lastFile = Optional.of(Path.of(url.getPath()));
    }

    @Override
    public void exitGivenTheTestResource(TestSpecificationFactoryParser.GivenTheTestResourceContext ctx) {
        testResources.add(getClass().getClassLoader().getResource(ctx.textInDoubleQuotes().getText()));
    }

    @Override
    public void enterGivenTheRawResource(TestSpecificationFactoryParser.GivenTheRawResourceContext ctx) {
        String resourceBody = ctx.docstring().getText().strip().replaceAll("\"\"\"", "");
        // Create a temporary file
        try {
            lastFile = Optional.of(Files.createTempFile("pdsl" + UUID.randomUUID(), ".tmp.txt"));
            Files.writeString(lastFile.get(), resourceBody);
            testResources = new HashSet<>();
            testResources.add(lastFile.get().toUri().toURL());
        } catch (IOException e) {
            throw new IllegalStateException("Could not create a temporary directory", e);
        }
    }

    @Override
    public void exitGivenTheRawResource(TestSpecificationFactoryParser.GivenTheRawResourceContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) { }

    @Override
    public void visitErrorNode(ErrorNode errorNode) { }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) { }
}
