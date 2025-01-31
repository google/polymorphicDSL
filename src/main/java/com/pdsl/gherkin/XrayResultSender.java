package com.pdsl.gherkin;

import com.pdsl.gherkin.xray.models.XrayTestExecutionResult;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Properties;


public class XrayResultSender {

  private final XrayAuth xrayAuth;
  private final ObjectMapper objectMapper;

  public XrayResultSender(XrayAuth xrayAuth) {
    this.xrayAuth = xrayAuth;
    this.objectMapper = new ObjectMapper();
  }

  public void sendResultsToXray(XrayTestExecutionResult xrayResult) throws IOException, InterruptedException {
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
      System.err.println("Failed to import Xray test execution results: " + response.statusCode() + " - " + response.body());
    }
  }

  private String getXrayApiUrl(){
    Properties properties = new Properties();
    try (InputStream input = XrayAuth.class.getClassLoader().getResourceAsStream("xray.properties")) {
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
}