package WhiteBoxTest3;

import org.junit.Test;
import static org.junit.Assert.*;

public class Test3 {

	Project project = new Project("projectName");
	@Test
	public void testDataSetA() throws Exception {
		try {
			project.isProjectManager(new Employee("jens"));
		} catch (Exception e){
			assertEquals(e.getMessage(), "Projectmanager is not registered");
		}
	}

	@Test
	public void testDataSetB() throws Exception {
		Employee employee = new Employee("hans");
		project.projectManager = employee;

		try {
			project.isProjectManager(new Employee("fred"));
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Projectmanager is not registered");
		}
	}

	@Test
	public void testDataSetC() throws Exception {
		Employee employee = new Employee("hans");
		project.projectManager = employee;
		assertTrue(project.isProjectManager(employee));
	}
}

class Employee {
	String name;
	Employee(String name){
		this.name = name;
	}
}

class Project {
	Employee projectManager;
	String name;
	Project(String name){
		this.name = name;
		this.projectManager = null;
	}

	public boolean isProjectManager(Employee employee) throws Exception{
		if(this.projectManager == null) {                               // 1
			throw new Exception("Projectmanager is not registered");    // 1.a
		} else if (this.projectManager != employee) {                   // 2
			throw new Exception("Projectmanager is not registered");    // 2.a
		} else return true;                                             // 3
	}
}