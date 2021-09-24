package com.pdsl.component;

import com.pdsl.gherkin.models.GherkinModelSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({FrameworkTestCase.class, FrameworkTestExecutor.class, FrameworkTestResource.class,
        FrameworkTestRunResults.class, FrameworkTestSpecification.class,
        TestResourceFinderComponent.class, GherkinModelSuite.class,
        //AsciiDoctorTraceableReport.class
})
public class ComponentSuiteTest { }
