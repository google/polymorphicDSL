package com.pdsl.gherkin.models;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DataTablesTest.class, Descriptions.class, Docstring.class, Empty.class, EscapedPipes.class,
        ExampleTokens.class, GherkinTransformer.class, IncompleteFeature.class
})
public class GherkinModelSuite {
}
