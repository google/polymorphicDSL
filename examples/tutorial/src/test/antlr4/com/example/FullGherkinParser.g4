parser grammar FullGherkinParser;

options {tokenVocab=FullGherkinLexer;}

givenTable: GIVEN_A_TABLE dataTable;
givenDocstring: GIVEN_A_DOCSTRING DOC_STRING;
givenSomething: GIVEN_A_SOMETHING;
thenItIs: THEN_IT_IS;
givenBackground: GIVEN_A_BACKGROUND;
dataTable: row+;
row: cell+;
cell: CELL_TEXT (END_CELL | END_ROW | END_TABLE);

polymorphicDslSyntaxCheck: polymorphicDslAllRules+;
polymorphicDslAllRules: givenTable | givenDocstring | givenSomething | thenItIs | givenBackground;