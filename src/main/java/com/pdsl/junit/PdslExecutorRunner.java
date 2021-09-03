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
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class PdslExecutorRunner extends ParentRunner<TestCase> {


    private final TraceableTestRunExecutor executor;
    private final ParseTreeListener parseTreeListener;
    private final List<TestCase> testCases;
    private final String context;
    private final String applicationName;
    private final String resourceRoot;
    private final List<MetadataTestRunResults> metadataTestRunResults;

    private int accumulator = 1;

    public PdslExecutorRunner(Class<?> testClass, ParseTreeListener parseTreeListener, Collection<TestCase> testCases, TraceableTestRunExecutor executor) throws InitializationError {
        super(testClass);
        Preconditions.checkArgument(!testCases.isEmpty(),
                "Somehow no test cases were produced from the features! This is likely an error with the PDSL framework");
        PdslGherkinApplication annotation = testClass.getAnnotation(PdslGherkinApplication.class);
        Preconditions.checkNotNull(annotation, String.format("Class run with %s must be annotated with %s!",
                getClass().getSimpleName(), PdslGherkinApplication.class.getSimpleName()));
        Preconditions.checkArgument(!annotation.context().isBlank(), "Context cannot be blank!");
        Preconditions.checkArgument(!annotation.applicationName().isBlank());
        context = annotation.context();
        applicationName = annotation.applicationName();
        this.resourceRoot = annotation.resourceRoot();
        this.executor = executor;
        this.parseTreeListener = parseTreeListener;
        this.testCases = new ArrayList<>(testCases);
        metadataTestRunResults = new ArrayList<>(testCases.size());
        accumulator = 1;
    }

    public List<MetadataTestRunResults> getMetadataTestRunResults() {
        return metadataTestRunResults;
    }

    @Override
    protected List<TestCase> getChildren() {
        return testCases;
    }

    @Override
    protected Description describeChild(TestCase child) {
        return Description.createTestDescription(getTestClass().getName(),
                String.format("%d - %s", accumulator++, child.getTestTitle()));
    }

    @Override
    protected void runChild(TestCase child, RunNotifier notifier) {
        Preconditions.checkNotNull(child,
                "A null test case was created somehow! No way to run!");
            PdslStatement statement = new PdslStatement(List.of(child), parseTreeListener, context, executor);
            runLeaf(statement, describeChild(child), notifier);
            Optional<MetadataTestRunResults> runResults = statement.getResults();
            if (runResults.isPresent()) {
                metadataTestRunResults.add(statement.getResults().orElseThrow());
            } else {
                throw new IllegalStateException("The PDSL Test Run did not produce any results!");
            }
    }
}
