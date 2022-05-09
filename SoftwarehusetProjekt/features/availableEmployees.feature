Feature: Available employees
  Descriptions: A project manager wants to see Available employees
  Actors: user


  Scenario: A user request to see available employees successfully
    Given there is a project with project name "Project 1"
    And the user with initials "name" is the manager of the project
    When the user request to see available employees
    Then there is given a list with all names of available employees


  Scenario: request to see available employees when the user is not the project manager
    Given there is a project with project name "Project 1"
    And the user with initials "name" is not the manager of the project
    When the user request to see available employees
    Then the error message "Projectmanager is not registered" is given
