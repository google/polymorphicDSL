lexer grammar RestaurantPorterLexer;

fragment WS: [ \t\n\r];
fragment END : WS* ([\r\n]+ | EOF) ;
fragment GHERKIN_STEP_KEYWORD: WS* ('Given' | 'When' | 'Then' | 'And' | 'But') ' ';

THEN_A_KITCHEN_PORTER_PREPARES_THE_INGREDIENTS:
 GHERKIN_STEP_KEYWORD 'a kitchen porter prepares the ingredients' END;

