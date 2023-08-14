Feature: View Classes test

  I want to view all classes available.

Scenario: The user should be able to filter the list of available classes according to discipline.
  Given The user is logged in
  When The user navigates to their classes list
  And The user filters the list of available classes to OPSMGT
  Then Only OPSMGT classes are displayed in the list of available classes

