package com.pdsl.uat.java.jupiter;

import com.pdsl.executors.DefaultPolymorphicDslTestExecutor;
import com.pdsl.xray.core.XrayAuth;
import com.pdsl.xray.observers.XrayExecutorObserver;
import com.pdsl.xray.core.XrayTestResultUpdater;
import com.pdsl.grammars.AllGrammarsLexer;
import com.pdsl.grammars.AllGrammarsParser;
import com.pdsl.grammars.AllGrammarsParserBaseListener;
import java.nio.file.Paths;
import java.util.List;
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

public class XrayIntegrationTest2 {

  private static final String XRAY_PROPERTIES_FILE = "src/main/resources/xray.properties";
  private static final XrayAuth xrayAuth = XrayAuth.fromPropertiesFile(XRAY_PROPERTIES_FILE);
  private static final Supplier<ParseTreeListener> parseTreeListenerSupplier =
      AllGrammarsParserBaseListener::new;
  private static final String[] FEATURE_FILES = {
      "xray_integration.feature"};

  @TestTemplate
  @ExtendWith(IosExtension.class)
  public void iosTest(PdslExecutable executable) {
    executable.execute();
  }

  @TestTemplate
  @ExtendWith(AndroidExtension.class)
  public void androidTest(PdslExecutable executable) {
    executable.execute();
  }

  /**
   * Common Extension for any Platform
   */
  private static class PlatformExtension extends
      PdslGherkinInvocationContextProvider {

    protected final DefaultPolymorphicDslTestExecutor traceableTestRunExecutor =
        new DefaultPolymorphicDslTestExecutor();
    protected final XrayExecutorObserver xrayExecutorObserver;

    public PlatformExtension(XrayTestResultUpdater updater) {
      this.xrayExecutorObserver = new XrayExecutorObserver(updater);
    }

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(
        ExtensionContext context) {
      /** TODO: deal with tagExpression
       *
       */
      traceableTestRunExecutor.registerObserver(xrayExecutorObserver);
      System.out.println(xrayAuth.getAuthToken());

      return getInvocationContext(
          PdslConfigParameter.createGherkinPdslConfig(
                  List.of(
                      new PdslTestParameter.Builder(
                          parseTreeListenerSupplier, AllGrammarsLexer.class, AllGrammarsParser.class)
                          .withTagExpression("@ios") // Use the passed in tagExpression
                          .withIncludedResources(FEATURE_FILES) // Use constant array
                          .build()))
              .withApplicationName("Polymorphic DSL Framework")
              .withContext("User Acceptance Test")
              .withResourceRoot(Paths.get("src/test/resources/").toUri())
              .withRecognizerRule("polymorphicDslAllRules")
              .withTestRunExecutor(() -> traceableTestRunExecutor)
              .build())
          .stream();
    }
  }

  /**
   * Extension for IOS
   */
  private static class IosExtension extends PlatformExtension {

    private static final XrayTestResultUpdater iosUpdater = new XrayTestResultUpdater(xrayAuth);

    public IosExtension() {
      super(iosUpdater);
    }

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(
        ExtensionContext context) {
      return super.provideTestTemplateInvocationContexts(context); // Pass the tag
    }
  }

  /**
   * Extension for android
   */
  private static class AndroidExtension extends PlatformExtension {

    private static final XrayTestResultUpdater androidUpdater = new XrayTestResultUpdater(xrayAuth);

    public AndroidExtension() {
      super(androidUpdater);
    }

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(
        ExtensionContext context) {
      return super.provideTestTemplateInvocationContexts(context); // Pass the tag
    }
  }

  @AfterAll
  public static void publishReportsToXray() {
    // No need to instantiate the updaters again.
    IosExtension.iosUpdater.publishReportsToXray();  // Access static updater
    AndroidExtension.androidUpdater.publishReportsToXray(); // Access static updater

  }
}