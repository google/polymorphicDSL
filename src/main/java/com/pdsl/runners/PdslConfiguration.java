package com.pdsl.runners;

import com.pdsl.executors.TraceableTestRunExecutor;
import com.pdsl.specifications.TestResourceFinderGenerator;
import com.pdsl.testcases.TestCaseFactory;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;

import javax.inject.Provider;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A configuration to be used by a PDSL test runner, such as {@link:com.pdsl.runners.junit.PdslJUnit4ConfigurableRunner}.
 *
 * If a <b>resourceRoot</b> is specified it will be prepended to all <i>includes</i> and </i>excludes</i> in every underlying
 * @PdslTest.
 *
 * If <b>dslRecognizerParser</b> and <b>dslRecognizerLexer</b> are specified they will be used as recognizers on all
 * @PdslTest grammars <i>unless</i> a @RecognizedBy annotation is also specified on the same method as the @PdslTest.
 *
 * <b>specificationFactoryProvider</b> is a required field that tells the framework how to convert to divide the
 * input into executable phrases.
 *
 * <b>testCaseFactoryProvider</b> provides a factory that converts the specification into some number of test cases.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PdslConfiguration {
    String context() default "Unspecified";
    String applicationName() default "Polymorphic DSL System Under Test";
    String resourceRoot() default "./";
    // Default class providers make these parameters optional. The underlying framework is expected to provide a
    // concrete implementation suitable for the runner.
    Class<? extends Parser> dslRecognizerParser() default EmptyRecognizerParser.class;
    Class<? extends Lexer> dslRecognizerLexer() default EmptyRecognizerLexer.class;
    Class<? extends Provider<? extends TraceableTestRunExecutor>> testRunExecutor() default EmptyTestExecutorProvider.class;
    Class<? extends Provider<? extends TestResourceFinderGenerator>> resourceFinder() default EmptyTestResourceFinder.class;
    String recognizerRule() default RecognizedBy.DEFAULT_RECOGNIZER_RULE_NAME;
    Class<? extends Provider<? extends TestSpecificationFactoryGenerator>> specificationFactoryProvider();
    Class<? extends Provider<? extends TestCaseFactory>> testCaseFactoryProvider();
}


