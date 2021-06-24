parser grammar PdslTestAlphaParser;
import AlphaParser;
options {tokenVocab=RegistryLexer;}

polymorphicDslAllRules:
	(
		mathematical_expression
	)+
	;