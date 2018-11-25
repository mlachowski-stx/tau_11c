Feature: Deleting from database
  Sometimes you want to delete some records from database

  Scenario: Deleting some records
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