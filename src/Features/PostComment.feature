Feature: post a comment on blogpost
  Scenario: post a comment and your name on a blogpost
    Given navigated to the blog page
    When entered a comment
    And entered your name
    And entered your email
    Then comment posted successfully