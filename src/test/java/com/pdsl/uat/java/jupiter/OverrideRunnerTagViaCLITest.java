package com.pdsl.uat.java.jupiter;

import com.pdsl.executors.InterpreterObj;
import com.pdsl.grammars.AllGrammarsLexer;
import com.pdsl.grammars.AllGrammarsParser;
import com.pdsl.grammars.AllGrammarsParserBaseVisitor;
import com.pdsl.grammars.InterpreterAllLexer;
import com.pdsl.grammars.InterpreterAllParser;
import com.pdsl.grammars.MultiInterpreter2Parser;
import com.pdsl.grammars.MultiInterpreter2Parser.ExecuteSentenceContext;
import com.pdsl.grammars.MultiInterpreter2Parser.ParserTwoAllRulesContext;
import com.pdsl.grammars.MultiInterpreter2ParserVisitor;
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
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.engine.descriptor.Interpreter;
import org.junit.jupiter.engine.descriptor.PdslConfigParameter;
import org.junit.jupiter.engine.descriptor.PdslExecutable;
import org.junit.jupiter.engine.descriptor.PdslSharedInvocationContextProvider;
import org.junit.jupiter.engine.descriptor.PdslTestParameter;

public class OverrideRunnerTagViaCLITest {

  private static int totalRunTests = 0;

  @TestTemplate
  @ExtendWith(PdslExtension.class)
  public void pdslGherkinTestFrameworkResources(PdslExecutable executable) {
    totalRunTests++;
  }

  @AfterAll
  public static void executeAfterAll(){
    Assertions.assertEquals(1,totalRunTests);
  }

  private static class PdslExtension extends PdslSharedInvocationContextProvider {

    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(
        ExtensionContext context) {
      System.setProperty("tags", "@ex_tag1");
      Stream<TestTemplateInvocationContext>  contexts = getInvocationContext(
          PdslConfigParameter.createGherkinPdslConfig(
                  List.of(
                      new PdslTestParameter.Builder(
                          List.of(
                              new Interpreter(AllGrammarsLexer.class, AllGrammarsParser.class,
                                  new InterpreterObj(new AllGrammarsParserBaseVisitor<>(),
                                      "polymorphicDslAllRules"))
                          ))
                          .withIncludedResources(new String[]{"tags.feature"})
                          .withRecognizer(AllGrammarsLexer.class, AllGrammarsParser.class)
                          .withTagExpression("@feature_tag1")
                          .build()

                  ))
              .withRecognizerRule("polymorphicDslAllRules")
              .withResourceRoot(
                  Paths.get("src/test/resources/testdata/good/")
                      .toUri())
              .build())
          .stream();
      System.clearProperty("tags");
      return contexts;
    }
  }

}
