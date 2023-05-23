package com.pdsl.runners;

import com.pdsl.specifications.TestResourceFinderGenerator;

import javax.inject.Provider;

/**
 * A helper class for test framework implementers.
 *
 * Normal users of the framework should ignore this.
 */
public class EmptyTestResourceFinder implements Provider<TestResourceFinderGenerator> {
    @Override
    public TestResourceFinderGenerator get() {
        return null;
    }
}
