package com.pdsl.specifications;

import com.google.common.base.Preconditions;
import com.pdsl.exceptions.SentenceNotFoundException;
import com.pdsl.logging.AnsiTerminalColorHelper;
import com.pdsl.specifications.*;
import com.pdsl.transformers.PolymorphicDslFileException;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.sampled.Line;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class LineDelimitedTestSpecificationFactory implements TestSpecificationFactory {

    private PolymorphicDslPhraseFilter phraseFilter;
    private Charset charset = StandardCharsets.UTF_8;

    public LineDelimitedTestSpecificationFactory(PolymorphicDslPhraseFilter phraseFilter) {
        this.phraseFilter = phraseFilter;
    }

    public LineDelimitedTestSpecificationFactory(PolymorphicDslPhraseFilter phraseFilter, Charset charset) {
        this.charset = charset;
        this.phraseFilter = phraseFilter;
    }

    public Optional<Collection<TestSpecification>> getTestSpecifications(Set<URL> resourceLocations) {
        Objects.requireNonNull(resourceLocations, "Resource paths cannot be null!");
        Preconditions.checkArgument(!resourceLocations.isEmpty(), "Test Resources cannot be empty!");
        List<TestSpecification> testSpecifications = new ArrayList<>(resourceLocations.size());
        for (URL resource : resourceLocations) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resource.openStream()));
                List<InputStream> lines = bufferedReader.lines()
                        .map(line -> new ByteArrayInputStream(line.getBytes(charset)))
                        .collect(Collectors.toList());
                Optional<List<ParseTree>> parseTreesOptional = phraseFilter.validateAndFilterPhrases(lines);
                if (parseTreesOptional.isPresent()) {
                    testSpecifications.add(new DefaultTestSpecification.Builder(resource.toString())
                            .withPhrases(parseTreesOptional.get())
                            .build()
                    );
                }
            } catch (IOException e) {
                throw new PolymorphicDslFileException("Could not open test resource!", e);
            }
        }
        if (testSpecifications.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(testSpecifications);
        }
    }
}
