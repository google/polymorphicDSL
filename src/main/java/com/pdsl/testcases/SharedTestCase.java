package com.pdsl.testcases;

import com.google.common.base.Preconditions;
import com.pdsl.executors.InterpreterObj;
import com.pdsl.specifications.FilteredPhrase;
import java.util.*;
import java.util.stream.Collectors;

/**
 * TODO
 * the collections of the input parameter should to have the same capacity - ADD VALIDATION
 *
 *
 *
 */
public final class SharedTestCase {

 private final List<TestCase> testCases;// = new ArrayList<>();
 private final List<InterpreterObj> interpreterObjs;// = new ArrayList<>();
 // List<Interpreter> interpreter,

  private final List<Iterator<FilteredPhrase>> iterators;

  public SharedTestCase(List<TestCase> testCases, List<InterpreterObj> interpreterObjs) {
    int countFirst = testCases.get(0).getUnfilteredPhraseBody().size();
    Preconditions.checkArgument(testCases.stream().allMatch(tc -> tc.getUnfilteredPhraseBody().size() == countFirst), "The size should be the same for all " + countFirst);
    this.testCases = testCases;
    this.interpreterObjs = interpreterObjs;
    this.iterators = this.testCases.stream().map(TestCase::getFilteredPhrases).map(v-> v.iterator()).collect(Collectors.toUnmodifiableList());
  }

  public List<TestCase> getTestCases(){return testCases;}

  public List<InterpreterObj> getInterpreters(){return interpreterObjs;}

  public List<Iterator<FilteredPhrase>> getSharedParseIterator() { return iterators;}

  // // public Optional<ParseTree> getNextParseTree() {
  // //   List<Pair<Optional<ParseTree>, Interpreter> optParseTree= new ArrayList<>();
  // //
  // //   for(TestCase testCase : testCases) {
  // //
  // //     optParseTree.add(testCase.getFilteredPhrases().get(0).getParseTree());
  // //
  // //
  // //   }//for
  //
  //
  // }


}
