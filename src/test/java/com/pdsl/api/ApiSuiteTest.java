package com.pdsl.api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({FrameworkSpecifications.class, GherkinApi.class, GherkinPolymorphicDslTestExecutor.class,
        GherkinTestRunWithFailures.class, PolymorphicDslGherkinExecution.class, PolymorphicDsl.class
})
public class ApiSuiteTest {}
