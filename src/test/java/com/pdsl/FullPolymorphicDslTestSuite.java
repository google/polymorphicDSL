package com.pdsl;

import com.pdsl.component.TestResourceFinderComponentTest;
import com.pdsl.framework.FrameworkSpecificationsTest;
import com.pdsl.gherkin.*;
import com.pdsl.gherkin.models.GherkinModelSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({PolymorphicDslTest.class, PolymorphicDslGherkinExecutionTest.class, GherkinTagFiltererTest.class,
        GherkinPolymorphicDslTestExecutorTest.class, GherkinModelSuite.class,
        GherkinTestRunWithFailuresTest.class,
        //InterpreterBasedPhraseFilterTest.class, //TODO: finish this feature then enable it
        FrameworkSpecificationsTest.class,
        TestResourceFinderComponentTest.class
})
public class FullPolymorphicDslTestSuite {
}
