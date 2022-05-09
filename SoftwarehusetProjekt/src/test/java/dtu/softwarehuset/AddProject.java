package dtu.softwarehuset;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import system.app.Activity;
import system.app.Employee;
import system.app.PMA;
import system.app.Project;
public class AddProject {

    Employee employee;
    TestData testData;
    Activity activity;
    ErrorMessageHolder errorMessageHolder;

    public AddProject(TestData testData, ErrorMessageHolder errorMessageHolder) {
        this.testData = testData;
        this.testData.pma = new PMA();
        this.testData.errorMessageHolder = errorMessageHolder;
    }

    @Given("the project with name {string} does not exist")
    public void the_project_with_name_does_not_exist(String string) {
        assertFalse(testData.pma.existProjectName(string));
    }

    @When("the project with name {string} is added")
    public void the_project_with_name_is_added(String string) {
        testData.pma.addProject(new Project(string));
    }

    @Then("the project is contained in PMA with name {string}")
    public void the_project_is_contained_in_pma_with_name(String string) {
        assertTrue(testData.pma.existProjectName(string));
    }

    @Given("the project with name {string} exist")
    public void the_project_with_name_exist(String string) {
        assertFalse(testData.pma.existProjectName(string));
        testData.errorMessageHolder.setErrorMessage("project already exists");
    }

}
