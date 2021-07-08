package com.pdsl.specifications;

import com.pdsl.transformers.PolymorphicDslPhraseFilter;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * A factory that converts a test resource into a test specification when possible.
 *
 * The factory is responsible for allowing the PDSL framework to follow the
 * <i>Interface Segregation Principle</i>; in other words the test specification should not
 * have any information
 */
public interface TestSpecificationFactory {

    /**
     * Creates a test specification if possible from the provided input.
     *
     *  It is conceivable that filtering by the factory will filter out <i>all</i> phrases not relevent to the context
     * @param testContent The data to be processed into a Test Specification
     * @return TestSpecification if the test content could be processed, empty if not
     */
    Optional<TestSpecification> getTestSpecifications(Set<URL> testContent);


}
