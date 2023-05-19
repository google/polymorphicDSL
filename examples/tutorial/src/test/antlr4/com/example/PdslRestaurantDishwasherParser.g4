parser grammar PdslRestaurantDishwasherParser;

import RestaurantDishwasherParser;

options{tokenVocab=RestaurantDishwasherLexer;}

polymorphicDslSyntaxCheck: dishwasherAllRules+;
polymorphicDslAllRules: dishwasherAllRules;