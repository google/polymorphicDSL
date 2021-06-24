package com.pdsl.transformers;

import com.pdsl.exceptions.SentenceNotFoundException;
import com.pdsl.logging.AnsiTerminalColorHelper;
import com.pdsl.specifications.*;
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
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
//   Grab the names of all the rules from the parser and dynamically create a new rule?

public class LineDelimitedTestSpecificationFactory<P extends Parser, L extends Lexer> implements TestSpecificationFactory {
    private final Logger logger = LoggerFactory.getLogger(LineDelimitedTestSpecificationFactory.class);
    private Constructor<L> lexerConstructor;
    private Constructor<P> parserConstructor;
    private Method activePhrasesRule;
    private final ErrorListenerStrategy strategy;
    private static final String BOLD = "\033[1m";
    private static final String RESET_ANSI = "\033[0m";
    private final PhraseTransformer phraseTransformer = new LineDelimitedPhraseTransformer();

    public enum ErrorListenerStrategy {
        SUBGRAMMAR, // Ignore token recognition errors
        GRAMMAR; // Report any errors detected
    }

    public LineDelimitedTestSpecificationFactory(Class<P> parserClass, Class<L> lexerClass, ErrorListenerStrategy strategy) {
        final String allRulesMethodName = "polymorphicDslAllRules";
        try {
            this.lexerConstructor = lexerClass.getConstructor(CharStream.class);
            this.parserConstructor = parserClass.getConstructor(TokenStream.class);
            this.activePhrasesRule = parserClass.getMethod(allRulesMethodName,  (Class<?>[]) null);
            this.strategy = strategy;
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(
                    String.format("Trouble creating either the lexer or parser!\nNote the parser MUST Have a rule in the grammar called '%s'", allRulesMethodName), e);
        }
    }

    private Optional<L> createLexer(InputStream inputStream, String testId) {
        try {
            // We need to see if the lexer will recognize any of the tokens in the input stream
            // However checking this will consume the tokens in the lexer, making it useless for future processing,
            // so we create two: one to check if this line is relevant at all and the other to use if it is
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            inputStream.transferTo(baos);
            CharStream charStream = CharStreams.fromStream(new ByteArrayInputStream(baos.toByteArray()));

            L pdslLexer = lexerConstructor.newInstance(CharStreams.fromStream(new ByteArrayInputStream(baos.toByteArray())));
            pdslLexer.removeErrorListeners();
            PdslErrorListener errorListener = new PdslErrorListener();
            pdslLexer.addErrorListener(errorListener);
            List<? extends  Token> allTokens = pdslLexer.getAllTokens();
            if (strategy.equals(ErrorListenerStrategy.GRAMMAR) && (allTokens.size() == 0 || errorListener.isErrorFound()) ) {
                throw new SentenceNotFoundException(testId + ":\n" + "Could not find the following sentence in the grammar:\n" + new String(baos.toByteArray()));
            } else {
                if (allTokens.size() == 0) {
                    if (strategy.equals(ErrorListenerStrategy.SUBGRAMMAR)) {
                        logger.warn(AnsiTerminalColorHelper.BRIGHT_CYAN + "Filtering out phrase:\n\t" + new String(baos.toByteArray()) + RESET_ANSI);
                        return Optional.empty();
                    } else {

                    }
                } else if (errorListener.isErrorFound()) { //Stream may have been partially consumed. Only keep if there were no errors
                    logger.warn(AnsiTerminalColorHelper.BRIGHT_YELLOW + "A line was partially matched! This may indicate an error in the grammar!");
                    logger.warn(AnsiTerminalColorHelper.BRIGHT_RED + "Filtering out phrase:\n\t" + new String((baos.toByteArray())) + RESET_ANSI);
                    return Optional.empty();
                } else if (allTokens.get(0).getType() == Token.EOF) {  // We know the size of the list is at least 1 from the check above. See if the only token is the end of file
                    logger.warn(AnsiTerminalColorHelper.YELLOW + "Only the End of File was left. Treating as though everything has been filtered out of this phrase:\n" + pdslLexer.getText() + AnsiTerminalColorHelper.RESET);
                    return Optional.empty();
                }
            }
            pdslLexer.reset();
            return Optional.of(pdslLexer);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | IOException e) {
            throw new PolymorphicDslTransformationException("Could not create lexer from input stream!", e);
        }
    }

    private Optional<P> parserOf(InputStream inputStream, String testId) {
        Optional<L> lexer = createLexer(inputStream, testId);
        if (lexer.isEmpty()) {
            return Optional.empty();
        }
        try {
            // Create a parser-grammar file
            // Convert the grammar file to an actual parser
            P dslParser =  parserConstructor.newInstance(new CommonTokenStream(lexer.get()));
            if (strategy.equals(ErrorListenerStrategy.GRAMMAR)) {
                // Immediately quit if a problem is detected
                dslParser.setErrorHandler(new BailErrorStrategy());
            }
            return Optional.of(dslParser);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new PolymorphicDslTransformationException("Could not create parser from lexer!", e);
        }
    }

    private ParseTree parseTreeOf(P parser) {
        try {
            return (ParseTree) activePhrasesRule.invoke(parser, null);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new PolymorphicDslTransformationException("Could not make parse tree from phrase!", e);
        }
    }

    @Override
    public TestSpecification getTestSpecifications(Set<String> dslTestFilePaths) {
        Objects.requireNonNull(dslTestFilePaths, "File paths cannot be null!");
        Objects.requireNonNull(phraseTransformer, "Phrase transformer cannot be null!");
        // Make sure all paths are valid
        List<TestSpecification> testSpecifications = dslTestFilePaths.stream()
                .map(Paths::get) // Get all the files that the paths point to
                .map(file -> getTestSpecification(file.getFileName().toString(), phraseTransformer.testSpecificationPhrases(file))) // Turn them into optionals that may contain a test specification
                    .filter(Optional::isPresent) // Get the non-empty optionals
                    .map(Optional::get) // unwrap the test specification
                    .collect(Collectors.toUnmodifiableList());
        if (testSpecifications.isEmpty()) { throw new PolymorphicDslTransformationException("None of the files could be processed into a viable test specification!"); }
        // Convert transformed files and input streams into parse tree phrases
        return new DefaultTestSpecification.Builder("Line-delimited test Specification Container (" + testSpecifications.size() + " out of " + dslTestFilePaths.size() + " successfully processed)")
                .withChildTestSpecifications(testSpecifications)
                .build();
    }

    @Override
    public Optional<TestSpecification> getTestSpecification(String testId, List<InputStream> testContent) {
        DefaultTestSpecification.Builder specification = new DefaultTestSpecification.Builder(testId);
        List<ParseTree> parserTrees = new LinkedList<>();
        int phrasesFilteredOut = 0;
        for (InputStream inputStream : testContent) {
            Optional<P> parser = parserOf(inputStream, testId);
            if (parser.isPresent()) {
                parser.get().setBuildParseTree(true); // A parse tree creates a child object, causing the tree walker to traverse the rule twice
                ParseTree parseTree = parseTreeOf(parser.get());
                parserTrees.add(parseTree);
            } else {
                phrasesFilteredOut++;
            }
        }
        if (parserTrees.isEmpty()) { // Let the user know we couldn't parse
            String errorType = phrasesFilteredOut == testContent.size() ? "All phrases were filtered out of a test!" : "A test entirely failed to be parsed!";
            StringBuilder errorMessage = new StringBuilder(AnsiTerminalColorHelper.BRIGHT_YELLOW + errorType + RESET_ANSI);
            errorMessage.append("\n\t" + BOLD + "Path: " + RESET_ANSI + testId + "\n\t" +
                    BOLD + "Parser Context: " + RESET_ANSI + parserConstructor.getName() + "\n\t" +
                    BOLD + "Strategy: " + RESET_ANSI + strategy.name());
            logger.warn(errorMessage.toString()) ;
            return Optional.empty();
        }
        return Optional.of(specification.withTestPhrases(parserTrees).build());
    }

}
