package com.pdsl.specifications;

import com.google.common.base.Preconditions;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GlobPathMatcher implements PathMatcher {

    private static final String GLOB = "glob:";
    private final List<PathMatcher> includes;
    private final Optional<List<PathMatcher>> excludes;

    private static PathMatcher getMatcher(String expression) {
        return FileSystems.getDefault().getPathMatcher(
                expression.startsWith(GLOB) ? expression : String.format("%s%s", GLOB, expression));
    }

    private static List<PathMatcher> getMatcher(List<String> expressions) {
        return expressions.stream()
                .map(GlobPathMatcher::getMatcher)
                .collect(Collectors.toUnmodifiableList());
    }

    public GlobPathMatcher(String includes, String excludes) {
        Preconditions.checkNotNull(includes);
        Preconditions.checkNotNull(excludes);
        this.includes = List.of(getMatcher(includes));
        this.excludes = Optional.of(List.of(getMatcher(excludes)));
    }

    public GlobPathMatcher(String includes) {
        Preconditions.checkNotNull(includes);
        this.includes = List.of(getMatcher(includes));
        this.excludes = Optional.empty();
    }

    public GlobPathMatcher(List<String> includeExpressions) {
        Preconditions.checkNotNull(includeExpressions);
        this.includes = getMatcher(includeExpressions);
        this.excludes = Optional.empty();
    }

    public GlobPathMatcher(List<String> includeExpressions, List<String> excludeExpressions) {
        Preconditions.checkNotNull(includeExpressions);
        Preconditions.checkNotNull(excludeExpressions);
        this.includes = getMatcher(includeExpressions);
        this.excludes = Optional.of(getMatcher(excludeExpressions));
    }

    public GlobPathMatcher(String includeExpression, List<String> excludeExpressions) {
        Preconditions.checkNotNull(includeExpression);
        Preconditions.checkNotNull(excludeExpressions);
        this.includes = List.of(getMatcher(includeExpression));
        this.excludes = Optional.of(getMatcher(excludeExpressions));
    }

    public GlobPathMatcher(List<String> includeExpressions, String excludeExpression) {
        Preconditions.checkNotNull(includeExpressions);
        Preconditions.checkNotNull(excludeExpression);
        this.includes = getMatcher(includeExpressions);
        this.excludes = Optional.of(List.of(getMatcher(excludeExpression)));
    }

    @Override
    public boolean matches(Path path) {
        if (excludes.isPresent()) {
            return includes.stream().anyMatch(matcher -> matcher.matches(path))
                    && excludes.get().stream().noneMatch(matcher -> matcher.matches(path));
        } else {
            return includes.stream().anyMatch(matcher -> matcher.matches(path));
        }
    }
}
