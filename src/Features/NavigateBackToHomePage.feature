Feature: navigate back to home page
  Scenario: when entered a blogpost, go back to home page when Miniblog.Core is clicked
    Given navigated to a blogpost page
    When clicked on page title
    Then go back to homepage