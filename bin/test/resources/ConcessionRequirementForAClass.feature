Feature: Concession Requirements for a Class

  I want to see the concession requirements for any potential class.

Scenario: The user should be able to view concession requirements for classes they can enrol in
  Given The user is logged in
  And User is in academic profile page
  When The user views the list of classes
  Then User can see concessions