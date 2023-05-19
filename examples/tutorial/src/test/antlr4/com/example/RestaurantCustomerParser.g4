parser grammar RestaurantCustomerParser;

options{tokenVocab=RestaurantCustomerLexer;}
givenCustomer: GIVEN_A_CUSTOMER;
thenCustomerEatsMeal: THEN_THE_CUSTOMER_EATS_THE (
  SOUP | APPETIZER | ENTREE | DESSERT)
  END_MEAL;
whenTheCustomerOrdersMeal: WHEN_THE_CUSTOMER_ORDERS INT END_INTEGER COURSE_MEAL;
thenTheCustomerPaysTheBill: THEN_THE_CUSTOMER_PAYS_THE_BILL;
thenTheCustomerMayLeaveTip: THEN_THE_CUSTOMER_MAY_LEAVE_A_TIP;

customerAllRules: givenCustomer |
  thenCustomerEatsMeal |
  thenTheCustomerPaysTheBill |
  thenTheCustomerMayLeaveTip |
  whenTheCustomerOrdersMeal;