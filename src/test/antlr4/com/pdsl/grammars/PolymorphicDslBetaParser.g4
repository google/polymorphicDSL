parser grammar PolymorphicDslBetaParser;

options { tokenVocab=BetaLexer; } 

import BetaParser;

polymorphicDslAllRules : helloWorld ;
