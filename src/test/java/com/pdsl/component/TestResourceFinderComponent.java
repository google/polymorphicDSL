package com.pdsl.component;

import com.pdsl.specifications.FileSystemTestResourceFinder;
import com.pdsl.specifications.GlobPathMatcher;
import org.junit.Test;

import java.net.URI;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;

public class TestResourceFinderComponent {

    private final Path resourcesDirectory = Path.of(getClass().getClassLoader().getResource("test_resource_finder").getPath());

    @Test
    public void resourceFinder_canRecursivelyMatchFiles() {
        PathMatcher pathMatcher = new GlobPathMatcher("glob:**/*\\.txt");
        FileSystemTestResourceFinder fileSystemTestResourceFinder = new FileSystemTestResourceFinder(pathMatcher);
        Collection<URI> allTextFiles = fileSystemTestResourceFinder.scanForTestResources(resourcesDirectory).orElseThrow();
        assertThat(allTextFiles.size()).isEqualTo(4);
    }

    @Test
    public void resourceFinder_exclusionsWork() {
        PathMatcher matcher = new GlobPathMatcher("glob:**/*\\.txt", "glob:**/b\\.txt");
        FileSystemTestResourceFinder noBFiles = new FileSystemTestResourceFinder(matcher);
        Collection<URI> noBResources = noBFiles.scanForTestResources(resourcesDirectory).orElseThrow();
        assertThat(noBResources.size()).isEqualTo(2);
    }

    @Test
    public void resourceFinderWithoutGlobKeywords_exclusionsWork() {
        PathMatcher matcher = new GlobPathMatcher("**/*\\.txt", "**/a\\.txt");
        FileSystemTestResourceFinder noBFiles = new FileSystemTestResourceFinder(matcher);
        Collection<URI> noBResources = noBFiles.scanForTestResources(resourcesDirectory).orElseThrow();
        assertThat(noBResources.size()).isEqualTo(2);
    }

    @Test
    public void optionalOperator_matches() {
        PathMatcher matcher = new GlobPathMatcher("**/?\\.txt");
        FileSystemTestResourceFinder noBFiles = new FileSystemTestResourceFinder(matcher);
        Collection<URI> noBResources = noBFiles.scanForTestResources(resourcesDirectory).orElseThrow();
        assertThat(noBResources.size()).isEqualTo(4);
    }

    @Test
    public void directorySearch_matchesOnlySubdirectory() {
        PathMatcher matcher = new GlobPathMatcher("**/subdirectory/*");
        FileSystemTestResourceFinder noBFiles = new FileSystemTestResourceFinder(matcher);
        Collection<URI> noBResources = noBFiles.scanForTestResources(resourcesDirectory).orElseThrow();
        assertThat(noBResources.size()).isEqualTo(2);
    }

    @Test
    public void characterClass_matches() {
        PathMatcher matcher = new GlobPathMatcher("**/[a-b]\\.txt");
        FileSystemTestResourceFinder noBFiles = new FileSystemTestResourceFinder(matcher);
        Collection<URI> noBResources = noBFiles.scanForTestResources(resourcesDirectory).orElseThrow();
        assertThat(noBResources.size()).isEqualTo(4);
    }

    @Test
    public void multipleMatchExpressions_matchesAllFiles() {
        PathMatcher matcher = new GlobPathMatcher(List.of("**/a\\.txt", "**/b\\.txt"));
        FileSystemTestResourceFinder noBFiles = new FileSystemTestResourceFinder(matcher);
        Collection<URI> noBResources = noBFiles.scanForTestResources(resourcesDirectory).orElseThrow();
        assertThat(noBResources.size()).isEqualTo(4);
    }

    @Test
    public void allItemsExcluded_returnsEmptyCollection() {
        PathMatcher matcher = new GlobPathMatcher("**/a\\.txt", "**/*.txt");
        FileSystemTestResourceFinder noBFiles = new FileSystemTestResourceFinder(matcher);
        Optional<Collection<URI>> noBResources = noBFiles.scanForTestResources(resourcesDirectory);
        assertThat(noBResources.isEmpty()).isTrue();
    }
}
