Feature: Concession Outcome test

  I want to know the outcome of my concession, so that i know if i have been enrolled in a class or not.

Scenario: If the user has applied for a concession and it is approved the concession status should reflect this and the user should be enrolled in the class.
  Given The user is logged in as a Lecturer
  And The user navigates to their classes list
  When The user has an active concession
  And The active concession is approved
  Then The status of the concession should display approved

  Scenario: If the user has applied for a concession and it is declined the concession status should reflect this.
    Given The user is logged in as a Lecturer
    And The user navigates to their classes list
    When The user has an active concession
    And The active concession is declined
    Then The status of the concession should display declined