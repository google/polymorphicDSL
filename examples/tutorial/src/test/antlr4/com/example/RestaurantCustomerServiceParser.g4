parser grammar RestaurantCustomerServiceParser;

options{tokenVocab=RestaurantCustomerServiceLexer;}

thenTheyAreShownToTable: THEN_THEY_ARE_SHOWN_TO_A_TABLE;
whenWaiterTakesTheCustomerOrder: WHEN_WAITER_TAKES_THE_CUSTOMERS_ORDER;

thenTheWaiterDeliversTheFoodToTheCustomer: THEN_THE_WAITER_DELIVERS_THE_FOOD_TO_THE_CUSTOMER;
thenTheCustomerIsGivenBill: THEN_THE_CUSTOMER_IS_GIVEN_A_BILL;

thenTheCustomerLeaves: THEN_THE_CUSTOMER_LEAVES;

customerServiceAllRules: (
  thenTheyAreShownToTable |
  whenWaiterTakesTheCustomerOrder |

  thenTheWaiterDeliversTheFoodToTheCustomer |
  thenTheCustomerIsGivenBill |
  thenTheCustomerLeaves
);