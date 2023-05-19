lexer grammar RestaurantReservationLexer;

fragment WS: [ \t\n\r];
fragment END : WS* ([\r\n]+ | EOF) ;
fragment GHERKIN_STEP_KEYWORD: WS* ('Given' | 'When' | 'Then' | 'And' | 'But') ' ';

GIVEN_THE_CUSTOMER_HAS_RESERVATION: GHERKIN_STEP_KEYWORD 'the customer has a reservation' END;
WHEN_THE_CUSTOMER_ARRIVES_ON_TIME: GHERKIN_STEP_KEYWORD 'the customer arrives on time' END;