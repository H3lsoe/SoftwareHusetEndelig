Feature: Delete activity
  Description: A user deletes an activity
  Actors: User

  Scenario: A user delete an activity succesfully
    Given there is a project with project name "Project 1"
    And the user with initials "name" is the manager of the project
    And the project have an activity named "testing"
    When the user deletes the activity
    Then the activity named "testing" is deleted from the project

  Scenario: Deleting an activity when the user is not the projectmanager
    Given there is a project with project name "Project 1"
    And the user with initials "name" is not the manager of the project
    And the project have an activity named "testing"
    When the user deletes the activity
    Then the error message "Projectmanager is not registered" is given

  Scenario: Deleting an activity when the project contains zero activities
    Given there is a project with project name "Project 1"
    And the user with initials "name" is the manager of the project
    And the project contains zero activities
    When the user deletes the activity
    Then the error message "No activities available for deleting" is given

