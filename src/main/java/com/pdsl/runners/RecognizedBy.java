package com.pdsl.runners;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RecognizedBy {
    Class<? extends Parser> dslRecognizerParser();

    Class<? extends Lexer> dslRecognizerLexer();

    String recognizerRule() default DEFAULT_RECOGNIZER_RULE_NAME;

    String DEFAULT_RECOGNIZER_RULE_NAME = "polymorphicDslSyntaxCheck";
}

