Feature: Basic page information

  Scenario: Can see initial courses
    When The page is updated
    Then The section "CS 220" is scheduled on Monday at slot 1
     And The section "CS 220" is scheduled on Wednesday at slot 1
     And The section "CS 220" is scheduled on Friday at slot 1