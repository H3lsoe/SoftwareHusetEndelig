package WhiteBoxTest2;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class Test2 {
	ArrayList<Project> projects = new ArrayList<>();
	@Test
	public void testDataSetA() throws Exception{
		try {
			existProjectName("abcd");
		} catch (Exception e){
			assertEquals(e.getMessage(), "Project does not exist");
		}

	}

	@Test
	public void testDataSetB() throws Exception {
		projects.add(new Project("name"));
		assertTrue(existProjectName("name"));
	}

	public Boolean existProjectName(String projectName) throws Exception {
        for (Project project : projects) {						// 1
            if (project.name.equals(projectName)) {				// 2
                return true;									// 2.a
            }
        } throw new Exception("Project does not exist");		// 3
    }
}

class Project {
	String name;
	Project(String projectName){
		this.name = projectName;
	}
}
