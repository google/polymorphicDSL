lexer grammar RestaurantCustomerLexer;

//import RestaurantCommonLexer;

fragment WS: [ \t\n\r];
fragment END : WS* ([\r\n]+ | EOF) ;
fragment GHERKIN_STEP_KEYWORD: WS* ('Given' | 'When' | 'Then' | 'And' | 'But') ' ';

GIVEN_A_CUSTOMER: GHERKIN_STEP_KEYWORD 'a customer' END;
THEN_THE_CUSTOMER_EATS_THE: GHERKIN_STEP_KEYWORD 'the customer eats the ' -> mode(MEAL_MODE);
WHEN_THE_CUSTOMER_ORDERS: GHERKIN_STEP_KEYWORD 'the customer orders a ' -> mode(INTEGER_MODE);
THEN_THE_CUSTOMER_PAYS_THE_BILL: GHERKIN_STEP_KEYWORD 'the customer pays the bill' END;
THEN_THE_CUSTOMER_MAY_LEAVE_A_TIP: GHERKIN_STEP_KEYWORD 'the customer may leave a tip' END;

mode INTEGER_MODE;
	INT: [0-9]+;
	END_INTEGER: ' ' ->mode(DEFAULT_MODE);
mode MEAL_MODE;
	SOUP: 'soup';
	APPETIZER: 'appetizer';
	ENTREE: 'entrée';
	DESSERT: 'dessert';
	END_MEAL: END -> mode(DEFAULT_MODE);
