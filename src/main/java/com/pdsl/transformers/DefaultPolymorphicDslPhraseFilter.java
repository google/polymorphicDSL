package com.pdsl.transformers;

import com.pdsl.logging.AnsiTerminalColorHelper;
import com.pdsl.specifications.FilteredPhrase;
import com.pdsl.specifications.PolymorphicDslTransformationException;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class DefaultPolymorphicDslPhraseFilter<SP extends Parser, SL extends Lexer>
        implements PolymorphicDslPhraseFilter {

    private static final String BOLD = "\033[1m";
    private static final String RESET_ANSI = "\033[0m";
    private final Logger logger = LoggerFactory.getLogger(DefaultPolymorphicDslPhraseFilter.class);
    private final Constructor<SP> subgrammarParserConstructor;
    private final Constructor<SL> subgrammarLexerConstructor;
    private final Method subgrammarActivePhraseRule;
    private final ErrorListenerStrategy strategy;

    public DefaultPolymorphicDslPhraseFilter(Class<SP> subgrammarParser, Class<SL> subgrammarLexer) {
        final String allRulesMethodName = "polymorphicDslAllRules";
        try {
            this.subgrammarLexerConstructor = subgrammarLexer.getConstructor(CharStream.class);
            this.subgrammarParserConstructor = subgrammarParser.getConstructor(TokenStream.class);
            this.subgrammarActivePhraseRule = subgrammarParser.getMethod(allRulesMethodName, (Class<?>[]) null);
            this.strategy = ErrorListenerStrategy.GRAMMAR;
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(
                    String.format("Trouble creating either the lexer or parser!%nNote the parser MUST Have a rule in the grammar called '%s'", allRulesMethodName), e);
        }
    }

    @Override
    public Optional<List<FilteredPhrase>> filterPhrases(List<InputStream> testContent) {
        List<FilteredPhrase> filteredPhrases = new LinkedList<>();
        int phrasesFilteredOut = 0;
        for (InputStream inputStream : testContent) {
            Optional<SP> parser = subgrammarParserOf(inputStream);
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
                        try {
                            return new String(inputStream.readAllBytes());
                        } catch (IOException e) {
                            throw new PolymorphicDslTransformationException("Problem when filtering phrases!", e);
                        }
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
            errorMessage.append(BOLD + "\n\tParser Context: " + RESET_ANSI + subgrammarParserConstructor.getName() + "\n\t" +
                    BOLD + "Strategy: " + RESET_ANSI + strategy.name());
            String message = errorMessage.toString();
            logger.warn(message);
            return Optional.empty();
        }
        return Optional.of(filteredPhrases);
    }

    private Optional<SL> createSublexer(InputStream inputStream) {
        try {
            // We need to see if the lexer will recognize any of the tokens in the input stream
            // However checking this will consume the tokens in the lexer, making it useless for future processing,
            // so we create two: one to check if this line is relevant at all and the other to use if it is
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            inputStream.transferTo(baos);
            CharStream charStream = CharStreams.fromStream(new ByteArrayInputStream(baos.toByteArray()));
            SL pdslLexer = subgrammarLexerConstructor.newInstance(charStream);
            PdslErrorListener errorListener = new PdslErrorListener();
            pdslLexer.addErrorListener(errorListener);
            List<? extends Token> allTokens = pdslLexer.getAllTokens();
            if (allTokens.isEmpty()) {
                if (logger.isWarnEnabled()) {
                    logger.warn(String.format("%sFiltering out phrase:%n%s<START>%s%s%s<END>", AnsiTerminalColorHelper.BRIGHT_CYAN, RESET_ANSI, AnsiTerminalColorHelper.BRIGHT_CYAN, new String(baos.toByteArray()), RESET_ANSI));
                }
                return Optional.empty();
            } else if (errorListener.isErrorFound()) { //Stream may have been partially consumed. Only keep if there were no errors
                if (logger.isWarnEnabled()) {
                    logger.warn("%sA line was partially matched! This may indicate an error in the grammar!", AnsiTerminalColorHelper.BRIGHT_YELLOW);
                    logger.warn("The match was: %s", allTokens);
                    logger.warn("%sFiltering out phrase:%n\t%s%s", AnsiTerminalColorHelper.BRIGHT_RED, new String((baos.toByteArray())), RESET_ANSI);
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

    private Optional<SP> subgrammarParserOf(InputStream inputStream) {
        Optional<SL> lexer = createSublexer(inputStream);
        if (lexer.isEmpty()) {
            return Optional.empty();
        }
        try {
            // Create a parser-grammar file
            // Convert the grammar file to an actual parser
            SP dslParser = subgrammarParserConstructor.newInstance(new CommonTokenStream(lexer.get()));
            return Optional.of(dslParser);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new PolymorphicDslTransformationException("Could not create parser from lexer!", e);
        }
    }

    private ParseTree subgrammarParseTreeOf(SP parser) {
        try {
            return (ParseTree) subgrammarActivePhraseRule.invoke(parser, null);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new PolymorphicDslTransformationException("Could not make parse tree from phrase!", e);
        }
    }

    public enum ErrorListenerStrategy {
        SUBGRAMMAR, // Ignore token recognition errors
        GRAMMAR // Report any errors detected
    }

}
