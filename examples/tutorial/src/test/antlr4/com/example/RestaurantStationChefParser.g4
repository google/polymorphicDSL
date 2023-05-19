parser grammar RestaurantStationChefParser;

options{tokenVocab=RestaurantStationChefLexer;}

thenTheStationChefsPrepare: THEN_STATION_CHEFS_PREPARE
  	(
  	  SOUP
    | APPETIZER
  	| ENTREE
  	| DESSERT
  	)
  	END_MEAL;

stationChefsAllRules: thenTheStationChefsPrepare;