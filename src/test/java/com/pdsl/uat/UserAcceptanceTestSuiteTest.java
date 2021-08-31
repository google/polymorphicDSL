package com.pdsl.uat;

import com.pdsl.junit.PdslGherkinJUnit4Runner;
import com.pdsl.junit.PdslTestSuite;
import com.pdsl.reports.asciidoctor.DefaultAsciidoctorReportGeneratorProvider;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.Suite;

@RunWith(PdslTestSuite.class)
@Suite.SuiteClasses(value = {GherkinJUnit4Test.class})
@PdslTestSuite.PdslSuiteClasses(
        systemUnderTest = "Polymorphic DSL Test Framework"
        //,reportGenerator = DefaultAsciidoctorReportGeneratorProvider.class
)
public class UserAcceptanceTestSuiteTest {}
