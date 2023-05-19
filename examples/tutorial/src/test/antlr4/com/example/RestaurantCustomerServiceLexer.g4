lexer grammar RestaurantCustomerServiceLexer;

// Behavior related to waiter/customer interactions
fragment WS: [ \t\n\r];
fragment END : WS* ([\r\n]+ | EOF) ;
fragment GHERKIN_STEP_KEYWORD: WS* ('Given' | 'When' | 'Then' | 'And' | 'But') ' ';

THEN_THEY_ARE_SHOWN_TO_A_TABLE: GHERKIN_STEP_KEYWORD 'they are shown to a table' END;
WHEN_WAITER_TAKES_THE_CUSTOMERS_ORDER: GHERKIN_STEP_KEYWORD 'the waiter takes the customers order' END;

COURSE_MEAL: 'course meal' END;
THEN_THE_WAITER_DELIVERS_THE_FOOD_TO_THE_CUSTOMER: GHERKIN_STEP_KEYWORD 'the waiter delivers the food to the customer' END;
THEN_THE_CUSTOMER_IS_GIVEN_A_BILL:  GHERKIN_STEP_KEYWORD 'the customer is given a bill' END;

THEN_THE_CUSTOMER_LEAVES: GHERKIN_STEP_KEYWORD 'the customer leaves' END;
