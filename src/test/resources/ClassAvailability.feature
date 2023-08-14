Feature: Class Availability test

  I want to see if a potential class is available or if I will need waitlist.

Scenario: If there are seats available, a class should be marked as open on the classes list and the number of remaining seats should be displayed.
  Given The user is logged in
  And The user views the list of classes
  When A class is not full
  Then The class should be marked as open
  And The number of remaining seats should be displayed

Scenario: If there are no more seats for a class it is marked as full on the classes list and the user should be informed that they will be referred to the waiting list.
  Given The user is logged in
  And The user views the list of classes
  When A class is not full
  Then The class should be marked as full
  And The user is informed that they will be referred to the waiting list
