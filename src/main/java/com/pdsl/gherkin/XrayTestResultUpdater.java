package com.pdsl.gherkin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdsl.executors.ExecutorObserver;
import com.pdsl.gherkin.xray.models.Info;
import com.pdsl.gherkin.xray.models.XrayTestResult;
import com.pdsl.gherkin.xray.models.XrayTestExecutionResult;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.specifications.Phrase;
import com.pdsl.testcases.DefaultTaggedTestCase;
import com.pdsl.testcases.TaggedTestCase;
import com.pdsl.testcases.TestCase;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * TODO: add code to support multiple plans, environments and platforms
 */
public class XrayTestResultUpdater implements GherkinObserver, ExecutorObserver {
  private final List<TestCase> testCases = new ArrayList<>();
  private final XrayAuth xrayAuth;
  private final ObjectMapper objectMapper; // Jackson ObjectMapper for JSON serialization

  public XrayTestResultUpdater(XrayAuth xrayAuth) {
    this.xrayAuth = xrayAuth;
    this.objectMapper = new ObjectMapper();
  }

  @Override
  public void onScenarioConverted(String title, List<String> steps, Set<String> tags,
      Map<String, String> substitutions) {
    String xrayTestKey = extractXrayTestKey(tags, "@xray-test-case");
    if (xrayTestKey != null) {
      System.out.println("Found Xray Test Key: " + xrayTestKey + " for scenario: " + title);
    } else {
      System.out.println("No Xray Test Key found for scenario: " + title);
    }
  }

  @Override
  public void onAfterTestSuite(Collection<? extends TestCase> testCases,
      org.antlr.v4.runtime.tree.ParseTreeVisitor<?> visitor, MetadataTestRunResults results, String context) {
    this.testCases.addAll(testCases);
  }

  public void publishReportsToXray() {

    List<XrayTestResult> tests = new ArrayList<>();
    for (TestCase testCase : testCases) {
      String xrayTestKey = extractXrayTestKey(testCase,"@xray-test-case=");
      String testStatus = extractTestStatus(testCase);

      if (xrayTestKey != null) {
        tests.add(new XrayTestResult(xrayTestKey, testStatus));
      }
    }

    XrayTestExecutionResult xrayResult = new XrayTestExecutionResult(
        new Info(
            "PDSL Automated Test Execution",
            "Results from automated tests PDSL",
            "GFENG-44108",
            Arrays.asList("PRD"),
            "janaarthanan"
        ),
        tests
    );

    try {
      String requestBody = objectMapper.writeValueAsString(xrayResult);

      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(getXrayReportUrl()))
          .header("Authorization", "Bearer " + xrayAuth.getAuthToken())
          .header("Content-Type", "application/json")
          .header("Accept", "*/*")
          .POST(HttpRequest.BodyPublishers.ofString(requestBody))
          .build();

      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

      if (response.statusCode() >= 200 && response.statusCode() < 300) {
        System.out.println("Xray test execution results imported successfully\n"+ response.body());
      } else {
        System.err.println(
            "Failed to import Xray test execution results: " + response.statusCode() + " - "
                + response.body());
      }
    } catch (IOException | InterruptedException e) {
      System.err.println("Error importing Xray test execution results: " + e.getMessage());
    }finally{
      testCases.clear();
    }
  }


  private String getXrayReportUrl() {
    Properties properties = new Properties();
    try (InputStream input = XrayAuth.class.getClassLoader()
        .getResourceAsStream("xray.properties")) {
      if (input == null) {
        throw new RuntimeException("Unable to find properties file: xray.properties");
      }
      properties.load(input);
      String apiUrl = properties.getProperty("xray.api.report.url");
      if (apiUrl == null) {
        throw new RuntimeException("xray.api.url must be defined in the properties file.");
      }
      return apiUrl;
    } catch (IOException ex) {
      throw new RuntimeException("Error loading properties file: " + ex.getMessage(), ex);
    }
  }

  private String extractXrayTestKey(TestCase testCase, String tagName) {
    if (testCase instanceof TaggedTestCase taggedTestCase) {
      return extractXrayTestKey(taggedTestCase.getTags(),tagName);
    }
    return null;
  }

  private String extractXrayTestKey(Collection<String> tags, String tagName) {
    Optional<String> xrayKey = tags.stream()
        .filter(tag -> tag.startsWith(tagName))
        .map(tag -> tag.substring(tagName.length()))
        .findFirst();
    return xrayKey.orElse(null);
  }

  private String extractTestStatus(TestCase testCase) {

    if (testCase instanceof XrayTestCase) {
      return ((XrayTestCase) testCase).getTestResult();
    } else {
      //TODO: decide default
      return "PASSED";
    }
  }

  private Throwable extractFailureReason(TestCase testCase) {
    if (testCase instanceof XrayTestCase) {
      return ((XrayTestCase) testCase).getFailureException();
    } else {
      return null;
    }
  }


  @Override
  public void onBeforePhrase(ParseTreeListener listener, ParseTreeWalker walker,
      Phrase activePhrase) {

  }

  @Override
  public void onBeforePhrase(ParseTreeVisitor<?> visitor, Phrase activePhrase) {

  }

  @Override
  public void onAfterPhrase(ParseTreeListener listener, ParseTreeWalker walker,
      Phrase activePhrase) {

  }

  @Override
  public void onAfterPhrase(ParseTreeVisitor<?> visitor, Phrase activePhrase) {

  }

  @Override
  public void onPhraseFailure(ParseTreeListener listener, Phrase activePhrase,TestCase testCase,
      Throwable exception) {

  }

  @Override
  public void onPhraseFailure(ParseTreeVisitor<?> visitor, Phrase activePhrase,TestCase testCase,
      Throwable exception) {

  }




  @Override
  public void onBeforeTestSuite(Collection<? extends TestCase> testCases,
      ParseTreeListener listener, String context) {

  }

  @Override
  public void onBeforeTestSuite(Collection<? extends TestCase> testCases, String context) {

  }


  @Override
  public void onAfterTestSuite(Collection<? extends TestCase> testCases, ParseTreeListener listener,
      MetadataTestRunResults results, String context) {
    this.testCases.addAll(testCases);
  }

  @Override
  public void onAfterTestSuite(Collection<? extends TestCase> testCases,
      MetadataTestRunResults results, String context) {
    this.testCases.addAll(testCases);
  }

  @Override
  public void onBeforeTestSuite(Collection<? extends TestCase> testCases, ParseTreeVisitor<?> visitor,
      String context) {

  }

}