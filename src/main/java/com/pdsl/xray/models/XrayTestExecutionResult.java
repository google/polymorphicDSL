package com.pdsl.xray.models;

import java.util.List;

public class XrayTestExecutionResult {

  private final Info info;
  private final List<XrayTestResult> tests;

  public XrayTestExecutionResult(Info info, List<XrayTestResult> tests) {
    this.info = info;
    this.tests = tests;
  }

  public Info getInfo() {
    return info;
  }

  public List<XrayTestResult> getTests() {
    return tests;
  }


}