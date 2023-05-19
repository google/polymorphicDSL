parser grammar RestaurantWaiterParser;

options{tokenVocab=RestaurantWaiterLexer; }

givenWaiter: GIVEN_A_WAITER;
thenTheWaiterCleansTheTable: THEN_THE_WAITER_CLEANS_THE_TABLE;
waiterAllRules: givenWaiter | thenTheWaiterCleansTheTable;