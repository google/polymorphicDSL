package com.pdsl.runners;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

import javax.inject.Provider;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PdslTest {
    Class<? extends Parser> parser();// default Parser.class
    Class<? extends Lexer> lexer();//default Lexer.class
    Class<? extends Provider<? extends ParseTreeListener>> listener() default EmptyParseTreeListenerProvider.class;
    Class<? extends Provider<? extends ParseTreeVisitor<?>>> visitor() default EmptyParseTreeVisitorProvider.class;


    String tags() default "";
    String[] includesResources() default {"*.feature"};
    String[] excludesResources() default "";
    String startRule() default DEFAULT_ALL_RULE;
    static String DEFAULT_ALL_RULE = "polymorphicDslAllRules" ;

    CodeExecution[] codeExecution() default{};
}