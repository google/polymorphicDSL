package com.pdsl.grammars;

import com.pdsl.exceptions.PolymorphicDslFrameworkException;
import com.pdsl.gherkin.DefaultGherkinTestSpecificationFactory;
import com.pdsl.gherkin.parser.GherkinCommonContextHelper;
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

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static com.google.common.truth.Truth.assertThat;

public class TestSpecificationFactoryParserListenerImpl implements TestSpecificationFactoryParserListener {

    private static final GherkinCommonContextHelper ctxHelper = new GherkinCommonContextHelper(TestSpecificationFactoryParser.VOCABULARY);
    private Optional<Path> lastFile = Optional.empty();
    private Optional<Collection<TestSpecification>>  lastTestSpecification = Optional.empty();
    private Optional<TestSpecificationFactory> factory = Optional.empty();
    private Set<URI> testResources = new HashSet<>();
    private Optional<Exception> exception = Optional.empty();
    private Optional<PdslHelper.SupportedGrammars> grammar = Optional.empty();
    private Optional<PdslHelper.SupportedGrammars> subgrammar = Optional.empty();
    private PdslHelper.Factories factoryType;

    @Override
    public void enterGivenSpecificTestSpecificationFactory(TestSpecificationFactoryParser.GivenSpecificTestSpecificationFactoryContext ctx) {
        factoryType = PdslHelper.Factories.valueOf(ctxHelper.extractTextInQuotes(ctx).replaceAll(" ", "_").toUpperCase());
    }

    @Override
    public void exitGivenSpecificTestSpecificationFactory(TestSpecificationFactoryParser.GivenSpecificTestSpecificationFactoryContext ctx) { }

    @Override
    public void enterGivenSpecificGrammar(TestSpecificationFactoryParser.GivenSpecificGrammarContext ctx) {
        grammar = Optional.of(PdslHelper.SupportedGrammars.valueOf(PdslHelper.convertToEnumCase(ctxHelper.extractTextInQuotes(ctx))));
    }

    @Override
    public void exitGivenSpecificGrammar(TestSpecificationFactoryParser.GivenSpecificGrammarContext ctx) { }

    @Override
    public void enterGivenSpecificSubgrammar(TestSpecificationFactoryParser.GivenSpecificSubgrammarContext ctx) {
        subgrammar = Optional.of(PdslHelper.SupportedGrammars.valueOf(PdslHelper.convertToEnumCase(
                ctxHelper.extractTextInQuotes(ctx))));
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
        assertThat(actual).isEqualTo(ctxHelper.extractInt(ctx));
    }

    private int totalPhrasesInTestSpecification(TestSpecification specification, Integer total) {
        if (specification.getFilteredPhrases().isPresent()) {
            total += specification.getFilteredPhrases().get().size();
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
        PolymorphicDslPhraseFilter phraseFilter = new DefaultPolymorphicDslPhraseFilter((Class<? extends Parser>)subgrammar.get().getParserClass(), (Class<? extends Lexer>)subgrammar.get().getLexerClass());

        switch(factoryType) {
            case GHERKIN_TEST_SPECIFICATION_FACTORY:
                factory = Optional.of(new DefaultGherkinTestSpecificationFactory.Builder(phraseFilter).build());
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
    public void enterDocstring(TestSpecificationFactoryParser.DocstringContext ctx) { }

    @Override
    public void exitDocstring(TestSpecificationFactoryParser.DocstringContext ctx) { }

    @Override
    public void enterGivenTheTestResource(TestSpecificationFactoryParser.GivenTheTestResourceContext ctx) {
        String resource = ctxHelper.extractTextInQuotes(ctx);
        URL url = getClass().getClassLoader().getResource(resource);
        lastFile = Optional.of(Path.of(url.getPath()));
    }

    @Override
    public void exitGivenTheTestResource(TestSpecificationFactoryParser.GivenTheTestResourceContext ctx) {
        try {
            testResources.add(getClass().getClassLoader().getResource(ctxHelper.extractTextInQuotes(ctx)).toURI());
        } catch (URISyntaxException e) {
            throw new PolymorphicDslFrameworkException("Could not add URI to test resources!", e);
        }
    }

    @Override
    public void enterGivenTheRawResource(TestSpecificationFactoryParser.GivenTheRawResourceContext ctx) {
        String resourceBody = ctxHelper.extractDocstring(ctx);
        // Create a temporary file
        try {
            lastFile = Optional.of(Files.createTempFile("pdsl" + UUID.randomUUID(), ".tmp.txt"));
            Files.writeString(lastFile.get(), resourceBody);
            testResources = new HashSet<>();
            testResources.add(lastFile.get().toUri());
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
