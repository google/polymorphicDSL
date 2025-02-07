package com.pdsl.uat.java.jupiter;

import com.pdsl.executors.DefaultPolymorphicDslTestExecutor;
import com.pdsl.gherkin.DefaultGherkinTestSpecificationFactory;
import com.pdsl.gherkin.DefaultGherkinTestSpecificationFactory.QuadFunction;
import com.pdsl.gherkin.DefaultGherkinTestSpecificationFactoryGenerator;
import com.pdsl.gherkin.PdslGherkinListener;
import com.pdsl.gherkin.PdslGherkinRecognizer;
import com.pdsl.gherkin.PickleJarFactory;
import com.pdsl.grammars.AllGrammarsLexer;
import com.pdsl.grammars.AllGrammarsParser;
import com.pdsl.grammars.AllGrammarsParserBaseListener;
import com.pdsl.specifications.FilteredPhrase;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import com.pdsl.xray.core.XrayAuth;
import com.pdsl.xray.core.XrayTestResultUpdater;
import com.pdsl.xray.observers.GherkinObserver;
import com.pdsl.xray.observers.PickleJarScenarioObserver;
import com.pdsl.xray.observers.XrayExecutorObserver;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.engine.descriptor.PdslConfigParameter;
import org.junit.jupiter.engine.descriptor.PdslExecutable;
import org.junit.jupiter.engine.descriptor.PdslGherkinInvocationContextProvider;
import org.junit.jupiter.engine.descriptor.PdslTestParameter;

public class XrayIntegrationTest {

  private int totalRunTests = 0;
  private static final XrayAuth xrayAuth = XrayAuth.fromPropertiesFile(
      "src/main/resources/xray.properties");

  private static final XrayTestResultUpdater iosUpdater = new XrayTestResultUpdater(xrayAuth);
  private static final XrayTestResultUpdater androidUpdater = new XrayTestResultUpdater(xrayAuth);

  @TestTemplate
  @ExtendWith(IosExtension.class)
  public void iosTest(PdslExecutable executable) {

    executable.execute();
    totalRunTests++;
    assert (totalRunTests == 1);
  }

  @TestTemplate
  @ExtendWith(AndroidExtension.class)
  public void androidTest(PdslExecutable executable) {

    executable.execute();
    totalRunTests++;
    assert (totalRunTests == 1);
  }

  private static final Supplier<ParseTreeListener> parseTreeListenerSupplier = () -> new AllGrammarsParserBaseListener();

  private static class IosExtension extends PdslGherkinInvocationContextProvider {

    private static final DefaultPolymorphicDslTestExecutor traceableTestRunExecutor = new DefaultPolymorphicDslTestExecutor();
    private static final QuadFunction<PdslGherkinRecognizer, PdslGherkinListener, Charset,List<GherkinObserver>,PickleJarFactory> TRI_FUNCTION = PickleJarFactory::new;
    private static final PickleJarScenarioObserver PICKLE_JAR_SCENARIO_OBSERVER = new PickleJarScenarioObserver();
    private static final XrayExecutorObserver xrayExecutorObserver = new XrayExecutorObserver(iosUpdater);
    private static final PickleJarFactory PICKLE_JAR_FACTORY = PickleJarFactory.getDefaultPickleJarFactory();
    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(
        ExtensionContext context) {
      traceableTestRunExecutor.registerObserver(xrayExecutorObserver);
      System.out.println(xrayAuth.getAuthToken());
      PICKLE_JAR_FACTORY.registerObserver(PICKLE_JAR_SCENARIO_OBSERVER);
      return getInvocationContext(PdslConfigParameter.createGherkinPdslConfig(
              List.of(
                  new PdslTestParameter.Builder(parseTreeListenerSupplier,
                      AllGrammarsLexer.class, AllGrammarsParser.class)
                      .withTagExpression("@ios")
                      .withIncludedResources(new String[]{"xray_integration.feature"})
                      .build()
              )
          )
          .withApplicationName("Polymorphic DSL Framework")
          .withContext("User Acceptance Test")
          .withResourceRoot(Paths.get("src/test/resources/").toUri())
          .withRecognizerRule("polymorphicDslAllRules")
          .withTestRunExecutor(() -> traceableTestRunExecutor)
          .withTestSpecificationFactoryGenerator(()->{
            PolymorphicDslPhraseFilter filter = new MyCustomFilter();

            return new DefaultGherkinTestSpecificationFactoryGenerator(
                new DefaultGherkinTestSpecificationFactory.Builder((filter))
                .withPickleJarFactory(PICKLE_JAR_FACTORY));
          })
          .build())
          .stream();
    }
  }

  private static class AndroidExtension extends PdslGherkinInvocationContextProvider {

    private static final DefaultPolymorphicDslTestExecutor traceableTestRunExecutor = new DefaultPolymorphicDslTestExecutor();

    private static final XrayExecutorObserver xrayExecutorObserver = new XrayExecutorObserver(androidUpdater);

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(
        ExtensionContext context) {
      traceableTestRunExecutor.registerObserver(xrayExecutorObserver);
      System.out.println(xrayAuth.getAuthToken());
      return getInvocationContext(PdslConfigParameter.createGherkinPdslConfig(
              List.of(
                  new PdslTestParameter.Builder(parseTreeListenerSupplier,
                      AllGrammarsLexer.class, AllGrammarsParser.class)
                      .withTagExpression("@android")
                      .withIncludedResources(new String[]{"xray_integration.feature"})
                      .build()
              )
          )
          .withApplicationName("Polymorphic DSL Framework")
          .withContext("User Acceptance Test")
          .withResourceRoot(Paths.get("src/test/resources/").toUri())
          .withRecognizerRule("polymorphicDslAllRules")
          .withTestRunExecutor(() -> traceableTestRunExecutor)
          .build())
          .stream();
    }
  }

  @AfterAll
  public static void publishReportsToXray() {

    iosUpdater.publishReportsToXray();
    androidUpdater.publishReportsToXray();
  }

  public static class MyCustomFilter implements PolymorphicDslPhraseFilter {
    @Override
    public Optional<List<FilteredPhrase>> filterPhrases(List<InputStream> testInput) {
      return Optional.empty();
    }
  }
}



