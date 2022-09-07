package com.pdsl.specifications;

import java.net.URI;
import java.net.URL;
import java.util.Collection;
import java.util.Optional;

/**
 * A scanner that uses some underlying logic to determine which resources are valid Test Resources.
 */
public interface TestResourceFinder {
    /**
     * Searches the URI for relevant test resources.
     *
     * In the event no test resources are found an empty optional is returned to enable fast failure
     *
     * Any critical runtime exception is expected to be thrown rather than buried with an empty optional return
     * @param uri the location to scan for resources
     * @return an optional collection of URIs that map to relevant test resources.
     */
    Optional<Collection<URI>> scanForTestResources(URI uri);
}
