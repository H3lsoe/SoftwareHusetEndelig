
Feature: Add an activity
	Description: An activity is added to the project
	Actors: Project manager, Employee

Scenario: Add an activity successfully
	Given there is a project with project name "Project 1"
 	And the user with initials "name" is the manager of the project
 	And the activity with the name "activity" is not in the project
 	When the activity is added to the project
 	Then the activity with title "activity" is added to the project

Scenario: Add an activity as a manager/employee of another project
	Given there is a project with project name "Project 1"
	And the user with initials "name" is not the manager of the project
	When the activity is added to the project
	Then the error message "Projectmanager is not registered" is given

Scenario: An activity that already exist within the project, is added to the project
	Given there is a project with project name "Project 1"
	And the user with initials "name" is not the manager of the project
	And the activity with the name "activity" is already added to the project
	When the activity is added to the project
	Then the error message "Activity already exists" is given

