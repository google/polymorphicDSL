package com.pdsl.component;

import com.pdsl.grammars.BetaLexer;
import com.pdsl.grammars.PolymorphicDslBetaParser;
import com.pdsl.specifications.LineDelimitedTestSpecificationFactory;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.TestSpecificationFactory;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.InputStream;
import java.net.URL;
import java.util.*;

import static org.junit.Assert.fail;

@RunWith(JUnit4.class)
public class TestSpecificationTest {

    @Test
    public void emptyFile_throwsException() {
        // Arrange
        TestSpecificationFactory provider =
                new LineDelimitedTestSpecificationFactory(new PolymorphicDslPhraseFilter() {
                    @Override
                    public Optional<List<ParseTree>> validateAndFilterPhrases(List<InputStream> testInput) {
                        return Optional.empty();
                    }

                    @Override
                    public List<ParseTree> validatePhrases(List<InputStream> testInput) {
                        return new ArrayList<ParseTree>();
                    }
                });
        Set<URL> dslFiles = new HashSet<>();
        // Act
        try {
            provider.getTestSpecifications(dslFiles);
            fail("No exception thrown when given empty file list!");
        } catch (Exception e) {

        }
    }
}
