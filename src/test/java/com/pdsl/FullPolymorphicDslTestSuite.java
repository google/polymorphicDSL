package com.pdsl;

import com.pdsl.gherkin.*;
import com.pdsl.gherkin.models.GherkinModelSuite;
import com.pdsl.transformers.InterpreterBasedPhraseFilterTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({PolymorphicDslTest.class, PolymorphicDslGherkinExecutionTest.class, GherkinTagFiltererTest.class,
        GherkinTagFiltererInvalidInputTest.class, GherkinPolymorphicDslTestExecutorTest.class, GherkinModelSuite.class,
        GherkinTestRunWithFailuresTest.class,
        InterpreterBasedPhraseFilterTest.class
})
public class FullPolymorphicDslTestSuite {}
