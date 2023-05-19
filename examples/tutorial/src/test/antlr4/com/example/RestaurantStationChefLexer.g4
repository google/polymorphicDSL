lexer grammar RestaurantStationChefLexer;

fragment WS: [ \t\n\r];
fragment END : WS* ([\r\n]+ | EOF) ;
fragment GHERKIN_STEP_KEYWORD: WS* ('Given' | 'When' | 'Then' | 'And' | 'But') ' ';

THEN_STATION_CHEFS_PREPARE: GHERKIN_STEP_KEYWORD 'the station chefs prepare ' ('the'| 'an' | 'a') ' ' -> mode(MEAL_MODE);

mode MEAL_MODE;
	SOUP: 'soup';
	APPETIZER: 'appetizer';
	ENTREE: 'entrée';
	DESSERT: 'dessert';
	END_MEAL: END -> mode(DEFAULT_MODE);

