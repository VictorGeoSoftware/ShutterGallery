Feature: First start
  Load app for first time, and go to Store view

  Scenario: Load app, ask to API for a first image list and show it up in the main view
    Given a user launch the app for first time
    When home screen is shown
    And image list is requested
    Then list is fulfilled


