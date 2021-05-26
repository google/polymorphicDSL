parser grammar PolymorphicDslMinimalParser;

options {tokenVocab=MinimalLexer;}
import MinimalParser;

polymorphicDslAllRules:
	minimal
	;