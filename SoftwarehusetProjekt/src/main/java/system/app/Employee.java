/* Morten Linhardt Helsø - s214927
 *
 *  */

package system.app;

import java.util.ArrayList;

public class Employee {
    public String employeeId;
    public ArrayList<Activity> activities;
    public ArrayList<Project> projects;

    public Employee(String employeeId) {
        this.employeeId = employeeId;
        this.activities = new ArrayList<>();
        this.projects = new ArrayList<>();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public boolean EmployeeAvailable() {
        boolean holder = false;
        assert true: "precondition for EmployeeAvailable";
        if(this.activities == null || this.activities.size() < 10) {
            assert this.activities.size() < 10 || this.activities.isEmpty(): "postcondition for EmployeeAvailable";
            holder = true;
        }
            return holder;
    }
}