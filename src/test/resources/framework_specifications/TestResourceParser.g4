parser grammar TestResourceParser ;

import GherkinCommonParser ;

options {tokenVocab=TestResourceLexer ;}

givenTheTestResource :  GIVEN_THE_TEST_RESOURCE textInDoubleQuotes END_QUOTE;
givenTheRawResource : GIVEN_THE_FOLLOWING_TEST_RESOURCE docstring ;
