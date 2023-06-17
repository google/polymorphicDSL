package com.pdsl.testcases;

import java.util.Collection;

public interface TaggedTestCase extends TestCase {

    Collection<String> getTags();
}
