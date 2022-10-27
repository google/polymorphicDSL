parser grammar SimpleParameterParser;

options {tokenVocab=SimpleParameterLexer;}

// The context object will allow us to grab NUMBER directly (as well as the other tokens)
parameterExample: SENTENCE_PARAMETER NUMBER END;
multiExample: parameterExample+;