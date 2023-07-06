lexer grammar FullGherkinLexer;

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

GIVEN_A_TABLE: GHERKIN_STEP_KEYWORD 'a table' WS* NL WS_NL* '|' WS* -> mode(DATA_TABLE_TEXT_MODE);
GIVEN_A_DOCSTRING: GHERKIN_STEP_KEYWORD 'a doc string' WS* NL WS* -> mode(DOC_STRING_MODE);
GIVEN_A_SOMETHING: GHERKIN_STEP_KEYWORD 'a ' .* END;
THEN_IT_IS: GHERKIN_STEP_KEYWORD 'it is ' .* END;
GIVEN_A_BACKGROUND: GHERKIN_STEP_KEYWORD ('a' | 'another') ' background step' END;

mode DATA_TABLE_MODE;
  END_ROW: WS* '|' NL WS* '|' -> mode(DATA_TABLE_TEXT_MODE);
  END_TABLE: WS* '|' END -> mode(DEFAULT_MODE);
  END_CELL: WS* '|' WS* -> mode(DATA_TABLE_TEXT_MODE);

mode DATA_TABLE_TEXT_MODE;
      fragment SPECIAL_ALPHA_NUMERIC: [a-zA-Z0-9.:;~!@#$%^&*()+\-\\] | '\n';
      CELL_TEXT: (SPECIAL_ALPHA_NUMERIC | WS)+ SPECIAL_ALPHA_NUMERIC -> mode(DATA_TABLE_MODE); // Match up until whitespace at the end of the cell

mode DOC_STRING_MODE;
    DOC_STRING: (
    ('"""' .*? '"""')
    | ('```' .*? '```')
    );
    END_DOC_STRING: END -> mode(DEFAULT_MODE);



