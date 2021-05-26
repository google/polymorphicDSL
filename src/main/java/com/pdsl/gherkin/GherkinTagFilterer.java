package com.pdsl.gherkin;

import java.nio.charset.Charset;
import java.util.Set;

public interface GherkinTagFilterer {
    public boolean tagExpressionMatchesPickle(Set<String> pickleTags, String tagExpression, Charset charset);
    public static boolean pickleHasAllTags(Set<String> allTags, Set<String> pickleTags) {
        return allTags.containsAll(pickleTags);
    };
}
