package org.junit.jupiter.engine.descriptor;

import com.pdsl.executors.DefaultPolymorphicDslTestExecutor;
import com.pdsl.executors.TraceableTestRunExecutor;
import com.pdsl.runners.*;
import com.pdsl.specifications.TestResourceFinder;
import com.pdsl.specifications.TestResourceFinderGenerator;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.testcases.TaggedTestCase;
import com.pdsl.testcases.TestCase;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.junit.jupiter.api.extension.*;

import java.net.URI;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A InvocationContextProvider used with a JUnit5 @TestTemplate to integrate
 * with the PDSL framework.
 *
 * This context provider does the majority of the work converting DSLs from
 * URIs into test cases that may be executed by JUnit5.
 * 
 *  The user will need to provide an InvocationContextProvider. An example of how
 *  to practically do that would be like this:
 * 
 *
 *   ＠Override
 *   public Stream&lt;TestTemplateInvocationContext&gt; provideTestTemplateInvocationContexts(ExtensionContext context) {
 *            return getInvocationContext(PdslConfigParameter.createGeneralPdslConfig(testCaseFactorySupplier, testSpecificationFactoryGeneratorSupplier,
 *                        List.of(
 *                                new PdslTestParameter.Builder(parseTreeListenerSupplier,
 *                                        MyLexer.class, MyParser.class)
 *                                        .build()
 *                        )
 *                    )
 *                    .withApplicationName("Polymorphic DSL Framework")
 *                    .withContext("User Acceptance Test")
 *                    .withResourceRoot(Paths.get("src/test/resources/testdata/good").toUri())
 *                    .withRecognizerRule("polymorphicDslAllRules")
 *                    .build())
 *                    .stream();
 *        }
 *
 */
public abstract class PdslGeneralInvocationContextProvider implements InvocationInterceptor, TestTemplateInvocationContextProvider {

    private static final Logger logger = LoggerFactory.getLogger(PdslGeneralInvocationContextProvider.class);
    private static final ExecutorHelper executorHelper = PdslConfigurationHelper.getExecutorHelper(new JupiterDescriptorKey());
    private static final TraceableTestRunExecutor DEFAULT_EXECUTOR = new DefaultPolymorphicDslTestExecutor();
    private final Map<List<String>, TestCase> duplicateTest = new HashMap<>();

    @Override
    public boolean supportsTestTemplate(ExtensionContext context) {
        return true;
    }

    /**
     * Creates URIs using the resourceFinder the supplier in the PdslConfigParameter.
     *
     * If the user has not specified a resourceFinder then the local filesystem
     * will be searched.
     */
    protected Collection<URI> getResources(PdslConfigParameter configParameter, PdslTestParameter testParameter) {
        TestResourceFinderGenerator resourceFinderGenerator = configParameter.getResourceFinder().isPresent() ? configParameter.getResourceFinder().get().get()
                : new ExecutorHelper.DefaultResourceFinderGenerator(configParameter.getResourceRoot().toString()).get();

        TestResourceFinder finder = resourceFinderGenerator.get(testParameter.getIncludesResources(), testParameter.getExcludesResources());
        Optional<Collection<URI>> resources = finder.scanForTestResources(configParameter.getResourceRoot());
        if (resources.isEmpty()) {
            throw new IllegalArgumentException(String.format("No test resources where found!%n"
                            + "Resource Root: %s%n"
                            + "Included Resources: %s%n"
                            + "Excluded Resources: %s%n",
                    configParameter.getResourceRoot(),
                    String.join(", ", testParameter.getIncludesResources()),
                    String.join(", ", testParameter.getExcludesResources())));
        }
        return resources.get();
    }

    /**
     * Creates TestSpecifications from the provided URIs and phraseFilter.
     *
     * The specified phraseFilter will be used for all URIs.
     *
     * If no specifications can be produced a runtime exception will be thrown.
     */
    protected Collection<TestSpecification> getTestSpecifications(PdslConfigParameter configParameter, PolymorphicDslPhraseFilter phraseFilter, Collection<URI> testResources) {
        Optional<Collection<TestSpecification>> testSpecifications = configParameter.getSpecificationFactoryProvider().get()
                .get(phraseFilter)
                .getTestSpecifications(testResources.stream().collect(Collectors.toUnmodifiableSet()));
        if (testSpecifications.isEmpty()) {
            throw new IllegalStateException(String.format("No test specifications could be produced from the resources!%n"
                            + "Test Specification Factory: %s%n"
                            + "Resources: %s%n", configParameter.getSpecificationFactoryProvider().get().getClass(),
                    String.join(String.format("%n\t\t")), testResources));
        }
        return testSpecifications.get();
    }

    /**
     * Produces a phrase filter from the specified parameters.
     *
     * The phrase filter is used to provide a recognizer for any input it
     * sees and a subgrammar parser that will execute test logic on a
     * potential subset of the sentences.
     *
     * If neither the PdslConfigParameter or PdslTestParameter does not have 
     * a recognizer then the PdslTestParameter will be used as both the parser
     * and general recognizer.
     *
     * If the PdslTestParameter has a recognizer specified it will always be
     * used regardless if a PdslConfigParameter has one specified.
     *
     * If the PdslTestParameter does NOT have a recognizer specified but the
     * PdslConfigParameter does have one then the parent PdslConfigParameter
     * will provide a recognizer.
     */
    protected PolymorphicDslPhraseFilter getPhrasefilter(PdslConfigParameter configParameter, PdslTestParameter pdslTestParameter) {

        // Use the recognizer on the individual test case if specified
        if (pdslTestParameter.getRecognizedByLexer().isPresent() && pdslTestParameter.getRecognizedByParser().isPresent()) {
            return executorHelper.makeDefaultFilter(pdslTestParameter.getParser(), pdslTestParameter.getLexer(),
                    pdslTestParameter.getRecognizedByParser().get(), pdslTestParameter.getRecognizedByLexer().get(),
                    pdslTestParameter.getStartRule(),
                    pdslTestParameter.getRecognizerRule().isPresent() ? pdslTestParameter.getRecognizerRule().get() : configParameter.getRecognizerRule()
                    );
        }
        // Otherwise use the recognizer specified in the configuration. If none specified, the parser used by the
        // pdslTest will be used as the recognizer as well.
        PolymorphicDslPhraseFilter phraseFilter = executorHelper.makeDefaultFilter(pdslTestParameter.getParser(),
                pdslTestParameter.getLexer(),
                configParameter.getDslRecognizerParser().isPresent() ? configParameter.getDslRecognizerParser().get() : pdslTestParameter.getParser(),
                configParameter.getDslRecognizerLexer().isPresent() ? configParameter.getDslRecognizerLexer().get() : pdslTestParameter.getLexer(),
                pdslTestParameter.getStartRule(),
                pdslTestParameter.getRecognizerRule().isPresent() ? pdslTestParameter.getRecognizerRule().get() : configParameter.getRecognizerRule()
        );
        return phraseFilter;
    }

    /**
     * Creates test cases from the provided testSpecifications.
     *
     * The specifications provided will be created from the TestCaseFactory
     * specified by the PdslConfigParameter.
     */
    protected Collection<TestCase> getTestCases(PdslConfigParameter configParameter, Collection<TestSpecification> testSpecifications) {
        Collection<TestCase> testCases = configParameter.getTestCaseFactoryProvider().get()
                .processTestSpecification(testSpecifications);
        List<URI> duplicateUris = new ArrayList<>();
        // Remove any duplicates with the same filtered test body
        testCases.forEach(tc -> {
                if (duplicateTest.containsKey(tc.getContextFilteredPhraseBody())) {
                    duplicateUris.add(tc.getOriginalSource());
                } else {
                    duplicateTest.put(tc.getContextFilteredPhraseBody(), tc);
                }
            }
        );
        logger.info("{} duplicate tests filtered out", testCases.size() - duplicateTest.entrySet().size());
        duplicateUris.stream().forEach(uri -> logger.info(uri.toString()));
        return duplicateTest.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toUnmodifiableSet());
    }

    /**
     *     Filters out test cases that do not match the supplied tag expression.
     *
     *     It is up to the underlying implementation on how to do this. By default only TaggedTestCases can be
     *     filtered out. In the event the test case is a tagged test case and none of its tags exactly equals the tag
     *     expression provided then it is filtered out.
     */
    protected Collection<TestCase> filterTestCases(Collection<TestCase> testCases, String tagExpression) {
        return testCases.stream()
                .filter(tc -> {
                    if (tc instanceof TaggedTestCase) {
                        return ((TaggedTestCase)tc).getTags().equals(tagExpression);
                    }
                    return true;
                }).collect(Collectors.toUnmodifiableList());
    }

    /**
     * Creates a PdslExecutables from the provided PdslConfigParameter.
     *
     * Users are unlikely to need to call this unless they are embedding PDSL
     * within their own frameworks.
     */
    public Collection<PdslExecutable> createPdslExecutables(PdslConfigParameter parameter) {
        List<PdslExecutable> executables = new ArrayList<>();
        for (PdslTestParameter pdslTestParameter : parameter.getPdslTestParameters()) {
            // Get the test resources
            Collection<URI> testResources = getResources(parameter, pdslTestParameter);
            // Create the phrase filter which determines which sentences are relevant to the tests based on context
            PolymorphicDslPhraseFilter phraseFilter = getPhrasefilter(parameter, pdslTestParameter);
            // Create test specifications from the test resource files written in a DSL
            Collection<TestSpecification> testSpecifications = getTestSpecifications(parameter, phraseFilter, testResources);
            // Convert specifications to test cases
            Collection<TestCase> unfilteredTestCases = getTestCases(parameter, testSpecifications);
            // Filter the test cases
            Collection<TestCase> filteredTestCases = filterTestCases(unfilteredTestCases, pdslTestParameter.getTagExpression());
            if (filteredTestCases.isEmpty()) {
                throw new IllegalArgumentException(String.format(" All of the test cases were filtered out of a test!%n"
                        + "\tResources: %s%n"
                        + "\tFilter: %s", pdslTestParameter.getIncludesResources(), pdslTestParameter.getTagExpression())

                );
            }
            // Finally convert test cases into invocation contexts for a JUnit5 @TestTemplate
            executables.addAll(filteredTestCases.stream()
                    .map(testCase ->  getPdslExecutable(testCase, parameter, pdslTestParameter))
                    .collect(Collectors.toUnmodifiableList()));
        }
        return executables;
    }

    /**
     * Creates InvocationContext using the PDSL test framework to use with a JUnit5
     * ＠TestTemplate.
     *
     */
    public Collection<TestTemplateInvocationContext> getInvocationContext(PdslConfigParameter parameter) {
        return createPdslExecutables(parameter).stream()
                    .map(testCase ->  invocationContext(testCase))
                    .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Creates a PdslExecutable from the provided TestCase.
     *
     * The TestCase will be executed using whatever PolymorphicDslTestExecutor
     * is specified by the PdslConfigParameter (if none was specified a
     * default will be used).
     */
    protected PdslExecutable getPdslExecutable(TestCase testCase, PdslConfigParameter parameter, PdslTestParameter pdslTestParameter) {
        TraceableTestRunExecutor executor = parameter.getTestRunExecutor().isPresent() ? parameter.getTestRunExecutor().get().get()
                : DEFAULT_EXECUTOR;
        if (pdslTestParameter.getVisitor().isPresent()) {
            return new PdslExecutable(testCase, executor,
                    (Supplier<ParseTreeVisitor<?>>) pdslTestParameter.getVisitor().get(), parameter.getContext());
        }
        return new PdslExecutable(testCase, executor,
                parameter.getContext(), (Supplier<ParseTreeListener>) pdslTestParameter.getListener().get());
    }

    /**
     * Creates an invocationContext for a JUnit5 ＠TestTemplate from the provided
     * PdslExecutable.
     */
    protected TestTemplateInvocationContext invocationContext(PdslExecutable executable) {


        return new TestTemplateInvocationContext() {
            @Override
            public String getDisplayName(int invocationIndex) {
                return executable.getTestTitle();
            }

            @Override
            public List<Extension> getAdditionalExtensions() {
                return Collections.singletonList(new ParameterResolver() {
                    @Override
                    public boolean supportsParameter(ParameterContext parameterContext,
                                                     ExtensionContext extensionContext) {
                        return parameterContext.getParameter().getType().equals(PdslExecutable.class);
                    }

                    @Override
                    public Object resolveParameter(ParameterContext parameterContext,
                                                   ExtensionContext extensionContext) {
                        return executable;
                    }
                });
            }
        };
    }
}
