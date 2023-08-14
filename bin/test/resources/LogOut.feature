Feature: Log out test

  I want to log out of the system.

  Scenario: If the log out button is pressed the user is logged out of the system and sent to the log in page
    Given The user is logged in
    When The user clicks the log out button
    Then The user is logged out and navigated to the log in page