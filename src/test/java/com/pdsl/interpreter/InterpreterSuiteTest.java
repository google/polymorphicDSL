package com.pdsl.interpreter;

import com.pdsl.api.FrameworkSpecifications;
import com.pdsl.api.GherkinApi;
import com.pdsl.api.GherkinPolymorphicDslTestExecutor;
import com.pdsl.api.GherkinTestRunWithFailures;
import com.pdsl.api.PolymorphicDsl;
import com.pdsl.api.PolymorphicDslGherkinExecution;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({InterpreterAll.class})//, InterpreterOne.class, InterpreterTwo.class
public class InterpreterSuiteTest {}
