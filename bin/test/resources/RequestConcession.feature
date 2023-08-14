Feature: Request Concession test

  I want to request a concession for a course i do not meet the prerequisites for.

  Scenario: The user should be able to request a concession from the available classes list
    Given The user is logged in
    And User is in academic profile page
    When The user views the list of classes
    And User can click request concession
