Feature: Deleting from database
  Sometimes you want to delete some records from database

  Scenario Outline: Deleting one record
    Given I have category named <name> with id <id> in my database
      And I have <count> example categories in my database
    When I delete category with id <id>
    Then There should left <count> categories
      And There shouldn't be <id> in database

  Examples:
    | id | name | count |
    | 1 | Income | 3 |
    | 1 | Category 1 | 5 |