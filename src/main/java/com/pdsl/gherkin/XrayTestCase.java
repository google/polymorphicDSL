package com.pdsl.gherkin;

import com.pdsl.testcases.TestCase;
import com.pdsl.specifications.FilteredPhrase;
import com.pdsl.testcases.TestSection;
import java.net.URI;
import java.util.Iterator;
import java.util.List;

public class XrayTestCase implements TestCase {

  private final URI originalSource;
  private final List<String> unfilteredPhraseBody;
  private final List<String> contextFilteredPhraseBody;
  private final String testTitle;
  private final List<FilteredPhrase> filteredPhrases;

  public XrayTestCase(URI originalSource, List<String> unfilteredPhraseBody,
      List<String> contextFilteredPhraseBody, String testTitle,
      List<FilteredPhrase> filteredPhrases) {
    this.originalSource = originalSource;
    this.unfilteredPhraseBody = unfilteredPhraseBody;
    this.contextFilteredPhraseBody = contextFilteredPhraseBody;
    this.testTitle = testTitle;
    this.filteredPhrases = filteredPhrases;
  }

  @Override
  public URI getOriginalSource() {
    return originalSource;
  }

  @Override
  public List<String> getUnfilteredPhraseBody() {
    return unfilteredPhraseBody;
  }

  @Override
  public List<String> getContextFilteredPhraseBody() {
    return contextFilteredPhraseBody;
  }

  @Override
  public String getTestTitle() {
    return testTitle;
  }

  @Override
  public Iterator<TestSection> getContextFilteredTestSectionIterator() {
    return null;
  }

  @Override
  public List<FilteredPhrase> getFilteredPhrases() {
    return filteredPhrases;
  }

  public String getTestResult() {
    return contextFilteredPhraseBody.isEmpty() ? "PASS" : "FAIL";
  }

  public Throwable getFailureException() {
    return contextFilteredPhraseBody.isEmpty() ? null : new Throwable("Test Case Failed");
  }
}