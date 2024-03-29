package com.pdsl.specifications;

import com.google.common.base.Preconditions;

import java.net.URI;
import java.nio.file.PathMatcher;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A resource finder that searches the local file system.
 */
public class FileSystemTestResourceGenerator implements TestResourceFinderGenerator {

    private final String resourceRoot;

    /**
     * Creates a resource generator that searches from a specified directory.
     *
     * The provided parameter does not need to be a valid URI as it will internally be
     * converted into one if possible.
     *
     * E.g., the value "./" will search from the directory PDSL executes from.
     *
     * @param resourceRoot a relative path to search
     */
    public FileSystemTestResourceGenerator(String resourceRoot) {
        this.resourceRoot = resourceRoot;
    }

    private List<String> getGlobResourcePaths(String[] resources) {
        if (resourceRoot.startsWith("file:///")) {
            return Arrays.stream(resources).map(resourceRoot::concat).collect(Collectors.toList());
        }
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
