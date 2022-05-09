/*
 *  Rasmus Søndergaard - s214925
 *  */
package system.app;


public class Activity {
    String name;
    Employee employee;

    public Activity(String name) {
        this.name = name;
        this.employee = null;
    }

    public void staffActivity(Employee employee) {
        assert true: "precondition for assignEmployeeActivities";
        this.employee = employee;
        if(!employee.activities.contains(this)) {
            employee.activities.add(this);
        }
        assert employee.activities.contains(this):"postcondition for assignEmployeeActivities";

    }

    public boolean isActivityStaffed() {
        return this.employee != null;
    }


    public void setName(String name){
        this.name = name;
    }


    public String getEmployeeId() {
        return this.employee.employeeId;
    }

    public String getName(){
        return this.name;
    }




}