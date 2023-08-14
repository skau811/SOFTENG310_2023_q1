Feature: LogInFeature

  I want to log in to the system.

Scenario: If a correct combination of user information is entered the user gets a level of access to the system, according to his/her user-rights and is sent to their Academic profile
  Given User navigates to the Login page
  When User logs in with valid username: {string} and valid password: {string}
  And User clicks on Login button
  Then The user is sent to to their Academic profile

  Scenario: If an invalid combination of user information is entered the user is denied access
    Given User navigates to the Login page
    When User logs in with invalid username: {string} and invalid password: {string}
    And User clicks on Login button
    Then The user is not logged in and remains on the login page