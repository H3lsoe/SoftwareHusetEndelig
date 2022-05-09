package WhiteBoxTest4;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class Test4 {
	ArrayList<Employee> employees = new ArrayList<>();
	@Test
	public void testDataSetA() throws Exception {
		try {
			availableEmployees();
		} catch (Exception e){
			assertEquals(e.getMessage(), "No available employees");
		}
	}

	@Test
	public void testDataSetB() throws Exception {
		employees.add(new Employee("loli", 5));
		assertEquals(availableEmployees(),employees);
	}

	@Test
	public void testDataSetC() throws Exception {
		Employee employee1 = new Employee("poli", 10);
		Employee employee2 = new Employee("tolk", 6);
		employees.add(employee1);
		employees.add(employee2);
		assertEquals(availableEmployees().size(), 1);
	}

	
	public ArrayList<Employee> availableEmployees() throws Exception {
        ArrayList<Employee> availableEmployees = new ArrayList<Employee>();
        for(Employee employee : employees) {								// 1
			if (employee.EmployeeAvailable()) {								// 2
				availableEmployees.add(employee);							// 2.a
			}
		} if(availableEmployees.size() == 0) {								// 3
			throw new Exception("No available employees");					// 3.a
		} else return availableEmployees;									// 4
    }
}

class Activity {
	String activityName;
	Activity(){
		this.activityName = null;
	}
}

class Employee {
	ArrayList<Activity> activities = new ArrayList<Activity>();
	String name;

	Employee(String name){
		this.name = name;
	}

	Employee(int amountOfActivities){
		for(int i = 0; i < amountOfActivities; i++) {
			this.activities.add(new Activity());
		}
	}

	Employee(String name, int amountOfActivities){
		this.name = name;
		for(int i = 0; i < amountOfActivities; i++) {
			this.activities.add(new Activity());
		}
	}

	public boolean EmployeeAvailable() {
		if(this.activities == null || this.activities.size() < 10) {
			return true;
		} else return false;
	}

	public String EmployeeAvailableTestOutput() {
		if(this.activities == null || this.activities.size() < 10) {
			return "Yes";
		} else return "No";
	}
}
