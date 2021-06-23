package com.pdsl.component;

import com.pdsl.grammars.BetaLexer;
import com.pdsl.grammars.PolymorphicDslBetaParser;
import com.pdsl.transformers.LineDelimitedTestSpecificationFactory;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.TestSpecificationFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.fail;

@RunWith(JUnit4.class)
public class TestSpecificationTest {

    @Test
    public void emptyFile_throwsException() {
        // Arrange
        TestSpecificationFactory provider =
                new LineDelimitedTestSpecificationFactory<PolymorphicDslBetaParser, BetaLexer>(PolymorphicDslBetaParser.class, BetaLexer.class, LineDelimitedTestSpecificationFactory.ErrorListenerStrategy.SUBGRAMMAR);
        Set<String> dslFiles = new HashSet<>();
        // Act
        try {
            TestSpecification specifications = provider.getTestSpecifications(dslFiles);
            fail("No exception thrown when given empty file list!");
        } catch (Exception e) {

        }
    }
}
