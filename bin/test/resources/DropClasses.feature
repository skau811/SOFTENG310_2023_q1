Feature: Drop classes test

  I want to drop any classes i no longer wish to be enrolled in.

Scenario: The user should be able to remove a class from their enrolled classes list
  Given The user is logged in
  And The user navigates to their classes list
  When The user has an enrolled class
  And The user clicks on Drop Class on an enrolled class
  Then The class is removed from the enrolled class list

Scenario: The user should be registered as a student in the class before it can be dropped
  Given The user is logged in as a Lecturer
  When The user navigates to their classes list
  Then The Drop Class button should not appear on list of enrolled classes
  And The Drop Class button should not appear on list of available classes

Scenario: The user should be registered as a student in the class before it can be dropped
  Given The user is logged in as a Staff
  When The user navigates to their classes list
  Then The Drop Class button should not appear on list of enrolled classes
  And The Drop Class button should not appear on list of available classes

Scenario: The dropped class should not be shown in the “currently enrolled classes” list
  Given The user is logged in
  And The user navigates to their classes list
  When The user has an enrolled class
  And The user clicks on Drop Class on an enrolled class
  Then The class is removed from the enrolled class list

Scenario: After a class is dropped a new slot should be opened in the class and a new student should be able to enroll
  Given The user is logged in
  And The user navigates to their classes list
  When The user has an enrolled class
  And The user clicks on Drop Class on an enrolled class
  Then The class is removed from the enrolled class list
  And The class is moved to the available class list
  And A new slot is opened in the class

Scenario: After a class is dropped, if the waitlist contains any students, the first student in the list should be enrolled.
  Given The user is logged in
  And The user navigates to their classes list
  When The user has an enrolled class
  And The user clicks on Drop Class on an enrolled class
  Then The class is removed from the enrolled class list
  And The class is moved to the available class list
  And A new slot is opened in the class
  And The number of waitlisted students decreases by 1


