package com.pdsl.testcases;

import com.google.common.base.Preconditions;
import com.pdsl.executors.InterpreterObj;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SharedTestSuite {

  public List<SharedTestCase> getSharedTestCaseList() {
    return sharedTestCaseList;
  }

  private List<SharedTestCase> sharedTestCaseList;


  private SharedTestSuite(List<SharedTestCase> sharedTestCasesList) {
    this.sharedTestCaseList = sharedTestCasesList;
  }

  public static SharedTestSuite of(List<List<TestCase>> listOfTestCases,
      List<InterpreterObj> interpreterObj) {
    /* One test case per interpreter
     */
    int interpreterSize = interpreterObj.size();
    /*
    every interpreter should have its own test suite
     */
    Preconditions.checkArgument(listOfTestCases.size() == interpreterSize);
    /*
    every test suite should have the same number of test cases
    * */
    listOfTestCases.forEach(
        item -> Preconditions.checkArgument(item.size() == listOfTestCases.get(0).size()));
    List<SharedTestCase> sharedTestCaseList = new ArrayList<>();
    List<List<SharedTestCaseWithInterpreter>> listOfSharedTestCaseWithInterpreter = new ArrayList<>();
    for (TestCase testCase : listOfTestCases.get(0)) {
      listOfSharedTestCaseWithInterpreter.add(new ArrayList<>());
    }
    List<SharedTestCaseWithInterpreter> SharedTestCaseWithInterpreterList = new ArrayList<>();
    for (int testCase = 0; testCase < listOfTestCases.get(0).size(); testCase++) { //3
      for(int interpreterIndex =0; interpreterIndex<interpreterObj.size();interpreterIndex++) {
        SharedTestCaseWithInterpreter sharedTestCaseWithInterpreter = new SharedTestCaseWithInterpreter(
            listOfTestCases.get(interpreterIndex).get(testCase),interpreterObj.get(interpreterIndex));
        SharedTestCaseWithInterpreterList.add(sharedTestCaseWithInterpreter);
        listOfSharedTestCaseWithInterpreter.get(testCase).add(sharedTestCaseWithInterpreter);
      }
    }
    /*for (InterpreterObj interpreter : interpreterObj) {
      for (List<TestCase> interpreterTestSuite : listOfTestCases) {
        List<SharedTestCaseWithInterpreter> SharedTestCaseWithInterpreterList = new ArrayList<>();
        for (int testCase = 0; testCase < interpreterTestSuite.size(); testCase++) {

          SharedTestCaseWithInterpreter sharedTestCaseWithInterpreter = new SharedTestCaseWithInterpreter(
              interpreterTestSuite.get(testCase),interpreter);
          SharedTestCaseWithInterpreterList.add(sharedTestCaseWithInterpreter);
          listOfSharedTestCaseWithInterpreter.get(testCase).add(sharedTestCaseWithInterpreter);
        }
        sharedTestCaseList.add(new SharedTestCase(SharedTestCaseWithInterpreterList));
      }
    }*/
    return new SharedTestSuite(
        listOfSharedTestCaseWithInterpreter.stream().map(item -> new SharedTestCase(item)).collect(
            Collectors.toUnmodifiableList()));
  }

  public static class SharedTestCaseWithInterpreter {


    private final InterpreterObj interpreterObj;
    private final TestCase testCase;

    SharedTestCaseWithInterpreter(TestCase testCase, InterpreterObj interpreterObj) {
      this.interpreterObj = interpreterObj;
      this.testCase = testCase;
    }

    public InterpreterObj getInterpreterObj() {
      return interpreterObj;
    }

    public TestCase getTestCase() {
      return testCase;
    }
  }

}


