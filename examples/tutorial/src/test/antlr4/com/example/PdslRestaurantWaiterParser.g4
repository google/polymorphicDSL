parser grammar PdslRestaurantWaiterParser;

import RestaurantWaiterParser, RestaurantCustomerServiceParser;

options{tokenVocab=PdslRestaurantWaiterLexer; }

polymorphicDslSyntaxCheck: polymorphicDslAllRules+;
polymorphicDslAllRules: (customerServiceAllRules | waiterAllRules);