parser grammar PdslRestaurantReservationParser;

import RestaurantReservationParser;

options {tokenVocab=RestaurantReservationLexer;}

polymorphicDslSyntaxCheck: polymorphicDslAllRules+;
polymorphicDslAllRules: reservationAllRules;