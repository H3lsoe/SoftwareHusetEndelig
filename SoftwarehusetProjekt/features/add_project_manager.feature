Feature: Add Project manager
	Description: A project manager is added to the project
	Actors: user

Scenario: Add a project manager successfully
	Given there is a project with name "Software project" without a project manager
	And the user with name "Name" exist
 	When the user with name "Name" is added to the project "Software project"
 	Then the user with name "Name" is added as project manager to "Software project" successfully
 	
Scenario: Add a project manager failed
	Given there is a project with name "Software project" with a project manager
	And the user with name "Name" exist
	When the user with name "Name" is added to the project "Software project"
	Then the error message "project already has a project manager" is given

Scenario: Add a project manager failed
	Given there is a project with name "Software project" without a project manager
	And the user with name "Name" does not exist
	When the user with name "Name" is added to the project "Software project"
	Then the error message "Employee does not exist" is given
	

