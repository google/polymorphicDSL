package com.pdsl.xray.models;

import java.util.List;

public class Info {
  private final String summary;
  private final String description;
  private final String testPlanKey;
  private final List<String> testEnvironments;
  private final String user;

  public Info(String summary, String description, String testPlanKey, List<String> testEnvironments, String user) {
    this.summary = summary;
    this.description = description;
    this.testPlanKey = testPlanKey;
    this.testEnvironments = testEnvironments;
    this.user = user;
  }

  public String getSummary() {
    return summary;
  }

  public String getDescription() {
    return description;
  }

  public String getTestPlanKey() {
    return testPlanKey;
  }

  public List<String> getTestEnvironments() {
    return testEnvironments;
  }

  public String getUser() {
    return user;
  }
}
