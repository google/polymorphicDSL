package com.pdsl.uat.java.jupiter;

import com.pdsl.executors.InterpreterObj;
import com.pdsl.grammars.AllGrammarsLexer;
import com.pdsl.grammars.AllGrammarsParser;
import com.pdsl.grammars.AllGrammarsParserBaseListener;
import com.pdsl.grammars.AlphaLexer;
import com.pdsl.grammars.AlphaParser;
import com.pdsl.grammars.AlphaParserVisitor;
import com.pdsl.grammars.BetaLexer;
import com.pdsl.grammars.BetaParser;
import com.pdsl.grammars.MultiInterpreter2Lexer;
import com.pdsl.grammars.MultiInterpreter2Parser;
import com.pdsl.grammars.MultiInterpreter2Parser.ExecuteSentenceContext;
import com.pdsl.grammars.MultiInterpreter2Parser.ParserTwoAllRulesContext;
import com.pdsl.grammars.MultiInterpreter2ParserVisitor;
import com.pdsl.grammars.MultiInterpreterLexer;
import com.pdsl.grammars.MultiInterpreterParser;
import com.pdsl.grammars.MultiInterpreterParser.ExecutedByAllParsersContext;
import com.pdsl.grammars.MultiInterpreterParser.FirstInterpreterContext;
import com.pdsl.grammars.MultiInterpreterParser.IgnoreInterpreterContext;
import com.pdsl.grammars.MultiInterpreterParser.ParseSentenceContext;
import com.pdsl.grammars.MultiInterpreterParser.ParsedByAllInterpretersContext;
import com.pdsl.grammars.MultiInterpreterParser.ParserOneAllRulesContext;
import com.pdsl.grammars.MultiInterpreterParser.RecognizeInterpreterContext;
import com.pdsl.grammars.MultiInterpreterParserVisitor;
import com.pdsl.grammars.MultiInterpreterRecognizerLexer;
import com.pdsl.grammars.MultiInterpreterRecognizerParser;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.engine.descriptor.Interpreter;
import org.junit.jupiter.engine.descriptor.PdslConfigParameter;
import org.junit.jupiter.engine.descriptor.PdslExecutable;
import org.junit.jupiter.engine.descriptor.PdslGherkinInvocationContextProvider;
import org.junit.jupiter.engine.descriptor.PdslSharedInvocationContextProvider;
import org.junit.jupiter.engine.descriptor.PdslTestParameter;

public class Junit5MultiInterpreterTest {

  private int totalRunTests = 0;

  private static final Context context = new Context();

  // @RegisterExtension private final PdslExtension pdslExtension = new PdslExtension(context);
  @TestTemplate
  @ExtendWith(PdslExtension.class)
  public void pdslGherkinTestFrameworkResources(PdslExecutable executable) {

    executable.execute();
    totalRunTests++;
    Assertions.assertEquals(2, context.sharedSentenceCalls);
    Assertions.assertTrue(context.independentSentenceCalls);
  }

  private static final Supplier<ParseTreeListener> parseTreeListenerSupplier = () -> new AllGrammarsParserBaseListener();


  private static class PdslExtension extends PdslSharedInvocationContextProvider {

    private final ParseTreeVisitor<Void> parserOneVisitor;
    private final ParseTreeVisitor<Void> parserTwoVisitor;

    PdslExtension() {
      Context context = Junit5MultiInterpreterTest.context;
      this.parserOneVisitor = new ParserOneVisitor(context);
      this.parserTwoVisitor = new ParserTwoVisitor(context);
    }

    PdslExtension(Context context) {
      this.parserOneVisitor = new ParserOneVisitor(context);
      this.parserTwoVisitor = new ParserTwoVisitor(context);
    }

    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(
        ExtensionContext context) {
      return getInvocationContext(
          PdslConfigParameter.createGherkinPdslConfig(
                  List.of(
                      new PdslTestParameter.Builder(
                          List.of(
                              new Interpreter(MultiInterpreterLexer.class, MultiInterpreterParser.class,
                                  new InterpreterObj(parserOneVisitor, "parserOneAllRules")),
                              new Interpreter(MultiInterpreter2Lexer.class,
                                  MultiInterpreter2Parser.class,
                                  new InterpreterObj(parserTwoVisitor, "parserTwoAllRules")))
                      )
                          .withIncludedResources(new String[]{"MultiInterpreter.feature"})
                          .withStartRule("parserOneAllRules")
                          .build()

                  ))

              .withDslRecognizerParser(MultiInterpreterRecognizerParser.class)
              .withDslRecognizerLexer(MultiInterpreterRecognizerLexer.class)
              .withRecognizerRule("polymorphicDslAllRules")
              .withResourceRoot(
                  Paths.get("src/test/resources/framework_specifications/features/interpreter/")
                      .toUri())
              // .withRecognizerRule("polymorphicDslAllRules")
              .build())
          .stream();
    }
  }

  private static class Context {

    int sharedSentenceCalls = 0;
    boolean independentSentenceCalls = false;

  }


  private static class ParserOneVisitor extends AbstractParseTreeVisitor<Void> implements
      MultiInterpreterParserVisitor<Void> {

    private final Context context;

    public ParserOneVisitor(Context context) {
      this.context = context;
    }

    @Override
    public Void visitFirstInterpreter(FirstInterpreterContext ctx) {
      return null;
    }

    @Override
    public Void visitRecognizeInterpreter(RecognizeInterpreterContext ctx) {
      return null;
    }

    @Override
    public Void visitParseSentence(ParseSentenceContext ctx) {
      return null;
    }

    @Override
    public Void visitIgnoreInterpreter(IgnoreInterpreterContext ctx) {
      return null;
    }

    @Override
    public Void visitParsedByAllInterpreters(ParsedByAllInterpretersContext ctx) {
      this.context.sharedSentenceCalls++;
      return null;
    }

    @Override
    public Void visitExecutedByAllParsers(ExecutedByAllParsersContext ctx) {
      visitChildren(ctx);
      return null;
    }

    @Override
    public Void visitParserOneAllRules(ParserOneAllRulesContext ctx) {
      visitChildren(ctx);
      return null;
    }
  }

  private static class ParserTwoVisitor extends AbstractParseTreeVisitor<Void> implements
      MultiInterpreter2ParserVisitor<Void> {

    private final Context context;

    public ParserTwoVisitor(Context context) {
      this.context = context;
    }

    @Override
    public Void visitExecuteSentence(ExecuteSentenceContext ctx) {
      this.context.independentSentenceCalls = true;
      return null;
    }

    @Override
    public Void visitParsedByAllInterpreters(
        MultiInterpreter2Parser.ParsedByAllInterpretersContext ctx) {
      this.context.sharedSentenceCalls++;
      return null;
    }

    @Override
    public Void visitParserTwoAllRules(ParserTwoAllRulesContext ctx) {
      visitChildren(ctx);
      return null;
    }
  }
}
