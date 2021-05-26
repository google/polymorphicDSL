package com.google.pdsl.component;

import com.google.pdsl.grammars.BetaLexer;
import com.google.pdsl.grammars.PolymorphicDslBetaParser;
import com.google.pdsl.specifications.LineDelimitedTestSpecificationFactory;
import com.google.pdsl.specifications.TestSpecification;
import com.google.pdsl.specifications.TestSpecificationFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.fail;

@RunWith(JUnit4.class)
public class TestSpecificationTest {

    @Test
    public void emptyFile_throwsException() {
        // Arrange
        TestSpecificationFactory provider =
                new LineDelimitedTestSpecificationFactory<PolymorphicDslBetaParser, BetaLexer>(PolymorphicDslBetaParser.class, BetaLexer.class, LineDelimitedTestSpecificationFactory.ErrorListenerStrategy.SUBGRAMMAR);
        List<String> dslFiles = new LinkedList<>();
        // Act
        try {
            TestSpecification specifications = provider.getTestSpecifications(dslFiles);
            fail("No exception thrown when given empty file list!");
        } catch (Exception e) {

        }
    }
}
