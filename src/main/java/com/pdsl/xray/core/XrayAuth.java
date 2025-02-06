package com.pdsl.xray.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;
import java.util.logging.Logger;

public class XrayAuth {

  private static final HttpClient client = HttpClient.newHttpClient();
  Logger logger = Logger.getLogger(this.getClass().getName());
  private static final Properties properties = new Properties();
  private final String xrayUrl;
  private String authToken;
  private long tokenExpirationTime;
  private final String clientId;
  private final String clientSecret;

  public XrayAuth(String xrayUrl, String clientId, String clientSecret) {
    this.xrayUrl = xrayUrl;
    this.clientId = clientId;
    this.clientSecret = clientSecret;
  }

  public String getAuthToken() {
    if (authToken == null || (System.currentTimeMillis() >= tokenExpirationTime
        && tokenExpirationTime != 0)) {
      fetchAuthToken();
    }
    return authToken;
  }

  private void fetchAuthToken() {
    try {
      // Create a JSON object for the request body
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode requestBody = objectMapper.createObjectNode()
          .put("client_id", this.clientId)
          .put("client_secret", this.clientSecret);

      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(this.xrayUrl))
          .header("Content-Type", "application/json")
          .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(requestBody)))
          .build();

      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      String responseBody = response.body().replaceAll("\"", "");

      if (response.statusCode() >= 200 && response.statusCode() < 300) {
        this.authToken = responseBody;
      } else {
        throw new IllegalStateException(
            "Failed to fetch Xray auth token: %d - %s".formatted(response.statusCode(),
                responseBody));
      }
    } catch (IOException | InterruptedException e) {
      throw new IllegalStateException(
          "Error fetching Xray auth token: %s".formatted(e.getMessage()), e);
    }
  }

  private static void loadXrayProperties(String propertiesFilePath) {
    try {
      properties.load(new FileInputStream(propertiesFilePath));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static XrayAuth fromPropertiesFile(String propertiesFilePath) {

    loadXrayProperties(propertiesFilePath);
    String xrayUrl = properties.getProperty("xray.api.url");
    String clientId = properties.getProperty("xray.client.id");
    String clientSecret = properties.getProperty("xray.client.secret");
    if (clientId == null || clientSecret == null || xrayUrl == null) {
      throw new RuntimeException(
          "xray.client.id, xray.client.secret and xray.api.url must be defined in the properties file.");
    }
    return new XrayAuth(xrayUrl, clientId, clientSecret);

  }
}