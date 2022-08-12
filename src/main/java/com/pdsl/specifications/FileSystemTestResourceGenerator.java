package com.pdsl.specifications;

import com.google.common.base.Preconditions;

import java.net.URI;
import java.nio.file.PathMatcher;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FileSystemTestResourceGenerator implements TestResourceFinderGenerator {

    private final String resourceRoot;

    public FileSystemTestResourceGenerator(String resourceRoot) {
        this.resourceRoot = resourceRoot;
    }

    private List<String> getGlobResourcePaths(String[] resources) {
        String root = !resourceRoot.endsWith("/") ? "**/" + resourceRoot + "/" : "**/" + resourceRoot;
        return Arrays.stream(resources).map(root::concat).collect(Collectors.toList());
    }
    @Override
    public TestResourceFinder get(String[] includes, String[] excludes) {
        Preconditions.checkArgument(includes.length != 0,
                "Resource finder could not search for anything because 'includes' was empty!");
        Set<URI> testResources = new HashSet<>();
        // Find the files we will be testing
        PathMatcher pathMatcher = new GlobPathMatcher(getGlobResourcePaths(includes),
                getGlobResourcePaths(excludes));
        TestResourceFinder finder = new FileSystemTestResourceFinder(pathMatcher);
        return finder;
    }
}
