package com.google.pdsl.gherkin.models;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DataTablesTest.class, DescriptionsTest.class, DocstringTest.class, EmptyTest.class, EscapedPipesTest.class,
        ExampleTokensTest.class, GherkinTransformerTest.class, IncompleteFeatureTest.class
})
public class GherkinModelSuite {
}
