package com.pdsl.gherkin.filter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class GherkinTagFiltererAdapter implements TagFilterer {

  private final GherkinTagFilterer gherkinTagFilterer;

  public GherkinTagFiltererAdapter(GherkinTagFilterer gherkinTagFilterer) {
    this.gherkinTagFilterer = gherkinTagFilterer;
  }

  @Override
  public boolean tagExpressionMatches(Collection<String> tags, String tagExpression) {
    return gherkinTagFilterer.tagExpressionMatchesPickle(new HashSet<>(tags), tagExpression);
  }
}