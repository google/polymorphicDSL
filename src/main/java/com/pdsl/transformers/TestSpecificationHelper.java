package com.pdsl.transformers;

import com.pdsl.exceptions.PolymorphicDslFrameworkException;
import com.pdsl.exceptions.SentenceNotFoundException;
import com.pdsl.logging.AnsiTerminalColorHelper;
import com.pdsl.specifications.PolymorphicDslTransformationException;
import org.antlr.v4.runtime.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A utility class that provides common functionality for processing an arbitrary PDSL test.
 */
public interface TestSpecificationHelper {

    /**
     *  Generates the parse trees for arbitrary input using the specified ANTLR lexers and parsers.
     *
     * @param inputStream the input to parse
     * @param strategy whether or not errors should be printed or hidden when parsing
     * @param parserClass the ANTLR parser grammar for the input
     * @param lexerClass the ANTLR lexer grammar for tokenizing the input
     * @return
     */
    static Optional<Parser> parserOf(InputStream inputStream, ErrorListenerStrategy strategy, Class<?> parserClass, Class<?> lexerClass) {

        Optional<? extends Lexer> lexer = lexerOf(lexerClass, inputStream, strategy);
        if (lexer.isEmpty()) {
            return Optional.empty();
        }
        try {
            // Create a parser-grammar file
            // Convert the grammar input to actual parser tokens
            Parser dslParser = (Parser) parserClass
                    .getDeclaredConstructor(TokenStream.class)
                    .newInstance(new CommonTokenStream(lexer.get()));
            if (strategy.equals(ErrorListenerStrategy.GRAMMAR)) {
                // Immediately quit if a problem is detected
                dslParser.setErrorHandler(new BailErrorStrategy());
            }
            return Optional.of(dslParser);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new PolymorphicDslTransformationException("Could not create parser from lexer!", e);
        }
    }

    private static Optional<Lexer> lexerOf(Class<?> lexerClass, InputStream inputStream, ErrorListenerStrategy strategy) {
        try {
            final Logger logger = LoggerFactory.getLogger(TestSpecificationHelper.class);
            // We need to see if the lexer will recognize any of the tokens in the input stream
            // However checking this will consume the tokens in the lexer, making it useless for future processing,
            // so we create two: one to check if this line is relevant at all and the other to use if it is
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            inputStream.transferTo(baos);
            Lexer pdslLexer = (Lexer) lexerClass.getDeclaredConstructor(CharStream.class).newInstance(CharStreams.fromStream(new ByteArrayInputStream(baos.toByteArray())));
            if (strategy.equals(ErrorListenerStrategy.SUBGRAMMAR)) {
                //pdslLexer.removeErrorListeners();
            }
            PdslErrorListener errorListener = new PdslErrorListener();
            pdslLexer.addErrorListener(errorListener);
            List<? extends Token> allTokens = pdslLexer.getAllTokens();
            if (strategy.equals(ErrorListenerStrategy.GRAMMAR) && (allTokens.isEmpty() || errorListener.isErrorFound())) {
                throw new SentenceNotFoundException(String.format("Could not find the following sentence in the grammar:%n<START>%s<END>%n%nCommon errors include:%n\tNot having this sentence in the lexer%n\tForgetting to create a parser rule for this sentence%n\tLeading and trailing whitespace or newlines%n\tOptional End of file (EOF?) tokens needed at the end of your other lexer tokens%n%nTo further troubleshoot you may want to check for \"token recognition error\"s and/or the generated code directory logged earlier", baos.toString()));
            } else {
                if (allTokens.isEmpty()) {
                    if (strategy.equals(ErrorListenerStrategy.SUBGRAMMAR)) {
                        if (logger.isWarnEnabled()) {
                            logger.warn(String.format("%sFiltering out phrase:%n\t%s%s", AnsiTerminalColorHelper.BRIGHT_CYAN, baos.toString(), AnsiTerminalColorHelper.RESET));
                        }
                        return Optional.empty();
                    }
                } else if (errorListener.isErrorFound()) { //Stream may have been partially consumed. Only keep if there were no errors
                    if (logger.isWarnEnabled()) {
                        logger.warn(AnsiTerminalColorHelper.BRIGHT_YELLOW + "A line was partially matched! This may indicate an error in the grammar!");
                        logger.warn(String.format("%sFiltering out phrase:%n%s%nLexed as:%n\t%s%s%n",
                                AnsiTerminalColorHelper.BRIGHT_RED, baos.toString(), allTokens.stream().map(token -> {
                                    return String.format("%s,\t%s", token, pdslLexer.getVocabulary().getSymbolicName(token.getType()));
                                }).collect(Collectors.joining(String.format("%n\t"))), AnsiTerminalColorHelper.RESET));
                    }
                    return Optional.empty();
                } else if (allTokens.get(0).getType() == Token.EOF) {  // We know the size of the list is at least 1 from the check above. See if the only token is the end of file
                    logger.warn(String.format("%sOnly the End of File was left. Treating as though everything has been filtered out of this phrase:%n%s%s",
                            AnsiTerminalColorHelper.YELLOW, pdslLexer.getText(), AnsiTerminalColorHelper.RESET));
                    return Optional.empty();
                }
            }
            pdslLexer.reset();
            return Optional.of(pdslLexer);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | IOException | NoSuchMethodException e) {
            throw new PolymorphicDslTransformationException("Could not create a lexer from the input stream!", e);
        }
    }

    enum ErrorListenerStrategy {
        GRAMMAR,
        SUBGRAMMAR
    }

    /**
     * Determines whether the input as a whole conforms to a specified syntactic rule.
     *
     * Note that calling this method will consume the provided input streams! This method will
     * return a copy of the provided inputs for future use if needed.
     *
     * @param parserClass the ANTLR parser to use for the input
     * @param lexerClass the ANTLR lexer for tokenizing the input
     * @param inputStreams the input to parse
     * @param syntaxRuleName the ANTLR rule in the provided parser to use for checking hte input
     * @return a new copy of the provided InputStreams
     */
    static List<InputStream> checkGrammarValidity(Class<? extends Parser> parserClass, Class<? extends Lexer> lexerClass,
                                     List<InputStream> inputStreams, String syntaxRuleName) {
        // Combine the input streams into a single stream
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        List<InputStream> inputStreamCopy = new ArrayList<>(inputStreams.size());
        try {
            for (InputStream inputStream : inputStreams) {
                byte[] bytes = inputStream.readAllBytes();
                baos.write(bytes);
                inputStreamCopy.add(new ByteArrayInputStream(bytes));
            }
            checkGrammarValidity(parserClass, lexerClass, new ByteArrayInputStream(baos.toByteArray()), syntaxRuleName);
        } catch (IOException e) {
            throw new PolymorphicDslTransformationException("Error while reading input produced from test resource", e);
        }
        return inputStreamCopy;
    }

    /**
     * Determines whether the input as a whole conforms to a specified syntactic rule.
     *
     * Note that calling this method will consume the provided input stream! This method will
     * return a copy of the provided input for future use if needed.
     *
     * @param parserClass the ANTLR parser to use for the input
     * @param lexerClass the ANTLR lexer for tokenizing the input
     * @param inputStream the input to parse
     * @param syntaxRuleName the ANTLR rule in the provided parser to use for checking hte input
     * @return a new copy of the provided InputStream
     */
    static void checkGrammarValidity(Class<? extends Parser> parserClass, Class<? extends Lexer> lexerClass, InputStream inputStream, String syntaxRuleName) {
        Method syntaxRule;
        InputStream bufferdInputStream = new BufferedInputStream(inputStream);
        bufferdInputStream.mark(0);
        try {
             syntaxRule = parserClass.getMethod(syntaxRuleName);
        } catch (NoSuchMethodException e) {
            throw new PolymorphicDslTransformationException(String.format(
                    "There was no production rule named '%s' in parser %s",
                    syntaxRuleName, parserClass.getName()));
        }
        Optional<Parser> parser = parserOf(bufferdInputStream, ErrorListenerStrategy.SUBGRAMMAR, parserClass, lexerClass);
        if (parser.isPresent()) {
            try {
                Parser syntaxParser = parser.get();
                // Get any helpful error messages using ANTLR4s default error correcting strategy
                syntaxRule.invoke(parserClass.cast(syntaxParser), null);
                bufferdInputStream.reset();
                // Run again and crash with a runtime exception if anything is incorrect
                Optional<Parser> strictParser = parserOf(bufferdInputStream, ErrorListenerStrategy.GRAMMAR, parserClass, lexerClass);
                syntaxRule.invoke(parserClass.cast(strictParser.get()), null);
            } catch (IllegalAccessException | IOException e) {
                throw new PolymorphicDslTransformationException("Unable to check the syntax of the test resource!",
                        e);
            } catch (InvocationTargetException e) {
                try {
                    bufferdInputStream.reset();
                    StringBuilder parsedTokens = new StringBuilder();
                    for (int i=0; i < parser.get().getTokenStream().size(); i++) {
                        Token token = parser.get().getTokenStream().get(i);
                        Lexer lexer = (Lexer) lexerClass.getDeclaredConstructor(CharStream.class).newInstance(CharStreams.fromStream(new ByteArrayInputStream(new byte[0])));
                        String tokenType = lexer.getVocabulary().getSymbolicName(token.getType());
                        parsedTokens.append(String.format("%sType: %s%s%n%s%n%n%s", AnsiTerminalColorHelper.BRIGHT_RED, tokenType, AnsiTerminalColorHelper.RED,  token,AnsiTerminalColorHelper.RESET));
                    }

                throw new PolymorphicDslTransformationException(

                        String.format("There was an error while checking the syntax of the test resource!%nResource Text:%n%s%n%nParsed as:%n%s%n",
                                new String(bufferdInputStream.readAllBytes()), parsedTokens), e);
                } catch (IOException io) {
                    throw new PolymorphicDslTransformationException(
                            "There was an error while checking the syntax of the test resource!%nWas unable to get the source text that caused the error"
                                    , e);
                } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException   ex) {
                    throw new PolymorphicDslFrameworkException("An error occurred while trying to get the vocabulary of a lexer. This may be due to an API change in the ANTLR4 library.", ex);
                }
            }
        } else {
            try {
                bufferdInputStream.reset();
                throw new PolymorphicDslTransformationException(String.format(
                        "Syntax check on grammar failed!%n\tParser: %s%n\tLexer: %s%n\tInput:%n%s",
                        parserClass.getName(), lexerClass.getName(),
                        new String(bufferdInputStream.readAllBytes())
                ));
            } catch (IOException e) {
                throw new PolymorphicDslTransformationException(String.format(
                        "Syntax check on grammar failed!%n\tParser: %s%n\tLexer: %s%n\tInput: " +
                                "<Could not get input. See stack trace for further details.>",
                        parserClass.getName(), lexerClass.getName()), e);
            }
        }

    }
}
