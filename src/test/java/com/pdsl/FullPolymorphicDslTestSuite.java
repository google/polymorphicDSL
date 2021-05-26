package com.pdsl;

import com.pdsl.gherkin.*;
import com.pdsl.gherkin.models.*;
import com.pdsl.gherkin.*;
import com.pdsl.gherkin.models.GherkinModelSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({PolymorphicDslTest.class, PolymorphicDslGherkinExecutionTest.class, GherkinTagFiltererTest.class,
        GherkinTagFiltererInvalidInputTest.class, GherkinPolymorphicDslTestExecutorTest.class, GherkinModelSuite.class,
        GherkinTestRunWithFailuresTest.class
})
public class FullPolymorphicDslTestSuite {}
