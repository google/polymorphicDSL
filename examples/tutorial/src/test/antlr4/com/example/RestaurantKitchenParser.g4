parser grammar RestaurantKitchenParser;

options{tokenVocab=RestaurantKitchenLexer;}

thenTheOrderIsSentToTheKitchen: THEN_THE_ORDER_IS_SENT_TO_THE_KITCHEN;
whenTheKitchenBeginsWorkingOnTheOrder: WHEN_THE_KITCHEN_BEGINS_WORKING_ON_THE_ORDER;

kitchenAllRules: thenTheOrderIsSentToTheKitchen | whenTheKitchenBeginsWorkingOnTheOrder;