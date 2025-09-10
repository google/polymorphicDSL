package com.pdsl.testcases;

import com.google.common.base.Preconditions;
import com.pdsl.executors.InterpreterObj;
import com.pdsl.specifications.FilteredPhrase;
import com.pdsl.testcases.SharedTestSuite.SharedTestCaseWithInterpreter;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The container for the {@link com.pdsl.testcases.TestCase}
 * and associated (Lexer/Parser; Listener/Visitor) with them.
 * {@link com.pdsl.executors.InterpreterObj}
 */
public final class SharedTestCase implements TestCase, TaggedTestCase {

  private final List<SharedTestCaseWithInterpreter> sharedTestCaseWithInterpreters;

  public SharedTestCase(List<SharedTestCaseWithInterpreter> sharedTestCaseWithInterpreters) {
    this.sharedTestCaseWithInterpreters = sharedTestCaseWithInterpreters;
  }

  public List<SharedTestCaseWithInterpreter> getSharedTestCaseWithInterpreters() {return sharedTestCaseWithInterpreters;}


    @Override
    public URI getOriginalSource() {
        return sharedTestCaseWithInterpreters.getFirst().getTestCase().getOriginalSource();
    }

    @Override
    public List<String> getUnfilteredPhraseBody() {
        return sharedTestCaseWithInterpreters.getFirst().getTestCase().getUnfilteredPhraseBody();
    }

    @Override
    public List<String> getContextFilteredPhraseBody() {
        return sharedTestCaseWithInterpreters.getFirst().getTestCase().getContextFilteredPhraseBody();
    }

    @Override
    public String getTestTitle() {
        return sharedTestCaseWithInterpreters.getFirst().getTestCase().getTestTitle();
    }

    @Override
    public Iterator<TestSection> getContextFilteredTestSectionIterator() {
        return sharedTestCaseWithInterpreters.getFirst().getTestCase().getContextFilteredTestSectionIterator();
    }

    @Override
    public List<FilteredPhrase> getFilteredPhrases() {
        return sharedTestCaseWithInterpreters.getFirst().getTestCase().getFilteredPhrases();
    }

    @Override
    public Map<String, Object> getMetadata() {
        return sharedTestCaseWithInterpreters.getFirst().getTestCase().getMetadata();
    }

    @Override
    public Collection<String> getTags() {
        if (sharedTestCaseWithInterpreters.getFirst().getTestCase() instanceof TaggedTestCase tagged) {
            return tagged.getTags();
        }
        return Set.of();
    }
}
