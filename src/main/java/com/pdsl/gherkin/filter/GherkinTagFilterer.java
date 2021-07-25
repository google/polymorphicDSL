package com.pdsl.gherkin.filter;

import java.nio.charset.Charset;
import java.util.Set;

public interface GherkinTagFilterer {

    public static boolean pickleHasAllTags(Set<String> allTags, Set<String> pickleTags) {
        return allTags.containsAll(pickleTags);
    }

    public boolean tagExpressionMatchesPickle(Set<String> pickleTags, String tagExpression);
}
