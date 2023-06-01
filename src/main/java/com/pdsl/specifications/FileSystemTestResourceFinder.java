package com.pdsl.specifications;

import com.google.common.base.Preconditions;
import com.pdsl.transformers.PolymorphicDslFileException;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A resource finder that searches the local filesystem.
 */
public class FileSystemTestResourceFinder implements TestResourceFinder {

    private final PathMatcher pathMatcher;

    public FileSystemTestResourceFinder(PathMatcher pathMatcher) {
        this.pathMatcher = pathMatcher;
    }

    /**
     * Finds a file in a directory tree a specific name.
     *
     * @param searchDirectory directory to search
     * @param fileName name of the file
     * @return paths to all files that matched the name
     * @throws IOException if there was an error traversing the file system
     */
    public static Collection<Path> findExactMatch(Path searchDirectory, String fileName) throws IOException {
        try (Stream<Path> files = Files.walk(searchDirectory)) {
            return files
                    .filter(f -> f.getFileName().toString().equals(fileName))
                    .collect(Collectors.toList());
        }
    }

    /**
     * Finds files that match the provided criteria.
     *
     * @param searchDirectory the root directory to search
     * @param matcher a match expression for filtering files
     * @return paths to matching files
     * @throws IOException if there was an error traversing the filesystem
     */
    public static Collection<Path> findMatchingFiles(Path searchDirectory, PathMatcher matcher) throws IOException {
        try (Stream<Path> files = Files.walk(searchDirectory)) {
            return files
                    .filter(path -> !Files.isDirectory(path)) // Only get files
                    .filter(matcher::matches)
                    .collect(Collectors.toList());
        }
    }

    /**
     * Searches filesystem path using an internal path matcher when created.
     *
     * @param path directory to search
     * @return URIs to all matching files
     */
    public Optional<Collection<URI>> scanForTestResources(Path path) {
        return scanForTestResources(path.toUri());
    }

    @Override
    public Optional<Collection<URI>> scanForTestResources(URI uri) {
        Path sourceDirectory = Paths.get(uri);
        Preconditions.checkArgument(Files.exists(sourceDirectory), String.format("File did not exist at at this location! %s", uri));
        Preconditions.checkArgument(Files.isDirectory(sourceDirectory), String.format("URL must be a directory! %s", uri));
        try {
            Collection<Path> matches = findMatchingFiles(sourceDirectory, pathMatcher);
            Collection<URI> resources = matches.stream()
                    .map(Path::toUri)
                    .collect(Collectors.toList());
            return resources.isEmpty() ? Optional.empty() : Optional.of(resources);
        } catch (IOException e) {
            throw new PolymorphicDslFileException("Error searching for test resources!", e);
        }
    }
}
