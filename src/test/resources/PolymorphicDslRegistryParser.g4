parser grammar PolymorphicDslRegistryParser;

import RegistryParser;
options {tokenVocab=RegistryLexer;}

polymorphicDslAllRules:
	( mathematical_expression
	| helloWorld
	)+
	;
