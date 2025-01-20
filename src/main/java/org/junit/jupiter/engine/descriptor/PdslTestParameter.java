package org.junit.jupiter.engine.descriptor;

import com.google.common.base.Preconditions;
import com.pdsl.gherkin.filter.GherkinTagFiltererAdapter;
import com.pdsl.gherkin.filter.GherkinTagsVisitorImpl;
import com.pdsl.gherkin.filter.TagFilterer;
import java.util.logging.Logger;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

import java.util.List;
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

    private static final Logger logger = Logger.getLogger(PdslTestParameter.class.getName());
    private final Optional<Class<? extends Parser>> recognizedByParser;
    private final Optional<Class<? extends Lexer>> recognizedByLexer;
    private final Class<? extends Parser> parser;
    private final Class<? extends Lexer> lexer;
    private final Optional<Supplier<? extends ParseTreeListener>> listener;
    private final Optional<Supplier<? extends ParseTreeVisitor<?>>> visitor;
    private final Optional<List<Interpreter>> interpreterParams;
    private final String tagExpression;
    private final String[] includesResources;
    private final String[] excludesResources;
    private final String startRule;
    private final Optional<String> recognizerRule;
    private final TagFilterer tagFilterer;

    private PdslTestParameter(Builder builder) {
      Preconditions.checkArgument((builder.listener.isPresent()
                ^ builder.visitor.isPresent() ^ builder.interpreterParams.isPresent()),
                "You can only have one of a visitor, listener or list of interpreterDtos!");
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
        this.interpreterParams = builder.interpreterParams;
        this.tagFilterer = builder.tagFilterer;
    }

    public Optional<List<Interpreter>> getInterpreters() { return interpreterParams; }

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

  public TagFilterer getTagFilterer() {
    return tagFilterer;
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
        private Optional<List<Interpreter>> interpreterParams = Optional.empty();

        private TagFilterer tagFilterer = new GherkinTagFiltererAdapter(new GherkinTagsVisitorImpl());

        public Builder withTagFilterer(TagFilterer tagFilterer) {
            this.tagFilterer = tagFilterer;
            return this;
        }



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

        public Builder(List<Interpreter> interpreters) {
            withInterpreters(interpreters);
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
            this.interpreterParams = Optional.empty();
            return this;
        }

        public Builder withLexer(  Class<? extends Lexer> lexer) {
            Preconditions.checkNotNull(lexer, "Lexer cannot be null!");
            this.lexer = lexer;
            this.interpreterParams = Optional.empty();
            return this;
        }

        public Builder withInterpreters(List<Interpreter> interpreters) {
            Preconditions.checkNotNull(interpreters, "InterpreterParams cannot be null!");
            Preconditions.checkArgument(!interpreters.isEmpty(), "Interpreter params cannot be empty!");
            interpreters.forEach(i -> Preconditions.checkNotNull(i, "InterpreterDto cannot be null!"));
            interpreters.forEach(i -> Preconditions.checkNotNull(i.interpreterObj(), "InterpreterObj cannot be null!"));
            this.interpreterParams = Optional.of(interpreters);
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
        String commandLineTags = System.getProperty("tags");
        if(commandLineTags != null && !commandLineTags.isEmpty()) {
          this.tagExpression = commandLineTags;
          logger.info("Overriding runner tags with tags provided in the command line {%s}".formatted(commandLineTags) );
          logger.info("Ignoring provided tag expression {%s}".formatted(str) );
        } else {
          this.tagExpression =str;
        }

        return this;
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
    public static PdslTestParameter createTestParameter(Supplier<ParseTreeListener> parseTreeListenerSupplier,
        Class<? extends Lexer> lexerClass,
        Class<? extends Parser> parserClass,
        String[] includedResources) {
        String defaultTagExpression = "@comment_tag#2";
        String commandLineTags = System.getProperty("tags"); // Get the value

        String effectiveTagExpression = (commandLineTags != null && !commandLineTags.isEmpty())
            ? commandLineTags
            : defaultTagExpression;

        return new PdslTestParameter.Builder(parseTreeListenerSupplier, lexerClass, parserClass)
            .withTagExpression(effectiveTagExpression)
            .withIncludedResources(includedResources)
            .build();
    }

    /**
   * I've introduced a static factory method createTestParameter. This method handles the logic for
   * overriding the tag expression. This keeps the builder focused on building objects and separates
   * the configuration logic.
   */
  public static PdslTestParameter createTestParameter(
      Supplier<ParseTreeListener> parseTreeListenerSupplier,
      Class<? extends Lexer> lexerClass,
      Class<? extends Parser> parserClass,
      String[] includedResources, String defaultTagExpression) {
    // retrieves the value passed from the command line (e.g., -Dtags="@smoke").
    String commandLineTags = System.getProperty("tags");
    // handles the logic for overriding the tag expression
    String effectiveTagExpression = (commandLineTags != null && !commandLineTags.isEmpty())
        ? commandLineTags
        : defaultTagExpression;

    // // String effectiveTagExpression = defaultTagExpression;  // Use default if invalid format
    // if (commandLineTags != null && !commandLineTags.isEmpty()) {
    //   String pattern = "^(?:@\\w+(?: and | or )?)*@\\w+$";
    //   if (Pattern.matches(pattern, commandLineTags)) {
    //     // ... logic to build effectiveTagExpression from commandLineTags (as shown above) ...
    //   }
    // }

    return new PdslTestParameter.Builder(parseTreeListenerSupplier, lexerClass, parserClass)
        .withTagExpression(effectiveTagExpression)
        .withIncludedResources(includedResources)
        .build();
  }
}

