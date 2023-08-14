Feature: Class Schedule test

  I want to see the schedule/time-table of any potential class.

Scenario: The user can see the schedule/time-table of all classes in the available classes list
  Given The user is logged in
  When The user navigates to their classes list
  Then The classes in the available classes list shows the timetable for all classes
  And The classes in the available classes list shows an alternative timetable for classes with this set