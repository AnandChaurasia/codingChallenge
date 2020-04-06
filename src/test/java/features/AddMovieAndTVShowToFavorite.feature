@Ready
Feature: Adding Movies and TV-Shows in favorite list


  Background: Launch Movies Catalogue screen
    Given The app is launched


  Scenario:User successfully adds a movie from movie catalogue in favorite list
    Given User is on 'Movie Catalogue' page
    When User selects a item from catalogue page
    And User clicks on the 'ADD TO FAVORITE' button
    Then Selected movie is successfully added to Favorite list

  Scenario: User successfully deletes a movie from 'favorite Movie' list
    Given User is on 'Favorite Movie' page
    When User selects item from favorite list
    And User clicks on the 'UNFAVORITE' button
    Then Selected movie is successfully deleted from Favorite Movie list


  Scenario:User successfully adds a TV Show from TV Show catalogue in favorite list
    Given User is on 'TV Show Catalogue' page
    When User selects a item from catalogue page
    And User clicks on the 'ADD TO FAVORITE' button
    Then Selected TV show is successfully added to Favorite list


  Scenario: User successfully deletes a TV Show from 'Favorite TV Show' list
    Given User is on 'Favorite TV Show' page
    When User selects item from favorite list
    And User clicks on the 'UNFAVORITE' button
    Then Selected TV Show is successfully deleted from Favorite TV Show list
