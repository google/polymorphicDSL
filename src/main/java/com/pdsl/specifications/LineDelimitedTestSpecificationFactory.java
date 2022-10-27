package com.pdsl.specifications;

import com.google.common.base.Preconditions;
import com.pdsl.runners.TestSpecificationFactoryGenerator;
import com.pdsl.transformers.PolymorphicDslFileException;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;

import javax.inject.Provider;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A Test Specification Factory that demarcates phrases by newlines.
 */
public class LineDelimitedTestSpecificationFactory implements TestSpecificationFactory {

    private final PolymorphicDslPhraseFilter phraseFilter;
    private Charset charset = StandardCharsets.UTF_8;

    public static final class DefaultProvider implements Provider<TestSpecificationFactoryGenerator> {
        private static final TestSpecificationFactoryGenerator INSTANCE = new Generator();
        @Override
        public TestSpecificationFactoryGenerator get() {
            return INSTANCE;
        }
    }
    public static final class Generator implements TestSpecificationFactoryGenerator {

        @Override
        public TestSpecificationFactory get(PolymorphicDslPhraseFilter filter) {
            return new LineDelimitedTestSpecificationFactory(filter);
        }
    }

    public LineDelimitedTestSpecificationFactory(PolymorphicDslPhraseFilter phraseFilter) {
        this.phraseFilter = phraseFilter;
    }

    public LineDelimitedTestSpecificationFactory(PolymorphicDslPhraseFilter phraseFilter, Charset charset) {
        this.charset = charset;
        this.phraseFilter = phraseFilter;
    }

    public Optional<Collection<TestSpecification>> getTestSpecifications(Set<URI> resourceLocations) {
        Objects.requireNonNull(resourceLocations, "Resource paths cannot be null!");
        Preconditions.checkArgument(!resourceLocations.isEmpty(), "Test Resources cannot be empty!");
        List<TestSpecification> testSpecifications = new ArrayList<>(resourceLocations.size());
        for (URI resource : resourceLocations) {

            URI uri;
            uri = resource;
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(uri.toURL().openStream()))) {
                List<InputStream> lines = bufferedReader.lines()
                        .map(line -> new ByteArrayInputStream(line.getBytes(charset)))
                        .collect(Collectors.toList());
                Optional<List<FilteredPhrase>> parseTreesOptional = phraseFilter.filterPhrases(lines);
                if (parseTreesOptional.isPresent()) {
                    testSpecifications.add(new DefaultTestSpecification.Builder(uri.toString(), uri)
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
