Feature: add a new blog post
  Scenario: when logged in, add a new blog post when navigated to the "New post" page
    Given navigated to the log in page
    When logged in successfully
    And entered the new post page and filled out title and excerpt
    And clicked the submit button
    Then error message is displayed