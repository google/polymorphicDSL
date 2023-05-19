parser grammar PdslRestaurantCustomerParser;

import RestaurantCustomerParser, RestaurantCustomerServiceParser;

options {tokenVocab=PdslRestaurantCustomerLexer;}
polymorphicDslSyntaxCheck: polymorphicDslAllRules+;
polymorphicDslAllRules: (customerAllRules | customerServiceAllRules);