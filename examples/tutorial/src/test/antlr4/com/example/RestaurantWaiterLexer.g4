lexer grammar RestaurantWaiterLexer;

fragment WS: [ \t\n\r];
fragment END : WS* ([\r\n]+ | EOF) ;
fragment GHERKIN_STEP_KEYWORD: WS* ('Given' | 'When' | 'Then' | 'And' | 'But') ' ';

GIVEN_A_WAITER: GHERKIN_STEP_KEYWORD 'a waiter' END;
THEN_THE_WAITER_CLEANS_THE_TABLE: GHERKIN_STEP_KEYWORD 'the waiter cleans the table' END;
