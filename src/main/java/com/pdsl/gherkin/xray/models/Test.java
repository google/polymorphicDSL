package com.pdsl.gherkin.xray.models;

public class Test {

  private final String testKey;
  private final String status;

  public Test(String testKey, String status) {
    this.testKey = testKey;
    this.status = status;
  }

  public String getTestKey() {
    return testKey;
  }

  public String getStatus() {
    return status;
  }
}