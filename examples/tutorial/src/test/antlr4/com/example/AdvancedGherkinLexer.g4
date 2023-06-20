lexer grammar AdvancedGherkinLexer;
fragment AND: WS* 'And ';
fragment GIVEN: WS* 'Given ' | AND;
fragment WHEN: WS* 'When ' | AND;
fragment THEN: WS* 'Then ' | AND;
fragment BUT: WS* 'But ' | AND;
fragment WILD: WS* '* ' | AND;
fragment GHERKIN_STEP_KEYWORD: GIVEN | THEN | WHEN | BUT | WILD;
fragment WS: [ \t];
fragment NL: [\r\n];
fragment WS_NL: WS | NL;
fragment END: WS_NL* EOF?;
GIVEN_AN_EXAMPLE_OF_NEGATIVE_SPACE: GHERKIN_STEP_KEYWORD 'an example of negative space' WS* NL WS_NL* '|' WS* -> mode(DATA_TABLE_TEXT_MODE);
GIVEN_N_LINES: GIVEN -> mode(INT_MODE);
LINES_OF_POETRY_ON: 'lines of poetry on ' -> mode(INT_MODE);
LINES: 'lines' WS* NL WS_NL* '|' -> mode(DATA_TABLE_TEXT_MODE);

mode INT_MODE;
INT: ([1-9][0-9]*) | '0'; //Integers cannot start with 0 unless they are precisely 0
END_INT: ' ' -> mode(DEFAULT_MODE);

mode DATA_TABLE_MODE;
  END_ROW: WS* '|' NL WS* '|' -> mode(DATA_TABLE_TEXT_MODE);
  END_TABLE: WS* '|' END -> mode(DEFAULT_MODE);
  END_CELL: WS* '|' WS* -> mode(DATA_TABLE_TEXT_MODE);

mode DATA_TABLE_TEXT_MODE;
      fragment SPECIAL_ALPHA_NUMERIC: [a-zA-Z0-9.:;~!@#$%^&*()+\-\\] | '\n';
      CELL_TEXT: (SPECIAL_ALPHA_NUMERIC | WS)+ SPECIAL_ALPHA_NUMERIC -> mode(DATA_TABLE_MODE); // Match up until whitespace at the end of the cell

