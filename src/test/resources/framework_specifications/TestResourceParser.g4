parser grammar TestResourceParser ;

import GherkinCommonParser ;

options {tokenVocab=TestResourceLexer ;}

givenTheTestResource :  GIVEN_THE_TEST_RESOURCE textInDoubleQuotes END;
givenTheRawResource : GIVEN_THE_FOLLOWING_TEST_RESOURCE docstring ;
