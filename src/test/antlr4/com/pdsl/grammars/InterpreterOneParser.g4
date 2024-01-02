parser grammar InterpreterOneParser;

options { tokenVocab=InterpreterOneLexer; }

helloWorld : HELLO_WORLD;

polymorphicDslAllRules: (helloWorld)+;
