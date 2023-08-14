Feature: View Currently Enrolled Classes test

  I want to view all the classes im currently enrolled in


Scenario: The user can see a list of classes that they are enrolled in
  Given The user is logged in
  When The user views the list of classes
  Then The user should see enrolled classes separate from other classes
  And The list should not contain pending classes
