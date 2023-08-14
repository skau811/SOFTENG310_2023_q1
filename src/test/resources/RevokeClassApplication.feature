Feature: Revoke Class Application test

  I want to revoke any active applications i have for a class

  Scenario: The user should be able to revoke an application from their active concessions list
    Given The user is logged in
    And The user navigates to their classes list
    When The user has an active concession
    And The user clicks on Revoke application on a concession
    Then The concession is removed from the active concessions list
