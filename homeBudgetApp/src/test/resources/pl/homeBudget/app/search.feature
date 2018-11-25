Feature: Searching in database
  Sometimes you want to find some record in database

  Scenario Outline: Returned correct records
    Given I have the following categories names:
      | abc |
      | abbbccc |
      | abc2 |
      | sixcha |
    When I search pattern <pattern>
    Then I should get <count> records

  Examples:
    | pattern | count |
    | [a-zA-Z]{3} | 1 |
    | [a-zA-Z0-9]{4,6} | 2 |