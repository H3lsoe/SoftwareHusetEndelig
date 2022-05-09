/*
 *  Rasmus Søndergaard - s214925
 *  */

package dtu.softwarehuset;

import Exceptions.OperationNotAllowed;
import system.app.Activity;
import system.app.Employee;
import system.app.PMA;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StaffActivity {

    Employee employee;
    TestData testData;
    Activity activity;
    ErrorMessageHolder errorMessageHolder;

    public StaffActivity(TestData testData, ErrorMessageHolder errorMessageHolder) {
        this.testData = testData;
        this.testData.pma = new PMA();
        this.errorMessageHolder = errorMessageHolder;
    }

    @Given("the activity named {string} is not staffed")
    public void the_activity_named_is_not_staffed(String string) {
        assertFalse(testData.project.getActivity(string).isActivityStaffed());
    }

    @Given("the given employee named {string} is available")
    public void the_given_employee_named_is_available(String string) throws OperationNotAllowed {
        Employee worker = new Employee(string);
        testData.pma.addEmployee(worker);
        assertTrue(testData.pma.isEmployeeAvailable(string));
    }

    @When("the user {string} staffs the employee {string} to the activity {string}")
    public void the_user_staffs_the_employee_to_the_activity(String string, String string2, String string3) {
        if(testData.project.getProjectManager() == null || !testData.project.getProjectManager().employeeId.equals(string)) {
            this.errorMessageHolder.setErrorMessage("Projectmanager is not registered");
        } else {
            if(testData.pma.getEmployee(string2) != null) {
                Activity activity = testData.project.getActivity(string3);
                activity.staffActivity(testData.pma.getEmployee(string2));
                testData.project.addActivity(activity);
            }
            try {
                testData.pma.isEmployeeAvailable(string2);
            } catch (OperationNotAllowed e) {
                this.errorMessageHolder.setErrorMessage(e.getMessage());
            }
        }
    }


    @Then("the employee {string} is staffed to the activity {string}")
    public void the_employee_is_staffed_to_the_activity(String string, String string2) {
        assertEquals(string, testData.project.getActivity(string2).getEmployeeId());
    }

    @Given("the given employee named {string} is not available")
    public void the_given_employee_named_is_not_available(String string) throws OperationNotAllowed {
        try {
            assertFalse(testData.pma.isEmployeeAvailable(string));
        } catch (OperationNotAllowed e) {
            assertEquals(e.getMessage(), "Employee is not available");
        }

    }

    @Given("the activity named {string} is already staffed")
    public void the_activity_named_is_already_staffed(String string) {
        Employee employee = new Employee("test");
        testData.project.getActivity(string).staffActivity(employee);
        assertTrue(testData.project.getActivity(string).isActivityStaffed());
        this.errorMessageHolder.setErrorMessage("Activity is already staffed");
    }
}