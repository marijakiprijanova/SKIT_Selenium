Feature: successful login
  Scenario: login successfully when entered the correct username and password
    Given navigated to the sign in page
    When entered the correct username and password
    Then successfully sign in