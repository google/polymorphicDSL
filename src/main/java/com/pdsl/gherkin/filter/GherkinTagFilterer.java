package com.pdsl.gherkin.filter;

import java.util.Set;

/**
 * Determines if a test case derived from a gherkin file (i.e., a pickle) matches a particular tag expression.
 *
 * For example, the following test would match the tag expression "@Foo and not @Bazz" but not "@Gralt and @Foo":
 *
 * @Foo @Bar
 * Scenario: Some scenario
 *    Given some step
 * */
public interface GherkinTagFilterer {

    static boolean pickleHasAllTags(Set<String> allTags, Set<String> pickleTags) {
        return allTags.containsAll(pickleTags);
    }

    boolean tagExpressionMatchesPickle(Set<String> pickleTags, String tagExpression);
}
