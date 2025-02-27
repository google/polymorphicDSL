package com.pdsl.gherkin.filter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class adapts the `GherkinTagFilterer` interface to the `TagFilterer` interface. It provides
 * a way to use the functionality of `GherkinTagFilterer` with code that expects a `TagFilterer`.
 */
public class GherkinTagFiltererAdapter implements TagFilterer {

  /**
   * The instance of the `GherkinTagFilterer` to be adapted.
   */
  private final GherkinTagFilterer gherkinTagFilterer;

  /**
   * Constructor for the `GherkinTagFiltererAdapter`.
   *
   * @param gherkinTagFilterer The instance of the `GherkinTagFilterer` to be adapted.
   */
  public GherkinTagFiltererAdapter(GherkinTagFilterer gherkinTagFilterer) {
    this.gherkinTagFilterer = gherkinTagFilterer;
  }

  /**
   * This method checks whether a collection of tags matches a given tag expression. It converts the
   * collection of tags to a `Set` using a `HashSet` and then delegates the logic to the
   * `gherkinTagFilterer.tagExpressionMatchesPickle` method.
   *
   * @param tags The collection of tags to be checked.
   * @param tagExpression The tag expression to match against.
   * @return True if the tags match the tag expression, False otherwise.
   */

  @Override
  public boolean tagExpressionMatches(Collection<String> tags, String tagExpression) {
    return gherkinTagFilterer.tagExpressionMatchesPickle(new HashSet<>(tags), tagExpression);
  }
}