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
public final class SharedTestCase implements TestCase{

  List<SharedTestCaseWithInterpreter> sharedTestCaseWithInterpreters;

 private final List<TestCase> testCases;
 private final List<InterpreterObj> interpreterObjs;

  private final List<Iterator<FilteredPhrase>> iterators;

  public SharedTestCase(List<TestCase> testCases, List<InterpreterObj> interpreterObjs) {
    /**
     *  Take the first one (same) phrase in different Listener/Visitor (InterpreterObj).
     */
    int countFirst = testCases.get(0).unfilteredPhraseBody().size();
    Preconditions.checkArgument(testCases.stream().allMatch(tc -> tc.unfilteredPhraseBody().size() == countFirst), "The size should be the same for all " + countFirst);
    this.testCases = testCases;
    this.interpreterObjs = interpreterObjs;
    this.iterators = this.testCases.stream().map(TestCase::filteredPhrases).map(v-> v.iterator()).collect(Collectors.toUnmodifiableList());
  }

  public SharedTestCase(List<SharedTestCaseWithInterpreter> sharedTestCaseWithInterpreters) {
    this.sharedTestCaseWithInterpreters = sharedTestCaseWithInterpreters;
    this.testCases=null;
    this.interpreterObjs=null;
    this.iterators = null;
  }

  public List<SharedTestCaseWithInterpreter> getSharedTestCaseWithInterpreters() {return sharedTestCaseWithInterpreters;}


    @Override
    public URI originalSource() {
        return sharedTestCaseWithInterpreters.getFirst().getTestCase().originalSource();
    }

    @Override
    public List<String> unfilteredPhraseBody() {
        return sharedTestCaseWithInterpreters.getFirst().getTestCase().unfilteredPhraseBody();
    }

    @Override
    public List<String> contextFilteredPhraseBody() {
        return sharedTestCaseWithInterpreters.getFirst().getTestCase().contextFilteredPhraseBody();
    }

    @Override
    public String testTitle() {
        return sharedTestCaseWithInterpreters.getFirst().getTestCase().testTitle();
    }

    @Override
    public Iterator<TestSection> getContextFilteredTestSectionIterator() {
        return sharedTestCaseWithInterpreters.getFirst().getTestCase().getContextFilteredTestSectionIterator();
    }

    @Override
    public List<FilteredPhrase> filteredPhrases() {
        return sharedTestCaseWithInterpreters.getFirst().getTestCase().filteredPhrases();
    }
}
