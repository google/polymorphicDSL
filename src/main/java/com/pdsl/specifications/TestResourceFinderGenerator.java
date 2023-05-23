package com.pdsl.specifications;

/**
 * A generator for @link{com.pdsl.specifications.TestResourceFinder}s that provide explicit inclusion and exclusion
 * criteria.
 */
public interface TestResourceFinderGenerator {

    /**
     * Creates a resource finder using the provided string expressions.
     *
     * @param includes patterns to include
     * @param excludes patterns to exclude
     * @return a test resource finder operating off the specified criteria
     */
    TestResourceFinder get(String[] includes, String[] excludes);
}
