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

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PdslConfiguration {
    String context() default "Unspecified";
    String applicationName() default "Polymorphic DSL System Under Test";
    String resourceRoot() default "";
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


