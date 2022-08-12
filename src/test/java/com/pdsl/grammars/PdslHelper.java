package com.pdsl.grammars;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

public class PdslHelper {

    public static String convertToEnumCase(String str) {
        return str.replaceAll(" ", "_").toUpperCase();
    }
    public static String extractStringInQuotes(String str) {
        return str.strip().replaceAll("\"", "");
    }
    public static String extractDocstring(String str) {
        return str.replaceAll("\"\"\"", "").strip();
    }
    public enum SupportedGrammars {
        ALL_GRAMMARS(AllGrammarsLexer.class, AllGrammarsParser.class);

        private Class<? extends Parser> parser;
        private Class<? extends Lexer> lexer;
        private Parser grammarParser;
        private Lexer grammarLexer;

        private SupportedGrammars(Class<? extends Lexer> lexer, Class<? extends Parser> parser) {
            this.parser = parser;
            this.lexer = lexer;
        }

        public Class<?> getLexerClass() {
            return lexer;
        }

        public Class<?> getParserClass() {
            return parser;
        }
    }

    enum Factories {
        GHERKIN_TEST_SPECIFICATION_FACTORY,
        LINE_DELIMITED_TEST_SPECIFICATION_FACTORY;
    }

    enum ListenerType {
        ALL_GRAMMARS(new AllGrammarsParserBaseListener()),
        ARITHMETIC(new SimpleArithmeticImpl());
        private ParseTreeListener listener;

        private ListenerType(ParseTreeListener listener) {
            this.listener = listener;
        }

        public ParseTreeListener getListener() {
            return listener;
        }
    }

    enum ExecutorType {
        DEFAULT_PDSL_TEST_EXECUTOR,
        DEFAULT_GHERKIN_TEST_EXECUTOR;
    }

    public static Path processRawResourceFromDocstring(String resourceBody) {
        // Create a temporary file
        try {
            Path lastFile = Files.createTempFile("pdsl" + UUID.randomUUID(), ".tmp.txt");
            Files.writeString(lastFile, resourceBody);
            return lastFile;
        } catch (IOException e) {
            throw new IllegalStateException("Could not create a temporary directory", e);
        }
    }

}