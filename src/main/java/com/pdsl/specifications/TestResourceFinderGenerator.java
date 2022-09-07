package com.pdsl.specifications;

/**
 * A generator for @link{com.pdsl.specifications.TestResourceFinder}s that provide explicit inclusion and exclusion
 * criteria.
 */
public interface TestResourceFinderGenerator {

    TestResourceFinder get(String[] includes, String[] excludes);
}
