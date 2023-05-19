parser grammar RestaurantRecognizerParser;
import RestaurantCustomerParser, RestaurantCustomerServiceParser, RestaurantDishwasherParser,
  RestaurantKitchenParser,
  RestaurantReservationParser,
  RestaurantWaiterParser,
  RestaurantPorterParser,
  RestaurantStationChefParser;

options{tokenVocab=RestaurantRecognizerLexer;}
polymorphicDslSyntaxCheck: polymorphicDslAllRules+;
polymorphicDslAllRules: (customerAllRules
 | customerServiceAllRules
 | dishwasherAllRules
 | kitchenAllRules
 | reservationAllRules
 | porterAllRules
 | waiterAllRules
 | stationChefsAllRules);