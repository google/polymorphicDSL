package com.pdsl;

import com.pdsl.api.*;
import com.pdsl.component.ComponentSuiteTest;
import com.pdsl.uat.UserAcceptanceTestSuiteTest;
import com.pdsl.unit.GherkinTagFiltererTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({PolymorphicDsl.class, GherkinTagFiltererTest.class,
        //InterpreterBasedPhraseFilterTest.class, //TODO: finish this feature then enable it
        ComponentSuiteTest.class,
        ApiSuiteTest.class,
        UserAcceptanceTestSuiteTest.class
})
public class FullPolymorphicDslTestSuite { }
