Feature: Login Houston
  Scenario Outline: Login Houston
    Given User Open Browser And Go To Houston
    When User Input "<Username>" And "<Password>"
    And User Click Button Login
    Then Verify "<ExpectedResult>"

    Examples:
      |Username                 |Password     |ExpectedResult   |
      |fiky@gmail.com           |123123       |ERROR            |
      |fiky@gmail.com           |Usenobi123#  |ERROR            |
      |fiky.anggra@usenobi.com  |123123       |ERROR            |
      |fiky.anggra@usenobi.com  |Usenobi123#  |Coming Soon      |
