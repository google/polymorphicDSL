package com.pdsl.runners;

import com.pdsl.specifications.TestResourceFinderGenerator;

import javax.inject.Provider;

public class EmptyTestResourceFinder implements Provider<TestResourceFinderGenerator> {
    @Override
    public TestResourceFinderGenerator get() {
        return null;
    }
}
