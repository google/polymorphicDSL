parser grammar PdslTestResourceParser ;

import TestResourceParser, ExtendedTestResourceParser ;

options {tokenVocab=PdslTestResourceLexer ; }

polymorphicDslAllRules : (
    givenTheTestResource |
    givenTheRawResource |
    testResourceValidity
    )+
    ;