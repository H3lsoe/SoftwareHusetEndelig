/*
 *  Andreas Sjögren Fürst - s201189
 *
 *  */

package dtu.softwarehuset;

import Exceptions.OperationNotAllowed;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import system.app.Activity;
import system.app.Employee;
import system.app.PMA;
import system.app.Project;

import static org.junit.Assert.*;

public class AddProjectManager {

    Employee employee;
    TestData testData;
    Activity activity;
    ErrorMessageHolder errorMessageHolder;

    public AddProjectManager(TestData testData, ErrorMessageHolder errorMessageHolder) {
        this.testData = testData;
        this.testData.pma = new PMA();
        this.testData.errorMessageHolder = errorMessageHolder;
    }

    @Given("there is a project with name {string} without a project manager")
    public void the_project_with_name_does_not_contain_a_project_manager(String string) {
        testData.pma.projects.add(new Project(string));
        assertFalse(testData.pma.getProject(string).hasProjectManager());
    }

    @Given("the user with name {string} exist")
    public void the_user_with_name_exist(String string) {
        Employee employee = new Employee(string);
        testData.pma.addEmployee(employee);
        assertTrue(testData.pma.employeeExist(string));
    }

    @Given("there is a project with name {string} with a project manager")
    public void there_is_a_project_with_name_with_a_project_manager(String string) throws OperationNotAllowed {
        testData.employee = new Employee("testName");
        testData.pma.projects.add(new Project(string));
        testData.pma.getProject(string).setProjectManager(testData.employee);
        assertTrue(testData.pma.getProject(string).hasProjectManager());
        testData.errorMessageHolder.setErrorMessage("project already has a project manager");
    }
    @Given("the user with name {string} does not exist")
    public void the_user_with_name_does_not_exist(String string) {
        assertFalse(testData.pma.employeeExist(string));
        testData.errorMessageHolder.setErrorMessage("Employee does not exist");
    }

    @When("the user with name {string} is added to the project {string}")
    public void the_user_with_name_is_added_to_the_project(String string, String string2) throws OperationNotAllowed {
        testData.employee = new Employee(string);
        try {
            testData.pma.getProject(string2).setProjectManager(testData.employee);
        } catch (OperationNotAllowed e) {
            testData.errorMessageHolder.setErrorMessage(e.getMessage());
        }

    }

    @Then("the user with name {string} is added as project manager to {string} successfully")
    public void the_user_with_name_is_added_as_project_manager_to_successfully(String string, String string2) {
        assertEquals(testData.pma.getProject(string2).getProjectManager().employeeId, string);
    }
}
