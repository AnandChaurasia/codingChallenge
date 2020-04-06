@Ready
Feature: Verify content fields of Movies and TV-Shows detail page

  Background: Launch Movies Catalogue screen
    Given The app is launched

  Scenario:User selects a movie from movie catalogue to check contents
    Given User is on 'Movie Catalogue' page
    When User selects poster "Bloodshot"
    Then User Verifies title of selected item
    And User checks rating of selected item
    And User checks release date of selected item
    And User check movie summary is displayed

  Scenario:User selects a TV Show from TV Show catalogue to check contents
    Given User is on 'TV Show Catalogue' page
    When User selects poster "Siren"
    Then User Verifies title of selected item
    And User checks rating of selected item
    And User check TV Show summary is displayed
