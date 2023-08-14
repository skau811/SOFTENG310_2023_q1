Feature: Concession status test

  I want to see the concession status of any active concession applications i have.

  Scenario: The user should be able to see concession status in available classes
    Given The user is logged in
    And User is in academic profile page
    When The user views the list of classes
    Then User is navigated to the view classes list
    And User can see concession status