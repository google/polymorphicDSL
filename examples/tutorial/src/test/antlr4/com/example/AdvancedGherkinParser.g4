parser grammar AdvancedGherkinParser;

options{tokenVocab=AdvancedGherkinLexer;}

givenExampleOfNegativeSpace: GIVEN_AN_EXAMPLE_OF_NEGATIVE_SPACE dataTable;
givenLinesOfPoetry: GIVEN_N_LINES INT END_INT LINES_OF_POETRY_ON INT END_INT LINES dataTable;

dataTable: row+;
row: cell+;
cell: CELL_TEXT (END_CELL | END_ROW | END_TABLE);
advancedGherkinAllRules: givenLinesOfPoetry | givenExampleOfNegativeSpace;