Feature: edit blog post
  Scenario: if the "Edit" button is clicked, successfully change the contents of the blogpost
    Given entered the login page
    When logged in with correct username and password
    And clicked on the Edit button
    And changed the contents of the blogpost
    Then blogpost edited successfully