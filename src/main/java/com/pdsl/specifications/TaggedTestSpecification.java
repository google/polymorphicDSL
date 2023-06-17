package com.pdsl.specifications;

import java.util.Collection;

/** A test specification that is marked with arbitrary tags. */
public interface TaggedTestSpecification extends TestSpecification {

    /**
     * Get the tags associated with this test specification.
     *
     * @return the tags marking this specification
     */
    Collection<String> getTags();
}
