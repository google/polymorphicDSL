package com.pdsl.uat;

import com.pdsl.runners.PdslGherkinApplication;
import com.pdsl.runners.junit.PdslGherkinJUnit4Runner;
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
