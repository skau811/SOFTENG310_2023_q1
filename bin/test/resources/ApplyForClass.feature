Feature: Apply for class test

  I want to be able to apply for a class i'm eligible for.

  Scenario: The user should be able to apply for a class they are eligible for
    Given The user is logged in
    And User is in academic profile page
    When The user views the list of classes
    Then User enrols in a class
    Then User can see this class in their class list