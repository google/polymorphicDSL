parser grammar RegistryParser;

import AlphaParser, BetaParser;
options {tokenVocab=RegistryLexer;}

polymorphicDslAllRules: (mathematical_expression | helloWorld)+ ;