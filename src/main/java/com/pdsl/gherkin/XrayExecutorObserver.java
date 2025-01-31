package com.pdsl.gherkin;

import static java.awt.SystemColor.info;

import com.pdsl.executors.ExecutorObserver;
import com.pdsl.gherkin.xray.models.Info;
import com.pdsl.gherkin.xray.models.XrayTestExecutionResult;
import com.pdsl.gherkin.xray.models.XrayTestResult;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.specifications.Phrase;
import com.pdsl.testcases.TaggedTestCase;
import com.pdsl.testcases.TestCase;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class XrayExecutorObserver implements ExecutorObserver {

  Properties prop = new Properties();
  private final XrayTestResultUpdater xrayUpdater;
  private Map<TestCase, List<XrayTestExecutionResult>> testCaseXrayTestExecutionResultMap = new HashMap<>();

  public XrayExecutorObserver(XrayTestResultUpdater xrayUpdater) {
    this.xrayUpdater = xrayUpdater;
  }

  @Override
  public void onBeforePhrase(ParseTreeListener listener, ParseTreeWalker walker,
      Phrase activePhrase) {
  }

  @Override
  public void onBeforePhrase(ParseTreeVisitor<?> visitor,
      Phrase activePhrase) {
  }

  @Override
  public void onAfterPhrase(ParseTreeListener listener, ParseTreeWalker walker,
      Phrase activePhrase) {
  }

  @Override
  public void onAfterPhrase(ParseTreeVisitor<?> visitor,
      Phrase activePhrase) {
  }

  @Override
  public void onPhraseFailure(ParseTreeListener listener,
      Phrase activePhrase, TestCase testCase, Throwable exception) {

  }

  @Override
  public void onPhraseFailure(ParseTreeVisitor<?> visitor,
      Phrase activePhrase, TestCase testCase, Throwable exception) {

    loadXrayProperties();
    String environmentsStr = prop.getProperty("environments");
    List<String> environments = Arrays.asList(environmentsStr.split(","));
    String user = prop.getProperty("user");

    if (testCase instanceof TaggedTestCase taggedTestCase) {
      Collection<String> tags = extractTags(taggedTestCase.getTags(),"@xray-test-plan=");
      Collection<String> caseTags = extractTags(taggedTestCase.getTags(),"@xray-test-case=");

      List<Info> listOfInfo = tags.stream().map(tag -> new Info(
          testCase.getTestTitle(),
          String.join("", taggedTestCase.getUnfilteredPhraseBody()),
          tag,
          environments,
          user
      )).collect(Collectors.toList());

      List<XrayTestResult> xrayTestResults = caseTags.stream().map(t -> new XrayTestResult(
          t, "FAILED"
      )).collect(Collectors.toList());

      for (Info info : listOfInfo) {
        List<XrayTestExecutionResult> results = testCaseXrayTestExecutionResultMap.computeIfAbsent(
            testCase, k -> new ArrayList<>());
        results.add(new XrayTestExecutionResult(info, xrayTestResults));
      }

    }
  }

  private static List<String> extractTags(Collection<String> tags, String prefix) {
    return tags.stream()
        .filter(tag -> tag.toLowerCase().startsWith(prefix.toLowerCase()))
        .map(tag -> {
          String[] split = tag.split("=");
          if (split.length == 2) {
            return Optional.of(split[1]);
          } else {
            return Optional.empty();
          }
        })
        .filter(Optional::isPresent)
        .map(Optional::get)
        .map(Object::toString)
        .collect(Collectors.toList());
  }

  @Override
  public void onBeforeTestSuite(Collection<TestCase> testCases, ParseTreeVisitor<?> visitor,
      String context) {
  }

  @Override
  public void onAfterTestSuite(Collection<TestCase> testCases, ParseTreeVisitor<?> visitor,
      MetadataTestRunResults results,
      String context) {
    xrayUpdater.onAfterTestSuite(testCases, visitor,results, context);
    //
  }

  @Override
  public void onBeforeTestSuite(Collection<TestCase> testCases, ParseTreeListener listener,
      String context) {
  }

  @Override
  public void onAfterTestSuite(Collection<TestCase> testCases, ParseTreeListener listener,
      MetadataTestRunResults results,
      String context) {
  }

  private void loadXrayProperties() {
    try {
      prop.load(new FileInputStream("xray.properties"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
