package com.google.pdsl.specifications;

import com.google.pdsl.transformers.PhraseTransformer;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface TestSpecificationFactory {
    public TestSpecification getTestSpecifications(List<String> dslTestFilePaths);

    /**
     * Creates a test specification if possible from the provided input.
     *
     * @param id An identifier for the generated test specification
     * @param testContent The data to be processed into a Test Specification
     * @return TestSpecification if the test content could be processed, empty if not
     */
    public Optional<TestSpecification> getTestSpecification(String id, List<InputStream> testContent);
}
