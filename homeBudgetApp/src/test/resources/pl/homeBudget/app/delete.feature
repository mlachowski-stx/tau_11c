Feature: Deleting from database
  Sometimes you want to delete some records from database

  Scenario Outline: Deleting one record
    Given The following categories:
      | 1 |
      | 2 |
      | 3 |
      | 4 |
    When I delete following categories:
      | 2 |
      | 4 |
    Then There should left 2 categories
      And There shouldn't be following categories in database:
        | 2 |
        | 4 |

  Examples:
    | id | name | count |
    | 1 | Income | 3 |
    | 1 | Category 1 | 5 |