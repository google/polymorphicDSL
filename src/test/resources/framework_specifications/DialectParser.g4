parser grammar DialectParser;

options { tokenVocab=DialectLexer; }

grammarStep : GRAMMAR_STEP ;
subgrammarStep : SUBGRAMMAR_STEP;

polymorphicDslSyntaxCheck : (grammarStep subgrammarStep)+ ;

polymorphicDslAllRules : subgrammarStep ;