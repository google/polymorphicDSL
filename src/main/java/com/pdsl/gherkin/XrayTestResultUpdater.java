package com.pdsl.gherkin;

import com.pdsl.executors.ExecutorObserver;
import com.pdsl.gherkin.xray.models.Info;
import com.pdsl.gherkin.xray.models.Test;
import com.pdsl.gherkin.xray.models.XrayTestExecutionResult;
import com.pdsl.specifications.Phrase;
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

  private final XrayAuth xrayAuth;
  private final ObjectMapper objectMapper; // Jackson ObjectMapper for JSON serialization

  public XrayTestResultUpdater(XrayAuth xrayAuth) {
    this.xrayAuth = xrayAuth;
    this.objectMapper = new ObjectMapper();
  }

  @Override
  public void onScenarioConverted(String title, List<String> steps, Set<String> tags,
      Map<String, String> substitutions) {
    String xrayTestKey = extractXrayTestKey(tags);
    if (xrayTestKey != null) {
      System.out.println("Found Xray Test Key: " + xrayTestKey + " for scenario: " + title);
    } else {
      System.out.println("No Xray Test Key found for scenario: " + title);
    }
  }

  @Override
  public void onAfterTestSuite(Collection<TestCase> testCases,
      org.antlr.v4.runtime.tree.ParseTreeVisitor<?> visitor, String context) {
    List<Test> tests = new ArrayList<>();
    for (TestCase testCase : testCases) {
      String xrayTestKey = extractXrayTestKey(testCase);
      String testStatus = extractTestStatus(testCase);

      if (xrayTestKey != null) {
        tests.add(new Test(xrayTestKey, testStatus));
      }
    }

    XrayTestExecutionResult xrayResult = new XrayTestExecutionResult(
        new Info(
            "Automated Test Execution", // Summary (from config)
            "Results from automated tests", // Description (from config)
            "YOUR_TEST_PLAN_KEY", // Test Plan Key (from config)
            Arrays.asList("YOUR_TEST_ENVIRONMENT"), // Test Environments (from config)
            "YOUR_USERNAME" // Username (from config)
        ),
        tests
    );

    try {
      String requestBody = objectMapper.writeValueAsString(xrayResult);

      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(getXrayApiUrl() + "/rest/raven/1.0/import/execution"))
          .header("Authorization", "Bearer " + xrayAuth.getAuthToken())
          .header("Content-Type", "application/json")
          .POST(HttpRequest.BodyPublishers.ofString(requestBody))
          .build();

      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

      if (response.statusCode() >= 200 && response.statusCode() < 300) {
        System.out.println("Xray test execution results imported successfully.");
      } else {
        System.err.println(
            "Failed to import Xray test execution results: " + response.statusCode() + " - "
                + response.body());
      }
    } catch (IOException | InterruptedException e) {
      System.err.println("Error importing Xray test execution results: " + e.getMessage());
    }
  }

  @Override
  public void onBeforeTestSuite(Collection<TestCase> testCases, ParseTreeListener listener,
      String context) {

  }

  @Override
  public void onAfterTestSuite(Collection<TestCase> testCases, ParseTreeListener listener,
      String context) {

  }

  private String getXrayApiUrl() {
    Properties properties = new Properties();
    try (InputStream input = XrayAuth.class.getClassLoader()
        .getResourceAsStream("xray.properties")) {
      if (input == null) {
        throw new RuntimeException("Unable to find properties file: xray.properties");
      }
      properties.load(input);
      String apiUrl = properties.getProperty("xray.api.url");
      if (apiUrl == null) {
        throw new RuntimeException("xray.api.url must be defined in the properties file.");
      }
      return apiUrl;
    } catch (IOException ex) {
      throw new RuntimeException("Error loading properties file: " + ex.getMessage(), ex);
    }
  }

  private String extractXrayTestKey(TestCase testCase) {
    if (testCase instanceof TaggedTestCase taggedTestCase) {
      return extractXrayTestKey(taggedTestCase.getTags());
    }
    return null;
  }

  private String extractXrayTestKey(Collection<String> tags) {
    Optional<String> xrayKey = tags.stream()
        .filter(tag -> tag.startsWith("@xray-key="))
        .map(tag -> tag.substring("@xray-key=".length()))
        .findFirst();
    return xrayKey.orElse(null);
  }

  private String extractTestStatus(TestCase testCase) {
    if (testCase instanceof XrayTestCase) {
      return ((XrayTestCase) testCase).getTestResult();
    } else {
      //TODO: decide default
      return "UNKNOWN";
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
  public void onPhraseFailure(ParseTreeListener listener, Phrase activePhrase,
      Throwable exception) {

  }

  @Override
  public void onPhraseFailure(ParseTreeVisitor<?> visitor, Phrase activePhrase,
      Throwable exception) {

  }

  @Override
  public void onBeforeTestSuite(Collection<TestCase> testCases, ParseTreeVisitor<?> visitor,
      String context) {

  }

}