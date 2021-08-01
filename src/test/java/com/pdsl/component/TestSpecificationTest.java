package com.pdsl.component;

import com.pdsl.specifications.FilteredPhrase;
import com.pdsl.specifications.LineDelimitedTestSpecificationFactory;
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
                    public Optional<List<FilteredPhrase>> filterPhrases(List<InputStream> testInput) {
                        return Optional.empty();
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
