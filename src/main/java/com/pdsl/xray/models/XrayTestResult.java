package com.pdsl.xray.models;

public class XrayTestResult {

  private final String testKey;
  private final String status;

  public XrayTestResult(String testKey, String status) {
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