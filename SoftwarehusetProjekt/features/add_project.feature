Feature: Add Project
	Description: A user adds a project
	Actors: user

Scenario: Add a project successfully
	Given the project with name "Software project" does not exist
	When the project with name "Software project" is added
 	Then the project is contained in PMA with name "Software project"
 	
Scenario: Add an existing project
	Given the project with name "Software project" exist
	When the project with name "Software project" is added
	Then the error message "project already exists" is given
	

