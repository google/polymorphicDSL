parser grammar MyFirstParser;

options{tokenVocab=MyFirstLexer;}

givenUserHasSpecifiedPickles: GIVEN_I_HAVE NUMBER PICKLES;
thenUserHasMoreThanSpecifiedPickles: THEN_I_HAVE_MORE_THAN NUMBER PICKLES;

polymorphicDslAllRules: (givenUserHasSpecifiedPickles | thenUserHasMoreThanSpecifiedPickles);
