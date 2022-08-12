parser grammar TestResourceParser ;


options {tokenVocab=TestResourceLexer ;}

docstring: DOCSTRING_DOUBLE_QUOTES;
givenTheTestResource :  GIVEN_THE_TEST_RESOURCE ;
givenTheRawResource : GIVEN_THE_FOLLOWING_TEST_RESOURCE ;
