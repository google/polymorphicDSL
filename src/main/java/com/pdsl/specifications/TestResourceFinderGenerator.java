package com.pdsl.specifications;

public interface TestResourceFinderGenerator {

    TestResourceFinder get(String[] includes, String[] excludes);
}
