package com.pdsl.gherkin;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class OAuthClient {

  private final String tokenEndpoint;
  private final String clientId;
  private final String clientSecret;
  private String accessToken; // Store the token


  public OAuthClient(String tokenEndpoint, String clientId, String clientSecret) {
    this.tokenEndpoint = tokenEndpoint;
    this.clientId = clientId;
    this.clientSecret = clientSecret;
  }

  public void fetchAccessToken() throws IOException, InterruptedException {
    String credentials = clientId + ":" + clientSecret;
    String encodedCredentials = Base64.getEncoder()
        .encodeToString(credentials.getBytes(StandardCharsets.UTF_8));

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(tokenEndpoint))
        .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
        .header("Authorization", "Basic " + encodedCredentials)
        .header("Content-Type", "application/x-www-form-urlencoded")
        .build();

    HttpResponse<String> response = client.send(request,
        HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

    if (response.statusCode() != 200) {
      throw new IOException(
          "Unexpected code " + response.statusCode() + " Response body: " + response.body());
    }

    String responseBody = response.body();
    this.accessToken = responseBody;

  }


  public String getAccessToken() {
    return this.accessToken;
  }

  private static String getClientId() {
    PropertiesApplication properties = PropertiesApplication.sharedInstance();
    return properties.getProperty("OAUTH_CLIENT_ID");
  }

  private static String getClientSecret() {
    PropertiesApplication properties = PropertiesApplication.sharedInstance();
    return properties.getProperty("OAUTH_CLIENT_SECRET");
  }

  public static void main(String[] args) throws IOException, InterruptedException {

    // Example usage (using environment variables - recommended):

    // String tokenEndpoint = System.getenv("OAUTH_TOKEN_ENDPOINT");
    // String clientId = System.getenv("OAUTH_CLIENT_ID");
    // String clientSecret = System.getenv("OAUTH_CLIENT_SECRET");
    String tokenEndpoint = "";
    String clientId = "";
    String clientSecret = "";
    if (tokenEndpoint == null || clientId == null || clientSecret == null) {
      System.err.println(
          "Please set OAUTH_TOKEN_ENDPOINT, OAUTH_CLIENT_ID, and OAUTH_CLIENT_SECRET environment variables.");
      return;
    }

    OAuthClient oauthClient = new OAuthClient(tokenEndpoint, clientId, clientSecret);
    oauthClient.fetchAccessToken(); // Fetch the token

    String token = oauthClient.getAccessToken();
    System.out.println("Access Token: " + token);

    // Example of using the token in a different class
    MyApiClient apiClient = new MyApiClient(token);
    apiClient.makeApiCall();
  }
}

class MyApiClient {

  private final String accessToken;

  public MyApiClient(String accessToken) {
    this.accessToken = accessToken;
  }

  public void makeApiCall() {
    // Use the access token to make API calls
    System.out.println("Making API call with token: " + accessToken);
    // ... (Your API call logic here, including setting the Authorization header)
  }
}
