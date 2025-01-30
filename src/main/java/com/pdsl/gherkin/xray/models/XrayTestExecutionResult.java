package com.pdsl.gherkin.xray.models;

import java.util.List;

public class XrayTestExecutionResult {

  private final Info info;
  private final List<Test> tests;

  public XrayTestExecutionResult(Info info, List<Test> tests) {
    this.info = info;
    this.tests = tests;
  }

  public Info getInfo() {
    return info;
  }

  public List<Test> getTests() {
    return tests;
  }


}