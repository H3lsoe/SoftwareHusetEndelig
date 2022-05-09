
Feature: Edit activity
	Description: An activity in the project is edited
	Actors: Project manager, Employee

Scenario: Edit the name of an activity successfully
	Given there is a project with project name "Project 1"
	And the user with initials "name" is the manager of the project
	And the project have an activity named "testing"
	And there is a name "testing 2"
	When the name is edited
	Then the name of the activity is "testing 2"

Scenario: Edit the employee working on the activity successfully
	Given there is a project with project name "Project 1"
	And the user with initials "name" is the manager of the project
	And the project have an activity named "testing"
	And an employee with the initials "name 2" is assigned to the activity
	When the employee with the initials "name2" is replaced by the employee with the initials "name3"
	Then the activity has the employee with the initials "name3"

Scenario: Edit the name of an activity without being project manager
	Given there is a project with project name "Project 1"
	And the user with initials "name" is not the manager of the project
	And the project have an activity named "testing"
	When the name of the activity is edited to "spillMilk"
	Then the error message "Projectmanager is not registered" is given

Scenario: Edit the employee working on the activity without being project manager
	Given there is a project with project name "Project 1"
	And the user with initials "name" is not the manager of the project
	And the project have an activity named "testing"
	And an employee with the initials "name 2" is assigned to the activity
	When the employee with the initials "name2" is replaced by the employee with the initials "name3"
	Then the error message "Projectmanager is not registered" is given