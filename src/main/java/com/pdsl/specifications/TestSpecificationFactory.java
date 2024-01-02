package com.pdsl.specifications;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

/**
 * A factory that converts a test resource into a test specification when possible.
 * <p>
 * The factory is responsible for allowing the PDSL framework to follow the
 * <i>Interface Segregation Principle</i>; in other words the test specification should not
 * have any information
 */
public interface TestSpecificationFactory {

    /**
     * Creates a test specification if possible from the provided input.
     * <p>
     * It is conceivable that filtering by the factory will filter out <i>all</i> phrases not relevent to the context
     *
     * @param testContent The data to be processed into a Test Specification
     * @return TestSpecification if the test content could be processed, empty if not
     */
    Optional<Collection<TestSpecification>> getTestSpecifications(Set<URI> testContent);


}
