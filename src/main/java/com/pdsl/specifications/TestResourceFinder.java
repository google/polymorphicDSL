package com.pdsl.specifications;

import java.net.URL;
import java.util.Collection;
import java.util.Optional;

public interface TestResourceFinder {
    /**
     * Searches the URL for relevant test resources.
     *
     * In the event no test resources are found an empty optional is returned to enable fast failure
     *
     * Any critical runtime exception is expected to be thrown rather than buried with an empty optional return
     * @param url
     * @return
     */
    Optional<Collection<URL>> scanForTestResources(URL url);
}
