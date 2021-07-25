package com.pdsl.transformers;

import com.pdsl.exceptions.SentenceNotFoundException;
import com.pdsl.logging.AnsiTerminalColorHelper;
import com.pdsl.specifications.PolymorphicDslTransformationException;
import org.antlr.v4.runtime.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

public interface TestSpecificationHelper {
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
            CharStream charStream = CharStreams.fromStream(new ByteArrayInputStream(baos.toByteArray()));

            Lexer pdslLexer = (Lexer) lexerClass.getDeclaredConstructor(CharStream.class).newInstance(CharStreams.fromStream(new ByteArrayInputStream(baos.toByteArray())));
            if (strategy.equals(ErrorListenerStrategy.SUBGRAMMAR)) {
                pdslLexer.removeErrorListeners();
            }
            PdslErrorListener errorListener = new PdslErrorListener();
            pdslLexer.addErrorListener(errorListener);
            List<? extends Token> allTokens = pdslLexer.getAllTokens();
            if (strategy.equals(ErrorListenerStrategy.GRAMMAR) && (allTokens.size() == 0 || errorListener.isErrorFound())) {
                throw new SentenceNotFoundException(String.format("Could not find the following sentence in the grammar:\n<START>%s<END>\n\nCommon errors include:\n\tNot having this sentence in the lexer\n\tForgetting to create a parser rule for this sentence\n\tLeading and trailing whitespace or newlines\n\tOptional End of file (EOF?) tokens needed at the end of your other lexer tokens\n\nTo further troubleshoot you may want to check for \"token recognition error\"s and/or the generated code directory logged earlier", new String(baos.toByteArray())));
            } else {
                if (allTokens.size() == 0) {
                    if (strategy.equals(ErrorListenerStrategy.SUBGRAMMAR)) {
                        logger.warn(AnsiTerminalColorHelper.BRIGHT_CYAN + "Filtering out phrase:\n\t" + new String(baos.toByteArray()) + AnsiTerminalColorHelper.RESET);
                        return Optional.empty();
                    }
                } else if (errorListener.isErrorFound()) { //Stream may have been partially consumed. Only keep if there were no errors
                    logger.warn(AnsiTerminalColorHelper.BRIGHT_YELLOW + "A line was partially matched! This may indicate an error in the grammar!");
                    logger.warn(AnsiTerminalColorHelper.BRIGHT_RED + "Filtering out phrase:\n\t" + new String((baos.toByteArray())) + AnsiTerminalColorHelper.RESET);
                    return Optional.empty();
                } else if (allTokens.get(0).getType() == Token.EOF) {  // We know the size of the list is at least 1 from the check above. See if the only token is the end of file
                    logger.warn(AnsiTerminalColorHelper.YELLOW + "Only the End of File was left. Treating as though everything has been filtered out of this phrase:\n" + pdslLexer.getText() + AnsiTerminalColorHelper.RESET);
                    return Optional.empty();
                }
            }
            pdslLexer.reset();
            return Optional.of(pdslLexer);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | IOException | NoSuchMethodException e) {
            throw new PolymorphicDslTransformationException("Could not create a lexer from the input stream!", e);
        }
    }

    public enum ErrorListenerStrategy {
        GRAMMAR,
        SUBGRAMMAR;
    }
}
