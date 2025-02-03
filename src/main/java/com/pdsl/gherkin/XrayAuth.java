package com.pdsl.gherkin;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XrayAuth {

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
    if (authToken == null || System.currentTimeMillis() >= tokenExpirationTime) {
      fetchAuthToken();
    }
    return authToken;
  }

  private void fetchAuthToken() {
    try {
      // Create a JSON object for the request body
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode requestBody = objectMapper.createObjectNode()
          .put("clientID", this.clientId)
          .put("clientSecret", this.clientSecret);

      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(this.xrayUrl))
          .header("Content-Type", "application/json")
          .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(requestBody)))
          .build();

      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

      if (response.statusCode() >= 200 && response.statusCode() < 300) {
        String responseBody = response.body();
        this.authToken = extractValueFromJson(responseBody, "token");
        String expiresInStr = extractValueFromJson(responseBody, "expiresIn");

        if (this.authToken == null) {
          throw new RuntimeException(
              "Could not extract access token from response: " + responseBody);
        }

        if (expiresInStr != null) {
          try {
            long expiresIn = Long.parseLong(expiresInStr);
            this.tokenExpirationTime =
                System.currentTimeMillis() + (expiresIn * 1000) - 60000; // 1 minute buffer
          } catch (NumberFormatException e) {
            logger.info("Could not parse expiresIn: " + expiresInStr);
            // TODO: Handle the error appropriately,set a default expiration or throw an exception
          }
        } else {
          logger.info("expiresIn not found in response.");
          // TODO: Handle the missing expiresIn, set a default expiration
        }

      } else {
        throw new RuntimeException(
            "Failed to fetch Xray auth token: " + response.statusCode() + " - " + response.body());
      }
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException("Error fetching Xray auth token: " + e.getMessage(), e);
    }
  }

  private String extractValueFromJson(String json, String key) {
    String regex = "\"" + key + "\":\"([^\"]+)\"";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(json);
    if (matcher.find()) {
      return matcher.group(1);
    }
    return null;
  }

  private static void loadXrayProperties() {
    try {
      properties.load(new FileInputStream("src/main/resources/xray.properties"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  public static XrayAuth fromPropertiesFile(String propertiesFilePath) {

    loadXrayProperties();
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