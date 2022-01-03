Feature: Sign out of blog
  Scenario: if signed in, sign out by clicking on the sign out button at the bottom
    Given navigated to the login site
    When logged in with the correct credentials
    And clicked the sign out button in the bottom
    Then log out successfully
