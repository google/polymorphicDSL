package com.pdsl.uat.java.jupiter;

import com.pdsl.executors.InterpreterObj;
import com.pdsl.grammars.AllGrammarsLexer;
import com.pdsl.grammars.AllGrammarsParser;
import com.pdsl.grammars.AllGrammarsParserBaseVisitor;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
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

/**
 * This test class demonstrates how to override the tag expression set in the test
 * with tags provided via the command line using the system property "tags".
 */
public class OverrideRunnerTagViaCLITest {

  private static int totalRunTests = 0;

  /**
   * This test template uses the `PdslExtension` to configure and run the test.
   * The purpose of this test is to verify that the total number of tests run is 1.
   */
  @TestTemplate
  @ExtendWith(PdslExtension.class)
  public void pdslGherkinTestFrameworkResources(PdslExecutable executable) {
    totalRunTests++;
  }

  /**
   * This method asserts that only one test was run. This verifies that the
   * overridden tag expression from the command line took effect.
   */
  @AfterAll
  public static void executeAfterAll(){
    Assertions.assertEquals(1,totalRunTests);
  }

  private static class PdslExtension extends PdslSharedInvocationContextProvider {

    /**
     * This method provides the test template invocation contexts.
     * It sets the system property "tags" to "@ex_tag1" before creating the
     * PdslConfigParameter and then clears the property afterwards.
     *
     * @param context The ExtensionContext for the test.
     * @return A stream of TestTemplateInvocationContext objects.
     */
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
      // Clear command-line tags
      System.clearProperty("tags");
      return contexts;
    }
  }

}
