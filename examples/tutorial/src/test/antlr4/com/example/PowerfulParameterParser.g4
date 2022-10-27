parser grammar PowerfulParameterParser;
options {tokenVocab=PowerfulParameterLexer;}
quotedParameter: GIVEN_THE_PARAMETER OPEN_QUOTE BODY CLOSE_QUOTE;
docstringParameter: GIVEN_THE_PARAMETER DOCSTRING_START DOCSTRING+ END_DOCSTRING;


polymorphicDslAllRules: (quotedParameter | docstringParameter)+;