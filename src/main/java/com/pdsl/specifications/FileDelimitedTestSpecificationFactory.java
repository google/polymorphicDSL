package com.pdsl.specifications;

import static java.nio.charset.StandardCharsets.UTF_8;
import com.google.common.base.Preconditions;
import com.pdsl.runners.TestSpecificationFactoryGenerator;
import com.pdsl.transformers.PolymorphicDslFileException;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.SequenceInputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.inject.Provider;
/**
 * A TestSpecificationFactory that treats each file as a single test case and the entire content as
 * a single phrase.
 *
 * <p>Note that every files content will be stored in main memory simultaneously. This will be
 * unsuitable for massive files.
 */
public final class FileDelimitedTestSpecificationFactory implements TestSpecificationFactory {
  private final PolymorphicDslPhraseFilter phraseFilter;
  private Charset charset = UTF_8;
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
      return new FileDelimitedTestSpecificationFactory(filter);
    }
  }
  public FileDelimitedTestSpecificationFactory(PolymorphicDslPhraseFilter phraseFilter) {
    this.phraseFilter = phraseFilter;
  }
  public FileDelimitedTestSpecificationFactory(
      PolymorphicDslPhraseFilter phraseFilter, Charset charset) {
    this.charset = charset;
    this.phraseFilter = phraseFilter;
  }
  @Override
  public Optional<Collection<TestSpecification>> getTestSpecifications(Set<URI> resourceLocations) {
    Objects.requireNonNull(resourceLocations, "Resource paths cannot be null!");
    Preconditions.checkArgument(!resourceLocations.isEmpty(), "Test Resources cannot be empty!");
    List<TestSpecification> testSpecifications = new ArrayList<>(resourceLocations.size());
    for (URI resource : resourceLocations) {
      URI uri = resource;
      try (BufferedReader bufferedReader =
          new BufferedReader(new InputStreamReader(uri.toURL().openStream(), charset))) {
        List<InputStream> lines =
            bufferedReader
                .lines()
                .map(line -> String.format("%s%n", line))
                .map(line -> new ByteArrayInputStream(line.getBytes(charset)))
                .collect(Collectors.toUnmodifiableList());
        // Combine into a single stream
        InputStream combined;
        if (lines.size() >= 2) {
          combined = new SequenceInputStream(lines.get(0), lines.get(1));
          for (int i = 2; i < lines.size(); i++) {
            combined = new SequenceInputStream(combined, lines.get(i));
          }
        } else if (lines.size() == 1) {
          combined = lines.get(0);
        } else {
          throw new IllegalArgumentException(String.format("The input was empty!%nFile: %s", uri));
        }
        Optional<List<FilteredPhrase>> parseTreesOptional =
            phraseFilter.filterPhrases(List.of(combined));
        if (parseTreesOptional.isPresent()) {
          testSpecifications.add(
              new DefaultTestSpecification.Builder(uri.toString(), uri)
                  .withPhrases(parseTreesOptional.get())
                  .build());
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

