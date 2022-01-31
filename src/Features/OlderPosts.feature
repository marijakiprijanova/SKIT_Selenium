Feature: navigate to older posts on blog
  Scenario: when opening the homepage, navigate to the previous blogposts by clicking the "Older" button
    Given navigated to the homepage
    When clicked on the older button
    Then go to the previous page of blogposts