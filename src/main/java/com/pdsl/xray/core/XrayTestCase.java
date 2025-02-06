package com.pdsl.xray.core;

import com.pdsl.testcases.TestCase;
import com.pdsl.specifications.FilteredPhrase;
import com.pdsl.testcases.TestSection;
import java.net.URI;
import java.util.Iterator;
import java.util.List;

public record XrayTestCase(URI originalSource, List<String> unfilteredPhraseBody,
                           List<String> contextFilteredPhraseBody, String testTitle,
                           List<FilteredPhrase> filteredPhrases) implements TestCase {

  @Override
  public Iterator<TestSection> getContextFilteredTestSectionIterator() {
    return null;
  }

  public String getTestResult() {
    return contextFilteredPhraseBody.isEmpty() ? "PASS" : "FAIL";
  }

  public Throwable getFailureException() {
    return contextFilteredPhraseBody.isEmpty() ? null : new Throwable("Test Case Failed");
  }
}