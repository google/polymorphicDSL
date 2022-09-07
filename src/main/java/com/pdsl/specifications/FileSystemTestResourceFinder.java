package com.pdsl.specifications;

import com.google.common.base.Preconditions;
import com.pdsl.transformers.PolymorphicDslFileException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileSystemTestResourceFinder implements TestResourceFinder {

    private final PathMatcher pathMatcher;

    public FileSystemTestResourceFinder(PathMatcher pathMatcher) {
        this.pathMatcher = pathMatcher;
    }

    public static Collection<Path> findExactMatch(Path searchDirectory, String fileName) throws IOException {
        try (Stream<Path> files = Files.walk(searchDirectory)) {
            return files
                    .filter(f -> f.getFileName().toString().equals(fileName))
                    .collect(Collectors.toList());
        }
    }

    public static Collection<Path> findMatchingFiles(Path searchDirectory, PathMatcher matcher) throws IOException {
        try (Stream<Path> files = Files.walk(searchDirectory)) {
            return files
                    .filter(path -> !Files.isDirectory(path)) // Only get files
                    .filter(matcher::matches)
                    .collect(Collectors.toList());
        }
    }

    public Optional<Collection<URI>> scanForTestResources(Path path) {
        return scanForTestResources(path.toUri());
    }

    @Override
    public Optional<Collection<URI>> scanForTestResources(URI uri) {
        Path sourceDirectory = Paths.get(uri.getPath());
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
