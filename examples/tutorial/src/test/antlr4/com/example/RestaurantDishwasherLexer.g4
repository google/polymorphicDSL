lexer grammar RestaurantDishwasherLexer;

fragment WS: [ \t\n\r];
fragment END : WS* ([\r\n]+ | EOF) ;
fragment GHERKIN_STEP_KEYWORD: WS* ('Given' | 'When' | 'Then' | 'And' | 'But') ' ';

THEN_A_DISHWASHER_CLEANS_THE_DISHES: GHERKIN_STEP_KEYWORD 'a dishwasher cleans the dishes' END;