package com.pdsl.junit;

import com.google.common.base.Preconditions;
import com.pdsl.executors.DefaultPolymorphicDslTestExecutor;
import com.pdsl.executors.TraceableTestRunExecutor;
import com.pdsl.gherkin.DefaultGherkinTestSpecificationFactory;
import com.pdsl.gherkin.specifications.GherkinTestSpecificationFactory;
import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.specifications.FileSystemTestResourceFinder;
import com.pdsl.specifications.GlobPathMatcher;
import com.pdsl.specifications.TestResourceFinder;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.testcases.PreorderTestCaseFactory;
import com.pdsl.testcases.TestCase;
import com.pdsl.testcases.TestCaseFactory;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class PdslGherkinJUnit4Runner extends BlockJUnit4ClassRunner {

    private final String context;
    private final String applicationName;
    private final String resourceRoot;
    private final Collection<MetadataTestRunResults> results = new ArrayList<>();
    private static final TestCaseFactory testCaseFactory = new PreorderTestCaseFactory();

    public Map<String, Collection<MetadataTestRunResults>> getMetaDataTestRunResults() {
        return Map.of(context, results);
    }

    /**
     * Returns the methods that run tests.
     * <p>
     * This includes all standard JUnit4 methods annotated with @Test as well as any annotated with @PdslTest.
     */
    @Override
    protected List<FrameworkMethod> computeTestMethods() {
        List<FrameworkMethod> frameworkMethods = new ArrayList<>();
        frameworkMethods.addAll(getTestClass().getAnnotatedMethods(Test.class));
        frameworkMethods.addAll(
                getTestClass().getAnnotatedMethods(PdslTest.class));
        return List.copyOf(frameworkMethods);
    }

    private Collection<TestCase> getTestCases(PdslTest pdslTest) {
        Set<URL> testResources = getTestResources(pdslTest);
        // Create the phrase filter that will determine the grammar we use
        PolymorphicDslPhraseFilter polymorphicDslPhraseFilter = new DefaultPolymorphicDslPhraseFilter(pdslTest.parser(), pdslTest.lexer());
        GherkinTestSpecificationFactory gherkinTestSpecificationFactory = new DefaultGherkinTestSpecificationFactory(polymorphicDslPhraseFilter);
        Optional<Collection<TestSpecification>> gherkinTestSpecifications = gherkinTestSpecificationFactory.getTestSpecifications(testResources);
        if (gherkinTestSpecifications.isPresent()) {
            gherkinTestSpecifications = gherkinTestSpecificationFactory.filterGherkinTestSpecificationsByTagExpression(
                    gherkinTestSpecifications.get(),
                    pdslTest.tags());
        }
        // Previous if block may have changed the collection to be empty after filtering
        if (gherkinTestSpecifications.isEmpty()) {
            throw new IllegalArgumentException(String.format(
                    "Feature files were found but they were all filtered out!%n\tTag Filter: %s", pdslTest.tags()));
        }
        return testCaseFactory.processTestSpecification(gherkinTestSpecifications.get());
    }

    private Set<URL> getTestResources(PdslTest pdslTest) {
        Set<URL> testResources = new HashSet<>();
        // Find the files we will be testing
        PathMatcher pathMatcher = new GlobPathMatcher(getGlobResourcePaths(pdslTest.includesResources()),
                getGlobResourcePaths(pdslTest.excludesResources()));
        TestResourceFinder finder = new FileSystemTestResourceFinder(pathMatcher);
        try {

            Optional<Collection<URL>> resources = finder.scanForTestResources(Paths.get(resourceRoot).toUri().toURL());
            if (resources.isPresent()) {
                testResources.addAll(resources.get());
            }
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(String.format(
                    "Could not find test resources when searching file system at:%n\t%s", resourceRoot),
                    e);
        }
        if (testResources.isEmpty()) {
            throw new IllegalArgumentException(String.format(
                    "No feature files were found!%n\tResource Root: %s%n%n\tincluding: %s%n%n\texcluding: %s",
                    resourceRoot, Arrays.deepToString(pdslTest.includesResources()), Arrays.deepToString(pdslTest.excludesResources())));
        }
        return testResources;
    }

    private List<String> getGlobResourcePaths(String[] resources) {
        String root = !resourceRoot.endsWith("/") ? "**/" + resourceRoot + "/" : "**/" + resourceRoot;
        return Arrays.stream(resources).map(root::concat).collect(Collectors.toList());
    }

    @Override
    public void run(RunNotifier runNotifier) {
        for (FrameworkMethod method : getChildren()) {
            if (isIgnored(method)) {
                runNotifier.fireTestIgnored(describeChild(method));
            } else {
                runChild(method, runNotifier);
            }
        }
    }

    @Override
    protected void runChild(final FrameworkMethod method, RunNotifier notifier) {
        PdslTest pdslTest = method.getAnnotation(PdslTest.class);
        if (pdslTest != null) {
            ParseTreeListener parseTreeListener = getParseTreeListener(pdslTest);
            final Collection<TestCase> testCases = getTestCases(pdslTest);
            //TODO: Allow the input streams for the executor to be customized
            final TraceableTestRunExecutor executor = new DefaultPolymorphicDslTestExecutor();
            try {
            Preconditions.checkArgument(!testCases.isEmpty(),
                    "Somehow no test cases were produced from the features! This is likely an error with the PDSL framework");

                PdslExecutorRunner pdslExecutorRunner = new PdslExecutorRunner(getTestClass().getJavaClass(),
                        parseTreeListener, getTestCases(pdslTest), executor);
                pdslExecutorRunner.run(notifier);
                results.addAll(pdslExecutorRunner.getMetadataTestRunResults());
            } catch (InitializationError initializationError) {
                throw new IllegalStateException("Could not intialize PdslExecutorRunner!", initializationError);
            }
        } else {
            super.runChild(method, notifier);
        }
    }


    private ParseTreeListener getParseTreeListener(PdslTest pdslTest) {
        try {
            //TODO: Allow a provider to pass the parsetreelistener for more complicated implementations
            Constructor<?> parseTreeListenerConstructor = pdslTest.listener().getConstructor();
            return (ParseTreeListener) parseTreeListenerConstructor.newInstance();
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException(String.format("Could not find a default constructor for Parse Tree Listener %s.%n"
                    + "The ParseTreeListener MUST have a constructor that takes no parameters.", pdslTest.listener().getSimpleName()), e);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new IllegalStateException(String.format("Something went wrong when trying to create the Parse Tree Listener %s.%n"
                    + "The ParseTreeListener MUST have a constructor that takes no parameters.", pdslTest.listener().getSimpleName()), e);
        }
    }

    public String getApplicationName() {
        return applicationName;
    }

    public PdslGherkinJUnit4Runner(Class<?> testClass) throws InitializationError {
        super(testClass);
        PdslGherkinApplication annotation = testClass.getAnnotation(PdslGherkinApplication.class);
        Preconditions.checkNotNull(annotation, String.format("Class run with %s must be annotated with %s!",
                getClass().getSimpleName(), PdslGherkinApplication.class.getSimpleName()));
        Preconditions.checkArgument(!annotation.context().isBlank(), "Context cannot be blank!");
        Preconditions.checkArgument(!annotation.applicationName().isBlank());
        context = annotation.context();
        applicationName = annotation.applicationName();
        this.resourceRoot = annotation.resourceRoot();
    }
}
