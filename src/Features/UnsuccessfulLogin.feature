Feature: unsuccessful login
  Scenario: fail to login when entered wrong username or password
    Given navigated to the login page
    When entered wrong username or password
    Then display error message