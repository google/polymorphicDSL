package com.pdsl.transformers;

import com.pdsl.runners.RecognizedBy;
import com.pdsl.logging.AnsiTerminalColorHelper;
import com.pdsl.specifications.FilteredPhrase;
import com.pdsl.specifications.PolymorphicDslTransformationException;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class DefaultPolymorphicDslPhraseFilter
        implements PolymorphicDslPhraseFilter {
    private static final String BOLD = "\033[1m";
    private static final String RESET_ANSI = "\033[0m";
    private final Logger logger = LoggerFactory.getLogger(DefaultPolymorphicDslPhraseFilter.class);
    private final Constructor<? extends Parser> subgrammarParserConstructor;
    private final Constructor<? extends Lexer> subgrammarLexerConstructor;
    private final Optional<Class<? extends Parser>> recognizerParser;
    private final Optional<Class<? extends Lexer>> recognizerLexer;
    private final Method subgrammarActivePhraseRule;
    private final Optional<String> syntaxRuleName;
    public static String DEFAULT_ALL_RULES_METHOD_NAME = "polymorphicDslAllRules";

    public DefaultPolymorphicDslPhraseFilter(Class<? extends Parser> subgrammarParser, Class<? extends Lexer> subgrammarLexer) {
        Optional<Method> syntaxRuleOptional1;
        try {
            this.subgrammarLexerConstructor = subgrammarLexer.getConstructor(CharStream.class);
            this.subgrammarParserConstructor = subgrammarParser.getConstructor(TokenStream.class);
            this.subgrammarActivePhraseRule = subgrammarParser.getMethod(DEFAULT_ALL_RULES_METHOD_NAME);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(
                    String.format("Trouble creating either the lexer or parser!%nNote the parser MUST Have a rule in the grammar called '%s'", DEFAULT_ALL_RULES_METHOD_NAME), e);
        }
        recognizerLexer = Optional.empty();
        recognizerParser = Optional.empty();
        syntaxRuleName = Optional.empty();
    }

    public DefaultPolymorphicDslPhraseFilter(Class<? extends Parser> subgrammarParser, Class<? extends Lexer> subgrammarLexer, String recognizerRule) {
        Optional<Method> syntaxRuleOptional1;
        try {
            this.subgrammarLexerConstructor = subgrammarLexer.getConstructor(CharStream.class);
            this.subgrammarParserConstructor = subgrammarParser.getConstructor(TokenStream.class);
            this.subgrammarActivePhraseRule = subgrammarParser.getMethod(recognizerRule);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(
                    String.format("Trouble creating either the lexer or parser!%nNote the parser was expected to have a rule in the grammar called '%s'", recognizerRule), e);
        }
        recognizerLexer = Optional.empty();
        recognizerParser = Optional.empty();
        syntaxRuleName = Optional.empty();
    }

    public DefaultPolymorphicDslPhraseFilter(Class<? extends Parser> subgrammarParser, Class<? extends Lexer> subgrammarLexer, Class<? extends Parser> parserRecognizer, Class<? extends Lexer> lexerRecognizer)  {
        try {
            this.subgrammarLexerConstructor = subgrammarLexer.getConstructor(CharStream.class);
            this.subgrammarParserConstructor = subgrammarParser.getConstructor(TokenStream.class);
            this.subgrammarActivePhraseRule = subgrammarParser.getMethod(DEFAULT_ALL_RULES_METHOD_NAME);
            this.recognizerLexer = Optional.of(lexerRecognizer);
            this.recognizerParser = Optional.of(parserRecognizer);

        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(
                    String.format("Trouble creating either the lexer or parser!%nNote the parser MUST Have a rule in the grammar called '%s'", DEFAULT_ALL_RULES_METHOD_NAME), e);
        }
        syntaxRuleName = Optional.of(RecognizedBy.DEFAULT_RECOGNIZER_RULE_NAME);
    }



    public DefaultPolymorphicDslPhraseFilter(Class<? extends Parser> subgrammarParser, Class<? extends Lexer> subgrammarLexer, Class<? extends Parser> parserRecognizer, Class<? extends Lexer> lexerRecognizer, String startRule, String syntaxCheckRuleName)  {
        try {
            this.subgrammarLexerConstructor = subgrammarLexer.getConstructor(CharStream.class);
            this.subgrammarParserConstructor = subgrammarParser.getConstructor(TokenStream.class);
            this.subgrammarActivePhraseRule = subgrammarParser.getMethod(startRule);
            this.recognizerLexer = Optional.of(lexerRecognizer);
            this.recognizerParser = Optional.of(parserRecognizer);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(
                    String.format("Trouble creating either the lexer or parser!%nNote the parser was expected to have a rule in the grammar called '%s'%n"
                            +"\tRecognizer Parser: %s%n\tRecognizer Lexer: %s%n\tSubgrammar Parser: %s%n\tSubgrammer Lexer: %s%n\tRule: %s%n",
                            syntaxCheckRuleName, parserRecognizer, lexerRecognizer, subgrammarParser, subgrammarLexer, startRule), e);
        }
        syntaxRuleName = Optional.of(syntaxCheckRuleName);
    }

    @Override
    public Optional<List<FilteredPhrase>> filterPhrases(List<InputStream> testContent) {
        // Avoid changing the reference of the above variable to the streams we create here
        List<InputStream> testContextForfiltering = testContent;
        if (recognizerParser.isPresent() && recognizerLexer.isPresent()) {
            testContextForfiltering = TestSpecificationHelper.checkGrammarValidity(recognizerParser.get(), recognizerLexer.get(), testContent,
                    syntaxRuleName.isPresent() ? syntaxRuleName.get() : RecognizedBy.DEFAULT_RECOGNIZER_RULE_NAME);
        }
        List<FilteredPhrase> filteredPhrases = new ArrayList<>();
        int phrasesFilteredOut = 0;
        for (InputStream inputStream : testContextForfiltering) {
            ByteArrayInputStream bais;
            try {
                bais = new ByteArrayInputStream(inputStream.readAllBytes());
            } catch (IOException e) {
                throw new PolymorphicDslTransformationException("Could not read from the stream while filtering phrases!", e);
            }
            Optional<Parser> parser = subgrammarParserOf(new ByteArrayInputStream(bais.readAllBytes()));
            if (parser.isPresent()) {
                parser.get().setBuildParseTree(true); // A parse tree creates a child object, causing the tree walker to traverse the rule twice
                ParseTree parseTree = subgrammarParseTreeOf(parser.get());
                filteredPhrases.add(new FilteredPhrase() {
                    @Override
                    public String getPhrase() {
                        return parseTree.getText();
                    }

                    @Override
                    public Optional<ParseTree> getParseTree() {
                        return Optional.of(parseTree);
                    }
                });
            } else {
                filteredPhrases.add(new FilteredPhrase() {
                    @Override
                    public String getPhrase() {
                            return new String(bais.readAllBytes());
                    }

                    @Override
                    public Optional<ParseTree> getParseTree() {
                        return Optional.empty();
                    }
                });
                phrasesFilteredOut++;
            }
        }
        if (filteredPhrases.isEmpty() || filteredPhrases.stream().noneMatch(filteredPhrase -> filteredPhrase.getParseTree().isPresent())) { // Let the user know we couldn't parse
            String errorType = phrasesFilteredOut == testContent.size() ? "All phrases were filtered out of a test!" : "A test entirely failed to be parsed!";
            StringBuilder errorMessage = new StringBuilder(AnsiTerminalColorHelper.BRIGHT_YELLOW + errorType + RESET_ANSI);
            errorMessage.append(BOLD + "\n\tParser Context: " + RESET_ANSI + subgrammarParserConstructor.getName());
            String message = errorMessage.toString();
            logger.warn(message);
            return Optional.empty();
        }
        return Optional.of(filteredPhrases);
    }

    private Optional<? extends Lexer> createSublexer(InputStream inputStream) {
        try {
            // We need to see if the lexer will recognize any of the tokens in the input stream
            // However checking this will consume the tokens in the lexer, making it useless for future processing,
            // so we create two: one to check if this line is relevant at all and the other to use if it is
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            inputStream.transferTo(baos);
            CharStream charStream = CharStreams.fromStream(new ByteArrayInputStream(baos.toByteArray()));
            Lexer pdslLexer = subgrammarLexerConstructor.newInstance(charStream);
            pdslLexer.removeErrorListeners();
            PdslErrorListener errorListener = new PdslErrorListener();
            pdslLexer.addErrorListener(errorListener);
            List<? extends Token> allTokens = pdslLexer.getAllTokens();
            if (allTokens.isEmpty()) {
                if (logger.isWarnEnabled()) {
                    logger.warn(String.format("%sFiltering out phrase:%n%s<START>%s%s%s<END>", AnsiTerminalColorHelper.BRIGHT_CYAN, RESET_ANSI, AnsiTerminalColorHelper.BRIGHT_CYAN, baos.toString(), RESET_ANSI));
                }
                return Optional.empty();
            } else if (errorListener.isErrorFound()) { //Stream may have been partially consumed. Only keep if there were no errors
                if (logger.isWarnEnabled()) {
                    String filteredTokens = allTokens.stream()
                                    .map(token -> {
                                        return String.format("\t%s: %s", pdslLexer.getVocabulary().getSymbolicName(token.getType()), token);
                                    })
                                    .collect(Collectors.joining(String.format("%n")));
                    logger.warn(String.format("%sA line was partially matched! This may indicate an error in the grammar!%n", AnsiTerminalColorHelper.YELLOW));
                    logger.warn(String.format("The match was:%n%s%s%s", AnsiTerminalColorHelper.BRIGHT_YELLOW, filteredTokens, AnsiTerminalColorHelper.RESET));
                    logger.warn(String.format("%sFiltering out phrase:%n%s%n\tLexer:\t%s%s%n", AnsiTerminalColorHelper.BRIGHT_RED, baos.toString(), pdslLexer.getClass(), RESET_ANSI));
                }
                return Optional.empty();
            } else if (allTokens.get(0).getType() == Token.EOF) {  // We know the size of the list is at least 1 from the check above. See if the only token is the end of file
                String message = String.format("%sOnly the End of File was left. Treating as though everything has been filtered out of this phrase:%n%s%s", AnsiTerminalColorHelper.YELLOW, pdslLexer.getText(), AnsiTerminalColorHelper.RESET);
                logger.warn(message);
                return Optional.empty();
            }
            pdslLexer.reset();
            return Optional.of(pdslLexer);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | IOException e) {
            throw new PolymorphicDslTransformationException("Could not create lexer from input stream!", e);
        }
    }

    private Optional<Parser> subgrammarParserOf(InputStream inputStream) {
        Optional<? extends Lexer> lexer = createSublexer(inputStream);
        if (lexer.isEmpty()) {
            return Optional.empty();
        }
        try {
            // Create a parser-grammar file
            // Convert the grammar file to an actual parser
            Parser dslParser = subgrammarParserConstructor.newInstance(new CommonTokenStream(lexer.get()));
            return Optional.of(dslParser);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new PolymorphicDslTransformationException("Could not create parser from lexer!", e);
        }
    }

    private ParseTree subgrammarParseTreeOf(Parser parser) {
        try {
            // Remove error messages. These are provided in check grammar.
            parser.removeErrorListeners();
            return (ParseTree) subgrammarActivePhraseRule.invoke(parser, null);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new PolymorphicDslTransformationException("Could not make parse tree from phrase!", e);
        }
    }
}
