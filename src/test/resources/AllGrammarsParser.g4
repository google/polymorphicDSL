parser grammar AllGrammarsParser;

options {tokenVocab=AllGrammarsLexer; }

polymorphicDslAllRules : ALL_INPUTS+;

