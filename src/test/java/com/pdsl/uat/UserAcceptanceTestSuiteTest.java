package com.pdsl.uat;

import com.pdsl.runners.PdslTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(PdslTestSuite.class)
@Suite.SuiteClasses(value = {GherkinJUnit4Test.class, SyntaxCheckTest.class,
 GherkinJunitRecognizerTest.class, ClassLevelRecognizerTest.class})
@PdslTestSuite.PdslSuiteClasses(
        systemUnderTest = "Polymorphic DSL Test Framework"
        //,reportGenerator = DefaultAsciidoctorReportGeneratorProvider.class
)
public class UserAcceptanceTestSuiteTest {}
