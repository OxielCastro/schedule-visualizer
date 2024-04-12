Feature: Basic page information

  Scenario: Can see initial courses
    Given A blank schedule
     And A section with prefix "CS" number "220" and description "Fundamentals"
    When The page is updated
    Then The section "CS 220" is scheduled on Monday at slot 1
     And The section "CS 220" is scheduled on Wednesday at slot 1
     And The section "CS 220" is scheduled on Friday at slot 1