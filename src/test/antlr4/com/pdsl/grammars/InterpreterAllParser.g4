parser grammar InterpreterAllParser;

import InterpreterOneParser, InterpreterTwoParser;

options { tokenVocab=InterpreterAllLexer; }

polymorphicDslAllRulesAll: (hiFolks|helloWorld)+;

