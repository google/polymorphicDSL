package com.pdsl.testcases;

import com.google.common.base.Preconditions;
import com.pdsl.executors.InterpreterObj;
import com.pdsl.specifications.FilteredPhrase;
import com.pdsl.testcases.SharedTestSuite.SharedTestCaseWithInterpreter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The container for the {@link com.pdsl.testcases.TestCase}
 * and associated (Lexer/Parser; Listener/Visitor) with them.
 * {@link com.pdsl.executors.InterpreterObj}
 */
public final class SharedTestCase {

  List<SharedTestCaseWithInterpreter> sharedTestCaseWithInterpreters;

 private final List<TestCase> testCases;
 private final List<InterpreterObj> interpreterObjs;

  private final List<Iterator<FilteredPhrase>> iterators;

  public SharedTestCase(List<TestCase> testCases, List<InterpreterObj> interpreterObjs) {
    /**
     *  Take the first one (same) phrase in different Listener/Visitor (InterpreterObj).
     */
    int countFirst = testCases.get(0).getUnfilteredPhraseBody().size();
    Preconditions.checkArgument(testCases.stream().allMatch(tc -> tc.getUnfilteredPhraseBody().size() == countFirst), "The size should be the same for all " + countFirst);
    this.testCases = testCases;
    this.interpreterObjs = interpreterObjs;
    this.iterators = this.testCases.stream().map(TestCase::getFilteredPhrases).map(v-> v.iterator()).collect(Collectors.toUnmodifiableList());
  }

  public SharedTestCase(List<SharedTestCaseWithInterpreter> sharedTestCaseWithInterpreters) {
    this.sharedTestCaseWithInterpreters = sharedTestCaseWithInterpreters;
    this.testCases=null;
    this.interpreterObjs=null;
    this.iterators = null;
  }

  //public List<TestCase> getTestCases(){return testCases;}

  //public List<InterpreterObj> getInterpreters(){return interpreterObjs;}

  //public List<Iterator<FilteredPhrase>> getSharedParseIterator() { return iterators;}

  public List<SharedTestCaseWithInterpreter> getSharedTestCaseWithInterpreters() {return sharedTestCaseWithInterpreters;}

}
