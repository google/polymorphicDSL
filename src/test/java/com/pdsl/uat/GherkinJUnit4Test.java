package com.pdsl.uat;

import com.pdsl.grammars.PdslFrameworkSpecificationImpl;
import com.pdsl.grammars.PdslFrameworkSpecificationLexer;
import com.pdsl.grammars.PdslFrameworkSpecificationParser;
import com.pdsl.junit.PdslGherkinApplication;
import com.pdsl.junit.PdslGherkinJUnit4Runner;
import com.pdsl.junit.PdslTest;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
@RunWith(PdslGherkinJUnit4Runner.class)
@PdslGherkinApplication(
        applicationName = "Polymorphic DSL Test Framework",
        context = "User Acceptance Testing",
        resourceRoot = "src/test/resources/framework_specifications/features"
)
public class GherkinJUnit4Test {

    @Test
    public void normalJUnitTests_stillRunWithPdslRunner() {}

    @Test
    @Ignore("Make sure ignore tests render correctly by runner")
    public void ignoreThis(){}
}
