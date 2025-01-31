package com.pdsl.gherkin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdsl.gherkin.xray.models.XrayTestExecutionResult;
import java.io.File;
import java.io.IOException;


public class XrayReportReader {

  public static XrayTestExecutionResult readXrayReport(String reportFilePath) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(new File(reportFilePath), XrayTestExecutionResult.class);
  }
}