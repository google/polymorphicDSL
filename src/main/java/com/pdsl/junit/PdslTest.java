package com.pdsl.junit;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

import javax.inject.Provider;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PdslTest {
    Class<? extends Parser> parser();
    Class<? extends Lexer> lexer();
    Class<? extends Provider<? extends ParseTreeListener>> listener();
    String tags() default "";
    String[] includesResources() default {"*.feature"};
    String[] excludesResources() default "";
    boolean skipUnrecognized() default true;

    static String DEFAULT_ALL_RULE = "polymorphicDslAllRules" ;
}
