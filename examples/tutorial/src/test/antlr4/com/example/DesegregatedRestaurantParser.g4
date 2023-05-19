parser grammar DesegregatedRestaurantParser;

options {tokenVocab=DesegregatedRestaurantLexer; }

givenCustomerHasReservation: GIVEN_THE_CUSTOMER_HAS_A_RESERVATION;
givenCustomer: GIVEN_A_CUSTOMER;
whenCustomerArrivesOnTime: WHEN_THE_CUSTOMER_ARRIVES_ON_TIME;
thenCustomerShownToTable: THEN_THEY_ARE_SHOWN_TO_A_TABLE;

givenWaiter: GIVEN_A_WAITER;
whenWaiterTakesTheCustomersOrder: WHEN_WAITER_TAKES_THE_CUSTOMERS_ORDER;
whenTheCustomerOrders: WHEN_THE_CUSTOMER_ORDERS INT END_INTEGER COURSE_MEAL;

thenOrderIsSentToKitchen: THEN_THE_ORDER_IS_SENT_TO_THE_KITCHEN;
whenTheKitchenBeginsWorkingOnTheOrder: WHEN_THE_KITCHEN_BEGINS_WORKING_ON_THE_ORDER;
whenPorterPreparesIngredients: WHEN_THE_PORTER_PREPARES_THE_INGREDIENTS;
whenStationChefMakes: WHEN_A_STATION_CHEF_MAKES (SOUP | APPETIZER | ENTREE | DESSERT) END_MEAL;
thenWaiterDeliversFoodToCustomer: THEN_THE_WAITER_DELIVERS_THE_FOOD_TO_THE_CUSTOMER;
thenCustomerEats: THEN_THE_CUSTOMER_EATS_THE (SOUP | APPETIZER | ENTREE | DESSERT) END_MEAL;
thenCustomerGivenBill: THEN_THE_CUSTOMER_IS_GIVEN_A_BILL;
thenCustomerPaysBill: THEN_THE_CUSTOMER_PAYS_THE_BILL;
thenCustomerMayLeaveTip: THEN_THE_CUSTOMER_MAY_LEAVE_A_TIP;
thenCustomerLeaves: THEN_THE_CUSTOMER_LEAVES;
thenWaiterCleansTable: THEN_THE_WAITER_CLEANS_THE_TABLE;
thenDishwasherCleansDishes: THEN_THE_DISHWASHER_CLEANS_THE_DISHES;

polymorphicDslAllRules: (
  givenCustomer
| givenCustomerHasReservation
| whenCustomerArrivesOnTime
| thenCustomerShownToTable

| givenWaiter
| whenWaiterTakesTheCustomersOrder
| whenTheCustomerOrders

| thenOrderIsSentToKitchen
| whenTheKitchenBeginsWorkingOnTheOrder
| whenPorterPreparesIngredients
| whenStationChefMakes
| thenWaiterDeliversFoodToCustomer
| thenCustomerEats
| thenCustomerGivenBill
| thenCustomerPaysBill
| thenCustomerMayLeaveTip
| thenCustomerLeaves
| thenWaiterCleansTable
| thenDishwasherCleansDishes
)+;