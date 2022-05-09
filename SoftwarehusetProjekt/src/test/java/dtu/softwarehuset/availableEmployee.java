/*
 *
 *  Oscar Wullf Mandrupsen - s216163
 *
 *  */

package dtu.softwarehuset;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import system.app.Activity;
import system.app.Employee;
import system.app.PMA;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class availableEmployee {
    Employee employee;
    TestData testData;
    Activity activity;

    public availableEmployee(TestData testData) {
        this.testData = testData;
        this.testData.pma = new PMA();
        this.testData.errorMessageHolder = new ErrorMessageHolder();
    }


    @When("the user request to see available employees")
    public void the_user_request_to_see_available_employees() {
        testData.pma.availableEmployees();
    }

    @Then("there is given a list with all names of available employees")
    public void there_is_given_a_list_with_all_names_of_available_employees() {
        for (Employee employee : testData.pma.availableEmployees()) {
                assertTrue(employee.EmployeeAvailable());
        }
    }
}


