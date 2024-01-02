parser grammar InterpreterTwoParser;

options { tokenVocab=InterpreterTwoLexer; }

hiFolks : HI_FOLKS;

polymorphicDslAllRules: (hiFolks)+;
