Feature: Login Houston

  Scenario: Login Houston
    Given User Redirect to Houston
    When User input email
    And User Input Password
    And User Click Button Login
    Then User Redirect To Homepage