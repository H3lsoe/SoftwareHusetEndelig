Feature: Delete project
  Descriptions: project manager deletes project
  Actors: user

  Scenario: A user deletes project successfully
    Given there is a project with project name "Project 1"
    And the user with initials "name" is the manager of the project
    When the user removes the project
    Then the project is removed

  Scenario: A user deletes project when project doesn't exist
    Given there isn't a project with project name "Project 1"
    When the user removes the project
    Then the error message "project doesn't exist" is given

  Scenario: A user deletes project when the user is not the project manager
    Given there is a project with project name "Project 1"
    And the user with initials "name" is not the manager of the project
    When the user removes the project
    Then the error message "Projectmanager is not registered" is given
