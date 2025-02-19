package com.pdsl.gherkin.filter;

import java.util.Collection;
import java.util.Set;

/**
 * Determines if a test case derived from a gherkin file (i.e., a pickle) matches a particular tag
 * expression.
 */
public interface TagFilterer {

  boolean tagExpressionMatches(Collection<String> tags, String tagExpression);
}