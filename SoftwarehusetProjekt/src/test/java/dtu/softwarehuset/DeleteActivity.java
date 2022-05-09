/*
 *  Rasmus Søndergaard - s214925
 *  */

package dtu.softwarehuset;

import Exceptions.OperationNotAllowed;
import system.app.Employee;
import system.app.PMA;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import system.app.*;

public class DeleteActivity {

    Employee employee;
    TestData testData;
    Activity activity;
    ErrorMessageHolder errorMessageHolder;

    public DeleteActivity(TestData testData, ErrorMessageHolder errorMessageHolder) {
        this.testData = testData;
        this.testData.pma = new PMA();
        this.errorMessageHolder = errorMessageHolder;
    }

    @Given("the project have an activity named {string}")
    public void the_project_have_an_activity_named(String string) {
        testData.activity = new Activity(string);
        testData.project.addActivity(testData.activity);
        assertTrue(testData.project.hasActivity(testData.activity));
    }

    @When("the user deletes the activity")
    public void the_user_deletes_the_activity() {
        testData.project.removeActivity(testData.activity);
    }

    @Then("the activity named {string} is deleted from the project")
    public void the_activity_named_is_deleted_from_the_project(String string) {
        assertFalse(testData.project.nameExistActivity(string));
    }

    @Given("the project contains zero activities")
    public void the_project_contains_zero_activities() {
        try {
            assertFalse(testData.project.projectContainsActivities());
        } catch (OperationNotAllowed e) {
            this.errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
}
