package com.pdsl.uat.java.jupiter;

import com.pdsl.executors.ExecutorObserver;
import com.pdsl.executors.InterpreterObj;
import com.pdsl.gherkin.GherkinObserver;
import com.pdsl.grammars.MultiInterpreterLexer;
import com.pdsl.grammars.MultiInterpreterParser;
import com.pdsl.grammars.MultiInterpreterParserBaseListener;
import com.pdsl.grammars.MultiInterpreterRecognizerLexer;
import com.pdsl.grammars.MultiInterpreterRecognizerParser;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.specifications.Phrase;
import com.pdsl.testcases.SharedTestCase;
import com.pdsl.testcases.TestCase;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.engine.descriptor.Interpreter;
import org.junit.jupiter.engine.descriptor.PdslConfigParameter;
import org.junit.jupiter.engine.descriptor.PdslExecutable;
import org.junit.jupiter.engine.descriptor.PdslSharedInvocationContextProvider;
import org.junit.jupiter.engine.descriptor.PdslTestParameter;

public class CustomObserverExecutorTest {


  @TestTemplate
  @ExtendWith(PdslExtension.class)
  public void pdslGherkinTestFrameworkResources(PdslExecutable executable) {

    executable.execute();
  }

  private static class PdslExtension extends PdslSharedInvocationContextProvider implements
      AfterTestExecutionCallback {

    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(
        ExtensionContext context) {
      return getInvocationContext(
          PdslConfigParameter.createGherkinPdslConfig(
                  List.of(
                      new PdslTestParameter.Builder(
                          List.of(
                              new Interpreter(MultiInterpreterLexer.class, MultiInterpreterParser.class,
                                  new InterpreterObj(new MultiInterpreterParserBaseListener(),
                                      "parserOneAllRules"))
                          ))
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

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
      String uniqueId = extensionContext.getUniqueId();
      Namespace namespace = Namespace.create(uniqueId);
      PdslExecutable executable = (PdslExecutable) extensionContext.getStore(namespace)
          .get(namespace);
      String testTitle = executable.getTestTitle();

    }
  }

  private static class MyObserver implements ExecutorObserver, GherkinObserver {

    int beforePhrase = 0;
    int afterPhrase = 0;
    int failureCount = 0;
    int beforeTestSuite = 0;
    int afterTestSuite = 0;
    Set<String> tags = new HashSet<>();

    @Override
    public void onBeforePhrase(ParseTreeListener listener, ParseTreeWalker walker,
        Phrase activePhrase) {
      beforePhrase++;
    }

    @Override
    public void onBeforePhrase(ParseTreeVisitor<?> visitor, Phrase activePhrase) {
      beforePhrase++;
    }

    @Override
    public void onAfterPhrase(ParseTreeListener listener, ParseTreeWalker walker,
        Phrase activePhrase) {
      afterPhrase++;
    }

    @Override
    public void onAfterPhrase(ParseTreeVisitor<?> visitor, Phrase activePhrase) {
      afterPhrase++;
    }

    @Override
    public void onPhraseFailure(ParseTreeListener listener, Phrase activePhrase, TestCase testCase,
        Throwable exception) {
      failureCount++;
    }

    @Override
    public void onPhraseFailure(ParseTreeVisitor<?> visitor, Phrase activePhrase, TestCase testCase,
        Throwable exception) {
      failureCount++;
    }

    @Override
    public void onBeforeTestSuite(Collection<? extends TestCase> testCases, ParseTreeVisitor<?> visitor,
        String context) {
      beforeTestSuite++;
    }

    @Override
    public void onAfterTestSuite(Collection<? extends TestCase> testCases, ParseTreeVisitor<?> visitor,
        MetadataTestRunResults results,
        String context) {
      afterTestSuite++;
    }

    @Override
    public void onBeforeTestSuite(Collection<? extends TestCase> testCases, ParseTreeListener listener,
        String context) {
      beforeTestSuite++;
    }

    @Override
    public void onBeforeTestSuite(Collection<? extends TestCase> testCases, String context) {

    }

    @Override
    public void onAfterTestSuite(Collection<? extends TestCase> testCases, ParseTreeListener listener,
        MetadataTestRunResults results,
        String context) {
      afterTestSuite++;
    }

    @Override
    public void onAfterTestSuite(Collection<? extends TestCase> testCases,
        MetadataTestRunResults results, String context) {

    }

    @Override
    public void onScenarioConverted(String title, List<String> steps, Set<String> tags,
        Map<String, String> substitutions) {
      this.tags = tags;
    }


  }
}
