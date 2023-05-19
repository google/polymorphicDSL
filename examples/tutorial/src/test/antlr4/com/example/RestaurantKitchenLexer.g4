lexer grammar RestaurantKitchenLexer;

fragment WS: [ \t\n\r];
fragment END : WS* ([\r\n]+ | EOF) ;
fragment GHERKIN_STEP_KEYWORD: WS* ('Given' | 'When' | 'Then' | 'And' | 'But') ' ';

THEN_THE_ORDER_IS_SENT_TO_THE_KITCHEN: GHERKIN_STEP_KEYWORD 'the order is sent to the kitchen' END;
WHEN_THE_KITCHEN_BEGINS_WORKING_ON_THE_ORDER: GHERKIN_STEP_KEYWORD 'the kitchen begins working on the order' END;