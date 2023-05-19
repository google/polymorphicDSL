lexer grammar RestaurantCommonLexer;

fragment WS: [ \t\n\r];
END : WS* ([\r\n]+ | EOF) ;
GHERKIN_STEP_KEYWORD: WS* ('Given' | 'When' | 'Then' | 'And' | 'But') ' ';