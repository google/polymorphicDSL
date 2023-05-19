lexer grammar DesegregatedRestaurantLexer;

fragment WS: [ \t\n\r];
fragment END : WS* ([\r\n]+ | EOF) ;
fragment GHERKIN_STEP_KEYWORD: WS* ('Given' | 'When' | 'Then' | 'And' | 'But') ' ';

GIVEN_THE_CUSTOMER_HAS_A_RESERVATION: GHERKIN_STEP_KEYWORD 'the customer has a reservation' END;
GIVEN_A_CUSTOMER: GHERKIN_STEP_KEYWORD 'a customer' END;
WHEN_THE_CUSTOMER_ARRIVES_ON_TIME: GHERKIN_STEP_KEYWORD 'the customer arrives on time' END;
THEN_THEY_ARE_SHOWN_TO_A_TABLE: GHERKIN_STEP_KEYWORD 'they are shown to a table' END;

GIVEN_A_WAITER: GHERKIN_STEP_KEYWORD 'a waiter' END;
WHEN_WAITER_TAKES_THE_CUSTOMERS_ORDER: GHERKIN_STEP_KEYWORD 'the waiter takes the customers order' END;
WHEN_THE_CUSTOMER_ORDERS: GHERKIN_STEP_KEYWORD 'the customer orders a ' -> mode(INTEGER_MODE);
COURSE_MEAL: 'course meal' END; 

THEN_THE_ORDER_IS_SENT_TO_THE_KITCHEN: GHERKIN_STEP_KEYWORD 'the order is sent to the kitchen' END;

WHEN_THE_KITCHEN_BEGINS_WORKING_ON_THE_ORDER: GHERKIN_STEP_KEYWORD 'the kitchen begins working on the order' END;
WHEN_THE_PORTER_PREPARES_THE_INGREDIENTS: GHERKIN_STEP_KEYWORD 'a kitchen porter prepares the ingredients' END;
WHEN_A_STATION_CHEF_MAKES: GHERKIN_STEP_KEYWORD 'the station chefs prepare ' ('an' | 'a' | 'the')? ' ' -> mode(MEAL_MODE);
THEN_THE_WAITER_DELIVERS_THE_FOOD_TO_THE_CUSTOMER: GHERKIN_STEP_KEYWORD 'the waiter delivers the food to the customer' END;

THEN_THE_CUSTOMER_EATS_THE: GHERKIN_STEP_KEYWORD 'the customer eats the ' -> mode(MEAL_MODE);
THEN_THE_CUSTOMER_IS_GIVEN_A_BILL:  GHERKIN_STEP_KEYWORD 'the customer is given a bill' END;
THEN_THE_CUSTOMER_PAYS_THE_BILL: GHERKIN_STEP_KEYWORD 'the customer pays the bill' END;
THEN_THE_CUSTOMER_MAY_LEAVE_A_TIP: GHERKIN_STEP_KEYWORD 'the customer may leave a tip' END;
THEN_THE_CUSTOMER_LEAVES: GHERKIN_STEP_KEYWORD 'the customer leaves' END;
THEN_THE_WAITER_CLEANS_THE_TABLE: GHERKIN_STEP_KEYWORD 'the waiter cleans the table' END;
THEN_THE_DISHWASHER_CLEANS_THE_DISHES: GHERKIN_STEP_KEYWORD 'a dishwasher cleans the dishes' END;

mode INTEGER_MODE;
	INT: [0-9]+;
	END_INTEGER: ' ' ->mode(DEFAULT_MODE);

mode MEAL_MODE;
	SOUP: 'soup';
	APPETIZER: 'appetizer';
	ENTREE: 'entrée';
	DESSERT: 'dessert';
	END_MEAL: END -> mode(DEFAULT_MODE);

