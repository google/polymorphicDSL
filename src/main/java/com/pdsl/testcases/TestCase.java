package com.pdsl.testcases;

import java.util.Iterator;

/**
 * An executable
 */
public interface TestCase {

    /* Provides an identifier such that if the underlying, ordered phrases are identical to another group of phra they will have the same identifier
     *
     * This is necessary because after the Test Specifications are filtered it may produce duplicates.
     * Most Test Executors will not want to run duplicate tests and they will rely on this identifier
     * to avoid executing redundant tests.
     *
     */
    long getTestCaseId();
    String getTestTitle();
    int getBodySize();
    Iterator<TestSection> getTestBody();
}
