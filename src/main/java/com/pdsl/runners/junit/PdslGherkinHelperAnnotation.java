package com.pdsl.runners.junit;

import com.pdsl.executors.TraceableTestRunExecutor;
import com.pdsl.runners.PdslConfiguration;
import com.pdsl.runners.PdslGherkinApplication;
import com.pdsl.runners.RecognizedBy;
import com.pdsl.runners.TestSpecificationFactoryGenerator;
import com.pdsl.specifications.TestResourceFinderGenerator;
import com.pdsl.testcases.TestCaseFactory;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;

import javax.inject.Provider;
import java.lang.annotation.Annotation;
import java.util.Objects;

class PdslGherkinHelperAnnotation implements Annotation, PdslConfiguration {
    private final PdslGherkinApplication annotation;
    PdslGherkinHelperAnnotation(PdslGherkinApplication annotation) {
        this.annotation = annotation;
    }
    @Override
    public String context() {
        return annotation.context();
    }

    @Override
    public String applicationName() {
        return annotation.applicationName();
    }

    @Override
    public String resourceRoot() {
        return annotation.resourceRoot();
    }

    @Override
    public Class<? extends Parser> dslRecognizerParser() {
        return annotation.dslRecognizerParser();
    }

    @Override
    public Class<? extends Lexer> dslRecognizerLexer() {
        return annotation.dslRecognizerLexer();
    }

    @Override
    public Class<? extends Provider<? extends TraceableTestRunExecutor>> testRunExecutor() {
        return annotation.testRunExecutor();
    }

    @Override
    public Class<? extends Provider<? extends TestResourceFinderGenerator>> resourceFinder() {
        return annotation.resourceFinder();
    }

    @Override
    public String recognizerRule() {
        return RecognizedBy.DEFAULT_RECOGNIZER_RULE_NAME;
    }

    @Override
    public Class<? extends Provider<? extends TestSpecificationFactoryGenerator>> specificationFactoryProvider() {
        return PdslGherkinHelperAnnotation.EmptyFactoryGenerator.class;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PdslGherkinHelperAnnotation)) return false;
        PdslGherkinHelperAnnotation that = (PdslGherkinHelperAnnotation) o;
        return annotation.equals(that.annotation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(annotation);
    }

    public static final class EmptyFactoryGenerator implements Provider<TestSpecificationFactoryGenerator> {
        @Override
        public TestSpecificationFactoryGenerator get() {
            return null;
        }
    }

    @Override
    public Class<? extends Provider<? extends TestCaseFactory>> testCaseFactoryProvider() {
        return PdslGherkinHelperAnnotation.EmptyTestCaseFactory.class;
    }

    public static final class EmptyTestCaseFactory implements Provider<TestCaseFactory> {
        public EmptyTestCaseFactory(){}
        @Override
        public TestCaseFactory get() {
            return null;
        }
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return PdslConfiguration.class;
    }

}
