package com.google.pdsl;

import com.google.pdsl.gherkin.*;
import com.google.pdsl.gherkin.models.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({PolymorphicDslTest.class, PolymorphicDslGherkinExecutionTest.class, GherkinTagFiltererTest.class,
        GherkinTagFiltererInvalidInputTest.class, GherkinPolymorphicDslTestExecutorTest.class, GherkinModelSuite.class,
        GherkinTestRunWithFailuresTest.class
})
public class FullPolymorphicDslTestSuite {}
