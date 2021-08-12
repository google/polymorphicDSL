package com.pdsl.gherkin.filter;

import java.util.Set;

public interface GherkinTagFilterer {

    static boolean pickleHasAllTags(Set<String> allTags, Set<String> pickleTags) {
        return allTags.containsAll(pickleTags);
    }

    boolean tagExpressionMatchesPickle(Set<String> pickleTags, String tagExpression);
}
