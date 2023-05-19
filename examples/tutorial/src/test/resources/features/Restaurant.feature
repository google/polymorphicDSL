Feature: Restaurant

  Scenario: Restaurant serving 4 course meal

    Given a customer
    And the customer has a reservation
    When the customer arrives on time
    Given a waiter
    Then they are shown to a table


    When the customer orders a 4 course meal
    And the waiter takes the customers order
    Then the order is sent to the kitchen

    When the kitchen begins working on the order
    Then a kitchen porter prepares the ingredients
    Then the station chefs prepare the soup
    And the station chefs prepare an appetizer
    And the station chefs prepare an entrée
    And the station chefs prepare a dessert
    Then the waiter delivers the food to the customer

    When the customer eats the soup
    And the customer eats the appetizer
    And the customer eats the entrée
    And the customer eats the dessert
    Then the customer is given a bill

    When the customer pays the bill
    Then the customer may leave a tip
    And the customer leaves
    Then the waiter cleans the table
    And a dishwasher cleans the dishes
