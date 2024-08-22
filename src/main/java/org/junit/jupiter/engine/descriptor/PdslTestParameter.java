package org.junit.jupiter.engine.descriptor;

import com.google.common.base.Preconditions;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

import java.util.Optional;
import java.util.function.Supplier;

import static com.pdsl.runners.PdslTest.DEFAULT_ALL_RULE;

/**
 * A Data Transfer Object provided to either a {@link
 * org.junit.jupiter.engine.descriptor.PdslGeneralInvocationContextProvider
 * PdslGeneralInvocationContextProvider}
 * or a {@link org.junit.jupiter.engine.descriptor.PdslGherkinInvocationContextProvider
 * PdslGherkinInvocationContextProvider}.
 *
 * This is ultimately to integrate a JUnit5 @TestTemplate with PDSL.
 */
public class PdslTestParameter {

    private final Optional<Class<? extends Parser>> recognizedByParser;
    private final Optional<Class<? extends Lexer>> recognizedByLexer;
    private final Class<? extends Parser> parser;
    private final Class<? extends Lexer> lexer;
    private final Optional<Supplier<? extends ParseTreeListener>> listener;
    private final Optional<Supplier<? extends ParseTreeVisitor<?>>> visitor;
    private final String tagExpression;
    private final String[] includesResources;
    private final String[] excludesResources;
    private final String startRule;
    private final Optional<String> recognizerRule;

    private PdslTestParameter(Builder builder) {
        this.parser = builder.parser;
        this.lexer = builder.lexer;
        this.listener = builder.listener;
        this.visitor = builder.visitor;
        this.tagExpression = builder.tagExpression;
        this.includesResources = builder.includesResources;
        this.excludesResources = builder.excludesResources;
        this.startRule = builder.startRule;
        this.recognizerRule = builder.recognizerRule;
        this.recognizedByLexer = builder.recognizedByLexer;
        this.recognizedByParser = builder.recognizedByParser;
    }

    public Optional<Class<? extends Parser>> getRecognizedByParser() {
        return recognizedByParser;
    }

    public Optional<Class<? extends Lexer>> getRecognizedByLexer() {
        return recognizedByLexer;
    }

    public Class<? extends Parser> getParser() {
        return parser;
    }

    public Class<? extends Lexer> getLexer() {
        return lexer;
    }

    public Optional<Supplier<? extends ParseTreeListener>> getListener() {
        return listener;
    }

    public Optional<Supplier<? extends ParseTreeVisitor<?>>> getVisitor() {
        return visitor;
    }

    public String getTagExpression() {
        return tagExpression;
    }

    public String[] getIncludesResources() {
        return includesResources;
    }

    public String[] getExcludesResources() {
        return excludesResources;
    }

    public String getStartRule() {
        return startRule;
    }

    public Optional<String> getRecognizerRule() {
        return recognizerRule;
    }

    public static class Builder {
        private Class<? extends Parser> parser;
        private Class<? extends Lexer> lexer;
        private Optional<Class<? extends Parser>> recognizedByParser = Optional.empty();
        private Optional<Class<? extends Lexer>> recognizedByLexer = Optional.empty();
        private Optional<Supplier<? extends ParseTreeListener>> listener = Optional.empty();
        private Optional<Supplier<? extends ParseTreeVisitor<?>>> visitor = Optional.empty();
        private String tagExpression = "";
        private String[] includesResources = {"*.feature"};
        private String[] excludesResources = {};
        private String startRule = DEFAULT_ALL_RULE;
        private Optional<String> recognizerRule = Optional.empty();

        public Builder(
                Class<? extends Lexer> lexer,
                Class<? extends Parser> parser,
                Supplier<? extends ParseTreeVisitor<?>> visitor
                        ) {
            Preconditions.checkNotNull(parser, "Parser cannot be null!");
            Preconditions.checkNotNull(lexer, "Lexer cannot be null!");
            Preconditions.checkNotNull(visitor, "Visitor cannot be null!");
            this.parser = parser;
            this.lexer = lexer;
            this.visitor = Optional.of(visitor);
        }

        public Builder(
                Supplier<? extends ParseTreeListener> listener,
                Class<? extends Lexer> lexer,
                Class<? extends Parser> parser
        ) {
            Preconditions.checkNotNull(parser, "Parser cannot be null!");
            Preconditions.checkNotNull(lexer, "Lexer cannot be null!");
            Preconditions.checkNotNull(listener, "Visitor cannot be null!");
            this.parser = parser;
            this.lexer = lexer;
            this.listener = Optional.of(listener);
        }

        public Builder withRecognizer(Class<? extends Lexer> recognizedByLexer, Class<? extends Parser> recognizedByParser) {
            if (recognizedByLexer == null ^ recognizedByParser == null) {
                throw new IllegalArgumentException("If a null lexer or parser is specified then both must be null!");
            }
            this.recognizedByLexer = Optional.ofNullable(recognizedByLexer);
            this.recognizedByParser = Optional.ofNullable(recognizedByParser);
            return this;
        }

        public Builder withParser(Class<? extends Parser> parser) {
            Preconditions.checkNotNull(parser, "Parser cannot be null!");
            this.parser = parser;
            return this;
        }

        public Builder withLexer(  Class<? extends Lexer> lexer) {
            Preconditions.checkNotNull(lexer, "Lexer cannot be null!");
            this.lexer = lexer;
            return this;
        }

        public Builder withListener( Supplier<? extends ParseTreeListener> listener) {
            this.listener = Optional.of(listener);
            return this;
        }

        public Builder withVisitor(Supplier<? extends ParseTreeVisitor<?>>  visitor) {
            this.visitor = Optional.of(visitor);
            return this;
        }

        public Builder withTagExpression(String str) {
            Preconditions.checkNotNull(str, "Tag expression cannot be null!");
            this.tagExpression = str;
            return  this;
        }

        public Builder withIncludedResources(String[] resources) {
            this.includesResources = resources;
            return this;
        }

        public Builder withExcludedResources(String[] excludes) {
            this.excludesResources = excludes;
            return this;
        }

        public Builder withStartRule(String startRule) {
            Preconditions.checkNotNull(startRule, "start rule cannot be null!");
            this.startRule = startRule;
            return this;
        }

        public Builder withRecognizerRule(String recognizerRule) {
            Preconditions.checkNotNull(recognizerRule, "Recognizer rule cannot be null!");
            Preconditions.checkArgument(!recognizerRule.isEmpty(), "Recognizer rule cannot be empty!");
            this.recognizerRule = Optional.of(recognizerRule);
            return this;
        }

        public PdslTestParameter build() {
            return new PdslTestParameter(this);
        }
    }
}

