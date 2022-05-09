/* Morten Linhardt Helsø - s214927
 *
 *  */

package dtu.softwarehuset;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import system.app.Employee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EditActivity {
    String name;
    TestData testData;


    public EditActivity(TestData testData){this.testData = testData;}

    @Given("there is a name {string}")
    public void there_is_a_name(String string) {
        this.name = "testing 2";
       assertEquals(string, name);
    }

    @When("the name is edited")
    public void the_name_is_edited() {
       testData.activity.setName(name);


    }

    @Then("the name of the activity is {string}")
    public void the_name_of_the_activity_is(String string) {
        assertEquals(testData.activity.getName(), name);
    }
    @Given("an employee with the initials {string} is assigned to the activity")
    public void an_employee_with_the_initials_is_assigned_to_the_activity(String string) {
        testData.employee = new Employee(string);
        testData.activity.staffActivity(testData.employee);
    }

    @When("the employee with the initials {string} is replaced by the employee with the initials {string}")
    public void the_employee_with_the_initials_is_replaced_by_the_employee_with_the_initials(String string, String string2) {
        testData.activity.staffActivity(testData.employee = new Employee(string2));
        assertTrue(string != testData.employee.getEmployeeId());
    }

    @Then("the activity has the employee with the initials {string}")
    public void the_activity_has_the_employee_with_the_initials(String string) {
        assertEquals(string, testData.activity.getEmployeeId());
    }

    @When("the name of the activity is edited to {string}")
    public void the_name_of_the_activity_is_edited_to(String string) {
        testData.activity.setName(string);
    }



}
